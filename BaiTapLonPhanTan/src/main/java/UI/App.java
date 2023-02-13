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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Custom.ButtonItem;
import Custom.PanelCustom;
import PanelCustom.PanelQuanLyDatPhong;
import PanelCustom.PanelQuanLyDichVu;
import PanelCustom.PanelQuanLyPhong;
import dao.DichVuDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.LoaiDichVuDao;
import dao.LoaiPhongDao;
import dao.PhongDao;
import entity.NhanVien;

public class App extends JFrame implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width = 1850; 
	private int height = 960; 
	private Icon homeIcon = new ImageIcon("img/Home-100.png");
	private Icon roomManagerIcon = new ImageIcon("img/RoomManagerIcon.png");
	private Icon cusTomerManagerIcon = new ImageIcon("img/Business-Man-Settings-50.png");
	private Icon iconThongKe = new ImageIcon("img/IconThongKe.png");
	private Icon iconDichVu = new ImageIcon("img/Chef-Cap-50.png");
	private Icon iconRoomManager = new ImageIcon("img/RoomManager.png");
	private Icon iconEmployee = new ImageIcon("img/Employee-50.png");
//	private Icon iconBell = new ImageIcon("img/Bell-24.png");
	private Icon iconUser_Male = new ImageIcon("img/User-Male-50.png");
	private Icon iconUser_Female = new ImageIcon("img/User-Female-50.png");
	private Icon iconLogout = new ImageIcon("img/logout.png");
	
	private JPanel  panel1,panel2, panel3, panel4, panel5, panel6;
	private JButton btnQLKhachHang, btnQLDatPhong, btnQLPhong, btnQLDichVu, btnQLNhanVien, btnThongKe;
	private JPanel pnTab1, pnTab2, pnTab3, pnTab4, pnTab5, pnTab6;
	private JButton btnInfo;
	private JButton btnChucVu;
//	private JButton btnThoat;
	private DichVuDao dichVuDao;
	private LoaiDichVuDao loaiDichVuDao;
	private PhongDao phongDao;
	private LoaiPhongDao loaiPhongDao;
