package Comun;

import java.util.Comparator;
import Mariano.TableroLogicoMariano;

/**
 * Clase encargada de añadir un nuevo criterio de ordenación para las partidas entre jugador y Mariano (TableroLogicoMariano), considerando el nickname del ganador.
 * Para ello, implementará el método compare, de la interfaz Comparator.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsOrdenarPorGanadorMariano implements Comparator <TableroLogicoMariano>
{
	@Override
	public int compare(TableroLogicoMariano arg0, TableroLogicoMariano arg1) 
	{
		return arg0.getGanadorString().compareTo(arg1.getGanadorString());
	}
}
