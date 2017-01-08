package LN;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Clase creada para generar un objeto nuevo (clsAlfil) que hereda de clsPieza.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */

public class clsAlfil extends clsPieza implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de clsAlfil con el que se construirán inicialmente las piezas.
	 * @param x Coordenada X del tablero.
	 * @param y Coordenada Y del tablero.
	 * @param color true = blanco; false = negro.
	 */
	public clsAlfil(int x, int y, Boolean color) 
	{
		super(x, y, color);
		this.a=Comun.clsConstantes.piezas.Alfil;
		influencia= new LinkedList<clsCasilla>();
		valor=300;
		if(color)
		{
			try 
			{
				Image img = ImageIO.read(getClass().getResource("/img/Alfil_b.png"));
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
				Image img = ImageIO.read(getClass().getResource("/img/Alfil_n.png"));
				this.setIcon(new ImageIcon(img));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	/**
	 * Constructor de clsAlfil usado para clonar un alfil determinado.
	 * @param x Coordenada X del tablero.
	 * @param y Coordenada Y del tablero.
	 * @param color true = blanco; false = negro.
	 * @param na Flag distintivo del constructor.
	 */
	public clsAlfil(int x, int y, Boolean color,Boolean na) 
	{
		super(x, y, color);
		this.a=Comun.clsConstantes.piezas.Alfil;
		valor=300;
	}
	/**
	 * Constructor vacío para serializar.
	 */
	public clsAlfil()
	{}
	
	/**
	 * Método para obtener la influencia que ejerce un alfil sobre un tablero dado.
	 * @param tablero Tablero con las casillas ocupadas y vacías.
	 * @return LinkedList de casillas que indica a qué posiciones afecta la pieza.
	 */
	public LinkedList<clsCasilla> influencia (clsCasilla[][] tablero)
	{
		influencia.clear();
		int t1=this.getY();
		int t2=this.getX();
		do
		{
			if((t1+1)==8 || (t2+1)==8 )
				break;
			t1++;
			t2++;
			if(tablero[t1][t2].getOcupado()==null)
			{
				this.influencia.add(tablero[t1][t2]);
			}
			else
			{	
				this.influencia.add(tablero[t1][t2]);
				break;
			}
		}
		while(true);
		t1=this.getY();
		t2=this.getX();
		do
		{
			if((t1+1)==8 || (t2-1)==-1 )
				break;
			t1++;
			t2--;
			if(tablero[t1][t2].getOcupado()==null)
			{
				this.influencia.add(tablero[t1][t2]);
			}
			else
			{
				this.influencia.add(tablero[t1][t2]);
				break;
			}
		}
		while(true);
		t1=this.getY();
		t2=this.getX();
		do
		{
			if((t1-1)== -1|| (t2-1)==-1 )
				break;
			t1--;
			t2--;
			if(tablero[t1][t2].getOcupado()==null)
			{
				this.influencia.add(tablero[t1][t2]);
			}
			else
			{
				this.influencia.add(tablero[t1][t2]);
				break;
			}
		}
		while(true);
		t1=this.getY();
		t2=this.getX();
		do
		{
			if((t1-1)== -1|| (t2+1)==8 )
				break;
			t1--;
			t2++;
			if(tablero[t1][t2].getOcupado()==null)
			{
				this.influencia.add(tablero[t1][t2]);
			}
			else
			{
				this.influencia.add(tablero[t1][t2]);
				break;
			}
		}
		while(true);
		return influencia;
	}
	/**
	 * Método para obtener los movimientos que puede realizar un alfil en un tablero dado.
	 * @param tablero Tablero con las casillas ocupadas y vacías.
	 */
	public void mov(clsCasilla[][] tablero)
	{
		movimientos.clear();
		int t1=this.getY();
		int t2=this.getX();
		do
		{
			if((t1+1)==8 || (t2+1)==8 )
				break;
			t1++;
			t2++;
			if(tablero[t1][t2].getOcupado()==null)
			{
				this.movimientos.add(tablero[t1][t2]);
			}
			else
			{
				if(tablero[t1][t2].getOcupado().getColor().equals(this.getColor())==false)
				{
					this.movimientos.add(tablero[t1][t2]);
				}
				break;
			}
		}
		while(true);
		t1=this.getY();
		t2=this.getX();
		do
		{
			if((t1+1)==8 || (t2-1)==-1 )
				break;
			t1++;
			t2--;
			if(tablero[t1][t2].getOcupado()==null)
			{
				this.movimientos.add(tablero[t1][t2]);
			}
			else
			{
				if(tablero[t1][t2].getOcupado().getColor().equals(this.getColor())==false)
				{
					this.movimientos.add(tablero[t1][t2]);
				}
				break;
			}
		}
		while(true);
		t1=this.getY();
		t2=this.getX();
		do
		{
			if((t1-1)== -1|| (t2-1)==-1 )
				break;
			t1--;
			t2--;
			if(tablero[t1][t2].getOcupado()==null)
			{
				this.movimientos.add(tablero[t1][t2]);
			}
			else
			{
				if(tablero[t1][t2].getOcupado().getColor().equals(this.getColor())==false)
				{
					this.movimientos.add(tablero[t1][t2]);
				}
				break;
			}
		}
		while(true);
		t1=this.getY();
		t2=this.getX();
		do
		{	
			if((t1-1)== -1|| (t2+1)==8 )
				break;
			t1--;
			t2++;
			if(tablero[t1][t2].getOcupado()==null)
			{
				this.movimientos.add(tablero[t1][t2]);
			}
			else
			{
				if(tablero[t1][t2].getOcupado().getColor().equals(this.getColor())==false)
				{
					this.movimientos.add(tablero[t1][t2]);
				}
				break;
			}
		}
		while(true);	
	}
}
