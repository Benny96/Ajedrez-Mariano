package LN;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Clase creada para generar un objeto nuevo (clsCasilla), que extiende de JButton e implementa la interfaz Serializable.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsCasilla extends JButton implements Serializable
{
	private static final long serialVersionUID = 1L;
	
		private clsPieza pieza;
		private int x;
		private int y;
		
		public Boolean mov;
		public Boolean provisional;
		public clsCasilla(int a,int b)
		{
			super();
			mov=false;
			pieza=null;
			y=a;
			x=b;
			provisional=false;
		}
		/**
		 * Constructor vacío para poder serializar clsCasillas.
		 */
		public clsCasilla ()
		{}
		
		/**
		 * Getter para saber si está ocupado
		 * @return clsPieza con la pieza ocupada
		 */
		public clsPieza getOcupado() 
		{
			return pieza;
		}
		/**
		 * Setter que marca una posición como ocupada.
		 * @param pieza Pieza que ocupará la casilla
		 */
		public void setOcupado(clsPieza pieza)
		{
			try
			{
				this.pieza = pieza;
				mov=false;
				this.setIcon(pieza.getIcon());
				pieza.setY(this.y);
				pieza.setX(this.x);
			}
			catch(NullPointerException e)
			{	
				this.pieza = null;
				mov=false;
				this.setIcon(null);
			}
		}
		/**
		 * Genera los círculos con los movimientos posibles.
		 * @return boolean indicando el movimiento
		 */
		public boolean imov() 
		{
			if(this.pieza==null)
			{
				try 
				{
					Image img = ImageIO.read(getClass().getResource("/img/mov.png"));
					mov=true;
					this.setIcon(new ImageIcon(img));
					return true;
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
					throw new Exception();
				} 
				catch (Exception e) 
				{
					return false;
				}
			}
			return true;
		}
		/**
		 * Borra los movimientos marcados por una casilla determinada.
		 * @param borrar Casilla cuyos marcas de movimientos han de ser borradas
		 */
		public void clear(LinkedList <clsCasilla> borrar)
		{
			for(clsCasilla aux: borrar)
			{
				aux.mov=false;
				aux.setIcon(null);
			}
		}
		/**
		 * Reimplementación de hashCode generando así diferencias entre casillas, considerando sus coordenadas X e Y, y la pieza que contiene.
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((pieza == null) ? 0 : pieza.hashCode());
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		/**
		 * Reimplementación del método equals con los atributos mencionados previamente.
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			clsCasilla other = (clsCasilla) obj;
			if (pieza == null) {
				if (other.pieza != null)
					return false;
			} else if (!pieza.equals(other.pieza))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		/**
		 * Reimplementación del método paintComponent para repintar gráficos.
		 */
		@Override
		protected void paintComponent(Graphics g) 
		{
			super.paintComponent(g);		
		}
		public int getx()
		{
			return x;
		}
		public void setX(int x) 
		{
			this.x = x;
		}
		public int gety() 
		{
			return y;
		}
		public void setY(int y) 
		{
			this.y = y;
		}
		public clsPieza getPieza() 
		{
			return pieza;
		}
		public void setPieza(clsPieza pieza) 
		{
			this.pieza = pieza;
		}
		@Override
		public String toString() 
		{
			return "clsCasilla [y=" + y + ", x=" + x + "]";
		}
}