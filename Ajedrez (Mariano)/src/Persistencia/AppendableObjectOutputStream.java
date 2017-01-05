package Persistencia;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Clase que permitirá al usuario escribir en un fichero información adicional, evitando escribir la cabecera de dicha información.<br>
 * Fuente:<br>
 * @see <a href="http://stackoverflow.com/questions/1194656/appending-to-an-objectoutputstream">http://stackoverflow.com/questions/1194656/appending-to-an-objectoutputstream</a>
 */

public class AppendableObjectOutputStream extends ObjectOutputStream 
{ 
	public AppendableObjectOutputStream(OutputStream out) throws IOException  
 	{	  
  	super(out);	
  	}
	
	/**	  
	 * Sobrescribir el método para que cada vez que se abra un fichero para añadir información, no se escriba la 
	 * cabecera, que genera información heterogénea dentro del fichero, y al intentar leerlo produce error.   
	 * http://stackoverflow.com/questions/1194656/appending-to-an-objectoutputstream	   
	 */
	@Override	 
	protected void writeStreamHeader() throws IOException 
	 {   
	 // do not write a header  
	 }
}
