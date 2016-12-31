package Unopauno;


import java.awt.Color;
import java.io.File;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import Comun.clsConstantes;
import Comun.clsConstantes.piezas;
import GUI.clsEleccion;
import LN.clsAlfil;
import LN.clsCaballo;
import LN.clsCasilla;
import LN.clsGestor;
import LN.clsJugada;
import LN.clsPeon;
import LN.clsPieza;
import LN.clsReina;
import LN.clsRey;
import LN.clsTorre;
import LN.clsUsuario;
import Persistencia.clsBD;

public class TableroLogico1v1 implements Cloneable, Serializable, Comparable <TableroLogico1v1>{

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
	
	TableroVisual1v1 visual;
	
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
	
	clsUsuario ganador;
	clsUsuario perdedor;
	
	boolean jaquemate;

	private clsReina reinab;
	
	transient Runnable reloj;

	private clsReina reinan;
	
	private Object [][] datosTabla;
	
	int num=0;
	
	public TableroLogico1v1()
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
	public TableroLogico1v1 (int a, String b, String c, long d, long e, String f)
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
	public TableroLogico1v1(Boolean cargado, TableroVisual1v1 TableroVisual1v1) 
	{
			if (cargado)
			{
				//No vale ni pa' cagar.
			}
			else
			{
			ganadorString = "";
			clsBD.crearTablaBD(clsConstantes.PARTIDA);
			ResultSet rs = clsBD.obtenerDatosTablaBD (clsConstantes.PARTIDA);
			try 
			{
				while (rs.next())
				{
					ID_partida++;
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			fec_com = new Date();
			fec_fin = new Date(new Long(0) );
			tablero= new clsCasilla[8][8];
			
			System.out.println("pasoo");
			
			visual=TableroVisual1v1;
			
			
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
			aux.setMovimientos(legales(aux,this));
			}
			for(clsPieza aux: pnegras)
			{
				aux.setMovimientos(legales(aux,this));
			}
			
			nmin=10;
			nseg = 00;
			nstr = String.format("%d:%02d", nmin, nseg);
			
			bmin=10;
			bseg = 00;
			bstr = String.format("%d:%02d", bmin, bseg);
			
			IniciarReloj();			
			}
	}	
	//TODO: Nuevo constructor con 123159279283740370 par�metros.
	public TableroLogico1v1 (clsTorre btorrede, clsTorre btorreiz, int bminu, int bsegu, LinkedList <clsCasilla> movacti, int nminu,
			int nsegu, clsTorre ntorrede, clsTorre ntorreiz, LinkedList <clsPieza> piblancas, LinkedList <clsPieza> pinegras,
			clsRey reybl, clsRey reyne, Object [][] datos, int numfila, clsUsuario ublanquito, clsUsuario unegrito, boolean turnoactual)
	{
		btorred = btorrede;
		btorrei = btorreiz;
		bmin = bminu;
		bseg = bsegu;
		bstr = String.format("%d:%02d", bmin, bseg);
		
		movact = new LinkedList <clsCasilla>();
		for (clsCasilla aux: movacti)
		{
			movact.add(aux);
		}
		//acasilla = ????
//		ncasilla = TableroVisual1v1.ncasilla;
		
		nmin = nminu;
		nseg = nsegu;
		nstr = String.format("%d:%02d", nmin, nseg);

		ntorred = ntorrede;
		ntorrei = ntorreiz;
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
		reyb = reybl;
		reyn = reyne;
		selec = null;
		setDatosTabla(datos);
		tablero = new clsCasilla[8][8];
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				tablero[i][j]=new clsCasilla(i, j);	
			}
		}
		for(clsPieza aux: piblancas)
		{
			tablero[aux.getY()][aux.getX()].setOcupado(aux);
		}
		for(clsPieza aux: pinegras)
		{
			tablero[aux.getY()][aux.getX()].setOcupado(aux);
		}
		
		for(clsPieza aux: piblancas)
		{
			aux.setMovimientos(legales(aux,this));
		}
		for(clsPieza aux: pinegras)
		{
			aux.setMovimientos(legales(aux,this));
		}
		ublanco = ublanquito;
		unigga = unegrito;
		turno = turnoactual;
		num = numfila;
		IniciarReloj();
	}
	public void IniciarReloj()
	{
		reloj = new Timer1();
		Thread a= new Thread (reloj);
		a.start();
	}

