package GUI;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Comun.clsConstantes;
import Comun.clsConstantes.piezas;


public class clsRey extends clsPieza
{
	
	
	public clsRey(int x, int y, boolean color)
	{
		super(x, y, color);
		this.a=clsConstantes.piezas.Rey;
		CargarImagen(color);
		
	}
	
	
	public void CargarImagen(boolean c)
	{
		if(c) //Es decir, si el color es true será blanca la imagen
		{

			try
			{
				Image i = ImageIO.read(getClass().getResource("/img/rey_b.png"));
				this.setIcon(new ImageIcon(i));
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		else
		{
			try
			{
				Image i = ImageIO.read(getClass().getResource("/img/rey_b.png"));
				this.setIcon(new ImageIcon(i));
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	
	
	}
}

