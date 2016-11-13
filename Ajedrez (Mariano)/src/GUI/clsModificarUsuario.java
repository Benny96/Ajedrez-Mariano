package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import LN.clsGestor;
import LN.clsUsuario;
import LN.clsUsuarioRepetido;

public class clsModificarUsuario extends clsAltaUsuario
{
	private static final long serialVersionUID = 1L;
	
	public clsModificarUsuario(clsUsuario usuario)
	{
		super();
		
		this.txtNombre.setText(usuario.getNombre());
		this.txtApe1.setText(usuario.getApellido1());
		this.txtApe2.setText(usuario.getApellido2());
		this.txtNickname.setText(usuario.getNickname());
		txtNickname.setEditable(false);
		this.txtContraseña1.setText(usuario.getContraseña());
		
		//Escuchadores
		this.btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				clsGestor objGestor=new clsGestor();
				if(txtNombre.getText().length()>0&&txtApe1.getText().length()>0&&txtApe2.getText().length()>0&&txtNickname.getText().length()>0&&txtContraseña1.getText().length()>0&&txtContraseña2.getText().length()>0)
				{		
					if(txtContraseña1.getText().equals(txtContraseña2.getText())==false)
					{
						JOptionPane.showMessageDialog(null, "Introduzca la misma contraseña", "¡Contraseñas diferentes!", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						try
						{
							objGestor.CrearUsuario(txtNombre.getText(), txtApe1.getText(), txtApe2.getText(), txtNickname.getText(), txtContraseña1.getText());//, frmFechas.getFec());
							dispose();
						}
						catch(clsUsuarioRepetido p)
						{
							JOptionPane.showMessageDialog(null, p.getMessage(), "Nickname repetido", JOptionPane.WARNING_MESSAGE);
						}
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