//	private NhanVien nhanVien;
	private JButton btnThoat;

	@SuppressWarnings("static-access")
	public App(NhanVien nhanVien, DichVuDao dichVuDao, LoaiDichVuDao loaiDichVuDao, PhongDao phongDao, LoaiPhongDao loaiPhongDao, KhachHangDao khachHangDao, HoaDonDao hoaDonDao) throws Exception {		
		this.dichVuDao = dichVuDao;
		this.loaiDichVuDao=loaiDichVuDao;
		this.phongDao = phongDao;
		this.loaiPhongDao = loaiPhongDao;
//		this.nhanVien = nhanVien;
		
		setSize(width, height);
		getContentPane().setLayout(null);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(new Color(255,255,255,0));
		getContentPane().setLayout(null);

		JPanel pnMain = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(new Color(0, 255, 255));
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
			}
		};
		pnMain.setLayout(null);
		pnMain.setBounds(0, 0, width, height);
		pnMain.setOpaque(false);
		getContentPane().add(pnMain);

		JPanel pnMenu = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gra = new GradientPaint(0, 0, Color.decode("#a1c4fd"), 0, getHeight(),
						Color.decode("#bf84ff"));
				g2.setPaint(gra);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
				g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
				
				
			}
		};
		pnMenu.setLayout(null);
		pnMenu.setBounds(0, 0, 270, height);
		pnMenu.setOpaque(false);
		pnMain.add(pnMenu);
		
		
		btnThoat = new JButton(iconLogout);
		btnThoat.setFont(new Font("Serif", Font.BOLD, 18));
		btnThoat.setBounds(5, 5,30, 30);
		btnThoat.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnThoat.setContentAreaFilled(false);
		btnThoat.setFocusPainted(false);
		btnThoat.setBorder(null);
		btnThoat.addMouseListener(this);
		btnThoat.addActionListener(this);
		pnMenu.add(btnThoat);
		
		JLabel lbNewLabel = new JLabel(homeIcon);
		lbNewLabel.setBounds(0, 30, 270, 90);
		pnMenu.add(lbNewLabel); 
		
		pnTab1 = new JPanel();
		pnTab1.setBounds(0, 150, 270, 60);
		pnTab1.setOpaque(false);
		pnMenu.add(pnTab1);
		pnTab1.setLayout(null);

		btnQLDatPhong = new ButtonItem("Quản lý đặt phòng");
		((ButtonItem) btnQLDatPhong).setPrefixIcon(roomManagerIcon);
		btnQLDatPhong.setFont(new Font("Serif", Font.BOLD, 22));
		btnQLDatPhong.setForeground(Color.WHITE);
		btnQLDatPhong.setBounds(0, 0, 270, 60);
		pnTab1.add(btnQLDatPhong);

		pnTab2 = new JPanel();
		pnTab2.setBounds(0, 225, 270, 60);
		pnMenu.add(pnTab2);
		pnTab2.setOpaque(false);
		pnTab2.setLayout(null);

		btnQLKhachHang = new ButtonItem("Quản lý khách hàng");
		btnQLKhachHang.setForeground(Color.WHITE);
		((ButtonItem) btnQLKhachHang).setPrefixIcon(cusTomerManagerIcon);
		btnQLKhachHang.setFont(new Font("Serif", Font.BOLD, 22));
		btnQLKhachHang.setBounds(0, 0, 270, 60);
		pnTab2.add(btnQLKhachHang);

		pnTab3 = new JPanel();
		pnTab3.setLayout(null);
		pnTab3.setBounds(0, 300, 270, 60);
		pnTab3.setOpaque(false);
		pnMenu.add(pnTab3);

		btnQLPhong = new ButtonItem("Quản lý phòng");
		btnQLPhong.setForeground(Color.WHITE);
		((ButtonItem) btnQLPhong).setPrefixIcon(iconRoomManager);
		btnQLPhong.setFont(new Font("Serif", Font.BOLD, 22));
		btnQLPhong.setBounds(0, 0, 270, 60);
		pnTab3.add(btnQLPhong);

		pnTab4 = new JPanel();
		pnTab4.setLayout(null);
		pnTab4.setBounds(0, 375, 270, 60);
		pnTab4.setOpaque(false);
		pnMenu.add(pnTab4);

		btnQLDichVu = new ButtonItem("Quản lý dịch vụ");
		btnQLDichVu.setForeground(Color.WHITE);
		((ButtonItem) btnQLDichVu).setPrefixIcon(iconDichVu);
		btnQLDichVu.setFont(new Font("Serif", Font.BOLD, 22));
		btnQLDichVu.setBounds(0, 0, 270, 60);
		pnTab4.add(btnQLDichVu);

		pnTab5 = new JPanel();
		pnTab5.setBounds(0, 450, 270, 60);
		pnTab5.setOpaque(false);
		pnMenu.add(pnTab5);
		pnTab5.setLayout(null);

		btnQLNhanVien = new ButtonItem("Quản lý nhân viên");
		btnQLNhanVien.setForeground(Color.WHITE);
		((ButtonItem) btnQLNhanVien).setPrefixIcon(iconEmployee);
		btnQLNhanVien.setFont(new Font("Serif", Font.BOLD, 22));
		btnQLNhanVien.setBounds(0, 0, 270, 60);
		pnTab5.add(btnQLNhanVien);

		pnTab6 = new JPanel();
		pnTab6.setBounds(0, 525, 270, 60);
		pnTab6.setOpaque(false);
		pnMenu.add(pnTab6);
		pnTab6.setLayout(null);

		btnThongKe = new ButtonItem("Thống kê");
		btnThongKe.setForeground(Color.WHITE);
		((ButtonItem) btnThongKe).setPrefixIcon(iconThongKe);
		btnThongKe.setFont(new Font("Serif", Font.BOLD, 22));
		btnThongKe.setBounds(0, 0, 270, 60);
		pnTab6.add(btnThongKe);
		
		JPanel pnHeader = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gra = new GradientPaint(0, 0, new Color(162,193,253), getWidth(), 0,
						new Color(172,170,253));
				g2.setPaint(gra);
				g2.fillRoundRect(0,0,getWidth(),getHeight(),28,28);
				g2.fillRect(0,0,getWidth()-30,getHeight());
				g2.fillRect(0,30,getWidth(),getHeight());
				Image prefix= null;
				if(nhanVien.isGioiTinh())
				 prefix = ((ImageIcon) iconUser_Male).getImage();
				else
					prefix = ((ImageIcon) iconUser_Female).getImage();
				g2.drawImage(prefix, 1520, 5, this);
			}
		};
		pnHeader.setForeground(Color.WHITE);
		pnHeader.setLayout(null);
		pnHeader.setOpaque(false);
		pnHeader.setBounds(270,0,1580, 60);
		pnMain.add(pnHeader);
		
		btnInfo = new JButton(nhanVien.getHoTenNhanVien());
		btnInfo.setHorizontalAlignment(SwingConstants.TRAILING);
		btnInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnInfo.setContentAreaFilled(false);
    	btnInfo.setFocusPainted(false);
		btnInfo.setBorder(null);
		btnInfo.setFont(new Font("Serif", Font.BOLD, 21));
		btnInfo.setBounds(1010, 7, 500, 20);
		btnInfo.setForeground(new Color(255,255,255));
		pnHeader.add(btnInfo);
		
		btnChucVu = new JButton(nhanVien.getChucVu());
		btnChucVu.setHorizontalAlignment(SwingConstants.TRAILING);
		btnChucVu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnChucVu.setContentAreaFilled(false);
		btnChucVu.setFocusPainted(false);
		btnChucVu.setBorder(null);
		btnChucVu.setFont(new Font("Serif", Font.BOLD, 20));
		btnChucVu.setBounds(1010, 33, 500, 20);
		btnChucVu.setForeground(new Color(255,255,255,200));
		pnHeader.add(btnChucVu);
		
