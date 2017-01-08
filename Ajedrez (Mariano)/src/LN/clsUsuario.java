package LN;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase creada para generar un objeto nuevo (clsUsuario). Implementa la interfaz Comparable con clsUsuario y Serializable. <br>
 * La ordenación natural se ha hecho mediante los nicknames. Se implementarán de manera externa otros tipos de ordenaciones.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
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
	
	/**
	 * Constructor con parámetros para crear un nuevo usuario.
	 * @param n Nombre del usuario
	 * @param ap1 Primer apellido del usuario
	 * @param ap2 Segundo apellido del usuario
	 * @param nick Nickname del usuario
	 * @param cont Contraseña del usuario
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
	 * Constructor con parámetros para tomar usuarios de la Base de Datos.
	 * @param n Nombre del usuario
	 * @param ap1 Primer apellido del usuario
	 * @param ap2 Segundo apellido del usuario
	 * @param nick Nickname del usuario
	 * @param cont Contraseña del usuario
	 * @param el Puntuación ELO del usuario
	 * @param fecha Fecha de alta del usuario
	 */
	public clsUsuario(String n, String ap1, String ap2, String nick, String cont, int el, Date fecha)
	{
		nombre=n;
		apellido1=ap1;
		apellido2=ap2;
		nickname=nick;
		contraseña=cont;
		elo = el;
		fechadealta=fecha;
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
	
	/**
	 * Implementación de hashCode() para evitar crear colisiones entre usuarios.
	 */
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((nickname == null) ? 0 : nickname.hashCode());
		return result;
	}
	
	/**
	 * Implementación del método equals() para determinar el atributo distintivo (nickname) de un objeto clsUsuario.
	 */
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

	/**
	 * Reimplementación del método toString.
	 */
	public String toString()
	{
		SimpleDateFormat formato = new SimpleDateFormat ("dd/MM/yyyy");
		String e = "Nombre: "+this.getNombre()+" - Apellido 1: "+this.getApellido1()+" - Apellido 2: "+this.getApellido2()+
				" - Nickname: "+this.getNickname()+" - Fecha de registro: "+formato.format(this.getFechadealta())+
				" - Elo: "+this.getElo();
		return e;
	}
	
	/**
	 * Ordenación natural hecha mediante nickname de usuarios.
	 */
	public int compareTo(clsUsuario arg0) 
	{
		return this.getNickname().compareTo(arg0.getNickname());
	}

	public String getNombre() 
	{
		return nombre;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	public String getApellido1()
	{
		return apellido1;
	}
	public void setApellido1(String apellido1) 
	{
		this.apellido1 = apellido1;
	}
	public String getApellido2() 
	{
		return apellido2;
	}
	public void setApellido2(String apellido2) 
	{
		this.apellido2 = apellido2;
	}
	public Date getFechadealta()
	{
		return fechadealta;
	}
	public void setFechadealta(Date fechadealta)
	{
		this.fechadealta = fechadealta;
	}
	public String getNickname() 
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	public String getContraseña()
	{
		return contraseña;
	}
	public void setContraseña(String contraseña)
	{
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
}