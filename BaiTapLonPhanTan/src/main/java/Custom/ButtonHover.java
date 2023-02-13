package Custom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;

public class ButtonHover extends JButton implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color colorHover = new Color(255,255,255,0);
	private Color colorExit = new Color(255,255,255,30);
	private Color color;
	
	public ButtonHover(String label, Icon icon) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setContentAreaFilled(false);
		setFocusPainted(false);
		setBorder(null);
		setIcon(icon);
		setText(label);
		setForeground(new Color(255,255,255));
		addMouseListener(this);
	}
	public void setColorHover(Color colorHover) {
		this.colorHover = colorHover;
	}

	public void setColorExit(Color colorExit) {
		this.colorExit = colorExit;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(color);
		g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 5, 5);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		color = colorHover;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		color = colorExit;
	}

}
