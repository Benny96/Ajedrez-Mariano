package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import LN.clsUsuarioRepetido;
import LN.clsGestor;



public class clsAltaUsuario extends JFrame {

	private JPanel panel;
	
	private JTextField txtNombre;
	private JTextField txtApe1;
	private JTextField txtApe2;
	private JTextField txtNickname;
	private JPasswordField txtContrase�a1;
	private JPasswordField txtContrase�a2;
	
	private JLabel lblNombre;
	private JLabel lblApe1;
	private JLabel lblApe2;
	private JLabel lblNickname;
	private JLabel lblContrase�a1;
	private JLabel lblContrase�a2;
	
	private JButton btnAceptar;
	private JButton btnCancelar;

	/**
	 * Create the application.
	 */
	public clsAltaUsuario() {

		panel = new JPanel();
		this.setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		getContentPane().add( panel, BorderLayout.CENTER );
		setTitle("Alta de usuario");
		panel.setBackground(Color.WHITE);
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 43, 81, 14);
		panel.add(lblNombre);
		
		lblApe1 = new JLabel("Apellido 1:");
		lblApe1.setBounds(10, 68, 81, 14);
		panel.add(lblApe1);
		
		lblApe2 = new JLabel("Apellido 2:");
		lblApe2.setBounds(10, 93, 81, 14);
		panel.add(lblApe2);
		
		lblNickname = new JLabel("Nickname:");
		lblNickname.setBounds(10, 118, 81, 14);
		panel.add(lblNickname);
		
		lblContrase�a1 = new JLabel("Contrase�a:");
		lblContrase�a1.setBounds(10, 143, 81, 14);
		panel.add(lblContrase�a1);
		
		lblContrase�a2 = new JLabel("Contrase�a:");
		lblContrase�a2.setBounds(10, 168, 81, 14);
		panel.add(lblContrase�a2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(101, 40, 86, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApe1 = new JTextField();
		txtApe1.setBounds(101, 65, 86, 20);
		panel.add(txtApe1);
		txtApe1.setColumns(10);
		
		txtApe2 = new JTextField();
		txtApe2.setBounds(101, 90, 86, 20);
		panel.add(txtApe2);
		txtApe2.setColumns(10);
		
		txtNickname = new JTextField();
		txtNickname.setBounds(101, 115, 86, 20);
		panel.add(txtNickname);
		txtNickname.setColumns(10);
		
		txtContrase�a1 = new JPasswordField();
		txtContrase�a1.setBounds(101, 140, 86, 20);
		panel.add(txtContrase�a1);
		txtContrase�a1.setColumns(10);
		
		txtContrase�a2 = new JPasswordField();
		txtContrase�a2.setBounds(101, 165, 86, 20);
		panel.add(txtContrase�a2);
		txtContrase�a2.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(215, 228, 89, 23);
		panel.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(314, 228, 89, 23);
		panel.add(btnCancelar);
		
		//Escuchadores
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				
				clsGestor objGestor=new clsGestor();
				
				
				if(txtNombre.getText().length()>0&&txtApe1.getText().length()>0&&txtApe2.getText().length()>0&&txtNickname.getText().length()>0&&txtContrase�a1.getText().length()>0&&txtContrase�a2.getText().length()>0)
				{		
					
					
					
					if(txtContrase�a1.getText().equals(txtContrase�a2.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "Introduzca la misma contrase�a", "�Contrase�as diferentes!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						try
						{
							objGestor.CrearUsuario(txtNombre.getText(), txtApe1.getText(), txtApe2.getText(), txtNickname.getText(), txtContrase�a1.getText());//, frmFechas.getFec());
							dispose();
						}
						catch(clsUsuarioRepetido p)
						{
								JOptionPane.showMessageDialog(null, p.getMessage(), "Nickname repetido", JOptionPane.WARNING_MESSAGE);
						}
						
					}
					

//					
//				
//					
//				
//				else
//				{
//				
//							
//				
//				try {
//					frmModificarUsuario.ActualizarTabla();
//				} catch (NullPointerException e1) {
//					// TODO Auto-generated catch block
////					e1.printStackTrace();
//				}
//				
//				}
//				}
				}
//				
//				
				else
				{
					JOptionPane.showMessageDialog(null, "Introduzca todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
//				break;
			}
		});
		
		
		btnCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
		
		
		
		
		
		
	}
}
