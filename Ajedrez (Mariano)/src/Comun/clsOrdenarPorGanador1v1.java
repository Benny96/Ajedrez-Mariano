package Comun;

import java.util.Comparator;
import Unopauno.TableroLogico1v1;

/**
 * Clase encargada de añadir un nuevo criterio de ordenación para las partidas entre jugador y jugador (TableroLogico1v1), considerando el nickname del ganador.
 * Para ello, implementará el método compare, de la interfaz Comparator.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsOrdenarPorGanador1v1 implements Comparator <TableroLogico1v1>
{
	@Override
	public int compare(TableroLogico1v1 arg0, TableroLogico1v1 arg1) 
	{
		return arg0.getGanadorString().compareTo(arg1.getGanadorString());
	}
}
