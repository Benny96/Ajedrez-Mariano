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
import LN.clsUsuario;
import Persistencia.clsBD;
import Persistencia.clsBinarios;

/**
 * Clase creada para generar un objeto nuevo (clsGestor), que valdrá para establecer un vínculo entre la aplicación y sus dos métodos de persistencia: <br>
 * 1) Base de Datos (para usuarios e historiales de partidas). <br>
 * 2) Ficheros serializados (para guardar el estado de la partida).
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */

public class clsGestor implements Serializable 
{
	private static final long serialVersionUID = 1L;

	/**
	 * Método que recoge la lista de usuarios registrados en la Base de Datos.
	 * @return Lista de usuarios registrados
	 */
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
		return lista;	
	}
	
	/**
	 * Envía una serie de atributos para crear un nuevo usuario. En caso de que hubiera alguna repetición de nickname, saltará una excepción que impida
	 * que se lleve a cabo dicho registro.
	 * @param n Nombre del usuario
	 * @param ap1 Primer apellido del usuario
	 * @param ap2 Segundo apellido del usuario
	 * @param nick Nickname del usuario
	 * @param cont Contraseña del usuario
	 * @throws clsUsuarioRepetido Excepción indicando que ha sucedido una repetición de nickname
	 */
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
	
	/**
	 * Método para modificar un usuario existente en la Base de Datos.
	 * @param n Nombre del usuario
	 * @param ap1 Primer apellido del usuario
	 * @param ap2 Segundo apellido del usuario
	 * @param nick Nickname del usuario
	 * @param cont Contraseña del usuario
	 * @param elo Puntuación ELO del usuario
	 * @param fechaalta Fecha de alta del usuario
	 * @return Usuario modificado para actualizar la ventana clsEleccion
	 */
	public clsUsuario ModificarUsuario (String n, String ap1, String ap2, String nick, String cont, int elo, Date fechaalta)
	{
		clsUsuario modificado=new clsUsuario(n, ap1, ap2, nick, cont, elo, fechaalta);
		clsBD.modificarDatoTablaBD(modificado);
		return modificado;
	}
	
	/**
	 * Método para guardar el estado de la partida (1v1) en un fichero serializado.
	 * @param tabaguardar TableroLogico1v1 a guardar
	 */
	public void GuardarPartida(TableroLogico1v1 tabaguardar)
	{	
		clsBinarios objDatos=new clsBinarios();
		objDatos.ResetFile(enFicDatos.FICHERO_PARTIDA);
		objDatos.ComenzarSave(enFicDatos.FICHERO_PARTIDA);
		objDatos.Save(tabaguardar);
		objDatos.TerminarSave();
	}
	
	/**
	 * Método para cargar el estado de la partida (1v1) de un fichero serializado.
	 * @return Estado de la partida a retomar
	 */
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
			tabacargar = (TableroLogico1v1)guardado.get(0);
		}
		objDatos.TerminarRead();
		return tabacargar;
	}
	
	/**
	 * Método para borrar el estado de la partida, en caso de que esta acabe, no se quiera retomar una vez dada la opción o se guarde una nueva partida.
	 */
	public void BorrarPartida()
	{
		clsBinarios objDatos=new clsBinarios();
		objDatos.ResetFile(enFicDatos.FICHERO_PARTIDA);
	}
}
