package dao.Impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.DichVuDao;
import entity.DichVu;
import util.HibernateUtil;

public class DichVuDaoImpl extends UnicastRemoteObject implements DichVuDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager em;

	public DichVuDaoImpl() throws RemoteException {
		super();
		em = HibernateUtil.getInstance().getEntityManager();
	}

	@Override
	public boolean addDichVu(DichVu dv) throws Exception {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<DichVu> getDsDichVuTrongKhoan(int skip, int limit) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsDichVu.aggregate([{'$skip':" + skip + "},{'$limit':" + limit + "}]) ";
			List<DichVu> dsdv = em.createNativeQuery(query, DichVu.class).getResultList();
			tr.commit();
			return dsdv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DichVu> getDsDichVu() throws Exception {

		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsDichVu.find({'TrangThai':true})";
			List<DichVu> dsdv = em.createNativeQuery(query, DichVu.class).getResultList();
			tr.commit();
			return dsdv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public boolean deleteDichVu(String maDichVu) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsDichVu.updateOne({'_id':'" + maDichVu + "'},{'$set':{'TrangThai':false}})";
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
	public boolean updateDichVu(DichVu dv) throws Exception {
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

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<DichVu> getAllDichVu() throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsDichVu.find({})";
			List<DichVu> dsdv = em.createNativeQuery(query, DichVu.class).getResultList();
			tr.commit();
			return dsdv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public DichVu getDichVuKhiBietTen(String tenDichVu) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsDichVu.find({'TenDichVu':'" + tenDichVu + "'}) ";
			DichVu dv = (DichVu) em.createNativeQuery(query, DichVu.class).getSingleResult();
			tr.commit();
			return dv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public boolean restoreDichVu(String tenDichVu) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsDichVu.updateOne({'TenDichVu':'" + tenDichVu + "'},{'$set':{'TrangThai':true}})";
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
	public DichVu getDichVuKhiBietMa(String maDichVu) throws Exception {

		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsDichVu.find({'_id':'" + maDichVu + "'}) ";
			DichVu dv = (DichVu) em.createNativeQuery(query, DichVu.class).getSingleResult();
			tr.commit();
			return dv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DichVu> getDsDichVuKhiBietMaDV(String maDV) throws Exception {

		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "	db.dsDichVu.aggregate([{'$lookup':{'from':'dsLoaiDichVu','localField':'MaLoaiDichVu','foreignField':'_id','as':'loaiDichVu'}},{'$unwind':'$loaiDichVu'},{'$match':{'loaiDichVu.TenLoaiDichVu':'"
					+ maDV
					+ "'}},{'$replaceWith':{'$mergeObjects':[{'_id':'$_id'},{'TenDichVu':'$TenDichVu'},{'GiaBan':'$GiaBan'},{'SoLuongTon':'$SoLuongTon'},{'TrangThai':'$TrangThai'},{'MaLoaiDichVu':'$MaLoaiDichVu'},{'HinhAnh':'$HinhAnh'}]}}]) \r\n"
					+ "])";
			List<DichVu> dsdv = em.createNativeQuery(query, DichVu.class).getResultList();
			tr.commit();
			return dsdv;
		} catch (Exception e) {
			tr.commit();
			return null;
		}
	}

	@Override
	public boolean updateSoLuong(String mDV, int sl) throws Exception {
		
		
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsDichVu.updateOne({'_id':'" + mDV + "'},{'$set':{'SoLuongTon':"+sl+"}})";
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
	public boolean updateTongTienDichVu(String maHD,double tienDichVu) throws Exception {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			String query = "db.dsDichVu.updateOne({'_id':'" + maHD + "'},{'$set':{'TongTienDichVu':"+tienDichVu+"}})";
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
