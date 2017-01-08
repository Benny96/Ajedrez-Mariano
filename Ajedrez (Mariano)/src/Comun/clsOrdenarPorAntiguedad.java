package Comun;

import java.util.Comparator;
import LN.clsUsuario;

/**
 * Clase encargada de añadir un nuevo criterio de ordenación para los usuarios (clsUsuario), considerando la fecha de alta.
 * Para ello, implementará el método compare, de la interfaz Comparator.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsOrdenarPorAntiguedad implements Comparator <clsUsuario>
{
	@Override
	public int compare(clsUsuario o1, clsUsuario o2)
	{
		return o1.getFechadealta().compareTo(o2.getFechadealta());
	}
}