package entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dsLoaiPhong")
public class LoaiPhong implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 388535964947326045L;
	@Id
	private String maLoaiPhong;
	@Column(name = "TenLoaiPhong", nullable = false, unique = true)
	private String tenLoaiPhong;
	@Column(name = "GiaTien", nullable = false)
	private double giaTien;
	@Column(name = "TrangThai")
	private boolean trangThai;

	public LoaiPhong() {
		this.trangThai = true;
	}

	public LoaiPhong(String maLoaiPhong) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.trangThai = true;
	}

	public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, double giaTien) {
		super();
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = tenLoaiPhong;
		this.giaTien = giaTien;
		this.trangThai = true;
	}

	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}

	public void setMaLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}

	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}

	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}

	public double getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(double giaTien) {
		this.giaTien = giaTien;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "LoaiPhong [maLoaiPhong=" + maLoaiPhong + ", tenLoaiPhong=" + tenLoaiPhong + ", giaTien=" + giaTien
				+ ", trangThai=" + trangThai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoaiPhong, tenLoaiPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiPhong other = (LoaiPhong) obj;
		return Objects.equals(maLoaiPhong, other.maLoaiPhong) && Objects.equals(tenLoaiPhong, other.tenLoaiPhong);
	}

}
