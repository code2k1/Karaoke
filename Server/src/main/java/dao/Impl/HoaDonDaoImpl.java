package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.HoaDonDao;
import entity.HoaDon;
import util.HibernateUtil;

public class HoaDonDaoImpl extends UnicastRemoteObject implements HoaDonDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em;
	public HoaDonDaoImpl() throws RemoteException {
		super();
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public boolean addHoaDon(HoaDon dv) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(dv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean updateHoaDon(HoaDon dv) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(dv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public HoaDon getHoaDonTheoMaPhong(String maPhong) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsHoaDon.find({'$and':[{'TrangThai':false},{'CTHoaDon.MaPhong':'"+maPhong+"'}]}) ";
			HoaDon hd = (HoaDon) em.createNativeQuery(query, HoaDon.class).getSingleResult();
			tr.commit();
			return hd;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public List<HoaDon> getDsHoaDon() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsHoaDon.find({'TrangThai':false})";
			@SuppressWarnings("unchecked")
			List<HoaDon> dsdv = em.createNativeQuery(query, HoaDon.class).getResultList();
			tr.commit();
			return dsdv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HoaDon> getAllHoaDon() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsHoaDon.find({})";
			List<HoaDon> dsdv = em.createNativeQuery(query, HoaDon.class).getResultList();
			tr.commit();
			return dsdv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public boolean updateTrangThaiHoaDon(String maHoaDon) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsHoaDon.updateOne({'_id':'" + maHoaDon + "'},{'$set':{'TrangThai':true}})";
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
	public HoaDon getHoaDonKhiBietMa(String maHoaDon) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsHoaDon.find({'_id':'" + maHoaDon + "'}) ";
			HoaDon dv = (HoaDon) em.createNativeQuery(query, HoaDon.class).getSingleResult();
			tr.commit();
			return dv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

}
