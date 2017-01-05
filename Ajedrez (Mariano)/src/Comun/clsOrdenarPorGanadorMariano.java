package Comun;

import java.util.Comparator;
import Mariano.TableroLogicoMariano;

/**
 * Clase encargada de a�adir un nuevo criterio de ordenaci�n para las partidas entre jugador y Mariano (TableroLogicoMariano), considerando el nickname del ganador.
 * Para ello, implementar� el m�todo compare, de la interfaz Comparator.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Be�at Gald�s (Benny96)
 */
public class clsOrdenarPorGanadorMariano implements Comparator <TableroLogicoMariano>
{
	@Override
	public int compare(TableroLogicoMariano arg0, TableroLogicoMariano arg1) 
	{
		return arg0.getGanadorString().compareTo(arg1.getGanadorString());
	}
}
