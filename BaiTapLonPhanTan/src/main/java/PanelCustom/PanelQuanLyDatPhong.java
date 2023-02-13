package PanelCustom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Custom.ButtonBorderBottom;
import Custom.ButtonGradient2;
import Custom.ButtonGradient3;
import Custom.ButtonItemRoom;
import Custom.ButtonItemService;
import Custom.ButtonRadioCustom;
import Custom.CustomUI;
import Custom.DataSearch;
import Custom.ItemDetailService;
import Custom.PanelSearch;
import Custom.PanelSlide;
import Custom.SearchItem;
import Custom.TextFieldAutoComplete;
import dao.DichVuDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.LoaiDichVuDao;
import dao.LoaiPhongDao;
import dao.NhanVienDao;
import dao.PhongDao;
import entity.CTHoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiDichVu;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.Phong;
import entity.TrangThaiPhong;

public class PanelQuanLyDatPhong extends JFrame
		implements ActionListener, ChangeListener, FocusListener, KeyListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonGradient2 btnPhongHat;
	private ButtonGradient2 btnDichVu;
	private ButtonGradient2 btnGDThanhToan;
	private Icon icontTheaterRoomW = new ImageIcon("img/dressing-roomWhite.png");
	private Icon icontTheaterRoomG = new ImageIcon("img/dressing-roomGreen.png");
	private Icon icontFork_KnifeW = new ImageIcon("img/Cutlery-Fork-KnifeWhite.png");
	private Icon icontFork_KnifeG = new ImageIcon("img/Cutlery-Fork-KnifeGreen.png");
	private Icon icontBillG = new ImageIcon("img/billGreen.png");
	private ImageIcon nextLeftIcon = new ImageIcon("img/nextLeft.png");
	private ImageIcon nextRightIcon = new ImageIcon("img/nextRight.png");
	private ImageIcon radioEnableIcon = new ImageIcon("img/radioEnable.png");
	private ImageIcon radioDisableIcon = new ImageIcon("img/radioDisable.png");
	private ImageIcon roomGreenIcon = new ImageIcon("img/RoomGreenIcon.png");
	private ImageIcon roomWhiteIcon = new ImageIcon("img/roomWhiteIcon.png");
	private ImageIcon roomDarkIcon = new ImageIcon("img/roomDarkIcon.png");
	private ImageIcon searchIcon = new ImageIcon("img/searchIcon.png");
	private ImageIcon dolaIcon = new ImageIcon("img/dollar-symbol.png");
	private ImageIcon bookingIcon = new ImageIcon("img/booking.png");
	private JPanel pnDichVu;
	private JPanel pnPhongHat;
	private JPanel pnThanhToan;
	private static JPanel pnMain;
	private JRadioButton[] rdbtnPageFecesService = {}, rdbtnPageFecesRoom = {};
	private JButton btnNextLeft, btnNextRight, btnNextRightPageRoom, btnNextLeftPageRoom;
	private ButtonGroup group, groupRoom;
	private PanelSlide pnlSlide, pnlSlideRoomItem;
	private JPanel[] listPanelDichVu, listPanelPhong;
	private List<ButtonItemService> listButtonService = new ArrayList<ButtonItemService>();
	private List<ButtonItemRoom> listButtonRoom;
	private List<JButton> listSearchNameServiceType;
	private JPanel pnlPageFecesService, pnlPageFecesRoom;
	private int width = 1580, height = 900;
	private JPanel pnlSearchRoom;
	private JPanel pnlSearchService;
	private ButtonBorderBottom btnSearchAllPositionRoom;
	private ButtonBorderBottom btnSearchPositionGroundfloorRoom;
	private ButtonBorderBottom btnSearchPositionfloor1Room;
	private ButtonBorderBottom btnSearchPositionfloor2Room;
	private ButtonBorderBottom btnSearchPositionfloor3Room;
	private ButtonBorderBottom btnSearchRoomVip;

	private ButtonBorderBottom btnSearchAllPositionService;
	private TextFieldAutoComplete txtSearchRoom;
	private PanelSearch searchRoom;
	private JPopupMenu menuRoom;
	private DecimalFormat df = new DecimalFormat("#,###.##" + " VNĐ");

	private List<SearchItem> listSearchItemRoom, listSearchItemService, listSearchItemCustomer;
	private JCheckBox checkBoxRoom;
	private TextFieldAutoComplete txtSearchService;
	private PanelSearch searchService;
	private JPopupMenu menuService;
	private JCheckBox checkBoxService;
	private JTextField lblStaffName;
	private JTextField txtRoomID;
	private JTextField txtStartTime;
	private JTextField txtStopTime;
	private ButtonGradient3 btnThanhToan;
	private ButtonGradient3 btnThuePhong;
	private JPanel pnlServiceDetail;
	private JTextField txtTotalPrice;
	private JTextField txtPriceRoom;
	private PhongDao phongDao;
	private DichVuDao dichVuDao;
	@SuppressWarnings("unused")
	private LoaiPhongDao loaiPhongDao;
	@SuppressWarnings("unused")
	private LoaiDichVuDao loaiDichVuDao;

	private NhanVien nhanVien;
	@SuppressWarnings("unused")
	private KhachHangDao khachHangDao;
	private HoaDonDao hoaDonDao;
	private TextFieldAutoComplete txtSearchCustomer;
	private PanelSearch searchCustomer;
	private JPopupMenu menuCustomer;
	private JTextField txtCustomerName;
	private List<ItemDetailService> listItemDetailService;
	private List<HoaDon> dsHD, listHD;
