package GUI;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import LN.clsCaballo;
import LN.clsCasilla;
import LN.clsPeon;
import LN.clsPieza;
import LN.clsTorre;
import Unopauno.TableroLogico1v1;
import Unopauno.TableroVisual1v1;

public class clsTablero1v1Test {
	
	private TableroVisual1v1 tv;
	private TableroLogico1v1 tl;

	private clsCasilla[][] tablero;
	
	clsPieza peon1=new clsPeon(1, 0, true);//Blanco
	clsPieza peon2=new clsPeon(1, 0, false);//Negro
	
	clsPieza peon3=new clsPeon(1, 1, true);
	clsPieza cab=new clsCaballo(5, 5, false);

	@Before
	public void setUp() throws Exception 
	{
		tv=new TableroVisual1v1();
		tl=new TableroLogico1v1();
		tl.pblancas.add(peon1);
		tablero = new clsCasilla[8][8];
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
			tablero[i][j]=new clsCasilla(i, j);	
			}
		}
	}
	
	
	
	
	//Este test es realmente malo.
	@Test
	public void comprobar_posicion() 
	{
		assertEquals(peon1, tl.getPblancas().get(0));
	}
	
	
	
	@Test
	public void mirar_si_son_diferentes()
	{
		assertNotEquals(peon1, peon2);//Para hacer esto he añadido una condición de colores en el equals de clsPieza
		assertNotEquals(peon1, peon3);
		assertNotEquals(peon3, peon2);
		
		clsPieza peon4=new clsTorre(1, 0, true); //Lo pongo con los mismos atributos que el peon1, pero es una torre
		assertNotEquals(peon1, peon4);


	}
	@Test
	public void mirar_si_cambia_el_color()
	{
		peon1.setColor(false);
		assertEquals(peon1, peon2);
	}
	
	@Test
	public void mirar_si_cambia_la_posicion()
	{
		peon1.setX(1);
		assertEquals(peon1, peon3);
	}

//	Este creo que debería dar error pero no da
	//Este test da error por el OutOfBounds.
	@Test
	public void mirar_si_se_salen_del_tablero()
	{
		peon1.setY(8);
		tablero[peon1.getY()][peon1.getX()].setOcupado(peon1);
	}
	
	@After
	public void tearDown() throws Exception 
	{
		tl.pblancas.clear();
	}

}