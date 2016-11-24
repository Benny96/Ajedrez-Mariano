package GUI;

import java.util.LinkedList;

import javax.swing.ImageIcon;

import Comun.clsConstantes.piezas;

public class clsPieza 
{
	protected int x;
	protected int y;
	piezas a;
	private Boolean color; //True--> blanco ///  False--> negro
	private ImageIcon icon;
	protected LinkedList<GUI.clsCasilla> movimientos;
	protected LinkedList<clsCasilla> influencia;
	protected Boolean primera;
	int valor;
	
	public clsPieza(int x, int y, Boolean color)
	{
		this.y=x;
		this.x=y;
		this.color=color;	
		this.primera=false;
		
		

		movimientos= new LinkedList<clsCasilla>();
		influencia= new LinkedList<clsCasilla>();
	}
	
	public clsCasilla sitio(clsCasilla[][] tablero)
	{
		return tablero[y][x];
	}
	public int getX()
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public Boolean getColor()
	{
		return color;
	}

	public void setColor(Boolean color) 
	{
		this.color = color;
	}

	public ImageIcon getIcon() 
	{
		return icon;
	}

	public void setIcon(ImageIcon icon) 
	{
		this.icon = icon;
	}

	@Override
	public String toString()
	{
		return "clsPieza ["+x+", "+y+"]";
	}

	public piezas getA() 
	{
		return a;
	}

	public void setA(piezas a) 
	{
		this.a = a;
	}
	
	public void mov(clsCasilla[][] tablero)
	{
		System.out.println("WERT");
	}
	public LinkedList<clsCasilla> influencia(clsCasilla[][] tablero){
		return movimientos;}
	public LinkedList<GUI.clsCasilla> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(LinkedList<GUI.clsCasilla> movimientos) {
		this.movimientos = movimientos;
	}

	public LinkedList<clsCasilla> getInfluencia() {
		return influencia;
	}

	public void setInfluencia(LinkedList<clsCasilla> influencia) {
		this.influencia = influencia;
	}

	public Boolean getPrimera() {
		return primera;
	}

	public void setPrimera(Boolean primera) {
		this.primera = primera;
	}
	
	public clsPieza clonar(clsPieza pieza,tablerologico tab)
	{
		//mejorar rey,torre atributos especiales
		clsCasilla [][] tablerete=tab.getTablero();
		if(pieza instanceof clsPeon)
		{
			clsPeon a=new clsPeon(pieza.getY(),pieza.getX(),pieza.getColor(),true);
			for(clsCasilla s: pieza.movimientos)
			{
				a.movimientos.add(tablerete[s.gety()][s.gety()]);
			}
			return (a);
		}
		if(pieza instanceof clsTorre)
		{
			clsTorre a=new clsTorre(pieza.getY(),pieza.getX(),pieza.getColor(),true);
			for(clsCasilla s: pieza.movimientos)
			{
				a.movimientos.add(tablerete[s.gety()][s.gety()]);
			}
			return (a);
		}
		if(pieza instanceof clsCaballo)
		{
			clsCaballo a=new clsCaballo(pieza.getY(),pieza.getX(),pieza.getColor(),true);
			for(clsCasilla s: pieza.movimientos)
			{
				a.movimientos.add(tablerete[s.gety()][s.gety()]);
			}
			return (a);
		}
		if(pieza instanceof clsAlfil)
		{
			clsAlfil a=new clsAlfil(pieza.getY(),pieza.getX(),pieza.getColor(),true);
			for(clsCasilla s: pieza.movimientos)
			{
				a.movimientos.add(tablerete[s.gety()][s.gety()]);
			}
			return (a);
		}
		if(pieza instanceof clsRey)
		{
			clsRey a=new clsRey(pieza.getY(),pieza.getX(),pieza.getColor(),true);
			for(clsCasilla s: pieza.movimientos)
			{
				a.movimientos.add(tablerete[s.gety()][s.gety()]);
			}
			return (a);
		}
		if(pieza instanceof clsReina)
		{
			clsReina a=new clsReina(pieza.getY(),pieza.getX(),pieza.getColor(),true);
			for(clsCasilla s: pieza.movimientos)
			{
				a.movimientos.add(tablerete[s.gety()][s.gety()]);
			}
			return (a);
		}	
		return null;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

//	@Override
//	protected Object clone() throws CloneNotSupportedException {
//		// TODO Auto-generated method stub
//		return super.clone();
//	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj.getClass().equals(this.getClass())&& this.x==((clsPieza)obj).x &&  this.y==((clsPieza)obj).y )
			return true;
		else 
			return false;
	}
}