//	private List<CTHoaDon> dsCTHD = new ArrayList<CTHoaDon>();
	private JTextField txtBillID;
	private List<Phong> dsp;
	private List<LoaiPhong> dslp;
	private List<DichVu> dsdv;
	private List<LoaiDichVu> dsldv;
	private List<KhachHang> dskh;

	public static JPanel getPnMain() {
		return pnMain;
	}

	public PanelQuanLyDatPhong(NhanVien nhanVien, DichVuDao dichVuDao, LoaiDichVuDao loaiDichVuDao, PhongDao phongDao,
			LoaiPhongDao loaiPhongDao, KhachHangDao khachHangDao, HoaDonDao hoaDonDao) throws Exception {
		this.dichVuDao = dichVuDao;
		this.phongDao = phongDao;
		this.loaiDichVuDao = loaiDichVuDao;
		this.loaiPhongDao = loaiPhongDao;
		this.nhanVien = nhanVien;
		this.khachHangDao = khachHangDao;
		this.hoaDonDao = hoaDonDao;

		setSize(width, height);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.setColor(Color.decode("#0eb289"));
//				120, 126, 121
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
				g2.fillRect(0, 0, getWidth(), getHeight() - 20);
				g2.fillRect(0, 0, getWidth() - 20, getHeight());
				setFont(new Font("Serif", Font.BOLD, 21));
			}
		};

		pnMain.setLayout(null);
		pnMain.setOpaque(false);
		pnMain.setBounds(0, 0, width, height);
		getContentPane().add(pnMain);

		btnPhongHat = new ButtonGradient2(true);
		btnPhongHat.setText("Phòng hát");
		btnPhongHat.setFont(new Font("Serif", Font.BOLD, 21));
		btnPhongHat.setBounds(50, 5, 180, 35);
		btnPhongHat.setIcon(icontTheaterRoomG);
		pnMain.add(btnPhongHat);

		btnDichVu = new ButtonGradient2(false);
		btnDichVu.setText("Dịch vụ");
		btnDichVu.setFont(new Font("Serif", Font.BOLD, 21));
		btnDichVu.setIcon(icontFork_KnifeW);
		btnDichVu.setBounds(230, 5, 180, 35);
		pnMain.add(btnDichVu);

		pnPhongHat = new JPanel(null) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
				setFont(new Font("Serif", Font.BOLD, 21));
			}
		};
		pnPhongHat.setOpaque(false);
		pnPhongHat.setBounds(20, 40, 940, 840);

		pnMain.add(pnPhongHat);

		pnlSearchRoom = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
				g2.fillRect(0, 20, getWidth(), getHeight() - 20);
				setFont(new Font("Serif", Font.BOLD, 21));
			}
		};
		pnlSearchRoom.setBounds(0, 0, 940, 50);
		pnlSearchRoom.setOpaque(false);
		pnlSearchRoom.setBorder(new MatteBorder(0, 0, 1, 0, new Color(170, 201, 186, 100)));
		FlowLayout fl_pnlSearchRoom = new FlowLayout(FlowLayout.LEFT, 20, 5);
		pnlSearchRoom.setLayout(fl_pnlSearchRoom);
		pnPhongHat.add(pnlSearchRoom);

		btnSearchAllPositionRoom = new ButtonBorderBottom("Tất cả");
		btnSearchAllPositionRoom.setPreferredSize(new Dimension(57, 40));
		pnlSearchRoom.add(btnSearchAllPositionRoom);

		btnSearchPositionGroundfloorRoom = new ButtonBorderBottom("Tầng trệt");
		btnSearchPositionGroundfloorRoom.setPreferredSize(new Dimension(83, 40));
		pnlSearchRoom.add(btnSearchPositionGroundfloorRoom);

		btnSearchPositionfloor1Room = new ButtonBorderBottom("Lầu 1");
		btnSearchPositionfloor1Room.setPreferredSize(new Dimension(52, 40));
		pnlSearchRoom.add(btnSearchPositionfloor1Room);

		btnSearchPositionfloor2Room = new ButtonBorderBottom("Lầu 2");
		btnSearchPositionfloor2Room.setPreferredSize(new Dimension(52, 40));
		pnlSearchRoom.add(btnSearchPositionfloor2Room);

		btnSearchPositionfloor3Room = new ButtonBorderBottom("Lầu 3");
		btnSearchPositionfloor3Room.setPreferredSize(new Dimension(52, 40));
		pnlSearchRoom.add(btnSearchPositionfloor3Room);

		btnSearchRoomVip = new ButtonBorderBottom("Phòng VIP");
		btnSearchRoomVip.setPreferredSize(new Dimension(100, 40));
		pnlSearchRoom.add(btnSearchRoomVip);

		JPanel pnlTextSearch = new JPanel();
		pnlTextSearch.setPreferredSize(new Dimension(400, 49));
		pnlTextSearch.setOpaque(false);
		FlowLayout fl_pnlTextSearchRoom = new FlowLayout(FlowLayout.LEFT, 0, 5);
		pnlTextSearch.setLayout(fl_pnlTextSearchRoom);
		pnlSearchRoom.add(pnlTextSearch);

		txtSearchRoom = new TextFieldAutoComplete();
		txtSearchRoom.setPrefixIcon(searchIcon);
		txtSearchRoom.setPreferredSize(new Dimension(250, 30));
		pnlTextSearch.add(txtSearchRoom);

		searchRoom = new PanelSearch();
		searchRoom.setPreferredSize(new Dimension(250, 350));

		menuRoom = new JPopupMenu();
		menuRoom.setFocusable(false);
		menuRoom.add(searchRoom);

		checkBoxRoom = new JCheckBox("Tìm theo mã phòng");
		checkBoxRoom.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkBoxRoom.setContentAreaFilled(false);
		checkBoxRoom.setFocusPainted(false);
		checkBoxRoom.setFont(new Font("Serif", Font.PLAIN, 16));
		checkBoxRoom.setBackground(Color.white);
		pnlTextSearch.add(checkBoxRoom);

		JPanel pnlRoomItem = new JPanel(null);
		pnlRoomItem.setBounds(0, 50, 940, 720);
		pnlRoomItem.setBackground(Color.white);
		pnPhongHat.add(pnlRoomItem);

		pnlSlideRoomItem = new PanelSlide();
		pnlSlideRoomItem.setBounds(0, 10, 940, 720);
		pnlSlideRoomItem.setAnimate(25);
		pnlRoomItem.add(pnlSlideRoomItem);

		pnlPageFecesRoom = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
				g2.fillRoundRect(0, 20, getWidth(), getHeight() - 20, 40, 40);
				g2.fillRect(0, 00, getWidth(), getHeight() - 20);
				setFont(new Font("Serif", Font.BOLD, 21));
			}
		};
		pnlPageFecesRoom.setBounds(0, 770, 940, 80);
		pnlPageFecesRoom.setOpaque(false);
		FlowLayout fl_pnlPageFecesRoom = new FlowLayout(FlowLayout.CENTER, 5, 10);
		pnlPageFecesRoom.setLayout(fl_pnlPageFecesRoom);
		pnPhongHat.add(pnlPageFecesRoom);

		pnDichVu = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.setColor(Color.orange);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
				setFont(new Font("Serif", Font.BOLD, 21));
			}
		};
		pnDichVu.setOpaque(false);
		pnDichVu.setBounds(20, 40, 940, 840);
		pnDichVu.setVisible(false);
		pnDichVu.setLayout(null);
		pnMain.add(pnDichVu);

		pnlSearchService = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
				g2.fillRect(0, 20, getWidth(), getHeight() - 20);
				setFont(new Font("Serif", Font.BOLD, 21));
			}
		};
		pnlSearchService.setBounds(0, 0, 940, 50);
		pnlSearchService.setOpaque(false);
		pnlSearchService.setBorder(new MatteBorder(0, 0, 1, 0, new Color(170, 201, 186, 100)));
		FlowLayout fl_pnlSearchService = new FlowLayout(FlowLayout.LEFT, 20, 5);
		pnlSearchService.setLayout(fl_pnlSearchService);
		pnDichVu.add(pnlSearchService);

		btnSearchAllPositionService = new ButtonBorderBottom("Tất cả");
		btnSearchAllPositionService.setPreferredSize(new Dimension(110, 40));
		pnlSearchService.add(btnSearchAllPositionService);

		dsldv = loaiDichVuDao.getDsLoaiDichVu();
		loadLoaiDichVu(dsldv);

		JPanel pnlTextSearchService = new JPanel();
		pnlTextSearchService.setPreferredSize(new Dimension(400, 49));
		pnlTextSearchService.setOpaque(false);
		FlowLayout fl_pnlTextSearchService = new FlowLayout(FlowLayout.LEFT, 0, 5);
		pnlTextSearchService.setLayout(fl_pnlTextSearchService);
		pnlSearchService.add(pnlTextSearchService);

		txtSearchService = new TextFieldAutoComplete();
		txtSearchService.setPrefixIcon(searchIcon);
		txtSearchService.setPreferredSize(new Dimension(250, 30));
		pnlTextSearchService.add(txtSearchService);

		searchService = new PanelSearch();
		searchService.setPreferredSize(new Dimension(250, 350));

		menuService = new JPopupMenu();
		menuService.setFocusable(false);
		menuService.add(searchService);

		checkBoxService = new JCheckBox("Tìm theo tên dịch vụ");
		checkBoxService.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkBoxService.setContentAreaFilled(false);
		checkBoxService.setFocusPainted(false);
		checkBoxService.setFont(new Font("Serif", Font.PLAIN, 16));
		checkBoxService.setBackground(Color.white);
		pnlTextSearchService.add(checkBoxService);

		JPanel pnlServiceItem = new JPanel(null);
		pnlServiceItem.setBounds(0, 50, 940, 720);
		pnlServiceItem.setBackground(Color.white);
		pnDichVu.add(pnlServiceItem);

		pnlSlide = new PanelSlide();
		pnlSlide.setBounds(0, 10, 940, 720);
		pnlServiceItem.add(pnlSlide);
		pnlSlide.setAnimate(25);

		pnlPageFecesService = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
				g2.fillRoundRect(0, 20, getWidth(), getHeight() - 20, 40, 40);
				g2.fillRect(0, 00, getWidth(), getHeight() - 20);
				setFont(new Font("Serif", Font.BOLD, 21));
			}
		};
		pnlPageFecesService.setBounds(0, 770, 940, 80);
		pnlPageFecesService.setOpaque(false);
		pnDichVu.add(pnlPageFecesService);
		FlowLayout fl_pnlPageFecesService = new FlowLayout(FlowLayout.CENTER, 5, 10);
		pnlPageFecesService.setLayout(fl_pnlPageFecesService);

		pnThanhToan = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
				setFont(new Font("Serif", Font.BOLD, 21));
			}
		};
		pnThanhToan.setOpaque(false);
		pnThanhToan.setLayout(null);
		pnThanhToan.setBounds(980, 40, 580, 840);
		pnMain.add(pnThanhToan);

		btnGDThanhToan = new ButtonGradient2(true);
		btnGDThanhToan.setText("Hóa đơn");
		btnGDThanhToan.setFont(new Font("Serif", Font.BOLD, 21));
		btnGDThanhToan.setBounds(1020, 5, 180, 35);
		btnGDThanhToan.setIcon(icontBillG);
		pnMain.add(btnGDThanhToan);

		lblStaffName = new JTextField(nhanVien.getHoTenNhanVien());
		lblStaffName.setEnabled(false);
		CustomUI.getInstance().setCustomTextFieldUnFocus(lblStaffName);

		lblStaffName.setBounds(211, 85, 250, 30);
		pnThanhToan.add(lblStaffName);

		JLabel lblTnDchV = new JLabel("Tên nhân viên:");
		CustomUI.getInstance().setCustomLabel(lblTnDchV);
		lblTnDchV.setBounds(46, 85, 165, 30);
		pnThanhToan.add(lblTnDchV);

		JLabel lblTnDchV1 = new JLabel("Tên khách hàng:");
		CustomUI.getInstance().setCustomLabel(lblTnDchV1);
		lblTnDchV1.setBounds(46, 40, 165, 30);
		pnThanhToan.add(lblTnDchV1);

		txtCustomerName = new JTextField();
		txtCustomerName.setEnabled(false);
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtCustomerName);
		txtCustomerName.setBounds(211, 40, 250, 30);
		pnThanhToan.add(txtCustomerName);

		searchCustomer = new PanelSearch();
		searchCustomer.setPreferredSize(new Dimension(250, 350));

		menuCustomer = new JPopupMenu();
		menuCustomer.setFocusable(false);
		menuCustomer.add(searchCustomer);

		txtRoomID = new JTextField();
		txtRoomID.setEnabled(false);
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtRoomID);
		txtRoomID.setBounds(211, 130, 250, 30);
		pnThanhToan.add(txtRoomID);

		JLabel lblTnDchV2 = new JLabel("Mã phòng:");
		CustomUI.getInstance().setCustomLabel(lblTnDchV2);
		lblTnDchV2.setBounds(46, 130, 165, 30);
		pnThanhToan.add(lblTnDchV2);

		txtPriceRoom = new JTextField();
		txtPriceRoom.setEnabled(false);
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtPriceRoom);
		txtPriceRoom.setBounds(211, 175, 250, 30);
		pnThanhToan.add(txtPriceRoom);

		JLabel lblTnDchV6 = new JLabel("Giá phòng:");
		CustomUI.getInstance().setCustomLabel(lblTnDchV6);
		lblTnDchV6.setBounds(46, 175, 165, 30);
		pnThanhToan.add(lblTnDchV6);

		txtStartTime = new JTextField();
		txtStartTime.setEnabled(false);
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtStartTime);
		txtStartTime.setBounds(211, 220, 250, 30);
		pnThanhToan.add(txtStartTime);

		JLabel lblTnDchV3 = new JLabel("Giờ vào:");
		CustomUI.getInstance().setCustomLabel(lblTnDchV3);
		lblTnDchV3.setBounds(46, 220, 165, 30);
		pnThanhToan.add(lblTnDchV3);

		txtStopTime = new JTextField();
		txtStopTime.setEnabled(false);
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtStopTime);
		txtStopTime.setBounds(211, 260, 250, 30);
		pnThanhToan.add(txtStopTime);

		JLabel lblTnDchV4 = new JLabel("Giờ ra:");
		CustomUI.getInstance().setCustomLabel(lblTnDchV4);
		lblTnDchV4.setBounds(46, 260, 165, 30);
		pnThanhToan.add(lblTnDchV4);

		pnlServiceDetail = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setFont(new Font("Serif", Font.BOLD, 21));
			}
		};
		pnlServiceDetail.setBounds(0, 260, 580, 405);
		pnlServiceDetail.setOpaque(false);
		pnlServiceDetail.setLayout(new BoxLayout(pnlServiceDetail, BoxLayout.PAGE_AXIS));

		JScrollPane src = new JScrollPane(pnlServiceDetail, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.setColor(new Color(200, 200, 200, 40));
				g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
				setFont(new Font("Serif", Font.BOLD, 21));
			}
		};
		src.setOpaque(false);
		src.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		src.getViewport().setOpaque(false);
		src.getViewport().setBackground(Color.WHITE);
		src.setBounds(0, 300, 596, 405);
		pnThanhToan.add(src);

		txtTotalPrice = new JTextField("0 VNĐ");
		txtTotalPrice.setEnabled(false);
		txtTotalPrice.setBounds(290, 725, 250, 30);
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtTotalPrice);
		txtTotalPrice.setBorder(null);
		txtTotalPrice.setFont(new Font("Serif", Font.BOLD, 23));
		txtTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		pnThanhToan.add(txtTotalPrice);

		txtBillID = new JTextField("Mã Hóa Đơn");
		txtBillID.setEnabled(false);
		txtBillID.setBounds(20, 0, 130, 30);
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtBillID);
		txtBillID.setBorder(null);
		txtBillID.setFont(new Font("Serif", Font.BOLD, 23));
		txtBillID.setHorizontalAlignment(SwingConstants.LEFT);
		pnThanhToan.add(txtBillID);

		JLabel lblTnDchV5 = new JLabel("Tổng tiền:");
		CustomUI.getInstance().setCustomLabel(lblTnDchV5);
		lblTnDchV5.setFont(new Font("Serif", Font.BOLD, 23));
		lblTnDchV5.setBounds(46, 725, 165, 30);
		pnThanhToan.add(lblTnDchV5);

		btnThanhToan = new ButtonGradient3(true, false);
		btnThanhToan.setText("Thanh toán");
		btnThanhToan.setEnabled(false);
		btnThanhToan.setFont(new Font("Serif", Font.BOLD, 21));
		((ButtonGradient3) btnThanhToan).setImageIcon(dolaIcon);
		btnThanhToan.setBounds(290, 780, 290, 60);
		pnThanhToan.add(btnThanhToan);

		btnThuePhong = new ButtonGradient3(false, true);
		btnThuePhong.setText("Thuê phòng");
		btnThuePhong.setBounds(0, 780, 290, 60);
		((ButtonGradient3) btnThuePhong).setImageIcon(bookingIcon);
		btnThuePhong.setFont(new Font("Serif", Font.BOLD, 21));
		pnThanhToan.add(btnThuePhong);

		JPanel pnlSearchCustomer = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				g2.setColor(Color.decode("#fffffff"));
				g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
				setFont(new Font("Serif", Font.BOLD, 21));
			}
		};
		FlowLayout fl_pnlSearchCustomer = new FlowLayout(FlowLayout.CENTER, 0, 0);
		pnlSearchCustomer.setLayout(fl_pnlSearchCustomer);
		pnlSearchCustomer.setOpaque(false);
		pnlSearchCustomer.setBounds(1250, 5, 250, 30);
		pnMain.add(pnlSearchCustomer);

		txtSearchCustomer = new TextFieldAutoComplete();
