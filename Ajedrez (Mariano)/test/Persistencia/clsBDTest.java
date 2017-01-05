package Persistencia;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Comun.clsConstantes;
import LN.clsUsuario;

public class clsBDTest 
{
	clsUsuario betatester;
	clsUsuario betatester2;
	
	@Before
	public void setUp() throws Exception 
	{
		clsBD.initBD("marianotest.bd");
	}
	
	@Test
	public void comprobar_existencia_bd()
	{
		assertNotNull("marianotest.bd");
	}
	@Test
	public void crear_tabla()
	{
		clsBD.crearTablaBD(clsConstantes.USUARIO);
		ResultSet rs = clsBD.obtenerDatosTablaBD(clsConstantes.USUARIO);
		assertNotNull(rs);
	}

	//NOTA: En el programa, se controla que no haya usuarios repetidos mediante una excepción y un HashSet. Por lo tanto, aunque aquí genere un problema,
	//a partir de la segunda ejecución consecutiva del test, está previsto en la aplicación principal.	
	@Test
	public void actualizar_datos()
	{
		clsBD.crearTablaBD(clsConstantes.USUARIO);
		betatester = new clsUsuario ("Juana","Lopez","Grillete","Juanita66","micontraseña");
		clsBD.insertarDatoTablaBD(betatester);
		betatester = new clsUsuario ("Beñat","Galdós","Aizpurua","Juanita66","micontraseña");
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
	
	@After
	public void tearDown() throws Exception 
	{
		betatester = null;
		betatester2 = null;
	}

}
