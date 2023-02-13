package PanelCustom;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.Naming;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
import Custom.ButtonImage;
import Custom.CustomUI;
import dao.DichVuDao;
import dao.LoaiDichVuDao;
import entity.DichVu;
import entity.LoaiDichVu;

public class PanelQuanLyDichVu extends JPanel implements ActionListener, MouseListener, ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnMainServices;
	private int width = 1580, height = 900;
	private JTextField txtServicesID;
	private JTextField txtServicesName;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private JLabel lblGiBn_2;
	private JLabel lblGiBn_3;
	private JFileChooser chooseFile;
	private JComboBox<String> cboServicesType;
	private JTextField txtBFieldServiceType;
	private JButton btnNewButton;
	private ButtonGradient btnAdd;
	private ButtonGradient btnUpdate;
	private ButtonGradient btnDelete;
	private AbstractButton btnRefresh;
	private ImageIcon imageService;
	private String fileImagePath;
	private DecimalFormat df = new DecimalFormat("#,###.##");
	private DefaultTableModel modelTableService;
	private JTable tblTableService;
	private JButton btnServicesDetail;
	private DichVuDao dichVuDao;
	private LoaiDichVuDao loaiDichVuDao;
	private List<LoaiDichVu> listServicesType = new ArrayList<LoaiDichVu>();
	private List<DichVu> listServices;
	private List<DichVu> listAllServices;
	private JDialogLoaiDichVu dialogLDV;
	private int sizeList = 0;

	public PanelQuanLyDichVu(DichVuDao dichVuDao, LoaiDichVuDao loaiDichVuDao) throws Exception {
		this.dichVuDao = dichVuDao;
		this.loaiDichVuDao = loaiDichVuDao;
		listAllServices = this.dichVuDao.getAllDichVu();

		setSize(width, height);
//		this.setResizable(false);
//		this.setUndecorated(true);
//		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(new Color(0, 0, 0, 0));
		this.setLayout(null);

		pnMainServices = new JPanel() {
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
		pnMainServices.setForeground(Color.GRAY);
		pnMainServices.setLayout(null);
		pnMainServices.setOpaque(false);
		pnMainServices.setBounds(0, 0, width, height);
		this.add(pnMainServices);

		JLabel lblNewLabel_1 = new JLabel("QUẢN LÝ DỊCH VỤ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(540, 20, 500, 40);
		lblNewLabel_1.setForeground(Color.white);
		lblNewLabel_1.setFont(new Font("Serif", Font.BOLD, 32));
		pnMainServices.add(lblNewLabel_1);

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
//				g2.setColor(Color.decode("#0eb289"));
				g2.setColor(Color.white);
				g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
//				g2.fillRect(0, 0, getWidth(), getHeight() - 20);
//				g2.fillRect(0, 0, getWidth() - 20, getHeight());
				setFont(new Font("Serif", Font.PLAIN, 20));
			}
		};
		pnInfor.setLayout(null);
		pnInfor.setOpaque(false);
		pnInfor.setBounds(30, 80, 1520, 280);
		pnMainServices.add(pnInfor);

		JLabel lblNewLabel = new JLabel("Mã dịch vụ:");
		CustomUI.getInstance().setCustomLabel(lblNewLabel);
		lblNewLabel.setBounds(70, 30, 140, 30);
		pnInfor.add(lblNewLabel);

		txtServicesID = new JTextField();
		CustomUI.getInstance().setCustomTextFieldOff(txtServicesID);
		txtServicesID.setBounds(210, 30, 250, 30);
		pnInfor.add(txtServicesID);

		txtServicesName = new JTextField();
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtServicesName);
		txtServicesName.setBounds(210, 85, 250, 30);
		pnInfor.add(txtServicesName);

		JLabel lblTnDchV = new JLabel("Tên dịch vụ:");
		CustomUI.getInstance().setCustomLabel(lblTnDchV);
		lblTnDchV.setBounds(70, 85, 140, 30);
		pnInfor.add(lblTnDchV);

		txtPrice = new JTextField();
		txtPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrice.setText("50000");
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtPrice);
		txtPrice.setBounds(780, 30, 250, 30);
		pnInfor.add(txtPrice);

		JLabel lblGiBn = new JLabel("Giá bán:");
		CustomUI.getInstance().setCustomLabel(lblGiBn);
		lblGiBn.setBounds(640, 30, 140, 30);
		pnInfor.add(lblGiBn);

		txtQuantity = new JTextField();
		txtQuantity.setText("10000");
		txtQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
		CustomUI.getInstance().setCustomTextFieldUnFocus(txtQuantity);
		txtQuantity.setBounds(210, 140, 250, 30);
		pnInfor.add(txtQuantity);

		lblGiBn_2 = new JLabel("Số lượng tồn:");
		CustomUI.getInstance().setCustomLabel(lblGiBn_2);
		lblGiBn_2.setBounds(70, 140, 140, 30);
		pnInfor.add(lblGiBn_2);

		lblGiBn_3 = new JLabel("Loại dịch vụ:");
		CustomUI.getInstance().setCustomLabel(lblGiBn_3);
		lblGiBn_3.setBounds(640, 85, 140, 30);
		pnInfor.add(lblGiBn_3);

		cboServicesType = new JComboBox<String>();
		CustomUI.getInstance().setCustomComboBox(cboServicesType);
		txtBFieldServiceType = CustomUI.getInstance().setCustomCBoxField(cboServicesType);
		cboServicesType.setBounds(780, 85, 250, 30);
		pnInfor.add(cboServicesType);

		btnNewButton = new ButtonImage("<HTML><p><u>Chọn ảnh</u></p></HTML>");
		btnNewButton.setBounds(1210, 30, 220, 220);
		pnInfor.add(btnNewButton);

		chooseFile = new JFileChooser(new File("./imgDichVu"));
		btnNewButton.addActionListener((e) -> {
			int x = chooseFile.showDialog(chooseFile, "Open");
			if (x == JFileChooser.APPROVE_OPTION) {
				File f = chooseFile.getSelectedFile();
				fileImagePath = f.getPath();
				imageService = new ImageIcon(fileImagePath);
				((ButtonImage) btnNewButton).setValue("");
				((ButtonImage) btnNewButton).setImageIcon(imageService);
			}
		});

		btnAdd = new ButtonGradient();
		btnAdd.setText("Thêm");
		btnAdd.setFont(CustomUI.fontButton);
		((ButtonGradient) btnAdd).setColor2(Color.decode("#0eb289"));
		((ButtonGradient) btnAdd).setColor1(Color.decode("#68D8B7"));
		btnAdd.setBounds(70, 215, 130, 40);
		pnInfor.add(btnAdd);

		btnUpdate = new ButtonGradient();
		btnUpdate.setText("Sửa");
		btnUpdate.setFont(CustomUI.fontButton);
		((ButtonGradient) btnUpdate).setColor2(Color.decode("#0eb289"));
		((ButtonGradient) btnUpdate).setColor1(Color.decode("#68D8B7"));
		btnUpdate.setBounds(250, 215, 130, 40);
		pnInfor.add(btnUpdate);

		btnDelete = new ButtonGradient();
		btnDelete.setText("Xóa");
		btnDelete.setFont(CustomUI.fontButton);
		((ButtonGradient) btnDelete).setColor2(Color.decode("#0eb289"));
		((ButtonGradient) btnDelete).setColor1(Color.decode("#68D8B7"));
		btnDelete.setBounds(430, 215, 130, 40);
		pnInfor.add(btnDelete);

		btnRefresh = new ButtonGradient();
		btnRefresh.setText("Làm mới");
		btnRefresh.setFont(CustomUI.fontButton);
		((ButtonGradient) btnRefresh).setColor2(Color.decode("#0eb289"));
		((ButtonGradient) btnRefresh).setColor1(Color.decode("#68D8B7"));
		btnRefresh.setBounds(610, 215, 130, 40);
		pnInfor.add(btnRefresh);

		btnServicesDetail = new JButton("Chi tiết");
		btnServicesDetail.setBounds(1030, 85, 90, 30);
		btnServicesDetail.setFont(new Font("Serif", Font.BOLD, 20));
		btnServicesDetail.setContentAreaFilled(false);
		btnServicesDetail.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnServicesDetail.setFocusPainted(false);
		btnServicesDetail.setForeground(Color.decode("#365F42"));
		btnServicesDetail.setBorder(null);
		pnInfor.add(btnServicesDetail);

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
		pnTable.setBounds(30, 380, 1520, 500);
		pnMainServices.add(pnTable);

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
		pnlTable.setBounds(20, 60, 1480, 400);
		pnlTable.setOpaque(false);

		String[] cols = { "STT", "Mã dịch vụ", "Tên dịch vụ", "Giá bán", "Số lượng", "Loại dịch vụ", "Hình ảnh" };
		modelTableService = new DefaultTableModel(cols, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int i, int i1) {
				return false;
			}
		};
		tblTableService = new JTable(modelTableService) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setFont(new Font("Serif", Font.PLAIN, 21));
			}

			public Class<?> getColumnClass(int column) {
				return (column == 6) ? Icon.class : Object.class;
			}
		};
		CustomUI.getInstance().setCustomTable(tblTableService);
		tblTableService.setRowHeight(121);
		tblTableService.setGridColor(Color.decode("#0eb289"));

		JScrollPane scrTable = CustomUI.getInstance().setCustomScrollPane(tblTableService);
		scrTable.setBounds(0, 0, 1497, 400);
		pnlTable.add(scrTable);
		pnTable.add(pnlTable);

		dialogLDV = new JDialogLoaiDichVu(this.loaiDichVuDao, null, true);

		txtBFieldServiceType.addMouseListener(this);
		tblTableService.addMouseListener(this);
		btnServicesDetail.addMouseListener(this);

		btnAdd.addActionListener(this);
		btnDelete.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnRefresh.addActionListener(this);
		btnServicesDetail.addActionListener(this);

		btnServicesDetail.addChangeListener(this);

		loadComBoBox();
		loadTable();

	}

	public static void main(String[] args) {

		SecurityManager ser = System.getSecurityManager();
		if (ser == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		DichVuDao dichVuDao = null;
		LoaiDichVuDao loaiDichVuDao = null;
		try {
			dichVuDao = (DichVuDao) Naming.lookup("rmi://192.168.1.104:1099//dichVuDao");
			loaiDichVuDao = (LoaiDichVuDao) Naming.lookup("rmi://192.168.1.104:1099//loaiDichVuDao");
			new PanelQuanLyDichVu(dichVuDao, loaiDichVuDao).setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnAdd)) {
			String message = "";
			if (validData()) {
				try {
					if (listAllServices.size() > 0) {
						DichVu getDvTheoTen = dichVuDao.getDichVuKhiBietTen(txtServicesName.getText().trim());
						if (getDvTheoTen != null) {
							if (listAllServices.contains(getDvTheoTen)) {
								if (listServices.contains(getDvTheoTen)) {
									message = "Dịch vụ " + txtServicesName.getText().trim() + "Đã tồn tại";
									JOptionPane.showMessageDialog(this, message);
								} else {
									DichVu temp = getServiceDataInForm();
									temp.setMaDichVu(getDvTheoTen.getMaDichVu());
									if (dichVuDao.updateDichVu(temp) && dichVuDao.restoreDichVu(temp.getTenDichVu())) {
										message = "Đã khôi phục và cập nhật thành công dịch vụ "
												+ txtServicesName.getText().trim();
										JOptionPane.showMessageDialog(this, message);
										loadTable();
									} else {
										message = "Khôi phục và cập nhật không thành công dịch vụ"
												+ txtServicesName.getText().trim();
										JOptionPane.showMessageDialog(this, message);
									}
								}
							}
						} else {
							txtServicesID.setText("");
							DichVu sercices = getServiceDataInForm();
							if (dichVuDao.addDichVu(sercices)) {
								message = "Thêm dịch vụ mới thành công";
								JOptionPane.showMessageDialog(this, message);
								listAllServices.add(sercices);
								sizeList = listAllServices.size();
								addRow(tblTableService.getRowCount() + 1, sercices);
								tblTableService.getSelectionModel().setSelectionInterval(
										tblTableService.getRowCount() - 1, tblTableService.getRowCount() - 1);
								tblTableService.scrollRectToVisible(tblTableService.getCellRect(
										tblTableService.getRowCount() - 1, tblTableService.getRowCount() - 1, true));
							} else {
								message = "Thêm dịch vụ thất bại";
								JOptionPane.showMessageDialog(this, message);
							}
						}
					} else {
						txtServicesID.setText("");
						DichVu sercices = getServiceDataInForm();
						if (dichVuDao.addDichVu(sercices)) {
							message = "Thêm dịch vụ mới thành công";
							JOptionPane.showMessageDialog(this, message);
							listAllServices.add(sercices);
							sizeList = listAllServices.size();
							addRow(tblTableService.getRowCount() + 1, sercices);
							tblTableService.getSelectionModel().setSelectionInterval(tblTableService.getRowCount() - 1,
									tblTableService.getRowCount() - 1);
							tblTableService.scrollRectToVisible(tblTableService.getCellRect(
									tblTableService.getRowCount() - 1, tblTableService.getRowCount() - 1, true));
						} else {
							message = "Thêm dịch vụ thất bại";
							JOptionPane.showMessageDialog(this, message);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if (o.equals(btnServicesDetail)) {
			dialogLDV.setVisible(true);

		}
		if (o.equals(btnDelete)) {
			String message = "";
			try {
				if (tblTableService.getSelectedRow() != -1 && txtServicesID.getText().length() > 0) {
					message = "Xác nhận xóa thông tin " + "Dịch vụ" + ": " + txtServicesName.getText();
					int choose = JOptionPane.showConfirmDialog(this, message,
							"Xác nhận cập nhật thông tin " + "Dịch vụ" + "", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (choose == JOptionPane.OK_OPTION) {
						if (dichVuDao.deleteDichVu(txtServicesID.getText())) {
							message = "Xóa dịch vụ thành công";
							JOptionPane.showMessageDialog(this, message);
							loadTable();
						} else {
							message = "Xóa dịch vụ thất bại";
							JOptionPane.showMessageDialog(this, message);
						}
					}
				} else {
					message = "Hãy chọn " + "Dịch vụ" + " mà bạn cần xóa thông tin";
					JOptionPane.showMessageDialog(this, message);
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (o.equals(btnUpdate)) {
			String message = "";
			if (validData()) {
				try {
					if (tblTableService.getSelectedRow() != -1 && txtServicesID.getText().length() > 0) {
						message = "Xác nhận cập nhật thông tin " + "Dịch vụ" + ": " + txtServicesName.getText();
						int choose = JOptionPane.showConfirmDialog(this, message,
								"Xác nhận cập nhật thông tin " + "Dịch vụ" + "", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE);
						if (choose == JOptionPane.OK_OPTION) {
							if (dichVuDao.updateDichVu(getServiceDataInForm())) {
								message = "Cập nhật dịch vụ thành công";
								JOptionPane.showMessageDialog(this, message);
								loadTable();
							} else {
								message = "Cập nhật dịch vụ thất bại";
								JOptionPane.showMessageDialog(this, message);
							}
						}
					} else {
						message = "Hãy chọn " + "Dịch vụ" + " mà bạn cần cập nhật  thông tin";
						JOptionPane.showMessageDialog(this, message);
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		}
		if (o.equals(btnRefresh)) {
			clearForm();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(txtBFieldServiceType)) {
			cboServicesType.showPopup();
		} else if (o.equals(tblTableService)) {
			int selectedRow = tblTableService.getSelectedRow();
			txtServicesID.setText(tblTableService.getValueAt(selectedRow, 1).toString().trim());
			txtServicesName.setText(tblTableService.getValueAt(selectedRow, 2).toString().trim());
			String priceStr = tblTableService.getValueAt(selectedRow, 3).toString().trim().replace(",", "");
			priceStr.replace(".", "");
			txtPrice.setText(priceStr);
			String quantityStr = tblTableService.getValueAt(selectedRow, 4).toString().trim().replace(",", "");
			txtQuantity.setText(quantityStr);
			cboServicesType.setSelectedItem(tblTableService.getValueAt(selectedRow, 5).toString().trim());
			ImageIcon img = null;
			try {
				img = new ImageIcon(dichVuDao
						.getDichVuKhiBietMa(tblTableService.getValueAt(selectedRow, 1).toString().trim()).getHinhAnh());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			((ButtonImage) btnNewButton).setImageIcon(img);
//			btnUpdate.setEnabledCustom(true);
//			btnAdd.setEnabledCustom(false);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(btnServicesDetail)) {
			btnServicesDetail.setForeground(Color.decode("#0eb289"));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(btnServicesDetail)) {
			btnServicesDetail.setForeground(Color.decode("#365F42"));
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Object o = e.getSource();
		if (o.equals(btnServicesDetail)) {
			if (!dialogLDV.isVisible()) {
				loadComBoBox();
			}
		}
	}

	private void clearForm() {
		cboServicesType.setSelectedIndex(0);
		txtPrice.setText("");
		txtQuantity.setText("");
		txtServicesID.setText("");
		txtServicesName.setText("");
	}

	/**
	 * Thêm dòng vào danh sách dịch vụ đang hiển thị
	 * 
	 * @param stt     {@code int}: số thứ tự
	 * @param service {@code DichVu}: dịch vụ cần được thêm
	 * 
	 */
	private void addRow(int stt, DichVu service) {
		String priceStr = df.format(service.getGiaBan());
		String quantityStr = df.format(service.getSoLuongTon());

		LoaiDichVu temp = null;
		try {
			temp = loaiDichVuDao.getLoaiDichVuKhiBietMa(service.getLoaiDichVu().getMaLoaiDichVu());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String serviceTypeName = temp.getTenLoaiDichVu();
		ImageIcon imageIcon = new ImageIcon(service.getHinhAnh());
		imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH));
		modelTableService.addRow(new Object[] { stt, addSpaceToString(service.getMaDichVu()),
				addSpaceToString(service.getTenDichVu()), addSpaceToString(priceStr), addSpaceToString(quantityStr),
				addSpaceToString(serviceTypeName), imageIcon });
		modelTableService.fireTableDataChanged();
	}

	public void loadComBoBox() {
		try {
			listServicesType = loaiDichVuDao.getDsLoaiDichVu();
			if (listServicesType != null) {
				loadServicesType((ArrayList<LoaiDichVu>) listServicesType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadTable() {
		reSizeColumnTable();
		if (listAllServices.size() > 0) {
			try {
				sizeList = listAllServices.size();
				listServices = dichVuDao.getDsDichVu();
				loadServiceList((ArrayList<DichVu>) listServices);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Thay đổi kích thước cột
	 */
	private void reSizeColumnTable() {
		TableColumnModel columnModel = tblTableService.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(70);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(250);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(200);
		columnModel.getColumn(5).setPreferredWidth(200);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

		columnModel.getColumn(0).setCellRenderer(centerRenderer);
		columnModel.getColumn(1).setCellRenderer(centerRenderer);
		columnModel.getColumn(3).setCellRenderer(rightRenderer);
		columnModel.getColumn(4).setCellRenderer(rightRenderer);
	}

	private String addSpaceToString(String str) {
		return " " + str + " ";
	}

	/**
	 * chuyển đổi thông tin trong form thành đối tượng DichVu
	 * 
	 * @return {@code DichVu}: dịch vụ
	 */
	private DichVu getServiceDataInForm() {
		try {
			String serviceId = "";
			if (txtServicesID.getText().length() <= 0) {
				int size = sizeList + 1;
				serviceId = "DV";
				for (int i = 0; i < 5 - Integer.toString(size).length(); i++) {
					serviceId += 0;
				}
				serviceId += size;
			} else {
				serviceId = txtServicesID.getText();
			}
			Icon icon = ((ButtonImage) btnNewButton).getImageIcon();

			BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = img.createGraphics();
			g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			icon.paintIcon(null, g2d, 0, 0);
			g2d.dispose();

			byte[] bytes = null;

			try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
				ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
				try {
					ImageIO.write(img, "jpg", ios);
					// Set a flag to indicate that the write was successful
				} finally {
					ios.close();
				}
				bytes = baos.toByteArray();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			DichVu dv = new DichVu(serviceId, txtServicesName.getText(), Double.parseDouble(txtPrice.getText()),
					Integer.parseInt(txtQuantity.getText()), bytes);
			dv.setLoaiDichVu(listServicesType.get(cboServicesType.getSelectedIndex()));
			return dv;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * Kiểm tra thông tin trong form
	 * 
	 * @return {@code boolean}: kết quả trả về của quá trình kiểm tra thông tin
	 *         <ul>
	 *         <li>Nếu hợp lệ thì trả về {@code true}</li>
	 *         <li>Nếu không hợp lệ thì trả về {@code false}</li>
	 *         </ul>
	 */
	private boolean validData() {

		return true;
	}

	private void loadServiceList(ArrayList<DichVu> serviceList) {
		modelTableService.getDataVector().removeAllElements();
		modelTableService.fireTableDataChanged();
		int i = 1;
		for (DichVu item : serviceList) {
			addRow(i++, item);
		}
	}

	private void loadServicesType(ArrayList<LoaiDichVu> serviceTypeList) {
		cboServicesType.removeAllItems();
		for (LoaiDichVu loaiDichVu : serviceTypeList) {
			cboServicesType.addItem(loaiDichVu.getTenLoaiDichVu());
		}

	}

}
