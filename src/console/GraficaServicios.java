package console;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import serviciosPack.Servicios;

public class GraficaServicios {

    public GraficaServicios() {
        // Crear y mostrar el JFrame
        JFrame frame = new JFrame("Diagrama de Barras");
        frame.setPreferredSize(new Dimension(800, 480));
        frame.setContentPane(createChartPanel());
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static ChartPanel createChartPanel() {
        // Crear el dataset y el gráfico
        DefaultCategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        // Crear un panel de gráfico y agregarlo al JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

    public static DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        // vamos a llenar el dataset
        HashMap<String, Integer> dataSetAUsar = Servicios.dataForGraph();
        for (java.util.Map.Entry<String, Integer> entry : dataSetAUsar.entrySet()) {
            String nombreProducto = entry.getKey();
            Integer precio = entry.getValue();
            dataset.addValue(precio, "Precio", nombreProducto);
        }
        return dataset;
    }

    public static JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Valor Total por Producto/Servicios", // Título de la gráfica
                "Producto/Servicio", // Etiqueta del eje x
                "Precio", // Etiqueta del eje y
                dataset, // Datos
                PlotOrientation.VERTICAL, // Orientación del gráfico
                true, // Incluir leyenda
                true, // Incluir tooltips
                false // Incluir URLs
        );

        CategoryPlot plot = chart.getCategoryPlot();

        // Configurar el renderizador de las barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        renderer.setSeriesPaint(1, Color.red);

        // Configurar el eje y
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // Configurar el eje x
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryMargin(0.25);
        domainAxis.setUpperMargin(0.05);
        domainAxis.setLowerMargin(0.05);

        return chart;
    }
}
