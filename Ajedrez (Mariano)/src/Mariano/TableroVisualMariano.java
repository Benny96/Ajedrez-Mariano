package Mariano;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import GUI.clsEleccion;
import LN.clsCasilla;
import LN.clsPieza;
import LN.clsRey;
import LN.clsTorre;
import LN.clsUsuario;

/**
 * Clase que soportará la parte gráfica del tablero en una partida jugador vs Mariano (1vMariano).
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */

public class TableroVisualMariano extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	JPanel pPrincipal;
	clsCasilla [][] tablero;
	
	TableroLogicoMariano tab;
	
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
	
	private Runnable myTimer;
	
	int nmin;
	int nseg;
	private String nstr;

	private int bmin;
	private int bseg;
	private String bstr;

	JLabel btiempo;
	
	SimpleTableDemo tabla;
	
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
	
	clsUsuario usu;
	
	transient TableroVisualMariano miVentana;
	
	/**
	 * Constructor que recibe un clsUsuario que será el jugador blanco de la nueva partida contra Mariano.
	 * @param Jugador blanco
	 */
	public TableroVisualMariano(clsUsuario aux) 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/img/Rajoy.png")).getImage());
		setBounds(0, 0, 1050, 720);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pPrincipal);
		pPrincipal.setLayout(null);
		pPrincipal.setBackground(Color.white);
		
		setTitle("Modo de partida: Jugador Vs Mariano");
		
		miVentana = this;
		usu=aux;
		
		tab= new TableroLogicoMariano(true, this, myTimer, usu);
		
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

		double widthRatio = (double)componentWidth / (double)stringWidth;

		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = blanquito.getHeight();

		int fontSizeToUse = Math.min(newFontSize, componentHeight);

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
	
		addWindowListener( new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent e) 
			{
				tab.jaquemate = true;
				clsEleccion menu = new clsEleccion(usu);
				menu.setVisible(true);
				miVentana.dispose();			
			}
		});	
	}	
	/**
	 * Evento generado al hacer click en cualquiera de las casillas del tablero.
	 */
	public void actionPerformed(ActionEvent arg) 
	{
		acasilla=ncasilla;
		ncasilla=(clsCasilla) arg.getSource();
		tab.action(ncasilla);	
		this.repaint();
	}
	
	/**
	 * Clase interna que extiende de JPanel para generar una JTable al lado del tablero, con las jugadas hechas.
	 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
	 */
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
}