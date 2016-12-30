package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import LN.clsGestor;
import LN.clsUsuario;

public class clsModificarUsuario extends clsAltaUsuario
{
	private static final long serialVersionUID = 1L;
	
	clsUsuario modif;
	
	public clsModificarUsuario(clsUsuario usuario, clsEleccion ventanita)
	{
		super();
		
		this.txtNombre.setText(usuario.getNombre());
		this.txtApe1.setText(usuario.getApellido1());
		this.txtApe2.setText(usuario.getApellido2());
		this.txtNickname.setText(usuario.getNickname());
		txtNickname.setEditable(false);
		this.txtContrasenya1.setText(usuario.getContraseña());
		modifusu = true;
		
		//Escuchadores
		this.btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				clsGestor objGestor=new clsGestor();
				if(txtNombre.getText().length()>0&&txtApe1.getText().length()>0&&txtApe2.getText().length()>0&&txtNickname.getText().length()>0&&txtContrasenya1.getText().length()>0&&txtContrasenya2.getText().length()>0)
				{		
					if(txtContrasenya1.getText().equals(txtContrasenya2.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "Introduzca la misma contraseña", "¡Contraseñas diferentes!", JOptionPane.ERROR_MESSAGE);
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
