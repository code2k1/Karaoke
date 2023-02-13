package dao;

import java.rmi.Remote;
import java.util.List;

import entity.NhanVien;

public interface NhanVienDao extends Remote{
	public boolean addNhanVien(NhanVien nv)throws Exception;
	public boolean deleteNhanVien(String maNhanVien)throws Exception;
	public boolean updateNhanVien(NhanVien nv)throws Exception;
	public List<NhanVien> getDsNhanVien()throws Exception;
	public List<NhanVien> getAllNhanVien()throws Exception;
	public NhanVien getNhanVienKhiBietMa(String maNhanVien)throws Exception;
	public NhanVien getNhanVienKhiBietTenTK(String tenTaiKhoan)throws Exception;
	
}
