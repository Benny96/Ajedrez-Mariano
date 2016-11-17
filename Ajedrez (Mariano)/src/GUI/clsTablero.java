package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



import LN.clsUsuario;

public class clsTablero extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	JPanel pPrincipal;
	clsCasilla [][] tablero;
	
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
	private JLabel btiempo;
	
	clsPieza selec;
	
	
	private JScrollPane scrollPane;
	JTextArea textArea;
	
	public static void main(String[] args) 
	{

//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					clsPaginaPrincipal p=new clsPaginaPrincipal();
//					p.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});

		clsTablero a=new clsTablero();
		a.setVisible(true);

	}
	
	public clsTablero() 
	{
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1360, 720);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pPrincipal);
		pPrincipal.setLayout(null);
		
		tablero= new clsCasilla[8][8];
		

		
		clsUsuario ublanco= new clsUsuario("blanquito","a","a","blanquito","a");
		clsUsuario unigga= new clsUsuario("nigga","b","b","nigga","b");
		
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
				tablero[i][j]=new clsCasilla(i, j);
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
		
		turno=true;
		
		pblancas= new LinkedList<clsPieza>();
		pnegras= new LinkedList<clsPieza>();
		movact= new LinkedList<clsCasilla>();
		
		reyb=new clsRey(0,3,true);
		reyn=new clsRey(7,3,false);
		
		btorrei=new clsTorre(0,7,true);
		btorred=new clsTorre(0,0,true);
		ntorrei=new clsTorre(7,7,false);
		ntorred=new clsTorre(7,0,false);
		
		
		
		pblancas.add(btorred);
		pblancas.add(new clsCaballo(0,1,true));
		pblancas.add(new clsAlfil(0,2,true));
		pblancas.add(reyb);
		pblancas.add(new clsReina(0,4,true));
		pblancas.add(new clsAlfil(0,5,true));
		pblancas.add(new clsCaballo(0,6,true));
		pblancas.add(btorrei);
		
		pblancas.add(new clsPeon(1,0,true));
		pblancas.add(new clsPeon(1,1,true));
		pblancas.add(new clsPeon(1,2,true));
		pblancas.add(new clsPeon(1,3,true));
		pblancas.add(new clsPeon(1,4,true));
		pblancas.add(new clsPeon(1,5,true));
		pblancas.add(new clsPeon(1,6,true));
		pblancas.add(new clsPeon(1,7,true));
		
		pnegras.add(ntorred);
		pnegras.add(new clsCaballo(7,1,false));
		pnegras.add(new clsAlfil(7,2,false));
		pnegras.add(reyn);
		pnegras.add(new clsReina(7,4,false));
		pnegras.add(new clsAlfil(7,5,false));
		pnegras.add(new clsCaballo(7,6,false));
		pnegras.add(ntorrei);
		
		pnegras.add(new clsPeon(6,0,false));
		pnegras.add(new clsPeon(6,1,false));
		pnegras.add(new clsPeon(6,2,false));
		pnegras.add(new clsPeon(6,3,false));
		pnegras.add(new clsPeon(6,4,false));
		pnegras.add(new clsPeon(6,5,false));
		pnegras.add(new clsPeon(6,6,false));
		pnegras.add(new clsPeon(6,7,false));
		
		for(clsPieza aux: pblancas)
		{
			tablero[aux.getY()][aux.getX()].setOcupado(aux);
		}
		for(clsPieza aux: pnegras)
		{
			tablero[aux.getY()][aux.getX()].setOcupado(aux);
		}
		
		nmin=10;
		nseg = 00;
		nstr = String.format("%d:%02d", nmin, nseg);
		
		ntiempo= new JLabel(nstr);
		ntiempo.setBounds(160, 80, 100,40);
		ntiempo.setFont( new Font( "Arial", Font.BOLD, 18 ));
		pPrincipal.add(ntiempo);
		
		bmin=10;
		bseg = 00;
		bstr = String.format("%d:%02d", bmin, bseg);
		
		btiempo= new JLabel(bstr);
		btiempo.setBounds(410, 595, 100,40);
		btiempo.setFont( new Font( "Arial", Font.BOLD, 18 ));
		pPrincipal.add(btiempo);
		
		//para que no meleste
