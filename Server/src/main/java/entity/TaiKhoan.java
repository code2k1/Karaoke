package entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class TaiKhoan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String TenDangNhap;
	private String MatKhau;

	public TaiKhoan() {
	}

	public TaiKhoan(String TenDangNhap, String MatKhau) {
		super();
		this.TenDangNhap = TenDangNhap;
		this.MatKhau = MatKhau;
	}

	public TaiKhoan(String TenDangNhap) {
		super();
		this.TenDangNhap = TenDangNhap;
	}

	public String getTenDangNhap() {
		return TenDangNhap;
	}

	public void setTenDangNhap(String TenDangNhap) {
		this.TenDangNhap = TenDangNhap;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}

	@Override
	public String toString() {
		return "TaiKhoan [TenDangNhap=" + TenDangNhap + ", MatKhau=" + MatKhau + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(TenDangNhap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(TenDangNhap, other.TenDangNhap);
	}

}