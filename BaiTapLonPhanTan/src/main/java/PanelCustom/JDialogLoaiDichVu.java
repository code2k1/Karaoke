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
import dao.LoaiDichVuDao;
import entity.LoaiDichVu;

public class JDialogLoaiDichVu extends JDialog implements MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon backWhiteIcon = new ImageIcon("img/backWhite.png");
	private Icon backRedIcon = new ImageIcon("img/backRed.png");
	private JPanel pnMainServicesType;
	private int width = 650, height = 800;
	private JTextField txtServicesTypeId;
	private JTextField txtServicesTypeName;
	private DefaultTableModel modelTableServicesType;
	private JTable tblTableServicesType;
	private JButton btnBack;
	private JScrollPane scrTable;
	private ButtonGradient btnAdd;
	private ButtonGradient btnUpdate;
	private ButtonGradient btnDelete;
	private LoaiDichVuDao loaiDichVuDao;
	private List<LoaiDichVu> listServicesType;
	private List<LoaiDichVu> listAllServicesType;
	private int sizeList = 0;

	public JDialogLoaiDichVu(LoaiDichVuDao loaiDichVuDao, Frame owner, boolean modal) throws Exception {
		super(owner, "", modal);

		this.loaiDichVuDao = loaiDichVuDao;
		listAllServicesType = loaiDichVuDao.getAllLoaiDichVu();

		this.setSize(width, height);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLocationRelativeTo(owner);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setBackground(new Color(0, 0, 0, 0));
		getContentPane().setLayout(null);

		pnMainServicesType = new JPanel() {
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
		pnMainServicesType.setForeground(Color.GRAY);
		pnMainServicesType.setLayout(null);
		pnMainServicesType.setOpaque(false);
		pnMainServicesType.setBounds(0, 0, width, height);
		getContentPane().add(pnMainServicesType);

		JLabel lblNewLabel_1 = new JLabel("QUẢN LÝ LOẠI DỊCH VỤ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(125, 20, 400, 40);
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 32));
		pnMainServicesType.add(lblNewLabel_1);

		btnBack = new JButton(backWhiteIcon);
		btnBack.setFont(new Font("Serif", Font.BOLD, 18));
		btnBack.setBounds(610, 10, 30, 30);
		btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);
		btnBack.setBorder(null);
		pnMainServicesType.add(btnBack);

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
		pnInfor.setBounds(25, 80, 600, 210);
		pnMainServicesType.add(pnInfor);

		JLabel lblNewLabel = new JLabel("Mã loại dịch vụ:");
		CustomUI.getInstance().setCustomLabel(lblNewLabel);
		lblNewLabel.setBounds(85, 30, 180, 30);
		pnInfor.add(lblNewLabel);

		txtServicesTypeId = new JTextField();
		CustomUI.getInstance().setCustomTextFieldOff(txtServicesTypeId);
		txtServicesTypeId.setBounds(265, 30, 250, 30);
		pnInfor.add(txtServicesTypeId);

		txtServicesTypeName = new JTextField();
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtServicesTypeName);
		txtServicesTypeName.setBounds(265, 88, 250, 30);
		pnInfor.add(txtServicesTypeName);

		JLabel lblTnDchV = new JLabel("Tên loại dịch vụ:");
		CustomUI.getInstance().setCustomLabel(lblTnDchV);
		lblTnDchV.setBounds(85, 88, 180, 30);
		pnInfor.add(lblTnDchV);

		btnAdd = new ButtonGradient();
		btnAdd.setText("Thêm");
		btnAdd.setFont(CustomUI.fontButton);
		((ButtonGradient) btnAdd).setColor2(Color.decode("#0eb289"));
		((ButtonGradient) btnAdd).setColor1(Color.decode("#68D8B7"));
		btnAdd.setBounds(60, 148, 130, 40);
		pnInfor.add(btnAdd);

		btnUpdate = new ButtonGradient();
		btnUpdate.setText("Sửa");
		btnUpdate.setFont(CustomUI.fontButton);
		((ButtonGradient) btnUpdate).setColor2(Color.decode("#0eb289"));
		((ButtonGradient) btnUpdate).setColor1(Color.decode("#68D8B7"));
		btnUpdate.setBounds(235, 148, 130, 40);
		pnInfor.add(btnUpdate);

		btnDelete = new ButtonGradient();
		btnDelete.setText("Xóa");
		btnDelete.setFont(CustomUI.fontButton);
		((ButtonGradient) btnDelete).setColor2(Color.decode("#0eb289"));
		((ButtonGradient) btnDelete).setColor1(Color.decode("#68D8B7"));
		btnDelete.setBounds(410, 148, 130, 40);
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
		pnTable.setBounds(30, 320, 600, 450);
		pnMainServicesType.add(pnTable);

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
		pnlTable.setBounds(10, 60, 580, 370);
		pnlTable.setOpaque(false);

		String[] cols = { "STT", "Mã loại dịch vụ", "Tên loại dịch vụ" };
		modelTableServicesType = new DefaultTableModel(cols, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};
		tblTableServicesType = new JTable(modelTableServicesType) {
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
		CustomUI.getInstance().setCustomTable(tblTableServicesType);
		tblTableServicesType.setRowHeight(20);
		tblTableServicesType.setGridColor(Color.decode("#0eb289"));

		scrTable = CustomUI.getInstance().setCustomScrollPane(tblTableServicesType);
		scrTable.setBounds(0, 0, 596, 370);
		pnlTable.add(scrTable);
		pnTable.add(pnlTable);

		btnBack.addMouseListener(this);
		tblTableServicesType.addMouseListener(this);

		btnBack.addActionListener(this);
		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);

		allLoaded();

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
					if (listAllServicesType.size() > 0) {
						LoaiDichVu getLdvTheoTen = loaiDichVuDao
								.getLoaiDichVuKhiBietTen(txtServicesTypeName.getText().trim());
						if (getLdvTheoTen != null) {
							if (listAllServicesType.contains(getLdvTheoTen)) {
								if (!listServicesType.contains(getLdvTheoTen)) {
									LoaiDichVu temp = getServicesTypeDataInForm();
									temp.setMaLoaiDichVu(getLdvTheoTen.getMaLoaiDichVu());
									if (loaiDichVuDao.updateLoaiDichVu(temp)
											&& loaiDichVuDao.restoreLoaiDichVu(temp.getTenLoaiDichVu())) {
										message = "Đã khôi phục và cập nhật thành công loại dịch vụ "
												+ txtServicesTypeName.getText().trim();
										JOptionPane.showMessageDialog(this, message);
										allLoaded();
									} else {
										message = "Khôi phục và cập nhật không thành công loại dịch vụ"
												+ txtServicesTypeName.getText().trim();
										JOptionPane.showMessageDialog(this, message);
									}
								} else {
									message = "Loại dịch vụ " + txtServicesTypeName.getText().trim() + "Đã tồn tại";
									JOptionPane.showMessageDialog(this, message);
								}
							}
						} else {
							txtServicesTypeId.setText("");
							LoaiDichVu ldv = getServicesTypeDataInForm();
							if (loaiDichVuDao.addLoaiDichVu(ldv)) {
								message = "Thêm loại dịch vụ mới thành công";
								JOptionPane.showMessageDialog(this, message);
								listAllServicesType.add(ldv);
								sizeList = listAllServicesType.size();
								addRow(tblTableServicesType.getRowCount() + 1, ldv);
								tblTableServicesType.getSelectionModel().setSelectionInterval(
										tblTableServicesType.getRowCount() - 1, tblTableServicesType.getRowCount() - 1);
								tblTableServicesType.scrollRectToVisible(
										tblTableServicesType.getCellRect(tblTableServicesType.getRowCount() - 1,
												tblTableServicesType.getRowCount() - 1, true));
							} else {
								message = "Thêm loại dịch vụ thất bại";
								JOptionPane.showMessageDialog(this, message);
							}
						}
					} else {
						txtServicesTypeId.setText("");
						LoaiDichVu ldv = getServicesTypeDataInForm();
						if (loaiDichVuDao.addLoaiDichVu(ldv)) {
							message = "Thêm loại dịch vụ mới thành công";
							JOptionPane.showMessageDialog(this, message);
							listAllServicesType.add(ldv);
							sizeList = listAllServicesType.size();
							addRow(tblTableServicesType.getRowCount() + 1, ldv);
							tblTableServicesType.getSelectionModel().setSelectionInterval(
									tblTableServicesType.getRowCount() - 1, tblTableServicesType.getRowCount() - 1);
							tblTableServicesType.scrollRectToVisible(
									tblTableServicesType.getCellRect(tblTableServicesType.getRowCount() - 1,
											tblTableServicesType.getRowCount() - 1, true));
						} else {
							message = "Thêm loại dịch vụ thất bại";
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
				if (tblTableServicesType.getSelectedRow() != -1 && txtServicesTypeId.getText().length() > 0) {
					message = "Xác nhận xóa thông tin " + "Loại dịch vụ" + ": " + txtServicesTypeName.getText();
					int choose = JOptionPane.showConfirmDialog(this, message,
							"Xác nhận cập nhật thông tin " + "Loại dịch vụ" + "", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (choose == JOptionPane.OK_OPTION) {
						if (loaiDichVuDao.deleteLoaiDichVu(txtServicesTypeId.getText())) {
							message = "Xóa loại dịch vụ thành công";
							JOptionPane.showMessageDialog(this, message);
							allLoaded();
						} else {
							message = "Xóa loại dịch vụ thất bại";
							JOptionPane.showMessageDialog(this, message);
						}
					}
				} else {
					message = "Hãy chọn " + "Loại dịch vụ" + " mà bạn cần xóa thông tin";
					JOptionPane.showMessageDialog(this, message);
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (o.equals(btnUpdate)) {
			String message = "";
			try {
				if (tblTableServicesType.getSelectedRow() != -1 && txtServicesTypeId.getText().length() > 0) {
					if (validData()) {
						message = "Xác nhận cập nhật thông tin " + "Loại dịch vụ" + ": "
								+ txtServicesTypeName.getText();
						int choose = JOptionPane.showConfirmDialog(this, message,
								"Xác nhận cập nhật thông tin " + "Loại dịch vụ" + "", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (choose == JOptionPane.OK_OPTION) {
							if (loaiDichVuDao.updateLoaiDichVu(getServicesTypeDataInForm())) {
								message = "Cập nhật loại dịch vụ thành công";
								JOptionPane.showMessageDialog(this, message);
								allLoaded();
							} else {
								message = "Cập nhật loại dịch vụ thất bại";
								JOptionPane.showMessageDialog(this, message);
							}
						}
					}
				} else {
					message = "Hãy chọn " + "Loại dịch vụ" + " mà bạn cần cập nhật  thông tin";
					JOptionPane.showMessageDialog(this, message);
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	private boolean validData() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(tblTableServicesType)) {
			int selectRow = tblTableServicesType.getSelectedRow();
			txtServicesTypeId.setText(tblTableServicesType.getValueAt(selectRow, 1).toString().trim());
			txtServicesTypeName.setText(tblTableServicesType.getValueAt(selectRow, 2).toString().trim());
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

	/**
	 * chạy tất cả các hàm khi bắt đầu chạy form
	 */
	public void allLoaded() {
		reSizeColumnTable();
		if (listAllServicesType.size() > 0) {
			try {
				sizeList = listAllServicesType.size();
				listServicesType = loaiDichVuDao.getDsLoaiDichVu();
				loadServicesTypeList((ArrayList<LoaiDichVu>) listServicesType);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void loadServicesTypeList(ArrayList<LoaiDichVu> list) {
		modelTableServicesType.getDataVector().removeAllElements();
		modelTableServicesType.fireTableDataChanged();
		int i = 1;
		for (LoaiDichVu loaiDichVu : list) {
			addRow(i++, loaiDichVu);
		}
	}

	private void addRow(int stt, LoaiDichVu servicesType) {
		modelTableServicesType.addRow(new Object[] { stt, addSpaceToString(servicesType.getMaLoaiDichVu()),
				addSpaceToString(servicesType.getTenLoaiDichVu()) });
		modelTableServicesType.fireTableDataChanged();
	}

	private String addSpaceToString(String str) {
		return " " + str + " ";
	}

	/**
	 * Thay đổi kích thước cột
	 */
	private void reSizeColumnTable() {
		TableColumnModel columnModel = tblTableServicesType.getColumnModel();
		tblTableServicesType.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		columnModel.getColumn(0).setPreferredWidth(70);
		columnModel.getColumn(1).setPreferredWidth(251);
		columnModel.getColumn(2).setPreferredWidth(255);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		columnModel.getColumn(0).setCellRenderer(centerRenderer);
		columnModel.getColumn(1).setCellRenderer(centerRenderer);

	}

	/**
	 * chuyển đổi thông tin trong form thành đối tượng DichVu
	 * 
	 * @return {@code DichVu}: dịch vụ
	 */
	private LoaiDichVu getServicesTypeDataInForm() {
		try {
			String servicesTypeId = "";
			if (txtServicesTypeId.getText().length() <= 0) {
				int size = sizeList + 1;
				servicesTypeId = "LDV";
				for (int i = 0; i < 4 - Integer.toString(size).length(); i++) {
					servicesTypeId += 0;
				}
				servicesTypeId += size;
			} else {
				servicesTypeId = txtServicesTypeId.getText().trim();
			}

			LoaiDichVu ldv = new LoaiDichVu(servicesTypeId, txtServicesTypeName.getText());

			return ldv;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
