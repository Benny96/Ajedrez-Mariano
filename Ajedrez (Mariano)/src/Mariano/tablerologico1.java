package Mariano;



import java.awt.Color;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.JOptionPane;















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



public class tablerologico1 implements Cloneable{

	clsCasilla [][] tablero;
	
//	JLabel blanquito;
//	JLabel nigga;
//	
//	JLabel ntiempo;
//	
//	JList asd;

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
	
	 tablerovisual1 visual;
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
	
	public tablerologico1()
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
	
	pblancas= new LinkedList<clsPieza>();
	pnegras= new LinkedList<clsPieza>();
	movact= new LinkedList<clsCasilla>();
	
	selec=null;
	
	}
	public tablerologico1(Boolean asd, tablerovisual1 tablerovisual, Runnable myTimer) 
	{
	
	tablero= new clsCasilla[8][8];
	
	visual=tablerovisual;
	
	
	ublanco= new clsUsuario("blanquito","a","a","blanquito","a");
	unigga= new clsUsuario("nigga","b","b","nigga","b");
	
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
	movact= new LinkedList<clsCasilla>();
	
	reyb=new clsRey(0,3,true);
	reyn=new clsRey(7,3,false);
	
	reinab=new clsReina(0,4,true);
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
	public tablerologico1 clonar(tablerologico1 tab)
	{
	tablerologico1 mewto = new tablerologico1();
	
	
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
	
	
	public boolean jaquematen(tablerologico1 tab)
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
	public boolean jaquemateb(tablerologico1 tab)
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
	
	public Boolean comprobarjaque(clsRey rey,tablerologico1 tab)

	
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
	public void Inteligencia()
	{
	clsJugada definitiva= new clsJugada();
	
	for(clsPieza p: this.pnegras)
	{
	
	System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
	System.out.println(p.getClass());
	System.out.println(p);
	int iy=p.getY();
	int ix=p.getX();
	
	
	LinkedList<clsCasilla> candidatos=legales(p,this);
	
	
	for(clsCasilla caux: candidatos)
	{
//	System.out.println("candidatos "+ caux);
	clsJugada aux= new clsJugada(p,caux);
	
	aux.valor=Valorar(aux,this,1);
//	System.out.println(aux.valor);
	if(aux.valor>definitiva.valor)
	{
	definitiva=aux;
	}
	
	}
	
	}
	
	int x=definitiva.pieza.getX();
	int y=definitiva.pieza.getY();
	
	Boolean ocupado=false;
	if(tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].getOcupado()!=null)
	{
	ocupado=true;
	this.pblancas.remove(tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].getOcupado());
	}
	tablero[y][x].setOcupado(null);
	
	
	
//	tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].setPieza(definitiva.pieza);
//	tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].mov=false;
//	System.out.println("sssssssssssssssss " +definitiva.pieza.getClass());
//	tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].setIcon(definitiva.pieza.getIcon());
//	definitiva.pieza.setY(tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].gety());
//	definitiva.pieza.setX(tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].getx());
//	
//	tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].paintComponents(tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].getGraphics());
	
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
//	jaquemate=jaquemateb(this);
//	if(jaquemate)
//	{
//	visual.repaint();
//	System.out.println("SoyDioooooooooooooooooooooooos");
//	}
	
	}
	public int Valorar(clsJugada jugada,tablerologico1 tablero, int numero)

	{
	
	tablerologico1 tab=new tablerologico1();
	tab=clonar(tablero);
	clsCasilla [][] tabaux=tab.getTablero();
	
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
	tab.getPblancas().remove(okupada);
	}
	else
	{
	tab.getPnegras().remove(okupada);
	}
	}

	if(tabaux[cy][cx].getOcupado()!=null && tabaux[cy][cx].getOcupado().getColor().equals(pieza.getColor())==false)
	{
	 valor= tabaux[cy][cx].getOcupado().getValor();
	}
	if(pieza.primera==false)
	{
	valor=valor+30;
	}
	if(pieza.getColor())
	tab.getPblancas().remove(pieza);
	else
	tab.getPnegras().remove(pieza);
	tabaux[cy][cx].setOcupado(pieza);
	tabaux[y][x].setOcupado(null);
	
	//tab.setTurno(!tab.getTurno());

