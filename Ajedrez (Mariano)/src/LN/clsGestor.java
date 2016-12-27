package LN;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import Comun.clsConstantes.enFicDatos;
import GUI.tablerovisual;
import LN.clsUsuario;
import Persistencia.clsBinarios;

public class clsGestor implements Serializable 
{
	private static final long serialVersionUID = 1L;

	public ArrayList<clsUsuario> ListaUsuarios()	
	{	
		ArrayList<clsUsuario> lista = new ArrayList <clsUsuario>();
		clsBinarios objDatos = new clsBinarios();
	
		try 
		{
			objDatos.ComenzarRead(enFicDatos.FICHERO_USUARIOS);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		ArrayList<Serializable> listar=new ArrayList<Serializable>();
		listar=objDatos.Read();
		for (Serializable o: listar)
		{
			lista.add((clsUsuario)o);
		}
		objDatos.TerminarRead();

		return lista;

	
	}
	
	
	
	public boolean CrearUsuario(String n, String ap1, String ap2, String nick, String cont) throws clsUsuarioRepetido
	{
		clsUsuario nuevo=new clsUsuario(n, ap1, ap2, nick, cont);
		
		ArrayList<clsUsuario> listausuarios=new ArrayList<clsUsuario>();
		listausuarios=ListaUsuarios();
		
		if(listausuarios.size()!=0)
		{
			HashSet<clsUsuario> set=new HashSet<clsUsuario>();
			set.addAll(listausuarios);
	
			if(set.add(nuevo)==false)
			{
				throw new clsUsuarioRepetido();
			}
		}
		
			clsBinarios objDatos=new clsBinarios();
	
			objDatos.ComenzarSave(enFicDatos.FICHERO_USUARIOS);
			objDatos.Save(nuevo);
			objDatos.TerminarSave();
		

		
	return false;
	
	}
	public void GuardarPartida(tablerovisual tabaguardar)
	{	
		
		clsBinarios objDatos=new clsBinarios();
	
		objDatos.ComenzarSave(enFicDatos.FICHERO_PARTIDA);
		objDatos.Save(tabaguardar);
		objDatos.TerminarSave();
	}
	public tablerovisual CargarPartida()
	{	
		ArrayList <Serializable> guardado = new ArrayList <Serializable>();
		clsBinarios objDatos=new clsBinarios();
		try 
		{
			objDatos.ComenzarRead(enFicDatos.FICHERO_PARTIDA);
		} 
		catch (IOException e) 
		{}
		tablerovisual tabacargar = new tablerovisual();
		guardado = objDatos.Read();
		if (guardado.size()>0)
		{
			tabacargar = (tablerovisual)guardado.get(0);
		}
		objDatos.TerminarSave();
		return tabacargar;
	}
	
}
