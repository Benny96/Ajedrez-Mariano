package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class clsTablero extends JFrame
{
	
	JPanel pPrincipal;
	clsCasilla [][] tablero;
	
	

	public clsTablero() 
	{
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1360, 720);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pPrincipal);
		pPrincipal.setLayout(null);
		
		tablero= new clsCasilla[8][8];

		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				String t="img/torre_b.png";
//				ImageIcon torre=new ImageIcon("img/torre_b.png");
				tablero[i][j]=new clsCasilla(i, j);
				
//				casilla[i][j].setIcon(new ImageIcon(clsTablero.class.getResource("img/torre_b.png")));

				tablero[i][j].setText(Integer.toString(i)+Integer.toString(j));
				if((i+j)%2==0)
				{
					tablero[i][j].setBackground(Color.WHITE);
				}
				else
				{
					tablero[i][j].setBackground(Color.BLACK);
				}				

				tablero[i][j].setBounds(500-j*60, 540-i*60, 60, 60);
//				casilla[i][j].addActionListener(this);
				pPrincipal.add(tablero[i][j]);
				
			}
		}
		
	
	}

		
	
	
	
}
