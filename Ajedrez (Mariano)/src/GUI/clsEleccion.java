package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
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
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import org.jfree.ui.RefineryUtilities;

import LN.clsUsuario;

/**
 * Clase que generar� una JFrame para acceder a las diversas opciones de la aplicaci�n: <br>
 * 1) Jugar partidas contra otro jugador. <br>
 * 2) Jugar partidas contra Mariano. <br>
 * 3) Visualizar datos de los jugadores: <br>
 * -> Lista. <br>
 * -> Histograma. <br>
 * -> Queso. <br>
 * 4) Visualizar el historial de las partidas jugadas. <br>
 * 5) Modificar la cuenta del usuario que est� utilizando la aplicaci�n.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Be�at Gald�s (Benny96)
 */
public class clsEleccion extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	static JDesktopPane desktop;

	private JLabel lblBienvenida;
	private JLabel lblModosJuego;
	private JLabel lblOtros;
	private JLabel lblDificultad;
	private JLabel lblVisualizacion;
	
	private JButton btnJugadorvsJugador;
	private JButton btnJugadorvsMariano;
	private JButton btnRanking;
	private JButton btnModificar;
	private JButton btnHistorial;
	private JButton btnDeslogear;
	
	private JRadioButton rdbtnLista;
	private JRadioButton rdbtnGraficoHistograma;
	private JRadioButton rdbtnGraficoQueso;
	private ButtonGroup btngrpRanking;
	
	static JFrame miVentana;
	static clsUsuario usuario;
	clsEleccion a;
	Graphics g;
	
	ArrayList<clsUsuario> usus=new ArrayList<clsUsuario>();
	
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
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsEleccion.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}

	/**
	 * Constructor del JFrame que genera la parte visual de la ventana, as� como los escuchadores requeridos para redirigir a cada funcionalidad de la aplicaci�n.
	 * @param usu Usuario que est� logeado en el sistema.
	 */
	public clsEleccion(clsUsuario usu) 
	{
		desktop = new JDesktopPane()
		{ 
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics g)
			{
		    	 g.setColor(Color.LIGHT_GRAY);
		         g.drawLine(415, 115, 415, 228);
		         g.drawLine(415, 228, 637, 228);
		         g.drawLine(637, 228, 637, 115);
		         g.drawLine(637, 115, 415, 115);
		    }
		};
		setContentPane(desktop);
		this.setBounds(350, 200, 720, 480);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Elecci�n - Ajedrez: Mariano");
		this.setIconImage(new ImageIcon(getClass().getResource("/img/Rajoy.png")).getImage());
		desktop.setBackground(Color.WHITE);
		desktop.setLayout(null);
		
		usuario = usu;

		lblBienvenida = new JLabel("�Bienvenid@, "+usuario.getNickname()+"! . Elige lo que desees hacer.");
		lblBienvenida.setBounds(60, 35, 560, 34);
		Font labelFont = lblBienvenida.getFont();
		lblBienvenida.setFont(new Font(labelFont.getName(), Font.BOLD, 16));
		desktop.add(lblBienvenida);
		
		lblModosJuego = new JLabel("Modos de juego:");
		lblModosJuego.setBounds(60, 76, 153, 14);
		lblModosJuego.setFont(new Font(labelFont.getName(), Font.BOLD, 12));
		desktop.add(lblModosJuego);
		
		lblOtros = new JLabel("Otros:");
		lblOtros.setBounds(425, 76, 46, 14);
		lblOtros.setFont(new Font(labelFont.getName(), Font.BOLD, 12));
		desktop.add(lblOtros);
		
		btnJugadorvsJugador = new JButton("Jugador vs Jugador");
		btnJugadorvsJugador.setBounds(60, 124, 257, 50);
		desktop.add(btnJugadorvsJugador);
		
		btnHistorial = new JButton("Historial de partidas");
		btnHistorial.setBounds(425, 236, 202, 50);
		desktop.add(btnHistorial);
		
		btnRanking = new JButton("Ranking");
		btnRanking.setBounds(425, 124, 202, 50);
		desktop.add(btnRanking);
		
		btnModificar = new JButton("Modificar cuenta");
		btnModificar.setBounds(425, 317, 202, 50);
		desktop.add(btnModificar);
		
		btnJugadorvsMariano = new JButton("Jugador vs Mariano");
		btnJugadorvsMariano.setBounds(60, 236, 257, 50);
		desktop.add(btnJugadorvsMariano);
		
		lblDificultad = new JLabel("Seleccione la dificultad:");
		lblDificultad.setBounds(70, 317, 153, 14);
		desktop.add(lblDificultad);
		
		lblVisualizacion = new JLabel("Elija el modo de visualizaci�n:");
		lblVisualizacion.setBounds(425, 185, 188, 14);
		desktop.add(lblVisualizacion);
		
		rdbtnLista = new JRadioButton("Lista");
		rdbtnLista.setBounds(425, 200, 62, 23);
		rdbtnLista.setBackground(Color.white);
		desktop.add(rdbtnLista);
		
		rdbtnGraficoHistograma = new JRadioButton("Histograma");
		rdbtnGraficoHistograma.setBounds(489, 200, 68, 23);
		rdbtnGraficoHistograma.setBackground(Color.white);
		desktop.add(rdbtnGraficoHistograma);
		
		rdbtnGraficoQueso = new JRadioButton("Queso");
		rdbtnGraficoQueso.setBounds(559, 200, 68, 23);
		rdbtnGraficoQueso.setBackground(Color.white);
		desktop.add(rdbtnGraficoQueso);
		
		btngrpRanking = new ButtonGroup();
		btngrpRanking.add(rdbtnLista);
		btngrpRanking.add(rdbtnGraficoHistograma);
		btngrpRanking.add(rdbtnGraficoQueso);
		
		btnDeslogear = new JButton("Deslogear");
		btnDeslogear.setBounds(316, 387, 109, 23);
		desktop.add(btnDeslogear);

		miVentana = this;
		a = this;
		
		setResizable(false);
		
		/*Escuchadores*/
		btnJugadorvsJugador.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				clsAnyadirJugador internalframe = new clsAnyadirJugador(usuario);
				internalframe.setVisible(true);
				desktop.add(internalframe);
				try 
				{
					internalframe.setSelected(true);
				} 
				catch (PropertyVetoException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		btnJugadorvsMariano.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ProgressBar pb=new ProgressBar("Preparando partida contra Mariano...", usuario, 1, null);
				pb.setVisible(true);
		        miVentana.dispose();
			}	
		});	
		btnRanking.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(rdbtnLista.isSelected())
				{
					logger.log(Level.INFO, "Mostrando rankings");
					clsRankingLista frame = new clsRankingLista("Rankings");
					frame.pack();
					frame.setVisible(true);
				}
				if(rdbtnGraficoHistograma.isSelected())
				{
					logger.log(Level.INFO, "Mostrando histograma");
					GraficoHistograma demo = new GraficoHistograma("N� de partidas realizadas por d�a");
				    demo.pack();
				    RefineryUtilities.centerFrameOnScreen(demo);
				    demo.setVisible(true);
				}
				if(rdbtnGraficoQueso.isSelected())
				{
					logger.log(Level.INFO, "Mostrando queso");
					GraficoQueso demo = new GraficoQueso("N� de victorias vs. Mariano");
				    demo.pack();
				    RefineryUtilities.centerFrameOnScreen(demo);
				    demo.setVisible(true);
				}
			}
		});
		btnModificar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				logger.log(Level.INFO, "Habilitando la modificaci�n de usuario");
				clsModificarUsuario frame = new clsModificarUsuario(usuario, a);
				frame.setVisible(true);
			}	
		});
		btnHistorial.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				logger.log(Level.INFO, "Mostrando el historial de partidas");
				clsHistorialPartidas frame = new clsHistorialPartidas("Historial de Partidas");
				frame.pack();
				frame.setVisible(true);
			}		
		});
		btnDeslogear.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Deslogear();
			}
		});
		addWindowListener( new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				Deslogear();
			}
		});	
	}
	public void RefrescarUsuario(clsUsuario a)
	{
		usuario = a;
	}
	public void Deslogear()
	{
		logger.log(Level.INFO, "Volviendo al men� principal");
		JOptionPane.showMessageDialog(miVentana, "Esperemos que haya disfrutado de las partidas.");
		miVentana.dispose();
		clsPaginaPrincipal frame = new clsPaginaPrincipal();
		frame.setVisible(true);
	}

	
	
}
