package entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dsPhong")
public class Phong implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maPhong;
	@Column(name = "ViTri", nullable = false)
	private String viTri;
	@ManyToOne
	@JoinColumn(name = "LoaiPhong", nullable = false)
	private LoaiPhong loaiPhong;
	@Enumerated(EnumType.STRING)
	@Column(name = "TrangThai")
	private TrangThaiPhong trangThai = TrangThaiPhong.EMPTY;

	@Column(name = "GiaPhong")
	private double giaPhong = 0;

	public TrangThaiPhong getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(TrangThaiPhong trangThai) {
		this.trangThai = trangThai;
	}

	public double getGiaPhong() {
		return giaPhong;
	}

	public void setGiaPhong(double giaPhong) {
		this.giaPhong = giaPhong;
	}

	public Phong() {
	}

	public Phong(String maPhong) {
		super();
		this.maPhong = maPhong;
	}

	public Phong(String maPhong, String viTri) {
		super();
		this.maPhong = maPhong;
		this.viTri = viTri;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getViTri() {
		return viTri;
	}

	public void setViTri(String viTri) {
		this.viTri = viTri;
	}

	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
		giaPhong = loaiPhong.getGiaTien();
	}

	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", viTri=" + viTri + ", loaiPhong=" + loaiPhong + ", trangThai="
				+ trangThai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhong, viTri);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phong other = (Phong) obj;
		return Objects.equals(maPhong, other.maPhong) && Objects.equals(viTri, other.viTri);
	}

}
