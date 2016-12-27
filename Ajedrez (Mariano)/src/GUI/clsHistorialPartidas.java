package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Comun.clsConstantes;
import Comun.clsOrdenarPorGanador;
import Persistencia.clsBD;

public class clsHistorialPartidas extends JFrame
{
	private static final long serialVersionUID = 1L;

	static ArrayList <tablerologico> listaPartidas;
	
	private JLabel lblInformacion;
	private JRadioButton rdbtnOrdenID;
	private JRadioButton rdbtnOrdenGanador;
	private ButtonGroup btngrp;
	private JButton btnSalir;
	
	private JPanel paneltabla;
	private JPanel panelbotonera;
	
	public clsHistorialPartidas(String titulo)
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
		
		rdbtnOrdenID = new JRadioButton("Nickname");
		rdbtnOrdenID.setBounds(262, 400, 109, 23);
		panelbotonera.add(rdbtnOrdenID);
		
		rdbtnOrdenGanador = new JRadioButton("Ganador");
		rdbtnOrdenGanador.setBounds(426, 400, 109, 23);
		panelbotonera.add(rdbtnOrdenGanador);
		
		btngrp = new ButtonGroup();
		btngrp.add(rdbtnOrdenID);
		btngrp.add(rdbtnOrdenGanador);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(612, 400, 89, 23);
		panelbotonera.add(btnSalir);
		
		this.setPreferredSize(new Dimension(750,500));
		
		listaPartidas = new ArrayList <tablerologico>();

		ResultSet rs = clsBD.obtenerDatosTablaBD (clsConstantes.PARTIDA);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					listaPartidas.add(new tablerologico(
							rs.getInt("ID_PARTIDA"),
							rs.getString("USUARIO1"),
							rs.getString("USUARIO2"),
							rs.getLong("DIA_COM"),
							rs.getLong("DIA_FIN"),
							rs.getString("GANADOR")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		//Escuchadores.
		rdbtnOrdenID.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Collections.sort(listaPartidas);
				clsTablaHistorial t=new clsTablaHistorial(listaPartidas);
				t.setOpaque(true); //content panes must be opaque
				getContentPane().add(t, BorderLayout.NORTH);
		        
		        //Display the window.
		        pack();
		        setVisible(true);
			}
		});
		rdbtnOrdenGanador.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Collections.sort(listaPartidas, new clsOrdenarPorGanador());
				clsTablaHistorial t=new clsTablaHistorial(listaPartidas);
				t.setOpaque(true); //content panes must be opaque
				getContentPane().add(t, BorderLayout.NORTH);
				
		        //Display the window.
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
