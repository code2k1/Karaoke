package dao;

import java.rmi.Remote;
import java.util.List;

import entity.LoaiDichVu;

public interface LoaiDichVuDao extends Remote{
	public List<LoaiDichVu> getDsLoaiDichVu()throws Exception;
	public boolean addLoaiDichVu(LoaiDichVu ldv)throws Exception;
	public boolean deleteLoaiDichVu(String maLoaiDichVu)throws Exception;
	public boolean updateLoaiDichVu(LoaiDichVu ldv)throws Exception;
	public List<LoaiDichVu> getAllLoaiDichVu()throws Exception;
	public LoaiDichVu getLoaiDichVuKhiBietTen(String tenLoaiDichVu)throws Exception;
	public LoaiDichVu getLoaiDichVuKhiBietMa(String maLoaiDichVu)throws Exception;
	public boolean restoreLoaiDichVu(String tenLoaiDichVu)throws Exception;
}
