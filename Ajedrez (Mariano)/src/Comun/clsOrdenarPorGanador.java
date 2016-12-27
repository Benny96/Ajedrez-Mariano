package Comun;

import java.util.Comparator;
import GUI.tablerologico;

public class clsOrdenarPorGanador implements Comparator <tablerologico>
{
	@Override
	public int compare(tablerologico arg0, tablerologico arg1) 
	{
		return arg0.getGanador().compareTo(arg1.getGanador());
	}
}
