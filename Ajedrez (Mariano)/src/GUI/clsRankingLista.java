package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Comun.clsOrdenarPorPuntuacion;
import LN.clsGestor;
import LN.clsUsuario;

import javax.swing.JButton;
import javax.swing.JLabel;

public class clsRankingLista extends JFrame
{
	private static final long serialVersionUID = 1L;

	static ArrayList <clsUsuario> listaUsuarios;
	
	JTextArea textArea;
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
		scrollPane.setBounds(51, 27, 457, 288);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblNewLabel = new JLabel("Ordenar por:");
		lblNewLabel.setBounds(51, 326, 109, 14);
		getContentPane().add(lblNewLabel);
		
		rdbtnOrdenPuntos = new JRadioButton("Puntuación");
		rdbtnOrdenPuntos.setBounds(51, 358, 109, 23);
		getContentPane().add(rdbtnOrdenPuntos);
		
		rdbtnOrdenNick = new JRadioButton("Nickname");
		rdbtnOrdenNick.setBounds(162, 358, 109, 23);
		getContentPane().add(rdbtnOrdenNick);
		
		rdbtnOrdenAntig = new JRadioButton("Antigüedad");
		rdbtnOrdenAntig.setBounds(274, 358, 109, 23);
		getContentPane().add(rdbtnOrdenAntig);
		
		btngrp = new ButtonGroup();
		btngrp.add(rdbtnOrdenPuntos);
		btngrp.add(rdbtnOrdenNick);
		btngrp.add(rdbtnOrdenAntig);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(419, 358, 89, 23);
		getContentPane().add(btnSalir);

		clsGestor objGestor = new clsGestor();
		listaUsuarios = objGestor.ListaUsuarios();
		
		//Escuchadores.
		//TODO: ORDENACIONES PENDIENTES DE IMPLEMENTACIÓN.
		rdbtnOrdenPuntos.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Collections.sort(listaUsuarios, new clsOrdenarPorPuntuacion());
				StringBuffer strb = new StringBuffer();
				
			}
		});
		
	}
}

