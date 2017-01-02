package GUI;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import LN.clsUsuario;




public class ProgressBar extends JFrame{

	MiRunnable miHilo = null; 
	JProgressBar progressBar;
	int v1;
	int v2;
	int v3;
	int v4;
	int v5;
	
	int e1;
	int e2;
	int e3;
	int e4;
	int e5;
	clsUsuario usu;

	public ProgressBar(String titulo, clsUsuario aux) {
		
		usu=aux;
		setBounds(450, 300, 400, 140);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		setTitle(titulo);
		progressBar = new JProgressBar();
		progressBar.setBounds(50, 30, 300, 35);
		getContentPane().add(progressBar);
		
		Random rnd_v=new Random();
		v1=rnd_v.nextInt(100);
		v2=rnd_v.nextInt(100);
		v3=rnd_v.nextInt(100);
		v4=rnd_v.nextInt(100);
		v5=rnd_v.nextInt(100);

		e1=rnd_v.nextInt(400);
		e2=rnd_v.nextInt(400);
		e3=rnd_v.nextInt(400);
		e4=rnd_v.nextInt(400);
		e5=rnd_v.nextInt(400);

		progressBar.setBackground(Color.black);
		progressBar.setForeground(Color.blue);
		
		
		miHilo = new MiRunnable();  // Sintaxis de new para clase interna
		Thread nuevoHilo = new Thread(miHilo );
		nuevoHilo.start();
	}
	public void cerrar()
	{
		this.dispose();
		clsPaginaPrincipal.AbrirVentanaEleccion(usu);
			
	}
		
		
		class MiRunnable implements Runnable {
			boolean sigo = true;
			@Override
			public void run() {
				// Bucle principal forever hasta que se pare el juego...
				while (sigo) {
					
					for(int i=0;i<=100;i++)
					{
						progressBar.setStringPainted(true);
						progressBar.setString(i + "%");
						progressBar.setValue(i);
						
						if(i==v1)
							try {
								Thread.sleep(e1);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						if(i==v2)
							try {
								Thread.sleep(e2);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						if(i==v3)
							try {
								Thread.sleep(e3);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						if(i==v4)
							try {
								Thread.sleep(e4);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						if(i==v5)
							try {
								Thread.sleep(e5);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
						try {
							Thread.sleep(15);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					sigo=false;
					cerrar();
					
				}
			}
		
	}
}
