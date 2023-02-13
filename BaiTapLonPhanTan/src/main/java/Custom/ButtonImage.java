package Custom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonImage extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5913981442937238656L;
	private ImageIcon imageIcon = null;
	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

	public ButtonImage(String value) {
		this.value = value;
		setForeground(Color.decode("#0eb289"));
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setText(this.value);
		setFocusPainted(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage img = new BufferedImage(getWidth() - 1, getHeight() - 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setColor(new Color(70, 70, 70, 30));
		g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
		Image tem = null;
		if (imageIcon != null) {
			tem = imageIcon.getImage();
		}
		g2.drawImage(tem, 0, 0, 220, 220, this);
		g.drawImage(img, 0, 0, this);
		setFont(new Font("Serif", Font.PLAIN, 24));
	}

}
