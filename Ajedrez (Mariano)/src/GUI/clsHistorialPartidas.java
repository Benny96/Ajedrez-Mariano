package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import Comun.clsConstantes;
import Comun.clsOrdenarPorGanador1v1;
import Comun.clsOrdenarPorGanadorMariano;
import Mariano.TableroLogicoMariano;
import Persistencia.clsBD;
import Unopauno.TableroLogico1v1;
/**
 * Clase que generará una JFrame para mostrar los datos de las partidas hechas entre jugadores o contra Mariano.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsHistorialPartidas extends JFrame
{
	private static final long serialVersionUID = 1L;

	static ArrayList <TableroLogico1v1> listaPartidas1v1;
	static ArrayList <TableroLogicoMariano> listaPartidasMariano;
	
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
	
	private static final boolean ANYADIR_A_FIC_LOG = true;
	
	/*Logger de la clase*/
	private static Logger logger = Logger.getLogger( "Mariano" );
	static 
	{
		try 
		{
			logger.setLevel( Level.FINEST );
			Formatter f = new SimpleFormatter() 
			{
				@Override
				public synchronized String format(LogRecord record) 
				{
					if (record.getLevel().intValue()<Level.CONFIG.intValue())
						return "\t\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
					if (record.getLevel().intValue()<Level.WARNING.intValue())
						return "\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
					return "(" + record.getLevel() + ") " + record.getMessage() + "\n";
				}
			};
			FileOutputStream fLog = new FileOutputStream( "Mariano"+".log" , ANYADIR_A_FIC_LOG );
			Handler h = new StreamHandler( fLog, f );
			h.setLevel( Level.FINEST );
			logger.addHandler( h );
			}
		catch (SecurityException | IOException e) 
		{
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsHistorialPartidas.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}
	
	/**
	 * Constructor del JFrame que incluye escuchadores varios.
	 * @param titulo Título de la ventana.
	 */
	public clsHistorialPartidas(String titulo)
	{
		super(titulo);
		getContentPane().setLayout(new BorderLayout());
		this.setIconImage(new ImageIcon(getClass().getResource("/img/Rajoy.png")).getImage());
		paneltabla=new JPanel();
	    panelbotonera=new JPanel();    
	    
	    getContentPane().setBackground(Color.white);
	    panelbotonera.setLayout(new FlowLayout());
	    getContentPane().add(paneltabla, BorderLayout.NORTH);
	    getContentPane().add(panelbotonera, BorderLayout.SOUTH);
	    panelbotonera.setBackground(Color.white);
		
		rdbtn1v1 = new JRadioButton("Partidas 1v1");
		rdbtn1v1.setBackground(Color.white);
		panelbotonera.add(rdbtn1v1, BorderLayout.LINE_START);
		
		rdbtnMariano = new JRadioButton("Partidas contra Mariano");
		rdbtnMariano.setBackground(Color.white);
		panelbotonera.add(rdbtnMariano);
		
		btngrp1 = new ButtonGroup();
		btngrp1.add(rdbtn1v1);
		btngrp1.add(rdbtnMariano);
		
		lblInformacion = new JLabel("    Ordenar por:    ");
		Font labelFont = lblInformacion.getFont();
		lblInformacion.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
		panelbotonera.add(lblInformacion, BorderLayout.CENTER);
		
		rdbtnOrdenID = new JRadioButton("Nickname");
		rdbtnOrdenID.setBackground(Color.white);
		panelbotonera.add(rdbtnOrdenID);
		
		rdbtnOrdenGanador = new JRadioButton("Ganador");
		rdbtnOrdenGanador.setBackground(Color.white);
		panelbotonera.add(rdbtnOrdenGanador);
		
		btngrp2 = new ButtonGroup();
		btngrp2.add(rdbtnOrdenID);
		btngrp2.add(rdbtnOrdenGanador);
		
		btnSalir = new JButton("Salir");
		panelbotonera.add(btnSalir);
		
		this.setPreferredSize(new Dimension(750,500));
		
		listaPartidas1v1 = new ArrayList <TableroLogico1v1>();
		listaPartidasMariano = new ArrayList <TableroLogicoMariano>();

		ResultSet rs = clsBD.obtenerDatosTablaBD (clsConstantes.PARTIDA);
		logger.log( Level.INFO, "Recogiendo datos de la BD");
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
						listaPartidasMariano.add(new TableroLogicoMariano(
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
		/*Escuchadores*/
		rdbtn1v1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (rdbtnOrdenID.isSelected())
				{
					logger.log( Level.INFO, "Obteniendo el Historial 1v1, orden: ID ");
					Collections.sort(listaPartidas1v1);
					clsTablaHistorial1v1 t=new clsTablaHistorial1v1(listaPartidas1v1);
					t.setOpaque(true); //content panes must be opaque
					getContentPane().add(t, BorderLayout.NORTH);
			        pack();
			        setVisible(true);
				}
				else if (rdbtnOrdenGanador.isSelected())
				{
					logger.log( Level.INFO, "Obteniendo el Historial 1v1, orden: Ganador ");
					Collections.sort(listaPartidas1v1, new clsOrdenarPorGanador1v1());
					clsTablaHistorial1v1 t=new clsTablaHistorial1v1(listaPartidas1v1);
					t.setOpaque(true); //content panes must be opaque
					getContentPane().add(t, BorderLayout.NORTH);
			        pack();
			        setVisible(true);
				}
			}
		});
		rdbtnMariano.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (rdbtnOrdenID.isSelected())
				{
					logger.log( Level.INFO, "Obteniendo el Historial contra Mariano, orden: ID ");
					Collections.sort(listaPartidasMariano);
					clsTablaHistorialMariano t=new clsTablaHistorialMariano(listaPartidasMariano);
					t.setOpaque(true); //content panes must be opaque
					getContentPane().add(t, BorderLayout.NORTH);      
			        pack();
			        setVisible(true);
				}
				else if (rdbtnOrdenGanador.isSelected())
				{
					logger.log( Level.INFO, "Obteniendo el Historial contra Mariano, orden: Ganador ");
					Collections.sort(listaPartidasMariano, new clsOrdenarPorGanadorMariano());
					clsTablaHistorialMariano t=new clsTablaHistorialMariano(listaPartidasMariano);
					t.setOpaque(true); //content panes must be opaque
					getContentPane().add(t, BorderLayout.NORTH);
			        pack();
			        setVisible(true);
				}
			}
		});
		rdbtnOrdenID.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (rdbtn1v1.isSelected())
				{
					logger.log( Level.INFO, "Obteniendo el orden por ID: Historial 1v1");
					Collections.sort(listaPartidas1v1);
					clsTablaHistorial1v1 t=new clsTablaHistorial1v1(listaPartidas1v1);
					t.setOpaque(true);
					getContentPane().add(t, BorderLayout.NORTH);
			        pack();
			        setVisible(true);
				}
				else
				{
					logger.log( Level.INFO, "Obteniendo el orden por ID: Historial contra Mariano");
					Collections.sort(listaPartidasMariano);
					clsTablaHistorialMariano t=new clsTablaHistorialMariano(listaPartidasMariano);
					t.setOpaque(true);
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
					logger.log( Level.INFO, "Obteniendo el orden por Ganador: Historial 1v1");
					Collections.sort(listaPartidas1v1, new clsOrdenarPorGanador1v1());
					clsTablaHistorial1v1 t=new clsTablaHistorial1v1(listaPartidas1v1);
					t.setOpaque(true);
					getContentPane().add(t, BorderLayout.NORTH);
			        pack();
			        setVisible(true);
				}
				else if (!(rdbtn1v1.isSelected()))
				{
					logger.log( Level.INFO, "Obteniendo el orden por Ganador: Historial contra Mariano");
					Collections.sort(listaPartidasMariano, new clsOrdenarPorGanadorMariano());
					clsTablaHistorialMariano t=new clsTablaHistorialMariano(listaPartidasMariano);
					t.setOpaque(true);
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