//
	if(pieza.getColor())
	{
	tab.getPblancas().add(pieza);
	
	for(clsPieza dfg: tab.getPblancas())
	{
	valor=valor+legales(dfg,tab).size();
	}
	}
	else
	{
	tab.getPnegras().add(pieza);
	for(clsPieza dfg: tab.getPnegras())
	{
	valor=valor+legales(dfg,tab).size();
	}
	}
	
	if(pieza.getColor())
	{
	if(jaquematen(tab))
	{
	valor=valor+90000;
	if(okupada!=null)
	{
	if(okupada.getColor())
	tab.getPblancas().add(okupada);
	else
	tab.getPnegras().add(okupada);
	}
	
	tabaux[cy][cx].setOcupado(okupada);
	tabaux[y][x].setOcupado(pieza);
	
	if(pieza.getColor())
	tab.getPblancas().add(pieza);
	else
	tab.getPnegras().add(pieza);
	System.out.println("jaqueeeeeeeeeeeeeemateeeeeeeeeeeeeeee");
	return valor;
	}
	}
	else
	{
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
	if(jaquemateb(tab))
	{
	valor=valor+100000;
	if(okupada!=null)
	{
	if(okupada.getColor())
	tab.getPblancas().add(okupada);
	else
	tab.getPnegras().add(okupada);
	}
	
	tabaux[cy][cx].setOcupado(okupada);
	tabaux[y][x].setOcupado(pieza);
	
	if(pieza.getColor())
	tab.getPblancas().add(pieza);
	else
	tab.getPnegras().add(pieza);
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
//	System.out.println("putos blanquitoooooooooooooooooooooooooooooooooooooos");
	for(clsPieza blanca: tab.getPblancas())
	{
	clsPieza clon=blanca.clonar(blanca, tab);
//	System.out.println(blanca.getClass());
//	System.out.println("original "+blanca);
//	System.out.println("clon "+clon);
	LinkedList<clsCasilla> candidatos=rlegales(clon,tab);
	
	
	
	for(clsCasilla contrin: candidatos)
	{
//	System.out.println(contrin);
	clsJugada aux= new clsJugada(blanca,contrin);
	aux.valor=Valorar(aux,tab,2);
	if(aux.valor>peor.valor)
	peor=aux;

	}
	
	
	}
	valor=valor-peor.valor;
	}
	
//	if(numero==2)
//	{
////	System.out.println("putos putosnigggggaaaaaaaaaaaaaaaaaaas");
//	for(clsPieza blanca: tab.getPnegras())
//	{
//	clsPieza clon=blanca.clonar(blanca, tab);
////	System.out.println(blanca.getClass());
////	System.out.println("original "+blanca);
////	System.out.println("clon "+clon);
//	LinkedList<clsCasilla> candidatos=rlegales(clon,tab);
//	
//	
//	
//	for(clsCasilla contrin: candidatos)
//	{
////	System.out.println(contrin);
//	clsJugada aux= new clsJugada(blanca,contrin);
//	aux.valor=Valorar(aux,tab,3);
//	if(aux.valor>peor.valor)
//	peor=aux;
//
//	}
//	
//	
//	}
//	
//	}

//	System.out.println("erewerwe r "+peor.valor);
	
	
//	}
	
	
	if(okupada!=null)
	{
	if(okupada.getColor())
	tab.getPblancas().add(okupada);
	else
	tab.getPnegras().add(okupada);
	}
	
	tabaux[cy][cx].setOcupado(okupada);
	tabaux[y][x].setOcupado(pieza);
	
	if(pieza.getColor())
	tab.getPblancas().add(pieza);
	else
	tab.getPnegras().add(pieza);
	pieza.setX(x);
	pieza.setY(y);
	
	
	
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
	
	public Boolean Jugadajaque(clsPieza movida,clsPieza sitio, clsCasilla Original, clsCasilla Final,tablerologico1 tab)
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
	 
	 tablerologico1 tab= new tablerologico1();
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
	tablero[selec.getY()][selec.getX()].paintImmediately(tablero[selec.getY()][selec.getX()].getBounds());
	
	if(selec.a.equals(Comun.clsConstantes.piezas.Rey))
	{
	if(selec.getPrimera()==false)
	selec.setPrimera(true);
	
//	if(selec.getColor() && ncasilla.getx()==0 && ncasilla.gety()==1 && reyb.getPrimera()==true && btorred.getPrimera()==true && tablero[0][2].getOcupado()==null )
//	{
//	tablero[0][2].setOcupado(btorred);
//	tablero[0][0].setOcupado(null);
//	}
	
	//el enroque funciona mal si la casilla continua está en jaque
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
	
	if(  selec.getColor()==false && ncasilla.getx()==1 && ncasilla.gety()==7  )
	{
	tablero[7][2].setOcupado(ntorred);
	tablero[7][0].setOcupado(null);
	}
	if( selec.getColor()==false && ncasilla.getx()==5 && ncasilla.gety()==7 )
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
	
	
	Inteligencia();
	
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
	
	Inteligencia();

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
	
	
	public LinkedList<clsCasilla> rlegales(clsPieza pieza,tablerologico1 tab) {
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

	public LinkedList<clsCasilla> legales(clsPieza pieza,tablerologico1 tab) {
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
		JOptionPane.showMessageDialog(visual, "Ha ganado "+ ganador.getNickname());
		//this.setFec_fin(new Date());
		//TODO: Guardado del resultado final en BD. ¿Cómo se calcula el Elo? D-:
		clsBD.modificarDatoTablaBD(this);
		clsEleccion ventanaEleccion = new clsEleccion(ublanco);
		ventanaEleccion.setVisible(true);
		visual.dispose();
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
	porque();
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
}