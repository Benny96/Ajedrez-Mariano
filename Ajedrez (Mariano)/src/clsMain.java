import java.awt.EventQueue;

import GUI.clsPaginaPrincipal;

public class clsMain {


	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
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