package src;
import java.awt.EventQueue;

import GUI.clsPaginaPrincipal;
import LN.clsUsuario;
import Persistencia.clsBD;
import Unopauno.TableroVisual1v1;


public class clsMain {


	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					clsBD.initBD( "src/Data/mariano.bd" );
					TableroVisual1v1 a= new TableroVisual1v1(new clsUsuario("w","w","w","w","w"), new clsUsuario("q","q","q","q","q"));
					a.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});	
		
	}
}