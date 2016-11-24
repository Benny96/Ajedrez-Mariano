package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import LN.clsUsuario;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

/** 
 * TableDemo is just like SimpleTableDemo, except that it
 * uses a custom TableModel.
 */
public class TablaOrden extends JPanel {
    
	private static DefaultTableCellRenderer rendererCentrado = new DefaultTableCellRenderer();
	static {
		rendererCentrado.setHorizontalAlignment(JLabel.CENTER);
	}

    public TablaOrden(ArrayList<clsUsuario> u) {
    	 super(new GridLayout(1,0)); //TODO: NO quitar esto!
    	  JFrame frame = new JFrame("Rankings");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       

        JTable table = new JTable(new MyTableModel(u)); 
//        table.setPreferredScrollableViewportSize(new Dimension(650, 341));
        table.setFillsViewportHeight(true);
        
        table.getColumn("#").setCellRenderer(rendererCentrado);
        table.getColumn("Fecha de alta").setCellRenderer(rendererCentrado);
        table.getColumn("Puntuación (ELO)").setCellRenderer(rendererCentrado);

        table.getColumn("#").setPreferredWidth(20); //Poner un tamaño prefijado a la columna.


        
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
    

    class MyTableModel extends AbstractTableModel {  //TODO: Tiene que extender de AbstractTableModel 
        private String[] columnNames = {"#",
                                        "Nombre",
                                        "Apellido 1",
                                        "Apellido 2",
                                        "Nickname",
                                        "Fecha de alta", 
                                        "Puntuación (ELO)"};
        private Object[][] data;
        
        public MyTableModel(ArrayList<clsUsuario> u)
        {
        	
        	super();
        	
    		int filas = u.size();
    		int cont;
    		data=new Object[filas][];
    		cont=0;
    		SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");
    		
    		int pos=1;
    		//Nos recorremos el map para cargar la variable data[][]
    		for (clsUsuario aux : u)
    		{
    		    //System.out.println(entry.getKey() + "/" + entry.getValue());
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

//    /**
//     * Create the GUI and show it.  For thread safety,
//     * this method should be invoked from the
//     * event-dispatching thread.
//     */
//    private static void createAndShowGUI() {
//        //Create and set up the window.
//        JFrame frame = new JFrame("Rankings");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create and set up the content pane.
//        ArrayList<clsUsuario>u=new ArrayList<clsUsuario>();;
//        clsUsuario a=new clsUsuario("n", "1", "2", "a", "a");
//        clsUsuario b=new clsUsuario("n", "1", "2", "b", "a");
//        clsUsuario c=new clsUsuario("n", "1", "2", "c", "a");
//        clsUsuario d=new clsUsuario("n", "1", "2", "d", "a");
//        clsUsuario e=new clsUsuario("n", "1", "2", "e", "a");
//        u.add(a);
//        u.add(b);
//        u.add(c);
//        u.add(d);
//        u.add(e);
//        TablaOrden newContentPane = new TablaOrden(u);
//        newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        //Schedule a job for the event-dispatching thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
}