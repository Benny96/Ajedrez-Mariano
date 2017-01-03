package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
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
	
private static final boolean ANYADIR_A_FIC_LOG = true;  // poner true para hacer append en cada ejecución
	
	// Logger de la clase
		private static Logger logger = Logger.getLogger( "Mariano" );
		static {
			try {
				logger.setLevel( Level.FINEST );
				Formatter f = new SimpleFormatter() {
					@Override
					public synchronized String format(LogRecord record) {
						// return super.format(record);  // Si no queremos el formateador con tanta información
						if (record.getLevel().intValue()<Level.CONFIG.intValue())
							// Si es menor que CONFIG lo sacamos muy tabulado a la derecha
							return "\t\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
						if (record.getLevel().intValue()<Level.WARNING.intValue())
							// Si es menor que WARNING lo sacamos tabulado a la derecha
							return "\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
						return "(" + record.getLevel() + ") " + record.getMessage() + "\n";
					}
				};
				FileOutputStream fLog = new FileOutputStream( "Mariano"+".log" , ANYADIR_A_FIC_LOG );
				Handler h = new StreamHandler( fLog, f );
				h.setLevel( Level.FINEST );
				logger.addHandler( h );  // Saca todos los errores a out
//				logger.addHandler( new FileHandler( ListaDeReproduccion.class.getName()+".log.xml", ANYADIR_A_FIC_LOG ));
			} catch (SecurityException | IOException e) {
				logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsRankingLista.class.getName() );
			}
			logger.log( Level.INFO, "" );
			logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
		}
		
	public clsRankingLista(String titulo)
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
				logger.log( Level.INFO, "Obteniendo los jugadores ordenados por Elo");
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
				logger.log( Level.INFO, "Obteniendo los jugadores ordenados por Nickname");
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
				logger.log( Level.INFO, "Obteniendo los jugadores ordenados por Antigüedad");
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