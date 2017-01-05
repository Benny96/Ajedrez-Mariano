package Mariano;



import java.awt.Color;
import java.io.File;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

























import Comun.clsConstantes;
import GUI.clsEleccion;
import LN.clsAlfil;
import LN.clsCaballo;
import LN.clsCasilla;
import LN.clsJugada;
import LN.clsPeon;
import LN.clsPieza;
import LN.clsReina;
import LN.clsRey;
import LN.clsTorre;
import LN.clsUsuario;
import Persistencia.clsBD;
import Unopauno.TableroLogico1v1;



public class TableroLogicoMariano implements Cloneable, Serializable, Comparable <TableroLogicoMariano>{

	private static final long serialVersionUID = 1L;

	clsCasilla [][] tablero;
	
//	JLabel blanquito;
//	JLabel nigga;
//	
//	JLabel ntiempo;
//	
//	JList asd;

	private int ID_partida;
	private Date fec_com;
	private Date fec_fin;
	private String ganadorString;
	
	public LinkedList<clsPieza> pblancas;
	public LinkedList<clsPieza> pnegras;
	
	public LinkedList<clsPieza> cpblancas;
	public LinkedList<clsPieza> cpnegras;
	
	
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
	
	 TableroVisualMariano visual;
	//private Runnable myTimer;
	
	int nmin;
	int nseg;
	private String nstr;

	private int bmin;
	private int bseg;
	private String bstr;
//	private JLabel btiempo;
	
	clsPieza selec;

	private clsUsuario unigga;

	private clsUsuario ublanco;
	
	boolean jaquemate;

	private clsReina reinab;
	
	Runnable reloj;

	private clsReina reinan;
	
	clsUsuario ganador;
	clsUsuario perdedor;
	
	int num=0;
	
	LinkedList<clsJugada> jugadasblancas;
	HashSet<clsJugada> jugadasnegras;
	
	public TableroLogicoMariano()
	{
	tablero= new clsCasilla[8][8];
	
	for(int i=0;i<8;i++)
	{
	for(int j=0;j<8;j++)
	{
	tablero[i][j]=new clsCasilla(i, j);	
	//tablero[i][j].setVisible(false);
	
	}
	}
	
	reyb=new clsRey(0,3,true);
	reyn=new clsRey(7,3,false);
	
	pblancas= new LinkedList<clsPieza>();
	pnegras= new LinkedList<clsPieza>();
	movact= new LinkedList<clsCasilla>();
	
	selec=null;
	
	}
	public TableroLogicoMariano (int a, String b, String c, long d, long e, String f)
	{
		ID_partida = a;
		ublanco = new clsUsuario ();
		ublanco.setNickname(b);
		unigga = new clsUsuario();
		unigga.setNickname(c);
		fec_com = new Date(d);
		fec_fin = new Date(e);
		ganadorString = f;
	}
	public TableroLogicoMariano(Boolean asd, TableroVisualMariano tablerovisual, Runnable myTimer, clsUsuario usu) 
	{
//		ganadorString = "";
//		clsBD.crearTablaBD(clsConstantes.PARTIDA);
//		ResultSet rs = clsBD.obtenerDatosTablaBD (clsConstantes.PARTIDA);
//		try 
//		{
//			while (rs.next())
//			{
//				ID_partida++;
//			}
//		} 
//		catch (SQLException e)
//		{
//			e.printStackTrace();
//		}
//		fec_com = new Date();
//		fec_fin = new Date(new Long(0) );
	tablero= new clsCasilla[8][8];
	
	visual=tablerovisual;
	
	
//	ublanco= new clsUsuario("blanquito","a","a","blanquito","a");
	ublanco=usu;
	unigga= new clsUsuario("nigga","b","b","MARIANO","b");
	
	jaquemate=false;
	
	for(int i=0;i<8;i++)
	{
	for(int j=0;j<8;j++)
	{
	tablero[i][j]=new clsCasilla(i, j);	
	
	}
	}
	
	turno=true;
	
	pblancas= new LinkedList<clsPieza>();
	pnegras= new LinkedList<clsPieza>();
	
	cpblancas= new LinkedList<clsPieza>();
	cpnegras= new LinkedList<clsPieza>();
	
	movact= new LinkedList<clsCasilla>();
	
	reyb=new clsRey(0,3,true);
	reyn=new clsRey(7,3,false);
	
	
	jugadasblancas=new LinkedList<clsJugada>();
	jugadasnegras=new HashSet<clsJugada>();
	
	
	reinab=new clsReina(0,4,true);
	//reinab=new clsReina(2,2,true);
	reinan=new clsReina(7,4,false);
	
	btorrei=new clsTorre(0,7,true);
	btorred=new clsTorre(0,0,true);
	ntorrei=new clsTorre(7,7,false);
	ntorred=new clsTorre(7,0,false);

	pblancas.add(btorred);
	pblancas.add(new clsCaballo(0,1,true));
	pblancas.add(new clsAlfil(0,2,true));
	pblancas.add(reyb);
	pblancas.add(reinab);
	pblancas.add(new clsAlfil(0,5,true));
	//pblancas.add(new clsAlfil(3,5,true));
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
	//pnegras.add(new clsReina(5,2,false));
	pnegras.add(reinan);
	pnegras.add(new clsAlfil(7,5,false));
	//pnegras.add(new clsAlfil(4,5,false));
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
	
	for(clsPieza aux: pblancas)
	{
	aux.mov(tablero);
	}
	for(clsPieza aux: pnegras)
	{
	aux.mov(tablero);
	}
	
	nmin=10;
	nseg = 00;
	nstr = String.format("%d:%02d", nmin, nseg);
	
	bmin=10;
	bseg = 00;
	bstr = String.format("%d:%02d", bmin, bseg);
	
	
	reloj = new Timer1();
	
	Thread a= new Thread (reloj);
	a.start();
	}	
	

//	public static void pintar()
//	{
//	visual.repaint();
//	}
	public TableroLogicoMariano clonar(TableroLogicoMariano tab)
	{
	TableroLogicoMariano mewto = new TableroLogicoMariano();
	
	
	for(clsPieza paux: tab.getPblancas())
	{
	if(paux instanceof clsRey)
	{
	mewto.setReyb((clsRey) paux.clonar(tab.getReyb(),tab));
	mewto.getPblancas().add(mewto.getReyb());
	}
	else
	{
	mewto.getPblancas().add(paux.clonar(paux,tab));
	}
	}
	for(clsPieza paux: tab.getPnegras())
	{
	if(paux instanceof clsRey)
	{
	mewto.setReyn((clsRey) paux.clonar(tab.getReyn(),tab));
	mewto.getPnegras().add(mewto.getReyn());
	}
	else{
	mewto.getPnegras().add(paux.clonar(paux,tab));
	}
	}
	
	for(clsPieza aux: mewto.getPblancas())
	{
	mewto.getTablero()[aux.getY()][aux.getX()].setOcupado(aux);
	}
	for(clsPieza aux:mewto.getPnegras())
	{
	mewto.getTablero()[aux.getY()][aux.getX()].setOcupado(aux);
	}
	
	
	//mewto.setSelec(tab.getSelec().clonar(tab.getSelec(),tab));
	
	mewto.setTurno(tab.getTurno());
	
	return mewto;
	
	}
	
