package LN;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import Comun.clsConstantes;
import Comun.clsConstantes.enFicDatos;
import Unopauno.TableroLogico1v1;
import Unopauno.TableroVisual1v1;
import LN.clsUsuario;
import Mariano.tablerologico1;
import Persistencia.clsBD;
import Persistencia.clsBinarios;

public class clsGestor implements Serializable 
{
	private static final long serialVersionUID = 1L;

	public ArrayList<clsUsuario> ListaUsuarios()	
	{	
		ArrayList<clsUsuario> lista = new ArrayList <clsUsuario>();
		clsBD.crearTablaBD(clsConstantes.USUARIO);
		ResultSet rs = clsBD.obtenerDatosTablaBD (clsConstantes.USUARIO);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					lista.add(new clsUsuario(
							rs.getString("NOMBRE"),
							rs.getString("APELLIDO1"),
							rs.getString("APELLIDO2"),
							rs.getString("NICKNAME"),
							rs.getString("CONTRASENYA"),
							rs.getInt("ELO"),
							new Date(rs.getLong("FEC_ALTA"))));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
//		clsBinarios objDatos = new clsBinarios();
//	
//		try 
//		{
//			objDatos.ComenzarRead(enFicDatos.FICHERO_USUARIOS);
//		} 
//		catch (IOException e) 
//		{
//			e.printStackTrace();
//		}
//		ArrayList<Serializable> listar=new ArrayList<Serializable>();
//		listar=objDatos.Read();
//		for (Serializable o: listar)
//		{
//			lista.add((clsUsuario)o);
//		}
//		objDatos.TerminarRead();

		return lista;

	
	}
	
	
	
	public void CrearUsuario(String n, String ap1, String ap2, String nick, String cont) throws clsUsuarioRepetido
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
		clsBD.insertarDatoTablaBD(nuevo);
	}
	public clsUsuario ModificarUsuario (String n, String ap1, String ap2, String nick, String cont, int elo, Date fechaalta)
	{
		clsUsuario modificado=new clsUsuario(n, ap1, ap2, nick, cont, elo, fechaalta);
		clsBD.modificarDatoTablaBD(modificado);
		return modificado;
	}
	public void GuardarPartida(TableroLogico1v1 tabaguardar)
	{	
		clsBinarios objDatos=new clsBinarios();
		objDatos.ResetFile(enFicDatos.FICHERO_PARTIDA);
		objDatos.ComenzarSave(enFicDatos.FICHERO_PARTIDA);
		objDatos.Save(tabaguardar);
		objDatos.TerminarSave();
	}
	public TableroLogico1v1 CargarPartida()
	{	
		ArrayList <Serializable> guardado = new ArrayList <Serializable>();
		clsBinarios objDatos=new clsBinarios();
		try 
		{
			objDatos.ComenzarRead(enFicDatos.FICHERO_PARTIDA);
		} 
		catch (IOException e) 
		{}
		TableroLogico1v1 tabacargar = new TableroLogico1v1();
		guardado = objDatos.Read();
		if (guardado.size()>0)
		{
			//TODO: VOLVER A ESTA ASIGNACIÓN SI ES CORRECTA ASÍ SIN MÁS O SI HAY QUE IR ATRIBUTO POR ATRIBUTO.
			tabacargar = (TableroLogico1v1)guardado.get(0);
		}
		objDatos.TerminarSave();
		return tabacargar;
	}
	public void BorrarPartida()
	{
		clsBinarios objDatos=new clsBinarios();
		objDatos.ResetFile(enFicDatos.FICHERO_PARTIDA);
	}
}
