package dao;

import java.rmi.Remote;
import java.util.List;

import entity.LoaiPhong;


public interface LoaiPhongDao extends Remote{
	public List<LoaiPhong> getDsLoaiPhong()throws Exception;
	public boolean addLoaiPhong(LoaiPhong lp)throws Exception;
	public boolean deleteLoaiPhong(String maLoaiPhong)throws Exception;
	public boolean updateLoaiPhong(LoaiPhong lp)throws Exception;
	public List<LoaiPhong> getAllLoaiPhong()throws Exception;
	public LoaiPhong getLoaiPhongKhiBietTen(String tenLoaiPhong)throws Exception;
	public LoaiPhong getLoaiPhongKhiBietMa(String maLoaiPhong)throws Exception;
	public boolean restoreLoaiPhong(String tenLoaiPhong)throws Exception;
}
