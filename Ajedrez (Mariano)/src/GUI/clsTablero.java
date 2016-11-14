package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;




import LN.clsUsuario;

public class clsTablero extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	JPanel pPrincipal;
	static clsCasilla [][] tablero;
	
	JLabel blanquito;
	JLabel nigga;
	
	JLabel ntiempo;

	LinkedList<clsPieza> pblancas;
	LinkedList<clsPieza> pnegras;
	LinkedList<clsCasilla> movact;
	
	clsRey reyb;
	clsRey reyn;
	
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
		
		pblancas.add(new clsTorre(0,0,true));
		pblancas.add(new clsCaballo(0,1,true));
		pblancas.add(new clsAlfil(0,2,true));
		pblancas.add(reyb);
		pblancas.add(new clsReina(0,4,true));
		pblancas.add(new clsAlfil(0,5,true));
		pblancas.add(new clsCaballo(0,6,true));
		pblancas.add(new clsTorre(0,7,true));
		
		pblancas.add(new clsPeon(1,0,true));
		pblancas.add(new clsPeon(1,1,true));
		pblancas.add(new clsPeon(1,2,true));
		pblancas.add(new clsPeon(1,3,true));
		pblancas.add(new clsPeon(1,4,true));
		pblancas.add(new clsPeon(1,5,true));
		pblancas.add(new clsPeon(1,6,true));
		pblancas.add(new clsPeon(1,7,true));
		
		pnegras.add(new clsTorre(7,0,false));
		pnegras.add(new clsCaballo(7,1,false));
		pnegras.add(new clsAlfil(7,2,false));
		pnegras.add(reyn);
		pnegras.add(new clsReina(7,4,false));
		pnegras.add(new clsAlfil(7,5,false));
		pnegras.add(new clsCaballo(7,6,false));
		pnegras.add(new clsTorre(7,7,false));
		
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
		
		
	}	
	
	public void clear(LinkedList <clsCasilla> borrar)
	{
		for(clsCasilla aux: borrar)
		{
			System.out.println(aux.provisional);
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
			aux.mov=false;
			aux.setIcon(null);
			}
		}
		borrar.clear();
	}
	public void actionPerformed(ActionEvent arg) {
		// TODO Auto-generated method stub

		clsCasilla casilla=(clsCasilla) arg.getSource();
		
		if(casilla.mov)
		{
			tablero[selec.getY()][selec.getX()].setOcupado(null);
			casilla.setOcupado(selec);
			movact.remove(casilla);
			selec=null;
			clear(movact);
		}
		else if(casilla.getOcupado()==null)
		{
			clear(movact);
		}
		else if(casilla.getOcupado()!=null)
		{
			if(casilla.provisional==false)
			{
			clear(movact);
			selec=casilla.getOcupado();
			selec.mov(tablero);
			for(clsCasilla aux: selec.getMovimientos())
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
	
			else
			{
				
				if(selec.getColor())
					pblancas.remove(casilla.getOcupado());
				else
					pnegras.remove(casilla.getOcupado());
				
				tablero[selec.getY()][selec.getX()].setOcupado(null);
				casilla.setOcupado(selec);
				casilla.provisional=false;
				int i=casilla.getx();
				int j=casilla.gety();
			if((i+j)%2==0)
				casilla.setBackground(Color.WHITE);
			else
				casilla.setBackground(Color.GRAY);
			
				movact.remove(casilla);
				selec=null;
				clear(movact);
			}
		}
		this.repaint();
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

