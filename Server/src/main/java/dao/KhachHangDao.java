package dao;

import java.rmi.Remote;
import java.util.List;

import entity.KhachHang;

public interface KhachHangDao extends Remote {
	public boolean addKhachHang(KhachHang nv) throws Exception;
	public boolean deleteKhachHang(String maKhachHang) throws Exception;
	public boolean updateKhachHang(KhachHang nv) throws Exception;
	public List<KhachHang> getDsKhachHang() throws Exception;
	public List<KhachHang> getAllKhachHang() throws Exception;
	public KhachHang getKhachHangKhiBietMa(String maKhachHang) throws Exception;
	public KhachHang getKhachHangKhiBietSDT(String sdt) throws Exception;
}
