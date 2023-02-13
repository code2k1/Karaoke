package dao;

import java.rmi.Remote;
import java.util.List;

import entity.HoaDon;

public interface HoaDonDao extends Remote {
	public boolean addHoaDon(HoaDon dv) throws Exception;

	public boolean updateHoaDon(HoaDon dv) throws Exception;

	public HoaDon getHoaDonTheoMaPhong(String maPhong) throws Exception;

	public List<HoaDon> getDsHoaDon() throws Exception;

	public List<HoaDon> getAllHoaDon() throws Exception;
	
	public boolean updateTrangThaiHoaDon(String maHoaDon)throws Exception;
	
	public HoaDon getHoaDonKhiBietMa(String maHoaDon)throws Exception;

}
