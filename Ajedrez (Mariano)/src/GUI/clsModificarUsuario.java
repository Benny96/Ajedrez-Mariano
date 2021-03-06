package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import LN.clsGestor;
import LN.clsUsuario;
/**
 * Clase que generar� una ventana que hereda de clsAltaUsuario para modificar los datos de un usuario existente en la base de datos.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Be�at Gald�s (Benny96)
 */
public class clsModificarUsuario extends clsAltaUsuario
{
	private static final long serialVersionUID = 1L;
	
	clsUsuario modif;
	
	/**
	 * Constructor de la ventana que aprovecha la interfaz gr�fica de clsAltaUsuario, incluyendo los datos de un usuario dado.
	 * @param usuario El usuario que est� modificando sus datos.
	 * @param ventanita La ventana clsEleccion de la que proviene el usuario, que tendr� su atributo "usuario" modificado al cambiar los datos referentes al usuario.
	 */
	public clsModificarUsuario(clsUsuario usuario, clsEleccion ventanita)
	{
		super();
		setTitle("Modificar usuario");
		this.txtNombre.setText(usuario.getNombre());
		this.txtApe1.setText(usuario.getApellido1());
		this.txtApe2.setText(usuario.getApellido2());
		this.txtNickname.setText(usuario.getNickname());
		txtNickname.setEditable(false);
		this.txtContrasenya1.setText(usuario.getContrase�a());
		modifusu = true;
		
		/*Escuchadores*/
		this.btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				clsGestor objGestor=new clsGestor();
				if(txtNombre.getText().length()>0&&txtApe1.getText().length()>0&&txtApe2.getText().length()>0&&txtNickname.getText().length()>0&&txtContrasenya1.getText().length()>0&&txtContrasenya2.getText().length()>0)
				{		
					if(txtContrasenya1.getText().equals(txtContrasenya2.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "Introduzca la misma contrase�a", "�Contrase�as diferentes!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						modif = objGestor.ModificarUsuario(txtNombre.getText(), txtApe1.getText(), txtApe2.getText(), txtNickname.getText(), txtContrasenya1.getText(), usuario.getElo(), usuario.getFechadealta());
						JOptionPane.showMessageDialog(null, "Ha modificado el usuario correctamente.");
						ventanita.RefrescarUsuario(modif);
						dispose();
					}
				}
			}
		});
		this.btnCancelar.addActionListener(new ActionListener() 
			{
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
	}
}