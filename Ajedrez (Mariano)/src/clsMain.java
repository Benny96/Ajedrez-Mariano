import java.awt.EventQueue;

import GUI.clsPaginaPrincipal;
import Mariano.tablerovisual1;
import Persistencia.clsBD;




public class clsMain {

	public static void main(String[] args) 
	{

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					clsBD.initBD( "src/Data/mariano.bd" );
//					clsPaginaPrincipal p=new clsPaginaPrincipal();
//					p.setVisible(true);
					
					tablerovisual1 p=new tablerovisual1();
					p.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}