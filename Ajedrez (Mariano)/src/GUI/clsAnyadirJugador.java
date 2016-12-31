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
import Unopauno.TableroLogico1v1;
import Unopauno.TableroVisual1v1;

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
	
	public clsAnyadirJugador(clsUsuario usuactual) 
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
				int x = -1;
				for(clsUsuario aux:usus)
				{
					if((txtNickname.getText().toUpperCase().equals(aux.getNickname()))&&(txtContrasenya.getText().equals(aux.getContraseña())
							&&!(txtNickname.getText().toUpperCase().equals(usuactual.getNickname()))))
					{
						existe = true;
						TableroLogico1v1 tab = objGestor.CargarPartida();
						if (tab.getUblanco() != null)
						{
							if ((tab.getUblanco().getNickname().compareTo(txtNickname.getText().toUpperCase())==0) && 
									tab.getUnigga().getNickname().compareTo(usuactual.getNickname().toUpperCase())==0)
								{
								x = JOptionPane.showConfirmDialog(null,"Se ha encontrado una partida, ¿desea cargarla?", "Cargado de partida", JOptionPane.YES_NO_OPTION);
								if (x == 0)
									{
										TableroVisual1v1 frame = new TableroVisual1v1(tab);
										frame.setVisible(true);
									    miVentanaInterna.dispose();
										clsEleccion.miVentana.dispose();
									}
									else
									{
										objGestor.BorrarPartida();
										TableroVisual1v1 frame = new TableroVisual1v1(usuactual, aux);
									    frame.setVisible(true);
									    miVentanaInterna.dispose();
										clsEleccion.miVentana.dispose();
									}
								}
								if ((tab.getUblanco().getNickname().compareTo(usuactual.getNickname().toUpperCase())==0) && 
								tab.getUnigga().getNickname().compareTo(txtNickname.getText().toUpperCase())==0)						
								{
								x = -1;
								x = JOptionPane.showConfirmDialog(null,"Se ha encontrado una partida, ¿desea cargarla?", "Cargado de partida", JOptionPane.YES_NO_OPTION);
									if (x == 0)
									{
										TableroVisual1v1 frame = new TableroVisual1v1(tab);
										frame.setVisible(true);
									    miVentanaInterna.dispose();
										clsEleccion.miVentana.dispose();
									}
									else
									{
										objGestor.BorrarPartida();
										TableroVisual1v1 frame = new TableroVisual1v1(usuactual, aux);
									    frame.setVisible(true);
									    miVentanaInterna.dispose();
										clsEleccion.miVentana.dispose();
									}
								}
						}						
						else
						{
							TableroVisual1v1 frame = new TableroVisual1v1(usuactual, aux);
						    frame.setVisible(true);
						    miVentanaInterna.dispose();
							clsEleccion.miVentana.dispose();
						}
					}			
				}
				if(!existe)
				{
					JOptionPane.showMessageDialog(null, "Su nickname o contraseña son incorrectos (Jugador repetido o inexistente).", "¡Error de Login!", JOptionPane.ERROR_MESSAGE);
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
	