//		txtSearchCustomer.setBounds(1250, 5, 250, 30);
		txtSearchCustomer.setPreferredSize(new Dimension(250, 30));
		txtSearchCustomer.setPrefixIcon(searchIcon);
		txtSearchCustomer.addMouseListener(this);
		txtSearchCustomer.addKeyListener(this);
		pnlSearchCustomer.add(txtSearchCustomer);

		dslp = loaiPhongDao.getDsLoaiPhong();
		dskh = khachHangDao.getDsKhachHang();

		dsp = phongDao.getDsPhong();
		loadDataRoom(dsp);
		dsdv = dichVuDao.getDsDichVu();
		loadDataServic(dsdv);

		setDataRoom(searchRoom(""));
		setDataService(searchService(""));
		setDataCustomer(searchCustomer(""));

		dsHD = hoaDonDao.getAllHoaDon();
		listHD = hoaDonDao.getDsHoaDon();

		btnPhongHat.addActionListener(this);
		btnDichVu.addActionListener(this);
		btnSearchAllPositionRoom.addActionListener(this);
		btnSearchPositionGroundfloorRoom.addActionListener(this);
		btnSearchPositionfloor1Room.addActionListener(this);
		btnSearchPositionfloor2Room.addActionListener(this);
		btnSearchPositionfloor3Room.addActionListener(this);
		btnSearchRoomVip.addActionListener(this);
		btnSearchAllPositionService.addActionListener(this);
		checkBoxRoom.addActionListener(this);
		checkBoxService.addActionListener(this);
		btnThanhToan.addActionListener(this);
		btnThuePhong.addActionListener(this);

		txtSearchRoom.addMouseListener(this);
		txtSearchService.addMouseListener(this);

		txtSearchRoom.addKeyListener(this);
		txtSearchService.addKeyListener(this);

	}

	private HoaDon getBillDataInForm() throws Exception {
		String billID = "";
		int size = dsHD.size() + 1;
		billID = "HD";
		for (int i = 0; i < 6 - Integer.toString(size).length(); i++) {
			billID += 0;
		}
		billID += size;

		txtBillID.setText(billID);

		HoaDon hd = new HoaDon(billID, new Date(), new Date());

		for (KhachHang kh : dskh) {
			if (kh.getHoTenKhachHang().equals(txtCustomerName.getText())) {
				hd.setKhachHang(kh);
				break;
			}
		}
		hd.setNhanVien(nhanVien);
		List<CTHoaDon> list = new ArrayList<CTHoaDon>();
		for (Phong p : dsp) {
			if (p.getMaPhong().equals(txtRoomID.getText().trim())) {
				list.add(new CTHoaDon(p));
				break;
			}
		}
		hd.setCTHoaDon(list);

		return hd;
	}

	public static void main(String[] args) {
		SecurityManager ser = System.getSecurityManager();
		if (ser == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		try {
			NhanVienDao nhanVienDao = (NhanVienDao) Naming.lookup("rmi://192.168.1.104:1099//nhanVienDao");
			DichVuDao dichVuDao = (DichVuDao) Naming.lookup("rmi://192.168.1.104:1099//dichVuDao");
			LoaiDichVuDao loaiDichVuDao = (LoaiDichVuDao) Naming.lookup("rmi://192.168.1.104:1099//loaiDichVuDao");
			PhongDao phongDao = (PhongDao) Naming.lookup("rmi://192.168.1.104:1099//phongDao");
			LoaiPhongDao loaiPhongDao = (LoaiPhongDao) Naming.lookup("rmi://192.168.1.104:1099//loaiPhongDao");
			KhachHangDao khachHangDao = (KhachHangDao) Naming.lookup("rmi://192.168.1.104:1099//khachHangDao");
			HoaDonDao hoaDonDao = (HoaDonDao) Naming.lookup("rmi://192.168.1.104:1099//hoaDonDao");
			new PanelQuanLyDatPhong(nhanVienDao.getNhanVienKhiBietMa("NV00001"), dichVuDao, loaiDichVuDao, phongDao,
					loaiPhongDao, khachHangDao, hoaDonDao).setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		try {
			for (ItemDetailService item : listItemDetailService) {

				if (o.equals(item.getBtnRemove())) {

				}
				if (btnThuePhong.isActivate()) {
					if (o.equals(item.getSpinnerSL().getAdd())) {
						DichVu dv = null;
						for (DichVu d : dsdv) {
							if (d.getTenDichVu().equals(item.getTenDichVu()))
								dv = d;
						}
						if (dv.getSoLuongTon() > 5) {
							HoaDon hd = null;
							for (HoaDon h : listHD) {
								if (h.getMaHoaDon().equals(txtBillID.getText())) {
									hd = h;
									break;
								}
							}

							int sldv = 0;
							for (CTHoaDon cthd : hd.getCTHoaDon()) {
								if (cthd.getDichVu().getTenDichVu().equals(item.getTenDichVu())) {
									sldv = cthd.getSoLuongDat();
								}
							}
							int contain = hd.getCTHoaDon().indexOf(new CTHoaDon(dv));
							hd.getCTHoaDon().get(contain).setSoLuongDat(sldv + 5);
							int sl = dv.getSoLuongTon();
							updateCTHD(hd.getCTHoaDon());
							dichVuDao.updateSoLuong(dv.getMaDichVu(), sl - 5);
							int in = dsdv.indexOf(dv);
							dsdv.get(in).setSoLuongTon(sl - 5);
							int index = listButtonService.indexOf(new ButtonItemService(item.getTenDichVu()));
							listButtonService.get(index).setSl(sl - 5);

						} else {
							JOptionPane.showMessageDialog(this, "Vượt quá số lượng cho phép");
							item.getSpinnerSL().getAdd().setEnabled(false);
						}
					}
					if (o.equals(item.getSpinnerSL().getMinus())) {
						if (!item.getSpinnerSL().getTxtNum().getText().equals("0")) {
							DichVu dv = null;
							for (DichVu d : dsdv) {
								if (d.getTenDichVu().equals(item.getTenDichVu()))
									dv = d;
							}
							HoaDon hd = null;
							for (HoaDon h : listHD) {
								if (h.getMaHoaDon().equals(txtBillID.getText())) {
									hd = h;
									break;
								}
							}
							if (Integer.parseInt(item.getSpinnerSL().getTxtNum().getText()) >= 5) {

								int sldv = 0;
								for (CTHoaDon cthd : hd.getCTHoaDon()) {
									if (cthd.getDichVu().getTenDichVu().equals(item.getTenDichVu())) {
										sldv = cthd.getSoLuongDat();
									}
								}
								int contain = hd.getCTHoaDon().indexOf(new CTHoaDon(dv));
								int sl = dv.getSoLuongTon();

								hd.getCTHoaDon().get(contain).setSoLuongDat(sldv - 5);

								updateCTHD(hd.getCTHoaDon());
								dichVuDao.updateSoLuong(dv.getMaDichVu(), sl + 5);

								int in = dsdv.indexOf(dv);
								dsdv.get(in).setSoLuongTon(sl + 5);

								int index = listButtonService.indexOf(new ButtonItemService(item.getTenDichVu()));
								listButtonService.get(index).setSl(sl + 5);
							} else {
								int sldv = 0;
								for (CTHoaDon cthd : hd.getCTHoaDon()) {
									if (cthd.getDichVu().getTenDichVu().equals(item.getTenDichVu())) {
										sldv = cthd.getSoLuongDat();
									}
								}
								int contain = hd.getCTHoaDon().indexOf(new CTHoaDon(dv));
								int sl = dv.getSoLuongTon();

								hd.getCTHoaDon().get(contain).setSoLuongDat(
										sldv - Integer.parseInt(item.getSpinnerSL().getTxtNum().getText()));

								updateCTHD(hd.getCTHoaDon());
								dichVuDao.updateSoLuong(dv.getMaDichVu(),
										sl + Integer.parseInt(item.getSpinnerSL().getTxtNum().getText()));

								int in = dsdv.indexOf(dv);
								dsdv.get(in).setSoLuongTon(
										sl + Integer.parseInt(item.getSpinnerSL().getTxtNum().getText()));

								int index = listButtonService.indexOf(new ButtonItemService(item.getTenDichVu()));
								listButtonService.get(index)
										.setSl(sl + Integer.parseInt(item.getSpinnerSL().getTxtNum().getText()));
							}
						} else
							item.getSpinnerSL().getMinus().setEnabled(false);
					}
				}
			}
		} catch (Exception e2) {
		}

		if (o.equals(btnThuePhong)) {
			if (txtRoomID.getText().length() <= 0) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn phòng");
			} else if (txtCustomerName.getText().length() <= 0) {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn khách hàng");
			} else {
				try {
					if (hoaDonDao.addHoaDon(getBillDataInForm())) {
						listHD.add(getBillDataInForm());
						dsHD.add(getBillDataInForm());
						Date t = new Date();
						txtStartTime.setText(t.getHours() + ":" + t.getMinutes() + ":" + t.getSeconds());
						txtStopTime.setText("");
						Phong temp = null;
						for (Phong p : dsp) {
							if (p.getMaPhong().equals(txtRoomID.getText().trim())) {
								temp = p;
								break;
							}
						}
						int a = dsp.indexOf(temp);
						dsp.get(a).setTrangThai(TrangThaiPhong.INUSE);
						phongDao.updateStatusRoomInUse(txtRoomID.getText());
						JOptionPane.showMessageDialog(this, "Đã đặt phòng thành công. Mời bạn chọn dịch vụ");
						int index = listButtonRoom.indexOf(new ButtonItemRoom(txtRoomID.getText()));
						listButtonRoom.get(index).setStatus(true);
						listButtonRoom.get(index).setTinhTrang(TrangThaiPhong.INUSE.getTinhTrang());

						btnThuePhong.setActivate(true);
						btnThuePhong.setSelect(false);
						btnThanhToan.setSelect(true);
					} else {
						JOptionPane.showMessageDialog(this, "Đặt phòng thất bại !!");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if (o.equals(btnThanhToan)) {
			if (!btnThuePhong.isActivate()) {
				JOptionPane.showMessageDialog(this, "Bạn cần thuê phòng trước !!");
			} else {
				try {
					HoaDon hd = null;
					if (hoaDonDao.updateTrangThaiHoaDon(txtBillID.getText())) {
						for (HoaDon h : listHD) {
							if (h.getMaHoaDon().equals(txtBillID.getText())) {
								hd = h;
								break;
							}
						}

						hd.setTrangThai(true);
						hd.setNgayGioTra(new Date());
						hoaDonDao.updateHoaDon(hd);

						int in = listHD.indexOf(hd);
						listHD.remove(in);

						Phong p = hd.getCTHoaDon().get(0).getPhong();

						int in1 = dsp.indexOf(p);
						dsp.get(in1).setTrangThai(TrangThaiPhong.EMPTY);
						phongDao.updateStatusRoomEmpty(p.getMaPhong());
						int index = listButtonRoom.indexOf(new ButtonItemRoom(txtRoomID.getText()));
						listButtonRoom.get(index).setStatus(false);
						listButtonRoom.get(index).setTinhTrang(TrangThaiPhong.EMPTY.getTinhTrang());

						JOptionPane.showMessageDialog(this,
								"Hóa đơn có tổng tiền là " + df.format(hd.TongTienHoaDon()));
						Date t = new Date();
						txtStopTime.setText(t.getHours() + ":" + t.getMinutes() + ":" + t.getSeconds());

//						pnlServiceDetail.removeAll();

						btnThuePhong.setActivate(false);
						btnThuePhong.setSelect(true);
						btnThanhToan.setSelect(false);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		if (o.equals(btnPhongHat)) {
			((ButtonGradient2) btnPhongHat).setStatus(true);
			((ButtonGradient2) btnDichVu).setStatus(false);
			btnPhongHat.setIcon(icontTheaterRoomG);
			btnDichVu.setIcon(icontFork_KnifeW);

			pnPhongHat.setVisible(true);
			pnDichVu.setVisible(false);
		}
		if (o.equals(btnDichVu)) {
			((ButtonGradient2) btnDichVu).setStatus(true);
			((ButtonGradient2) btnPhongHat).setStatus(false);
			btnPhongHat.setIcon(icontTheaterRoomW);
			btnDichVu.setIcon(icontFork_KnifeG);
			pnDichVu.setVisible(true);
			pnPhongHat.setVisible(false);
		}
		if (o.equals(btnNextLeft)) {
			int a = Integer.parseInt(group.getSelection().getActionCommand());
			rdbtnPageFecesService[a - 1].setSelected(true);
			pnlSlide.show(a - 1);
		}
		if (o.equals(btnNextRight)) {
			int a = Integer.parseInt(group.getSelection().getActionCommand());
			rdbtnPageFecesService[a + 1].setSelected(true);
			pnlSlide.show(a + 1);
		}
		if (o.equals(btnNextLeftPageRoom)) {
			int a = Integer.parseInt(groupRoom.getSelection().getActionCommand());
			rdbtnPageFecesRoom[a - 1].setSelected(true);
			pnlSlideRoomItem.show(a - 1);
		}
		if (o.equals(btnNextRightPageRoom)) {
			int a = Integer.parseInt(groupRoom.getSelection().getActionCommand());
			rdbtnPageFecesRoom[a + 1].setSelected(true);
			pnlSlideRoomItem.show(a + 1);
		}

		if (rdbtnPageFecesService.length > 0) {
			for (JRadioButton rds : rdbtnPageFecesService) {
				if (o.equals(rds)) {
					int a = Integer.parseInt(group.getSelection().getActionCommand());
					pnlSlide.show(a);
				}
			}
		}

		if (rdbtnPageFecesRoom.length > 0) {
			for (JRadioButton rds : rdbtnPageFecesRoom) {
				if (o.equals(rds)) {
					int a = Integer.parseInt(groupRoom.getSelection().getActionCommand());
					pnlSlideRoomItem.show(a);
				}
			}
		}

		if (o.equals(btnSearchAllPositionRoom)) {
			try {
				for (JRadioButton pn : rdbtnPageFecesRoom) {
					pnlPageFecesRoom.remove(pn);
				}
				pnlPageFecesRoom.remove(btnNextRightPageRoom);
				pnlPageFecesRoom.remove(btnNextLeftPageRoom);
				pnlSlideRoomItem.remove(listPanelPhong);

				loadDataRoom(dsp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (o.equals(btnSearchPositionGroundfloorRoom)) {
			try {
				for (JRadioButton pn : rdbtnPageFecesRoom) {
					pnlPageFecesRoom.remove(pn);
				}
				pnlPageFecesRoom.remove(btnNextRightPageRoom);
				pnlPageFecesRoom.remove(btnNextLeftPageRoom);
				pnlSlideRoomItem.remove(listPanelPhong);
				String viTri = btnSearchPositionGroundfloorRoom.getText();
				List<Phong> temp = new ArrayList<Phong>();
				for (Phong p : dsp) {
					if (p.getViTri().equals(viTri))
						temp.add(p);
				}
				loadDataRoom(temp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (o.equals(btnSearchPositionfloor1Room)) {

			try {
				for (JRadioButton pn : rdbtnPageFecesRoom) {
					pnlPageFecesRoom.remove(pn);
				}
				pnlPageFecesRoom.remove(btnNextRightPageRoom);
				pnlPageFecesRoom.remove(btnNextLeftPageRoom);
				pnlSlideRoomItem.remove(listPanelPhong);
				String viTri = btnSearchPositionfloor1Room.getText();
				List<Phong> temp = new ArrayList<Phong>();
				for (Phong p : dsp) {
					if (p.getViTri().equals(viTri))
						temp.add(p);
				}
				loadDataRoom(temp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		if (o.equals(btnSearchPositionfloor2Room)) {

			try {
				for (JRadioButton pn : rdbtnPageFecesRoom) {
					pnlPageFecesRoom.remove(pn);
				}
				pnlPageFecesRoom.remove(btnNextRightPageRoom);
				pnlPageFecesRoom.remove(btnNextLeftPageRoom);
				pnlSlideRoomItem.remove(listPanelPhong);
				String viTri = btnSearchPositionfloor2Room.getText();
				List<Phong> temp = new ArrayList<Phong>();
				for (Phong p : dsp) {
					if (p.getViTri().equals(viTri))
						temp.add(p);
				}
				loadDataRoom(temp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		if (o.equals(btnSearchPositionfloor3Room)) {

			try {
				for (JRadioButton pn : rdbtnPageFecesRoom) {
					pnlPageFecesRoom.remove(pn);
				}
				pnlPageFecesRoom.remove(btnNextRightPageRoom);
				pnlPageFecesRoom.remove(btnNextLeftPageRoom);
				pnlSlideRoomItem.remove(listPanelPhong);
				String viTri = btnSearchPositionfloor3Room.getText();
				List<Phong> temp = new ArrayList<Phong>();
				for (Phong p : dsp) {
					if (p.getViTri().equals(viTri))
						temp.add(p);
				}
				loadDataRoom(temp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (o.equals(btnSearchRoomVip)) {
			try {
				for (JRadioButton pn : rdbtnPageFecesRoom) {
					pnlPageFecesRoom.remove(pn);
				}
				pnlPageFecesRoom.remove(btnNextRightPageRoom);
				pnlPageFecesRoom.remove(btnNextLeftPageRoom);
				pnlSlideRoomItem.remove(listPanelPhong);
				String tenLP = btnSearchRoomVip.getText();
				List<Phong> temp = new ArrayList<Phong>();
				for (Phong p : dsp) {
					if (p.getLoaiPhong().getTenLoaiPhong().equals(tenLP))
						temp.add(p);
				}
				loadDataRoom(temp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if (o.equals(btnSearchAllPositionService)) {
			for (JRadioButton rdo : rdbtnPageFecesService) {
				pnlPageFecesService.remove(rdo);
			}
			pnlPageFecesService.remove(btnNextLeft);
			pnlPageFecesService.remove(btnNextRight);
			pnlSlide.remove(listPanelDichVu);
			loadDataServic(dsdv);
		}
		if (listSearchNameServiceType.size() > 0) {
			for (JButton btn : listSearchNameServiceType) {
				if (o.equals(btn)) {
					for (JRadioButton rdo : rdbtnPageFecesService) {
						pnlPageFecesService.remove(rdo);
					}
					pnlPageFecesService.remove(btnNextLeft);
					pnlPageFecesService.remove(btnNextRight);
					pnlSlide.remove(listPanelDichVu);

					List<DichVu> temp = new ArrayList<DichVu>();
					for (DichVu dichVu : dsdv) {
						if (dichVu.getLoaiDichVu().getTenLoaiDichVu().equals(btn.getText()))
							temp.add(dichVu);
					}

					loadDataServic(temp);

				}
			}
		}
		if (o.equals(checkBoxRoom)) {
			String text = txtSearchRoom.getText().trim().toLowerCase();
			try {
				setDataRoom(searchRoom(text));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (searchRoom.getItemSize() > 0) {
				menuRoom.show(txtSearchRoom, 0, txtSearchRoom.getHeight());
				menuRoom.setPopupSize(menuRoom.getWidth(), (searchRoom.getItemSize() * 50));
			}

		}
		if (checkBoxRoom.isSelected()) {
			try {
				for (SearchItem itemRoom : listSearchItemRoom) {
					if (o.equals(itemRoom)) {
						txtSearchRoom.setText(itemRoom.getLbText().getText());
						menuRoom.setVisible(false);
						for (JRadioButton pn : rdbtnPageFecesRoom) {
							pnlPageFecesRoom.remove(pn);
						}
						pnlPageFecesRoom.remove(btnNextRightPageRoom);
						pnlPageFecesRoom.remove(btnNextLeftPageRoom);
						pnlSlideRoomItem.remove(listPanelPhong);
						String maP = itemRoom.getLbText().getText();
						List<Phong> temp = new ArrayList<Phong>();
						for (Phong phong : dsp) {
							if (phong.getMaPhong().equals(maP)) {
								temp.add(phong);
								break;
							}
						}
						loadDataRoom(temp);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				for (SearchItem itemRoom : listSearchItemRoom) {
					if (o.equals(itemRoom)) {
						txtSearchRoom.setText(itemRoom.getLbText().getText());
						menuRoom.setVisible(false);
						for (JRadioButton pn : rdbtnPageFecesRoom) {
							pnlPageFecesRoom.remove(pn);
						}
						pnlPageFecesRoom.remove(btnNextRightPageRoom);
						pnlPageFecesRoom.remove(btnNextLeftPageRoom);
						pnlSlideRoomItem.remove(listPanelPhong);
						String maLP = itemRoom.getLbText().getText();
						List<Phong> temp = new ArrayList<Phong>();
						for (Phong phong : dsp) {
							if (phong.getLoaiPhong().getTenLoaiPhong().equals(maLP)) {
								temp.add(phong);
							}
						}
						loadDataRoom(temp);
					}
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		if (o.equals(checkBoxRoom)) {
			String text = txtSearchRoom.getText().trim().toLowerCase();
			try {
				setDataRoom(searchRoom(text));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (searchRoom.getItemSize() > 0) {
				menuRoom.show(txtSearchRoom, 0, txtSearchRoom.getHeight());
				menuRoom.setPopupSize(menuRoom.getWidth(), (searchRoom.getItemSize() * 50));
			}

		}

		if (checkBoxService.isSelected()) {
			try {
				for (SearchItem itemService : listSearchItemService) {
					if (o.equals(itemService)) {
						txtSearchService.setText(itemService.getLbText().getText());
						menuService.setVisible(false);
						for (JRadioButton pn : rdbtnPageFecesService) {
							pnlPageFecesService.remove(pn);
						}
						pnlPageFecesService.remove(btnNextRight);
						pnlPageFecesService.remove(btnNextLeft);
						pnlSlide.remove(listPanelDichVu);
						String tenDichVu = itemService.getLbText().getText();
						List<DichVu> temp = new ArrayList<DichVu>();
						for (DichVu dichVu : dsdv) {
							if (dichVu.getTenDichVu().equals(tenDichVu))
								temp.add(dichVu);
						}

						loadDataServic(temp);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else {
			try {
				for (SearchItem itemService : listSearchItemService) {
					if (o.equals(itemService)) {
						txtSearchService.setText(itemService.getLbText().getText());
						menuService.setVisible(false);
						for (JRadioButton pn : rdbtnPageFecesService) {
							pnlPageFecesService.remove(pn);
						}
						pnlPageFecesService.remove(btnNextRight);
						pnlPageFecesService.remove(btnNextLeft);
						pnlSlide.remove(listPanelDichVu);
						String tenLoaiDichVu = itemService.getLbText().getText();
						List<DichVu> temp = new ArrayList<DichVu>();
						for (DichVu dichVu : dsdv) {
							if (dichVu.getLoaiDichVu().getTenLoaiDichVu().equals(tenLoaiDichVu))
								temp.add(dichVu);
						}

						loadDataServic(temp);
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		try {
			for (SearchItem itemCustomer : listSearchItemCustomer) {
				if (o.equals(itemCustomer)) {
					menuCustomer.setVisible(false);

					String text = itemCustomer.getLbText().getText();
					String sdt = text.replace("</br>", " ");
					String sdt2 = sdt.replace("</HTML>", " ");
					String[] sdt3 = sdt2.split(" ");
					KhachHang kH = null;
					for (KhachHang k : dskh) {
						if (k.getSoDienThoai().equals(sdt3[sdt3.length - 1])) {
							kH = k;
						}
					}
					txtSearchCustomer.setText(kH.getHoTenKhachHang());
					txtCustomerName.setText(kH.getHoTenKhachHang());
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (listButtonRoom.size() > 0) {
			for (ButtonItemRoom btn : listButtonRoom) {
				if (o.equals(btn)) {
					((ButtonItemRoom) btn).setColorFocus(Color.decode("#0eb289"));
					((ButtonItemRoom) btn).setImageIcon(roomWhiteIcon);
					btn.setForeground(Color.white);
				}
			}
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Object o = e.getSource();
		if (o.equals(rdbtnPageFecesService[0])) {
			if (!rdbtnPageFecesService[0].isSelected())
				btnNextLeft.setEnabled(true);
			else
				btnNextLeft.setEnabled(false);
		}
		if (listPanelDichVu.length - 1 > 0) {
			if (o.equals(rdbtnPageFecesService[listPanelDichVu.length - 1])) {
				if (!rdbtnPageFecesService[listPanelDichVu.length - 1].isSelected())
					btnNextRight.setEnabled(true);
				else
					btnNextRight.setEnabled(false);
			}
		}
		if (o.equals(rdbtnPageFecesRoom[0])) {
			if (!rdbtnPageFecesRoom[0].isSelected())
				btnNextLeftPageRoom.setEnabled(true);
			else
				btnNextLeftPageRoom.setEnabled(false);
		}
		if (listPanelPhong.length - 1 > 0) {
			if (o.equals(rdbtnPageFecesRoom[listPanelPhong.length - 1])) {
				if (!rdbtnPageFecesRoom[listPanelPhong.length - 1].isSelected())
					btnNextRightPageRoom.setEnabled(true);
				else
					btnNextRightPageRoom.setEnabled(false);
			}
		}

	}

	private void loadDataRoom(List<Phong> dsp) {
		try {
			int soTrang = 0;
			if (dsp.size() > 0) {
				listButtonRoom = new ArrayList<ButtonItemRoom>();
				if (dsp.size() % 15 == 0) {
					soTrang = dsp.size() / 15;
				} else {
					soTrang = dsp.size() / 15 + 1;
				}
				listPanelPhong = new JPanel[soTrang];
				int count = 0;
				for (int j = 0; j < listPanelPhong.length; j++) {
					listPanelPhong[j] = new JPanel();
					listPanelPhong[j].setOpaque(false);
					listPanelPhong[j].setLayout(new FlowLayout(FlowLayout.LEFT, 14, 10));
					JButton[] btnRoomList = new JButton[15];
					while (count < dsp.size()) {
						int temp = count - (15 * j);
						if (temp < btnRoomList.length) {
							btnRoomList[temp] = new ButtonItemRoom(dsp.get(count).getMaPhong(),
									dsp.get(count).getLoaiPhong().getTenLoaiPhong(),
									dsp.get(count).getTrangThai().getTinhTrang(),
									dsp.get(count).getTrangThai().getTinhTrang().equals("Đang sử dụng"));
							btnRoomList[temp].setPreferredSize(new Dimension(170, 220));
							btnRoomList[temp].addFocusListener(this);
							btnRoomList[temp].addActionListener(this);
							btnRoomList[temp].addMouseListener(this);
							listPanelPhong[j].add(btnRoomList[temp]);
							listButtonRoom.add((ButtonItemRoom) btnRoomList[temp]);
							count++;
						} else
							break;
					}
				}
				pnlSlideRoomItem.init(listPanelPhong);

				btnNextLeftPageRoom = new JButton(nextLeftIcon);
				btnNextLeftPageRoom.setContentAreaFilled(false);
				btnNextLeftPageRoom.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnNextLeftPageRoom.setFocusPainted(false);
				btnNextLeftPageRoom.setBorder(null);
				btnNextLeftPageRoom.setEnabled(false);
				pnlPageFecesRoom.add(btnNextLeftPageRoom);

				groupRoom = new ButtonGroup();
				rdbtnPageFecesRoom = new JRadioButton[soTrang];
				for (int i = 0; i < rdbtnPageFecesRoom.length; i++) {
					rdbtnPageFecesRoom[i] = new ButtonRadioCustom(radioDisableIcon, radioEnableIcon);
					if (i == 0)
						rdbtnPageFecesRoom[i].setSelected(true);
					rdbtnPageFecesRoom[i].setActionCommand(i + "");
					groupRoom.add(rdbtnPageFecesRoom[i]);
					pnlPageFecesRoom.add(rdbtnPageFecesRoom[i]);
					rdbtnPageFecesRoom[i].addChangeListener(this);
					rdbtnPageFecesRoom[i].addActionListener(this);
				}
				btnNextRightPageRoom = new JButton(nextRightIcon);
				btnNextRightPageRoom.setContentAreaFilled(false);
				btnNextRightPageRoom.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnNextRightPageRoom.setFocusPainted(false);
				btnNextRightPageRoom.setBorder(null);
				if (soTrang <= 1) {
					btnNextRightPageRoom.setEnabled(false);
					btnNextLeftPageRoom.setEnabled(false);
				}
				pnlPageFecesRoom.add(btnNextRightPageRoom);
				btnNextLeftPageRoom.addActionListener(this);
				btnNextRightPageRoom.addActionListener(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadLoaiDichVu(List<LoaiDichVu> dsLDV) {
		listSearchNameServiceType = new ArrayList<JButton>();
		for (LoaiDichVu loaiDichVu : dsLDV) {
			JButton temp = new ButtonBorderBottom(loaiDichVu.getTenLoaiDichVu());
			temp.setPreferredSize(new Dimension(110, 40));
			temp.addActionListener(this);
			pnlSearchService.add(temp);
			listSearchNameServiceType.add(temp);
			if (listSearchNameServiceType.size() == 3)
				break;
		}
	}

	public void loadDataServic(List<DichVu> dsdv) {
		try {
			int soTrang = 0;
			if (dsdv.size() > 0) {
				listButtonService = new ArrayList<ButtonItemService>();
				if (dsdv.size() % 15 == 0) {
					soTrang = dsdv.size() / 15;
				} else {
					soTrang = dsdv.size() / 15 + 1;
				}
				listPanelDichVu = new JPanel[soTrang];
				int count = 0;
				for (int j = 0; j < listPanelDichVu.length; j++) {
					listPanelDichVu[j] = new JPanel();
					listPanelDichVu[j].setOpaque(false);
					listPanelDichVu[j].setLayout(new FlowLayout(FlowLayout.LEFT, 14, 10));
					JButton[] btnServiceList = new JButton[15];
					while (count < dsdv.size()) {
						int temp = count - (15 * j);
						if (temp < btnServiceList.length) {
							btnServiceList[temp] = new ButtonItemService(new ImageIcon(dsdv.get(count).getHinhAnh()),
									dsdv.get(count).getSoLuongTon(), dsdv.get(count).getTenDichVu(),
									dsdv.get(count).getGiaBan());
							btnServiceList[temp].setPreferredSize(new Dimension(170, 220));
							btnServiceList[temp].addFocusListener(this);
							btnServiceList[temp].addActionListener(this);
							btnServiceList[temp].addMouseListener(this);
							listPanelDichVu[j].add(btnServiceList[temp]);
							listButtonService.add((ButtonItemService) btnServiceList[temp]);
							count++;
						} else
							break;
					}
				}

				pnlSlide.init(listPanelDichVu);

				btnNextLeft = new JButton(nextLeftIcon);
				btnNextLeft.setContentAreaFilled(false);
				btnNextLeft.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnNextLeft.setFocusPainted(false);
				btnNextLeft.setBorder(null);
				btnNextLeft.setEnabled(false);
				pnlPageFecesService.add(btnNextLeft);
				group = new ButtonGroup();

				rdbtnPageFecesService = new JRadioButton[soTrang];
				for (int i = 0; i < rdbtnPageFecesService.length; i++) {
					rdbtnPageFecesService[i] = new ButtonRadioCustom(radioDisableIcon, radioEnableIcon);
					if (i == 0)
						rdbtnPageFecesService[i].setSelected(true);
					rdbtnPageFecesService[i].setActionCommand(i + "");
					group.add(rdbtnPageFecesService[i]);
					pnlPageFecesService.add(rdbtnPageFecesService[i]);
					rdbtnPageFecesService[i].addChangeListener(this);
					rdbtnPageFecesService[i].addActionListener(this);
				}
				btnNextRight = new JButton(nextRightIcon);
				btnNextRight.setContentAreaFilled(false);
				btnNextRight.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnNextRight.setFocusPainted(false);
				btnNextRight.setBorder(null);
				if (soTrang <= 1) {
					btnNextRight.setEnabled(false);
					btnNextLeft.setEnabled(false);
				}
				pnlPageFecesService.add(btnNextRight);
				btnNextRight.addActionListener(this);
				btnNextLeft.addActionListener(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		Object o = e.getSource();
		if (listButtonRoom.size() > 0) {
			for (ButtonItemRoom btn : listButtonRoom) {
				if (o.equals(btn)) {
					((ButtonItemRoom) btn).setColorFocus(Color.decode("#0eb289"));
					((ButtonItemRoom) btn).setImageIcon(roomWhiteIcon);
					btn.setForeground(Color.white);
				}
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object o = e.getSource();
		if (listButtonRoom.size() > 0) {
			for (ButtonItemRoom btn : listButtonRoom) {
				if (o.equals(btn)) {
					((ButtonItemRoom) btn).setColorFocus(new Color(0, 0, 0, 0));
					if (((ButtonItemRoom) btn).isStatus()) {
						((ButtonItemRoom) btn).setImageIcon(roomGreenIcon);
					} else
						((ButtonItemRoom) btn).setImageIcon(roomDarkIcon);
					btn.setForeground(Color.decode("#0eb289"));
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		Object o = e.getSource();
		if (o.equals(txtSearchRoom)) {
			String text = txtSearchRoom.getText().trim().toLowerCase();
			try {
				setDataRoom(searchRoom(text));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (searchRoom.getItemSize() > 0) {
				menuRoom.show(txtSearchRoom, 0, txtSearchRoom.getHeight());
				menuRoom.setPopupSize(menuRoom.getWidth(), (searchRoom.getItemSize() * 50));
			}
		}
		if (o.equals(txtSearchService)) {
			String text = txtSearchService.getText().trim().toLowerCase();
			try {
				setDataService(searchService(text));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (searchService.getItemSize() > 0) {
				menuService.show(txtSearchService, 0, txtSearchService.getHeight());
				menuService.setPopupSize(menuService.getWidth(), (searchService.getItemSize() * 50));
			}
		}
		if (o.equals(txtSearchCustomer)) {
			String text = txtSearchCustomer.getText().trim().toLowerCase();
			try {
				setDataCustomer(searchCustomer(text));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (searchCustomer.getItemSize() > 0) {
				menuCustomer.show(txtSearchCustomer, 0, txtSearchCustomer.getHeight());
				menuCustomer.setPopupSize(menuCustomer.getWidth(), (searchCustomer.getItemSize() * 50));
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(txtSearchRoom)) {
			txtSearchRoom.selectAll();
			String text = txtSearchRoom.getText().trim().toLowerCase();
			try {
				setDataRoom(searchRoom(text));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (searchRoom.getItemSize() > 0) {
				menuRoom.show(txtSearchRoom, 0, txtSearchRoom.getHeight());
				menuRoom.setPopupSize(menuRoom.getWidth(), (searchRoom.getItemSize() * 50));
			}
		}
		if (o.equals(txtSearchCustomer)) {
			txtSearchCustomer.selectAll();
			String text = txtSearchCustomer.getText().trim().toLowerCase();
			try {
				setDataCustomer(searchCustomer(text));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (searchCustomer.getItemSize() > 0) {
				menuCustomer.show(txtSearchCustomer, 0, txtSearchCustomer.getHeight());
				menuCustomer.setPopupSize(menuCustomer.getWidth(), (searchCustomer.getItemSize() * 50));
			}
		}
		if (o.equals(txtSearchService)) {
			txtSearchService.selectAll();
			String text = txtSearchService.getText().trim().toLowerCase();
			try {
				setDataService(searchService(text));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if (searchService.getItemSize() > 0) {
				menuService.show(txtSearchService, 0, txtSearchService.getHeight());
				menuService.setPopupSize(menuService.getWidth(), (searchService.getItemSize() * 50));
			}
		}
		if (listButtonRoom.size() > 0) {
			for (ButtonItemRoom btn : listButtonRoom) {
				if (o.equals(btn)) {
					if (e.getClickCount() == 2 && !e.isConsumed()) {
						e.consume();
						txtCustomerName.setText("");
						txtStartTime.setText("");
						txtStopTime.setText("");
						String maPhong = ((ButtonItemRoom) btn).getMaPhong();
						JOptionPane.showMessageDialog(this, "Đã chọn phòng " + maPhong);
						txtRoomID.setText(maPhong);
						Phong phong = null;
						try {
							for (Phong p : dsp) {
								if (p.getMaPhong().equals(maPhong)) {
									phong = p;
									break;
								}
							}
							txtPriceRoom.setText((int) phong.getLoaiPhong().getGiaTien() + "");

							if (phong.getTrangThai().getTinhTrang().equals(TrangThaiPhong.INUSE.getTinhTrang())) {

								for (HoaDon hd : listHD) {
									if (hd.getCTHoaDon().get(0).getPhong().getMaPhong().equals(phong.getMaPhong())) {
										txtBillID.setText(hd.getMaHoaDon());
										break;
									}
								}
								loadBillTheoPhong(phong.getMaPhong());
								for (HoaDon hd : listHD) {
									if (hd.getMaHoaDon().equals(txtBillID.getText())) {
										txtCustomerName.setText(hd.getKhachHang().getHoTenKhachHang());
										txtTotalPrice.setText(df.format(hd.getTongTienDV()));
										break;
									}
								}
							} else {
								txtBillID.setText("Mã Hóa Đơn");
								pnlServiceDetail.removeAll();
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						((ButtonGradient2) btnDichVu).setStatus(true);
						((ButtonGradient2) btnPhongHat).setStatus(false);
						btnPhongHat.setIcon(icontTheaterRoomW);
						btnDichVu.setIcon(icontFork_KnifeG);
						pnDichVu.setVisible(true);
						pnPhongHat.setVisible(false);
					}
					btnThuePhong.setSelect(!((ButtonItemRoom) btn).isStatus());
					btnThanhToan.setSelect(((ButtonItemRoom) btn).isStatus());
				}
			}
		}
		if (listButtonService.size() > 0) {
			for (ButtonItemService btn : listButtonService) {
				if (o.equals(btn)) {
					if (e.getClickCount() == 2 && !e.isConsumed()) {
						e.consume();
						if (btnThuePhong.isActivate()) {
							int index = listButtonService.indexOf(new ButtonItemService(btn.getTenDichVu()));
							String results = "";
							results = JOptionPane.showInputDialog("Nhập số lương đặt:");
							if (results.equals(""))
								results = "1";
							try {
								DichVu dv = null;
								for (DichVu d : dsdv) {
									if (d.getTenDichVu().equals(listButtonService.get(index).getTenDichVu())) {
										dv = d;
										break;
									}
								}
								Phong p = null;
								for (Phong phong : dsp) {
									if (phong.getMaPhong().equals(txtRoomID.getText())) {
										p = phong;
										break;
									}
								}

								HoaDon hd = null;

								for (HoaDon h : listHD) {
									if (h.getCTHoaDon().get(0).getPhong().getMaPhong().equals(p.getMaPhong())) {
										hd = h;
										break;
									}
								}

								int contains = hd.getCTHoaDon().indexOf(new CTHoaDon(dv));
								if (contains != -1) {
									int sldv = hd.getCTHoaDon().get(contains).getSoLuongDat();
									hd.getCTHoaDon().get(contains).setSoLuongDat(sldv + Integer.parseInt(results));
									updateCTHD(hd.getCTHoaDon());
								} else {
									hd.getCTHoaDon().add(new CTHoaDon(dv, p, Integer.parseInt(results)));
									updateCTHD(hd.getCTHoaDon());
								}
								loadItemDetailService(hd.getCTHoaDon());
								int sl = btn.getSl();
								btn.setSl(sl - Integer.parseInt(results));
								dichVuDao.updateSoLuong(dv.getMaDichVu(), sl - Integer.parseInt(results));
								int in = dsdv.indexOf(dv);
								dsdv.get(in).setSoLuongTon(sl - Integer.parseInt(results));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(this, "Chưa thuê phòng !! ");
						}
					}
				}
			}
		}

	}

	public void updateCTHD(List<CTHoaDon> ds) throws Exception {
		String maPhong = ds.get(0).getPhong().getMaPhong();
		HoaDon hd = null;
		for (HoaDon h : listHD) {
			if (h.getCTHoaDon().get(0).getPhong().getMaPhong().equals(maPhong)) {
				hd = h;
				break;
			}
		}
		if (hd.getCTHoaDon().get(0).getTienPhong() == 0)
			hd.getCTHoaDon().remove(0);
		hd.setCTHoaDon(ds);
		hoaDonDao.updateHoaDon(hd);

		txtTotalPrice.setText(df.format(hd.getTongTienDV()));

		int in = listHD.indexOf(hd);
		listHD.get(in).setCTHoaDon(ds);
		listHD.get(in).setTongTienDichVu(hd.getTongTienDV());
	}

	private void loadItemDetailService(List<CTHoaDon> dsCTHD) {
		pnlServiceDetail.removeAll();
		listItemDetailService = new ArrayList<ItemDetailService>();
		int i = 1;
		if (dsCTHD.get(0).getTienPhong() != 0) {
			for (CTHoaDon ctHD : dsCTHD) {
				ItemDetailService temp = new ItemDetailService(i++, ctHD.getDichVu().getTenDichVu(),
						ctHD.getSoLuongDat(), ctHD.getDichVu().getGiaBan());
				pnlServiceDetail.add(temp);
				temp.getBtnRemove().addActionListener(this);
				temp.getSpinnerSL().getAdd().addActionListener(this);
				temp.getSpinnerSL().getMinus().addActionListener(this);
				listItemDetailService.add(temp);
			}
		}
		pnlServiceDetail.setBounds(0, 260, 580, 40 * listItemDetailService.size());
	}

	private void loadBillTheoPhong(String maPhong) throws Exception {
		btnThuePhong.setActivate(true);
		HoaDon hd = null;
		for (HoaDon h : listHD) {
			if (h.getCTHoaDon().get(0).getPhong().getMaPhong().equals(maPhong)) {
				hd = h;
				break;
			}
		}
		txtCustomerName.setText(hd.getKhachHang().getHoTenKhachHang());
		@SuppressWarnings("deprecation")
		String time = hd.getNgayGioDat().getHours() + ":" + hd.getNgayGioDat().getMinutes() + ":"
				+ hd.getNgayGioDat().getSeconds();
		txtStartTime.setText(time);

		loadItemDetailService(hd.getCTHoaDon());

	}

	private ImageIcon iconUser_Male = new ImageIcon("img/User-Male-50Red.png");
	private ImageIcon iconUser_Female = new ImageIcon("img/User-Female-50Pink.png");

	private List<DataSearch> searchCustomer(String search) throws Exception {

		int limitData = 7;
		List<DataSearch> list = new ArrayList<DataSearch>();
		for (KhachHang kh : dskh) {
			if (kh.getHoTenKhachHang().toLowerCase().contains(search))
				list.add(new DataSearch(kh.isGioiTinh() ? iconUser_Male : iconUser_Female,
						"<HTML><p> " + kh.getHoTenKhachHang() + " </p></br>" + kh.getSoDienThoai() + "</HTML>"));
			if (list.size() == limitData)
				break;
		}
		return list;
	}

	public void setDataCustomer(List<DataSearch> data) {
		searchCustomer.removeAll();
		listSearchItemCustomer = new ArrayList<SearchItem>();
		for (DataSearch dataSearch : data) {
			SearchItem item = new SearchItem(dataSearch);
			item.setPreferredSize(new Dimension(250, 50));
			item.addActionListener(this);
			searchCustomer.add(item);
			listSearchItemCustomer.add(item);
		}
		searchCustomer.repaint();
		searchCustomer.revalidate();
	}

	public void setDataService(List<DataSearch> data) {
		searchService.removeAll();
		listSearchItemService = new ArrayList<SearchItem>();
		for (DataSearch dataSearch : data) {
			SearchItem item = new SearchItem(dataSearch);
			item.setPreferredSize(new Dimension(250, 50));
			item.addActionListener(this);
			searchService.add(item);
			listSearchItemService.add(item);
		}
		searchService.repaint();
		searchService.revalidate();
	}

	public void setDataRoom(List<DataSearch> data) {
		searchRoom.removeAll();
		listSearchItemRoom = new ArrayList<SearchItem>();
		for (DataSearch dataSearch : data) {
			SearchItem item = new SearchItem(dataSearch);
			item.setPreferredSize(new Dimension(250, 50));
			item.addActionListener(this);
			searchRoom.add(item);
			listSearchItemRoom.add(item);
		}
		searchRoom.repaint();
		searchRoom.revalidate();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	private List<DataSearch> searchService(String search) throws Exception {

		int limitData = 7;
		List<DataSearch> list = new ArrayList<DataSearch>();
		if (!checkBoxService.isSelected()) {
			for (LoaiDichVu loaiDV : dsldv) {
				if (loaiDV.getTenLoaiDichVu().toLowerCase().contains(search))
					list.add(new DataSearch(null, loaiDV.getTenLoaiDichVu()));
				if (list.size() == limitData)
					break;
			}
		} else {
			for (DichVu dv : dsdv) {
				if (dv.getTenDichVu().toLowerCase().contains(search))
					list.add(new DataSearch(new ImageIcon(dv.getHinhAnh()), dv.getTenDichVu()));
				if (list.size() == limitData)
					break;
			}
		}
		return list;
	}

	private List<DataSearch> searchRoom(String search) throws Exception {

		int limitData = 7;
		List<DataSearch> list = new ArrayList<DataSearch>();
		if (!checkBoxRoom.isSelected()) {
			for (LoaiPhong loaiPhong : dslp) {
				if (loaiPhong.getTenLoaiPhong().toLowerCase().contains(search))
					list.add(new DataSearch(roomGreenIcon, loaiPhong.getTenLoaiPhong()));
				if (list.size() == limitData)
					break;
			}
		} else {
			for (Phong phong : dsp) {
				if (phong.getMaPhong().toLowerCase().contains(search))
					list.add(new DataSearch(roomGreenIcon, phong.getMaPhong()));
				if (list.size() == limitData)
					break;
			}
		}
		return list;
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
