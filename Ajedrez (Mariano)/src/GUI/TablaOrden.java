package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import LN.clsUsuario;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Clase que generará un JPanel que contendrá la tabla con los datos extraídos de BD, para poder insertarla después en clsRankingLista.
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class TablaOrden extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	/*Renderer de la tabla*/
	private static DefaultTableCellRenderer rendererCentrado = new DefaultTableCellRenderer();
	static 
	{
		rendererCentrado.setHorizontalAlignment(JLabel.CENTER);
	}

    public TablaOrden(ArrayList<clsUsuario> u) 
    {
    	super(new GridLayout(1,0));
    	JFrame frame = new JFrame("Rankings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        JTable table = new JTable(new MyTableModel(u));
        table.setFillsViewportHeight(true);
        
        table.getColumn("#").setCellRenderer(rendererCentrado);
        table.getColumn("Fecha de alta").setCellRenderer(rendererCentrado);
        table.getColumn("Puntuación (ELO)").setCellRenderer(rendererCentrado);

        table.getColumn("#").setPreferredWidth(20);
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
    
    /**
	 *Clase interna para el manejo del modelo de datos del objeto JTable.
	 *@see <a href="http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TableDemoProject/src/components/TableDemo.java">
	 *http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/TableDemoProject/src/components/TableDemo.java </a>
	 */	
    class MyTableModel extends AbstractTableModel 
    {
		private static final long serialVersionUID = 1L;
		
		private String[] columnNames = {"#",
                                        "Nombre",
                                        "Apellido 1",
                                        "Apellido 2",
                                        "Nickname",
                                        "Fecha de alta", 
                                        "Puntuación (ELO)"};
        private Object[][] data;
        
        /**
         * Constructor del modelo de datos.
         * @param Lista de usuarios.
         */
        public MyTableModel(ArrayList<clsUsuario> u)
        {
        	super();
    		int filas = u.size();
    		int cont;
    		data=new Object[filas][];
    		cont=0;
    		SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");
    		
    		int pos=1;
    		for (clsUsuario aux : u)
    		{
    			Object[]n={new Integer(pos),
    					   new String(aux.getNombre()),	    					
    					   new String(aux.getApellido1()),
    					   new String(aux.getApellido2()),
    					   new String(aux.getNickname()),
    					   new String(f.format(aux.getFechadealta())),
    					   new Integer(aux.getElo())};
    			data[cont]=n;
    			cont++;
    			pos++;
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
}