package LN;


public class clsUsuarioRepetido extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private final String mensaje="Lo siento... Ya existe un usuario con ese nickname. Tendrá que introducir una nueva opción";

	public String getMessage()
	{	
		return mensaje;
	}
	
	
	
}