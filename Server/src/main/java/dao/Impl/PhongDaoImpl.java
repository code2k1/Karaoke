package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.PhongDao;
import entity.Phong;
import util.HibernateUtil;

public class PhongDaoImpl extends UnicastRemoteObject implements PhongDao{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em;
	public PhongDaoImpl() throws RemoteException {
		super();
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public List<Phong> getDsPhong() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsPhong.find({$or:[{'TrangThai':'EMPTY'},{'TrangThai':'INUSE'}]}) ";
			@SuppressWarnings("unchecked")
			List<Phong> dsldv = em.createNativeQuery(query, Phong.class).getResultList();
			tr.commit();
			return dsldv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public boolean addPhong(Phong p) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(p);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean updatePhong(Phong p) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(p);
			tr.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<Phong> getAllPhong() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsPhong.find({})";
			@SuppressWarnings("unchecked")
			List<Phong> dsldv = em.createNativeQuery(query, Phong.class).getResultList();
			tr.commit();
			return dsldv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public List<Phong> getPhongKhiBietViTri(String viTriPhong) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsPhong.find({'ViTri':'" + viTriPhong+ "'}) ";
			@SuppressWarnings("unchecked")
			List<Phong> dsp =  em.createNativeQuery(query, Phong.class).getResultList();
			tr.commit();
			return dsp;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public Phong getPhongKhiBietMa(String maPhong) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsPhong.find({'_id':'" + maPhong+ "'}) ";
			Phong
				ldv = (Phong) em.createNativeQuery(query, Phong.class).getSingleResult();
			tr.commit();
			return ldv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public boolean updateStatusRoomEmpty(String maP) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsPhong.updateOne({'_id':'" + maP
					+ "'},{'$set':{'TrangThai':'EMPTY'}})";
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
	public boolean updateStatusRoomInUse(String maP) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsPhong.updateOne({'_id':'" + maP
					+ "'},{'$set':{'TrangThai':'INUSE'}})";
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
	public List<Phong> getDsPhongTrongKhoan(int skip, int limit) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsPhong.aggregate([{'$skip':" + skip + "},{'$limit':" + limit + "}]) ";
			@SuppressWarnings("unchecked")
			List<Phong> dsdv = em.createNativeQuery(query, Phong.class).getResultList();
			tr.commit();
			return dsdv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public List<Phong> getDsPhongKhiBietMaLP(String maLP) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsPhong.aggregate([{'$lookup':{'from':'dsLoaiPhong','localField':'LoaiPhong','foreignField':'_id','as':'loaiPhong'}},{'$unwind':'$loaiPhong'},{'$match':{'loaiPhong.TenLoaiPhong':'"+maLP+"'}},{'$replaceWith':{'$mergeObjects':[{'_id':'$_id','GiaPhong':'$GiaPhong','ViTri':'$ViTri','LoaiPhong':'$LoaiPhong','TrangThai':'$TrangThai'}]}}]) ) ";
			@SuppressWarnings("unchecked")
			List<Phong> dsp =  em.createNativeQuery(query, Phong.class).getResultList();
			tr.commit();
			return dsp;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

}
