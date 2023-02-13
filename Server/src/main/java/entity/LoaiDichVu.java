package entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.ogm.options.shared.IndexOption;
import org.hibernate.ogm.options.shared.IndexOptions;

@Entity
@Table(name = "dsLoaiDichVu", indexes = @Index(columnList = "TenLoaiDichVu", name = "LoaiDichVu_TenLoaiDichVu_index"))
@IndexOptions(@IndexOption(forIndex = "LoaiDichVu_TenLoaiDichVu_index", options = "{text:true}"))
public class LoaiDichVu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String maLoaiDichVu;
	@Column(name = "TenLoaiDichVu", nullable = false, unique = true)
	private String tenLoaiDichVu;

	@Column(name = "TrangThai")
	private boolean trangThai ;

	public LoaiDichVu() {
		this.trangThai = true;
	}

	public LoaiDichVu(String maLoaiDichVu) {
		super();
		this.maLoaiDichVu = maLoaiDichVu;
	}

	public LoaiDichVu(String maLoaiDichVu, String tenLoaiDichVu) {
		super();
		this.maLoaiDichVu = maLoaiDichVu;
		this.tenLoaiDichVu = tenLoaiDichVu;
		this.trangThai = true;
	}

	public String getMaLoaiDichVu() {
		return maLoaiDichVu;
	}

	public void setMaLoaiDichVu(String maLoaiDichVu) {
		this.maLoaiDichVu = maLoaiDichVu;
	}

	public String getTenLoaiDichVu() {
		return tenLoaiDichVu;
	}

	public void setTenLoaiDichVu(String tenLoaiDichVu) {
		this.tenLoaiDichVu = tenLoaiDichVu;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "LoaiDichVu [maLoaiDichVu=" + maLoaiDichVu + ", tenLoaiDichVu=" + tenLoaiDichVu + ", trangThai="
				+ trangThai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoaiDichVu, tenLoaiDichVu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiDichVu other = (LoaiDichVu) obj;
		return Objects.equals(maLoaiDichVu, other.maLoaiDichVu) && Objects.equals(tenLoaiDichVu, other.tenLoaiDichVu);
	}

}
