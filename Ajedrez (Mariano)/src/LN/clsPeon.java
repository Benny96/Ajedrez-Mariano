package LN;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Clase creada para generar un objeto nuevo (clsPeon) que hereda de clsPieza.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */

public class clsPeon extends clsPieza implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de clsPeon con el que se construirán inicialmente las piezas.
	 * @param x Coordenada X del tablero.
	 * @param y Coordenada Y del tablero.
	 * @param color true = blanco; false = negro.
	 */
	public clsPeon(int x, int y, Boolean color) 
	{
		super(x, y, color);
		this.a=Comun.clsConstantes.piezas.Peon;
		valor=100;
		if(color)
		{
			try 
			{
				Image img = ImageIO.read(getClass().getResource("/img/peon_blanco.png"));
				this.setIcon(new ImageIcon(img));
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			try 
			{
				Image img = ImageIO.read(getClass().getResource("/img/peon_negro.png"));
				this.setIcon(new ImageIcon(img));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Constructor de clsPeon usado para clonar un peón determinado.
	 * @param x Coordenada X del tablero.
	 * @param y Coordenada Y del tablero.
	 * @param color true = blanco; false = negro.
	 * @param na Flag distintivo del constructor.
	 */
	public clsPeon(int x, int y, Boolean color,Boolean na)
	{
		super(x, y, color);
		this.a=Comun.clsConstantes.piezas.Peon;
		valor=100;
	}
	
	/**
	 * Constructor vacío para serializar.
	 */
	public clsPeon()
	{}
	
	/**
	 * Método para obtener la influencia que ejerce un peón sobre un tablero dado.
	 * @param tablero Tablero con las casillas ocupadas y vacías.
	 * @return LinkedList <clsCasilla> que indica a qué posiciones afecta la pieza.
	 */
	public LinkedList<clsCasilla> influencia (clsCasilla[][] tablero)
	{
		influencia.clear();
		if(this.getColor())
		{
			if(this.y+1<8 && this.getX()+1<8)
			{
				this.influencia.add(tablero[this.y+1][this.getX()+1]);
			}
			if(this.y+1<8 && this.getX()-1>-1)
			{
				this.influencia.add(tablero[this.y+1][this.getX()-1]);
			}
		}
		else
		{
			if(this.y-1>-1 && this.getX()+1<8)
			{
				this.influencia.add(tablero[this.y-1][this.getX()+1]);
			}
			if(this.y-1>-1 && this.getX()-1>-1)
			{
				this.influencia.add(tablero[this.y-1][this.getX()-1]);
			}
		}
		return influencia;
	}
	/**
	 * Método para obtener los movimientos que puede realizar un peón en un tablero dado.
	 * @param tablero Tablero con las casillas ocupadas y vacías.
	 * @return LinkedList <clsCasilla> que indica a qué posiciones puede moverse la pieza.
	 */
	public void mov(clsCasilla[][] tablero)
	{
		movimientos.clear();
		if(this.getColor())
		{
			if (this.y <7)
			{
				if(this.y!=1)
				{
					if(tablero[this.y+1][this.getX()].getOcupado()==null)
						this.movimientos.add(tablero[this.y+1][this.getX()]);
				}
				else
				{
					if(tablero[this.y+1][this.getX()].getOcupado()==null)
					{
						this.movimientos.add(tablero[this.y+1][this.getX()]);
					if(tablero[this.y+2][this.getX()].getOcupado()==null)
						this.movimientos.add(tablero[this.y+2][this.getX()]);
					}
				}
			}
			if(this.y+1<8 && this.getX()+1<8 && tablero[this.y+1][this.getX()+1].getOcupado()!=null && tablero[this.y+1][this.getX()+1].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[this.y+1][this.getX()+1]);
			}
			if(this.y+1<8 && this.getX()-1>-1 && tablero[this.y+1][this.getX()-1].getOcupado()!=null && tablero[this.y+1][this.getX()-1].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[this.y+1][this.getX()-1]);
			}	
		}
		else
		{
			if(this.y!=6)
			{
				if(this.y>=1 && tablero[this.y-1][this.getX()].getOcupado()==null)
					this.movimientos.add(tablero[this.y-1][this.getX()]);				
			}
			else
			{		
				if(tablero[this.y-1][this.getX()].getOcupado()==null)
				{
					this.movimientos.add(tablero[this.y-1][this.getX()]);
				if(tablero[this.y-2][this.getX()].getOcupado()==null)
					this.movimientos.add(tablero[this.y-2][this.getX()]);
				}
			}
			if(this.y-1>-1 && this.getX()+1<8 && tablero[this.y-1][this.getX()+1].getOcupado()!=null && tablero[this.y-1][this.getX()+1].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[this.y-1][this.getX()+1]);
			}
			if(this.y-1>-1 && this.getX()-1>-1 && tablero[this.y-1][this.getX()-1].getOcupado()!=null && tablero[this.y-1][this.getX()-1].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[this.y-1][this.getX()-1]);
			}
		}
	}
}
