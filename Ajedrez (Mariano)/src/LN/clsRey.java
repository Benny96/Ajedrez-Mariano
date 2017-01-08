package LN;
import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Comun.clsConstantes;

/**
 * Clase creada para generar un objeto nuevo (clsRey) que hereda de clsPieza.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */

public class clsRey extends clsPieza implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	Boolean jaque;
	/**
	 * Constructor de clsRey con el que se construirán inicialmente las piezas.
	 * @param Coordenada X del tablero.
	 * @param Coordenada Y del tablero.
	 * @param color true = blanco; false = negro.
	 */
	public clsRey(int x, int y, boolean color)
	{
		super(x, y, color);
		this.a=clsConstantes.piezas.Rey;
		valor=10000;
		jaque=false;
		primera=false;
		if(color)
		{
			try 
			{
				Image img = ImageIO.read(getClass().getResource("/img/rey_b.png"));
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
				Image img = ImageIO.read(getClass().getResource("/img/rey_n.png"));
				this.setIcon(new ImageIcon(img));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Constructor de clsRey usado para clonar un rey determinado.
	 * @param Coordenada X del tablero.
	 * @param Coordenada Y del tablero.
	 * @param color true = blanco; false = negro.
	 * @param Flag distintivo del constructor.
	 */
	public clsRey(int x, int y, Boolean color,Boolean na) 
	{
		super(x, y, color);
		this.a=Comun.clsConstantes.piezas.Rey;
		valor=10000;
		primera=false;
		jaque=false;
	}
	
	/**
	 * Constructor vacío para serializar.
	 */
	public clsRey()
	{}
	
	/**
	 * Método para obtener la influencia que ejerce un rey sobre un tablero dado.
	 * @param Tablero con las casillas ocupadas y vacías.
	 * @return LinkedList de casillas que indica a qué posiciones afecta la pieza.
	 */
	public LinkedList<clsCasilla> influencia (clsCasilla[][] tablero)
	{	
		influencia.clear();
		int t1=this.getY();
		int t2=this.getX();
		
		if(t1+1<8)
		{
			if(tablero[t1+1][t2].getOcupado()==null || tablero[t1+1][t2].getOcupado()!=null )
			{
				this.influencia.add(tablero[t1+1][t2]);
			}
			if(t2+1<8 && tablero[t1+1][t2+1].getOcupado()==null || t2+1<8 && tablero[t1+1][t2+1].getOcupado()!=null )
			{
				this.influencia.add(tablero[t1+1][t2+1]);
			}
			if(t2-1>-1 && tablero[t1+1][t2-1].getOcupado()==null ||  t2-1>-1 && tablero[t1+1][t2-1].getOcupado()!=null )
			{
				this.influencia.add(tablero[t1+1][t2-1]);
			}
		}
		
		if(t1-1>=0)
		{
			if(tablero[t1-1][t2].getOcupado()==null || tablero[t1-1][t2].getOcupado()!=null )
			{
				this.influencia.add(tablero[t1-1][t2]);
			}
			if(t2+1<8 && tablero[t1-1][t2+1].getOcupado()==null|| t2+1<8 && tablero[t1-1][t2+1].getOcupado()!=null )
			{
				this.influencia.add(tablero[t1-1][t2+1]);
			}
			if(t2-1>-1 && tablero[t1-1][t2-1].getOcupado()==null|| t2-1>-1 && tablero[t1-1][t2-1].getOcupado()!=null)
			{
				this.influencia.add(tablero[t1-1][t2-1]);
			}
		}
		
		if(t2-1>-1 && tablero[t1][t2-1].getOcupado()==null || t2-1>-1)
		{
			this.influencia.add(tablero[t1][t2-1]);	
		}
		if(t2+1<8 && tablero[t1][t2+1].getOcupado()==null || t2+1<8 && tablero[t1][t2+1].getOcupado()!=null )
		{
			this.influencia.add(tablero[t1][t2+1]);		
		}	
		return influencia;
	}
	
	/**
	 * Método para obtener los movimientos que puede realizar un rey en un tablero dado.
	 * @param Tablero con las casillas ocupadas y vacías.
	 * @return LinkedList de casillas que indica a qué posiciones puede moverse la pieza.
	 */
	public void mov(clsCasilla[][] tablero)
	{
		movimientos.clear();
		int t1=this.getY();
		int t2=this.getX();
		
		if(t1+1<8)
		{
			if(tablero[t1+1][t2].getOcupado()==null || tablero[t1+1][t2].getOcupado()!=null && tablero[t1+1][t2].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[t1+1][t2]);
			}
			
			if(t2+1<8 && tablero[t1+1][t2+1].getOcupado()==null || t2+1<8 && tablero[t1+1][t2+1].getOcupado()!=null && tablero[t1+1][t2+1].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[t1+1][t2+1]);
			}
			if(t2-1>-1 && tablero[t1+1][t2-1].getOcupado()==null ||  t2-1>-1 && tablero[t1+1][t2-1].getOcupado()!=null && tablero[t1+1][t2-1].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[t1+1][t2-1]);
			}
		}
		
		if(t1-1>=0)
		{
			if(tablero[t1-1][t2].getOcupado()==null || tablero[t1-1][t2].getOcupado()!=null && tablero[t1-1][t2].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[t1-1][t2]);
			}
			if(t2+1<8 && tablero[t1-1][t2+1].getOcupado()==null|| t2+1<8 && tablero[t1-1][t2+1].getOcupado()!=null && tablero[t1-1][t2+1].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[t1-1][t2+1]);
			}
			if(t2-1>-1 && tablero[t1-1][t2-1].getOcupado()==null|| t2-1>-1 && tablero[t1-1][t2-1].getOcupado()!=null && tablero[t1-1][t2-1].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[t1-1][t2-1]);
			}
		}
		
		if(t2-1>-1 && tablero[t1][t2-1].getOcupado()==null || t2-1>-1 && tablero[t1][t2-1].getOcupado()!=null && tablero[t1][t2-1].getOcupado().getColor().equals(this.getColor()==false))
		{
			this.movimientos.add(tablero[t1][t2-1]);
			try
			{
				if(this.primera==false && t2-2>-1 && tablero[t1][t2-2].getOcupado()==null   && t2-3>-1 && tablero[t1][t2-3].getOcupado()!=null && ((clsTorre)tablero[t1][t2-3].getOcupado()).primera==false)
				{
					this.movimientos.add(tablero[t1][t2-2]);
				}
			}
			catch(ClassCastException|NullPointerException e)
			{}
		}
		
		if(t2+1<8 && tablero[t1][t2+1].getOcupado()==null || t2+1<8 && tablero[t1][t2+1].getOcupado()!=null && tablero[t1][t2+1].getOcupado().getColor().equals(this.getColor()==false))
		{
			this.movimientos.add(tablero[t1][t2+1]);
			try
			{
				if(this.primera==false && t2+2<8 && tablero[t1][t2+2].getOcupado()==null && t2+3<8 && tablero[t1][t2+3].getOcupado()==null && t2+4<8 && tablero[t1][t2+4].getOcupado()!=null && ((clsTorre)tablero[t1][t2+4].getOcupado()).primera==false)
				{
					this.movimientos.add(tablero[t1][t2+2]);
				}
			}
			catch(ClassCastException|NullPointerException e)
			{}
		}	
	}
}