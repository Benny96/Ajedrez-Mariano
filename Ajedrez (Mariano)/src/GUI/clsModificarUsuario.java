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
		this.txtContrase�a1.setText(usuario.getContrase�a());
		
		//Escuchadores
		this.btnAceptar.addActionListener(new ActionListener() 
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
