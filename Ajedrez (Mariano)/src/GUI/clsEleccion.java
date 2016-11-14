package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import LN.clsUsuario;

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
	private JRadioButton rdbtnGrafico;
	private ButtonGroup btngrpRanking;
	
	static JFrame miVentana;
	private clsUsuario usuario;
	
	ArrayList<clsUsuario> usus=new ArrayList<clsUsuario>();

	public clsEleccion(clsUsuario usu) 
	{
		desktop = new JDesktopPane();
		setContentPane(desktop);
		
//		desktop=new JPanel();
		this.setBounds(350, 200, 720, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Elección - Ajedrez: Mariano");
		desktop.setBackground(Color.WHITE);
		desktop.setLayout(null);
		
		usuario = usu;
		
		lblBienvenida = new JLabel("Bienvenido, "+usuario.getNickname()+". Elige lo que desees hacer.");
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
		btnHistorial.setBounds(425, 236, 171, 50);
		desktop.add(btnHistorial);
		
		btnRanking = new JButton("Ranking");
		btnRanking.setBounds(425, 124, 171, 50);
		desktop.add(btnRanking);
		
		btnModificar = new JButton("Modificar cuenta");
		btnModificar.setBounds(425, 317, 171, 50);
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
		rdbtnLista.setBounds(425, 206, 71, 23);
		desktop.add(rdbtnLista);
		
		rdbtnGrafico = new JRadioButton("Gráfico");
		rdbtnGrafico.setBounds(517, 206, 79, 23);
		desktop.add(rdbtnGrafico);
		
		btngrpRanking = new ButtonGroup();
		btngrpRanking.add(rdbtnLista);
		btngrpRanking.add(rdbtnGrafico);
		
		btnDeslogear = new JButton("Deslogear");
		btnDeslogear.setBounds(316, 387, 109, 23);
		desktop.add(btnDeslogear);

		miVentana = this;
		
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
					clsTablero frame = new clsTablero();
		            frame.setVisible(true);
		            miVentana.dispose();
				}
				if(rdbtnMarianoMedio.isSelected())
				{
					//TODO: Redirigir a tablero de dificultad media.
					clsTablero frame = new clsTablero();
		            frame.setVisible(true);
		            miVentana.dispose();
				}
				if(rdbtnMarianoDificil.isSelected())
				{
					//TODO: Redirigir a tablero de dificultad difícil.
					clsTablero frame = new clsTablero();
		            frame.setVisible(true);
		            miVentana.dispose();
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
					clsRankingLista frame = new clsRankingLista("Rankings");
					frame.pack();
					frame.setVisible(true);
				}
				if(rdbtnGrafico.isSelected())
				{
					//TODO: Codificar gráficos visuales. [Implementación de librería JFreeChart]
				}
			}
		});
		btnModificar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				clsModificarUsuario frame = new clsModificarUsuario(usuario);
				frame.setVisible(true);
			}	
		});
		btnHistorial.addActionListener(new ActionListener()
		{
		//TODO: Conexión con la Base de Datos.	
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}		
		});
		btnDeslogear.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JOptionPane.showMessageDialog(miVentana, "Esperemos que haya disfrutado de las partidas.");
				miVentana.dispose();
				clsPaginaPrincipal frame = new clsPaginaPrincipal();
				frame.setVisible(true);
			}
		});
	}
}
