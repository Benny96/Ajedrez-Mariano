package Persistencia;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Comun.clsConstantes.enFicDatos;
import Unopauno.TableroLogico1v1;

/**
 * Clase de testeo que hará tests unitarios del funcionamiento de la extracción y guardado de la información en ficheros serializados.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsBinariosTest 
{
	TableroLogico1v1 tablero;
	clsBinarios objDatos;
	
	/**
	 * SetUp de los valores a utilizar.
	 * @throws Exception: Excepción
	 */
	@Before
	public void setUp() throws Exception 
	{
		tablero = new TableroLogico1v1();
		objDatos = new clsBinarios();
	}
	
	/**
	 * Test que guarda un tablero en un nuevo fichero.
	 */
	@Test
	public void guardar_tablero()
	{
		objDatos.ComenzarSave(enFicDatos.FICHERO_PARTIDA_TEST);
		objDatos.Save(tablero);
		objDatos.TerminarSave();
		assertNotNull(new File ("test/Data/partidatest.dat"));
	}
	/**
	 * Test que realiza el cargado del tablero guardado en el fichero. Se ha copiado el método del Gestor aquí y se ha adaptado para guardar un nuevo fichero.
	 */
	@Test
	public void cargar_tablero()
	{
		guardar_tablero();
		ArrayList <Serializable> guardado = new ArrayList <Serializable>();
		clsBinarios objDatos=new clsBinarios();
		try 
		{
			objDatos.ComenzarRead(enFicDatos.FICHERO_PARTIDA_TEST);
		} 
		catch (IOException e) 
		{}
		
		TableroLogico1v1 tabacargar = new TableroLogico1v1(20,"a","b",50,30,"g");
		guardado = objDatos.Read();
		if (guardado.size()>0)
		{
			tabacargar = (TableroLogico1v1)guardado.get(0);
		}
		objDatos.TerminarRead();
		assertEquals(tablero.getID_partida(), tabacargar.getID_partida());
	}

	/**
	 * Test que permite comprobar el borrado del fichero.
	 */
	@Test
	public void borrar_tablero()
	{
		clsBinarios objDatos=new clsBinarios();
		File fic = new File("test/Data/partidatest.dat");
		if (fic.exists()&&fic.isFile())
		objDatos.ResetFile(enFicDatos.FICHERO_PARTIDA_TEST);
		assertFalse(fic.exists());
	}
	
	/**
	 * Método que finaliza el testeo.
	 * @throws Exception: Excepción
	 */
	@After
	public void tearDown() throws Exception 
	{
		tablero = null;
		objDatos = null;
	}
}