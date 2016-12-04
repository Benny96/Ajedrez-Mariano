package LN;

import java.util.Date;

public class clsPartida 
{
	private int ID_partida;
	private Date fec_com;
	private Date fec_fin;
	
	public clsPartida ()
	{
		
	}
	public int getID_partida() 
	{
		return ID_partida;
	}
	public void setID_partida(int iD_partida) 
	{
		ID_partida = iD_partida;
	}
	public Date getFec_com() 
	{
		return fec_com;
	}
	public void setFec_com(Date fec_com) {
		
		this.fec_com = fec_com;
	}
	public Date getFec_fin() 
	{
		return fec_fin;
	}
	public void setFec_fin(Date fec_fin) 
	{
		this.fec_fin = fec_fin;
	}
	
	
}