//		myTimer = new Timer1();
//		
//		Thread a= new Thread (myTimer);
//		a.start();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 27, 650, 341);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
//		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		add(textArea, BorderLayout.EAST);
		textArea.add(scrollPane);
		textArea.setBounds(670, 82, 300, 500);
		
	}	
	
	public void clear(LinkedList <clsCasilla> borrar)
	{
		for(clsCasilla aux: borrar)
		{
			
			if(aux.provisional)
			{
				System.out.println("ERTYUI");
				int i=aux.getx();
				int j=aux.gety();
				aux.provisional=false;
			if((i+j)%2==0)
				tablero[j][i].setBackground(Color.WHITE);
			else
				tablero[j][i].setBackground(Color.GRAY);
			}
			else
			{
				if(aux.getOcupado()==null)
				{
			aux.mov=false;
			aux.setIcon(null);
			}
			}
		}
		borrar.clear();
	}
	
	public void revisar()
	{
		LinkedList<clsPieza> todas = null;
		todas.addAll(pblancas);
		todas.addAll(pnegras);
		for(clsPieza pieza: todas)
		{
			for(clsCasilla casilla: pieza.getMovimientos())
			{
				
			}
		}
	}
	
	public LinkedList<clsPieza> getpniegras()
	{
		return pnegras;
	}
	public Boolean comprobarjaque( clsRey rey, clsCasilla[][] tablero)
	{
		LinkedList<clsPieza> colorcete;
		
		if(rey.getColor())
			colorcete=pnegras;
		else
			colorcete=pblancas;
		
		rey.mov(tablero);//revisar a futuro
		
		for(clsPieza paux: colorcete )
		{
			paux.mov(tablero);
			for(clsCasilla caux: paux.getMovimientos())
			{
				if(caux.equals(rey.sitio(tablero)))
				{
					return true;
				}
			}
		}
			return false;
			
		
	}
	public void dibujarmov(LinkedList<clsCasilla> lista)
	{
		for(clsCasilla aux: lista)
		{
		if(aux.getOcupado()==null)
		{
			aux.imov();
			movact.add(aux);
		}
		else
		{
		aux.provisional=true;
		aux.setBackground(Color.red);
		movact.add(aux);
		}
		}
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
		 
		 System.out.println(ncasilla);

		if(ncasilla.getOcupado()==null)
		{
			if(ncasilla.mov)
			{
				tablero[selec.getY()][selec.getX()].setOcupado(null);
				if(selec.a.equals(Comun.clsConstantes.piezas.Rey))
				{
					if(selec.getPrimera()==false)
						selec.setPrimera(true);
					
//					if(selec.getColor() && ncasilla.getx()==0 && ncasilla.gety()==1 && reyb.getPrimera()==true && btorred.getPrimera()==true && tablero[0][2].getOcupado()==null )
//					{
//						tablero[0][2].setOcupado(btorred);
//						tablero[0][0].setOcupado(null);
//					}
					if(selec.getColor() && ncasilla.getx()==1 && ncasilla.gety()==0  )
					{
						tablero[0][2].setOcupado(btorred);
						tablero[0][0].setOcupado(null);
					}
					if(selec.getColor() && ncasilla.getx()==5 && ncasilla.gety()==0  )
					{
						tablero[0][4].setOcupado(btorrei);
						tablero[0][7].setOcupado(null);
					}
					
					if(selec.getColor()==false && ncasilla.getx()==1 && ncasilla.gety()==7  )
					{
						tablero[7][2].setOcupado(ntorred);
						tablero[7][0].setOcupado(null);
					}
					if(selec.getColor()==false && ncasilla.getx()==5 && ncasilla.gety()==7 )
					{
						tablero[7][4].setOcupado(ntorrei);
						tablero[7][7].setOcupado(null);
					}
					
				}
				if(selec.a.equals(Comun.clsConstantes.piezas.Torre))
				{
					if(selec.getPrimera()==false)
						selec.setPrimera(true);
						
				}
				System.out.println("SDFGHJKHGHFDFHJ");
				System.out.println(selec.sitio(tablero));
				ncasilla.setOcupado(selec);
				movact.remove(ncasilla);
				if(selec.getColor())
					reyn.jaque=comprobarjaque(reyn,tablero);
				else
					reyb.jaque=comprobarjaque(reyb,tablero);
				selec=null;
				clear(movact);
				turno=!turno;
			
				//revisar();
			}
			clear(movact);
		}
		else if(ncasilla.getOcupado()!=null)
		{
			//clear(movact);
			
			if(ncasilla.getOcupado().a.equals(Comun.clsConstantes.piezas.Rey))
			{
				clear(movact);
				selec=ncasilla.getOcupado();
				selec.mov(tablero);
				LinkedList<clsCasilla> lista= new LinkedList<clsCasilla>();
				selec.mov(tablero);
				lista.addAll(selec.movimientos);
				
				if(selec.getColor()== turno)
				{	
					System.out.println("DFGHJKLKGFGHJKkjvbvcvbnbvcvbnbvbnmnbv");
					LinkedList<clsPieza> colorcete;
					
					if(selec.getColor())
						colorcete=pnegras;
					else
						colorcete=pblancas;
					
						for(clsPieza pieza: colorcete)
						{
							for(clsCasilla casilla: pieza.influencia(tablero))
							{
								lista.remove(casilla);
							}
						}
						dibujarmov(lista);
					
				}
				
			
			}
			else{
				
			if(ncasilla.provisional==false)
			{
			clear(movact);
			
			selec=ncasilla.getOcupado();
			
			if (selec.getColor()== turno)
			{
				selec.mov(tablero);
				dibujarmov(selec.movimientos);
			}

			}
			
	
	
			else
			{
				if(selec.getColor())
					pblancas.remove(ncasilla.getOcupado());
				else
					pnegras.remove(ncasilla.getOcupado());
				
				tablero[selec.getY()][selec.getX()].setOcupado(null);
				ncasilla.setOcupado(selec);
				ncasilla.provisional=false;
				int i=ncasilla.getx();
				int j=ncasilla.gety();
			if((i+j)%2==0)
				ncasilla.setBackground(Color.WHITE);
			else
				ncasilla.setBackground(Color.GRAY);
			
				movact.remove(ncasilla);
				if(selec.getColor())
					reyn.jaque=comprobarjaque(reyn,tablero);
				else
					reyb.jaque=comprobarjaque(reyb,tablero);
				selec=null;
				clear(movact);
				turno=!turno;
			//	revisar();
			}
		}
		}
		this.repaint();
		System.out.println("el rey nigro");
		System.out.println(reyn.jaque);
		System.out.println("el rey blanco");
		System.out.println(reyb.jaque);
	}

	
	private void Conversor()
	{
		if(turno)
		{
			bseg--;
			if (bseg==-1)
			{
				bseg=59;
				bmin--;
			}
			bstr = String.format("%d:%02d", bmin, bseg);
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
		ntiempo.setText(nstr);
		}
		this.repaint();
	}
	
	
	class Timer1 implements Runnable
	{
		@Override
		public void run() 
		{
			while(true)
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
		}
	
	}
	}

