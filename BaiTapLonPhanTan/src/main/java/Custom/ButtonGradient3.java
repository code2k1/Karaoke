package Custom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ButtonGradient3 extends JButton implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color colorCustom = Color.decode("#72BD39");
	private Color color = Color.decode("#73D93BC");
	private boolean select = true;
	private boolean isLeft;
	private ImageIcon imageIcon = null;
	
	
	public boolean isLeft() {
		return isLeft;
	}

	public void setRight(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

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
		this.setEnabled(select);
	}

	private boolean activate;
	
	public boolean isActivate() {
		return activate;
	}

	public void setActivate(boolean activate) {
		this.activate = activate;
	}

	public ButtonGradient3(Boolean boo, boolean isLeft) {
		activate = false;
		this.isLeft = isLeft;
		setContentAreaFilled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBorder(new EmptyBorder(0, 40, 0, 0));
		setText("");
		setFocusPainted(false);
		addMouseListener(this);
		select = boo;
		if (!isSelect()) {
			color = colorCustom;
			setForeground(Color.decode("#fffffff"));
		} else {
			setForeground(Color.white);
		}
	}

	@Override
	protected void paintComponent(Graphics grphcs) {
		int width = getWidth();
		int height = getHeight();
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setColor(color);
		g2.fillRoundRect(0, 0, width, height , 30, 30);
		g2.fillRect(0, 0, getWidth(), getHeight() / 2 + 1);
		if(isLeft)
		g2.fillRect(30, 0, getWidth()-30, getHeight());
		else
			g2.fillRect(0, 0, getWidth()-30, getHeight());

		 Image temp = null;
		if(imageIcon  != null)
			temp = imageIcon.getImage();
		g2.drawImage(temp, 70,10,40,40,this);
		
		grphcs.drawImage(img, 0, 0, null);
		super.paintComponent(grphcs);
	}

	public void setStatus(boolean b) {
		if (b) {
			color = Color.decode("#73D93BC");
			setForeground(Color.white);
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