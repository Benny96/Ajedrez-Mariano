package Comun;

import java.util.Comparator;
import Mariano.tablerologico1;

public class clsOrdenarPorGanadorMariano implements Comparator <tablerologico1>
{
	@Override
	public int compare(tablerologico1 arg0, tablerologico1 arg1) 
	{
		return arg0.getGanadorString().compareTo(arg1.getGanadorString());
	}
}
