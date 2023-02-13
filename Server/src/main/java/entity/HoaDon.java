package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "dsHoaDon")
public class HoaDon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maHoaDon;
	@Column(name = "NgayGioDat")
	private Date ngayGioDat;
	@Column(name = "NgayGioTra")
	private Date ngayGioTra;
	@Column(name = "TrangThai")
	private boolean trangThai;

	@ManyToOne
	@JoinColumn(name = "MaNhanVien")
	private NhanVien nhanVien;
	@ManyToOne
	@JoinColumn(name = "MaKhachHang")
	private KhachHang khachHang;

	@Column(name = "TongTienDichVu")
	private double tongTienDichVu;

	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CTHoaDon> CTHoaDon;

	@Transient
	private boolean status;

	public HoaDon() {
		// TODO Auto-generated constructor stub
	}

	public HoaDon(String maHoaDon, Date ngayGioDat, Date ngayGioTra) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayGioDat = ngayGioDat;
		this.ngayGioTra = ngayGioTra;
	}

	public HoaDon(String maHoaDon) {
		super();
		this.maHoaDon = maHoaDon;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public Date getNgayGioDat() {
		return ngayGioDat;
	}

	public void setNgayGioDat(Date ngayGioDat) {
		this.ngayGioDat = ngayGioDat;
	}

	public Date getNgayGioTra() {
		return ngayGioTra;
	}

	public void setNgayGioTra(Date ngayGioTra) {
		this.ngayGioTra = ngayGioTra;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public List<CTHoaDon> getCTHoaDon() {
		return CTHoaDon;
	}

	public void setCTHoaDon(List<CTHoaDon> cTHoaDon) {
		CTHoaDon = cTHoaDon;
		this.tongTienDichVu = getTongTienDV();
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayGioDat=" + ngayGioDat + ", ngayGioTra=" + ngayGioTra
				+ ", trangThai=" + trangThai + ", nhanVien=" + nhanVien.getMaNhanVien() + ", khachHang=" + khachHang
				+ ", CTHoaDon=" + CTHoaDon + "]";
	}

	public double getTongTienDichVu() {
		return getTongTienDV();
	}

	public void setTongTienDichVu(double tongTienDichVu) {
		this.tongTienDichVu = tongTienDichVu;
	}

	public double getTongTienDV() {
		double total = 0;
		if (CTHoaDon.get(0).getTienPhong() != 0) {
			for (CTHoaDon ctHoaDon2 : CTHoaDon) {
				total += ctHoaDon2.getTienDichVu();
			}
		}
		return total;
	}

	@SuppressWarnings("deprecation")
	public double TongTienHoaDon() {
		int gioVao = ngayGioDat.getHours();
		int phutVao = ngayGioDat.getMinutes();
		int giayVao = ngayGioDat.getSeconds();
		int gioRa = ngayGioTra.getHours();
		int phutRa = ngayGioTra.getMinutes();
		int giayRa = ngayGioTra.getSeconds();

		if (giayVao > 59) {
			phutVao++;
			giayVao = 0;
		}
		if (phutVao > 59) {
			gioVao++;
			phutVao = 0;
		}

		if (giayRa > 59) {
			phutRa++;
			giayRa = 0;
		}
		if (phutRa > 59) {
			gioRa++;
			phutRa = 0;
		}
		double temp = 0;
		if ((gioRa - gioVao) == 0)
			temp = CTHoaDon.get(0).getPhong().getLoaiPhong().getGiaTien() + ((phutRa - phutVao) * CTHoaDon.get(0).getTienPhong() / 60);
		else {
			temp = ((gioRa - gioVao) *  CTHoaDon.get(0).getPhong().getLoaiPhong().getGiaTien())
					+ ((phutRa - phutVao) *  CTHoaDon.get(0).getPhong().getLoaiPhong().getGiaTien() / 60) ;
		}
		return temp + tongTienDichVu;
	}

}
