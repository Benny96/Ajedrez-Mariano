package Unopauno;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import GUI.clsEleccion;
import Unopauno.TableroLogico1v1;
import LN.clsCasilla;
import LN.clsGestor;
import LN.clsPieza;
import LN.clsRey;
import LN.clsTorre;
import LN.clsUsuario;
import Persistencia.clsBD;

public class TableroVisual1v1 extends JFrame implements ActionListener, Serializable
{
	private static final long serialVersionUID = 1L;
	
	JPanel pPrincipal;
	clsCasilla [][] tablero;	
	TableroLogico1v1 tab;
	
	JLabel blanquito;
	JLabel nigga;
	JLabel ntiempo;
	JLabel btiempo;
	private String nstr;
	private String bstr;

	public LinkedList<clsPieza> pblancas;
	public LinkedList<clsPieza> pnegras;
	LinkedList<clsCasilla> movact;
	
	clsCasilla acasilla;
	clsCasilla ncasilla;
	
	clsRey reyb;
	clsRey reyn;
	
	clsTorre btorrei;
	clsTorre btorred;
	clsTorre ntorrei;
	clsTorre ntorred;
	
	boolean turno;
	
	SimpleTableDemo tabla;
	
	clsPieza selec;
	
	transient MiHilo hilo;
	boolean sigo;
	int x_ini;
	int y_ini;
	int x_final;
	int y_final;
	String ruta_foto="";
	JLabel foto;
	int control=0;
	
	clsCasilla casiaux;
	Image img =null;
	
	JLabel a;
	JLabel b;
	JLabel c;
	JLabel d;
	JLabel e;
	JLabel f;
	JLabel g;
	JLabel h;
	
	JLabel num_1;
	JLabel num_2;	
	JLabel num_3;	
	JLabel num_4;	
	JLabel num_5;
	JLabel num_6;	
	JLabel num_7;	
	JLabel num_8;
	
	transient TableroVisual1v1 miVentana;
	
	private Properties misProperties;
	private int ultimaXVentana = -1;
	private int ultimaYVentana = -1;
	private int ultimoAnchoVentana = -1;
	private int ultimoAltoVentana = -1;
	private String ultimoUsuarioBlanco = "";
	private String ultimoUsuarioNegro = "";
	boolean alterado;
	
	public TableroVisual1v1() 
	{
		tab= new TableroLogico1v1();
		tab.setUblanco(new clsUsuario("","","","",""));
		tab.setUnigga(new clsUsuario("","","","",""));
	}
	public TableroVisual1v1 (clsUsuario a, clsUsuario b)
	{
		tab= new TableroLogico1v1(this);
		tab.setUblanco(a);
		tab.setUnigga(b);
		clsBD.insertarDatoTablaBD(tab);
		CreateAndShowGUI();
	}

	public TableroVisual1v1(TableroLogico1v1 tablog)
	{
		tab = tablog;
		tab.visual = this;
		tabla = new SimpleTableDemo();
		tabla.createTable(tab.getDatosTabla());
		//Hilo arreglado. Soy gilipollas integral.
		tab.IniciarReloj();
		alterado = false;
		CreateAndShowGUI();
	}
	
