package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Comun.clsOrdenarPorAntiguedad;
import Comun.clsOrdenarPorElo;
import LN.clsGestor;
import LN.clsUsuario;

import javax.swing.JButton;
import javax.swing.JLabel;

public class clsRankingLista extends JFrame
{
	private static final long serialVersionUID = 1L;

	static ArrayList <clsUsuario> listaUsuarios;
	
	private JLabel lblInformacion;
	private JRadioButton rdbtnOrdenPuntos;
	private JRadioButton rdbtnOrdenNick;
	private JRadioButton rdbtnOrdenAntig;
	private ButtonGroup btngrp;
	private JButton btnSalir;
	
	private JPanel paneltabla;
	private JPanel panelbotonera;
	
	public clsRankingLista(String titulo)
	{
		super(titulo);
		getContentPane().setLayout(new BorderLayout());
		
		paneltabla=new JPanel();
	    panelbotonera=new JPanel();
	    
	    getContentPane().setBackground(Color.white);
	    panelbotonera.setLayout(new FlowLayout());
	    getContentPane().add(paneltabla, BorderLayout.NORTH);
	    getContentPane().add(panelbotonera, BorderLayout.SOUTH);
	    panelbotonera.setBackground(Color.white);

		lblInformacion = new JLabel("Ordenar por:");
		panelbotonera.add(lblInformacion, BorderLayout.CENTER);
		
		rdbtnOrdenPuntos = new JRadioButton("Puntuación (Elo)");
		rdbtnOrdenPuntos.setBounds(51, 400, 128, 23);
		panelbotonera.add(rdbtnOrdenPuntos);
		
		rdbtnOrdenNick = new JRadioButton("Nickname");
		rdbtnOrdenNick.setBounds(262, 400, 109, 23);
		panelbotonera.add(rdbtnOrdenNick);
		
		rdbtnOrdenAntig = new JRadioButton("Antigüedad");
		rdbtnOrdenAntig.setBounds(426, 400, 109, 23);
		panelbotonera.add(rdbtnOrdenAntig);
		
		btngrp = new ButtonGroup();
		btngrp.add(rdbtnOrdenPuntos);
		btngrp.add(rdbtnOrdenNick);
		btngrp.add(rdbtnOrdenAntig);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(612, 400, 89, 23);
		panelbotonera.add(btnSalir);
		
		this.setPreferredSize(new Dimension(750,500));

		clsGestor objGestor = new clsGestor();
		listaUsuarios = objGestor.ListaUsuarios();
		
		//Escuchadores.
		rdbtnOrdenPuntos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Collections.sort(listaUsuarios, new clsOrdenarPorElo());
				
				TablaOrden t=new TablaOrden(listaUsuarios);
				t.setOpaque(true); //content panes must be opaque
				getContentPane().add(t, BorderLayout.NORTH);
				
		        pack();
		        setVisible(true);
			}
		});
		rdbtnOrdenNick.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Collections.sort(listaUsuarios);
				TablaOrden t=new TablaOrden(listaUsuarios);
				t.setOpaque(true); //content panes must be opaque
				getContentPane().add(t, BorderLayout.NORTH);
		        
		        pack();
		        setVisible(true);
			}
		});
		rdbtnOrdenAntig.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Collections.sort(listaUsuarios, new clsOrdenarPorAntiguedad());
				TablaOrden t=new TablaOrden(listaUsuarios);
				t.setOpaque(true); //content panes must be opaque
				getContentPane().add(t, BorderLayout.NORTH);
				
		        pack();
		        setVisible(true);
		        
//		        paneltabla.add(t);		
			}			
		});
		btnSalir.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();	
			}
		});
	}
	
	

	
}	