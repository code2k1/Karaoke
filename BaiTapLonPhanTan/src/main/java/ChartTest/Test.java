package ChartTest;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Test extends JFrame{
	
	public Test() {
		this.setUndecorated(true);
		this.setSize(700,500);
		this.setLocationRelativeTo(null);
		
		JComponent blankPlotChart = new BlankPlotChart();
		blankPlotChart.setPreferredSize(new Dimension(700,600));
		
		this.add(blankPlotChart);
		
		
	}
	
	public static void main(String[] args) {
		new Test().setVisible(true);
	}
}
