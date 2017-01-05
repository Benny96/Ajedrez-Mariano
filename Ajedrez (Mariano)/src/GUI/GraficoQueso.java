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
* --------------------
* PieChart3DDemo4.java
* --------------------
* (C) Copyright 2004, by Object Refinery Limited and Contributors.
*
* Original Author:  David Gilbert (for Object Refinery Limited);
* Contributor(s):   -;
*
* $Id: PieChart3DDemo4.java,v 1.3 2004/04/26 19:12:00 taqua Exp $
*
* Changes
* -------
* 31-Mar-2004 : Version 1 (DG);
*
*/

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import Comun.clsConstantes;
import Mariano.TableroLogicoMariano;
import Persistencia.clsBD;

/**
 * Clase que generará una JFrame para mostrar los resultados de las partidas contra Mariano en forma de gráfico. Fuente:
 * @see <a href="http://www.jfree.org/jfreechart/">http://www.jfree.org/jfreechart/ </a>
 * @author Garikoitz Bereciartua (garibere13), Imanol Echeverria (Echever), Beñat Galdós (Benny96)
 */
public class GraficoQueso extends JFrame 
{
	private ArrayList <TableroLogicoMariano> listaPartidasMariano;
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase que creará los objetos visuales de la JFrame, para mostrar un gráfico de tarta.
	 * @param title Título de la JFrame
	 */
	public GraficoQueso(final String title) 
	{
     super(title);
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     this.setIconImage(new ImageIcon(getClass().getResource("/img/Rajoy.png")).getImage());
     final PieDataset dataset = createSampleDataset();
     final JFreeChart chart = createChart(dataset);
     final ChartPanel chartPanel = new ChartPanel(chart);
     chartPanel.setPreferredSize(new Dimension(750, 375));
     setContentPane(chartPanel);
 }
 
	/**
     * Crea un modelo de datos para formar el gráfico en forma de queso.
     * @return Modelo de datos
     */
	private PieDataset createSampleDataset() 
	{ 
		final DefaultPieDataset result = new DefaultPieDataset();
		listaPartidasMariano = new ArrayList <TableroLogicoMariano>();
		ResultSet rs = clsBD.obtenerDatosTablaBD (clsConstantes.PARTIDA);
		if (rs != null)
		{
			try 
			{
				while (rs.next())
				{
					listaPartidasMariano.add(new TableroLogicoMariano(
							rs.getInt("ID_PARTIDA"),
							rs.getString("USUARIO1"),
							rs.getString("USUARIO2"),
							rs.getLong("DIA_COM"),
							rs.getLong("DIA_FIN"),
							rs.getString("GANADOR")));
				}
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		int numvictorias = 0;
		int inconclusas = 0;
		for (int i = 0; i<listaPartidasMariano.size(); i++)
		{
			if (listaPartidasMariano.get(i).getGanadorString().compareTo("Mariano")==0)
			{
				numvictorias++;
			}
			else if (listaPartidasMariano.get(i).getGanadorString().compareTo("")==0)
			{
				inconclusas++;
			}
		}
		double propvictMariano = numvictorias * 100 / listaPartidasMariano.size();
	    result.setValue("Mariano", propvictMariano);
	    result.setValue("Jugadores", 100 - propvictMariano - inconclusas);
	    result.setValue("Partidas inconclusas", inconclusas);
	    return result;
 }
 
 // ****************************************************************************
 // * JFREECHART DEVELOPER GUIDE                                               *
 // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
 // * to purchase from Object Refinery Limited:                                *
 // *                                                                          *
 // * http://www.object-refinery.com/jfreechart/guide.html                     *
 // *                                                                          *
 // * Sales are used to provide funding for the JFreeChart project - please    * 
 // * support us so that we can continue developing free software.             *
 // ****************************************************************************
 
    /**
     * Crea el gráfico visual.
     * @param dataset El modelo de datos
     * @return El gráfico
     */
	private JFreeChart createChart(final PieDataset dataset) 
	{ 
	 final JFreeChart chart = ChartFactory.createPieChart3D(
         "Nº de victorias vs. Mariano (%)",  // chart title
         dataset,                // data
         true,                   // include legend
         true,
         false
     );
     final PiePlot3D plot = (PiePlot3D) chart.getPlot();
     plot.setStartAngle(290);
     plot.setDirection(Rotation.CLOCKWISE);
     plot.setForegroundAlpha(0.5f);
     plot.setNoDataMessage("No hay datos que mostrar");
     return chart;
	}
}

