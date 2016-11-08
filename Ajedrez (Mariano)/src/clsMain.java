import java.awt.EventQueue;

import GUI.clsTablero;




public class clsMain {

	public static void main(String[] args) {
		
		System.out.println("hola");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					clsTablero tab=new clsTablero();

					tab.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

				
			
				
		
		
		
	}

}
