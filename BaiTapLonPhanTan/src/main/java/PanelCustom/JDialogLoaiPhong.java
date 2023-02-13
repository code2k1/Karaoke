package PanelCustom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Custom.ButtonGradient;
import Custom.CustomUI;
import dao.LoaiPhongDao;
import entity.LoaiPhong;

public class JDialogLoaiPhong extends JDialog implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width = 650, height = 800;
	private Icon backWhiteIcon = new ImageIcon("img/backWhite.png");
	private Icon backRedIcon = new ImageIcon("img/backRed.png");
	private JPanel pnMainRoomType;
	private ButtonGradient btnAdd;
	private ButtonGradient btnUpdate;
	private ButtonGradient btnDelete;
	private DefaultTableModel modelTableRoomType;
	private JTable tblTableRoomType;
	private JScrollPane scrTable;
	private JTextField txtRoomTypeId;
	private JTextField txtRoomTypeName;
	private JTextField txtPrice;
	private LoaiPhongDao loaiPhongDao;
	private List<LoaiPhong> listRoomType;
	private List<LoaiPhong> listAllRoomType;
	private int sizeList = 0;
	private JButton btnBack;
	private DecimalFormat df = new DecimalFormat("#,###.##");

	public JDialogLoaiPhong(LoaiPhongDao loaiPhongDao, Frame owner, boolean modal) throws Exception {
		super(owner, modal);

		this.loaiPhongDao = loaiPhongDao;
		listAllRoomType = this.loaiPhongDao.getAllLoaiPhong();

		this.setSize(width, height);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(new Color(0, 0, 0, 0));
		getContentPane().setLayout(null);

		pnMainRoomType = new JPanel() {
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

				g2.setColor(Color.decode("#10624F"));
				g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);

				g2.setColor(Color.decode("#0eb289"));
				g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, 30, 30);

