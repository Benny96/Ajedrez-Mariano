package LN;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author Garikoitz.Bereciartua, Imanol.Echeverría, Beñat.Galdós
 * Clase que implementa la interfaz Comparable<clsUsuario> y Serializable. <br>
 * La ordenación natural se ha hecho mediante los nicknames. Se implementarán de
 * manera externa otros tipos de ordenaciones.
 */
public class clsUsuario implements Serializable, Comparable<clsUsuario> 
{
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String nickname;
	private String contraseña;
	private int elo;
	private Date fechadealta;
	

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public Date getFechadealta() {
		return fechadealta;
	}
	public void setFechadealta(Date fechadealta) {
		this.fechadealta = fechadealta;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public int getElo() 
	{
		return elo;
	}
	public void setElo(int elo)
	{
		this.elo = elo;
	}
	
	/**
	 * Constructor con parámetros
	 * @param String n para fijar el nombre del usuario.
	 * @param String ap1 para fijar el primer apellido del usuario.
	 * @param String ap2 para fijar el segundo apellido del usuario.
	 * @param String dni para fijar el DNI del usuario.
	 * @param String nick para fijar el nickname del usuario.
	 * @param String cont para fijar la contraseña del usuario.
	 *
	 */
	public clsUsuario(String n, String ap1, String ap2, String nick, String cont)
	{
		nombre=n;
		apellido1=ap1;
		apellido2=ap2;
		nickname=nick;
		contraseña=cont;
		elo = 1000;
		fechadealta=new Date();
	}
	
	/**
	 * Constructor vacío para poder serializar.
	 */
	public clsUsuario()
	{
		nombre=null;
		apellido1=null;
		apellido2=null;
		nickname=null;
		contraseña=null;
		elo = 0;
		fechadealta=null;		
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
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
		clsUsuario other = (clsUsuario) obj;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		return true;
	}

	public String toString()
	{
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy");
		String e = "Nombre: "+this.getNombre()+" - Apellido 1: "+this.getApellido1()+" - Apellido 2: "+this.getApellido2()+
				" - Nickname: "+this.getNickname()+" - Fecha de registro: "+formato.format(this.getFechadealta())+
				" - Elo: "+this.getElo();
		return e;
	}
	
	public int compareTo(clsUsuario arg0) 
	{
		return this.getNickname().compareTo(arg0.getNickname());
	}
}
