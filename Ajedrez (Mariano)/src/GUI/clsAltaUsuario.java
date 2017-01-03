package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import LN.clsUsuarioRepetido;
import LN.clsGestor;

public class clsAltaUsuario extends JFrame 
{

	private static final long serialVersionUID = 1L;

	private JPanel panelprincipal;
	private JPanel panelizda;
	private JPanel panelbotonera;
	private JPanel panelsuperior;
	
	
	JTextField txtNombre;
	JTextField txtApe1;
	JTextField txtApe2;
	JTextField txtNickname;
	JPasswordField txtContrasenya1;
	JPasswordField txtContrasenya2;
	
	private JLabel lblNombre;
	private JLabel lblApe1;
	private JLabel lblApe2;
	private JLabel lblNickname;
	private JLabel lblContrasenya1;
	private JLabel lblContrasenya2;
	private JLabel lblFrase;


	JButton btnAceptar;
	JButton btnCancelar;
	
	
	private JLabel img;
	private JFrame miVentana;
	
	final int posImgX=440;
	final int posImgY=120;
	
	boolean modifusu = false;
	private boolean controlPulsado = false;
	
	private static final boolean ANYADIR_A_FIC_LOG = true;  // poner true para hacer append en cada ejecuci�n
	
	// Logger de la clase
		private static Logger logger = Logger.getLogger( "Mariano" );
		static {
			try {
				logger.setLevel( Level.FINEST );
				Formatter f = new SimpleFormatter() {
					@Override
					public synchronized String format(LogRecord record) {
						// return super.format(record);  // Si no queremos el formateador con tanta informaci�n
						if (record.getLevel().intValue()<Level.CONFIG.intValue())
							// Si es menor que CONFIG lo sacamos muy tabulado a la derecha
							return "\t\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
						if (record.getLevel().intValue()<Level.WARNING.intValue())
							// Si es menor que WARNING lo sacamos tabulado a la derecha
							return "\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
						return "(" + record.getLevel() + ") " + record.getMessage() + "\n";
					}
				};
				FileOutputStream fLog = new FileOutputStream( "Mariano"+".log" , ANYADIR_A_FIC_LOG );
				Handler h = new StreamHandler( fLog, f );
				h.setLevel( Level.FINEST );
				logger.addHandler( h );  // Saca todos los errores a out
//				logger.addHandler( new FileHandler( ListaDeReproduccion.class.getName()+".log.xml", ANYADIR_A_FIC_LOG ));
			} catch (SecurityException | IOException e) {
				logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase "+ clsAltaUsuario.class.getName() );
			}
			logger.log( Level.INFO, "" );
			logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
		}
		
	/**
	 * Create the application.
	 */
	public clsAltaUsuario() 
	{
		panelprincipal = new JPanel();
		panelizda=new JPanel();
		panelbotonera=new JPanel();
		panelsuperior=new JPanel();
		this.setBounds(100, 100, 380, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/Rajoy.png")).getImage());
		panelprincipal.setLayout(new BorderLayout());
//		panelizda.setLayout(new SpringLayout());
//		panelizda.setLayout(new GridLayout());
		panelizda.setLayout(new BoxLayout(panelizda, BoxLayout.Y_AXIS));
		panelbotonera.setLayout(new FlowLayout());
		panelsuperior.setLayout(new FlowLayout());
		panelprincipal.add(panelizda, BorderLayout.WEST);
		panelprincipal.add(panelbotonera, BorderLayout.SOUTH);
		panelprincipal.add(panelsuperior, BorderLayout.NORTH);
		setTitle("Alta de usuario");
		panelprincipal.setBackground(Color.WHITE);
		panelizda.setBackground(Color.WHITE);
		panelbotonera.setBackground(Color.WHITE);
		panelsuperior.setBackground(Color.WHITE);

		lblNombre = new JLabel("        Nombre:           ");
		lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtNombre);
		
		lblApe1 = new JLabel("        Apellido 1:        ");
		lblApe1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblApe1);
		
