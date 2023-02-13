package Custom;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ItemDetailService extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon imageIcon = new ImageIcon("img/bin.png");
	private JButton btnRemove;
	private CustomSpinner spinnerSL;
	private Color colorHover = new Color(255,255,255);
	private DecimalFormat df = new DecimalFormat("#,###.##"+" VNĐ");
	
	
	
	
	public JButton getBtnRemove() {
		return btnRemove;
	}

	public void setBtnRemove(JButton btnRemove) {
		this.btnRemove = btnRemove;
	}

	public CustomSpinner getSpinnerSL() {
		return spinnerSL;
	}

	public void setSpinnerSL(CustomSpinner spinnerSL) {
		this.spinnerSL = spinnerSL;
	}
	
	private String tenDichVu;
	private int soLuong;
	private double gia;

	
	
	
	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}
	
	public void setNumSpin(int num) {
		spinnerSL.setNum(num);
	}
	

	public ItemDetailService(int stt, String tenDichVu, int soLuong, double d) {
		this.tenDichVu = tenDichVu;
		this.soLuong = soLuong;
		this.gia =d;
		this.setPreferredSize(new Dimension(579,40));
		this.setBackground(new Color(255,255,255));
		this.setBorder(new MatteBorder(0, 0, 1, 0, new Color(170, 201, 186, 100)));
		this.setFont(new Font("Serif", Font.PLAIN, 18));
		FlowLayout fl_pnlTextSearchService = new FlowLayout(FlowLayout.LEFT, 0, 5);
		this.setLayout(fl_pnlTextSearchService);

		btnRemove = new JButton() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				BufferedImage img = new BufferedImage(getWidth() - 1, getHeight() - 1, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2 = img.createGraphics();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				Image tem = imageIcon.getImage();
				g2.drawImage(tem, 0, 0, 25, 25, this);
				g.drawImage(img, 0, 0, this);
				setFont(new Font("Serif", Font.PLAIN, 21));
			}
		};
		btnRemove.setPreferredSize(new Dimension( 25, 30));
		btnRemove.setContentAreaFilled(false);
		btnRemove.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRemove.setFocusPainted(false);
		btnRemove.setBorder(null);
		this.add(btnRemove);

		JLabel lbSTT = new JLabel(stt+"");
		lbSTT.setPreferredSize(new Dimension(50,30));
		lbSTT.setFont(new Font("Serif", Font.PLAIN, 21));
		lbSTT.setForeground(Color.decode("#0eb289"));
		lbSTT.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(lbSTT);
		
		JLabel lbTen = new JLabel(tenDichVu);
		lbTen.setPreferredSize(new Dimension(230,30));
		lbTen.setFont(new Font("Serif", Font.PLAIN, 21));
		lbTen.setForeground(Color.decode("#0eb289"));
		lbTen.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(lbTen);
		
		spinnerSL = new CustomSpinner(soLuong);
		spinnerSL.setPreferredSize(new Dimension(110,30));
		this.add(spinnerSL);
		
		String priceStr = df.format(d);
		
		JLabel lbDonGia = new JLabel(priceStr);
		lbDonGia.setPreferredSize(new Dimension(159,30));
		lbDonGia.setFont(new Font("Serif", Font.PLAIN, 21));
		lbDonGia.setForeground(Color.decode("#0eb289"));
		lbDonGia.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(lbDonGia);
		
		this.addMouseListener(this);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		BufferedImage img = new BufferedImage(getWidth() - 1, getHeight() - 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setColor(colorHover );
		g2.fillRect(0,0,getWidth()-1,getHeight()-1);
		
		g.drawImage(img, 0, 0, this);
		setFont(new Font("Serif", Font.PLAIN, 21));
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
		colorHover = new Color(80,80,80,20);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		colorHover = new Color(255,255,255);
		
	}
}
