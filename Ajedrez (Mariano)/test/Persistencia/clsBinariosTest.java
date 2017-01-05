package Persistencia;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Comun.clsConstantes;
import Comun.clsConstantes.enFicDatos;
import LN.clsUsuario;
import Unopauno.TableroLogico1v1;

public class clsBinariosTest 
{
	TableroLogico1v1 tablero;
	clsBinarios objDatos;
	
	@Before
	public void setUp() throws Exception 
	{
		tablero = new TableroLogico1v1();
		objDatos = new clsBinarios();
	}
	
	@Test
	public void guardar_tablero()
	{
		objDatos.ComenzarSave(enFicDatos.FICHERO_PARTIDA_TEST);
		objDatos.Save(tablero);
		objDatos.TerminarSave();
	}
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
//		public TableroLogico1v1 (int a, String b, String c, long d, long e, String f)
		
		TableroLogico1v1 tabacargar = new TableroLogico1v1(20,"a","b",50,30,"g");
		guardado = objDatos.Read();
		if (guardado.size()>0)
		{
			tabacargar = (TableroLogico1v1)guardado.get(0);
		}
		objDatos.TerminarRead();
		assertEquals(tablero.getID_partida(), tabacargar.getID_partida());
	}

	@Test
	public void borrar_tablero()
	{
		clsBinarios objDatos=new clsBinarios();
		File fic = new File("partidatest.dat");
		if (fic.exists()&&fic.isFile())
		objDatos.ResetFile(enFicDatos.FICHERO_PARTIDA_TEST);
		assertFalse(fic.exists());
	}
	
	@After
	public void tearDown() throws Exception 
	{
		tablero = null;
		objDatos = null;
	}


}
