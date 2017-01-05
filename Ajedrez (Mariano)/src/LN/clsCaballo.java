package LN;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Clase creada para generar un objeto nuevo (clsCaballo) que hereda de clsPieza.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */

public class clsCaballo extends clsPieza implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de clsCaballo con el que se construirán inicialmente las piezas.
	 * @param x Coordenada X del tablero.
	 * @param y Coordenada Y del tablero.
	 * @param color true = blanco; false = negro.
	 */
	public clsCaballo(int x, int y, Boolean color) 
	{
		super(x, y, color);
		this.a=Comun.clsConstantes.piezas.Caballo;
		valor=300;
		if(color)
		{
			try 
			{
				Image img = ImageIO.read(getClass().getResource("/img/caballo_b.png"));
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
				Image img = ImageIO.read(getClass().getResource("/img/caballo_n.png"));
				this.setIcon(new ImageIcon(img));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * Constructor de clsCaballo usado para clonar un caballo determinado.
	 * @param x Coordenada X del tablero.
	 * @param y Coordenada Y del tablero.
	 * @param color true = blanco; false = negro.
	 * @param na Flag distintivo del constructor.
	 */
	public clsCaballo(int x, int y, Boolean color,Boolean na) 
	{
		super(x, y, color);
		this.a=Comun.clsConstantes.piezas.Caballo;
		valor=300;
	}
	
	/**
	 * Constructor vacío para serializar.
	 */
	public clsCaballo()
	{}
	
	/**
	 * Método para obtener la influencia que ejerce un caballo sobre un tablero dado.
	 * @param tablero Tablero con las casillas ocupadas y vacías.
	 * @return LinkedList <clsCasilla> que indica a qué posiciones afecta la pieza.
	 */
	public LinkedList<clsCasilla> influencia (clsCasilla[][] tablero)
	{
		influencia.clear();
		int t1=this.getY();
		int t2=this.getX();
		if(t1+2<8)
		{
			t1=t1+2;
			if(t2+1<8)
			{
				t2++;
				this.influencia.add(tablero[t1][t2]);
			}
			t2=this.getX();
			if(t2-1>=0)
			{
				t2--;
				this.influencia.add(tablero[t1][t2]);
			}
		}
		t1=this.getY();
		t2=this.getX();
		if(t1+1<8)
		{
			t1++;
			if(t2+2<8)
			{
				t2=t2+2;
				this.influencia.add(tablero[t1][t2]);
			}
			t2=this.getX();
			if(t2-2>=0)
			{
				t2=t2-2;
				this.influencia.add(tablero[t1][t2]);
			}
		}
		t1=this.getY();
		t2=this.getX();
		if(t1-1>=0)
		{
			t1--;
			if(t2+2<8)
			{
				t2=t2+2;
				this.influencia.add(tablero[t1][t2]);
			}
			t2=this.getX();
			if(t2-2>=0)
			{
				t2=t2-2;
				this.influencia.add(tablero[t1][t2]);
			}
		}	
		t1=this.getY();
		t2=this.getX();
		if(t1-2>=0)
		{
			t1=t1-2;		
			if(t2+1<8)
			{
				t2++;
				this.influencia.add(tablero[t1][t2]);
			}
			t2=this.getX();
			if(t2-1>=0)
			{
				t2--;
				this.influencia.add(tablero[t1][t2]);	
			}	
		}
		return influencia;
	}
	
	/**
	 * Método para obtener los movimientos que puede realizar un caballo en un tablero dado.
	 * @param tablero Tablero con las casillas ocupadas y vacías.
	 * @return LinkedList <clsCasilla> que indica a qué posiciones puede moverse la pieza.
	 */
	public void mov(clsCasilla[][] tablero)
	{
		movimientos.clear();	
		int t1=this.getY();
		int t2=this.getX();
		if(t1+2<8)
		{
			t1=t1+2;
			if(t2+1<8)
			{
				t2++;
				if(tablero[t1][t2].getOcupado()!=null && tablero[t1][t2].getOcupado().getColor().equals(this.getColor())==false)
				{
					this.movimientos.add(tablero[t1][t2]);
				}
				else
				{	if(tablero[t1][t2].getOcupado()==null)
						this.movimientos.add(tablero[t1][t2]);
				}
			}
			t2=this.getX();
			if(t2-1>=0)
			{
				t2--;
				if(tablero[t1][t2].getOcupado()!=null && tablero[t1][t2].getOcupado().getColor().equals(this.getColor())==false)
				{
					this.movimientos.add(tablero[t1][t2]);
				}
				else
				{
					if(tablero[t1][t2].getOcupado()==null)
						this.movimientos.add(tablero[t1][t2]);
				}
			}
		}
		t1=this.getY();
		t2=this.getX();
		
		if(t1+1<8)
		{
			t1++;
			if(t2+2<8)
			{
				t2=t2+2;
				if(tablero[t1][t2].getOcupado()!=null && tablero[t1][t2].getOcupado().getColor().equals(this.getColor())==false)
				{
					this.movimientos.add(tablero[t1][t2]);
				}
				else
				{	
					if(tablero[t1][t2].getOcupado()==null)
						this.movimientos.add(tablero[t1][t2]);
				}
			}
			t2=this.getX();
			if(t2-2>=0)
			{
				t2=t2-2;
				if(tablero[t1][t2].getOcupado()!=null && tablero[t1][t2].getOcupado().getColor().equals(this.getColor())==false)
				{
					this.movimientos.add(tablero[t1][t2]);
				}
				else
				{
					if(tablero[t1][t2].getOcupado()==null)
						this.movimientos.add(tablero[t1][t2]);
				}
			}
		}
		t1=this.getY();
		t2=this.getX();
		if(t1-1>=0)
		{
			t1--;
			if(t2+2<8)
			{
				t2=t2+2;
				if(tablero[t1][t2].getOcupado()!=null && tablero[t1][t2].getOcupado().getColor().equals(this.getColor())==false)
				{
					this.movimientos.add(tablero[t1][t2]);
				}
				else
				{
					if(tablero[t1][t2].getOcupado()==null)
						this.movimientos.add(tablero[t1][t2]);
				}
			}
			t2=this.getX();
			if(t2-2>=0)
			{
				t2=t2-2;
				if(tablero[t1][t2].getOcupado()!=null && tablero[t1][t2].getOcupado().getColor().equals(this.getColor())==false)
				{
					this.movimientos.add(tablero[t1][t2]);
				}
				else
				{
					if(tablero[t1][t2].getOcupado()==null)
						this.movimientos.add(tablero[t1][t2]);
				}
			}
		}
		t1=this.getY();
		t2=this.getX();
		if(t1-2>=0)
		{
			t1=t1-2;
			if(t2+1<8)
			{
				t2++;
				if(tablero[t1][t2].getOcupado()!=null && tablero[t1][t2].getOcupado().getColor().equals(this.getColor())==false)
				{
					this.movimientos.add(tablero[t1][t2]);
				}
				else
				{
					if(tablero[t1][t2].getOcupado()==null)
						this.movimientos.add(tablero[t1][t2]);
				}
			}
			t2=this.getX();
			if(t2-1>=0)
			{
				t2--;
				if(tablero[t1][t2].getOcupado()!=null && tablero[t1][t2].getOcupado().getColor().equals(this.getColor())==false)
				{
					this.movimientos.add(tablero[t1][t2]);
				}
				else
				{
					if(tablero[t1][t2].getOcupado()==null)
						this.movimientos.add(tablero[t1][t2]);
				}
			}
		}		
	}
}
