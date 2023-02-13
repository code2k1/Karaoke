package app;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.DichVuDao;
import dao.HoaDonDao;
import dao.KhachHangDao;
import dao.LoaiDichVuDao;
import dao.LoaiPhongDao;
import dao.NhanVienDao;
import dao.PhongDao;
import dao.Impl.DichVuDaoImpl;
import dao.Impl.HoaDonDaoImpl;
import dao.Impl.KhachHangDaoImpl;
import dao.Impl.LoaiDichVuDaoImpl;
import dao.Impl.LoaiPhongDaoImpl;
import dao.Impl.NhanVienDaoImpl;
import dao.Impl.PhongDaoImpl;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;

public class Server {

	public static void main(String[] args) {
		SecurityManager ser = System.getSecurityManager();
		if (ser == null) {
			System.setProperty("java.security.policy", "policy/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		try {
			LocateRegistry.createRegistry(1199);
			DichVuDao dichVuDao = new DichVuDaoImpl();
			Naming.bind("rmi://172.16.20.170:1199//dichVuDao", dichVuDao);
			LoaiDichVuDao loaiDichVuDao = new LoaiDichVuDaoImpl();
			Naming.bind("rmi://172.16.20.170:1199//loaiDichVuDao", loaiDichVuDao);
			PhongDao phongDao = new PhongDaoImpl();
			Naming.bind("rmi://172.16.20.170:1199//phongDao", phongDao);
			LoaiPhongDao loaiPhongDao = new LoaiPhongDaoImpl();
			Naming.bind("rmi://172.16.20.170:1199//loaiPhongDao", loaiPhongDao);
			NhanVienDao nhanVienDao = new NhanVienDaoImpl();
			Naming.bind("rmi://172.16.20.170:1199//nhanVienDao", nhanVienDao);
			KhachHangDao khachHangDao = new KhachHangDaoImpl();
			Naming.bind("rmi://172.16.20.170:1199//khachHangDao", khachHangDao);
			HoaDonDao hoaDonDao = new HoaDonDaoImpl();
			Naming.bind("rmi://172.16.20.170:1199//hoaDonDao", hoaDonDao);
			
//			NhanVien temp = new NhanVien("NV0001", "Võ Minh Hiếu", true, new Date(2001-1900, 12, Calendar.DECEMBER),"312471788","0377723460","Quản lý", 5000000d);
//			temp.setTaiKhoan(new TaiKhoan("code2k1","AnhNongDan"));
//			nhanVienDao.addNhanVien(temp);
			
//			NhanVien nv =nhanVienDao.getNhanVienKhiBietTenTK("code2k1");
//			List<NhanVien> nv =nhanVienDao.getAllNhanVien();
//			System.out.println(nv);
			System.out.println("Server start");

//			KhachHang kh = new KhachHang("KH00002", "Võ Hoàng Nam Phương", "0377723462", new Date(2001 - 1900, 3, 24),
//					false);
//			khachHangDao.addKhachHang(kh);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
