package GUI;
import java.awt.Image;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


import Comun.clsConstantes;
import Comun.clsConstantes.piezas;


public class clsRey extends clsPieza
{
	Boolean jaque;
	Boolean primera;
	
	public clsRey(int x, int y, boolean color)
	{
		super(x, y, color);
		this.a=clsConstantes.piezas.Rey;
	
		jaque=false;
		primera=false;
		
		if(color)
		{
			try {
				Image img = ImageIO.read(getClass().getResource("/img/rey_b.png"));
				this.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				Image img = ImageIO.read(getClass().getResource("/img/rey_n.png"));
				this.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	}
	public void mov(clsCasilla[][] tablero)
	{
		LinkedList<clsCasilla> movrey=new LinkedList<clsCasilla>();
	
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
			}catch(ClassCastException e)
			{
				
			}
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
			}catch(ClassCastException e)
			{
				
			}
		}	
	}
}

