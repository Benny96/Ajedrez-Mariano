package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Comun.clsConstantes;
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
										"CONTRASENYA STRING NOT NULL,FEC_ALTA DATE NOT NULL,"+ 
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
				statement.executeUpdate("CREATE TABLE PARTIDA (ID_PARTIDA INT NOT NULL PRIMARY KEY,"+
										"DIA_COM DATE NOT NULL, DIA_FIN DATE)");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
		case clsConstantes.JUEGA:
			try 
			{
				statement.executeUpdate("CREATE TABLE JUEGA (ID_PART INT NOT NULL REFERENCES PARTIDA (ID_PARTIDA),"+
										"NICKNAME1 STRING NOT NULL REFERENCES USUARIO (NICKNAME),"+
										"NICKNAME2 STRING NOT NULL REFERENCES USUARIO (NICKNAME) DEFAULT 'MARIANO',"+
										"RELOJMINNICK1 INT CHECK (RELOJMINNICK1 > 0), RELOJSEGNICK1 INT CHECK (RELOJSEGNICK1 > 0),"+
										"RELOJMINNICK2 INT CHECK (RELOJMINNICK2 > 0), RELOJSEGNICK2 INT CHECK (RELOJSEGNICK2 > 0),"+
										"PRIMARY KEY (ID_PART, NICKNAME1, NICKNAME2))");
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
						+ ((clsUsuario)obj).getContraseña()+"',"+((clsUsuario)obj).getFechadealta()+","
						+ ((clsUsuario)obj).getElo()+",'"+((clsUsuario)obj).getNombre()+"','"
						+ ((clsUsuario)obj).getApellido1()+"','"+((clsUsuario)obj).getApellido2()+"'");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
//			break;
//		TODO: Crear clsPartida. if (obj instanceof clsPartida)
			try 
			{
				statement.executeUpdate("CREATE TABLE PARTIDA (ID_PARTIDA INT NOT NULL PRIMARY KEY,"+
										"DIA_COM DATE NOT NULL, DIA_FIN DATE)");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
//			break;
//TODO: 	case (obj instanceof clsJuega)
			try 
			{
				statement.executeUpdate("CREATE TABLE JUEGA (ID_PART INT NOT NULL REFERENCES PARTIDA (ID_PARTIDA),"+
										"NICKNAME1 STRING NOT NULL REFERENCES USUARIO (NICKNAME),"+
										"NICKNAME2 STRING NOT NULL REFERENCES USUARIO (NICKNAME) DEFAULT 'MARIANO',"+
										"RELOJMINNICK1 INT CHECK (RELOJMINNICK1 > 0), RELOJSEGNICK1 INT CHECK (RELOJSEGNICK1 > 0),"+
										"RELOJMINNICK2 INT CHECK (RELOJMINNICK2 > 0), RELOJSEGNICK2 INT CHECK (RELOJSEGNICK2 > 0),"+
										"PRIMARY KEY (ID_PART, NICKNAME1, NICKNAME2))");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
//			break;
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
//			     while(rs.next())
//			     {
//			       // Leer el resultset
//			       System.out.println("name = " + rs.getString("name"));
//			       System.out.println("id = " + rs.getInt("id"));
//			     }
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
		case clsConstantes.JUEGA:
			try 
			{
				 rs = statement.executeQuery("select * from JUEGA");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
		}
		 return rs;
	}
//	 connection = DriverManager.getConnection("jdbc:sqlite:sample.db"); //sample.db es el nombre del archivo de la base de datos. (.db = Extensión de databases)
//     Statement statement = connection.createStatement();
//     statement.setQueryTimeout(30);  // poner timeout 30 msg - Corta la creación de la conexión si tarda más de 30 msg
//
//     statement.executeUpdate("drop table if exists person"); //Para que el ejemplo siempre funcione igual
//     statement.executeUpdate("create table person (id integer, name string)"); //Create if not exists hace que se cree una tabla si no existe previamente
//     statement.executeUpdate("insert into person values(1, 'leo')");
//     statement.executeUpdate("insert into person values(2, 'yui')");
//     ResultSet rs = statement.executeQuery("select * from person");
//     while(rs.next())
//     {
//       // Leer el resultset
//       System.out.println("name = " + rs.getString("name"));
//       System.out.println("id = " + rs.getInt("id"));
//     }

}
