package dao;

import java.rmi.Remote;
import java.util.List;

import entity.DichVu;

public interface DichVuDao extends Remote{
	public boolean addDichVu(DichVu dv)throws Exception;
	public boolean updateDichVu(DichVu dv)throws Exception;
	public boolean deleteDichVu(String maDichVu)throws Exception;
	public List<DichVu> getDsDichVu()throws Exception;
	public List<DichVu> getDsDichVuTrongKhoan(int skip, int limit)throws Exception;
	public List<DichVu> getAllDichVu()throws Exception;
	public DichVu getDichVuKhiBietTen(String tenDichVu)throws Exception;
	public DichVu getDichVuKhiBietMa(String maDichVu)throws Exception;
	public boolean restoreDichVu(String tenDichVu)throws Exception;
	public List<DichVu> getDsDichVuKhiBietMaDV(String maDV)throws Exception;
	public boolean updateSoLuong(String mDV,int sl)throws Exception;
	public boolean updateTongTienDichVu(String maHD,double tienDichVu)throws Exception;
}
