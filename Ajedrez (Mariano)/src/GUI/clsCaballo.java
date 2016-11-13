package GUI;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class clsCaballo extends clsPieza
{
	public clsCaballo(int x, int y, Boolean color) 
	{
		super(x, y, color);
		// TODO Auto-generated constructor stub
		this.a=Comun.clsConstantes.piezas.Caballo;
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

}
