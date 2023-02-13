package dao;

import java.rmi.Remote;
import java.util.List;

import entity.Phong;

public interface PhongDao extends Remote{
	public List<Phong> getDsPhong()throws Exception;
	public boolean addPhong(Phong p)throws Exception;

	public boolean updatePhong(Phong p)throws Exception;
	public List<Phong> getAllPhong()throws Exception;
	public Phong getPhongKhiBietMa(String maPhong)throws Exception;
	public boolean updateStatusRoomEmpty(String maP)throws Exception;
	public boolean updateStatusRoomInUse(String maP)throws Exception;
	public List<Phong> getPhongKhiBietViTri(String viTriPhong) throws Exception;
	public List<Phong> getDsPhongTrongKhoan(int skip, int limit)throws Exception;
	public List<Phong> getDsPhongKhiBietMaLP(String maLP)throws Exception;
}