		txtApe1 = new JTextField();
		txtApe1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtApe1);
		
		lblApe2 = new JLabel("        Apellido 2:        ");
		lblApe2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblApe2);
		
		txtApe2 = new JTextField();
		txtApe2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtApe2);
		
		
		
		lblNickname = new JLabel("         Nickname:         ");
		lblNickname.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblNickname);
		
		txtNickname = new JTextField();
		txtNickname.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtNickname);
		
		
		
		lblContrasenya1 = new JLabel("        Contrase�a:        ");
		lblContrasenya1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblContrasenya1);
		
		txtContrasenya1 = new JPasswordField();
		txtContrasenya1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtContrasenya1);
		
		
		
		lblContrasenya2 = new JLabel("        Contrase�a:        ");
		lblContrasenya2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(lblContrasenya2);
		
		txtContrasenya2 = new JPasswordField();
		txtContrasenya2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelizda.add(txtContrasenya2);
		

		btnAceptar = new JButton("Aceptar");
		panelbotonera.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		panelbotonera.add(btnCancelar);
		
		
		lblFrase = new JLabel("��nete a nuestra comunidad!");
		lblFrase.setFont (lblFrase.getFont ().deriveFont (18.0f));
		panelsuperior.add(lblFrase);
		
		
		
		miVentana = this;
		
		img=new JLabel();
		img.setSize(229, 186);
		img=CogerImagen(img);
		panelprincipal.add(img, BorderLayout.CENTER);
		
		
		add(panelprincipal);
			
		//Escuchadores
		btnAceptar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{		
				if (!modifusu)
				Registrar();
			}
		});
		
		
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				
					if(controlPulsado)
					{
						Registrar();
					}
					controlPulsado=false;					
				}	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					controlPulsado = true;
				}			
			}
	
		});
		
		txtApe1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				
					if(controlPulsado)
					{
						Registrar();
					}
					controlPulsado=false;					
				}	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					controlPulsado = true;
				}			
			}
	
		});
		
		txtApe2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				
					if(controlPulsado)
					{
						Registrar();
					}
					controlPulsado=false;					
				}	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					controlPulsado = true;
				}			
			}
	
		});
		
		
		txtNickname.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				
					if(controlPulsado)
					{
						Registrar();
					}
					controlPulsado=false;					
				}	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					controlPulsado = true;
				}			
			}
	
		});
		
		
		txtContrasenya1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				
					if(controlPulsado)
					{
						Registrar();
					}
					controlPulsado=false;					
				}	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					controlPulsado = true;
				}			
			}
	
		});
		
		
		
		txtContrasenya2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				
					if(controlPulsado)
					{
						Registrar();
					}
					controlPulsado=false;					
				}	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					controlPulsado = true;
				}			
			}
	
		});
		
		
		btnAceptar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				
					if(controlPulsado)
					{
						Registrar();
					}
					controlPulsado=false;					
				}	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					controlPulsado = true;
				}			
			}
	
		});
		
		
		
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
				
					if(controlPulsado)
					{
						Registrar();
					}
					controlPulsado=false;					
				}	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					controlPulsado = true;
				}			
			}
	
		});
				
		btnCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
	
		pack();
	}
	
	public JLabel CogerImagen(JLabel l)
	{
//		l.setIcon(new ImageIcon(new ImageIcon("C:/Users/ALUMNO/git/Ajedrez_Mariano/Ajedrez (Mariano)/src/img/pagina_principal.png").getImage().getScaledInstance(160, 140, Image.SCALE_DEFAULT)));
		l.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/img/pagina_registro.png")).getImage().getScaledInstance(220, 170, Image.SCALE_DEFAULT)));

		return l;
	}
	
	private void Registrar()
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
				try
				{
					logger.log( Level.INFO, "Dando de alta al usuario "+txtNickname.getText());
					objGestor.CrearUsuario(txtNombre.getText(), txtApe1.getText(), txtApe2.getText(), txtNickname.getText().toUpperCase(), txtContrasenya1.getText());//, frmFechas.getFec());
					JOptionPane.showMessageDialog(null, "Te has registrado correctamente");
					dispose();
				}
				catch(clsUsuarioRepetido p)
				{
						JOptionPane.showMessageDialog(null, p.getMessage(), "Nickname repetido", JOptionPane.WARNING_MESSAGE);
				}
				
			}

		}
	
		else
		{
			JOptionPane.showMessageDialog(null, "Introduzca todos los datos.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}