//					g2.fillRect(0, 0, getWidth(), getHeight() - 20);
//					g2.fillRect(0, 0, getWidth() - 20, getHeight());
			}
		};
		pnMainRoomType.setForeground(Color.GRAY);
		pnMainRoomType.setLayout(null);
		pnMainRoomType.setOpaque(false);
		pnMainRoomType.setBounds(0, 0, width, height);
		getContentPane().add(pnMainRoomType);

		JLabel lblNewLabel_1 = new JLabel("QUẢN LÝ LOẠI PHÒNG");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(125, 20, 400, 40);
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 32));
		pnMainRoomType.add(lblNewLabel_1);

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
//					g2.setColor(Color.decode("#0eb289"));
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
//					g2.fillRect(0, 0, getWidth(), getHeight() - 20);
//					g2.fillRect(0, 0, getWidth() - 20, getHeight());
				setFont(new Font("Serif", Font.PLAIN, 20));
			}
		};
		pnInfor.setLayout(null);
		pnInfor.setOpaque(false);
		pnInfor.setBounds(25, 80, 600, 250);
		pnMainRoomType.add(pnInfor);

		JLabel lblNewLabel = new JLabel("Mã Loại phòng:");
		CustomUI.getInstance().setCustomLabel(lblNewLabel);
		lblNewLabel.setBounds(85, 29, 180, 30);
		pnInfor.add(lblNewLabel);

		btnBack = new JButton(backWhiteIcon);
		btnBack.setFont(new Font("Serif", Font.BOLD, 18));
		btnBack.setBounds(610, 10, 30, 30);
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);
		btnBack.setBorder(null);
		pnMainRoomType.add(btnBack);

		txtRoomTypeId = new JTextField();
		CustomUI.getInstance().setCustomTextFieldOff(txtRoomTypeId);
		txtRoomTypeId.setBounds(265, 30, 250, 30);
		pnInfor.add(txtRoomTypeId);

		txtRoomTypeName = new JTextField();
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtRoomTypeName);
		txtRoomTypeName.setBounds(265, 80, 250, 30);
		pnInfor.add(txtRoomTypeName);

		JLabel lblTnDchV = new JLabel("Tên loại phòng:");
		CustomUI.getInstance().setCustomLabel(lblTnDchV);
		lblTnDchV.setBounds(85, 80, 180, 30);
		pnInfor.add(lblTnDchV);

		txtPrice = new JTextField();
		txtPrice.setText("100000");
		txtPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtPrice);
		txtPrice.setBounds(265, 130, 250, 30);
		pnInfor.add(txtPrice);

		JLabel lblTnDchV_1 = new JLabel("Giá tiền:");
		CustomUI.getInstance().setCustomLabel(lblTnDchV_1);
		lblTnDchV_1.setBounds(85, 130, 180, 30);
		pnInfor.add(lblTnDchV_1);

		btnAdd = new ButtonGradient();
		btnAdd.setText("Thêm");
		btnAdd.setFont(CustomUI.fontButton);
		((ButtonGradient) btnAdd).setColor2(Color.decode("#0eb289"));
		((ButtonGradient) btnAdd).setColor1(Color.decode("#68D8B7"));
		btnAdd.setBounds(60, 185, 130, 40);
		pnInfor.add(btnAdd);

		btnUpdate = new ButtonGradient();
		btnUpdate.setText("Sửa");
		btnUpdate.setFont(CustomUI.fontButton);
		((ButtonGradient) btnUpdate).setColor2(Color.decode("#0eb289"));
		((ButtonGradient) btnUpdate).setColor1(Color.decode("#68D8B7"));
		btnUpdate.setBounds(235, 185, 130, 40);
		pnInfor.add(btnUpdate);

		btnDelete = new ButtonGradient();
		btnDelete.setText("Xóa");
		btnDelete.setFont(CustomUI.fontButton);
		((ButtonGradient) btnDelete).setColor2(Color.decode("#0eb289"));
		((ButtonGradient) btnDelete).setColor1(Color.decode("#68D8B7"));
		btnDelete.setBounds(410, 185, 130, 40);
		pnInfor.add(btnDelete);

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
		pnTable.setBounds(30, 350, 600, 440);
		pnMainRoomType.add(pnTable);

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
		pnlTable.setBounds(10, 60, 580, 360);
		pnlTable.setOpaque(false);

		String[] cols = { "STT", "Mã loại phòng", "Tên loại phòng", "Giá tiền" };
		modelTableRoomType = new DefaultTableModel(cols, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};
		tblTableRoomType = new JTable(modelTableRoomType) {
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
		CustomUI.getInstance().setCustomTable(tblTableRoomType);
		tblTableRoomType.setRowHeight(20);
		tblTableRoomType.setGridColor(Color.decode("#0eb289"));

		scrTable = CustomUI.getInstance().setCustomScrollPane(tblTableRoomType);
		scrTable.setBounds(0, 0, 596, 360);
		pnlTable.add(scrTable);
		pnTable.add(pnlTable);

		btnBack.addMouseListener(this);
		tblTableRoomType.addMouseListener(this);

		btnBack.addActionListener(this);
		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);

		allLoaded();

	}

	private void allLoaded() {

		reSizeColumnTable();
		if (listAllRoomType.size() >0) {
			try {
				sizeList = listAllRoomType.size();
				listRoomType = loaiPhongDao.getDsLoaiPhong();
				loadRoomTypeList((ArrayList<LoaiPhong>) listRoomType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
	
	private void loadRoomTypeList(ArrayList<LoaiPhong> listRoomType2) {
		modelTableRoomType.getDataVector().removeAllElements();
		modelTableRoomType.fireTableDataChanged();
		int i = 1;
		for (LoaiPhong loaiPhong : listRoomType2) {
			addRow(i++, loaiPhong);
		}
	}
	
	private void reSizeColumnTable() {
		TableColumnModel columnModel = tblTableRoomType.getColumnModel();
		tblTableRoomType.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(151);
		columnModel.getColumn(2).setPreferredWidth(225);
		columnModel.getColumn(3).setPreferredWidth(150);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

		columnModel.getColumn(0).setCellRenderer(centerRenderer);
		columnModel.getColumn(1).setCellRenderer(centerRenderer);
		columnModel.getColumn(3).setCellRenderer(rightRenderer);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnBack)) {
			this.dispose();
			this.setVisible(false);
		}
		if (o.equals(btnAdd)) {
			String message = "";
			if (validData()) {
				try {
					if (listAllRoomType.size() > 0) {
						LoaiPhong getLdvTheoTen = loaiPhongDao.getLoaiPhongKhiBietTen(txtRoomTypeName.getText().trim());
						if (getLdvTheoTen != null) {
							if (listAllRoomType.contains(getLdvTheoTen)) {
								if (!listRoomType.contains(getLdvTheoTen)) {
									LoaiPhong temp = getRoomTypeDataInForm();
									temp.setMaLoaiPhong(getLdvTheoTen.getMaLoaiPhong());
									if (loaiPhongDao.updateLoaiPhong(temp)
											&& loaiPhongDao.restoreLoaiPhong(temp.getTenLoaiPhong())) {
										message = "Đã khôi phục và cập nhật thành công loại phòng "
												+ txtRoomTypeName.getText().trim();
										JOptionPane.showMessageDialog(this, message);
										allLoaded();
									} else {
										message = "Khôi phục và cập nhật không thành công loại phòng"
												+ txtRoomTypeName.getText().trim();
										JOptionPane.showMessageDialog(this, message);
									}
								} else {
									message = "Loại phòng " + txtRoomTypeName.getText().trim() + "Đã tồn tại";
									JOptionPane.showMessageDialog(this, message);
								}
							}
						} else {
							txtRoomTypeId.setText("");
							LoaiPhong ldv = getRoomTypeDataInForm();
							if (loaiPhongDao.addLoaiPhong(ldv)) {
								message = "Thêm loại phòng mới thành công";
								JOptionPane.showMessageDialog(this, message);
								listAllRoomType.add(ldv);
								sizeList = listAllRoomType.size();
								addRow(tblTableRoomType.getRowCount() + 1, ldv);
								tblTableRoomType.getSelectionModel().setSelectionInterval(
										tblTableRoomType.getRowCount() - 1, tblTableRoomType.getRowCount() - 1);
								tblTableRoomType.scrollRectToVisible(
										tblTableRoomType.getCellRect(tblTableRoomType.getRowCount() - 1,
												tblTableRoomType.getRowCount() - 1, true));
							} else {
								message = "Thêm loại phòng thất bại";
								JOptionPane.showMessageDialog(this, message);
							}
						}
					} else {
						txtRoomTypeId.setText("");
						LoaiPhong ldv = getRoomTypeDataInForm();
						if (loaiPhongDao.addLoaiPhong(ldv)) {
							message = "Thêm loại phòng mới thành công";
							JOptionPane.showMessageDialog(this, message);
							listAllRoomType.add(ldv);
							sizeList = listAllRoomType.size();
							addRow(tblTableRoomType.getRowCount() + 1, ldv);
							tblTableRoomType.getSelectionModel().setSelectionInterval(
									tblTableRoomType.getRowCount() - 1, tblTableRoomType.getRowCount() - 1);
							tblTableRoomType.scrollRectToVisible(
									tblTableRoomType.getCellRect(tblTableRoomType.getRowCount() - 1,
											tblTableRoomType.getRowCount() - 1, true));
						} else {
							message = "Thêm loại phòng thất bại";
							JOptionPane.showMessageDialog(this, message);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if (o.equals(btnDelete)) {
			String message = "";
			try {
				if (tblTableRoomType.getSelectedRow() != -1 && txtRoomTypeId.getText().length() > 0) {
					message = "Xác nhận xóa thông tin " + "Loại phòng" + ": " + txtRoomTypeName.getText();
					int choose = JOptionPane.showConfirmDialog(this, message,
							"Xác nhận cập nhật thông tin " + "Loại phòng" + "", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (choose == JOptionPane.OK_OPTION) {
						if (loaiPhongDao.deleteLoaiPhong(txtRoomTypeId.getText())) {
							message = "Xóa loại phòng thành công";
							JOptionPane.showMessageDialog(this, message);
							allLoaded();
						} else {
							message = "Xóa loại phòng thất bại";
							JOptionPane.showMessageDialog(this, message);
						}
					}
				} else {
					message = "Hãy chọn " + "Loại phòng" + " mà bạn cần xóa thông tin";
					JOptionPane.showMessageDialog(this, message);
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (o.equals(btnUpdate)) {
			String message = "";
			try {
				if (tblTableRoomType.getSelectedRow() != -1 && txtRoomTypeId.getText().length() > 0) {
					if (validData()) {
						message = "Xác nhận cập nhật thông tin " + "Loại phòng" + ": "
								+ txtRoomTypeName.getText();
						int choose = JOptionPane.showConfirmDialog(this, message,
								"Xác nhận cập nhật thông tin " + "Loại phòng" + "", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (choose == JOptionPane.OK_OPTION) {
							if (loaiPhongDao.updateLoaiPhong(getRoomTypeDataInForm())) {
								message = "Cập nhật loại phòng thành công";
								JOptionPane.showMessageDialog(this, message);
								allLoaded();
							} else {
								message = "Cập nhật loại phòng thất bại";
								JOptionPane.showMessageDialog(this, message);
							}
						}
					}
				} else {
					message = "Hãy chọn " + "Loại phòng" + " mà bạn cần cập nhật  thông tin";
					JOptionPane.showMessageDialog(this, message);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	private String addSpaceToString(String str) {
		return " " + str + " ";
	}

	private void addRow(int stt, LoaiPhong RoomType) {
		String priceStr = df.format(RoomType.getGiaTien());
		modelTableRoomType.addRow(new Object[] { stt, addSpaceToString(RoomType.getMaLoaiPhong()),
				addSpaceToString(RoomType.getTenLoaiPhong()),priceStr});
		modelTableRoomType.fireTableDataChanged();
	}
	
	private LoaiPhong getRoomTypeDataInForm() {
		try {
			String RoomTypeId = "";
			if (txtRoomTypeId.getText().length() <= 0) {
				int size = sizeList + 1;
				System.out.println(size);
				RoomTypeId = "LP";
				for (int i = 0; i < 3 - Integer.toString(size).length(); i++) {
					RoomTypeId += 0;
				}
				RoomTypeId += size;
			} else {
				RoomTypeId = txtRoomTypeId.getText().trim();
			}

			LoaiPhong loaiPhong = new LoaiPhong(RoomTypeId, txtRoomTypeName.getText().trim(),
					Double.parseDouble(txtPrice.getText().trim()));

			return loaiPhong;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

	private boolean validData() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblTableRoomType)) {
			int selectRow = tblTableRoomType.getSelectedRow();
			txtRoomTypeId.setText(tblTableRoomType.getValueAt(selectRow, 1).toString().trim());
			txtRoomTypeName.setText(tblTableRoomType.getValueAt(selectRow, 2).toString().trim());
			String priceStr = tblTableRoomType.getValueAt(selectRow, 3).toString().trim().replace(",", "");
			priceStr.replace(".", "");
			txtPrice.setText(priceStr);
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
		if (o.equals(btnBack)) {
			btnBack.setIcon(backRedIcon);
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(btnBack)) {
			btnBack.setIcon(backWhiteIcon);
		}

	}
}
