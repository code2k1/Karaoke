package Custom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ButtonGradient2 extends JButton implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color colorCustom = Color.decode("#0eb289");
	private Color color = Color.white;
	private boolean select = true;

	public Color getColorCustom() {
		return colorCustom;
	}

	public void setColorCustom(Color colorCustom) {
		this.colorCustom = colorCustom;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public ButtonGradient2(Boolean boo) {
		setContentAreaFilled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBorder(new EmptyBorder(10, 20, 10, 20));
		setText("");
		setFocusPainted(false);
		addMouseListener(this);
		select = boo;
		if (!isSelect()) {
			color = colorCustom;
			setForeground(Color.white);
		} else {
			setForeground(colorCustom);
		}
	}

	@Override
	protected void paintComponent(Graphics grphcs) {
		int width = getWidth();
		int height = getHeight();
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(color);
		g2.fillRoundRect(0, 0, width, height - 1, height, height);
		g2.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2 + 1);
		grphcs.drawImage(img, 0, 0, null);
		super.paintComponent(grphcs);
	}

	public void setStatus(boolean b) {
		if (b) {
			color = Color.white;
			setForeground(colorCustom);
		} else {
			color = colorCustom;
			setForeground(Color.white);
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		if(select) {
//			color = Color.white;
//			setForeground(colorCustom);		
//		}else {
//			color=colorCustom;
//			setForeground(Color.white);	
//		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}