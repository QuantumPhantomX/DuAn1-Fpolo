package Model_Bill;

import java.math.BigDecimal;

public class HoaDonChiTiet {

    private int id;
    private int idHoaDon;
    private String idSanPhamChiTiet;
    private String tenSanPham;
    private String tenMauSac;
    private String tenSize;
    private String tenChatLieu;
    private String tenThuongHieu;
    private String tenLoaiCoCao;
    private BigDecimal giaBan;
    private int soLuong;
    private BigDecimal thanhTien;
    private String trangThai;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int id, int idHoaDon, String idSanPhamChiTiet, int soLuong, BigDecimal thanhTien, String trangThai) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.idSanPhamChiTiet = idSanPhamChiTiet;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getIdSanPhamChiTiet() {
        return idSanPhamChiTiet;
    }

    public void setIdSanPhamChiTiet(String idSanPhamChiTiet) {
        this.idSanPhamChiTiet = idSanPhamChiTiet;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public String getTenLoaiCoCao() {
        return tenLoaiCoCao;
    }

    public void setTenLoaiCoCao(String tenLoaiCoCao) {
        this.tenLoaiCoCao = tenLoaiCoCao;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public HoaDonChiTiet(int id, int idHoaDon, String idSanPhamChiTiet, String tenSanPham, String tenMauSac, String tenSize, String tenChatLieu, String tenThuongHieu, String tenLoaiCoCao, BigDecimal giaBan, int soLuong, BigDecimal thanhTien, String trangThai) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.idSanPhamChiTiet = idSanPhamChiTiet;
        this.tenSanPham = tenSanPham;
        this.tenMauSac = tenMauSac;
        this.tenSize = tenSize;
        this.tenChatLieu = tenChatLieu;
        this.tenThuongHieu = tenThuongHieu;
        this.tenLoaiCoCao = tenLoaiCoCao;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.id,
            this.idHoaDon,
            this.idSanPhamChiTiet,
            this.tenSanPham,
            this.tenMauSac,
            this.tenSize,
            this.tenChatLieu,
            this.tenThuongHieu,
            this.tenLoaiCoCao,
            this.giaBan,
            this.soLuong,
            this.thanhTien,
            this.trangThai
        };
    }
}
