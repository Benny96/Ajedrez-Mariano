package Comun;

import java.util.Comparator;
import Mariano.TableroLogicoMariano;

public class clsOrdenarPorGanadorMariano implements Comparator <TableroLogicoMariano>
{
	@Override
	public int compare(TableroLogicoMariano arg0, TableroLogicoMariano arg1) 
	{
		return arg0.getGanadorString().compareTo(arg1.getGanadorString());
	}
}
