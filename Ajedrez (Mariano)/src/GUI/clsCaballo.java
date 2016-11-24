package GUI;

import java.awt.Image;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class clsCaballo extends clsPieza
{
	public clsCaballo(int x, int y, Boolean color) 
	{
		super(x, y, color);
		// TODO Auto-generated constructor stub
		this.a=Comun.clsConstantes.piezas.Caballo;
		valor=300;
		if(color)
		{
			try {
				Image img = ImageIO.read(getClass().getResource("/img/caballo_b.png"));
				this.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				Image img = ImageIO.read(getClass().getResource("/img/caballo_n.png"));
				this.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public clsCaballo(int x, int y, Boolean color,Boolean na) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
		this.a=Comun.clsConstantes.piezas.Caballo;
		
		valor=300;
	}
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
			t1++;;
			
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
			t1++;;
			
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
