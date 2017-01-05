package Comun;
/**
 * Clase que contendrá aquellos métodos y atributos que deban ser accesibles para cualquiera de las clases restantes.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsConstantes 
{
	/**
	 * Enumerado que servirá como referencia para los distintos tipos de pieza que se manejen en la aplicación.
	 */
	public enum piezas
	{
		Peon,
		Torre,
		Caballo,
		Alfil,
		Rey,
		Reina
	}
	/**
	 * Enumerado que servirá como referencia para los ficheros .dat que se generen mediante clsDatos (Partidas en el caso normal; Partidas_Test para
	 * los tests unitarios).
	 */
	public enum enFicDatos
	{
		FICHERO_PARTIDA,
		FICHERO_PARTIDA_TEST
	}
	/**
	 * Listado de constantes a aplicar en diferentes fases del programa.
	 */
	public static final String USUARIO = "USUARIO";
	public static final String PARTIDA = "PARTIDA";
}
