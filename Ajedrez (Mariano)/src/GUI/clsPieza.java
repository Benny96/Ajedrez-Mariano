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
	
	public clsPieza(int x, int y, Boolean color)
	{
		this.y=x;
		this.x=y;
		this.color=color;	

		movimientos= new LinkedList<clsCasilla>();
	}
	
	public clsCasilla sitio(clsCasilla[][] tablero)
	{
		return tablero[x][y];
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
	}
	public LinkedList<clsCasilla> influencia(clsCasilla[][] tablero){
		return movimientos;}
	public LinkedList<GUI.clsCasilla> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(LinkedList<GUI.clsCasilla> movimientos) {
		this.movimientos = movimientos;
	}
}