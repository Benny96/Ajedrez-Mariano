package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Comun.clsConstantes;
import GUI.tablerologico;
import LN.clsUsuario;

public class clsBD 
{

	// ------------------------------------
	// VALIDO PARA CUALQUIER BASE DE DATOS
	// ------------------------------------
	
	private static Connection connection = null;
	private static Statement statement = null;

	/** Inicializa una BD SQLITE y devuelve una conexión con ella. Debe llamarse a este 
	 * método antes que ningún otro, y debe devolver no null para poder seguir trabajando con la BD.
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexión con la base de datos indicada. Si hay algún error, se devuelve null
	 */
	public static Connection initBD( String nombreBD ) {
		try {
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
		    return connection;
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog( null, "Error de conexión!! No se ha podido conectar con " + nombreBD , "ERROR", JOptionPane.ERROR_MESSAGE );
			System.out.println( "Error de conexión!! No se ha podido conectar con " + nombreBD );
			return null;
		}
	}
	
	/** Cierra la conexión con la Base de Datos
	 */
	public static void close() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** Devuelve la conexión si ha sido establecida previamente (#initBD()).
	 * @return	Conexión con la BD, null si no se ha establecido correctamente.
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	/** Devuelve una sentencia para trabajar con la BD,
	 * si la conexión si ha sido establecida previamente (#initBD()).
	 * @return	Sentencia de trabajo con la BD, null si no se ha establecido correctamente.
	 */
	public static Statement getStatement() {
		return statement;
	}

	// ------------------------------------
	// PARTICULAR DEL CATALOGO MULTIMEDIA
	// ------------------------------------
	
	/** Crea una tabla de catálogo multimedia en una base de datos, si no existía ya.
	 * Debe haberse inicializado la conexión correctamente.
	 */
	public static void crearTablaBD(String tipo_tabla) //TODO: Hay sobreescritura de BD aquí. En SQL tendríamos que mirar si ya existe la tabla creada. (Cláusula IF NOT EXISTS)
	{
		if (statement==null) return;
		switch (tipo_tabla)
		{
		case clsConstantes.USUARIO: 
			try 
			{
				statement.executeUpdate("CREATE TABLE USUARIO (NICKNAME STRING NOT NULL PRIMARY KEY,"+
										"CONTRASENYA STRING NOT NULL, FEC_ALTA STRFTIME NOT NULL,"+ 
										"ELO INT DEFAULT 1000, NOMBRE STRING, APELLIDO1 STRING, APELLIDO2 STRING)");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
		case clsConstantes.PARTIDA:
			try 
			{
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS PARTIDA (ID_PARTIDA INT NOT NULL,"+
										"USUARIO1 STRING NOT NULL, USUARIO2 STRING NOT NULL,"+
										"DIA_COM STRFTIME NOT NULL, DIA_FIN STRFTIME, GANADOR STRING,"+
										"PRIMARY KEY (ID_PARTIDA, USUARIO1, USUARIO2))");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
		}
	}
	
	public static void insertarDatoTablaBD(Object obj)
	{
		if (statement==null) return;
		if (obj instanceof clsUsuario)
			try 
			{
				statement.executeUpdate("INSERT INTO USUARIO VALUES ('"+((clsUsuario)obj).getNickname()+"','"
						+ ((clsUsuario)obj).getContraseña()+"', strftime('%d/%m/%Y',"+((clsUsuario)obj).getFechadealta().getTime() / 1000+", 'unixepoch')"+","
						+ ((clsUsuario)obj).getElo()+",'"+((clsUsuario)obj).getNombre()+"','"
						+ ((clsUsuario)obj).getApellido1()+"','"+((clsUsuario)obj).getApellido2()+"')");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		if (obj instanceof tablerologico)
			try 
			{
				//TODO: Espero que no muera por null...
				statement.executeUpdate("INSERT INTO PARTIDA VALUES ("+ ((tablerologico)obj).getID_partida()+",'"
						+ ((tablerologico)obj).getUblanco().getNickname()+"','"+((tablerologico)obj).getUnigga().getNickname()+"',"
						+" strftime('%d/%m/%Y',"+((tablerologico)obj).getFec_com().getTime() / 1000+", 'unixepoch')"+","
						+" strftime('%d/%m/%Y',"+((tablerologico)obj).getFec_fin().getTime() / 1000+", 'unixepoch')"+",'"
						+ ((tablerologico)obj).getGanador()+"')");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
	}
	//TODO: Por simplicidad, voy a hacer 1 UPDATE que modifique todo (Elo inclusive), con lo que cada vez que se cambien los datos de un usuario, se modificará
	//el Elo.
	public static void modificarDatoTablaBD(Object obj)
	{
		if (statement==null) return;
		if (obj instanceof clsUsuario)
			try 
			{
				statement.executeUpdate("UPDATE USUARIO SET CONTRASENYA ='"+((clsUsuario)obj).getContraseña()+"',"
						+ "ELO = "+ ((clsUsuario)obj).getElo()+", NOMBRE = '"+((clsUsuario)obj).getNombre()+"',"
						+ "APELLIDO1 = '"+((clsUsuario)obj).getApellido1()+"', APELLIDO2 = '"+((clsUsuario)obj).getApellido2()+"'"
						+ "WHERE NICKNAME = '"+((clsUsuario)obj).getNickname()+"')");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		if (obj instanceof tablerologico)
			try 
			{
				statement.executeUpdate("UPDATE PARTIDA SET DIA_FIN ="+
						" strftime('%d/%m/%Y',"+((tablerologico)obj).getFec_fin().getTime() / 1000+", 'unixepoch')"+","
						+ "GANADOR = '"+((tablerologico)obj).getGanador()+"'"
						+ "WHERE ID_PARTIDA = "+((tablerologico)obj).getID_partida()+")");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
	}
	public static ResultSet obtenerDatosTablaBD (String tipo_tabla)
	{
		if (statement==null) return null;
		ResultSet rs = null;
		switch (tipo_tabla)
		{
		case clsConstantes.USUARIO: 
			try 
			{
			     rs = statement.executeQuery("select * from USUARIO");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
		case clsConstantes.PARTIDA:
			try 
			{
				 rs = statement.executeQuery("select * from PARTIDA");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
		}
		 return rs;
	}

}
