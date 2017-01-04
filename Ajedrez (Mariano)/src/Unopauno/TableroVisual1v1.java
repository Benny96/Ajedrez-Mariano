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
import javax.swing.Icon;
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
	
	int nmin;
	int nseg;
	private String nstr;

	private int bmin;
	private int bseg;
	private String bstr;

	JLabel btiempo;

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
	
	private Properties misProperties;  // Paso 6 - Propiedades
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
		tab= new TableroLogico1v1(false,this);
		tab.setUblanco(a);
		tab.setUnigga(b);
		clsBD.insertarDatoTablaBD(tab);
		CreateAndShowGUI();
	}
	//TODO: SERÁ INUTIL SI TODO VA BIEN.
//	public TableroVisual1v1 (TableroLogico1v1 tablog, Object [][] a, LinkedList <clsPieza> piblancas, LinkedList <clsPieza> pinegras, LinkedList <clsCasilla> movacti)
//	{
//		tab = tablog;
//		tab.pblancas = piblancas;
//		tab.pnegras = pinegras;
//		tab.movact = movacti;
////		tab = new TableroLogico1v1(true,this);
////		tab.IniciarReloj();
//		tabla = new SimpleTableDemo();
//		tabla.createTable(a);
//		CreateAndShowGUI();
//	}
	public TableroVisual1v1(TableroLogico1v1 tablog)
	{
		//Solución enrevesada nº1:
		tab = tablog;
		tab.visual = this;
		tabla = new SimpleTableDemo();
		tabla.createTable(tab.getDatosTabla());
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
		
		bseg=tab.getBseg();
		bmin=tab.getBmin();
		
		nseg=tab.getNseg();
		nmin=tab.getNmin();
		
		clsUsuario ublanco= tab.getUblanco();
		clsUsuario unigga= tab.getUnigga();
		
		
		blanquito= new JLabel(ublanco.getNickname());
		blanquito.setBounds(475, 650, 160, 40);
		Font labelFont = blanquito.getFont();
		String labelText =blanquito.getText();

		int stringWidth = blanquito.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = blanquito.getWidth();

		// Find out how much the font can grow in width.
		double widthRatio = (double)componentWidth / (double)stringWidth;

		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = blanquito.getHeight();

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.
		
		
//		blanquito.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		//Para que si el nickname es un poco largo, se pueda visualizar en pantalla
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
//				tablero[i][j].setText(Integer.toString(i)+Integer.toString(j));
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
//		nigga.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		//Para que si el nickname es un poco largo, se pueda visualizar en pantalla
		nigga.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));

		pPrincipal.add(nigga);
		
		miVentana = this;
		
