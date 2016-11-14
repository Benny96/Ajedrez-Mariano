package GUI;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class clsAlfil extends clsPieza
{
	public clsAlfil(int x, int y, Boolean color) 
	{
		super(x, y, color);
		// TODO Auto-generated constructor stub
		this.a=Comun.clsConstantes.piezas.Alfil;
		if(color)
		{
			try {
				Image img = ImageIO.read(getClass().getResource("/img/Alfil_b.png"));
				this.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				Image img = ImageIO.read(getClass().getResource("/img/Alfil_n.png"));
				this.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
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
		}while(true);
		
		
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
		}while(true);
		
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
		}while(true);
		
		
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
		}while(true);
		
		
	}
}
