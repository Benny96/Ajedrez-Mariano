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
import Comun.clsOrdenarPorGanador1v1;
import Comun.clsOrdenarPorGanadorMariano;
import Mariano.tablerologico1;
import Persistencia.clsBD;
import Unopauno.TableroLogico1v1;

public class clsHistorialPartidas extends JFrame
{
	private static final long serialVersionUID = 1L;

	static ArrayList <TableroLogico1v1> listaPartidas1v1;
	static ArrayList <tablerologico1> listaPartidasMariano;
	
	private JLabel lblInformacion;
	private JRadioButton rdbtn1v1;
	private JRadioButton rdbtnMariano;
	private JRadioButton rdbtnOrdenID;
	private JRadioButton rdbtnOrdenGanador;
	private ButtonGroup btngrp1;
	private ButtonGroup btngrp2;
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
		
		rdbtn1v1 = new JRadioButton("Partidas 1v1");
		panelbotonera.add(rdbtn1v1, BorderLayout.LINE_START);
		
		rdbtnMariano = new JRadioButton("Partidas contra Mariano");
		panelbotonera.add(rdbtnMariano);
		
		btngrp1 = new ButtonGroup();
		btngrp1.add(rdbtn1v1);
		btngrp1.add(rdbtnMariano);
		
		lblInformacion = new JLabel("Ordenar por:");
		panelbotonera.add(lblInformacion, BorderLayout.CENTER);
		
		rdbtnOrdenID = new JRadioButton("Nickname");
		panelbotonera.add(rdbtnOrdenID);
		
		rdbtnOrdenGanador = new JRadioButton("Ganador");
		panelbotonera.add(rdbtnOrdenGanador);
		
		btngrp2 = new ButtonGroup();
		btngrp2.add(rdbtnOrdenID);
		btngrp2.add(rdbtnOrdenGanador);
		
		btnSalir = new JButton("Salir");
		panelbotonera.add(btnSalir);
		
		this.setPreferredSize(new Dimension(750,500));
		
		listaPartidas1v1 = new ArrayList <TableroLogico1v1>();
		listaPartidasMariano = new ArrayList <tablerologico1>();

		ResultSet rs = clsBD.obtenerDatosTablaBD (clsConstantes.PARTIDA);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					if (rs.getString("USUARIO2").compareTo("Mariano")!=0)
					{
						listaPartidas1v1.add(new TableroLogico1v1(
								rs.getInt("ID_PARTIDA"),
								rs.getString("USUARIO1"),
								rs.getString("USUARIO2"),
								rs.getLong("DIA_COM"),
								rs.getLong("DIA_FIN"),
								rs.getString("GANADOR")));
					}
					else
					{
						listaPartidasMariano.add(new tablerologico1(
								rs.getInt("ID_PARTIDA"),
								rs.getString("USUARIO1"),
								rs.getString("USUARIO2"),
								rs.getLong("DIA_COM"),
								rs.getLong("DIA_FIN"),
								rs.getString("GANADOR")));
					}
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
				if (rdbtn1v1.isSelected())
				{
					Collections.sort(listaPartidas1v1);
					clsTablaHistorial1v1 t=new clsTablaHistorial1v1(listaPartidas1v1);
					t.setOpaque(true); //content panes must be opaque
					getContentPane().add(t, BorderLayout.NORTH);

			        pack();
			        setVisible(true);
				}
				else
				{
					Collections.sort(listaPartidasMariano);
					clsTablaHistorialMariano t=new clsTablaHistorialMariano(listaPartidasMariano);
					t.setOpaque(true); //content panes must be opaque
					getContentPane().add(t, BorderLayout.NORTH);
			        
			        pack();
			        setVisible(true);
				}
			}
		});
		rdbtnOrdenGanador.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (rdbtn1v1.isSelected())
				{
					Collections.sort(listaPartidas1v1, new clsOrdenarPorGanador1v1());
					clsTablaHistorial1v1 t=new clsTablaHistorial1v1(listaPartidas1v1);
					t.setOpaque(true); //content panes must be opaque
					getContentPane().add(t, BorderLayout.NORTH);
			        
			        pack();
			        setVisible(true);
				}
				else if (!(rdbtn1v1.isSelected()))
				{
					Collections.sort(listaPartidasMariano, new clsOrdenarPorGanadorMariano());
					clsTablaHistorialMariano t=new clsTablaHistorialMariano(listaPartidasMariano);
					t.setOpaque(true); //content panes must be opaque
					getContentPane().add(t, BorderLayout.NORTH);
			        
			        pack();
			        setVisible(true);
				}	
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
