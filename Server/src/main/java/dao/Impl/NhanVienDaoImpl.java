package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.NhanVienDao;
import entity.NhanVien;
import util.HibernateUtil;

public class NhanVienDaoImpl extends UnicastRemoteObject implements NhanVienDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EntityManager em;

	public NhanVienDaoImpl() throws RemoteException {
		super();
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public boolean addNhanVien(NhanVien nv) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(nv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean deleteNhanVien(String maNhanVien) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsNhanVien.updateOne({'_id':'" + maNhanVien + "'},{'$set':{'TrangThai':false}})";
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
	public boolean updateNhanVien(NhanVien nv) throws Exception {
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
	public List<NhanVien> getDsNhanVien() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsNhanVien.find({'TrangThai':true})";
			@SuppressWarnings("unchecked")
			List<NhanVien> dsdv = em.createNativeQuery(query, NhanVien.class).getResultList();
			tr.commit();
			return dsdv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public List<NhanVien> getAllNhanVien() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsNhanVien.find({})";
			@SuppressWarnings("unchecked")
			List<NhanVien> dsdv = em.createNativeQuery(query, NhanVien.class).getResultList();
			tr.commit();
			return dsdv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public NhanVien getNhanVienKhiBietMa(String maNhanVien) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsNhanVien.find({'_id':'" + maNhanVien + "'}) ";
			NhanVien dv = (NhanVien) em.createNativeQuery(query, NhanVien.class).getSingleResult();
			tr.commit();
			return dv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public NhanVien getNhanVienKhiBietTenTK(String tenTaiKhoan) throws Exception {
		
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsNhanVien.find({'TaiKhoan.TenDangNhap':'"+tenTaiKhoan+"'}) ";
			NhanVien dv = (NhanVien) em.createNativeQuery(query, NhanVien.class).getSingleResult();
			tr.commit();
			return dv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

}
