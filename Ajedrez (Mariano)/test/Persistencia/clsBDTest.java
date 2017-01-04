package Persistencia;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class clsBDTest 
{

	@Before
	public void setUp() throws Exception 
	{
		clsBD.initBD("mariano.bd");
	}
	
	
	
	
	//Debería comprobar si la conexión se ha establecido bien.
	//TODO: Da un Failure, con lo que el aserto está mal planteado.
	@Test
	public void comprobar_inicio_bd() 
	{
		try 
		{
			assertEquals(clsBD.getConnection(), DriverManager.getConnection("jdbc:sqlite:" + "mariano.bd" ));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	@Test
	public void mirar_si_son_diferentes()
	{
	}
	@Test
	public void mirar_si_cambia_el_color()
	{
	
	}
	
	@Test
	public void mirar_si_cambia_la_posicion()
	{
		
	}

//	Este creo que debería dar error pero no da
	//Este test da error por el OutOfBounds.
	@Test
	public void mirar_si_se_salen_del_tablero()
	{
		
	}
	
	@After
	public void tearDown() throws Exception 
	{
		
	}

}