	public void CreateAndShowGUI ()
	{
		this.setIconImage(new ImageIcon(getClass().getResource("/img/Rajoy.png")).getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		File fic = new File("mariano.ini");
		if (fic.exists())
		{
			cargaProperties();
		}
		if (!alterado)
		{
			setBounds(0, 0, 1200, 780);
		}
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pPrincipal);
		pPrincipal.setLayout(null);
		pPrincipal.setBackground(Color.WHITE);
				
		tablero= tab.getTablero();
		
		clsUsuario ublanco= tab.getUblanco();
		clsUsuario unigga= tab.getUnigga();	
		
		blanquito= new JLabel(ublanco.getNickname());
		blanquito.setBounds(475, 650, 160, 40);
		Font labelFont = blanquito.getFont();
		String labelText =blanquito.getText();

		int stringWidth = blanquito.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = blanquito.getWidth();

		double widthRatio = (double)componentWidth / (double)stringWidth;
		
		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = blanquito.getHeight();

		int fontSizeToUse = Math.min(newFontSize, componentHeight);
		
		blanquito.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));

		pPrincipal.add(blanquito);
		
		num_8=new JLabel("8");
		num_8.setBounds(40, 120, 50, 50);
		num_8.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(num_8);
		
		num_7=new JLabel("7");
		num_7.setBounds(40, 180, 50, 50);
		num_7.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(num_7);
		
		num_6=new JLabel("6");
		num_6.setBounds(40, 240, 50, 50);
		num_6.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(num_6);
		
		num_5=new JLabel("5");
		num_5.setBounds(40, 300, 50, 50);
		num_5.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(num_5);
		
		num_4=new JLabel("4");
		num_4.setBounds(40, 360, 50, 50);
		num_4.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(num_4);
		
		num_3=new JLabel("3");
		num_3.setBounds(40, 420, 50, 50);
		num_3.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(num_3);
		
		num_2=new JLabel("2");
		num_2.setBounds(40, 480, 50, 50);
		num_2.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(num_2);
		
		num_1=new JLabel("1");
		num_1.setBounds(40, 540, 50, 50);
		num_1.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(num_1);
		
		a=new JLabel("a");
		a.setBounds(100, 600, 35, 35);
		a.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(a);
		
		b=new JLabel("b");
		b.setBounds(160, 600, 35, 35);
		b.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(b);
		
		c=new JLabel("c");
		c.setBounds(220, 600, 35, 35);
		c.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(c);
		
		d=new JLabel("d");
		d.setBounds(280, 600, 35, 35);
		d.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(d);
		
		e=new JLabel("e");
		e.setBounds(340, 600, 35, 35);
		e.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(e);
		
		f=new JLabel("f");
		f.setBounds(400, 600, 35, 35);
		f.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(f);
		
		g=new JLabel("a");
		g.setBounds(460, 600, 35, 35);
		g.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(g);
		
		h=new JLabel("h");
		h.setBounds(520, 600, 35, 35);
		h.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(h);
			
		foto=new JLabel();
		pPrincipal.add(foto);
		
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if((i+j)%2==0)
					tablero[i][j].setBackground(Color.WHITE);
				else
					tablero[i][j].setBackground(Color.GRAY);			
				
				tablero[i][j].setBounds(500-j*60, 540-i*60, 60, 60);
				tablero[i][j].addActionListener(this);
				pPrincipal.add(tablero[i][j]);
			}
		}			
		turno=tab.getTurno();
		
		pblancas= tab.getPblancas();
		pnegras= tab.getPnegras();
		movact= tab.getMovact();
		
		reyb=tab.getReyb();
		reyn=tab.getReyn();
		
		btorrei=tab.getBtorrei();
		btorred=tab.getBtorred();
		ntorrei=tab.getBtorrei();
		ntorred=tab.getNtorred();
		
		bstr = tab.getBstr();	
		btiempo= new JLabel(bstr);
		btiempo.setBounds(410, 650, 100, 40);
		btiempo.setFont( new Font( "Arial", Font.BOLD, 18 ));
		pPrincipal.add(btiempo);

		nstr = tab.getNstr();
		ntiempo= new JLabel(nstr);
		ntiempo.setBounds(80, 20, 100, 40);
		ntiempo.setFont( new Font( "Arial", Font.BOLD, 18 ));
		pPrincipal.add(ntiempo);
				
		nigga=new JLabel(unigga.getNickname());
		nigga.setBounds(135, 20, 160, 40);
		nigga.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));

		pPrincipal.add(nigga);
		
		miVentana = this;		
		if (tabla == null)
		{
			tabla= new SimpleTableDemo();
			tabla.createTable(tabla.data);
		}
		tabla.setBounds(744, 69, 241, 500);
		pPrincipal.add(tabla);		
		addWindowListener( new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				int x = -1;
				x = JOptionPane.showConfirmDialog(null,"¿Deseas guardar la partida antes de volver al menú?", "Guardar la partida", JOptionPane.YES_NO_OPTION);
				if (x == 0)
				{
					TableroLogico1v1 tabaguardar = new TableroLogico1v1(tab.btorred, tab.btorrei, tab.getBmin(), tab.getBseg(),
							tab.movact, tab.getNmin(), tab.getNseg(), tab.ntorred, tab.ntorrei, tab.pblancas, tab.pnegras,
							tab.reyb, tab.reyn, tabla.data, tab.num, tab.getUblanco(), tab.getUnigga(), tab.getTurno());		
					clsGestor objGestor = new clsGestor();
					objGestor.GuardarPartida(tabaguardar);
					salvaProperties();
				}
				tab.jaquemate = true;
				clsEleccion menu = new clsEleccion(tab.getUblanco());
				menu.setVisible(true);
				miVentana.dispose();
			}
		});
	}
	
	public void EmpezarMovimiento(int x_i, int y_i, int x_f, int y_f, String ruta, clsCasilla ncasilla2)
	{
		x_ini=x_i;
		y_ini=y_i;
		x_final=x_f;
		y_final=y_f;
		ruta_foto=ruta;
		casiaux=ncasilla2;	
		foto.setVisible(true);		
		try 
		{
			img = ImageIO.read(getClass().getResource(ruta_foto));
			foto.setIcon(new ImageIcon(img));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		sigo=true;			
		if(control==0)
		{
			control++;
			hilo = new MiHilo();
			Thread a= new Thread(hilo);
			a.start();
		}
		else
		{			
			Thread a= new Thread(hilo);
			a.start();
		}	
	}

	public void actionPerformed(ActionEvent arg) 
	{
		acasilla=ncasilla;
		ncasilla=(clsCasilla) arg.getSource();
		tab.action(ncasilla);
		this.repaint();
	}
	
	private void cargaProperties() 
	{
		misProperties = new Properties();
		try 
		{
			FileInputStream fis = new FileInputStream( new File ( "mariano.ini" ));
			misProperties.loadFromXML( fis );
			ultimaXVentana = Integer.parseInt( misProperties.getProperty( "ultimaXVentana" ) );
			ultimaYVentana = Integer.parseInt( misProperties.getProperty( "ultimaYVentana" ) );
			ultimoAnchoVentana = Integer.parseInt( misProperties.getProperty( "ultimoAnchoVentana" ) );
			ultimoAltoVentana = Integer.parseInt( misProperties.getProperty( "ultimoAltoVentana" ) );
			ultimoUsuarioBlanco = misProperties.getProperty( "ultimoUsuarioBlanco" );
			ultimoUsuarioNegro = misProperties.getProperty( "ultimoUsuarioNegro" );
			if (ultimoUsuarioBlanco != null && ultimoUsuarioNegro != null)
			{
				if (ultimoAnchoVentana>0 && ultimoAltoVentana>0 && ultimoUsuarioBlanco.compareTo(tab.getUblanco().getNickname())==0
						&& ultimoUsuarioNegro.compareTo(tab.getUnigga().getNickname())==0) 
					{
						setSize( ultimoAnchoVentana, ultimoAltoVentana );
						setLocation( ultimaXVentana, ultimaYVentana );
						alterado = true;
					}
			}	
			fis.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void salvaProperties() 
	{
		PrintStream ps;
		try {
			ps = new PrintStream( new File( "mariano.ini" ) );
			misProperties = new Properties();
			misProperties.setProperty( "ultimaXVentana", ""+ getX() );
			misProperties.setProperty( "ultimaYVentana", ""+ getY() );
			misProperties.setProperty( "ultimoAnchoVentana", ""+getWidth() );
			misProperties.setProperty( "ultimoAltoVentana", ""+getHeight() );
			misProperties.setProperty( "ultimoUsuarioBlanco", tab.getUblanco().getNickname());
			misProperties.setProperty( "ultimoUsuarioNegro", tab.getUnigga().getNickname());
			misProperties.storeToXML( ps, "Ventana Mariano" );
			ps.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public TableroLogico1v1 getTab ()
	{
		return tab;
	}
	
	class SimpleTableDemo extends JPanel
	{ 
		private static final long serialVersionUID = 1L;
		
		Object[][] data;
		String[] columnNames = {"Blanco",
				                 "Negro",
				                 };
	    public SimpleTableDemo() 
	    {
	        super(new GridLayout(1,0));
	        data= new Object[100][2];
	    }
	    public void createTable (Object [][]a)
	    {
	    	data = a;
	        final JTable table = new JTable(data, columnNames);
	        table.setPreferredScrollableViewportSize(new Dimension(200, 500));
	        table.setFillsViewportHeight(true);
	        table.setEnabled(false);
	        
	        JScrollPane scrollPane = new JScrollPane(table);
	        add(scrollPane);
	    }    
	}	
	
	class MiHilo implements Runnable
	{
		@Override
		public void run() 
		{
			while(sigo)
			{
				try 
				{
					Thread.sleep(5);
					if(ruta_foto=="/img/peon_blanco.png" || ruta_foto=="/img/peon_negro.png")
					{
						if(y_ini<y_final);
						if(y_ini>y_final)y_ini--;
						if(y_ini<y_final)y_ini++;
						
						if(x_ini==x_final);
						if(x_ini>x_final)x_ini--;
						if(x_ini<x_final)x_ini++;
						
						if(x_ini==x_final&&y_ini==y_final)
						{
							acaba();
						}			
					}
					if(ruta_foto=="/img/torre_b.png" || ruta_foto=="/img/torre_n.png")
					{
						if(y_ini==y_final);
						if(y_ini>y_final)y_ini--;
						if(y_ini<y_final)y_ini++;
						
						if(x_ini==x_final);
						if(x_ini>x_final)x_ini--;
						if(x_ini<x_final)x_ini++;
									
						if(x_ini==x_final&&y_ini==y_final)
						{
							acaba();
						}
					}
					
					if(ruta_foto=="/img/caballo_b.png" || ruta_foto=="/img/caballo_n.png")
					{
						if(y_ini>y_final)y_ini--;
						if(y_ini<y_final)y_ini++;
						
						if(y_ini==y_final)
						{
							if(x_ini>x_final)x_ini--;
							if(x_ini<x_final)x_ini++;
							if(x_ini==x_final);
						}
						
						if(x_ini==x_final&&y_ini==y_final)
						{
							acaba();
						}
					}
					
					if(ruta_foto=="/img/Alfil_b.png" || ruta_foto=="/img/Alfil_n.png")
					{
						if(y_ini==y_final);
						if(y_ini>y_final)y_ini--;
						if(y_ini<y_final)y_ini++;
						
						if(x_ini==x_final);
						if(x_ini>x_final)x_ini--;
						if(x_ini<x_final)x_ini++;
											
						if(x_ini==x_final&&y_ini==y_final)
						{
							acaba();
						}
					}
					
					if(ruta_foto=="/img/reina_b.png" || ruta_foto=="/img/reina_n.png")
					{
						if(y_ini==y_final);
						if(y_ini>y_final)y_ini--;
						if(y_ini<y_final)y_ini++;
						
						if(x_ini==x_final);
						if(x_ini>x_final)x_ini--;
						if(x_ini<x_final)x_ini++;
						
						
						if(x_ini==x_final&&y_ini==y_final)
						{
							acaba();
						}
					}
					
					if(ruta_foto=="/img/rey_b.png" || ruta_foto=="/img/rey_n.png")
					{
						if(y_ini==y_final);
						if(y_ini>y_final)y_ini--;
						if(y_ini<y_final)y_ini++;
						
						if(x_ini==x_final);
						if(x_ini>x_final)x_ini--;
						if(x_ini<x_final)x_ini++;
						
						if(x_ini==x_final&&y_ini==y_final)
						{
							acaba();
						}
					}
					foto.setBounds(x_ini, y_ini, 50, 50);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}	
	}
	
	public void acaba()
	{
		sigo=false;
		foto.setVisible(false);
		casiaux.setIcon(new ImageIcon(img));
	}
}