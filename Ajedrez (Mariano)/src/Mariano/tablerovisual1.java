package Mariano;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import LN.clsCasilla;
import LN.clsPieza;
import LN.clsRey;
import LN.clsTorre;
import LN.clsUsuario;


public class tablerovisual1 extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	JPanel pPrincipal;
	clsCasilla [][] tablero;
	
	tablerologico1 tab;
	
	JLabel blanquito;
	JLabel nigga;
	
	JLabel ntiempo;
	
	JList asd;

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
	
	private Runnable myTimer;
	
	int nmin;
	int nseg;
	private String nstr;

	private int bmin;
	private int bseg;
	private String bstr;

	JLabel btiempo;
	
	SimpleTableDemo tabla;

	//private JLabel btiempo;
	
	
	clsPieza selec;
	
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
	
	
	public tablerovisual1(clsUsuario aux) 
	{
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/Rajoy.png")).getImage());
		setBounds(0, 0, 1050, 720);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pPrincipal);
		pPrincipal.setLayout(null);
		pPrincipal.setBackground(Color.white);
		
		tab= new tablerologico1(true, this, myTimer, aux);
		
		//clsBD.insertarDatoTablaBD(tab);
		
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
		blanquito.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
		pPrincipal.add(blanquito);
		
		nigga= new JLabel(unigga.getNickname());
		nigga.setBounds(135, 20, 160, 40);
		nigga.setFont(new Font(labelFont.getName(), Font.PLAIN, 24));
		pPrincipal.add(nigga);
		
		
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
		
		
		
		
		
		
	
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				//tablero[i][j].setText(Integer.toString(i)+Integer.toString(j));
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
		ntiempo.setBounds(80, 20, 100, 40);
		ntiempo.setFont( new Font( "Arial", Font.BOLD, 18 ));
		pPrincipal.add(ntiempo);
		
		
		bstr = tab.getBstr();
		
		btiempo= new JLabel(bstr);
		btiempo.setBounds(410, 650, 100, 40);
		btiempo.setFont( new Font( "Arial", Font.BOLD, 18 ));
		pPrincipal.add(btiempo);
		
		
		if (tabla == null)
		{
			tabla= new SimpleTableDemo();
			tabla.createTable(tabla.data);
		}
		tabla.setBounds(744, 69, 241, 500);
		pPrincipal.add(tabla);
	
	
		//para que no meleste
//		myTimer = new Timer1();
//		
//		Thread a= new Thread (myTimer);
//		a.start();
		
		
	}	
	public tablerovisual1(tablerologico1 tablerete) {
		
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/Rajoy.png")).getImage());
		setBounds(0, 0, 1360, 720);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pPrincipal);
		pPrincipal.setLayout(null);
		clsCasilla[][] ctablero = tablerete.getTablero();
		
		for(clsPieza aux: tablerete.getPblancas())
		{
			ctablero[aux.getY()][aux.getX()].setOcupado(aux);
		}
		for(clsPieza aux:tablerete.getPnegras())
		{
			ctablero[aux.getY()][aux.getX()].setOcupado(aux);
		}
		
		
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				ctablero[i][j].setText(Integer.toString(i)+Integer.toString(j));
				if((i+j)%2==0)
					ctablero[i][j].setBackground(Color.WHITE);
				else
					ctablero[i][j].setBackground(Color.GRAY);			
				
				ctablero[i][j].setBounds(500-j*60, 540-i*60, 60, 60);
				ctablero[i][j].addActionListener(this);
				pPrincipal.add(ctablero[i][j]);
				
			}
		}
		
		
		pPrincipal.repaint();
	}
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
		JOptionPane.showMessageDialog(this, "Jaquemate");
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

	}