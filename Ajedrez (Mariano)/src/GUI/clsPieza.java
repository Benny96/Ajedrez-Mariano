package GUI;

import javax.swing.ImageIcon;
import Comun.clsConstantes.piezas;

public class clsPieza 
{
	private int x;
	private int y;
	piezas a;
	private Boolean color; //True--> blanco ///  False--> negro
	private ImageIcon icon;
	
	public clsPieza(int x, int y, Boolean color)
	{
		this.y=x;
		this.x=y;
		this.color=color;		
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
}