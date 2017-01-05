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
 * TableDemo is just like SimpleTableDemo, except that it
 * uses a custom TableModel.
 */
public class clsTablaHistorialMariano extends JPanel {
    
	private static final long serialVersionUID = 1L;
	
	private static DefaultTableCellRenderer rendererCentrado = new DefaultTableCellRenderer();
	static {
		rendererCentrado.setHorizontalAlignment(JLabel.CENTER);
	}
	
    public clsTablaHistorialMariano(ArrayList<TableroLogicoMariano> u) {
    	 super(new GridLayout(1,0)); //TODO: NO quitar esto!
    	  JFrame frame = new JFrame("Rankings");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       

        JTable table = new JTable(new MyTableModelMariano(u)); 
//        table.setPreferredScrollableViewportSize(new Dimension(650, 341));
        table.setFillsViewportHeight(true);
        
        table.getColumn("ID").setCellRenderer(rendererCentrado);
        table.getColumn("Fecha de comienzo").setCellRenderer(rendererCentrado);
        table.getColumn("Fecha de comienzo").setCellRenderer(rendererCentrado);

        table.getColumn("ID").setPreferredWidth(20); //Poner un tamaño prefijado a la columna.


        
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
}

    class MyTableModelMariano extends AbstractTableModel {  //TODO: Tiene que extender de AbstractTableModel 
        private String[] columnNames = {"ID",
                                        "Jug. Blanco",
                                        "Jug. Negro",
                                        "Fecha de comienzo",
                                        "Fecha de final",
                                        "Ganador"};
        private Object[][] data;
        
        public MyTableModelMariano(ArrayList<TableroLogicoMariano> u)
        {
        	
        	super();
        	
    		int filas = u.size();
    		int cont;
    		data=new Object[filas][];
    		cont=0;
    		SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");
    		
    		int pos=1;
    		//Nos recorremos el map para cargar la variable data[][]
    		for (TableroLogicoMariano aux : u)
    		{
    		    //System.out.println(entry.getKey() + "/" + entry.getValue());
    			Object[]n={new Integer(aux.getID_partida()),
    					   new String(aux.getUblanco().getNickname()),	    					
    					   new String(aux.getUnigga().getNickname()),
    					   new String(f.format(aux.getFec_com())),
    					   new String(f.format(aux.getFec_fin())),
    					   new String(aux.getGanadorString())};
    			data[cont]=n;
    			cont++;
    			pos++;
    		}
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
                return false;
        }
        


    }

