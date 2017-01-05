package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import Mariano.TableroLogicoMariano;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Clase que generará un JPanel que contendrá la tabla con los datos extraídos de BD, para poder insertarla después en clsHistorialPartidas.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class clsTablaHistorialMariano extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	/*Renderer de la tabla*/
	private static DefaultTableCellRenderer rendererCentrado = new DefaultTableCellRenderer();
	static 
	{
		rendererCentrado.setHorizontalAlignment(JLabel.CENTER);
	}
	
	/**
	 * Constructor del JPanel.
	 * @param u Lista de tableros lógicos 1vMariano para mostrar los datos deseados de las partidas en la tabla.
	 */
    public clsTablaHistorialMariano(ArrayList<TableroLogicoMariano> u) 
    {
    	super(new GridLayout(1,0));
    	JFrame frame = new JFrame("Rankings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        JTable table = new JTable(new MyTableModelMariano(u)); 
        table.setFillsViewportHeight(true);
        
        table.getColumn("ID").setCellRenderer(rendererCentrado);
        table.getColumn("Fecha de comienzo").setCellRenderer(rendererCentrado);
        table.getColumn("Fecha de comienzo").setCellRenderer(rendererCentrado);

        table.getColumn("ID").setPreferredWidth(20); 
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
	/**
	 *Clase interna para el manejo del modelo de datos del objeto JTable.
	 *@see <a href="http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TableDemoProject/src/components/TableDemo.java">
	 *http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TableDemoProject/src/components/TableDemo.java </a>
	 */	
    class MyTableModelMariano extends AbstractTableModel 
    {  
		private static final long serialVersionUID = 1L;
		
        private String[] columnNames = {"ID",
                                        "Jug. Blanco",
                                        "Jug. Negro",
                                        "Fecha de comienzo",
                                        "Fecha de final",
                                        "Ganador"};
        private Object[][] data;
        
        /**
         * Constructor del modelo de datos.
         * @param u Lista de tableros lógicos 1vMariano (partidas 1vMariano).
         */
        public MyTableModelMariano(ArrayList<TableroLogicoMariano> u)
        {
        	super();
    		int filas = u.size();
    		int cont;
    		data=new Object[filas][];
    		cont=0;
    		SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");
    		for (TableroLogicoMariano aux : u)
    		{
    			Object[]n={new Integer(aux.getID_partida()),
    					   new String(aux.getUblanco().getNickname()),	    					
    					   new String(aux.getUnigga().getNickname()),
    					   new String(f.format(aux.getFec_com())),
    					   new String(f.format(aux.getFec_fin())),
    					   new String(aux.getGanadorString())};
    			data[cont]=n;
    			cont++;
    		}
        }
        
        public int getColumnCount() 
        {
            return columnNames.length;
        }

        public int getRowCount() 
        {
            return data.length;
        }

        public String getColumnName(int col) 
        {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) 
        {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) 
        {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) 
        {
                return false;
        }
    }