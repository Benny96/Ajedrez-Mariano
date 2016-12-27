package GUI;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import Comun.OcupadoException;


public class clsCasilla extends JButton implements Serializable
{
	private static final long serialVersionUID = 1L;
	
		private clsPieza pieza;
		private int x;
		private int y;
		
		public Boolean mov;
		Boolean provisional;
		public clsCasilla(int a,int b)
		{
			super();
			mov=false;
			pieza=null;
			y=a;
			x=b;
			provisional=false;
		}
		public clsCasilla ()
		{}
	//	
		public clsPieza getOcupado() {
			
				return pieza;
		}

		public void setOcupado(clsPieza pieza)
		{
			try
			{
			this.pieza = pieza;
			mov=false;
			this.setIcon(pieza.getIcon());
			pieza.setY(this.y);
			pieza.setX(this.x);
			}catch(NullPointerException e)
			{
				this.pieza = null;
				mov=false;
				this.setIcon(null);
			}
		}
		
		public boolean imov() 
		{
			
			if(this.pieza==null)
			{
			try {
				Image img = ImageIO.read(getClass().getResource("/img/mov.png"));
				mov=true;
				this.setIcon(new ImageIcon(img));
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else
			{
				try {
					throw new OcupadoException();
				} catch (OcupadoException e) {
					// TODO Auto-generated catch block
					
					return false;
				}
			}
//			tablerologico.pintar();
			return true;
		}
		
		public void clear(LinkedList <clsCasilla> borrar)
		{
			for(clsCasilla aux: borrar)
			{
				aux.mov=false;
				aux.setIcon(null);
			}
		}
		
		public int getx()
		{
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int gety() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		
		public clsPieza getPieza() {
			return pieza;
		}
		public void setPieza(clsPieza pieza) {
			this.pieza = pieza;
		}
		
		@Override
		public String toString() {
			return "clsCasilla [y=" + y + ", x=" + x + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			clsCasilla other = (clsCasilla) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	
}