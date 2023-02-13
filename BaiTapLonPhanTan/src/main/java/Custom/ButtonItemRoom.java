package Custom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ButtonItemRoom extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon emtyIcon = new ImageIcon("img/roomDarkIcon.png");
	private ImageIcon isUseIcon = new ImageIcon("img/RoomGreenIcon.png");
	private ImageIcon imageIcon;
	private boolean status;
	private Color colorFocus = new Color(255, 255, 255, 0);
	
	
	
	
	public ImageIcon getEmtyIcon() {
		return emtyIcon;
	}

	public void setEmtyIcon(ImageIcon emtyIcon) {
		this.emtyIcon = emtyIcon;
	}

	public ImageIcon getIsUseIcon() {
		return isUseIcon;
	}

	public void setIsUseIcon(ImageIcon isUseIcon) {
		this.isUseIcon = isUseIcon;
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
		if (status)
			imageIcon = isUseIcon;
		else
			imageIcon = emtyIcon;
	}

	public Color getColorFocus() {
		return colorFocus;
	}

	public void setColorFocus(Color colorFocus) {
		this.colorFocus = colorFocus;
		this.repaint();
	}

	public ButtonItemRoom() {
	}

	private String maPhong, tenLoaiPhong, tinhTrang ; 
	
	
	
	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}

	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
		this.setText(
				"<HTML><p text-align:\"center\">" + this.maPhong + "</p>"
						+ "</br> <p text-align:\"center\">"
						+  this.tenLoaiPhong+ "</p>"
						+ "</br> <p text-align:\"center\">"
						+ tinhTrang+ "</p></HTML>");
	}

	
	
	public ButtonItemRoom(String maPhong) {
		super();
		this.maPhong = maPhong;
	}

	
	public ButtonItemRoom(String maPhong,String tenLoaiPhong, String tinhTrang,boolean status) {
		super();
		this.maPhong = maPhong;
		this.tenLoaiPhong = tenLoaiPhong;
		this.tinhTrang = tinhTrang;
		this.status = status;
		
		if (status)
			imageIcon = isUseIcon;
		else
			imageIcon = emtyIcon;

		setContentAreaFilled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		setText(
				"<HTML><p text-align:\"center\">" + maPhong + "</p>"
						+ "</br> <p text-align:\"center\">"
						+  tenLoaiPhong+ "</p>"
						+ "</br> <p text-align:\"center\">"
						+ tinhTrang+ "</p></HTML>");
		setForeground(Color.decode("#0eb289"));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
		setFont(new Font("Serif", Font.PLAIN, 20));
		setVerticalAlignment(SwingConstants.BOTTOM);
		addMouseListener(new MouseAdapter() {

		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(colorFocus);
		g2.fillRoundRect(0, 0, width - 1, height - 1, 20, 20);

		Image bg = imageIcon.getImage();
		g2.drawImage(bg, 25, 5, width - 50, height - 100, this);

		g2.setColor(new Color(0, 0, 0, 60));
		g2.drawRoundRect(0, 0, width - 1, height - 1, 20, 20);
		g2.dispose();
		g.drawImage(img, 0, 0, null);

		super.paintComponent(g);
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ButtonItemRoom other = (ButtonItemRoom) obj;
		return Objects.equals(maPhong, other.maPhong);
	}

}
