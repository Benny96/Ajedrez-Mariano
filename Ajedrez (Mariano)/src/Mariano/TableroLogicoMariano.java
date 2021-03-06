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

/**
 * Clase que soportara la parte logica del tablero en una partida jugador vs Mariano (1vMariano).
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Benat Galdos (Benny96)
 */

public class TableroLogicoMariano implements Cloneable, Serializable, Comparable <TableroLogicoMariano>
{
	private static final long serialVersionUID = 1L;

	clsCasilla [][] tablero;

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
	
	int nmin;
	int nseg;
	private String nstr;

	private int bmin;
	private int bseg;
	private String bstr;
	
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
	
	/**
	 * Constructor sin parametros para reutilizar durante el curso de la partida.
	 */
	public TableroLogicoMariano()
	{
		tablero= new clsCasilla[8][8];
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				tablero[i][j]=new clsCasilla(i, j);	
			}
		}
		reyb=new clsRey(0,3,true);
		reyn=new clsRey(7,3,false);
	
		pblancas= new LinkedList<clsPieza>();
		pnegras= new LinkedList<clsPieza>();
		movact= new LinkedList<clsCasilla>();
	
		selec=null;
	}
	/**
	 * Constructor para gestionar los datos relevantes de una partida 1v1 de la Base de Datos.
	 * @param a ID de la partida
	 * @param b Nickname del jugador blanco
	 * @param c Nickname del jugador negro
	 * @param d Fecha de comienzo de la partida
	 * @param e Fecha de final de la partida
	 * @param f Nickname del ganador de la partida
	 */
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
	/**
	 * Constructor para generar un tablero logico 1vMariano al iniciar una nueva partida.
	 * @param asd Flag
	 * @param tablerovisual Tablero visual con el que se corresponde el tablero logico
	 * @param myTimer Runnable
	 * @param usu Usuario a insertar
	 */
	public TableroLogicoMariano(Boolean asd, TableroVisualMariano tablerovisual, Runnable myTimer, clsUsuario usu) 
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
	
		visual=tablerovisual;
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
		pnegras.add(reinan);
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
	
	/**
	 * Metodo utilizado para clonar el tablero logico a medida que se va jugando.
	 * @param tab Tablero logico a clonar
	 * @return Tablero logico clonado
	 */
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
			else
			{
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
	
	/**
	 * Metodo para limpiar el tablero, para que despues se actue sobre el.
	 * @param borrar Lista de clsCasillas a borrar
	 */
	public void clear(LinkedList <clsCasilla> borrar)
	{
		for(clsCasilla aux: borrar)
		{
			if(aux.provisional)
			{
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

	/**
	 * Metodo para comprobar un jaquemate hecho por piezas negras.
	 * @param tab Tablero logico
	 * @return Flag que indica si hay jaque o no
	 */
	public boolean jaquematen(TableroLogicoMariano tab)
	{
		for(clsPieza paux: tab.pnegras)
		{
			if(legales(paux,tab).size()!=0)
			{
				return false;
			}
		}
		return true;
	}
	/**
	 * Metodo para comprobar un jaquemate hecho por piezas blancas.
	 * @param tab Tablero logico
	 * @return Flag que indica si hay jaque o no
	 */
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
	
	/**
	 * Metodo para comprobar si un rey se encuentra en posicion de jaque o no.
	 * @param rey Rey afectado
	 * @param tab Tablero logico
	 * @return Flag indicando que esta en jaque
	 */
	public Boolean comprobarjaque(clsRey rey,TableroLogicoMariano tab)
	{
		LinkedList<clsPieza> colorcete;
		clsCasilla[][] tablero=tab.getTablero();
		
		if(rey.getColor())
			colorcete=tab.getPnegras();
		else
			colorcete=tab.getPblancas();
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
	
	/**
	 * Metodo que escribe las jugadas hechas por Mariano en la JTable.
	 * @param definitiva Jugada definitiva
	 */
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
		if(definitiva.pieza.a.equals(Comun.clsConstantes.piezas.Peon))
		{
			if(definitiva.pieza.getY()==1 && definitiva.pieza.getColor()==false)
			{
				pnegras.remove(definitiva.pieza);
				clsReina auxr=new clsReina(definitiva.pieza.getX(),definitiva.pieza.getY(),false);
				pnegras.add(auxr);
				definitiva.pieza=auxr;
			}
		}
		tablero[definitiva.cfinal.gety()][definitiva.cfinal.getx()].setOcupado(definitiva.pieza);
		
		String letra = null;
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
	}

	public clsJugada Inteligencia()
	{
		clsJugada definitiva= new clsJugada();
		TableroLogicoMariano tablero=new TableroLogicoMariano();
		tablero=clonar(this);
		for(clsPieza p: this.pnegras)
		{	
			System.out.println(p.getClass());
			System.out.println(p);
			int iy=p.getY();
			int ix=p.getX();
			LinkedList<clsCasilla> candidatos=legales(p,this);
	
			for(clsCasilla caux: candidatos)
			{
				clsJugada aux= new clsJugada(p,caux);
				aux.valor=Valorar(aux,tablero,1,1);
				if(aux.valor>definitiva.valor)
				{
				definitiva=aux;
				}
			}
		}
	return definitiva;
	}


	/**
	 * Metodo que permite valorar las jugadas hechas
	 * @param jugada Jugada realizada
	 * @param tablero Tablero logico
	 * @param numero Numero por parte de Mariano
	 * @param numeroblanquito Numero del jugador blanco
	 * @return Int con la valoracion
	 */
	public int Valorar(clsJugada jugada,TableroLogicoMariano tablero, int numero,int numeroblanquito)
	{
		boolean comiste=false; 
		clsCasilla [][] tableroaux=tablero.getTablero();
	
		int valor=0;
	
		clsPieza pieza= jugada.pieza;
	
		int x= pieza.getX();
		int y=pieza.getY();
	
		clsCasilla cfinal= jugada.cfinal;
	
		int cx= cfinal.getx();
		int cy=cfinal.gety();

		clsPieza okupada=null;
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
				tablero.getPblancas().remove(pieza);
				tableroaux[cy][cx].setOcupado(okupada);
				tableroaux[y][x].setOcupado(pieza);
				tablero.getPblancas().add(pieza);
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
				
				tablero.getPnegras().remove(pieza);
				tableroaux[cy][cx].setOcupado(okupada);
				tableroaux[y][x].setOcupado(pieza);
				tablero.getPnegras().add(pieza);
				return valor;
			}
		}
		clsJugada peor=new clsJugada();
		if(numero==1)
		{
			TableroLogicoMariano tablero2=new TableroLogicoMariano();
			tablero2=clonar(tablero);
			for(clsPieza blanca: tablero.getPblancas())
			{
				clsPieza clon=blanca.clonar(blanca, tablero);
				LinkedList<clsCasilla> candidatos=rlegales(clon,tablero);

				for(clsCasilla contrin: candidatos)
				{
					clsJugada aux= new clsJugada(blanca,contrin);
					aux.valor=Valorar(aux,tablero2,2,numeroblanquito);
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
	
	/**
	 * Metodo para anyadir los movimientos disponibles a una lista de movimientos.
	 * @param lista Lista de clsCasillas
	 */
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
	/**
	 * Acciones que ocurren al pulsar en una casilla.
	 * @param casilla clsCasilla en la que ocurre la pulsación
	 */
	public void action(clsCasilla casilla) 
	{
		 acasilla=ncasilla;
		 ncasilla=casilla;
		 
		TableroLogicoMariano tab= new TableroLogicoMariano();
		tab = clonar(this);
		if(ncasilla.getOcupado()==null)
		{
			if(ncasilla.mov)
			{
				tablero[selec.getY()][selec.getX()].setOcupado(null);
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
					if(selec.getColor()==false && ncasilla.getx()==1 && ncasilla.gety()==7 && selec.getPrimera()==false)
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
				
				ncasilla.setOcupado(selec);
				movact.remove(ncasilla);
				turno=!turno;
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
			}
			clear(movact);
		}
		else 
		{
			if(ncasilla.provisional==false)
			{
				clear(movact);
				selec=ncasilla.getOcupado();
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
			}
		}
	}
	
	public LinkedList<clsCasilla> rlegales(clsPieza pieza,TableroLogicoMariano tab) 
	{
		LinkedList<clsPieza> op=new LinkedList<clsPieza>();
		for(clsPieza p: tab.pblancas)
		{
			op.add(p);
		}
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
				else
				{
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
				else
				{
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

	/**
	 * Metodo para obtener los movimientos legales disponibles de una pieza.
	 * @param pieza Pieza afectada
	 * @param tab Tablero logico
	 * @return Lista con las clsCasillas disponibles
	 */
	public LinkedList<clsCasilla> legales(clsPieza pieza,TableroLogicoMariano tab) 
	{
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
				else
				{
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
				else
				{
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
	
	/**
	 * Metodo que permite reflejar el paso del tiempo del hilo en los cronometros.
	 */
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
				porque();
			}
			nstr = String.format("%d:%02d", nmin, nseg);
			visual.ntiempo.setText(nstr);
		}
	visual.repaint();
	}
	
	/**
	 * Metodo que se llama al acabar una partida. <br>
	 * Referencia tomada para entender el calculo de la puntuacion Elo: <br>
	 * @see <a href="http://www.todoajedrez.com.ar/ratings.php">http://www.todoajedrez.com.ar/ratings.php </a>
	 */
	public void porque()
	{
		if (ganador.getNickname().compareTo("Mariano")==0)
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
			   File file = new File("src/audio/Presidente_Rajoy-_Viva_el_vino_.wav");  
			   Clip clip = AudioSystem.getClip();
			   clip.open(AudioSystem.getAudioInputStream(file));
			   clip.start();
			   JOptionPane.showMessageDialog(visual, "Ha ganado "+ ganador.getNickname());
			   this.setFec_fin(new Date());
			   ganadorString = ganador.getNickname();

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
	/**
	 * Clase interna que implementa la interfaz Runnable que valdrá para generar el temporizador.
	 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Benat Galdos (Benny96)
	 */
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
		visual.repaint();
		}
	}
	/**
	 * Metodo compareTo() reimplementado para conseguir un orden natural a la hora de mostrar los datos. Atributo: ID_partida.
	 */
	@Override
	public int compareTo(TableroLogicoMariano arg0) 
	{
		return this.getID_partida()-arg0.getID_partida();
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
	
	public String getGanadorString() 
	{
		return ganadorString;
	}
	public void setGanadorString(String ganador) {
		this.ganadorString = ganador;
	}
}