//		JLabel lbHeader2 = new JLabel(iconBell);
//		lbHeader2.setBounds(1005, 7, 45, 45);
//		pnHeader.add(lbHeader2);
		
		PanelQuanLyDatPhong pnl =  new PanelQuanLyDatPhong(nhanVien, dichVuDao, loaiDichVuDao, phongDao, loaiPhongDao,khachHangDao,hoaDonDao);	
		
		panel1 =	 pnl.getPnMain();
		panel1.setBounds(270, 60, 1580, 900);
		panel1.setVisible(true);
		pnMain.add(panel1);

		
		panel2 = new PanelCustom();
		panel2.setBounds(270, 60, 1580, 900);
		((PanelCustom) panel2).setColor(Color.orange);
		pnMain.add(panel2);

		panel3 = new PanelQuanLyPhong(this.phongDao, this.loaiPhongDao);
		panel3.setBounds(270, 60, 1580, 900);
		panel3.setVisible(false);
		pnMain.add(panel3);

		panel4 = new PanelQuanLyDichVu(this.dichVuDao,this.loaiDichVuDao);
		panel4.setVisible(false);
		panel4.setBounds(270, 60, 1580, 900);
		pnMain.add(panel4);

		panel5 = new PanelCustom();
		panel5.setBounds(270, 60, 1580, 900);
		((PanelCustom) panel5).setColor(Color.orange);
		pnMain.add(panel5);

		panel6 = new PanelCustom();
		panel6.setBounds(270, 60, 1580, 900);
		((PanelCustom) panel6).setColor(Color.RED);
		pnMain.add(panel6);
		
		btnQLDatPhong.addActionListener(this);
		btnQLKhachHang.addActionListener(this);
		btnQLPhong.addActionListener(this);
		btnQLDichVu.addActionListener(this);
		btnQLNhanVien.addActionListener(this);
		btnThongKe.addActionListener(this);

	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(() -> {
//			SecurityManager ser = System.getSecurityManager();
//			if(ser==null) {
//				System.setProperty("java.security.policy", "policy/policy.policy");
//				System.setSecurityManager(new SecurityManager());
//			}
//			try {
//				DichVuDao dichVuDao  =	(DichVuDao) Naming.lookup("rmi://192.168.1.104:1099//dichVuDao");
//				LoaiDichVuDao loaiDichVuDao  = (LoaiDichVuDao) Naming.lookup("rmi://192.168.1.104:1099//loaiDichVuDao");
//				PhongDao phongDao = (PhongDao) Naming.lookup("rmi://192.168.1.104:1099//phongDao");
//				LoaiPhongDao loaiPhongDao = (LoaiPhongDao) Naming.lookup("rmi://192.168.1.104:1099//loaiPhongDao");
//				 new App(dichVuDao,loaiDichVuDao,phongDao,loaiPhongDao).setVisible(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object object =e.getSource();
		if(object.equals(btnThoat)) {
			System.exit(0);
		}
		if(object.equals(btnQLDatPhong)) {
			panel1.setVisible(true);
			panel2.setVisible(false);
			panel3.setVisible(false);
			panel4.setVisible(false);
			panel5.setVisible(false);
			panel6.setVisible(false);
		}
		if(object.equals(btnQLKhachHang)) {
			panel1.setVisible(false);
			panel2.setVisible(true);
			panel3.setVisible(false);
			panel4.setVisible(false);
			panel5.setVisible(false);
			panel6.setVisible(false);
		}
		if(object.equals(btnQLPhong)) {
			panel1.setVisible(false);
			panel2.setVisible(false);
			panel3.setVisible(true);
			panel4.setVisible(false);
			panel5.setVisible(false);
			panel6.setVisible(false);
		}
		if(object.equals(btnQLDichVu)) {
			panel1.setVisible(false);
			panel2.setVisible(false);
			panel3.setVisible(false);
			panel4.setVisible(true);
			panel5.setVisible(false);
			panel6.setVisible(false);
		}
		if(object.equals(btnQLNhanVien)) {
			panel1.setVisible(false);
			panel2.setVisible(false);
			panel3.setVisible(false);
			panel4.setVisible(false);
			panel5.setVisible(true);
			panel6.setVisible(false);
		}
		if(object.equals(btnThongKe)) {
			panel1.setVisible(false);
			panel2.setVisible(false);
			panel3.setVisible(false);
			panel4.setVisible(false);
			panel5.setVisible(false);
			panel6.setVisible(true);
		}
		
	}
}
