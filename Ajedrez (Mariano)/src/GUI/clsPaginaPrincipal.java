package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
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

public class clsPaginaPrincipal extends JFrame {

	private JPanel panel;
	private JTextField txtNickname;
	private JPasswordField txtContraseña;
	private JLabel lblUsuario;
	private JLabel lblContraseña;
	private JButton btnRegistro;
	private JButton btnAceptar;
	private JLabel lblLogin;
	private Border borderUsuario;
	private Border borderContraseña;



	ArrayList<clsUsuario> usus=new ArrayList<clsUsuario>();




//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					clsPaginaPrincipal window = new clsPaginaPrincipal();
//					window.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	

	/**
	 * Create the application.
	 */
	public clsPaginaPrincipal() {
	

		panel=new JPanel();
		this.setBounds(450, 225, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		add( panel, BorderLayout.CENTER );
		setTitle("¡Bienvenido al Ajedrez Mariano!");
		panel.setBackground(Color.WHITE);
		
		txtNickname = new JTextField();
		txtNickname.setBounds(112, 186, 123, 20);
		borderUsuario = BorderFactory.createLineBorder(Color.red, 1);
		txtNickname.setBorder(borderUsuario);
		panel.add(txtNickname);
		txtNickname.setColumns(10);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(112, 217, 123, 20);
		borderContraseña = BorderFactory.createLineBorder(Color.red, 1);
		txtContraseña.setBorder(borderContraseña);
		panel.add(txtContraseña);
		txtContraseña.setColumns(10);
		
		lblUsuario= new JLabel("Nickname:");
		lblUsuario.setBounds(27, 189, 65, 14);
		panel.add(lblUsuario);
		
		lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setBounds(27, 220, 75, 14);
		panel.add(lblContraseña);
		
		btnAceptar = new JButton("Entrar");
		btnAceptar.setBounds(269, 199, 89, 23);
		panel.add(btnAceptar);
		
		btnRegistro = new JButton("¡Regístrate ahora!");
		btnRegistro.setBounds(252, 110, 172, 23);
		panel.add(btnRegistro);
		
		lblLogin = new JLabel("¿Te atreves a jugar contra Mariano?");
		lblLogin.setBounds(70, 21, 251, 53);
		panel.add(lblLogin);
		
		
		
		
		
		
		
		//Escuchadores
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				clsGestor objGestor=new clsGestor();
				usus=objGestor.ListaUsuarios();
				int g=0;
				
				for(clsUsuario aux:usus)
				{
					if((txtNickname.getText().equals(aux.getNickname()))&&(txtContraseña.getText().equals(aux.getContraseña())))
					{
						g++;
						
						clsTablero frame = new clsTablero();
//			            frame.createAndShowGUI(); 
			            frame.setVisible(true);//necessary as of 1.3
//			            frame.setSelected(true);
			            
			            txtContraseña.setText("");
					}
				}

				if(g==0)
				{
					JOptionPane.showMessageDialog(null, "¿Está dado de alta? Su nickname o contraseña son incorrectos.", "¡Error de Login!", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		
		
		
		
		
		btnRegistro.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
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
}





		
//		if(txtNickname.getText().equals("administrador")&&txtContraseña.getText().equals("admi"))
//		{
//			g++;
//			frmPrincipalAdministrador frame = new frmPrincipalAdministrador("Menú de administrador");
//            frame.CreateAndShowGUI(); 
//            frame.setVisible(true);//necessary as of 1.3
//            frame.setSelected(true);
//            
//            
//            txtContraseña.setText("");
//            
//			
//		}
//		
//		else
//		{
//			for(clsUsuario aux:usus)
//			{
//				if((txtNickname.getText().equals(aux.getNickname()))&&(txtContraseña.getText().equals(aux.getContraseña())))
//				{
//					g++;
//					
//					frmPrincipalUsuario frame = new frmPrincipalUsuario("Menú de usuario");
//		            frame.createAndShowGUI(); 
//		            frame.setVisible(true);//necessary as of 1.3
//		            frame.setSelected(true);
//		            
//		            txtContraseña.setText("");
//				}
//			}
//			
//			
//			if(g==0)
//			{
//				JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
//			}
//
//			
//			
//			
//		}
//		
//		break;
