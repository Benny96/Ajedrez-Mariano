package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Comun.clsConstantes;
import Unopauno.TableroLogico1v1;
import LN.clsUsuario;
import Mariano.TableroLogicoMariano;

/**
 * Clase que se encargará de pasar la información de memoria a una Base de Datos, y al mismo tiempo, que extraerá
 * dicha información de esta BD a memoria.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsBD 
{
	private static Connection connection = null;
	private static Statement statement = null;

	/** Inicializa una BD SQLITE y devuelve una conexión con ella. Debe llamarse a este 
	 * método antes que ningún otro, y debe devolver no null para poder seguir trabajando con la BD.
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexión con la base de datos indicada. Si hay algún error, se devuelve null
	 */
	public static Connection initBD( String nombreBD ) 
	{
		try 
		{
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
		    return connection;
		} 
		catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog( null, "Error de conexión!! No se ha podido conectar con " + nombreBD , "ERROR", JOptionPane.ERROR_MESSAGE );
			System.out.println( "Error de conexión!! No se ha podido conectar con " + nombreBD );
			return null;
		}
	}
	
	/** Cierra la conexión con la Base de Datos
	 */
	public static void close() {
		try 
		{
			statement.close();
			connection.close();
		} 
		catch (SQLException e) 
		{
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

	/** Crea diferentes tablas en una base de datos, si no existía ya. <br>
	 * Debe haberse inicializado la conexión correctamente. <br>
	 * @param Tipo de tabla: Usuario o Partida.
	 */
	public static void crearTablaBD(String tipo_tabla) //TODO: Hay sobreescritura de BD aquí. En SQL tendríamos que mirar si ya existe la tabla creada. (Cláusula IF NOT EXISTS)
	{
		if (statement==null) return;
		switch (tipo_tabla)
		{
		case clsConstantes.USUARIO: 
			try 
			{
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS USUARIO (NICKNAME STRING NOT NULL PRIMARY KEY,"+
										"CONTRASENYA STRING NOT NULL, FEC_ALTA INTEGER(64) NOT NULL,"+ 
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
										"DIA_COM INTEGER(64) NOT NULL, DIA_FIN INTEGER(64), GANADOR STRING,"+
										"PRIMARY KEY (ID_PARTIDA, USUARIO1, USUARIO2))");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			break;
		}
	}
	
	/** Inserta un dato en las tablas previamente mencionadas. <br>
	 * Debe haberse inicializado la conexión correctamente. <br>
	 * @param Objeto a insertar: Usuario, partida 1v1 o partida vs Mariano.
	 */
	public static void insertarDatoTablaBD(Object obj)
	{
		if (statement==null) return;
		if (obj instanceof clsUsuario)
			try 
			{
				statement.executeUpdate("INSERT INTO USUARIO VALUES ('"+((clsUsuario)obj).getNickname()+"','"
						+ ((clsUsuario)obj).getContraseña()+"',"+((clsUsuario)obj).getFechadealta().getTime()+","
						+ ((clsUsuario)obj).getElo()+",'"+((clsUsuario)obj).getNombre()+"','"
						+ ((clsUsuario)obj).getApellido1()+"','"+((clsUsuario)obj).getApellido2()+"')");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		if (obj instanceof TableroLogico1v1)
			try 
			{
				statement.executeUpdate("INSERT INTO PARTIDA VALUES ("+ ((TableroLogico1v1)obj).getID_partida()+",'"
						+ ((TableroLogico1v1)obj).getUblanco().getNickname()+"','"+((TableroLogico1v1)obj).getUnigga().getNickname()+"',"
						+((TableroLogico1v1)obj).getFec_com().getTime()+","
						+((TableroLogico1v1)obj).getFec_fin().getTime()+",'"
						+ ((TableroLogico1v1)obj).getGanadorString()+"')");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		if (obj instanceof TableroLogicoMariano)
			try 
			{
				statement.executeUpdate("INSERT INTO PARTIDA VALUES ("+ ((TableroLogicoMariano)obj).getID_partida()+",'"
						+ ((TableroLogicoMariano)obj).getUblanco().getNickname()+"','"+((TableroLogicoMariano)obj).getUnigga().getNickname()+"',"
						+((TableroLogicoMariano)obj).getFec_com().getTime()+","
						+((TableroLogicoMariano)obj).getFec_fin().getTime()+",'"
						+ ((TableroLogicoMariano)obj).getGanadorString()+"')");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
	}
	
	/**
	 * Modifica un dato de una tabla, considerando sus atributos identificativos como base: <br>
	 * Usuario: Nickname. <br>
	 * Partida: ID_Partida.
	 * @param obj Dato a modificar
	 */
	public static void modificarDatoTablaBD(Object obj)
	{
		if (statement==null) return;
		if (obj instanceof clsUsuario)
			try 
			{
				statement.executeUpdate("UPDATE USUARIO SET CONTRASENYA ='"+((clsUsuario)obj).getContraseña()+"',"
						+ "ELO = "+ ((clsUsuario)obj).getElo()+", NOMBRE = '"+((clsUsuario)obj).getNombre()+"',"
						+ "APELLIDO1 = '"+((clsUsuario)obj).getApellido1()+"', APELLIDO2 = '"+((clsUsuario)obj).getApellido2()+"'"
						+ "WHERE NICKNAME = '"+((clsUsuario)obj).getNickname()+"'");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		if (obj instanceof TableroLogico1v1)
			try 
			{
				statement.executeUpdate("UPDATE PARTIDA SET DIA_FIN ="
						+((TableroLogico1v1)obj).getFec_fin().getTime()+","
						+"GANADOR = '"+((TableroLogico1v1)obj).getGanadorString()+"'"
						+ "WHERE ID_PARTIDA = "+((TableroLogico1v1)obj).getID_partida());
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		if (obj instanceof TableroLogicoMariano)
			try 
			{
				statement.executeUpdate("UPDATE PARTIDA SET DIA_FIN ="
						+((TableroLogicoMariano)obj).getFec_fin().getTime()+","
						+"GANADOR = '"+((TableroLogicoMariano)obj).getGanadorString()+"'"
						+ "WHERE ID_PARTIDA = "+((TableroLogicoMariano)obj).getID_partida());
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
	}
	
	/**
	 * Lectura de los datos de una tabla determinada.
	 * @param Tipo de tabla
	 * @return Representación abstracta de los datos de la tabla de la BD: ResultSet
	 */
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