package entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dsDichVu")
//,
//	indexes = @Index(columnList = "TenDichVu", name = "DichVu_TenDichVu_index")
//		)
//@IndexOptions(@IndexOption(forIndex = "DichVu_TenDichVu_index", options = "{text:true}"))
public class DichVu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String maDichVu;
	@Column(name = "TenDichVu", nullable = false, unique = true)
	private String tenDichVu;
	@Column(name = "GiaBan",nullable = false)
	private double giaBan;
	@Column(name = "SoLuongTon", nullable = false)
	private int soLuongTon;

	@Column(name = "TrangThai")
	private boolean trangThai;

	@ManyToOne()
	@JoinColumn(name = "MaLoaiDichVu", nullable = false)
	private LoaiDichVu loaiDichVu;

	@Lob
	@Column(name = "HinhAnh", nullable = false)
	private byte[] hinhAnh;

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public DichVu() {
		this.trangThai = true;
	}

	public DichVu(String maDichVu) {
		super();
		this.maDichVu = maDichVu;
		this.trangThai = true;
	}


	public DichVu(String maDichVu, String tenDichVu, double giaBan, int soLuongTon, byte[] hinhAnh) {
		super();
		this.maDichVu = maDichVu;
		this.tenDichVu = tenDichVu;
		this.giaBan = giaBan;
		this.soLuongTon = soLuongTon;
		this.hinhAnh = hinhAnh;
		this.trangThai = true;
	}

	public String getMaDichVu() {
		return maDichVu;
	}

	public void setMaDichVu(String maDichVu) {
		this.maDichVu = maDichVu;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public LoaiDichVu getLoaiDichVu() {
		return loaiDichVu;
	}

	public void setLoaiDichVu(LoaiDichVu loaiDichVu) {
		this.loaiDichVu = loaiDichVu;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	@Override
	public String toString() {
		return "DichVu [maDichVu=" + maDichVu + ", tenDichVu=" + tenDichVu + ", giaBan=" + giaBan + ", soLuongTon="
				+ soLuongTon + ", trangThai=" + trangThai + ", loaiDichVu=" + loaiDichVu + ", hinhAnh="
				+ Arrays.toString(hinhAnh) + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDichVu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DichVu other = (DichVu) obj;
		return Objects.equals(maDichVu, other.maDichVu);
	}



}