//		for(clsPieza aux: pblancas)
//		{
//			tablero[aux.getY()][aux.getX()].setOcupado(aux);
//		}
//		for(clsPieza aux: pnegras)
//		{
//			tablero[aux.getY()][aux.getX()].setOcupado(aux);
//		}
		
	
		
		if (tabla == null)
		{
			tabla= new SimpleTableDemo();
			tabla.createTable(tabla.data);
		}
		tabla.setBounds(744, 69, 241, 500);
		pPrincipal.add(tabla);
		//Nota sobre la escritura: no es del todo correcta( no manifiesta  jaques o jaquemates
		//y cuando come el peón está mal
		
		addWindowListener( new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				int x = -1;
				x = JOptionPane.showConfirmDialog(null,"¿Deseas guardar la partida antes de volver al menú?", "Guardar la partida", JOptionPane.YES_NO_OPTION);
				if (x == 0)
				{
					/**
					 * ATRIBUTOS A ENVIAR:
					 * btorred = TableroVisual1v1.btorred;
						btorrei = TableroVisual1v1.btorrei;
						
						bmin= TableroVisual1v1.bmin;
						bseg = TableroVisual1v1.bseg;
						bstr = String.format("%d:%02d", bmin, bseg);
						movact = new LinkedList <clsCasilla>();
						for (clsCasilla aux: movacti)
						{
							movact.add(aux);
						}
						nmin = TableroVisual1v1.nmin;
						nseg = TableroVisual1v1.nseg;
						nstr = String.format("%d:%02d", nmin, nseg);
						ntorred = TableroVisual1v1.ntorred;
						ntorrei = TableroVisual1v1.ntorrei;
						pblancas = new LinkedList <clsPieza>();
						for (clsPieza aux: piblancas)
						{
							pblancas.add(aux);
						}
						pnegras = new LinkedList <clsPieza>();
						for (clsPieza aux: pinegras)
						{
							pnegras.add(aux);
						}
						reyb = reyb;
						reyn = reyn;
						
					 */
//					TableroVisual1v1 tabaguardar = new TableroVisual1v1(tab, tabla.data, tab.pblancas, tab.pnegras, tab.movact);
					TableroLogico1v1 tabaguardar = new TableroLogico1v1(tab.btorred, tab.btorrei, tab.getBmin(), tab.getBseg(),
							tab.movact, tab.getNmin(), tab.getNseg(), tab.ntorred, tab.ntorrei, tab.pblancas, tab.pnegras,
							tab.reyb, tab.reyn, tabla.data, tab.num, tab.getUblanco(), tab.getUnigga(), tab.getTurno());		
					clsGestor objGestor = new clsGestor();
					objGestor.GuardarPartida(tabaguardar);
					//TODO: Guardar las propiedades (tamaño ventana, etc.) de la partida.
					salvaProperties();  // Paso 6
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
		//foto=new JLabel();
		casiaux=ncasilla2;
		
		foto.setVisible(true);
		
		try {
			img = ImageIO.read(getClass().getResource(ruta_foto));
			foto.setIcon(new ImageIcon(img));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	
//	public TableroVisual1v1(TableroLogico1v1 tablerete) {
//		
//		// TODO Auto-generated constructor stub
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(0, 0, 1360, 720);
//		pPrincipal = new JPanel();
//		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(pPrincipal);
//		pPrincipal.setLayout(null);
//		clsCasilla[][] ctablero = tablerete.getTablero();
//		
//		for(clsPieza aux: tablerete.getPblancas())
//		{
//			ctablero[aux.getY()][aux.getX()].setOcupado(aux);
//		}
//		for(clsPieza aux:tablerete.getPnegras())
//		{
//			ctablero[aux.getY()][aux.getX()].setOcupado(aux);
//		}
//		
//		
//		for(int i=0;i<8;i++)
//		{
//			for(int j=0;j<8;j++)
//			{
//				ctablero[i][j].setText(Integer.toString(i)+Integer.toString(j));
//				if((i+j)%2==0)
//					ctablero[i][j].setBackground(Color.WHITE);
//				else
//					ctablero[i][j].setBackground(Color.GRAY);			
//				
//				ctablero[i][j].setBounds(500-j*60, 540-i*60, 60, 60);
//				ctablero[i][j].addActionListener(this);
//				pPrincipal.add(ctablero[i][j]);
//				
//			}
//		}
//		
//		
//		pPrincipal.repaint();
//	}
	public void actionPerformed(ActionEvent arg) {
		// TODO Auto-generated method stub
		
		//2.comer al paso
		//3. peon al final
		//4.reloj 0 parar
		//5.metodo terminar partida
		//6. jaque
		
		 acasilla=ncasilla;
		 ncasilla=(clsCasilla) arg.getSource();
		 
		// System.out.println(ncasilla);

		tab.action(ncasilla);
		
		this.repaint();
//		System.out.println("el rey nigro");
//		System.out.println(reyn.jaque);
//		System.out.println("el rey blanco");
//		System.out.println(reyb.jaque);
	}


//	private void Conversor()
//	{
//		//System.out.println("SDFGHJKL"+ turno);
//		
//		turno=tab.getTurno();
//		if(turno)
//		{
//			bseg--;
//			if (bseg==-1)
//			{
//				bseg=59;
//				bmin--;
//			}
//			bstr = String.format("%d:%02d", bmin, bseg);
//			tab.setBstr(bstr);
//			btiempo.setText(bstr);
//		}
//		else
//		{
//		nseg--;
//		if (nseg==-1)
//		{
//			nseg=59;
//			nmin--;
//		}
//		nstr = String.format("%d:%02d", nmin, nseg);
//		tab.setNstr(nstr);
//		
//		ntiempo.setText(nstr);
//		}
//		
//		
//		this.repaint();
//			
//	}
//	
//	public void porque()
//	{
//		
//	}
	public TableroLogico1v1 getTab ()
	{
		return tab;

	}
	
	public void acaba()
	{
		sigo=false;
		foto.setVisible(false);
		casiaux.setIcon(new ImageIcon(img));
	}
	
	// Paso 6: carga y guardado de las propiedades
	private void cargaProperties() {
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
	
	private void salvaProperties() {
		PrintStream ps;
		try {
			ps = new PrintStream( new File( "mariano.ini" ) );
			misProperties = new Properties();
			misProperties.setProperty( "ultimaXVentana", ""+ getX() ); //Pares Propiedad - Valor.
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
//	        Object[][] data = {
//	                {"Kathy", "Smith",
//	                 "Snowboarding", new Integer(5), new Boolean(false)},
//	                {"John", "Doe",
//	                 "Rowing", new Integer(3), new Boolean(true)},
//	                {"Sue", "Black",
//	                 "Knitting", new Integer(2), new Boolean(false)},
//	                {"Jane", "White",
//	                 "Speed reading", new Integer(20), new Boolean(true)},
//	                {"Joe", "Brown",
//	                 "Pool", new Integer(10), new Boolean(false)}
//	                };
	    }
	    public void createTable (Object [][]a)
	    {
	    	data = a;
	        final JTable table = new JTable(data, columnNames);
	        table.setPreferredScrollableViewportSize(new Dimension(200, 500));
	        table.setFillsViewportHeight(true);
	        table.setEnabled(false);
	        
	 
//	        if (DEBUG) {
//	            table.addMouseListener(new MouseAdapter() {
//	                public void mouseClicked(MouseEvent e) {
//	                    printDebugData(table);
//	                }
//	            });
//	        }
	 
	        //Create the scroll pane and add the table to it.
	        JScrollPane scrollPane = new JScrollPane(table);
	 
	        //Add the scroll pane to this panel.
	        add(scrollPane);
	    }
	 
//	    private void printDebugData(JTable table) {
//	        int numRows = table.getRowCount();
//	        int numCols = table.getColumnCount();
//	        javax.swing.table.TableModel model = table.getModel();
//	 
//	        System.out.println("Value of data: ");
//	        for (int i=0; i < numRows; i++) {
//	            System.out.print("    row " + i + ":");
//	            for (int j=0; j < numCols; j++) {
//	                System.out.print("  " + model.getValueAt(i, j));
//	            }
//	            System.out.println();
//	        }
//	        System.out.println("--------------------------");
//	    }
	    
	}

	
	
	class MiHilo implements Runnable
	{
		
		@Override
		public void run() 
		{
			while(sigo)
			{
				try {

					Thread.sleep(5);
					
					//Segun la pieza, quiero que el movimiento será de una forma u otra
					
					//Esto es un peón, por lo que quiero que se mueva en vertical, pero
					//también en diagonal para que comer
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
	
					//Esto es una torre, solo puede moverse en horizontal o vertical
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
					
					
					//Esto es un caballo, por eso tiene que hacer el movimiento en L
					if(ruta_foto=="/img/caballo_b.png" || ruta_foto=="/img/caballo_n.png")
					{
						//Primero va a hacer todo el movimiento en el eje Y
						if(y_ini>y_final)y_ini--;
						if(y_ini<y_final)y_ini++;
						
						if(y_ini==y_final)
						{
							//Una vez hecho el movimiento de Y, hace el de X
							if(x_ini>x_final)x_ini--;
							if(x_ini<x_final)x_ini++;
							if(x_ini==x_final);
						}
						
						if(x_ini==x_final&&y_ini==y_final)
						{
							acaba();
						}
					}
					
					
					//Esto es un alfil, hace el movimiento siempre en diagonal
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
					
					//Esto es una reina, hace el movimiento que quiera
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
					
					//Esto es una rey, hace el movimiento que quiera
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

					
					
					
					//Dibujado del JLabel
					foto.setBounds(x_ini, y_ini, 50, 50);

			
		
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	}