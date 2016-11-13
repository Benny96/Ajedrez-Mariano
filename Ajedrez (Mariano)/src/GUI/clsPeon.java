package GUI;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class clsPeon extends clsPieza
{

	public clsPeon(int x, int y, Boolean color) {
		super(x, y, color);
		// TODO Auto-generated constructor stub
		this.a=Comun.clsConstantes.piezas.Peon;
		
		if(color)
		{
			try {
				Image img = ImageIO.read(getClass().getResource("/img/peon_blanco.png"));
				this.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				Image img = ImageIO.read(getClass().getResource("/img/peon_negro.png"));
				this.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void mov(clsCasilla[][] tablero)
	{
		if(this.getColor())
		{
		
		if(this.y!=1)
		{
			if(tablero[this.y+1][this.getX()].getOcupado()==null)
				this.movimientos.add(tablero[this.y+1][this.getX()]);
		}
		else
		{
			if(tablero[this.y+1][this.getX()].getOcupado()==null)
			{
				this.movimientos.add(tablero[this.y+1][this.getX()]);
			if(tablero[this.y+2][this.getX()].getOcupado()==null)
				this.movimientos.add(tablero[this.y+2][this.getX()]);
			}
		}
			if(this.y+1<8 && this.getX()+1<8 && tablero[this.y+1][this.getX()+1].getOcupado()!=null && tablero[this.y+1][this.getX()+1].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[this.y+1][this.getX()+1]);
			}
			if(this.y+1<8 && this.getX()-1>-1 && tablero[this.y+1][this.getX()-1].getOcupado()!=null && tablero[this.y+1][this.getX()-1].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[this.y+1][this.getX()-1]);
			}
			
		}
		else
		{
			if(this.y!=6)
			{
				if(tablero[this.y-1][this.getX()].getOcupado()==null)
					this.movimientos.add(tablero[this.y-1][this.getX()]);
				
			}
			else
			{
				
				if(tablero[this.y-1][this.getX()].getOcupado()==null)
				{
					this.movimientos.add(tablero[this.y-1][this.getX()]);
				if(tablero[this.y-2][this.getX()].getOcupado()==null)
					this.movimientos.add(tablero[this.y-2][this.getX()]);
				}
			}
			if(this.y-1>-1 && this.getX()+1<8 && tablero[this.y-1][this.getX()+1].getOcupado()!=null && tablero[this.y-1][this.getX()+1].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[this.y-1][this.getX()+1]);
			}
			if(this.y-1>-1 && this.getX()-1>-1 && tablero[this.y-1][this.getX()-1].getOcupado()!=null && tablero[this.y-1][this.getX()-1].getOcupado().getColor().equals(this.getColor()==false))
			{
				this.movimientos.add(tablero[this.y-1][this.getX()-1]);
			}
		}
	}

}
