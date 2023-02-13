package UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.rmi.Naming;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Custom.ButtonGradient;
import Custom.MyPassword;
import Custom.MyTextField;
import dao.DichVuDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.LoaiDichVuDao;
import dao.LoaiPhongDao;
import dao.NhanVienDao;
import dao.PhongDao;
import entity.NhanVien;

public class Login extends JFrame implements FocusListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font;
	private Icon userNameIcon = new ImageIcon("img/user.png");
	private Icon passWordIcon = new ImageIcon("img/lock.png");
	private JTextField txtUserName,txtPassWord;
	private int width = 550;
	private int height = 400;

	private JButton btnForgetPassword;
	private JButton btnLogin;
	private NhanVienDao nhanVienDao;
	private DichVuDao dichVuDao;
	private LoaiDichVuDao loaiDichVuDao;
	private PhongDao phongDao;
	private LoaiPhongDao loaiPhongDao;
	private KhachHangDao khachHangDao;
	private HoaDonDao hoaDonDao;
	private static JPanel pnMain;
	
	
	public static JPanel getPnMain() {
		return pnMain;
	}

	public Login(NhanVienDao nhanVienDao, DichVuDao dichVuDao, LoaiDichVuDao loaiDichVuDao, PhongDao phongDao, LoaiPhongDao loaiPhongDao, KhachHangDao khachHangDao, HoaDonDao hoaDonDao) {
		this.nhanVienDao = nhanVienDao;
		this.dichVuDao = dichVuDao;
		this.loaiDichVuDao=loaiDichVuDao;
		this.phongDao = phongDao;
		this.loaiPhongDao = loaiPhongDao;
		this.khachHangDao = khachHangDao;
		this.hoaDonDao = hoaDonDao;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("img\\MissFajardose-Regular.ttf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setContentPane(new JLabel(new ImageIcon(
				new ImageIcon("img/BG.jpg").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH))));
		getContentPane().setLayout(null);

		 pnMain = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setFont(font.deriveFont(85f));
				GradientPaint gra1 = new GradientPaint(0, 0, Color.decode("#CD3BED"), 270, 0, Color.decode("#F2D99F"));
				g2.setPaint(gra1);
				g2.drawString("Karaoke", 61, 76);
				g2.drawString("Karaoke", 60, 75);
				GradientPaint gra2 = new GradientPaint(290, 0, Color.decode("#CD3BED"), 390, 0,
						Color.decode("#F2D99F"));
				g2.setPaint(gra2);
				g2.drawString("Royal", 311, 76);
				g2.drawString("Royal", 310, 75);
				setFont(new Font("Tahoma", Font.PLAIN, 11));
			}
		};
//		{
//			@Override
//			protected void paintComponent(Graphics g) {
//				super.paintComponent(g);
//				setFont(new Font("Tahoma", Font.PLAIN, 11));
//				
//			}
//		};
		pnMain.setLayout(null);
		pnMain.setBounds(0, 0, width, height);
		pnMain.setOpaque(false);
		getContentPane().add(pnMain);

		JPanel pnFromLogin = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(0, 0, 0, 150));
				g2.fillRoundRect(0, 0, 340, 240, 20, 20);
//				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//				Image loginImage = ((ImageIcon) userLoing).getImage();
//				g2.drawImage(loginImage,70,10, 42,42,this);
//				setFont(new Font("Tahoma", Font.PLAIN, 11));
			}
		};

		pnFromLogin.setLayout(null);
		pnFromLogin.setBounds(105, 110, 340, 240);
		pnFromLogin.setOpaque(false);
		pnMain.add(pnFromLogin);

		txtUserName = new MyTextField();
		txtUserName.setFont(new Font("Serif", Font.PLAIN, 14));
		((MyTextField) txtUserName).setPrefixIcon(userNameIcon);
		txtUserName.setBounds(45, 40, 250, 34);
		txtUserName.setText("code2k1");
		txtUserName.setOpaque(false);
		txtUserName.setForeground(new Color(255, 255, 255, 180));
		
		pnFromLogin.add(txtUserName);

		txtPassWord = new MyPassword();
		((MyPassword) txtPassWord).setPrefixIcon(passWordIcon);
		txtPassWord.setFont(new Font("Serif", Font.PLAIN, 14));
		txtPassWord.setBounds(45, 100, 250, 34);
		txtPassWord.setText("AnhNongDan");
		txtPassWord.setOpaque(false);
		txtPassWord.requestFocus(true);
		txtPassWord.setForeground(new Color(255, 255, 255, 180));
		pnFromLogin.add(txtPassWord);

		btnForgetPassword = new JButton("Quên mật khẩu?");
		btnForgetPassword.setBounds(170, 135, 150, 34);
		btnForgetPassword.setContentAreaFilled(false);
		btnForgetPassword.setFocusPainted(false);
		btnForgetPassword.setBorder(null);
		btnForgetPassword.setForeground(new Color(255, 255, 255));
		btnForgetPassword.setFont(new Font("Serif", Font.PLAIN, 14));
		btnForgetPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
		pnFromLogin.add(btnForgetPassword);

