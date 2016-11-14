package GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
	
	JTextArea textArea;
	private JLabel lblInformacion;
	private JScrollPane scrollPane;
	private JRadioButton rdbtnOrdenPuntos;
	private JRadioButton rdbtnOrdenNick;
	private JRadioButton rdbtnOrdenAntig;
	private ButtonGroup btngrp;
	private JButton btnSalir;
	
	public clsRankingLista(String titulo)
	{
		super(titulo);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 27, 650, 341);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		
		lblInformacion = new JLabel("Ordenar por:");
		lblInformacion.setBounds(307, 379, 109, 14);
		getContentPane().add(lblInformacion);
		
		rdbtnOrdenPuntos = new JRadioButton("Puntuación (Elo)");
		rdbtnOrdenPuntos.setBounds(51, 400, 128, 23);
		getContentPane().add(rdbtnOrdenPuntos);
		
		rdbtnOrdenNick = new JRadioButton("Nickname");
		rdbtnOrdenNick.setBounds(262, 400, 109, 23);
		getContentPane().add(rdbtnOrdenNick);
		
		rdbtnOrdenAntig = new JRadioButton("Antigüedad");
		rdbtnOrdenAntig.setBounds(426, 400, 109, 23);
		getContentPane().add(rdbtnOrdenAntig);
		
		btngrp = new ButtonGroup();
		btngrp.add(rdbtnOrdenPuntos);
		btngrp.add(rdbtnOrdenNick);
		btngrp.add(rdbtnOrdenAntig);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(612, 400, 89, 23);
		getContentPane().add(btnSalir);
		
		this.setPreferredSize(new Dimension(750,500));
		this.setResizable(false);

		clsGestor objGestor = new clsGestor();
		listaUsuarios = objGestor.ListaUsuarios();
		
		//Escuchadores.
		rdbtnOrdenPuntos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				textArea.setText("");
				Collections.sort(listaUsuarios, new clsOrdenarPorElo());
				StringBuffer strb = new StringBuffer();
				for (clsUsuario auxu: listaUsuarios)
				{
					strb.append(auxu.toString());
					strb.append("\n");
				}
				textArea.setText(strb.toString());
			}
		});
		rdbtnOrdenNick.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				textArea.setText("");
				Collections.sort(listaUsuarios);
				StringBuffer strb = new StringBuffer();
				for (clsUsuario auxu: listaUsuarios)
				{
					strb.append(auxu.toString());
					strb.append("\n");
				}
				textArea.setText(strb.toString());
			}
		});
		rdbtnOrdenAntig.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				textArea.setText("");
				Collections.sort(listaUsuarios, new clsOrdenarPorAntiguedad());
				StringBuffer strb = new StringBuffer();
				for (clsUsuario auxu: listaUsuarios)
				{
					strb.append(auxu.toString());
					strb.append("\n");
				}
				textArea.setText(strb.toString());
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

