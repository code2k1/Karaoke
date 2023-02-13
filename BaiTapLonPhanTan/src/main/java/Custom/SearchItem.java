package Custom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class SearchItem extends JButton implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon imageIcon;
	private JLabel lbText;
	private Color colorHover = new Color(0, 0, 0, 0);
	private boolean isItemCustomer = false;

	public boolean isItemCustomer() {
		return isItemCustomer;
	}

	public void setItemCustomer(boolean isItemCustomer) {
		this.isItemCustomer = isItemCustomer;
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

	public JLabel getLbText() {
		return lbText;
	}

	public void setLbText(JLabel lbText) {
		this.lbText = lbText;
	}

	public SearchItem() {
	}

	public SearchItem(DataSearch data) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setBorder(null);

		lbText = new JLabel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setFont(new Font("Serif", Font.PLAIN, 19));
			}
		};

		if (!isItemCustomer)
			lbText.setBounds(55, 0, 250, 50);
		else
			lbText.setBounds(55, 0, 250, 100);
		lbText.setFont(new Font("Serif", Font.PLAIN, 19));
		this.add(lbText);
		setData(data);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage img = new BufferedImage(getWidth() - 1, getHeight() - 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g2.setColor(colorHover);
		g2.fillRect(0, 0, getWidth(), getHeight());

		Image tem = null;
		if (imageIcon != null)
			tem = imageIcon.getImage();
		if (!isItemCustomer)
			g2.drawImage(tem, 5, 5, 35, 35, this);
		else
			g2.drawImage(tem, 5, 30, 35, 35, this);
		g.drawImage(img, 0, 0, this);
		setFont(new Font("Serif", Font.PLAIN, 19));
		this.addMouseListener(this);
	}

	public void setData(DataSearch data) {
		imageIcon = data.getImageIcon();
		lbText.setText(data.getText());
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
		colorHover = new Color(0, 0, 0, 25);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		colorHover = new Color(0, 0, 0, 0);
	}

}