//	public static void pintar()
//	{
//	visual.repaint();
//	}
	public TableroLogico1v1 clonar(TableroLogico1v1 tab)
	{
	TableroLogico1v1 mewto = new TableroLogico1v1();
	
	
	for(clsPieza paux: tab.getPblancas())
	{
	if(paux instanceof clsRey)
	{
	mewto.setReyb((clsRey) paux.clonarTab1v1(tab.getReyb(),tab));
	mewto.getPblancas().add(mewto.getReyb());
	}
	else
	{
	mewto.getPblancas().add(paux.clonarTab1v1(paux,tab));
	}
	}
	for(clsPieza paux: tab.getPnegras())
	{
	if(paux instanceof clsRey)
	{
	mewto.setReyn((clsRey) paux.clonarTab1v1(tab.getReyn(),tab));
	mewto.getPnegras().add(mewto.getReyn());
	}
	else{
	mewto.getPnegras().add(paux.clonarTab1v1(paux,tab));
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
	
	public void revisar(clsJugada jugada,TableroLogico1v1 tab)
	{
	LinkedList<clsPieza> todas = new LinkedList<clsPieza>();
	
	todas.addAll(tab.pblancas);
	todas.addAll(tab.pnegras);
	
	if(comprobarjaque(tab.reyb,tab))
	{
		for(clsPieza pieza: tab.pblancas)
		{
			pieza.setMovimientos(legales(pieza,tab));
		}
	}
	else if(comprobarjaque(tab.reyn,tab))
	{
		for(clsPieza pieza: tab.pnegras)
		{
			pieza.setMovimientos(legales(pieza,tab));
		}
	}
	else
	{
		System.out.println("pasoooooooo");
	for(clsPieza pieza: todas)
	{
		clsPieza hola=pieza.clonarTab1v1(pieza, tab);
	for(clsCasilla casilla: hola.getMovimientos())
	{
		if(casilla.equals(jugada.cfinal)|| (casilla.gety()==jugada.pieza.getY() && casilla.getx()==jugada.pieza.getX()))
		{
			pieza.setMovimientos(legales(pieza,tab));
		}
			
	}
	}
	}
	}
	
	
	public boolean jaquematen(TableroLogico1v1 tab)
	{
	//tablerologico tablerete=clonar(this);
	
	for(clsPieza paux: tab.pnegras)
	{
	if(legales(paux,this).size()!=0)
	{
	return false;
	}
	}
	
	return true;
	}
	public boolean jaquemateb(TableroLogico1v1 tab)
	{
	
	for(clsPieza paux: tab.pblancas)
	{
	
	if(legales(paux,this).size()!=0)
	{
	return false;
	}
	}
	
	return true;
	}
	
	public Boolean comprobarjaque(clsRey rey,TableroLogico1v1 tab)

	
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
//	System.out.println("FGHJKLOIUYTREWAZXCVBNM;");
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
	
	
	//LinkedList<clsCasilla> candidatos=legales(p,this);
	
	clsPieza xc= p.clonarTab1v1(p, this);
	for(clsCasilla caux: xc.getMovimientos())
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
	
	if(tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].getOcupado()!=null)
	{
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
	if(definitiva.pieza.primera==false)
	definitiva.pieza.primera=true;
	
	revisar(definitiva,this);
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
	public int Valorar(clsJugada jugada,TableroLogico1v1 tablero, int numero)

	{
	
	TableroLogico1v1 tab=new TableroLogico1v1();
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
	revisar(jugada,tab);
	for(clsPieza dfg: tab.getPblancas())
	{
	valor=valor+dfg.getMovimientos().size();
	}
	}
	else
	{
	tab.getPnegras().add(pieza);
	revisar(jugada,tab);
	for(clsPieza dfg: tab.getPnegras())
	{
	valor=valor+dfg.getMovimientos().size();
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
	
	//revisar(jugada,tab);
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
//	 System.out.println("Negra�aaaaaaaaaaaaas");
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
	
	//revisar(jugada,tab);
	
	System.out.println("jaqueeeeeeeeeeeeeemateeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
	return valor;
	}
	}
	
//	if(numero==1||numero==2)
//	{
	//se podr�a usar el m�todo inteligencia
//	System.out.println(valor);
	clsJugada peor=new clsJugada();
	
	if(numero==1)
	{
//	System.out.println("putos blanquitoooooooooooooooooooooooooooooooooooooos");
	for(clsPieza blanca: tab.getPblancas())
	{
	clsPieza clon=blanca.clonarTab1v1(blanca, tab);
//	System.out.println(blanca.getClass());
//	System.out.println("original "+blanca);
//	System.out.println("clon "+clon);
	//LinkedList<clsCasilla> candidatos=rlegales(clon,tab);
	
	
	
	for(clsCasilla contrin: clon.getMovimientos())
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
	
	public Boolean Jugadajaque(clsPieza movida,clsPieza sitio, clsCasilla Original, clsCasilla Final,TableroLogico1v1 tab)
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
	tab.setReyb((clsRey) p.clonarTab1v1(p,this));
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
	tab.setReyn((clsRey) p.clonarTab1v1(p,this));
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
	 
//	 tablerologico tab= new tablerologico();
//	 tab= clonar(this);
	 
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
//	 System.out.println("Negra�aaaaaaaaaaaaas");
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
	
	
	String foto="";
	if(selec.getA()==piezas.Peon&&selec.getColor()==true)foto="/img/peon_blanco.png";
	if(selec.getA()==piezas.Torre&&selec.getColor()==true)foto="/img/torre_b.png";
	if(selec.getA()==piezas.Caballo&&selec.getColor()==true)foto="/img/caballo_b.png";
	if(selec.getA()==piezas.Alfil&&selec.getColor()==true)foto="/img/Alfil_b.png";
	if(selec.getA()==piezas.Rey&&selec.getColor()==true)foto="/img/rey_b.png";
	if(selec.getA()==piezas.Reina&&selec.getColor()==true)foto="/img/reina_b.png";
	
	if(selec.getA()==piezas.Peon&&selec.getColor()==false)foto="/img/peon_negro.png";
	if(selec.getA()==piezas.Torre&&selec.getColor()==false)foto="/img/torre_n.png";
	if(selec.getA()==piezas.Caballo&&selec.getColor()==false)foto="/img/caballo_n.png";
	if(selec.getA()==piezas.Alfil&&selec.getColor()==false)foto="/img/Alfil_n.png";
	if(selec.getA()==piezas.Rey&&selec.getColor()==false)foto="/img/rey_n.png";
	if(selec.getA()==piezas.Reina&&selec.getColor()==false)foto="/img/reina_n.png";
	
	visual.EmpezarMovimiento(acasilla.getX(), acasilla.getY(), ncasilla.getX(), ncasilla.getY(), foto,ncasilla);
	
	
	
	
	if(selec.a.equals(Comun.clsConstantes.piezas.Rey))
	{
	if(selec.getPrimera()==false)
	selec.setPrimera(true);
	
//	if(selec.getColor() && ncasilla.getx()==0 && ncasilla.gety()==1 && reyb.getPrimera()==true && btorred.getPrimera()==true && tablero[0][2].getOcupado()==null )
//	{
//	tablero[0][2].setOcupado(btorred);
//	tablero[0][0].setOcupado(null);
//	}
	
	//el enroque funciona mal si la casilla continua est� en jaque
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
	
	
	ncasilla.setOcupado(selec);
	ncasilla.setIcon(null);
	movact.remove(ncasilla);
	
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
	else
	{
		visual.tabla.data[num][1]=letra+selec.escritura();
		num++;
	}
	turno=!turno;

	ncasilla.paint(ncasilla.getGraphics());
	clear(movact);
	//revisar(new clsJugada(selec,acasilla),this);
	
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
	else
	{
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
	}

	selec=null;

	
	
	

	
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
	
	if (selec.getColor()== turno)
	{
	
	//LinkedList<clsCasilla> aux= legales(selec,this);

	dibujarmov(legales(selec,this));
	}

	}
	
	
	
	else
	{

	if(selec.getColor())
	pnegras.remove(ncasilla.getOcupado());
	else
	pblancas.remove(ncasilla.getOcupado());
	
	
	if(selec.a.equals(Comun.clsConstantes.piezas.Peon))
	{
		System.out.println("Punto1");
		System.out.println(selec.getY());
		System.out.println(selec.getX());
		//TODO: CAMBIO INTRODUCIDO AQU� (DE 6 A 7) para que no cree una dama de la nada por el movimiento visto en el v�deo. Sin embargo, no acaba de crear
		//la dama en ning�n momento...
		if(selec.getY()==6 && selec.getColor())
		{
			
			pblancas.remove(selec);
			
			clsReina auxr=new clsReina(selec.getY(),selec.getX(),true);
			pblancas.add(auxr);
			selec=auxr;
			
		}
		if(selec.getY()==1 && selec.getColor()==false)
		{
			pnegras.remove(selec);
			clsReina auxr=new clsReina(selec.getY(),selec.getX(),false);
			pnegras.add(auxr);
			selec=auxr;
		}
		
	}
	
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
//	
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
	else
	{
		visual.tabla.data[num][1]=letra+"x"+selec.escritura();
		num++;
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
	clear(movact);
	
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
	else
	{
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
	}
	
	selec=null;
	
	
	}
	}
//	}
	
//	System.out.println("el rey nigro");
//	System.out.println(reyn.jaque);
//	System.out.println("el rey blanco");
//	System.out.println(reyb.jaque);
	}
	
	
	public LinkedList<clsCasilla> rlegales(clsPieza pieza,TableroLogico1v1 tab) {
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

	public LinkedList<clsCasilla> legales(clsPieza pieza,TableroLogico1v1 tab) {
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
	
	if(bseg==0 && bmin==0)
	{
		ganador=unigga;
		perdedor=ublanco;
		porque();
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
	
	if(nseg==0 && nmin==0)
	{	
		ganador=ublanco;
		perdedor=unigga;
		
		//Display final.
		porque();
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
	//TODO: Calcular elo y pasarlo a base de datos. SE HAR� ANTES PORQUE SE DEBEN ACTUALIZAR LOS ELOS DE LOS 2 JUGADORES.
		try 
		  {
		   File file = new File("src/audio/Presidente_Rajoy-_Viva_el_vino_.wav");  
		   Clip clip = AudioSystem.getClip();
		   clip.open(AudioSystem.getAudioInputStream(file));
		   clip.start();
		   JOptionPane.showMessageDialog(visual, "Ha ganado "+ ganador.getNickname());
		   this.setFec_fin(new Date());
		   ganadorString = ganador.getNickname();
		   
			/* F�rmula general para el c�lculo del Elo: (Fuente: http://www.todoajedrez.com.ar/ratings.php)

		    * 1) factor = [3400 - ELO propio previo]^2 / 100000
		    Llamamos al factor F.
		    
			* 2) diferencia = ELO propio previo - ELO previo del Oponente.
			De ahora en adelante llamamos a la diferencia D.
			|D| = Valor absoluto de D.
			
			* 3) resultado esperado acorde a la tabla o f�rmula
			F�rmula del resultado esperado (R[E]): 0,5 + 1,4217 x 10^-3  x D - 2,4336 x 10^-7  x D x |D| -
			 									   2,5140 x 10^-9  x D x |D|^2 + 1,9910 x 10^-12 x D x |D|^3
			 									   
			* 4) cambio =(resultado - result. esperado) x factor
			Resultado = (1 - Victoria, 0.5 - Tablas, 0 - Derrota)
			
		*/
		   
			//Calculando Elo del ganador.
			double factor = (3400 - ganador.getElo())^2/100000;
			double dif = ganador.getElo() - perdedor.getElo();
			double difabs = Math.abs(dif);
			double resultadoesperado = 0.5 + 1.4217 * Math.pow(10, -3) * dif - 2.4336 * Math.pow(10, -7) * dif * difabs -
									  2.5140 * Math.pow(10, -9) * dif * Math.pow(difabs, 2) + 1.9910 * Math.pow(10,-12)* dif * Math.pow(difabs,3);
			double cambio = (1 - resultadoesperado)*factor;
			ganador.setElo(ganador.getElo()+(int)cambio);
			clsBD.modificarDatoTablaBD(ganador);
			
			
			//Calculando Elo del luser.
			factor = (3400 - perdedor.getElo())^2/100000;
			dif = perdedor.getElo() - ganador.getElo();
			difabs = Math.abs(dif);
			resultadoesperado = 0.5 + 1.4217 * Math.pow(10, -3) * dif - 2.4336 * Math.pow(10, -7) * dif * difabs -
									  2.5140 * Math.pow(10, -9) * dif * Math.pow(difabs, 2) + 1.9910 * Math.pow(10,-12)* dif * Math.pow(difabs,3);
			cambio = (0 - resultadoesperado)*factor;
			perdedor.setElo(perdedor.getElo()+(int)cambio);
			clsBD.modificarDatoTablaBD(perdedor);
			
           clsBD.modificarDatoTablaBD(visual.tab);
           clsGestor objGestor = new clsGestor();
           objGestor.BorrarPartida();
		   clsEleccion ventanaEleccion = new clsEleccion(ublanco);
		   ventanaEleccion.setVisible(true);
		   visual.dispose();
		  } 
		catch (Exception e) 
		{}
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
	
	public Object[][] getDatosTabla() {
		return datosTabla;
	}
	public void setDatosTabla(Object[][] datosTabla) {
		this.datosTabla = datosTabla;
	}
	@Override
	public int compareTo(TableroLogico1v1 arg0) 
	{
		return this.getID_partida()-arg0.getID_partida();
	}
}