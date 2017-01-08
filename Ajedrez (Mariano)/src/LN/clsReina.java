package LN;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Clase creada para generar un objeto nuevo (clsReina) que hereda de clsPieza.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */

public class clsReina extends clsPieza implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor de clsReina con el que se construirán inicialmente las piezas.
	 * @param x Coordenada X del tablero.
	 * @param y Coordenada Y del tablero.
	 * @param color true = blanco; false = negro.
	 */
	public clsReina(int x, int y, Boolean color) 
	{
		super(x, y, color);
		this.a=Comun.clsConstantes.piezas.Reina;
		valor=900;
		if(color)
		{
			try 
			{
				Image img = ImageIO.read(getClass().getResource("/img/reina_b.png"));
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
				Image img = ImageIO.read(getClass().getResource("/img/reina_n.png"));
				this.setIcon(new ImageIcon(img));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	/**
	 * Constructor de clsReina usado para clonar una reina determinada.
	 * @param x Coordenada X del tablero.
	 * @param y Coordenada Y del tablero.
	 * @param color true = blanco; false = negro.
	 * @param na Flag distintivo del constructor.
	 */
	public clsReina(int x, int y, Boolean color,Boolean na) 
	{
		super(x, y, color);
		this.a=Comun.clsConstantes.piezas.Reina;
		valor=900;
	}
	
	/**
	 * Constructor vacío para serializar.
	 */
	public clsReina()
	{}
	
	/**
	 * Método para obtener la influencia que ejerce una reina sobre un tablero dado.
	 * @param tablero Tablero con las casillas ocupadas y vacías.
	 * @return LinkedList de casillas que indica a qué posiciones afecta la pieza.
	 */
	public LinkedList<clsCasilla> influencia (clsCasilla[][] tablero)
	{
		int t1=this.getY();
		int t2=this.getX();
		
		influencia.clear();

		clsTorre torre= new clsTorre(t1,t2,this.getColor());
		clsAlfil alfil= new clsAlfil(t1,t2,this.getColor());
		
		influencia.addAll(torre.influencia(tablero));
		influencia.addAll(alfil.influencia(tablero));
		return influencia;	
	}
	
	/**
	 * Método para obtener los movimientos que puede realizar una reina en un tablero dado.
	 * @param tablero Tablero con las casillas ocupadas y vacías.
	 */
	public void mov(clsCasilla[][] tablero)
	{
		int t1=this.getY();
		int t2=this.getX();
		
		movimientos.clear();
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
		
		t1=this.getY();
		t2=this.getX();
		
		do
		{
			if((t1+1)==8)
				break;
			t1++;
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
			if(t1-1==-1)
				break;
			t1--;
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
			if(t2+1==8)
				break;
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
			if(t2-1==-1)
				break;
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
	}
}