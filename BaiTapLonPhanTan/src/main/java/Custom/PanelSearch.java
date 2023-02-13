package Custom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;
public class PanelSearch extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelSearch() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		this.setBackground(Color.white);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setFont(new Font("Serif", Font.PLAIN, 19));
	}
	
	public void setData(List<DataSearch> data) {
		this.removeAll();
		for (DataSearch dataSearch : data) {
			SearchItem item = new SearchItem(dataSearch);
			item.setPreferredSize(new Dimension(250,50));
			this.add(item);
		}
		repaint();
		revalidate();
	}
	
	public int getItemSize() {
		return getComponentCount();
	}
	
}
