package GUI;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import LN.clsUsuario;
import Mariano.TableroVisualMariano;
import Unopauno.TableroVisual1v1;

/**
 * Clase que generará una JFrame que visualizará una barra de progreso cuando se inicie la aplicación y cuando se juegue una partida contra Mariano.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class ProgressBar extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	MiRunnable miHilo = null; 
	JProgressBar progressBar;
	int v1;
	int v2;
	int v3;
	int v4;
	int v5;
	
	int e1;
	int e2;
	int e3;
	int e4;
	int e5;
	
	clsUsuario usu;
	clsUsuario usu1;
	int id;

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
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ ProgressBar.class.getName() );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}
	/**
	 * Constructor de la JFrame.
	 * @param titulo Titulo de la ventana.
	 * @param aux Usuario 1 (el que ya se encuentra logeado)
	 * @param num Identificativo para distinguir un uso de ProgressBar u otro.
	 * @param aux1 Usuario 2 (en caso
	 */
	public ProgressBar(String titulo, clsUsuario aux, int num, clsUsuario aux1) {
		
		usu=aux;
		usu1=aux1;
		id=num;
		
		setBounds(450, 300, 400, 140);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/Rajoy.png")).getImage());
		
		setTitle(titulo);
		progressBar = new JProgressBar();
		progressBar.setBounds(50, 30, 300, 35);
		getContentPane().add(progressBar);
		
		Random rnd_v=new Random();
		v1=rnd_v.nextInt(100);
		v2=rnd_v.nextInt(100);
		v3=rnd_v.nextInt(100);
		v4=rnd_v.nextInt(100);
		v5=rnd_v.nextInt(100);

		e1=rnd_v.nextInt(400);
		e2=rnd_v.nextInt(400);
		e3=rnd_v.nextInt(400);
		e4=rnd_v.nextInt(400);
		e5=rnd_v.nextInt(400);

		progressBar.setBackground(Color.black);
		progressBar.setForeground(Color.blue);
		
		
		miHilo = new MiRunnable();  // Sintaxis de new para clase interna
		Thread nuevoHilo = new Thread(miHilo);
		nuevoHilo.start();
	}
	/**
	 * Método que cierra la ProgressBar y carga la nueva ventana, dependiendo de cuál sea la que deba mostrar.
	 */
	public void cerrar()
	{
		this.dispose();
		if(id==0)
		{
			logger.log( Level.INFO, "Cargando ventana clsEleccion");
			clsEleccion ventanaEleccion = new clsEleccion(usu);
			ventanaEleccion.setVisible(true);
		}
		
		if(id==1)
		{
			logger.log( Level.INFO, "Generando tablero de juego contra Mariano");
			TableroVisualMariano frame = new TableroVisualMariano(usu);
            frame.setVisible(true);
		}
		if(id==2)
		{
			TableroVisual1v1 frame = new TableroVisual1v1(usu, usu1);
		    frame.setVisible(true);
		}
	}
	/**
	 * Clase interna que implementa la interfaz Runnable que repinta la ProgressBar.
	 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
	 */
		class MiRunnable implements Runnable 
		{
			boolean sigo = true;
			@Override
			public void run() 
			{
				while (sigo) 
				{
					for(int i=0;i<=100;i++)
					{
						progressBar.setStringPainted(true);
						progressBar.setString(i + "%");
						progressBar.setValue(i);
						
						if(i==v1)
						{
							try 
							{
								Thread.sleep(e1);
							} 
							catch (InterruptedException e1) 
							{
									e1.printStackTrace();
							}
						}
							
						if(i==v2)
						{
							try 
							{
								Thread.sleep(e2);
							} 
							catch (InterruptedException e1) 
							{
								e1.printStackTrace();
							}
						}
							
						if(i==v3)
						{
							try 
							{
								Thread.sleep(e3);
							} 
							catch (InterruptedException e1) 
							{
								e1.printStackTrace();
							}
						}
							
						if(i==v4)
						{
							try 
							{
								Thread.sleep(e4);
							} 
							catch (InterruptedException e1) 
							{
								e1.printStackTrace();
							}
						}
							
						if(i==v5)
						{
							try 
							{
								Thread.sleep(e5);
							} 
							catch (InterruptedException e1) 
							{
								e1.printStackTrace();
							}
						}
						
						try 
						{
							Thread.sleep(15);
						} 
						catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}					
					sigo=false;
					cerrar();
			}
		}
	}
}
