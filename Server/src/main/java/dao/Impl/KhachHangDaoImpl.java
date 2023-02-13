package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.KhachHangDao;
import entity.KhachHang;
import util.HibernateUtil;

public class KhachHangDaoImpl extends UnicastRemoteObject implements KhachHangDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em;

	public KhachHangDaoImpl() throws RemoteException {
		super();
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public boolean addKhachHang(KhachHang kh) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(kh);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean deleteKhachHang(String maKhachHang) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsKhachHang.updateOne({'_id':'" + maKhachHang + "'},{'$set':{'TrangThai':false}})";
			int rs = em.createNativeQuery(query).executeUpdate();
			tr.commit();
			return rs > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean updateKhachHang(KhachHang nv) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(nv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<KhachHang> getDsKhachHang() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsKhachHang.find({'TrangThai':true})";
			@SuppressWarnings("unchecked")
			List<KhachHang> dsdv = em.createNativeQuery(query, KhachHang.class).getResultList();
			tr.commit();
			return dsdv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public List<KhachHang> getAllKhachHang() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsKhachHang.find({})";
			@SuppressWarnings("unchecked")
			List<KhachHang> dsdv = em.createNativeQuery(query, KhachHang.class).getResultList();
			tr.commit();
			return dsdv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public KhachHang getKhachHangKhiBietMa(String maKhachHang) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsKhachHang.find({'_id':'" + maKhachHang + "'}) ";
			KhachHang dv = (KhachHang) em.createNativeQuery(query, KhachHang.class).getSingleResult();
			tr.commit();
			return dv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}
	@Override
	public KhachHang getKhachHangKhiBietSDT(String sdt) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsKhachHang.find({'SoDienThoai':'" + sdt + "'}) ";
			KhachHang dv = (KhachHang) em.createNativeQuery(query, KhachHang.class).getSingleResult();
			tr.commit();
			return dv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}
	
}
