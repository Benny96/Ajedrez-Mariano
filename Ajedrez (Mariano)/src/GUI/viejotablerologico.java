package GUI;


import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import LN.clsUsuario;

public class viejotablerologico implements Cloneable{

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
	
	public viejotablerologico()
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
	public viejotablerologico(Boolean asd) 
	{
			
		tablero= new clsCasilla[8][8];
		
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
		
		bmin=10;
		bseg = 00;
		bstr = String.format("%d:%02d", bmin, bseg);

		
		//para que no meleste
//		myTimer = new Timer();
//		
//		Thread a= new Thread (myTimer);
//		a.start();
		
		}	
	

	public viejotablerologico clonar(viejotablerologico tab)
	{
		viejotablerologico mewto = new viejotablerologico();
		
		
//		for(clsPieza paux: tab.getPblancas())
//		{
//			mewto.getPblancas().add(paux.clonar(paux));
//		}
//		for(clsPieza paux: tab.getPnegras())
//		{
//			mewto.getPnegras().add(paux.clonar(paux));
//		}
//		
		for(clsPieza aux: mewto.getPblancas())
		{
			mewto.getTablero()[aux.getY()][aux.getX()].setOcupado(aux);
		}
		for(clsPieza aux:mewto.getPnegras())
		{
			mewto.getTablero()[aux.getY()][aux.getX()].setOcupado(aux);
		}
		
		//LinkedList<clsCasilla> movact;
		
//		clsCasilla acasilla;
//		clsCasilla ncasilla;
		
				
//		mewto.setReyb((clsRey) tab.getReyb().clonar(tab.getReyb()));
//				
//		mewto.setReyn((clsRey) tab.getReyn().clonar(tab.getReyn()));
//		
//		mewto.setBtorred((clsTorre) tab.getBtorred().clonar(tab.getBtorred()));
//		mewto.setBtorrei((clsTorre) tab.getBtorrei().clonar(tab.getBtorrei()));
//		mewto.setBtorred((clsTorre) tab.getNtorred().clonar(tab.getNtorred()));
//		mewto.setNtorrei((clsTorre) tab.getNtorrei().clonar(tab.getNtorrei()));
//
//		
//		mewto.setSelec(tab.getSelec().clonar(tab.getSelec()));
		

		
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
	
//	
//	public boolean jaquemate(Boolean turno)
//	{
//		tablerologico tablerete=clonar(this);
//		tablerete.setTurno(false);
//		for(clsPieza paux: this.pnegras)
//			{
//				paux.sitio(tablerete.getTablero()).doClick();
//				if(tablerete.movact.size()!=0)
//					return false;
//			}
//			
//			return true;
//		}
//		
	public boolean jaquematen()
	{
		//tablerologico tablerete=clonar(this);
		
		for(clsPieza paux: this.pnegras)
			{
		
				paux.sitio(tablero).doClick();
				if(movact.size()!=0)
					return false;
			}
			
			return true;
		}
	public boolean jaquemateb()
	{
		//tablerologico tablerete=clonar(this);
		
		for(clsPieza paux: this.pblancas)
			{
			
				paux.sitio(tablero).doClick();
				if(movact.size()!=0)
					return false;
			}
			
			return true;
		}
//	public boolean jaquemate(Boolean turno)
//	{
//		for(clsPieza paux: this.pnegras)
//		{
//			clsPieza temp= paux.clonar(paux);
//			paux.mov(this.tablero);
//			
//			clsPieza okupada=null;
//			
//			for(clsCasilla caux: paux.movimientos)
//			{
//				if(caux.getOcupado()!=null)
//				{
//					okupada=caux.getOcupado();
//				}
//				caux.setOcupado(paux);//Igual mejor de tablero
//				temp.sitio(tablero).setOcupado(null);
//				
//				
//				for(clsPieza p1: this.pblancas)
//				{
//					Boolean ayuda=false;
//					p1.mov(tablero);
//					for(clsCasilla c1: p1.movimientos)
//					{
//						if(c1.equals(reyn.sitio(tablero)))
//						{
//							ayuda=true;
//							break;
//						}
//					}
//					if(ayuda==false)
//					{
//						caux.setOcupado(okupada);
//						temp.sitio(tablero).setOcupado(temp);
//						return false;
//					}
//				}
//			}
//		}
//		
//		return true;
//	}
//	
	public Boolean comprobarjaque(clsRey rey,viejotablerologico tab)
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
	
	public Boolean Jugadajaque(clsPieza movida,clsPieza sitio, clsCasilla Original, clsCasilla Final,viejotablerologico tab)
	{
		clsCasilla[][] tablero=tab.getTablero();
		
		Boolean retur=false;
		
		Original.setOcupado(null);
		Final.setOcupado(movida);
		
		if(movida.getColor())
		{
			for(clsPieza p: tab.getPblancas())
			{
//				if(p instanceof clsRey)
//					tab.setReyb((clsRey) p.clonar(p));
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
//				if(p instanceof clsRey)
//					tab.setReyn((clsRey) p.clonar(p));
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
				 
				// System.out.println(ncasilla);

				if(ncasilla.getOcupado()==null)
				{
					if(ncasilla.mov)
					{
						tablero[selec.getY()][selec.getX()].setOcupado(null);
						if(selec.a.equals(Comun.clsConstantes.piezas.Rey))
						{
							if(selec.getPrimera()==false)
								selec.setPrimera(true);
							
//							if(selec.getColor() && ncasilla.getx()==0 && ncasilla.gety()==1 && reyb.getPrimera()==true && btorred.getPrimera()==true && tablero[0][2].getOcupado()==null )
//							{
//								tablero[0][2].setOcupado(btorred);
//								tablero[0][0].setOcupado(null);
//							}
							
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
//						System.out.println("SDFGHJKHGHFDFHJ");
//						System.out.println(selec.sitio(tablero));
						ncasilla.setOcupado(selec);
						movact.remove(ncasilla);
						turno=!turno;
						if(selec.getColor())
						{
							if(comprobarjaque(reyn,this))
							{
							jaquemate=jaquematen();
							if(jaquemate)
								System.out.println("Siiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
							}
							}
						else
						{
							if(comprobarjaque(reyb,this))
							{
							jaquemate=jaquemateb();
							if(jaquemate)
								System.out.println("Siiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
							}
						}
						clear(movact);
						
						selec=null;
						
						
						//revisar();
					}
					clear(movact);
				}
				else 
				{
					//clear(movact);
					
//					if(ncasilla.getOcupado().a.equals(Comun.clsConstantes.piezas.Rey))
//					{
//						clear(movact);
//						selec=ncasilla.getOcupado();
//						selec.mov(tablero);
//						LinkedList<clsCasilla> lista= new LinkedList<clsCasilla>();
//						selec.mov(tablero);
//						lista.addAll(selec.movimientos);
//						
//						if(selec.getColor()== turno)
//						{	
//							//System.out.println("DFGHJKLKGFGHJKkjvbvcvbnbvcvbnbvbnmnbv");
//							LinkedList<clsPieza> colorcete;
//							
//							if(selec.getColor())
//								colorcete=pnegras;
//							else
//								colorcete=pblancas;
//							
//								for(clsPieza pieza: colorcete)
//								{
//									for(clsCasilla caux: pieza.influencia(tablero))
//									{
//										lista.remove(caux);
//									}
//								}
//								dibujarmov(lista);
//							
//						}
//						
//					
//					}
//					else{
						
					if(ncasilla.provisional==false)
					{
					clear(movact);
					
					selec=ncasilla.getOcupado();
					
					if (selec.getColor()== turno)
					{
						selec.mov(tablero);
						
						LinkedList<clsCasilla> aux= new LinkedList<clsCasilla>();

							viejotablerologico tablerete=clonar(this);
							tablerete.getSelec().mov(tablerete.getTablero());
							
//							for(clsPieza p: tablerete.getPblancas())System.out.println("blancas "+p);
//							for(clsPieza p: tablerete.getPnegras())System.out.println("negras "+p);
							clsCasilla[][] tabaux=tablerete.getTablero();
							
						for(clsCasilla caux : tablerete.getSelec().getMovimientos())
						{
							
						//	System.out.println("hola "+ caux );
							
							clsPieza okupada=null;
							if(caux.getOcupado()!=null)
							{
							//	System.out.println("pene");
								
								okupada=caux.getOcupado();
								
								if(okupada.getColor())
									tablerete.getPblancas().remove(okupada);
								else
									tablerete.getPnegras().remove(okupada);
							}
							//no estoy seguro orden 
				
//							tabaux[tablerete.getSelec().getY()][tablerete.getSelec().getX()].setOcupado(null);
//							tabaux[caux.gety()][caux.getx()].setOcupado(tablerete.getSelec());
							tabaux[tablerete.getSelec().getY()][tablerete.getSelec().getX()].setOcupado(null);
							caux.setOcupado(tablerete.getSelec());
							
						//Fijo que se puede evitar si se clona bien
							if(tablerete.getSelec() instanceof clsRey)
							{
								if(tablerete.getSelec().getColor())
								{
									caux.setOcupado(tablerete.getReyb());
								}
								else
								{
									caux.setOcupado(tablerete.getReyn());
								}
							}
							//mejorar con seleccionar una vez vale
							
							
							if(tablerete.getSelec().getColor())
							{								
								if(comprobarjaque(tablerete.getReyb(),tablerete)==false)
								{
									//System.out.println("B"+caux);
					
									aux.add(tablero[caux.gety()][caux.getx()]);
								}
							}
							else{
								if(comprobarjaque(tablerete.getReyn(),tablerete)==false)
								{
									//System.out.println("N"+caux);
									aux.add(tablero[caux.gety()][caux.getx()]);
								}
							}
							
							tabaux[selec.getY()][selec.getX()].setOcupado(tablerete.getSelec());
							caux.setOcupado(okupada);
							
							if(tablerete.getSelec() instanceof clsRey)
							{
								if(tablerete.getSelec().getColor())
								{
									tabaux[selec.getY()][selec.getX()].setOcupado(tablerete.getReyb());
								}
								else
								{
									tabaux[selec.getY()][selec.getX()].setOcupado(tablerete.getReyn());
								}
							}
							if(okupada!=null)
							{
							if(okupada.getColor())
								tablerete.getPblancas().add(okupada);
							else
								tablerete.getPnegras().add(okupada);
							}
						}
						
						
						
//						System.out.println(aux.size()+"SDFGHJKÑ");
//						for(clsCasilla a: aux)System.out.println("QAZ"+a);
						dibujarmov(aux);
					}

					}
					
			
			
					else
					{
//						System.out.println("WERTYUI "+ncasilla.getOcupado());
//						System.out.println(ncasilla.getOcupado().getColor());
//						System.out.println(ncasilla.getOcupado().getClass());
//						
//						System.out.println("q "+ selec);
//						System.out.println(selec.getColor());
//						System.out.println(selec.getClass());
						
//						if(selec.getColor())
//							pblancas.remove(ncasilla.getOcupado());
//						else
//							pnegras.remove(ncasilla.getOcupado());
						
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
//						if(selec.getColor())
//						{
//							reyn.jaque=comprobarjaque(reyn,this);
//						}
//							
//						else
//						{
//							reyb.jaque=comprobarjaque(reyb,this);
//						}
						turno=!turno;
						if(selec.getColor())
						{
							if(comprobarjaque(reyn,this))
							{
							jaquemate=jaquematen();
							if(jaquemate)
								System.out.println("Siiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
							}
							}
						else
						{
							if(comprobarjaque(reyb,this))
							{
							jaquemate=jaquemateb();
							if(jaquemate)
								System.out.println("Siiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
							}
						}
						selec=null;
						clear(movact);
						
						
					//	revisar();
					}
				}
//				}
			
//				System.out.println("el rey nigro");
//				System.out.println(reyn.jaque);
//				System.out.println("el rey blanco");
//				System.out.println(reyb.jaque);
	}

	
//	private void Conversor()
//	{
//		if(turno)
//		{
//			bseg--;
//			if (bseg==-1)
//			{
//				bseg=59;
//				bmin--;
//			}
//			bstr = String.format("%d:%02d", bmin, bseg);
//			//btiempo.setText(bstr);
//		}
//		else
//		{
//		nseg--;
//		if (nseg==-1)
//		{
//			nseg=59;
//			nmin--;
//		}
//		nstr = String.format("%d:%02d", nmin, nseg);
//		//ntiempo.setText(nstr);
//		}
//	}
	
	
//	class Timer implements Runnable
//	{
//		@Override
//		public void run() 
//		{
//			while(true)
//			{
//					try 
//					{
//						Thread.sleep(1000);
//						Conversor();
//					}
//					catch (InterruptedException e) 
//					{
//						return;
//					}
//			}
//		}
//	
//	}
//

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
//		return myTimer;
//	}
//
//	public void setMyTimer(Runnable myTimer) {
//		this.myTimer = myTimer;
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