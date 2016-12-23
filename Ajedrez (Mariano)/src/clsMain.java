import java.awt.EventQueue;

import GUI.clsPaginaPrincipal;
import Persistencia.clsBD;




public class clsMain {

	public static void main(String[] args) 
	{

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clsBD.initBD( "src/Data/mariano.bd" );
					clsPaginaPrincipal p=new clsPaginaPrincipal();
					p.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}