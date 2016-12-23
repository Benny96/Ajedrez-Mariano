package src;
import java.awt.EventQueue;

import GUI.clsPaginaPrincipal;
import GUI.clsTablero;
import GUI.tablerovisual;
import Persistencia.clsBD;


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
					tablerovisual a= new tablerovisual();
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