package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.Border;

import LN.clsGestor;
import LN.clsUsuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class clsPaginaPrincipal extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private JPanel panel;
	private JTextField txtNickname;
	private JPasswordField txtContrasenya;
	private JLabel lblUsuario;
	private JLabel lblContrasenya;
	private JButton btnRegistro;
	private JButton btnAceptar;
	private JLabel lblLogin;
	private Border borderUsuario;
	private Border borderContrasenya;
	private JLabel img;
	private JFrame miVentana;
	
	final int posImgX=440;
	final int posImgY=120;

	ArrayList<clsUsuario> usus=new ArrayList<clsUsuario>();

	public clsPaginaPrincipal() 
	{
		panel=new JPanel();
		this.setBounds(350, 200, 500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		getContentPane().add( panel, BorderLayout.CENTER );
		setTitle("¡Bienvenido al Ajedrez Mariano!");
		panel.setBackground(Color.WHITE);
		
		txtNickname = new JTextField();
		txtNickname.setBounds(112, 186, 123, 20);
		borderUsuario = BorderFactory.createLineBorder(Color.red, 1);
		txtNickname.setBorder(borderUsuario);
		panel.add(txtNickname);
		txtNickname.setColumns(10);
		
		txtContrasenya = new JPasswordField();
		txtContrasenya.setBounds(112, 217, 123, 20);
		borderContrasenya = BorderFactory.createLineBorder(Color.red, 1);
		txtContrasenya.setBorder(borderContrasenya);
		panel.add(txtContrasenya);
		txtContrasenya.setColumns(10);
		
		lblUsuario= new JLabel("Nickname:");
		lblUsuario.setBounds(27, 189, 65, 14);
		panel.add(lblUsuario);
		
		lblContrasenya = new JLabel("Contraseña:");
		lblContrasenya.setBounds(27, 220, 75, 14);
		panel.add(lblContrasenya);
		
		btnAceptar = new JButton("Entrar");
		btnAceptar.setBounds(286, 199, 89, 23);
		panel.add(btnAceptar);
		
		btnRegistro = new JButton("¡Regístrate ahora!");
		btnRegistro.setBounds(267, 110, 172, 23);
		panel.add(btnRegistro);
		
		lblLogin = new JLabel("¿Te atreves a jugar contra Mariano?");
		lblLogin.setBounds(27, 0, 573, 75);
		lblLogin.setFont (lblLogin.getFont ().deriveFont (24.0f));
		panel.add(lblLogin);
		
		miVentana = this;
		
		img=new JLabel();
		img.setSize(229, 186);
		img.setLocation(47, 21);
		img=CogerImagen(img);
		panel.add(img);
			
		setResizable(false);
		
		//Escuchadores
		btnAceptar.addActionListener(new ActionListener() 
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
						clsEleccion ventanaEleccion = new clsEleccion(aux);
						ventanaEleccion.setVisible(true);
						miVentana.dispose();
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
	}
	public JLabel CogerImagen(JLabel l)
	{
//		l.setIcon(new ImageIcon(new ImageIcon("C:/Users/ALUMNO/git/Ajedrez_Mariano/Ajedrez (Mariano)/src/img/pagina_principal.png").getImage().getScaledInstance(160, 140, Image.SCALE_DEFAULT)));
		l.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/img/pagina_principal.png")).getImage().getScaledInstance(160, 140, Image.SCALE_DEFAULT)));

		return l;
	}
}





