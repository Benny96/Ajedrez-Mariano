package GUI;

/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 * [Java is a trademark or registered trademark of Sun Microsystems, Inc. 
 * in the United States and other countries.]
 *
 * -------------------
 * LineChartDemo6.java
 * -------------------
 * (C) Copyright 2004, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * $Id: LineChartDemo6.java,v 1.5 2004/04/26 19:11:55 taqua Exp $
 *
 * Changes
 * -------
 * 27-Jan-2004 : Version 1 (DG);
 * 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import Comun.clsConstantes;
import Mariano.tablerologico1;
import Persistencia.clsBD;
import Unopauno.TableroLogico1v1;

public class GraficoHistograma extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private ArrayList <TableroLogico1v1> listaPartidas1v1;
	private ArrayList <tablerologico1> listaPartidasMariano;
	
    /* @param title  the frame title.
    */
   public GraficoHistograma(String title) {

       super(title);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       this.setIconImage(new ImageIcon(getClass().getResource("/img/Rajoy.png")).getImage());
       final CategoryDataset dataset = createDataset();
       final JFreeChart chart = createChart(dataset);
       final ChartPanel chartPanel = new ChartPanel(chart);
       chartPanel.setPreferredSize(new Dimension(750, 375));
       setContentPane(chartPanel);

   }
    private CategoryDataset createDataset() 
    {
    	listaPartidas1v1 = new ArrayList <TableroLogico1v1>();
		listaPartidasMariano = new ArrayList <tablerologico1>();
		ResultSet rs = clsBD.obtenerDatosTablaBD (clsConstantes.PARTIDA);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					if (rs.getString("USUARIO2").compareTo("Mariano")!=0)
					{
						listaPartidas1v1.add(new TableroLogico1v1(
								rs.getInt("ID_PARTIDA"),
								rs.getString("USUARIO1"),
								rs.getString("USUARIO2"),
								rs.getLong("DIA_COM"),
								rs.getLong("DIA_FIN"),
								rs.getString("GANADOR")));
					}
					else
					{
						listaPartidasMariano.add(new tablerologico1(
								rs.getInt("ID_PARTIDA"),
								rs.getString("USUARIO1"),
								rs.getString("USUARIO2"),
								rs.getLong("DIA_COM"),
								rs.getLong("DIA_FIN"),
								rs.getString("GANADOR")));
					}
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		    
		    Calendar cal = Calendar.getInstance();
		    int dia = 0;
		    int cantidades [] = new int [7];
		    for (TableroLogico1v1 aux: listaPartidas1v1)
		    {
		    	cal.setTime(aux.getFec_com());
		    	dia = cal.get(Calendar.DAY_OF_WEEK);
		    	cantidades[dia-1]++;
		    }
		    
		    int cantidades2 [] = new int [7];
		    for (tablerologico1 aux: listaPartidasMariano)
		    {
		    	cal.setTime(aux.getFec_com());
		    	dia = cal.get(Calendar.DAY_OF_WEEK);
		    	cantidades2[dia-1]++;
		    }

		 // row keys...
	        final String series1 = "Partidas entre jugadores";
	        final String series2 = "Partidas contra Mariano";

	        // column keys...
	        
	        String[] categorias = new String [7];
	        categorias[0] = "Lunes";
	        categorias[1] = "Martes";
	        categorias[2] = "Miércoles";
	        categorias[3] = "Jueves";
	        categorias[4] = "Viernes";
	        categorias[5] = "Sábado";
	        categorias[6] = "Domingo";
	        


	        // create the dataset...
	        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        boolean eligelista = true;
	        for (int i = 0; i<2; i++)
	        {
	        	if (i == 1)
	        	{
	        		eligelista = false;
	        	}
	        	for (int j = 0; j<7; j++)
	        	{
	        		if (eligelista)
	        		{
	        			dataset.addValue(cantidades[j], series1, categorias[j]);
	        		}
	        		else
	        		{
	        			dataset.addValue(cantidades2[j], series2, categorias[j]);
	        		}
	        		
	        	}
	        }
	        return dataset;		    
    }

    /* Creates a chart.
    * 
    * @param dataset  the data for the chart.
    * 
    * @return a chart.
    */
 private JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Nº de partidas realizadas por día de la semana",         // chart title
            "Categorías",               // domain axis label
            "Valores",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, Color.lightGray
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
       
   }
}