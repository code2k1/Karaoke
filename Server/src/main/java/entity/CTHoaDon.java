package entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CTHoaDon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "MaDichVu")
	private DichVu dichVu;
	@ManyToOne
	@JoinColumn(name = "MaPhong")
	private Phong phong;
	private int SoLuongDat =0;
	private double TienDichVu =0;
	private double TienPhong= 0;

	public CTHoaDon() {
	}

	public CTHoaDon(DichVu dichVu) {
		super();
		this.dichVu = dichVu;
	}

	public CTHoaDon(Phong phong) {
		super();
		this.phong = phong;
	}

	public CTHoaDon(DichVu dichVu, Phong phong, int soLuongDat) {
		super();
		this.dichVu = dichVu;
		this.phong = phong;
		SoLuongDat = soLuongDat;
		this.TienDichVu = dichVu.getGiaBan() * soLuongDat;
		this.TienPhong = phong.getGiaPhong();
	}

	@Override
	public String toString() {
		return "CTHoaDon [dichVu=" + dichVu + ", phong=" + phong.getMaPhong() + ", SoLuongDat=" + SoLuongDat
				+ ", TienDichVu=" + TienDichVu + ", TienPhong=" + TienPhong + "]";
	}

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public int getSoLuongDat() {
		return SoLuongDat;
	}

	public void setSoLuongDat(int soLuongDat) {
		SoLuongDat = soLuongDat;
		this.TienDichVu = dichVu.getGiaBan() * soLuongDat;
	}

	public double getTienDichVu() {
		return TienDichVu;
	}

	public void setTienDichVu(double tienDichVu) {
		TienDichVu = tienDichVu;
	}

	public double getTienPhong() {
		return TienPhong;
	}

	public void setTienPhong(double tienPhong) {
		TienPhong = tienPhong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dichVu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CTHoaDon other = (CTHoaDon) obj;
		return Objects.equals(dichVu, other.dichVu);
	}
	
}
