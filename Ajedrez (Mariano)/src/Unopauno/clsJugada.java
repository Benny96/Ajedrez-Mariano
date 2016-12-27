package Unopauno;


public class clsJugada {
	
	clsCasilla cfinal;
	clsPieza pieza;
	int valor;
	
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

}
