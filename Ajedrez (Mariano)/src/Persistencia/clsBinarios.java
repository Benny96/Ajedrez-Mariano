package Persistencia;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import Comun.clsConstantes.enFicDatos;



public class clsBinarios implements itfDatos {

	
	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	AppendableObjectOutputStream aos;
	
	
	/**
	 * Método estático privado que devuelve un String indicando la dirección del fichero
	 * @param fichero
	 * @return String
	 */
	private static String setFichero(enFicDatos fichero)
	{
		
		switch(fichero)
		{
		case FICHERO_PARTIDA: return "src/Data/partida.dat";
		case FICHERO_PARTIDA_TEST: return "partidatest.dat";
		}
		
		
		return null;
		
	}
	
	
	
	/**
	 * 
	 * @param fichero: 	enumerado de la clase clsConstantes que indica el fichero del que se
	 * 					va a escribir.
	 * Método que debe crear un objectOutputStream o un AppendableObjectOutputStream para proceder
	 * a la escritura del fichero. Si el fichero existe,AppendableObjectOutputStream; de lo 
	 * contrario objectOutputStream
	 */
	public void ComenzarSave(enFicDatos fichero)
	{
		
		String ruta=setFichero(fichero);
		File fic=new File(ruta);
		
		
	
			if(fic.exists()==true)
			{
				
				try
				{
					aos=new AppendableObjectOutputStream(new FileOutputStream(fic,true));
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
		
			}
			
			else
			{
				try
				{
					fic.createNewFile();
					oos=new ObjectOutputStream(new FileOutputStream(fic));
				}
				catch(FileNotFoundException e)
				{
					e.printStackTrace();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			

		
	}
	

	
	/**
	 * @param o: Objeto a guardar, que debe implementar la interfaz serializable.
	 * Método que guarda en el fichero indicado previamente el objeto recibido.
	 */
	public void Save(Serializable o)
	{
		try
		{
			if (oos!=null)
			{
				oos.writeObject(o);
			}
			else
			{
				if(aos!=null)
				{
					aos.writeObject(o);
				}
			}
		}
		
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que debe cerrar el fichero en el que se ha escrito.
	 */
	public void TerminarSave()
	{
		
		try
		{
			if(oos!=null)
			{
				oos.close();
			}
			if(aos!=null) 
			{
				aos.close();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}	


	/**
	 * @param fichero: enumerado de la clase clsConstantes que indica el fichero del que se
	 * 					va a leer. 
	 * @throws IOException: excepción lanzada en caso de que se dé un error de lectura/escritura en fichero.
	 * Método que crea un objectInputStream para la lectura del fichero indicado previamente.
	 */		
	public void ComenzarRead(enFicDatos fichero) throws IOException
	{
		String ruta=setFichero(fichero);
		File fic=new File(ruta);
		
		
		if(fic.exists()==true)
		{
			
			try
			{
				ois=new ObjectInputStream(new FileInputStream(fic));
			}
			
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	/**
	 * 
	 * @return LinkedList<Serializable>: Devuelve una LinkedList con los datos leídos, en el tipo
	 * Serializable.
	 */
	public ArrayList<Serializable> Read()
	{
		ArrayList<Serializable>lista=new ArrayList<Serializable>();
		Serializable o=null;
		try
		{
			while((o=(Serializable)ois.readObject())!=null)
			{
				lista.add(o);	
			}
		}
		catch(NullPointerException e)
		{
			
		}
		catch(EOFException e)
		{
			//Normal al acabar de leer el fichero.
			//e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e)
		{
			
			e.printStackTrace();
		}  

		
		return lista;
	}
	
	
	
	/**
	 * Método que cierra el fichero del que se ha leído.
	 */
	public void TerminarRead()
	{
		try
		{
			if(ois!=null)
			{
				ois.close();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * @param fichero enumerado de la clase clsConstantes que indica el fichero que se
	 * 					va a borrar.
	 * Se borra el fichero (porque vamos a escribir datos modificados).
	 */
	public void ResetFile(enFicDatos fichero)
	{
		String ruta=setFichero(fichero);
		
		File f=new File(ruta);
		f.delete();
	}



}