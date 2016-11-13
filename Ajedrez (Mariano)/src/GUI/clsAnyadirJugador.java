package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import LN.clsGestor;
import LN.clsUsuario;

public class clsAnyadirJugador extends JInternalFrame
{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lblInformacion;
	private JLabel lblNickname;
	private JTextField txtNickname;
	private JLabel lblContrasenya;
	private JPasswordField txtContrasenya;
	private JButton btnRegistro;
	private JButton btnEmpezar;
	private JButton btnSalir;
	
	ArrayList<clsUsuario> usus=new ArrayList<clsUsuario>();
	
	private JInternalFrame miVentanaInterna;
	
	public clsAnyadirJugador() 
	{
		getContentPane().setLayout(null);
		this.setBounds(100, 150, 450, 300);
		
		txtNickname = new JTextField();
		txtNickname.setBounds(50, 85, 345, 20);
		getContentPane().add(txtNickname);
		txtNickname.setColumns(10);
		
		lblInformacion = new JLabel("Introduzca el nickname y contraseña del Jugador 2");
		lblInformacion.setBounds(50, 22, 345, 14);
		getContentPane().add(lblInformacion);
		
		lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(50, 60, 74, 14);
		getContentPane().add(lblNickname);
		
		lblContrasenya = new JLabel("Contraseña");
		lblContrasenya.setBounds(51, 141, 102, 14);
		getContentPane().add(lblContrasenya);
		
		txtContrasenya = new JPasswordField();
		txtContrasenya.setBounds(50, 166, 345, 20);
		getContentPane().add(txtContrasenya);
		
		btnEmpezar = new JButton("Empezar");
		btnEmpezar.setBounds(50, 219, 89, 23);
		getContentPane().add(btnEmpezar);
		
		btnRegistro = new JButton("Registrarse");
		btnRegistro.setBounds(164, 219, 114, 23);
		getContentPane().add(btnRegistro);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(306, 219, 89, 23);
		getContentPane().add(btnSalir);
		
		miVentanaInterna = this;
		
		//Escuchadores
		btnEmpezar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{				
				clsGestor objGestor=new clsGestor();
				usus=objGestor.ListaUsuarios();
				boolean existe = false;						
				for(clsUsuario aux:usus)
				{
					if((txtNickname.getText().equals(aux.getNickname()))&&(txtContrasenya.getText().equals(aux.getContraseña())))
					{
						existe = true;
						//TODO: Añadir un constructor de Tableros que pida 2 parámetros: jugador 1 y jugador 2.
						clsTablero frame = new clsTablero();
					    frame.setVisible(true);
					    miVentanaInterna.dispose();
						clsEleccion.miVentana.dispose();
					}
				}
				if(!existe)
				{
					JOptionPane.showMessageDialog(null, "¿Está dado de alta? Su nickname o contraseña son incorrectos.", "¡Error de Login!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRegistro.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					clsAltaUsuario window = new clsAltaUsuario();
					window.setVisible(true);
				} 
				catch (Exception w) 
				{
					w.printStackTrace();
				}
						
			}
		});
		btnSalir.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				miVentanaInterna.dispose();			
			}			
		});
	}
}
