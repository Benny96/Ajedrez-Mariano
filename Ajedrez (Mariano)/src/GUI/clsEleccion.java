package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import LN.clsUsuario;

public class clsEleccion extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JLabel lblBienvenida;
	private JLabel lblModosJuego;
	private JLabel lblOtros;
	private JButton btnJugadorvsJugador;
	private JButton btnJugadorvsMariano;
	private JLabel lblDificultad;
	private JRadioButton rdbtnMarianoFacil;
	private JRadioButton rdbtnMarianoMedio;
	private JRadioButton rdbtnMarianoDificil;
	private ButtonGroup btngrpDificultad;
	private JButton btnRanking;
	private JLabel lblVisualizacion;
	private JRadioButton rdbtnLista;
	private JRadioButton rdbtnGrafico;
	private ButtonGroup btngrpRanking;
	private JButton btnModificar;
	private JButton btnHistorial;
	private JButton btnSalir;
	
	static JFrame miVentana;
	
	ArrayList<clsUsuario> usus=new ArrayList<clsUsuario>();

	public clsEleccion(clsUsuario usuario) 
	{
		panel=new JPanel();
		this.setBounds(350, 200, 720, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add( panel, BorderLayout.CENTER );
		setTitle("Elección - Ajedrez: Mariano");
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		lblBienvenida = new JLabel("Bienvenido, "+usuario.getNickname()+". Elige lo que desees hacer.");
		lblBienvenida.setBounds(60, 35, 271, 34);
		panel.add(lblBienvenida);
		
		lblModosJuego = new JLabel("Modos de juego:");
		lblModosJuego.setBounds(60, 80, 153, 14);
		panel.add(lblModosJuego);
		
		lblOtros = new JLabel("Otros:");
		lblOtros.setBounds(425, 80, 46, 14);
		panel.add(lblOtros);
		
		btnJugadorvsJugador = new JButton("Jugador vs Jugador");
		btnJugadorvsJugador.setBounds(60, 124, 257, 50);
		panel.add(btnJugadorvsJugador);
		
		btnHistorial = new JButton("Historial de partidas");
		btnHistorial.setBounds(425, 236, 171, 50);
		panel.add(btnHistorial);
		
		btnRanking = new JButton("Ranking");
		btnRanking.setBounds(425, 124, 171, 50);
		panel.add(btnRanking);
		
		btnModificar = new JButton("Modificar cuenta");
		btnModificar.setBounds(425, 317, 171, 50);
		panel.add(btnModificar);
		
		btnJugadorvsMariano = new JButton("Jugador vs Mariano");
		btnJugadorvsMariano.setBounds(60, 236, 257, 50);
		panel.add(btnJugadorvsMariano);
		
		lblDificultad = new JLabel("Seleccione la dificultad:");
		lblDificultad.setBounds(70, 317, 153, 14);
		panel.add(lblDificultad);
		
		rdbtnMarianoFacil = new JRadioButton("Mariano");
		rdbtnMarianoFacil.setBounds(60, 343, 79, 23);
		panel.add(rdbtnMarianoFacil);
		
		rdbtnMarianoMedio = new JRadioButton("Mariano+");
		rdbtnMarianoMedio.setBounds(141, 343, 79, 23);
		panel.add(rdbtnMarianoMedio);
		
		rdbtnMarianoDificil = new JRadioButton("Mariano++");
		rdbtnMarianoDificil.setBounds(222, 343, 95, 23);
		panel.add(rdbtnMarianoDificil);
		
		btngrpDificultad = new ButtonGroup();
		btngrpDificultad.add(rdbtnMarianoFacil);
		btngrpDificultad.add(rdbtnMarianoMedio);
		btngrpDificultad.add(rdbtnMarianoDificil);
		
		lblVisualizacion = new JLabel("Elija el modo de visualización:");
		lblVisualizacion.setBounds(425, 185, 188, 14);
		panel.add(lblVisualizacion);
		
		rdbtnLista = new JRadioButton("Lista");
		rdbtnLista.setBounds(425, 206, 71, 23);
		panel.add(rdbtnLista);
		
		rdbtnGrafico = new JRadioButton("Gráfico");
		rdbtnGrafico.setBounds(517, 206, 79, 23);
		panel.add(rdbtnGrafico);
		
		btngrpRanking = new ButtonGroup();
		btngrpRanking.add(rdbtnLista);
		btngrpRanking.add(rdbtnGrafico);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(328, 387, 89, 23);
		panel.add(btnSalir);

		miVentana = this;
		
		setResizable(false);
		
		//Escuchadores
		btnJugadorvsJugador.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				clsAnyadirJugador internalframe = new clsAnyadirJugador();
				internalframe.setVisible(true);
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
					frame.setVisible(true);
				}
				if(rdbtnGrafico.isSelected())
				{
					//TODO: Codificar gráficos visuales. [Implementación de librería JFreeChart]
				}
			}
			
		});
	}
}