//		JLabel lbLogin = new JLabel("Đăng Nhập Hệ Thống");
//		lbLogin.setForeground(Color.WHITE);
//		lbLogin.setFont(new Font("Serif", Font.BOLD, 21));
//		lbLogin.setHorizontalAlignment(SwingConstants.CENTER);
//		lbLogin.setBounds(45, 10, 250, 40);
//		pnFromLogin.add(lbLogin);

		btnLogin = new ButtonGradient();
		btnLogin.setText("Đăng nhập");
		((ButtonGradient) btnLogin).setColor1(Color.decode("#DD88F7"));
		((ButtonGradient) btnLogin).setColor2(Color.decode("#f3ff9b"));
		btnLogin.setBounds(100, 185, 130, 35);
		pnFromLogin.add(btnLogin);

		txtPassWord.addFocusListener(this);
		txtUserName.addFocusListener(this);
		
		btnLogin.addActionListener(this);
		
	}

	public static void main(String[] args) {
		SecurityManager ser = System.getSecurityManager();
		if (ser == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		try {
			NhanVienDao nhanVienDao = (NhanVienDao) Naming.lookup("rmi://172.16.20.170:1199//nhanVienDao");
			DichVuDao dichVuDao  =	(DichVuDao) Naming.lookup("rmi://172.16.20.170:1199//dichVuDao");
			LoaiDichVuDao loaiDichVuDao  = (LoaiDichVuDao) Naming.lookup("rmi://172.16.20.170:1199//loaiDichVuDao");
			PhongDao phongDao = (PhongDao) Naming.lookup("rmi://172.16.20.170:1199//phongDao");
			LoaiPhongDao loaiPhongDao = (LoaiPhongDao) Naming.lookup("rmi://172.16.20.170:1199//loaiPhongDao");
			KhachHangDao khachHangDao = (KhachHangDao) Naming.lookup("rmi://172.16.20.170:1199//khachHangDao");
			HoaDonDao hoaDonDao = (HoaDonDao) Naming.lookup("rmi://172.16.20.170:1199//hoaDonDao");
			new Login(nhanVienDao,dichVuDao,loaiDichVuDao,phongDao,loaiPhongDao, khachHangDao,hoaDonDao).setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void focusGained(FocusEvent e) {
		Object o = e.getSource();
		if (o.equals(txtUserName)) {
			if (txtUserName.getText().equals("Tên đăng nhập") )
				txtUserName.setText("");
			txtUserName.setForeground(Color.white);
		}
		if (o.equals(txtPassWord)) {
			if (txtPassWord.getText().equals("Mật khẩu"))
				txtPassWord.setText("");
			txtPassWord.setForeground(Color.white);
		}

	}

	public void focusLost(FocusEvent e) {
		Object o = e.getSource();
		if (o.equals(txtUserName)) {
			if (txtUserName.getText().equals(""))
				txtUserName.setText("Tên đăng nhập");
			txtUserName.setForeground(new Color(255, 255, 255, 180));
		}
		if (o.equals(txtPassWord)) {
			if (txtPassWord.getText().equals(""))
				txtPassWord.setText("Mật khẩu");
			txtPassWord.setForeground(new Color(255, 255, 255, 180));
		}

	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnLogin)) {
			String message = "";
			if(validData()) {
				try {
					String tenTK =txtUserName.getText().toString().replace(" ","");
					NhanVien nv =nhanVienDao.getNhanVienKhiBietTenTK(tenTK.toLowerCase());
					System.out.println(tenTK);
					System.out.println(txtPassWord.getText().toString());
					System.out.println(nv);
					if (nv==null) {
						message = "Sai tài khoản";
						JOptionPane.showMessageDialog(this, message);
					}else{
						String password = txtPassWord.getText().toString();
						if(!nv.getTaiKhoan().getMatKhau().equals(password)) {
							message = "Sai mật khẩu";
							JOptionPane.showMessageDialog(this, message);
						}else {
							App app = new App(nv , dichVuDao, loaiDichVuDao, phongDao, loaiPhongDao, khachHangDao,hoaDonDao);
							this.setVisible(false);
							app.setVisible(true);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private boolean validData() {
		return true;
	}
}
