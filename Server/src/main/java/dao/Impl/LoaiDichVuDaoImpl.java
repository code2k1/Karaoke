package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.LoaiDichVuDao;
import entity.LoaiDichVu;
import util.HibernateUtil;

public class LoaiDichVuDaoImpl extends UnicastRemoteObject implements LoaiDichVuDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em;

	public LoaiDichVuDaoImpl() throws RemoteException {
		super();
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoaiDichVu> getDsLoaiDichVu() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsLoaiDichVu.find({'TrangThai':true})";
			List<LoaiDichVu> 
				dsldv = em.createNativeQuery(query, LoaiDichVu.class).getResultList();
			tr.commit();
			return dsldv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public boolean addLoaiDichVu(LoaiDichVu ldv) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(ldv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean deleteLoaiDichVu(String maLoaiDichVu) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsLoaiDichVu.updateOne({'_id':'" + maLoaiDichVu + "'},{'$set':{'TrangThai':false}})";
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
	public boolean updateLoaiDichVu(LoaiDichVu ldv) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(ldv);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoaiDichVu> getAllLoaiDichVu() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsLoaiDichVu.find({})";
			List<LoaiDichVu>
				dsldv = em.createNativeQuery(query, LoaiDichVu.class).getResultList();
			tr.commit();
			return dsldv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public LoaiDichVu getLoaiDichVuKhiBietTen(String tenLoaiDichVu) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsLoaiDichVu.find({'TenLoaiDichVu':'" + tenLoaiDichVu.trim() + "'}) ";
			LoaiDichVu 
				ldv = (LoaiDichVu) em.createNativeQuery(query, LoaiDichVu.class).getSingleResult();
			tr.commit();
			return ldv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public boolean restoreLoaiDichVu(String tenLoaiDichVu) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsLoaiDichVu.updateOne({'TenLoaiDichVu':'" + tenLoaiDichVu
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

	@Override
	public LoaiDichVu getLoaiDichVuKhiBietMa(String maLoaiDichVu) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsLoaiDichVu.find({'_id':'" + maLoaiDichVu.trim() + "'}) ";
			LoaiDichVu 
				ldv = (LoaiDichVu) em.createNativeQuery(query, LoaiDichVu.class).getSingleResult();
			tr.commit();
			return ldv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

}
