package LN;

/**
 * Excepci�n a aplicar cuando ya haya alg�n usuario registrado con el mismo nickname.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Be�at Gald�s (Benny96)
 */
public class clsUsuarioRepetido extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private final String mensaje = "Ya existe un usuario con ese nickname. Tendr� que introducir una nueva opci�n.";

	public String getMessage()
	{	
		return mensaje;
	}	
}