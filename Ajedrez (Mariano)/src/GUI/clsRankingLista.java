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

/**
 * Clase que generar� una JFrame para mostrar los datos de los usuarios registrados.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Be�at Gald�s (Benny96)
 */
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
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsRankingLista.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}
	/**
	 * Constructor del JFrame que incluye escuchadores varios.
	 * @param titulo T�tulo de la ventana.
	 */	
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
		Font labelFont = lblInformacion.getFont();
		lblInformacion.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
		panelbotonera.add(lblInformacion, BorderLayout.CENTER);
		
		rdbtnOrdenPuntos = new JRadioButton("Puntuaci�n (Elo)");
		rdbtnOrdenPuntos.setBounds(51, 400, 128, 23);
		rdbtnOrdenPuntos.setBackground(Color.WHITE);
		panelbotonera.add(rdbtnOrdenPuntos);
		
		rdbtnOrdenNick = new JRadioButton("Nickname");
		rdbtnOrdenNick.setBounds(262, 400, 109, 23);
		rdbtnOrdenNick.setBackground(Color.WHITE);
		panelbotonera.add(rdbtnOrdenNick);
		
		rdbtnOrdenAntig = new JRadioButton("Antig�edad");
		rdbtnOrdenAntig.setBounds(426, 400, 109, 23);
		rdbtnOrdenAntig.setBackground(Color.WHITE);
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
		
		/*Escuchadores*/
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
				logger.log( Level.INFO, "Obteniendo los jugadores ordenados por Antig�edad");
				Collections.sort(listaUsuarios, new clsOrdenarPorAntiguedad());
				TablaOrden t=new TablaOrden(listaUsuarios);
				t.setOpaque(true); //content panes must be opaque
				getContentPane().add(t, BorderLayout.NORTH);
				
		        pack();
		        setVisible(true);	
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