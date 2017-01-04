package GUI;

import java.awt.Color;
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

import LN.clsGestor;
import LN.clsUsuario;
import Mariano.TableroVisualMariano;
import Mariano.tablerovisual1;
import Persistencia.clsBD;
import Unopauno.TableroVisual1v1;

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
	
	private JRadioButton rdbtnMarianoFacil;
	private JRadioButton rdbtnMarianoMedio;
	private JRadioButton rdbtnMarianoDificil;
	private ButtonGroup btngrpDificultad;
	
	private JRadioButton rdbtnLista;
	private JRadioButton rdbtnGraficoLinea;
	private JRadioButton rdbtnGraficoQueso;
	private ButtonGroup btngrpRanking;
	
	static JFrame miVentana;
	static clsUsuario usuario;
	clsEleccion a;
	
	ArrayList<clsUsuario> usus=new ArrayList<clsUsuario>();
	
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
//			logger.addHandler( new FileHandler( ListaDeReproduccion.class.getName()+".log.xml", ANYADIR_A_FIC_LOG ));
		} catch (SecurityException | IOException e) {
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsEleccion.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}

	public clsEleccion(clsUsuario usu) 
	{
		desktop = new JDesktopPane();
		setContentPane(desktop);
		this.setBounds(350, 200, 720, 480);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Elección - Ajedrez: Mariano");
		this.setIconImage(new ImageIcon(getClass().getResource("/img/Rajoy.png")).getImage());
		desktop.setBackground(Color.WHITE);
		desktop.setLayout(null);
		
		usuario = usu;
		
		lblBienvenida = new JLabel("¡Bienvenido, "+usuario.getNickname()+"! . Elige lo que desees hacer.");
		lblBienvenida.setBounds(60, 35, 271, 34);
		desktop.add(lblBienvenida);
		
		lblModosJuego = new JLabel("Modos de juego:");
		lblModosJuego.setBounds(60, 80, 153, 14);
		desktop.add(lblModosJuego);
		
		lblOtros = new JLabel("Otros:");
		lblOtros.setBounds(425, 80, 46, 14);
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
		
		rdbtnMarianoFacil = new JRadioButton("Mariano");
		rdbtnMarianoFacil.setBounds(60, 343, 79, 23);
		desktop.add(rdbtnMarianoFacil);
		
		rdbtnMarianoMedio = new JRadioButton("Mariano+");
		rdbtnMarianoMedio.setBounds(141, 343, 79, 23);
		desktop.add(rdbtnMarianoMedio);
		
		rdbtnMarianoDificil = new JRadioButton("Mariano++");
		rdbtnMarianoDificil.setBounds(222, 343, 95, 23);
		desktop.add(rdbtnMarianoDificil);
		
		btngrpDificultad = new ButtonGroup();
		btngrpDificultad.add(rdbtnMarianoFacil);
		btngrpDificultad.add(rdbtnMarianoMedio);
		btngrpDificultad.add(rdbtnMarianoDificil);
		
		lblVisualizacion = new JLabel("Elija el modo de visualización:");
		lblVisualizacion.setBounds(425, 185, 188, 14);
		desktop.add(lblVisualizacion);
		
		rdbtnLista = new JRadioButton("Lista");
		rdbtnLista.setBounds(425, 206, 62, 23);
		desktop.add(rdbtnLista);
		
		rdbtnGraficoLinea = new JRadioButton("Lineal");
		rdbtnGraficoLinea.setBounds(489, 206, 68, 23);
		desktop.add(rdbtnGraficoLinea);
		
		rdbtnGraficoQueso = new JRadioButton("Queso");
		rdbtnGraficoQueso.setBounds(559, 206, 68, 23);
		desktop.add(rdbtnGraficoQueso);
		
		btngrpRanking = new ButtonGroup();
		btngrpRanking.add(rdbtnLista);
		btngrpRanking.add(rdbtnGraficoLinea);
		btngrpRanking.add(rdbtnGraficoQueso);
		
		btnDeslogear = new JButton("Deslogear");
		btnDeslogear.setBounds(316, 387, 109, 23);
		desktop.add(btnDeslogear);

		miVentana = this;
		a = this;
		
		setResizable(false);
		
		//Escuchadores
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
				if(rdbtnMarianoFacil.isSelected())
				{
					//TODO: Redirigir a tablero de dificultad fácil. Veo añadir un constructor tipo clsTablero(jugador, dificultad).
					ProgressBar pb=new ProgressBar("Preparando partida contra Mariano...", usuario, 1);
					pb.setVisible(true);
//					tablerovisual1 frame = new tablerovisual1();
//		            frame.setVisible(true);
		            miVentana.dispose();
				}
				if(rdbtnMarianoMedio.isSelected())
				{
					//TODO: Redirigir a tablero de dificultad media.
//					tablerovisual frame = new tablerovisual();
//		            frame.setVisible(true);
//		            miVentana.dispose();
				}
				if(rdbtnMarianoDificil.isSelected())
				{
					//TODO: Redirigir a tablero de dificultad difícil.
//					tablerovisual frame = new tablerovisual();
//		            frame.setVisible(true);
//		            miVentana.dispose();
				}
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
				if(rdbtnGraficoLinea.isSelected())
				{
					GraficoHistograma demo = new GraficoHistograma("Nº de partidas realizadas por día");
				    demo.pack();
				    RefineryUtilities.centerFrameOnScreen(demo);
				    demo.setVisible(true);
				}
				if(rdbtnGraficoQueso.isSelected())
				{
					GraficoQueso demo = new GraficoQueso("Nº de victorias vs. Mariano");
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
				logger.log(Level.INFO, "Habilitando la modificación de usuario");
				clsModificarUsuario frame = new clsModificarUsuario(usuario, a);
				frame.setVisible(true);
			}	
		});
		btnHistorial.addActionListener(new ActionListener()
		{
		//TODO: Conexión con la Base de Datos.	
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
		logger.log(Level.INFO, "Volviendo al menú principal");
		JOptionPane.showMessageDialog(miVentana, "Esperemos que haya disfrutado de las partidas.");
		miVentana.dispose();
		clsPaginaPrincipal frame = new clsPaginaPrincipal();
		frame.setVisible(true);
	}
}
