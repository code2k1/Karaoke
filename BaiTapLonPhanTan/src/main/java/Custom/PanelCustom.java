package Custom;


import java.awt.*;
import javax.swing.*;

public class PanelCustom extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
		public PanelCustom() {
			setLayout(null);
			setOpaque(false);
			setVisible(false);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.setColor(color);
			g2.fillRoundRect(0,0,getWidth(),getHeight(),30,30);
			g2.fillRect(0,0,getWidth(),getHeight()-20);
			g2.fillRect(0,0,getWidth()-20,getHeight());
		}
}
