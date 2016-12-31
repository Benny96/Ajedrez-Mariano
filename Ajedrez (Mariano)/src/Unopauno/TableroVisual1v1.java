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
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

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
	
	Boolean turno;
	
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
	public TableroVisual1v1 (TableroLogico1v1 tablog, Object [][] a, LinkedList <clsPieza> piblancas, LinkedList <clsPieza> pinegras, LinkedList <clsCasilla> movacti)
	{
		tab = new TableroLogico1v1(true,this);
		tab = tablog;
		tab.pblancas = piblancas;
		tab.pnegras = pinegras;
		tab.movact = movacti;
		tab.IniciarReloj();
		tabla = new SimpleTableDemo();
		tabla.createTable(a);
		CreateAndShowGUI();
	}
	
	public void CreateAndShowGUI ()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 700, 720);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pPrincipal);
		pPrincipal.setLayout(null);
				
		tablero= tab.getTablero();
		
		bseg=tab.getBseg();
		bmin=tab.getBmin();
		
		nseg=tab.getNseg();
		nmin=tab.getNmin();
		
		clsUsuario ublanco= tab.getUblanco();
		clsUsuario unigga= tab.getUnigga();
		
		blanquito= new JLabel(ublanco.getNickname());
		blanquito.setBounds(475, 595, 100,40);
		
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
		blanquito.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(blanquito);
		
		nigga= new JLabel(unigga.getNickname());
		nigga.setBounds(80, 80, 100,40);
		nigga.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		pPrincipal.add(nigga);
	
		
		foto=new JLabel();
		foto.setBounds(0, 0, 50, 50);
		pPrincipal.add(foto);
		
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				tablero[i][j].setText(Integer.toString(i)+Integer.toString(j));
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
		
//		for(clsPieza aux: pblancas)
//		{
//			tablero[aux.getY()][aux.getX()].setOcupado(aux);
//		}
//		for(clsPieza aux: pnegras)
//		{
//			tablero[aux.getY()][aux.getX()].setOcupado(aux);
//		}
		
		nstr = tab.getNstr();
		
		ntiempo= new JLabel(nstr);
		ntiempo.setBounds(160, 80, 100,40);
		ntiempo.setFont( new Font( "Arial", Font.BOLD, 18 ));
		pPrincipal.add(ntiempo);
		
		
		bstr = tab.getBstr();
		
		btiempo= new JLabel(bstr);
		btiempo.setBounds(410, 595, 100,40);
		btiempo.setFont( new Font( "Arial", Font.BOLD, 18 ));
		pPrincipal.add(btiempo);
		
		if (tabla == null)
		{
			tabla= new SimpleTableDemo();
			tabla.createTable(tabla.data);
		}
		tabla.setBounds(744, 69, 241, 500);
		pPrincipal.add(tabla);
		//Nota sobre la escritura: no es del todo correcta( no manifiesta  jaques o jaquemates
		//y cuando come el pe�n est� mal
		
		//TODO: Entra aqu� cuando no se ha cerrado esta ventana...
		addWindowListener( new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				int x = -1;
				x = JOptionPane.showConfirmDialog(null,"�Deseas guardar la partida antes de volver al men�?", "Guardar la partida", JOptionPane.YES_NO_OPTION);
				if (x == 0)
				{
					TableroVisual1v1 tabaguardar = new TableroVisual1v1(tab, tabla.data, tab.pblancas, tab.pnegras, tab.movact);
					clsGestor objGestor = new clsGestor();
					objGestor.GuardarPartida(tabaguardar);
					//TODO: Guardar las propiedades (tama�o ventana, etc.) de la partida.
//					salvaProperties();  // Paso 6
				}
				clsEleccion menu = new clsEleccion(tab.getUblanco());
				menu.setVisible(true);
				dispose();		
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


	private void Conversor()
	{
		//System.out.println("SDFGHJKL"+ turno);
		
		turno=tab.getTurno();
		if(turno)
		{
			bseg--;
			if (bseg==-1)
			{
				bseg=59;
				bmin--;
			}
			bstr = String.format("%d:%02d", bmin, bseg);
			tab.setBstr(bstr);
			btiempo.setText(bstr);
		}
		else
		{
		nseg--;
		if (nseg==-1)
		{
			nseg=59;
			nmin--;
		}
		nstr = String.format("%d:%02d", nmin, nseg);
		tab.setNstr(nstr);
		
		ntiempo.setText(nstr);
		}
		
		
		this.repaint();
			
	}
	
	public void porque()
	{
		
	}
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
	
	class Timer1 implements Runnable
	{
		@Override
		public void run() 
		{
			while(tab.jaquemate==false)
			{
					try 
					{
						Thread.sleep(1000);
						Conversor();
						
					}
					catch (InterruptedException e) 
					{
						return;
					}
					
			}
			porque();
		}
	}
	
	class SimpleTableDemo extends JPanel{
	   
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
					
					//Segun la pieza, quiero que elmovimiento sea de una forma u otra
					
					//Esto es un pe�n, por lo que quiero que se mueva en vertical, pero
					//tambi�n en diagonal para que comer
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