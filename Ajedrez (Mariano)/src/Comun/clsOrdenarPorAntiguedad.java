package Comun;

import java.util.Comparator;
import LN.clsUsuario;

public class clsOrdenarPorAntiguedad implements Comparator <clsUsuario>
{
	//TODO: Para que esto funcione, el usuario debería requerir un atributo en el que aparezca su fecha de creación.
	@Override
	public int compare(clsUsuario o1, clsUsuario o2)
	{
		return o1.getFechadealta().compareTo(o2.getFechadealta());
	}
}
