package Comun;

import java.util.Comparator;
import Unopauno.TableroLogico1v1;

public class clsOrdenarPorGanador1v1 implements Comparator <TableroLogico1v1>
{
	@Override
	public int compare(TableroLogico1v1 arg0, TableroLogico1v1 arg1) 
	{
		return arg0.getGanadorString().compareTo(arg1.getGanadorString());
	}
}
