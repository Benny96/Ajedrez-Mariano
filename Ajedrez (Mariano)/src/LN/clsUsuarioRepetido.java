package LN;

/**
 * Excepción a aplicar cuando ya haya algún usuario registrado con el mismo nickname.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsUsuarioRepetido extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private final String mensaje = "Ya existe un usuario con ese nickname. Tendrá que introducir una nueva opción.";

	public String getMessage()
	{	
		return mensaje;
	}	
}