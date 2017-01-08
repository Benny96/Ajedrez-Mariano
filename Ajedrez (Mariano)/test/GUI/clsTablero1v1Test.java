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

/**
 * Clase de testeo que hará tests unitarios del funcionamiento del tablero lógico (1v1).
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsTablero1v1Test 
{
	private TableroLogico1v1 tl;
	private clsCasilla[][] tablero;
	clsPieza peon1, peon2, peon3, cab;

	/**
	 * SetUp de los valores a utilizar.
	 * @throws Exception Excepción
	 */
	@Before
	public void setUp() throws Exception 
	{
		peon1 = new clsPeon (1,0,true);
		peon2 = new clsPeon (1,0,false);
		peon3 = new clsPeon (1,1,true);
		cab = new clsCaballo(5,5,false);
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
	
	/**
	 * Test que comprueba la posición de una pieza.
	 */
	@Test
	public void comprobar_posicion() 
	{
		assertEquals(peon1, tl.getPblancas().get(0));
	}
	
	/**
	 * Test que mira si 2 piezas son efectivamente distintas. Para ello se toma la condición de los colores en el equals de clsPieza.
	 */
	@Test
	public void mirar_si_son_diferentes()
	{
		assertNotEquals(peon1, peon2);
		assertNotEquals(peon1, peon3);
		assertNotEquals(peon3, peon2);
		
		clsPieza peon4=new clsTorre(1, 0, true);
		assertNotEquals(peon1, peon4);
	}
	
	/**
	 * Comprueba si cambia el color de las piezas.
	 */
	@Test
	public void mirar_si_cambia_el_color()
	{
		peon1.setColor(false);
		assertEquals(peon1, peon2);
	}
	
	/**
	 * Comprueba si cambia la posición de una pieza correctamente.
	 */
	@Test
	public void mirar_si_cambia_la_posicion()
	{
		peon1.setX(1);
		assertEquals(peon1, peon3);
	}

	/**
	 * Test que comprueba si se puede poner una pieza fuera de lugar.
	 */
	@Test (expected = IndexOutOfBoundsException.class)
	public void mirar_si_se_salen_del_tablero()
	{
		peon1.setY(8);
		tablero[peon1.getY()][peon1.getX()].setOcupado(peon1);
		assertNull(tablero[peon1.getY()][peon1.getX()].getOcupado());
		assertEquals(tablero[peon1.getY()][peon1.getX()].getOcupado(), peon1);
	}
	
	/**
	 * Método que finaliza el testeo.
	 * @throws Exception Excepción
	 */
	@After
	public void tearDown() throws Exception 
	{
		tl.pblancas.clear();
	}
}