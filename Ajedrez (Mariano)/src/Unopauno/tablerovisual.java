package Unopauno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;











import LN.clsUsuario;

import javax.swing.JTextArea;

public class tablerovisual extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	JPanel pPrincipal;
	clsCasilla [][] tablero;
	
	tablerologico tab;
	
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
	

	//private JLabel btiempo;
	
	private JTextArea textArea; 
	private JLabel lblTextArea;
	private JScrollPane scroll;

	SimpleTableDemo tabla;
	
	clsPieza selec;
	
	public tablerovisual() 
	{
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1100, 720);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pPrincipal);
		pPrincipal.setLayout(null);
		
		tab= new tablerologico(true,this);
		
		
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
		ntiempo.setBounds(160, 80, 100,40);
		ntiempo.setFont( new Font( "Arial", Font.BOLD, 18 ));
		pPrincipal.add(ntiempo);
		
		
		bstr = tab.getBstr();
		
		btiempo= new JLabel(bstr);
		btiempo.setBounds(410, 595, 100,40);
		btiempo.setFont( new Font( "Arial", Font.BOLD, 18 ));
		pPrincipal.add(btiempo);
		
		
		
//		textArea = new JTextArea();
//		pPrincipal.add(textArea);
//		
//		lblTextArea = new JLabel("Movimientos: ");
//		lblTextArea.setFont(lblTextArea.getFont ().deriveFont (18.0f));
//		lblTextArea.setBounds(744, 69, 241, 51);
//		pPrincipal.add(lblTextArea);
//
//		scroll = new JScrollPane();
//		scroll.setBounds(727, 136, 300, 460);
//		pPrincipal.add(scroll);
//		scroll.setViewportView(textArea);
		
		tabla= new SimpleTableDemo();
		tabla.setBounds(744, 69, 241, 500);
		pPrincipal.add(tabla);
		//Nota sobre la escritura: no es del todo correcta( no manifiesta  jaques o jaquemates
		//y cuando come el peón está mal

		
		
	}	
	public tablerovisual(tablerologico tablerete) {
		
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	class SimpleTableDemo extends JPanel {
	   
	    Object[][] data;
	 
	    public SimpleTableDemo() {
	        super(new GridLayout(1,0));
	 
	        String[] columnNames = {"Blanco",
	                                "Negro",
	                                };
	 
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
	 
	        final JTable table = new JTable(data, columnNames);
	        table.setPreferredScrollableViewportSize(new Dimension(200, 500));
	        table.setFillsViewportHeight(true);
	 
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