	public void clear(LinkedList <clsCasilla> borrar)
	{
	for(clsCasilla aux: borrar)
	{
	
	if(aux.provisional)
	{
	//System.out.println("ERTYUI");
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
	aux.paint(aux.getGraphics());
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
	
	
	public boolean jaquematen(TableroLogicoMariano tab)
	{
	//tablerologico tablerete=clonar(this);
	
	for(clsPieza paux: tab.pnegras)
	{
	if(legales(paux,tab).size()!=0)
	{
	return false;
	}
	}
	
	return true;
	}
	public boolean jaquemateb(TableroLogicoMariano tab)
	{
	
	for(clsPieza paux: tab.pblancas)
	{
	
	if(legales(paux,tab).size()!=0)
	{
	return false;
	}
	}
	
	return true;
	}
	
	public Boolean comprobarjaque(clsRey rey,TableroLogicoMariano tab)

	
	{
	LinkedList<clsPieza> colorcete;
	
	clsCasilla[][] tablero=tab.getTablero();
	
	
	if(rey.getColor())
	colorcete=tab.getPnegras();
	else
	colorcete=tab.getPblancas();
	
	//rey.mov(tablero);//revisar a futuro
	
	for(clsPieza paux: colorcete )
	{
//	if(paux instanceof clsReina)
//	{
//	System.out.println("ASDFGHJKLOIUYTREWAZXCVBNM;");
//	}
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
	
	
	public void despuesintel(clsJugada definitiva)
	{
		
		jugadasblancas.clear();
		
		int x=definitiva.pieza.getX();
		int y=definitiva.pieza.getY();
		
		Boolean ocupado=false;
		
		if(definitiva.pieza.getPrimera()==false)
			definitiva.pieza.setPrimera(true);
		if(tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].getOcupado()!=null)
		{
		ocupado=true;
		this.pblancas.remove(tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].getOcupado());
		}
		tablero[y][x].setOcupado(null);
		
		
		System.out.println("EREERRRRRRRRRRRRRRRRRRRRRRRRRRRRRR "+ definitiva.pieza.getY());
		if(definitiva.pieza.a.equals(Comun.clsConstantes.piezas.Peon))
		{
			System.out.println("EREERRRRRRRRRRRRRRRRRRRRRRRRRRRRRR "+ definitiva.pieza.getY());
			if(definitiva.pieza.getY()==1 && definitiva.pieza.getColor()==false)
			{
				System.out.println("EREERRRRRRRRRRRRRRRRRRRRRRRRRRRRRR "+ definitiva.pieza.getY());
				pnegras.remove(definitiva.pieza);
				clsReina auxr=new clsReina(definitiva.pieza.getX(),definitiva.pieza.getY(),false);
				pnegras.add(auxr);
				definitiva.pieza=auxr;
			}
			
		}
//		tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].setPieza(definitiva.pieza);
//		tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].mov=false;
//		System.out.println("sssssssssssssssss " +definitiva.pieza.getClass());
//		tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].setIcon(definitiva.pieza.getIcon());
//		definitiva.pieza.setY(tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].gety());
//		definitiva.pieza.setX(tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].getx());
	//	
//		tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].paintComponents(tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].getGraphics());
		
		//System.out.println(this.tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].getOcupado().getClass());
		//this.tablero[0][1].setOcupado(null);
		tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].setOcupado(definitiva.pieza);
		
		String letra=null;
		if(definitiva.pieza.a.equals(Comun.clsConstantes.piezas.Peon))
		{
			letra="";
		}
		if(definitiva.pieza.a.equals(Comun.clsConstantes.piezas.Caballo))
		{
			letra="C";
		}
		if(definitiva.pieza.a.equals(Comun.clsConstantes.piezas.Torre))
		{
			letra="T";
		}
		if(definitiva.pieza.a.equals(Comun.clsConstantes.piezas.Reina))
		{
			letra="D";
		}
		if(definitiva.pieza.a.equals(Comun.clsConstantes.piezas.Rey))
		{
			letra="R";
		}
		if(definitiva.pieza.a.equals(Comun.clsConstantes.piezas.Alfil))
		{
			letra="A";
		}
		if(definitiva.pieza.getColor()==false)
		{
			if(ocupado)
				visual.tabla.data[num][1]=letra+"x"+definitiva.pieza.escritura();
			else
				visual.tabla.data[num][1]=letra+definitiva.pieza.escritura();
			num++;
		}
		if(definitiva.pieza.primera==false)
		definitiva.pieza.primera=true;
		//definitiva.cfinal.setOcupado(definitiva.pieza);
		if(definitiva.pieza.getIcon()!=null)
		{
		System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");
		}
		System.out.println(definitiva.cfinal.getOcupado().getClass());
		System.out.println("definitivo "+definitiva.valor);
		System.out.println(y);
		System.out.println(x);
		
		
		//pintar();
//		jaquemate=jaquemateb(this);
//		if(jaquemate)
//		{
//		visual.repaint();
//		System.out.println("SoyDioooooooooooooooooooooooos");
//		}
	}
	public LinkedList<clsPieza> clonarlistas(LinkedList<clsPieza> piezas, boolean color, TableroLogicoMariano a)
	{
		LinkedList<clsPieza> fin= new LinkedList<clsPieza>();
		
		if(color)
		{
			for(clsPieza paux: a.getPblancas())
		{
		fin.add(paux.clonar(paux,a));
		
		}
		}
		else
		{
			for(clsPieza paux: a.getPnegras())
		{
		fin.add(paux.clonar(paux,a));
		
		}
		}
		
		
		return fin;
		
	}
	
	
	public void vlarartadasjugadas(TableroLogicoMariano a)
	{
		clsJugada definitiva= new clsJugada();
		TableroLogicoMariano tablero=new TableroLogicoMariano();
		tablero=clonar(a);
		for(clsPieza p: a.pnegras)
		{
		
		System.out.println("11wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
		System.out.println(p.getClass());
		System.out.println(p);
		int iy=p.getY();
		int ix=p.getX();
		
		
		LinkedList<clsCasilla> candidatos=legales(p,a);
		
		
		
		
		
		for(clsCasilla caux: candidatos)
		{
//		System.out.println("candidatos "+ caux);
		clsJugada aux= new clsJugada(p,caux);
		
		aux.valor=Valorar(aux,tablero,1,1);
//		System.out.println(aux.valor);
		if(aux.valor>definitiva.valor)
		{
		definitiva=aux;
		}
		
		}
		
		}
		
		
	}
	public clsJugada Inteligencia()
	{
	clsJugada definitiva= new clsJugada();
	TableroLogicoMariano tablero=new TableroLogicoMariano();
	tablero=clonar(this);
	for(clsPieza p: this.pnegras)
	{
	
		
	System.out.println("11wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
	System.out.println(p.getClass());
	System.out.println(p);
	int iy=p.getY();
	int ix=p.getX();
	
	
	LinkedList<clsCasilla> candidatos=legales(p,this);
	
	
	
	
	
	for(clsCasilla caux: candidatos)
	{
//	System.out.println("candidatos "+ caux);
	clsJugada aux= new clsJugada(p,caux);
	
	aux.valor=Valorar(aux,tablero,1,1);
//	System.out.println(aux.valor);
	if(aux.valor>definitiva.valor)
	{
	definitiva=aux;
	}
	
	}
	
	}
	
	return definitiva;
	
	}
	public clsJugada Inteligencia2(TableroLogicoMariano tablero)
	{
	clsJugada definitiva= new clsJugada();
	
	TableroLogicoMariano t=new TableroLogicoMariano();
	t=clonar(tablero);
	
	for(clsPieza p: tablero.pnegras)
	{
	
//	System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
//	System.out.println(p.getClass());
//	System.out.println(p);
	int iy=p.getY();
	int ix=p.getX();
	
	
	LinkedList<clsCasilla> candidatos=legales(p,tablero);
	
	
	for(clsCasilla caux: candidatos)
	{
//	System.out.println("candidatos "+ caux);
	clsJugada aux= new clsJugada(p,caux);
	
	aux.valor=Valorar(aux,t,1,2);
//	System.out.println(aux.valor);
	if(aux.valor>definitiva.valor)
	{
	definitiva=aux;
	}
	
	}
	
	}


	return definitiva;
	}
	public int Valorar(clsJugada jugada,TableroLogicoMariano tablero, int numero,int numeroblanquito)

	{
	
	boolean comiste=false; 
//	tablerolerologico1 tablero=new tablerolerologico1();
//	tablero=clonar(tablerolero);
//	clsCasilla [][] tableroaux=tablero.gettablerolero();
	
//	tablerolerologico1 tablero=new tablerolerologico1();
//	tablero=clonar(tablerolero);
	clsCasilla [][] tableroaux=tablero.getTablero();
	
	int valor=0;
	
	clsPieza pieza= jugada.pieza;
	
	int x= pieza.getX();
	int y=pieza.getY();
	
	clsCasilla cfinal= jugada.cfinal;
	
	int cx= cfinal.getx();
	int cy=cfinal.gety();

	clsPieza okupada=null;//ya es hora de ponerlo en un metodo
	if(cfinal.getOcupado()!=null)
	{
	okupada=cfinal.getOcupado();
	
	if(okupada.getColor())
	{
	tablero.getPblancas().remove(okupada);
	}
	else
	{
	tablero.getPnegras().remove(okupada);
	comiste=true;
	}
	}

	if(tableroaux[cy][cx].getOcupado()!=null && tableroaux[cy][cx].getOcupado().getColor().equals(pieza.getColor())==false)
	{
	 valor= tableroaux[cy][cx].getOcupado().getValor();
	}
	
	if(pieza.primera==false)
	{
	valor=(valor+10);
	}
	
	if(pieza.getColor())
		tablero.getPblancas().remove(pieza);

	else
		tablero.getPnegras().remove(pieza);
	
	tableroaux[cy][cx].setOcupado(pieza);
	
	tableroaux[y][x].setOcupado(null);
	
//	if(comiste && pieza.getColor()&& numeroblanquito==1)
//	{
//		tablerologico1 tablero3=new tablerologico1();
//		tablero3=clonar(tablero);
//		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//		//valor=valor -Valorar(Inteligencia2(tablero3),tablero3,3,3);
//		valor=valor - Valorar(Inteligencia2(tablero3),tablero3,3,3);
//	}
//	
	
	//tablero.setTurno(!tablero.getTurno());

//
	if(pieza.getColor())
	{
	tablero.getPblancas().add(pieza);
	
	for(clsPieza dfg: tablero.getPblancas())
	{
	valor=valor+legales(dfg,tablero).size();
	}
	}
	else
	{
	tablero.getPnegras().add(pieza);
	for(clsPieza dfg: tablero.getPnegras())
	{
	valor=valor+legales(dfg,tablero).size();
	}
	}
	
	if(pieza.getColor())
	{
	if(jaquematen(tablero))
	{
	valor=valor+90000;
	if(okupada!=null)
	{
	if(okupada.getColor())
	tablero.getPblancas().add(okupada);
	else
	tablero.getPnegras().add(okupada);
	}
	
	if(pieza.getColor())
		tablero.getPblancas().remove(pieza);

	else
		tablero.getPnegras().remove(pieza);
	
	tableroaux[cy][cx].setOcupado(okupada);
	tableroaux[y][x].setOcupado(pieza);
	
	
	
	if(pieza.getColor())
		tablero.getPblancas().add(pieza);
	else
		tablero.getPnegras().add(pieza);
//	System.out.println("jaqueeeeeeeeeeeeeemateeeeeeeeeeeeeeee");
	return valor;
	}
	}
	else
	{

	if(jaquemateb(tablero))
	{
	valor=valor+100000;
	if(okupada!=null)
	{
	if(okupada.getColor())
	tablero.getPblancas().add(okupada);
	else
	tablero.getPnegras().add(okupada);
	}
	if(pieza.getColor())
		tablero.getPblancas().remove(pieza);

	else
		tablero.getPnegras().remove(pieza);
	
	tableroaux[cy][cx].setOcupado(okupada);
	tableroaux[y][x].setOcupado(pieza);
	
	if(pieza.getColor())
	tablero.getPblancas().add(pieza);
	else
	tablero.getPnegras().add(pieza);
	System.out.println("jaqueeeeeeeeeeeeeemateeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	return valor;
	}
	}
	
//	if(numero==1||numero==2)
//	{
	//se podría usar el método inteligencia
//	System.out.println(valor);
	clsJugada peor=new clsJugada();
	
	if(numero==1)
	{
		
		TableroLogicoMariano tablero2=new TableroLogicoMariano();
		tablero2=clonar(tablero);
//	System.out.println("putos blanquitoooooooooooooooooooooooooooooooooooooos");
	for(clsPieza blanca: tablero.getPblancas())
	{
	clsPieza clon=blanca.clonar(blanca, tablero);
//	System.out.println(blanca.getClass());
//	System.out.println("original "+blanca);
//	System.out.println("clon "+clon);
	LinkedList<clsCasilla> candidatos=rlegales(clon,tablero);
	
	
	boolean holi=true;
	for(clsCasilla contrin: candidatos)
	{
//	System.out.println(contrin);
	clsJugada aux= new clsJugada(blanca,contrin);
	
	if(contrin.getx()==1 &&  contrin.gety()==4)
	{
		System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRrrrrrrr");
		System.out.println(pieza.getClass());
		System.out.println(pieza);
		if(contrin.getPieza()==null)
			System.out.println("GUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUtttttttttt");
		else
		{
			System.out.println("mAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAALLLLLLLLLLLLLLLLL");
			System.out.println(contrin.getPieza().getClass());
			System.out.println(contrin.getPieza());
		}
			
	}
	holi=true;
	for(clsJugada ax: jugadasblancas)
	{
		if(((ax.cfinal.getPieza()!= null && aux.cfinal.getPieza()!=null && ax.cfinal.getPieza().equals(aux.cfinal.getPieza()))) || ( ax.cfinal.getPieza()== null && aux.cfinal.getPieza()==null  )  )
		{
			System.out.println("HOLLLLLLLLLLLLLLLLLLLLLLLLAAAAAAAAAAAAAAAAAA");
			holi=false;
			aux.valor=ax.valor;
			break;
		}
		
	}
	
	
	if(holi)
	{
		System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		aux.valor=Valorar(aux,tablero2,2,numeroblanquito);
		jugadasblancas.add(aux);
	}
//	aux.valor=Valorar(aux,tablero2,2,numeroblanquito);
	if(aux.valor>peor.valor)
		peor=aux;

	}
	
	
	}
	valor=valor-peor.valor;
	}
	

	
	
	if(okupada!=null)
	{
	if(okupada.getColor())
	tablero.getPblancas().add(okupada);
	else
	tablero.getPnegras().add(okupada);
	}
	if(pieza.getColor())
		tablero.getPblancas().remove(pieza);

	else
		tablero.getPnegras().remove(pieza);
	
	tableroaux[cy][cx].setOcupado(okupada);
	tableroaux[y][x].setOcupado(pieza);
	
	if(pieza.getColor())
	tablero.getPblancas().add(pieza);
	else
	tablero.getPnegras().add(pieza);
	
	
	
	
	return valor;
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
	
	public Boolean Jugadajaque(clsPieza movida,clsPieza sitio, clsCasilla Original, clsCasilla Final,TableroLogicoMariano tab)
	{
	clsCasilla[][] tablero=tab.getTablero();
	
	Boolean retur=false;
	
	Original.setOcupado(null);
	Final.setOcupado(movida);
	
	if(movida.getColor())
	{
	for(clsPieza p: tab.getPblancas())
	{
	if(p instanceof clsRey)
	tab.setReyb((clsRey) p.clonar(p,this));
	}
	//System.out.println("aaaaaaaaaaaaablancoooooooo");
	if(comprobarjaque(tab.getReyb(),tab))
	{
	//System.out.println("blancoooooooo");
	retur=true;
	}
	}
	else{
	for(clsPieza p: tab.getPnegras())
	{
	if(p instanceof clsRey)
	tab.setReyn((clsRey) p.clonar(p,this));
	}
	//System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaanegroooooooooooo");
	if(comprobarjaque(tab.getReyn(),tab))
	{
	//System.out.println("negroooooooooooo");
	retur=true;
	}
	}
	
	Original.setOcupado(movida);
	Final.setOcupado(sitio);
	
	return retur;
	
	
	
	
	}
	
	
	public void action(clsCasilla casilla) {
	// TODO Auto-generated method stub
	//2.comer al paso
	//3. peon al final
	//4.reloj 0 parar
	//5.metodo terminar partida
	//6. jaque
	
	 acasilla=ncasilla;
	 ncasilla=casilla;
	 
	 TableroLogicoMariano tab= new TableroLogicoMariano();
	 tab= clonar(this);
	 
//	 System.out.println("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
//	 
//	 for(int i=0;i<8;i++)
//	{
//	for(int j=0;j<8;j++)
//	{
//	System.out.println(i);
//	System.out.println(j);
//	System.out.println(tab.getTablero()[i][j].getOcupado());
//	}
//	}
	 
//	 System.out.println("Blaancaaaaaaaaaaaaas");
//	 for(clsPieza p: tab.pblancas)
//	 {
//	 System.out.println(p.getClass());
//	 System.out.println(p);
//	 }
//	 System.out.println("Negraçaaaaaaaaaaaaas");
//	 for(clsPieza p: tab.pnegras)
//	 {
//	 System.out.println(p.getClass());
//	 System.out.println(p);
//	 }
	 
	// System.out.println(ncasilla);

	if(ncasilla.getOcupado()==null)
	{
	if(ncasilla.mov)
	{
	tablero[selec.getY()][selec.getX()].setOcupado(null);
	//tablero[selec.getY()][selec.getX()].setBackground(Color.BLUE);
	tablero[selec.getY()][selec.getX()].paint(tablero[selec.getY()][selec.getX()].getGraphics());
	
	
	
	if(selec.a.equals(Comun.clsConstantes.piezas.Peon))
	{
		if(selec.getY()==6 && selec.getColor())
		{
			pblancas.remove(selec);
			clsReina auxr=new clsReina(selec.getX(),selec.getY(),true);
			pblancas.add(auxr);
			selec=auxr;
			
		}
		if(selec.getY()==1 && selec.getColor()==false)
		{
			pnegras.remove(selec);
			clsReina auxr=new clsReina(selec.getX(),selec.getY(),false);
			pnegras.add(auxr);
			selec=auxr;
		}
		
	}
	
	if(selec.a.equals(Comun.clsConstantes.piezas.Rey))
	{
	
	
//	if(selec.getColor() && ncasilla.getx()==0 && ncasilla.gety()==1 && reyb.getPrimera()==true && btorred.getPrimera()==true && tablero[0][2].getOcupado()==null )
//	{
//	tablero[0][2].setOcupado(btorred);
//	tablero[0][0].setOcupado(null);
//	}
	
	//el enroque funciona mal si la casilla continua está en jaque
	if(selec.getColor() && ncasilla.getx()==1 && ncasilla.gety()==0 && selec.getPrimera()==false)
	{
	tablero[0][2].setOcupado(btorred);
	tablero[0][0].setOcupado(null);
	}
	if(selec.getColor() && ncasilla.getx()==5 && ncasilla.gety()==0 && selec.getPrimera()==false )
	{
	tablero[0][4].setOcupado(btorrei);
	tablero[0][7].setOcupado(null);
	}
	
	if(  selec.getColor()==false && ncasilla.getx()==1 && ncasilla.gety()==7 && selec.getPrimera()==false)
	{
	tablero[7][2].setOcupado(ntorred);
	tablero[7][0].setOcupado(null);
	}
	if( selec.getColor()==false && ncasilla.getx()==5 && ncasilla.gety()==7 && selec.getPrimera()==false )
	{
	tablero[7][4].setOcupado(ntorrei);
	tablero[7][7].setOcupado(null);
	}
	
	}
	
	if(selec.getPrimera()==false)
		selec.setPrimera(true);
	
	
//	System.out.println("SDFGHJKHGHFDFHJ");
//	System.out.println(selec.sitio(tablero));
	ncasilla.setOcupado(selec);
	movact.remove(ncasilla);
	turno=!turno;
//	
//	for(int a=0;a>-1;a++)
//	{
//	System.out.println(a);
//	}
	ncasilla.paint(ncasilla.getGraphics());
	
	
	String letra=null;
	if(selec.a.equals(Comun.clsConstantes.piezas.Peon))
	{
		letra="";
	}
	if(selec.a.equals(Comun.clsConstantes.piezas.Caballo))
	{
		letra="C";
	}
	if(selec.a.equals(Comun.clsConstantes.piezas.Torre))
	{
		letra="T";
	}
	if(selec.a.equals(Comun.clsConstantes.piezas.Reina))
	{
		letra="D";
	}
	if(selec.a.equals(Comun.clsConstantes.piezas.Rey))
	{
		letra="R";
	}
	if(selec.a.equals(Comun.clsConstantes.piezas.Alfil))
	{
		letra="A";
	}
	if(selec.getColor())
	{
		visual.tabla.data[num][0]=letra+selec.escritura();
	}
	
	if(selec.getColor())
	{
	if(comprobarjaque(reyn,this))
	{
	jaquemate=jaquematen(this);
	if(jaquemate)
	{
		ganador=ublanco;
		perdedor=unigga;
		porque();
	}
	
	}
	}
	
	clear(movact);
	
	selec=null;
	
	
	despuesintel(Inteligencia());
	
	if(comprobarjaque(reyb,this))
	{
	visual.repaint();
	jaquemate=jaquemateb(this);
	
	if(jaquemate)
	{
		ganador=unigga;
		perdedor=ublanco;
		porque();
	}
	}
	
	turno=!turno;

	//revisar();
	}
	clear(movact);
	}
	else 
	{

	if(ncasilla.provisional==false)
	{
	clear(movact);
	
	selec=ncasilla.getOcupado();
	
	//System.out.println(selec.getClass());
	for(clsCasilla k: legales(selec,this))
	{
	
	//System.out.println(k);
	}
	if (selec.getColor()== turno)
	{
	
	LinkedList<clsCasilla> aux= legales(selec,this);

	dibujarmov(aux);
	}

	}
	
	
	
	else
	{

	if(selec.getColor())
	pnegras.remove(ncasilla.getOcupado());
	else
	pblancas.remove(ncasilla.getOcupado());
	
	if(selec.getPrimera()==false)
		selec.setPrimera(true);
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
	
	if(selec.a.equals(Comun.clsConstantes.piezas.Peon))
	{
		if(selec.getY()==6 && selec.getColor())
		{
			pblancas.remove(selec);
			clsReina auxr=new clsReina(selec.getX(),selec.getY(),true);
			pblancas.add(auxr);
			selec=auxr;
			
		}
		if(selec.getY()==1 && selec.getColor()==false)
		{
			pnegras.remove(selec);
			clsReina auxr=new clsReina(selec.getX(),selec.getY(),false);
			pnegras.add(auxr);
			selec=auxr;
		}
		
	}
	
	if(selec.a.equals(Comun.clsConstantes.piezas.Rey))
	{
	if(selec.getPrimera()==false)
	selec.setPrimera(true);
	}
	
	String letra=null;
	if(selec.a.equals(Comun.clsConstantes.piezas.Peon))
	{
		letra="";
	}
	if(selec.a.equals(Comun.clsConstantes.piezas.Caballo))
	{
		letra="C";
	}
	if(selec.a.equals(Comun.clsConstantes.piezas.Torre))
	{
		letra="T";
	}
	if(selec.a.equals(Comun.clsConstantes.piezas.Reina))
	{
		letra="D";
	}
	if(selec.a.equals(Comun.clsConstantes.piezas.Rey))
	{
		letra="R";
	}
	if(selec.a.equals(Comun.clsConstantes.piezas.Alfil))
	{
		letra="A";
	}
	if(selec.getColor())
	{
		visual.tabla.data[num][0]=letra+"x"+selec.escritura();
	}
	
	turno=!turno;
//	if(selec.getColor())
//	{
//	if(comprobarjaque(reyn,this))
//	{
//	jaquemate=jaquematen(this);
//	if(jaquemate)
//	System.out.println("Siiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
//	}
//	}
//	else
//	{
//	if(comprobarjaque(reyb,this))
//	{
//	jaquemate=jaquemateb(this);
//	if(jaquemate)
//	System.out.println("Siiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
//	}
//	}
	
	
	if(comprobarjaque(reyn,this))
	{
	jaquemate=jaquematen(this);
	if(jaquemate)
	{
		ganador=ublanco;
		perdedor=unigga;	
		porque();
	}
	}
	
	selec=null;
	clear(movact);
	
	despuesintel(Inteligencia());
	

	if(comprobarjaque(reyb,this))
	{
	jaquemate=jaquemateb(this);
	if(jaquemate)
	{
		ganador=unigga;
		perdedor=ublanco;
		porque();
	}
	}
	turno=!turno;
	
	//	revisar();
	}
	}
//	}
	
//	System.out.println("el rey nigro");
//	System.out.println(reyn.jaque);
//	System.out.println("el rey blanco");
//	System.out.println(reyb.jaque);
	}
	
	
	public LinkedList<clsCasilla> rlegales(clsPieza pieza,TableroLogicoMariano tab) {
	// TODO Auto-generated method stub
	
	//System.out.println(pieza.getClass());
//	tablerologico atab=new tablerologico();
//	atab=clonar(tab);
//	clsCasilla [][] tabaux=atab.getTablero();
	LinkedList<clsPieza> op=new LinkedList<clsPieza>();
	for(clsPieza p: tab.pblancas)
	{
	op.add(p);
	}
	
	
	LinkedList<clsCasilla> legales= new LinkedList<clsCasilla>();
	if(pieza instanceof clsRey==false)
	{
	//System.out.println(pieza.getMovimientos().size());
	pieza.mov(tab.getTablero());
	
//	for(int i=0;i<8;i++)
//	{
//	for(int j=0;j<8;j++)
//	{
//	System.out.println(i);
//	System.out.println(j);
//	System.out.println(tab.getTablero()[i][j].getOcupado());
//	}
//	}
	int y=pieza.getY();
	int x=pieza.getX();
//	if(pieza.getY()==2 && pieza.getX()==3)
//	{
//	System.out.println("me cago en tus muertooooooooooooooooooooooooooooooooooooooooooooos");
//	}
//	System.out.println("qwertyuiop "+ pieza);
	
	for(clsCasilla caux: pieza.getMovimientos())
	{
//	System.out.println("lalalalalala "+caux);
	clsPieza okupada=null;
	if(caux.getOcupado()!=null)
	{
	okupada=caux.getOcupado();
	
	if(okupada.getColor())
	op.remove(okupada);
	else
	tab.getPnegras().remove(okupada);
	}
	
	tab.getTablero()[y][x].setOcupado(null);
	caux.setOcupado(pieza);
	
	if(pieza.getColor())
	{	
	if(comprobarjaque(tab.getReyb(),tab)==false)
	{
	legales.add(tab.getTablero()[caux.gety()][caux.getx()]);
	}
	}
	else{
	if(comprobarjaque(tab.getReyn(),tab)==false)
	{
	legales.add(tab.getTablero()[caux.gety()][caux.getx()]);
	}
	}
	
	tab.getTablero()[y][x].setOcupado(pieza);
	caux.setOcupado(okupada);
	
	pieza.setY(y);
	pieza.setX(x);
	
	if(okupada!=null)
	{
	if(okupada.getColor())
	op.add(okupada);
	else
	tab.getPnegras().add(okupada);
	}
	}
	
	
	pieza.setY(y);
	pieza.setX(x);
	}
	else
	{
	pieza.mov(tab.getTablero());
	int y=pieza.getY();
	int x=pieza.getX();
	
	for(clsCasilla caux: pieza.getMovimientos())
	{
	clsPieza okupada=null;
	if(caux.getOcupado()!=null)
	{
	okupada=caux.getOcupado();
	
	if(okupada.getColor())
	op.remove(okupada);
	else
	tab.getPnegras().remove(okupada);
	}
	
	tab.getTablero()[y][x].setOcupado(null);
	caux.setOcupado(pieza);
	
	if(pieza.getColor())
	{	
	tab.getReyb().setY(pieza.getY());
	tab.getReyb().setX(pieza.getX());
	
	if(comprobarjaque(tab.getReyb(),tab)==false)
	{
	legales.add(tab.getTablero()[caux.gety()][caux.getx()]);
	}
	}
	else{
	tab.getReyn().setY(pieza.getY());
	tab.getReyn().setX(pieza.getX());
	if(comprobarjaque(tab.getReyn(),tab)==false)
	{
	legales.add(tab.getTablero()[caux.gety()][caux.getx()]);
	}
	}
	
	tab.getTablero()[y][x].setOcupado(pieza);
	caux.setOcupado(okupada);
	
	pieza.setY(y);
	pieza.setX(x);
	
	if(pieza.getColor())
	{	
	tab.getReyb().setY(y);
	tab.getReyb().setX(x);
	}
	else
	{
	tab.getReyn().setY(y);
	tab.getReyn().setX(x);
	}
	if(okupada!=null)
	{
	if(okupada.getColor())
	op.add(okupada);
	else
	tab.getPnegras().add(okupada);
	}
	}
	
	
	pieza.setY(y);
	pieza.setX(x);
	}
	return legales;

	}

	public LinkedList<clsCasilla> legales(clsPieza pieza,TableroLogicoMariano tab) {
	// TODO Auto-generated method stub
	
	//System.out.println(pieza.getClass());
	LinkedList<clsCasilla> legales= new LinkedList<clsCasilla>();
	if(pieza instanceof clsRey==false)
	{
	pieza.mov(tab.getTablero());
	int y=pieza.getY();
	int x=pieza.getX();
	
	for(clsCasilla caux: pieza.getMovimientos())
	{
	clsPieza okupada=null;
	if(caux.getOcupado()!=null)
	{
	okupada=caux.getOcupado();
	
	if(okupada.getColor())
	tab.getPblancas().remove(okupada);
	else
	tab.getPnegras().remove(okupada);
	}
	
	tab.getTablero()[y][x].setOcupado(null);
	caux.setOcupado(pieza);
	
	if(pieza.getColor())
	{	
	if(comprobarjaque(tab.getReyb(),tab)==false)
	{
	legales.add(tab.getTablero()[caux.gety()][caux.getx()]);
	}
	}
	else{
	if(comprobarjaque(tab.getReyn(),tab)==false)
	{
	legales.add(tab.getTablero()[caux.gety()][caux.getx()]);
	}
	}
	
	tab.getTablero()[y][x].setOcupado(pieza);
	caux.setOcupado(okupada);
	
	pieza.setY(y);
	pieza.setX(x);
	
	if(okupada!=null)
	{
	if(okupada.getColor())
	tab.getPblancas().add(okupada);
	else
	tab.getPnegras().add(okupada);
	}
	}
	
	
	pieza.setY(y);
	pieza.setX(x);
	}
	else
	{
	pieza.mov(tab.getTablero());
	
	int y=pieza.getY();
	int x=pieza.getX();
	
	for(clsCasilla caux: pieza.getMovimientos())
	{
	//System.out.println("casillllllllla "+ caux);
	clsPieza okupada=null;
	if(caux.getOcupado()!=null)
	{
	okupada=caux.getOcupado();
	
	if(okupada.getColor())
	tab.getPblancas().remove(okupada);
	else
	tab.getPnegras().remove(okupada);
	}
	
	tab.getTablero()[y][x].setOcupado(null);
	caux.setOcupado(pieza);
	
	if(pieza.getColor())
	{	
	tab.getReyb().setY(pieza.getY());
	tab.getReyb().setX(pieza.getX());
	
	if(comprobarjaque(tab.getReyb(),tab)==false)
	{
	legales.add(tab.getTablero()[caux.gety()][caux.getx()]);
	}
	}
	else{
	tab.getReyn().setY(pieza.getY());
	tab.getReyn().setX(pieza.getX());
	if(comprobarjaque(tab.getReyn(),tab)==false)
	{
	legales.add(tab.getTablero()[caux.gety()][caux.getx()]);
	}
	}
	
	tab.getTablero()[y][x].setOcupado(pieza);
	caux.setOcupado(okupada);
	
	pieza.setY(y);
	pieza.setX(x);
	
	if(pieza.getColor())
	{	
	tab.getReyb().setY(y);
	tab.getReyb().setX(x);
	}
	else
	{
	tab.getReyn().setY(y);
	tab.getReyn().setX(x);
	}
	if(okupada!=null)
	{
	if(okupada.getColor())
	tab.getPblancas().add(okupada);
	else
	tab.getPnegras().add(okupada);
	}
	}
	
	
	pieza.setY(y);
	pieza.setX(x);
	}
	return legales;

	}

	
//	private void Conversor()
//	{
//	if(turno)
//	{
//	bseg--;
//	if (bseg==-1)
//	{
//	bseg=59;
//	bmin--;
//	}
//	bstr = String.format("%d:%02d", bmin, bseg);
//	//btiempo.setText(bstr);
//	}
//	else
//	{
//	nseg--;
//	if (nseg==-1)
//	{
//	nseg=59;
//	nmin--;
//	}
//	nstr = String.format("%d:%02d", nmin, nseg);
//	//ntiempo.setText(nstr);
//	}
//	}
	
	
//	class Timer implements Runnable
//	{
//	@Override
//	public void run() 
//	{
//	while(true)
//	{
//	try 
//	{
//	Thread.sleep(1000);
//	Conversor();
//	}
//	catch (InterruptedException e) 
//	{
//	return;
//	}
//	}
//	}
//	
//	}
//
	private void Conversor()
	{
	//System.out.println("SDFGHJKL"+ turno);
	
	
	if(turno)
	{
	bseg--;
	if (bseg==-1)
	{
	bseg=59;
	bmin--;
	}
	bstr = String.format("%d:%02d", bmin, bseg);
	
	visual.btiempo.setText(bstr);
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
	
	
	visual.ntiempo.setText(nstr);
	}
	
	
	visual.repaint();
	
	
	
	}
	public void repain()
	{
	visual.repaint();
	}
	public void porque()
	{
		if (ganador.getNickname().compareTo("Mariano")==0)
		{
			try 
			  {
			   File file = new File("src/audio/Presidente_Rajoy-_Viva_el_vino_.wav");  
			   Clip clip = AudioSystem.getClip();
			   clip.open(AudioSystem.getAudioInputStream(file));
			   clip.start();
			   JOptionPane.showMessageDialog(visual, "Ha ganado "+ ganador.getNickname());
			   this.setFec_fin(new Date());
			   ganadorString = ganador.getNickname();
			   
				//Calculando Elo del luser.
				double factor = (3400 - perdedor.getElo())^2/100000;
				double dif = perdedor.getElo() - ganador.getElo();
				double difabs = Math.abs(dif);
				double resultadoesperado = 0.5 + 1.4217 * Math.pow(10, -3) * dif - 2.4336 * Math.pow(10, -7) * dif * difabs -
										  2.5140 * Math.pow(10, -9) * dif * Math.pow(difabs, 2) + 1.9910 * Math.pow(10,-12)* dif * Math.pow(difabs,3);
				double cambio = (0 - resultadoesperado)*factor;
				perdedor.setElo(perdedor.getElo()+(int)cambio);
				clsBD.modificarDatoTablaBD(perdedor);
				
			   clsBD.modificarDatoTablaBD(visual.tab);
			   clsEleccion ventanaEleccion = new clsEleccion(ublanco);
			   ventanaEleccion.setVisible(true);
			   visual.dispose();
			  } 
			catch (Exception e) 
			{}
		}
		else
		{
			try 
			  {
			   File file = new File("src/audio/Rajoy_-_La_segunda_ya_tal.wav");  
			   Clip clip = AudioSystem.getClip();
			   clip.open(AudioSystem.getAudioInputStream(file));
			   clip.start();
			   JOptionPane.showMessageDialog(visual, "Ha ganado "+ ganador.getNickname());
			   this.setFec_fin(new Date());
			   ganadorString = ganador.getNickname();

				//Calculando Elo del ganador.
				double factor = (3400 - ganador.getElo())^2/100000;
				double dif = ganador.getElo() - perdedor.getElo();
				double difabs = Math.abs(dif);
				double resultadoesperado = 0.5 + 1.4217 * Math.pow(10, -3) * dif - 2.4336 * Math.pow(10, -7) * dif * difabs -
										  2.5140 * Math.pow(10, -9) * dif * Math.pow(difabs, 2) + 1.9910 * Math.pow(10,-12)* dif * Math.pow(difabs,3);
				double cambio = (1 - resultadoesperado)*factor;
				ganador.setElo(ganador.getElo()+(int)cambio);
				clsBD.modificarDatoTablaBD(ganador);

			   clsBD.modificarDatoTablaBD(visual.tab);
			   clsEleccion ventanaEleccion = new clsEleccion(ublanco);
			   ventanaEleccion.setVisible(true);
			   visual.dispose();
			  } 
			catch (Exception e) 
			{}
		}
	}
	class Timer1 implements Runnable
	{
	@Override
	public void run() 
	{
	
	while(jaquemate==false)
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
	System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
	repain();
	//porque();
//	repain();
//	porque();
	}
	
	}
	public clsCasilla[][] getTablero() {
	return tablero;
	}

	public void setTablero(clsCasilla[][] tablero) {
	this.tablero = tablero;
	}

	public LinkedList<clsPieza> getPblancas() {
	return pblancas;
	}

	public void setPblancas(LinkedList<clsPieza> pblancas) {
	this.pblancas = pblancas;
	}

	public LinkedList<clsPieza> getPnegras() {
	return pnegras;
	}

	public void setPnegras(LinkedList<clsPieza> pnegras) {
	this.pnegras = pnegras;
	}

	public LinkedList<clsCasilla> getMovact() {
	return movact;
	}

	public void setMovact(LinkedList<clsCasilla> movact) {
	this.movact = movact;
	}

	public clsCasilla getAcasilla() {
	return acasilla;
	}

	public void setAcasilla(clsCasilla acasilla) {
	this.acasilla = acasilla;
	}

	public clsCasilla getNcasilla() {
	return ncasilla;
	}

	public void setNcasilla(clsCasilla ncasilla) {
	this.ncasilla = ncasilla;
	}

	public clsRey getReyb() {
	return reyb;
	}

	public void setReyb(clsRey reyb) {
	this.reyb = reyb;
	}

	public clsRey getReyn() {
	return reyn;
	}

	public void setReyn(clsRey reyn) {
	this.reyn = reyn;
	}

	public clsTorre getBtorrei() {
	return btorrei;
	}

	public void setBtorrei(clsTorre btorrei) {
	this.btorrei = btorrei;
	}

	public clsTorre getBtorred() {
	return btorred;
	}

	public void setBtorred(clsTorre btorred) {
	this.btorred = btorred;
	}

	public clsTorre getNtorrei() {
	return ntorrei;
	}

	public void setNtorrei(clsTorre ntorrei) {
	this.ntorrei = ntorrei;
	}

	public clsTorre getNtorred() {
	return ntorred;
	}

	public void setNtorred(clsTorre ntorred) {
	this.ntorred = ntorred;
	}

	public Boolean getTurno() {
	return turno;
	}

	public void setTurno(Boolean turno) {
	this.turno = turno;
	}

//	public Runnable getMyTimer() {
//	return myTimer;
//	}
//
//	public void setMyTimer(Runnable myTimer) {
//	this.myTimer = myTimer;
//	}

	public int getNmin() {
	return nmin;
	}

	public void setNmin(int nmin) {
	this.nmin = nmin;
	}

	public int getNseg() {
	return nseg;
	}

	public void setNseg(int nseg) {
	this.nseg = nseg;
	}

	public String getNstr() {
	return nstr;
	}

	public void setNstr(String nstr) {
	this.nstr = nstr;
	}

	public int getBmin() {
	return bmin;
	}

	public void setBmin(int bmin) {
	this.bmin = bmin;
	}

	public int getBseg() {
	return bseg;
	}

	public void setBseg(int bseg) {
	this.bseg = bseg;
	}

	public String getBstr() {
	return bstr;
	}

	public void setBstr(String bstr) {
	this.bstr = bstr;
	}

	public clsPieza getSelec() {
	return selec;
	}

	public void setSelec(clsPieza selec) {
	this.selec = selec;
	}

	public clsUsuario getUnigga() {
	return unigga;
	}

	public void setUnigga(clsUsuario unigga) {
	this.unigga = unigga;
	}

	public clsUsuario getUblanco() {
	return ublanco;
	}

	public void setUblanco(clsUsuario ublanco) {
	this.ublanco = ublanco;
	}
	public int getID_partida() 
	{
		return ID_partida;
	}
	public void setID_partida(int iD_partida) 
	{
		ID_partida = iD_partida;
	}
	public Date getFec_com() 
	{
		return fec_com;
	}
	public void setFec_com(Date fec_com) {
		
		this.fec_com = fec_com;
	}
	public Date getFec_fin() 
	{
		return fec_fin;
	}
	public void setFec_fin(Date fec_fin) 
	{
		this.fec_fin = fec_fin;
	}
	
	
	public String getGanadorString() {
		return ganadorString;
	}
	public void setGanadorString(String ganador) {
		this.ganadorString = ganador;
	}
	@Override
	public int compareTo(TableroLogicoMariano arg0) 
	{
		return this.getID_partida()-arg0.getID_partida();
	}
}