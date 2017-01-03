package LN;

import java.util.Comparator;

public class clsJugada implements Comparator{
	
	public clsCasilla cfinal;
	public clsPieza pieza;
	public int valor;
	
	public clsJugada(clsPieza pieza,clsCasilla cfinal)
	{
		this.cfinal=cfinal;
		this.pieza=pieza;
		valor=0;
	}

	public clsJugada() {
		// TODO Auto-generated constructor stub
		valor=-1000000000;
	}

	@Override
	public String toString() {
		return "clsJugada [cfinal=" + cfinal + ", pieza=" + pieza + ", valor="
				+ valor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cfinal == null) ? 0 : cfinal.hashCode());
		result = prime * result + ((pieza == null) ? 0 : pieza.hashCode());
		result = prime * result + valor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		clsJugada other = (clsJugada) obj;
		if (cfinal == null) {
			if (other.cfinal != null)
				return false;
		} else if (!cfinal.equals(other.cfinal))
			return false;
		if (pieza == null) {
			if (other.pieza != null)
				return false;
		} else if (!pieza.equals(other.pieza))
			return false;
//		if (valor != other.valor)
//			return false;
		return true;
	}

	@Override
	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return 0;
	}



	

}
