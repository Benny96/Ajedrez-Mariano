package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import LN.clsGestor;
import LN.clsUsuario;
import Persistencia.clsBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Clase que generar� una JFrame para iniciar la interacci�n con la aplicaci�n.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Be�at Gald�s (Benny96)
 */
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
	private JLabel lblPregunta;
	private JLabel img;
	private JFrame miVentana;
	
	final int posImgX=440;
	final int posImgY=120;

	ArrayList<clsUsuario> usus=new ArrayList<clsUsuario>();
	
	private boolean controlPulsado = false;

	/**
	 * Constructor del JFrame que genera la parte visual de la ventana, as� como los escuchadores requeridos para mejorar la interacci�n de dicha ventana.
	 */
	public clsPaginaPrincipal() 
	{
		panel=new JPanel();
		this.setBounds(350, 200, 500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setLayout(null);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/Rajoy.png")).getImage());
		getContentPane().add( panel, BorderLayout.CENTER );
		setTitle("�Bienvenido al Ajedrez Mariano!");
		panel.setBackground(Color.WHITE);
		
		/*Escuchador para que por defecto el cursor vaya al JTextField*/
		txtNickname=new JTextField();
		addWindowListener( new WindowAdapter() 
		{
		    public void windowOpened( WindowEvent e )
		    {
		    	txtNickname.requestFocus();
		    }
		}); 
		
		txtNickname.setBounds(112, 186, 123, 20);
		panel.add(txtNickname);
		txtNickname.setColumns(10);
		
		txtContrasenya = new JPasswordField();
		txtContrasenya.setBounds(112, 217, 123, 20);
		panel.add(txtContrasenya);
		txtContrasenya.setColumns(10);		

		lblUsuario= new JLabel("Nickname:");
		lblUsuario.setBounds(27, 189, 65, 14);
		panel.add(lblUsuario);
		
		lblContrasenya = new JLabel("Contrase�a:");
		lblContrasenya.setBounds(27, 220, 75, 14);
		panel.add(lblContrasenya);
		
		btnAceptar = new JButton("Entrar");
		btnAceptar.setBounds(312, 216, 172, 23);
		panel.add(btnAceptar);
		
		btnRegistro = new JButton("�Reg�strate ahora!");
		btnRegistro.setBounds(312, 183, 172, 23);
		panel.add(btnRegistro);		

		lblPregunta = new JLabel("�Te atreves a jugar contra Mariano?");
		lblPregunta.setBounds(27, 0, 573, 75);
		lblPregunta.setFont (lblPregunta.getFont ().deriveFont (24.0f));
		panel.add(lblPregunta);
		
		miVentana = this;
		
		img=new JLabel();
		img.setSize(229, 186);
		img.setLocation(159, 20);
		img=CogerImagen(img);
		panel.add(img);
			
		setResizable(false);
			
		/*Escuchadores*/
		
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Entrar();
			}

		});

		txtNickname.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(controlPulsado)
					{
						Entrar();
					}
					controlPulsado=false;					
				}	
			}		
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					controlPulsado = true;
				}			
			}	
		});
		
		txtContrasenya.addKeyListener(new KeyAdapter() 
		{	
			@Override
			public void keyReleased(KeyEvent e) 
			{	
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(controlPulsado)
					{
						Entrar();	
					}
					controlPulsado=false;	
				}		
			}
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					controlPulsado = true;
				}
			}
		});
		
		btnAceptar.addKeyListener(new KeyAdapter() 
		{	
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(controlPulsado)
					{
						Entrar();			
					}
					controlPulsado=false;	
				}		
			}
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					controlPulsado = true;
				}
			}				
		});
		
		btnRegistro.addKeyListener(new KeyAdapter() 
		{	
			@Override
			public void keyReleased(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				
					if(controlPulsado)
					{
						Entrar();		
					}
					controlPulsado=false;					
				}	
			}
			@Override
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					controlPulsado = true;
				}	
			}		
		});
		btnRegistro.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Registrar();
			}
		});
		addWindowListener( new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				clsBD.close();
			}
		});
	}
	
	/**
	 * M�todo que coge la imagen de las piezas que se muestra en la ventana.
	 * @param l JLabel que contendr� la imagen.
	 * @return JLabel con la imagen insertada.
	 */
	public JLabel CogerImagen(JLabel l)
	{
		l.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/img/pagina_principal.png")).getImage().getScaledInstance(160, 140, Image.SCALE_DEFAULT)));
		return l;
	}
	
	/**
	 * M�todo que genera una nueva JFrame para que un nuevo usuario se registre.
	 */
	private void Registrar()
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
	
	/**
	 * M�todo que valida la entrada de un usuario existente en la aplicaci�n.
	 */
	private void Entrar()
	{
		clsGestor objGestor=new clsGestor();
		usus=objGestor.ListaUsuarios();
		boolean existe = false;
		for(clsUsuario aux:usus)
		{
			if((txtNickname.getText().toUpperCase().equals(aux.getNickname().toUpperCase()))&&(txtContrasenya.getText().equals(aux.getContrase�a())))
			{
				existe = true;
				ProgressBar pb=new ProgressBar("Validando acceso...", aux, 0, null);
				pb.setVisible(true);
				miVentana.dispose();
			}
		}
		if(!existe)
		{
			JOptionPane.showMessageDialog(null, "�Est� dado de alta? Su nickname o contrase�a son incorrectos.", "�Error de Login!", JOptionPane.ERROR_MESSAGE);
		}
	}
}