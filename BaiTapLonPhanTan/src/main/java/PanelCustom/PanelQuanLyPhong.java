package PanelCustom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Custom.ButtonGradient;
import Custom.CustomUI;
import dao.LoaiPhongDao;
import dao.PhongDao;
import entity.LoaiPhong;
import entity.Phong;
import entity.TrangThaiPhong;

public class PanelQuanLyPhong extends JPanel implements ActionListener, MouseListener, ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width = 1580, height = 900;
	private JPanel pnMainRoom;
	private ButtonGradient btnAdd;
	private ButtonGradient btnUpdate;
	private DefaultTableModel modelTableRoom;
	private JTable tblTableRoom;
	private JTextField txtRoomID;
	private JComboBox<String> cboRoomPosition;
	private JTextField txtBFieldRoomPosition;
	private JComboBox<String> cboRoomStatus;
	private JTextField txtBFieldRoomStatus;
	private JComboBox<String> cboRoomType;
	private JTextField txtBFieldRoomType;
	private PhongDao phongDao;
	private LoaiPhongDao loaiPhongDao;
	private List<Phong> listAllRoom;
	private List<Phong> listRoom;
	private List<LoaiPhong> listRoomType = new ArrayList<LoaiPhong>();
	private int sizeList = 0;
	private JButton btnRoomDetail;
	private JDialogLoaiPhong dialogLoaiPhong;

	public PanelQuanLyPhong(PhongDao phongDao, LoaiPhongDao loaiPhongDao) throws Exception {
		this.phongDao = phongDao;
		this.loaiPhongDao = loaiPhongDao;
		listAllRoom = this.phongDao.getAllPhong();

		setSize(width, height);
//		this.setResizable(false);
//		this.setUndecorated(true);
//		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setLayout(null);
		pnMainRoom = new JPanel() {
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
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
				g2.fillRect(0, 0, getWidth(), getHeight() - 20);
				g2.fillRect(0, 0, getWidth() - 20, getHeight());
			}
		};
		pnMainRoom.setForeground(Color.GRAY);
		pnMainRoom.setLayout(null);
		pnMainRoom.setOpaque(false);
		pnMainRoom.setBounds(0, 0, width, height);
		this.add(pnMainRoom);

		JLabel lblNewLabel_1 = new JLabel("QUẢN LÝ PHÒNG");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(540, 20, 500, 40);
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 32));
		pnMainRoom.add(lblNewLabel_1);

		JPanel pnInfor = new JPanel() {
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
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
				setFont(new Font("Serif", Font.PLAIN, 20));
			}
		};
		pnInfor.setLayout(null);
		pnInfor.setOpaque(false);
		pnInfor.setBounds(30, 80, 1520, 240);
		pnMainRoom.add(pnInfor);

		JLabel lblNewLabel = new JLabel("Mã phòng:");
		CustomUI.getInstance().setCustomLabel(lblNewLabel);
		lblNewLabel.setBounds(70, 30, 140, 30);
		pnInfor.add(lblNewLabel);

		txtRoomID = new JTextField();
		CustomUI.getInstance().setCustomTextFieldOff(txtRoomID);
		txtRoomID.setBounds(210, 30, 250, 30);
		pnInfor.add(txtRoomID);

		JLabel lblTnDchV = new JLabel("ViTri:");
		CustomUI.getInstance().setCustomLabel(lblTnDchV);
		lblTnDchV.setBounds(70, 85, 140, 30);
		pnInfor.add(lblTnDchV);

		cboRoomPosition = new JComboBox<String>();
		cboRoomPosition.addItem("Tầng trệt");
		cboRoomPosition.addItem("Lầu 1");
		cboRoomPosition.addItem("Lầu 2");
		cboRoomPosition.addItem("Lầu 3");
		CustomUI.getInstance().setCustomComboBox(cboRoomPosition);
		txtBFieldRoomPosition = CustomUI.getInstance().setCustomCBoxField(cboRoomPosition);
		cboRoomPosition.setBounds(210, 85, 250, 30);
		pnInfor.add(cboRoomPosition);

		cboRoomStatus = new JComboBox<String>();
		cboRoomStatus.addItem(TrangThaiPhong.EMPTY.getTinhTrang());
		cboRoomStatus.addItem(TrangThaiPhong.INUSE.getTinhTrang());
		cboRoomStatus.addItem(TrangThaiPhong.NOTUSED.getTinhTrang());
		CustomUI.getInstance().setCustomComboBox(cboRoomStatus);
		txtBFieldRoomStatus = CustomUI.getInstance().setCustomCBoxField(cboRoomStatus);
		cboRoomStatus.setBounds(818, 30, 250, 30);
		pnInfor.add(cboRoomStatus);

		JLabel lblNewLabel_2 = new JLabel("Trạng thái:");
		CustomUI.getInstance().setCustomLabel(lblNewLabel_2);
		lblNewLabel_2.setBounds(678, 30, 140, 30);
		pnInfor.add(lblNewLabel_2);

		cboRoomType = new JComboBox<String>();
		CustomUI.getInstance().setCustomComboBox(cboRoomType);
		txtBFieldRoomType = CustomUI.getInstance().setCustomCBoxField(cboRoomType);
		cboRoomType.setBounds(818, 85, 250, 30);
		pnInfor.add(cboRoomType);

		JLabel lblNewLabel_3 = new JLabel("Loại phòng:");
		CustomUI.getInstance().setCustomLabel(lblNewLabel_3);
		lblNewLabel_3.setBounds(678, 85, 140, 30);
		pnInfor.add(lblNewLabel_3);

		btnRoomDetail = new JButton("Chi tiết");
		btnRoomDetail.setBounds(1070, 85, 90, 30);
		btnRoomDetail.setFont(new Font("Serif", Font.BOLD, 20));
		btnRoomDetail.setContentAreaFilled(false);
		btnRoomDetail.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRoomDetail.setFocusPainted(false);
		btnRoomDetail.setForeground(Color.decode("#365F42"));
		btnRoomDetail.setBorder(null);
		pnInfor.add(btnRoomDetail);

		btnAdd = new ButtonGradient();
		btnAdd.setText("Thêm");
		btnAdd.setFont(CustomUI.fontButton);
		((ButtonGradient) btnAdd).setColor2(Color.decode("#0eb289"));
		((ButtonGradient) btnAdd).setColor1(Color.decode("#68D8B7"));
		btnAdd.setBounds(70, 160, 130, 40);
		pnInfor.add(btnAdd);

		btnUpdate = new ButtonGradient();
		btnUpdate.setText("Sửa");
		btnUpdate.setFont(CustomUI.fontButton);
		((ButtonGradient) btnUpdate).setColor2(Color.decode("#0eb289"));
		((ButtonGradient) btnUpdate).setColor1(Color.decode("#68D8B7"));
		btnUpdate.setBounds(250, 160, 130, 40);
		pnInfor.add(btnUpdate);

		JPanel pnTable = new JPanel() {
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
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
				setFont(new Font("Serif", Font.PLAIN, 20));
			}
		};
		pnTable.setLayout(null);
		pnTable.setOpaque(false);
		pnTable.setBorder(null);
		pnTable.setBounds(30, 340, 1520, 540);
		pnMainRoom.add(pnTable);

		JPanel pnlTable = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(Color.decode("#0eb289"));
				g2.fillRect(getWidth() - 2, 0, 2, getHeight() - 1);
				g2.fillRect(0, 0, 2, getHeight() - 1);
				g2.fillRect(0, getHeight() - 3, getWidth() - 1, 2);
				setFont(new Font("Serif", Font.PLAIN, 21));

			}
		};
		pnlTable.setBackground(Color.WHITE);
		pnlTable.setLayout(null);
		pnlTable.setBounds(20, 60, 1480, 440);
		pnlTable.setOpaque(false);

		String[] cols = { "STT", "Mã phòng", "Vị trí", "Loại phòng", "Trạng thái" };
		modelTableRoom = new DefaultTableModel(cols, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};
		tblTableRoom = new JTable(modelTableRoom) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setFont(new Font("Serif", Font.PLAIN, 21));
			}
		};
		CustomUI.getInstance().setCustomTable(tblTableRoom);
		tblTableRoom.setRowHeight(30);
		tblTableRoom.setGridColor(Color.decode("#0eb289"));

		JScrollPane scrTable = CustomUI.getInstance().setCustomScrollPane(tblTableRoom);
		scrTable.setBounds(0, 0, 1497, 440);
		pnlTable.add(scrTable);
		pnTable.add(pnlTable);

		dialogLoaiPhong = new JDialogLoaiPhong(this.loaiPhongDao, null, true);
		
		txtBFieldRoomPosition.addMouseListener(this);
		txtBFieldRoomStatus.addMouseListener(this);
		txtBFieldRoomType.addMouseListener(this);
		tblTableRoom.addMouseListener(this);
		btnRoomDetail.addMouseListener(this);

		btnAdd.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnRoomDetail.addActionListener(this);

		btnRoomDetail.addChangeListener(this);

		loadComBoBox();
		loadTable();

	}

	public static void main(String[] args) {
		try {
			SecurityManager ser = System.getSecurityManager();
			if (ser == null) {
				System.setProperty("java.security.policy", "policy/policy.policy");
				System.setSecurityManager(new SecurityManager());
			}
			PhongDao phongDao = (PhongDao) Naming.lookup("rmi://192.168.1.104:1099//phongDao");
			LoaiPhongDao loaiPhongDao = (LoaiPhongDao) Naming.lookup("rmi://192.168.1.104:1099//loaiPhongDao");

			new PanelQuanLyPhong(phongDao, loaiPhongDao).setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Object o = e.getSource();
		if (o.equals(btnRoomDetail)) {
			if (!dialogLoaiPhong.isVisible()) {
				loadComBoBox();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(btnRoomDetail)) {
			dialogLoaiPhong.setVisible(true);
		}
		if (o.equals(txtBFieldRoomPosition)) {
			cboRoomPosition.showPopup();
		}
		if (o.equals(txtBFieldRoomStatus)) {
			cboRoomStatus.showPopup();
		}
		if (o.equals(txtBFieldRoomType)) {
			cboRoomType.showPopup();
		}
		if (o.equals(tblTableRoom)) {
			int selectedRow = tblTableRoom.getSelectedRow();
			txtRoomID.setText(tblTableRoom.getValueAt(selectedRow, 1).toString().trim());
			cboRoomPosition.setSelectedItem(tblTableRoom.getValueAt(selectedRow, 2).toString().trim());
			cboRoomType.setSelectedItem(tblTableRoom.getValueAt(selectedRow, 3).toString().trim());
			cboRoomStatus.setSelectedItem(tblTableRoom.getValueAt(selectedRow, 4).toString().trim());
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
		if (o.equals(btnRoomDetail)) {
			btnRoomDetail.setForeground(Color.decode("#0eb289"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(btnRoomDetail)) {
			btnRoomDetail.setForeground(Color.decode("#365F42"));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnAdd)) {
			String message = "";
			if (validData()) {
				try {
					txtRoomID.setText("");
					Phong room = getRoomDataInForm();
					if (phongDao.addPhong(room)) {
						message = "Thêm phòng mới thành công";
						JOptionPane.showMessageDialog(this, message);
						listAllRoom.add(room);
						sizeList =listAllRoom.size();
						addRow(tblTableRoom.getRowCount() + 1, room);
						tblTableRoom.getSelectionModel().setSelectionInterval(tblTableRoom.getRowCount() - 1,
								tblTableRoom.getRowCount() - 1);
						tblTableRoom.scrollRectToVisible(tblTableRoom.getCellRect(tblTableRoom.getRowCount() - 1,
								tblTableRoom.getRowCount() - 1, true));
					} else {
						message = "Thêm phòng thất bại";
						JOptionPane.showMessageDialog(this, message);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if (o.equals(btnUpdate)) {
			String message = "";
			if (validData()) {
				try {
					if (tblTableRoom.getSelectedRow() != -1 && txtRoomID.getText().length() > 0) {
						message = "Xác nhận cập nhật thông tin " + "Phòng" + ": " + txtRoomID.getText();
						int choose = JOptionPane.showConfirmDialog(this, message,
								"Xác nhận cập nhật thông tin " + "Phòng" + "", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (choose == JOptionPane.OK_OPTION) {
							if (phongDao.updatePhong(getRoomDataInForm())) {
								message = "Cập nhật phòng thành công";
								JOptionPane.showMessageDialog(this, message);
								loadTable();
							} else {
								message = "Cập nhật phòng thất bại";
								JOptionPane.showMessageDialog(this, message);
							}
						}
					} else {
						message = "Hãy chọn " + "Phòng" + " mà bạn cần cập nhật  thông tin";
						JOptionPane.showMessageDialog(this, message);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
	}

	/**
	 * chuyển đổi thông tin trong form thành đối tượng Phong
	 * 
	 * @return {@code DichVu}: phòng
	 */
	private Phong getRoomDataInForm() {
		try {
			String roomId = "";
			if (txtRoomID.getText().length() <= 0) {
				int size = sizeList + 1;
				roomId = "P";
				for (int i = 0; i < 4 - Integer.toString(size).length(); i++) {
					roomId += 0;
				}
				roomId += size;
			} else {
				roomId = txtRoomID.getText();
			}
			Phong p = new Phong(roomId, cboRoomPosition.getSelectedItem().toString().trim());
			p.setLoaiPhong(listRoomType.get(cboRoomType.getSelectedIndex()));
			if (TrangThaiPhong.EMPTY.getTinhTrang().equals(cboRoomStatus.getSelectedItem().toString()))
				p.setTrangThai(TrangThaiPhong.EMPTY);
			else if (TrangThaiPhong.INUSE.getTinhTrang().equals(cboRoomStatus.getSelectedItem().toString())) {
				p.setTrangThai(TrangThaiPhong.INUSE);
			} else {
				p.setTrangThai(TrangThaiPhong.NOTUSED);
			}
			return p;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

	private boolean validData() {
		// TODO Auto-generated method stub
		return true;
	}

	private void loadComBoBox() {
		try {
			listRoomType = loaiPhongDao.getDsLoaiPhong();
			if (listRoomType != null)
				loadRoomType((ArrayList<LoaiPhong>) listRoomType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loadRoomType(ArrayList<LoaiPhong> dsLoaiPhong) {
		cboRoomType.removeAllItems();
		for (LoaiPhong loaiPhong : dsLoaiPhong) {
			cboRoomType.addItem(loaiPhong.getTenLoaiPhong());
		}

	}

	public void loadTable() {
		reSizeColumnTable();
		if (listAllRoom.size() > 0) {
			try {
				sizeList = listAllRoom.size();
				listRoom = phongDao.getDsPhong();
				loadRoomList((ArrayList<Phong>) listRoom);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void loadRoomList(ArrayList<Phong> roomList) {
		modelTableRoom.getDataVector().removeAllElements();
		modelTableRoom.fireTableDataChanged();
		int i = 1;
		for (Phong item : roomList) {
			addRow(i++, item);
		}
	}

	private void addRow(int stt, Phong item) {

		try {
			LoaiPhong temp = loaiPhongDao.getLoaiPhongKhiBietMa(item.getLoaiPhong().getMaLoaiPhong());
			modelTableRoom.addRow(new Object[] { stt, addSpaceToString(item.getMaPhong()),
					addSpaceToString(item.getViTri()), addSpaceToString(temp.getTenLoaiPhong()),
					addSpaceToString(item.getTrangThai().getTinhTrang()) });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String addSpaceToString(String str) {
		return " " + str + " ";
	}

	/**
	 * Thay đổi kích thước cột
	 */
	private void reSizeColumnTable() {
		TableColumnModel columnModel = tblTableRoom.getColumnModel();
		tblTableRoom.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		columnModel.getColumn(0).setPreferredWidth(70);
		columnModel.getColumn(1).setPreferredWidth(300);
		columnModel.getColumn(2).setPreferredWidth(370);
		columnModel.getColumn(3).setPreferredWidth(379);
		columnModel.getColumn(4).setPreferredWidth(360);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		columnModel.getColumn(0).setCellRenderer(centerRenderer);
		columnModel.getColumn(1).setCellRenderer(centerRenderer);
	}
}
