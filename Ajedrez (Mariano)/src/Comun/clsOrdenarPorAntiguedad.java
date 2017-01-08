package Comun;

import java.util.Comparator;
import LN.clsUsuario;

/**
 * Clase encargada de a�adir un nuevo criterio de ordenaci�n para los usuarios (clsUsuario), considerando la fecha de alta.
 * Para ello, implementar� el m�todo compare, de la interfaz Comparator.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Be�at Gald�s (Benny96)
 */
public class clsOrdenarPorAntiguedad implements Comparator <clsUsuario>
{
	@Override
	public int compare(clsUsuario o1, clsUsuario o2)
	{
		return o1.getFechadealta().compareTo(o2.getFechadealta());
	}
}