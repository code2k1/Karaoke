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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CustomSpinner extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon imageIconMinusDask = new ImageIcon("img/minus.png");
	private ImageIcon imageIconMinusRed = new ImageIcon("img/minusRed.png");
	private ImageIcon imageIconAddDask = new ImageIcon("img/plus.png");
	private ImageIcon imageIconAddGreen = new ImageIcon("img/plusGreen.png");

	private JTextField txtNum;
	private ImageIcon imageIconMinus = imageIconMinusDask, imageIconAdd = imageIconAddDask;
	private JButton minus;
	private JButton add;
	
	
	
	public JButton getMinus() {
		return minus;
	}

	public void setMinus(JButton minus) {
		this.minus = minus;
	}

	public JButton getAdd() {
		return add;
	}

	public void setAdd(JButton add) {
		this.add = add;
	}

	public JTextField getTxtNum() {
		return txtNum;
	}
	
	private int num;
	
	

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		txtNum.setText(num+"");
	}

	public CustomSpinner(int num) {
		this.num = num;
		this.setLayout(null);
		this.setOpaque(false);
		minus = new JButton() {
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
				Image tem = null;
				if (imageIconMinus != null) {
					tem = imageIconMinus.getImage();
				}
				g2.drawImage(tem, 0, 9, 12, 16, this);
				g.drawImage(img, 0, 0, this);
				setFont(new Font("Serif", Font.PLAIN, 18));
			}
		};
		minus.setBounds(0, 5, 20, 20);
		minus.setContentAreaFilled(false);
		minus.setCursor(new Cursor(Cursor.HAND_CURSOR));
		minus.setFocusPainted(false);
		minus.setBorder(null);
		this.add(minus);

		txtNum = new JTextField(num + "");
		txtNum.setHorizontalAlignment(SwingConstants.CENTER);
		txtNum.setFont(new Font("Serif", Font.PLAIN, 18));
		txtNum.setForeground(Color.decode("#0eb289"));
		txtNum.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(150, 150, 150, 100)));
		txtNum.setBounds(25, 0, 60, 30);
		this.add(txtNum);

		add = new JButton() {
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
				Image tem = null;
				if (imageIconAdd != null) {
					tem = imageIconAdd.getImage();
				}
				g2.drawImage(tem, 0, 0, 26, 26, this);
				g.drawImage(img, 0, 0, this);
				setFont(new Font("Serif", Font.PLAIN, 18));
			}
		};
		add.setBounds(90, 0, 20, 30);
		add.setContentAreaFilled(false);
		add.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add.setFocusPainted(false);
		add.setBorder(null);
		this.add(add);
		minus.addMouseListener(this);
		add.addMouseListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(minus)) {
			int tem = Integer.parseInt(txtNum.getText().trim()) - 5;
			if (tem < 0)
				txtNum.setText("0");
			else
				txtNum.setText(tem + "");
		}
		if (o.equals(add)) {
			int tem = Integer.parseInt(txtNum.getText().trim()) + 5;
			if (tem > 100000)
				txtNum.setText("100000");
			else
				txtNum.setText(tem + "");
		}

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
		Object o = e.getSource();
		if (o.equals(minus)) {
			imageIconMinus = imageIconMinusRed;
		}
		if (o.equals(add)) {
			imageIconAdd = imageIconAddGreen;
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(minus)) {
			imageIconMinus = imageIconMinusDask;
		}
		if (o.equals(add)) {
			imageIconAdd = imageIconAddDask;
		}

	}

}
