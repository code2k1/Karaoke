package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.LoaiPhongDao;
import entity.LoaiPhong;
import util.HibernateUtil;

public class LoaiPhongDaoImpl extends UnicastRemoteObject implements LoaiPhongDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em;
	public LoaiPhongDaoImpl() throws RemoteException {
		super();
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public List<LoaiPhong> getDsLoaiPhong() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsLoaiPhong.find({'TrangThai':true})";
			@SuppressWarnings("unchecked")
			List<LoaiPhong> dslp = em.createNativeQuery(query, LoaiPhong.class).getResultList();
			tr.commit();
			return dslp;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public boolean addLoaiPhong(LoaiPhong lp) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(lp);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean deleteLoaiPhong(String maLoaiPhong) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsLoaiPhong.updateOne({'_id':'" + maLoaiPhong + "'},{'$set':{'TrangThai':false}})";
			int rs = em.createNativeQuery(query).executeUpdate();
			tr.commit();
			return rs > 0;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean updateLoaiPhong(LoaiPhong lp) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(lp);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<LoaiPhong> getAllLoaiPhong() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsLoaiPhong.find({})";
			@SuppressWarnings("unchecked")
			List<LoaiPhong> dsldv = em.createNativeQuery(query, LoaiPhong.class).getResultList();
			tr.commit();
			return dsldv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public LoaiPhong getLoaiPhongKhiBietTen(String tenLoaiPhong) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsLoaiPhong.find({'TenLoaiPhong':'" + tenLoaiPhong+ "'}) ";
			LoaiPhong
				ldv = (LoaiPhong) em.createNativeQuery(query, LoaiPhong.class).getSingleResult();
			tr.commit();
			return ldv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public LoaiPhong getLoaiPhongKhiBietMa(String maLoaiPhong) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsLoaiPhong.find({'_id':'" + maLoaiPhong + "'}) ";
			LoaiPhong 
				ldv = (LoaiPhong) em.createNativeQuery(query, LoaiPhong.class).getSingleResult();
			tr.commit();
			return ldv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public boolean restoreLoaiPhong(String tenLoaiPhong) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsLoaiPhong.updateOne({'TenLoaiPhong':'" + tenLoaiPhong
					+ "'},{'$set':{'TrangThai':true}})";
			int rs = em.createNativeQuery(query).executeUpdate();
			tr.commit();
			return rs > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

}
