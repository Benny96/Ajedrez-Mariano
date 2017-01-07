package Persistencia;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sqlite.SQLiteException;

import Comun.clsConstantes;
import LN.clsUsuario;

/**
 * Clase de testeo que har� tests unitarios del funcionamiento de la Base de Datos.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Be�at Gald�s (Benny96)
 */
public class clsBDTest 
{
	clsUsuario betatester;
	clsUsuario betatester2;
	
	/**
	 * SetUp de los valores a utilizar.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		clsBD.initBD("test/Data/marianotest.bd");
	}
	
	/**
	 * Test que comprobar� si realmente existe una base de datos (inicializada en el setUp).
	 */
	@Test
	public void comprobar_existencia_bd()
	{
		assertNotNull("test/Data/marianotest.bd");
	}
	/**
	 * Test que comprobar� la generaci�n de tablas.
	 */
	@Test
	public void crear_tabla()
	{
		clsBD.crearTablaBD(clsConstantes.USUARIO);
		ResultSet rs = clsBD.obtenerDatosTablaBD(clsConstantes.USUARIO);
		assertNotNull(rs);
	}

	/**
	 * Testeo del funcionamiento correcto de una actualizaci�n de BD. Testeamos 3 cosas internamente: <br>
	 * 1) Insertar datos. <br>
	 * 2) Cargar datos. <br>
	 * 3) Actualizar datos. <br>
	 * NOTA: En el programa, se controla que no haya usuarios repetidos mediante una excepci�n y un HashSet. Por lo tanto, se considera esta excepci�n en el test, as�
	 * como la excepci�n relativa a SQLite ya que aqu� no utilizamos el HashSet.
	 */
	@Test (expected = SQLiteException.class)
	public void actualizar_datos()
	{
		clsBD.crearTablaBD(clsConstantes.USUARIO);
		betatester = new clsUsuario ("Juana","Lopez","Grillete","Juanita66","micontrase�a");
		clsBD.insertarDatoTablaBD(betatester);
		betatester = new clsUsuario ("Be�at","Gald�s","Aizpurua","Juanita66","micontrase�a");
		clsBD.modificarDatoTablaBD(betatester);
		ResultSet rs = clsBD.obtenerDatosTablaBD(clsConstantes.USUARIO);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					betatester2 = new clsUsuario(
									rs.getString("NOMBRE"),
									rs.getString("APELLIDO1"),
									rs.getString("APELLIDO2"),
									rs.getString("NICKNAME"),
									rs.getString("CONTRASENYA"),
									rs.getInt("ELO"),
									new Date(rs.getLong("FEC_ALTA")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		assertEquals(betatester, betatester2);
	}
	
	/**
	 * M�todo que finaliza el testeo.
	 * @throws Excepci�n
	 */
	@After
	public void tearDown() throws Exception 
	{
		betatester = null;
		betatester2 = null;
	}
}