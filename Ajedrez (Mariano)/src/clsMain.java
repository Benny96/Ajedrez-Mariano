import java.awt.EventQueue;

import GUI.clsPaginaPrincipal;
import Persistencia.clsBD;

/**
 * Inicio del programa, que llevará a cabo el hilo de ejecución main, del que colgará el resto.  
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsMain 
{
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					clsBD.initBD( "src/Data/mariano.bd" );
					clsPaginaPrincipal p=new clsPaginaPrincipal();
					p.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}