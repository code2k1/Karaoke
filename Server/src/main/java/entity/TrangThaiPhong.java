package entity;

public enum TrangThaiPhong {
	INUSE("Đang sử dụng"), EMPTY("Trống"), NOTUSED("Không được sử dụng");
	private String tinhTrang;
	
	private TrangThaiPhong() {
		// TODO Auto-generated constructor stub
	}
	private TrangThaiPhong(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	
}
