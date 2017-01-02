package Comun;

import java.util.Comparator;

import LN.clsUsuario;

public class clsOrdenarPorElo implements Comparator <clsUsuario>
{
	//TODO: Añadiría una puntuación a los usuarios para poder clasificarlos en Ranking.
	@Override
	public int compare(clsUsuario o1, clsUsuario o2) 
	{
		return o2.getElo()-o1.getElo();
	}

}
