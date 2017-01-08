package LN;

/**
 * Clase creada para generar un objeto nuevo (clsJugada), que valdrá para evaluar jugadas.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */

public class clsJugada
{
	public clsCasilla cfinal;
	public clsPieza pieza;
	public int valor;
	
	/**
	 * Constructor de clsJugada recibiendo una pieza y una casilla final.
	 * @param pieza Pieza a guardar
	 * @param cfinal Casilla final
	 */
	public clsJugada(clsPieza pieza,clsCasilla cfinal)
	{
		this.cfinal=cfinal;
		this.pieza=pieza;
		valor=0;
	}

	/**
	 * Constructor vacío.
	 */
	public clsJugada() 
	{
		valor=-1000000000;
	}

	/**
	 * Implementación de toString().
	 */
	@Override
	public String toString() 
	{
		return "clsJugada [cfinal=" + cfinal + ", pieza=" + pieza + ", valor="
				+ valor + "]";
	}

	/**
	 * Implementación de hashCode() para evitar colisiones a la hora de generar jugadas.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cfinal == null) ? 0 : cfinal.hashCode());
		result = prime * result + ((pieza == null) ? 0 : pieza.hashCode());
		result = prime * result + valor;
		return result;
	}
	/**
	 * Implementación de hashCode(), con los atributos distintivos de una jugada a otra.
	 */
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
		return true;
	}
}