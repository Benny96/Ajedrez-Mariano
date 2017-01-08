package LN;
import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import Mariano.TableroLogicoMariano;
import Unopauno.TableroLogico1v1;
import Comun.clsConstantes.piezas;

/**
 * Clase creada para generar un objeto nuevo (clsPieza), de la que heredarán todas las piezas del ajedrez.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */

public class clsPieza implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected int x;
	protected int y;
	public piezas a;
	private Boolean color;
	private ImageIcon icon;
	protected LinkedList<clsCasilla> movimientos;
	protected LinkedList<clsCasilla> influencia;
	public Boolean primera;
	int valor;
	
	/**
	 * Constructor de clsPieza con el que se construirán inicialmente las piezas.
	 * @param Coordenada X del tablero.
	 * @param Coordenada Y del tablero.
	 * @param color true = blanco; false = negro.
	 */
	public clsPieza(int x, int y, Boolean color)
	{
		this.y=x;
		this.x=y;
		this.color=color;	
		this.primera=false;
		movimientos= new LinkedList<clsCasilla>();
		influencia= new LinkedList<clsCasilla>();
	}
	
	/**
	 * Constructor vacío para serializar.
	 */
	public clsPieza()
	{}
	
	/**
	 * Método que devuelve una posición concreta de un tablero, según las coordenadas de la pieza.
	 * @param Tablero de juego
	 * @return Posición en la que se encuentra la pieza
	 */
	public clsCasilla sitio(clsCasilla[][] tablero)
	{
		return tablero[y][x];
	}
	
	/**
	 * Método mov sin codificar que heredarán todo el resto de piezas y que implementará cada una de ellas.
	 * @param Tablero actual.
	 */
	public void mov(clsCasilla[][] tablero)
	{}
	
	/**
	 * Implementación del método toString() que recoge las coordenadas x e y.
	 */
	@Override
	public String toString()
	{
		return "clsPieza ["+x+", "+y+"]";
	}
	/**
	 * Método utilizado para escribir los movimientos en la JTable de la partida.
	 * @return Letra con la coordenada X.
	 */
	public String escritura()
	{
		String letra = null;
		
			if(this.getX()==0)
				letra="h";
			if(this.getX()==1)
				letra="g";
			if(this.getX()==2)
				letra="f";
			if(this.getX()==3)
				letra="e";
			if(this.getX()==4)
				letra="d";
			if(this.getX()==5)
				letra="c";
			if(this.getX()==6)
				letra="b";
			if(this.getX()==7)
				letra="a";	
		return letra+(this.getY()+1);
	}
	/**
	 * Método para clonar una pieza utilizando un TableroLogico1v1.
	 * @param Pieza a clonar en el tablero.
	 * @param TableroLogico1v1 a utilizar.
	 * @return Pieza clonada.
	 */
	public clsPieza clonarTab1v1(clsPieza pieza,TableroLogico1v1 tab)
	{
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
	
	/**
	 * Método para clonar una pieza utilizando un TableroLogicoMariano.
	 * @param Pieza a clonar en el tablero.
	 * @param TableroLogicoMariano a utilizar.
	 * @return Pieza clonada.
	 */
	public clsPieza clonar(clsPieza pieza,TableroLogicoMariano tab)
	{
		clsCasilla [][] tablerete=tab.getTablero();
		if(pieza instanceof clsPeon)
		{
			clsPeon a=new clsPeon(pieza.getY(),pieza.getX(),pieza.getColor(),false);
			for(clsCasilla s: pieza.movimientos)
			{
				a.movimientos.add(tablerete[s.gety()][s.gety()]);
			}
			return (a);
		}
		if(pieza instanceof clsTorre)
		{
			clsTorre a=new clsTorre(pieza.getY(),pieza.getX(),pieza.getColor(),false);
			for(clsCasilla s: pieza.movimientos)
			{
				a.movimientos.add(tablerete[s.gety()][s.gety()]);
			}
			return (a);
		}
		if(pieza instanceof clsCaballo)
		{
			clsCaballo a=new clsCaballo(pieza.getY(),pieza.getX(),pieza.getColor(),false);
			for(clsCasilla s: pieza.movimientos)
			{
				a.movimientos.add(tablerete[s.gety()][s.gety()]);
			}
			return (a);
		}
		if(pieza instanceof clsAlfil)
		{
			clsAlfil a=new clsAlfil(pieza.getY(),pieza.getX(),pieza.getColor(),false);
			for(clsCasilla s: pieza.movimientos)
			{
				a.movimientos.add(tablerete[s.gety()][s.gety()]);
			}
			return (a);
		}
		if(pieza instanceof clsRey)
		{
			clsRey a=new clsRey(pieza.getY(),pieza.getX(),pieza.getColor(),false);
			for(clsCasilla s: pieza.movimientos)
			{
				a.movimientos.add(tablerete[s.gety()][s.gety()]);
			}
			return (a);
		}
		if(pieza instanceof clsReina)
		{
			clsReina a=new clsReina(pieza.getY(),pieza.getX(),pieza.getColor(),false);
			for(clsCasilla s: pieza.movimientos)
			{
				a.movimientos.add(tablerete[s.gety()][s.gety()]);
			}
			return (a);
		}	
		return null;
	}
	
	/**
	 * Implementación de hashCode() usando números primos para evitar colisiones a la hora de generar piezas.
	 */
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/**
	 * Implementación del método equals para determinar cuáles son los atributos distintivos de una pieza.
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		clsPieza other = (clsPieza) obj;
		if (a != other.a)
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
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

	public piezas getA() 
	{
		return a;
	}

	public void setA(piezas a) 
	{
		this.a = a;
	}
	
	public LinkedList<clsCasilla> influencia(clsCasilla[][] tablero)
	{
		return movimientos;
	}
	
	public LinkedList<clsCasilla> getMovimientos() 
	{
		return movimientos;
	}

	public void setMovimientos(LinkedList<clsCasilla> movimientos) 
	{
		this.movimientos = movimientos;
	}

	public LinkedList<clsCasilla> getInfluencia() 
	{
		return influencia;
	}

	public void setInfluencia(LinkedList<clsCasilla> influencia) 
	{
		this.influencia = influencia;
	}

	public Boolean getPrimera() 
	{
		return primera;
	}

	public void setPrimera(Boolean primera) 
	{
		this.primera = primera;
	}
	
	public int getValor() 
	{
		return valor;
	}

	public void setValor(int valor) 
	{
		this.valor = valor;
	}
}