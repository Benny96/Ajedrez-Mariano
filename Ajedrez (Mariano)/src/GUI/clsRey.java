package GUI;
import java.awt.Image;
import java.io.IOException;

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
				Image img = ImageIO.read(getClass().getResource("/Imagenes/rey_b.png"));
				this.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try {
				Image img = ImageIO.read(getClass().getResource("/Imagenes/rey_n.png"));
				this.setIcon(new ImageIcon(img));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}
}

