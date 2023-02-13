package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dsNhanVien")
public class NhanVien implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maNhanVien;
	@Column(name = "HoTenNhanVien", unique = true)
	private String hoTenNhanVien;
	@Column(name = "GioiTinh")
	private boolean gioiTinh;
	@Column(name = "NgaySinh")
	private Date ngaySinh;
	@Column(name = "CMND", unique = true, nullable = false)
	private String cmnd;
	@Column(name = "SoDienThoai", unique = true, nullable = false)
	private String soDienThoai;
	@Column(name = "ChucVu", nullable = false)
	private String chucVu;
	@Column(name = "MucLuong", nullable = false)
	private Double mucLuong;
	@Column(name = "TrangThai", nullable = false)
	private boolean trangThai;
	
	@Embedded
	private TaiKhoan TaiKhoan;
	
	public NhanVien() {
		trangThai = true;
	}

	public NhanVien(String maNhanVien, String hoTenNhanVien, boolean gioiTinh, Date ngaySinh, String cmnd,
			String soDienThoai, String chucVu, Double mucLuong) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTenNhanVien = hoTenNhanVien;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.cmnd = cmnd;
		this.soDienThoai = soDienThoai;
		this.chucVu = chucVu;
		this.mucLuong = mucLuong;
		trangThai = true;
	}

	public NhanVien(String maNhanVien) {
		super();
		this.maNhanVien = maNhanVien;
		trangThai = true;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTenNhanVien=" + hoTenNhanVien + ", gioiTinh=" + gioiTinh
				+ ", ngaySinh=" + ngaySinh + ", cmnd=" + cmnd + ", soDienThoai=" + soDienThoai + ", chucVu=" + chucVu
				+ ", mucLuong=" + mucLuong + ", trangThai=" + trangThai + ", TaiKhoan=" + TaiKhoan + "]";
	}

	
	
	
	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getHoTenNhanVien() {
		return hoTenNhanVien;
	}

	public void setHoTenNhanVien(String hoTenNhanVien) {
		this.hoTenNhanVien = hoTenNhanVien;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public Double getMucLuong() {
		return mucLuong;
	}

	public void setMucLuong(Double mucLuong) {
		this.mucLuong = mucLuong;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public TaiKhoan getTaiKhoan() {
		return TaiKhoan;
	}

	public void setTaiKhoan(TaiKhoan TaiKhoan) {
		this.TaiKhoan = TaiKhoan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cmnd, maNhanVien, soDienThoai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(cmnd, other.cmnd) && Objects.equals(maNhanVien, other.maNhanVien)
				&& Objects.equals(soDienThoai, other.soDienThoai);
	}
	
}
