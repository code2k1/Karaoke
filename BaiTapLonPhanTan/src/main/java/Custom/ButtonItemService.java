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
import java.text.DecimalFormat;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
public class ButtonItemService extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DecimalFormat df = new DecimalFormat("#,###.##" + " VNĐ");
	private ImageIcon background;

	public ButtonItemService() {
	}
	
	private  int sl;
	private String tenDichVu;
	private Double giaBan;

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
		setText("<HTML><p text-align:\"center\">" + "SL: " +sl
				+ "</p>" + "<p text-align:\"center\">" + tenDichVu
				+ "</p>" + "</br> <p text-align:\"center\"  style=\"color:#0eb289;\">"
				+ df.format(giaBan)+ "</p></HTML>");
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public Double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(Double giaBan) {
		this.giaBan = giaBan;
	}
	
	public ButtonItemService(String tenDichVu) {
		super();
		this.tenDichVu = tenDichVu;
	}

	public ButtonItemService(ImageIcon background, int sl, String tenDichVu,Double giaBan) {
		super();
		this.background = background;
		this.sl = sl;
		this.tenDichVu = tenDichVu;
		this.giaBan = giaBan;
		
		setContentAreaFilled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		setText("<HTML><p text-align:\"center\">" + "SL: " +sl
				+ "</p>" + "<p text-align:\"center\">" + tenDichVu
				+ "</p>" + "</br> <p text-align:\"center\"  style=\"color:#0eb289;\">"
				+ df.format(giaBan)+ "</p></HTML>");
		setForeground(Color.black);
		setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
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
	        //  Create Gradients Color
	        Image bg = background.getImage();
	        g2.drawImage(bg, 10, 0, width-20, height-85, this);
	        

	        
	        g2.setColor(new Color(0,0,0,60));
	        
	        g2.fillRect(10, 0, width-20, height-85);
	        
	        g2.drawRoundRect(0,0,width-1,height-1,20,20);
	        g2.dispose();
	        g.drawImage(img, 0, 0, null);
	        
	        super.paintComponent(g);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tenDichVu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ButtonItemService other = (ButtonItemService) obj;
		return Objects.equals(tenDichVu, other.tenDichVu);
	}

	
	
}
