package Model_Bill;

import java.math.BigDecimal;
import java.util.Date;

public class HoaDon {

    private int id;
    private int idNhanVien;
    private int idKhachHang;
    private int idHinhThucThanhToan;
    private String tenNhanVien;
    private String tenKhachHang;
    private String hinhThucThanhToan;
    private String lichSuHoaDon;
    private BigDecimal tongTien;
    private Date ngayTao;
    private Date ngaySua;
    private String trangThai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdHinhThucThanhToan() {
        return idHinhThucThanhToan;
    }

    public void setIdHinhThucThanhToan(int idHinhThucThanhToan) {
        this.idHinhThucThanhToan = idHinhThucThanhToan;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public String getLichSuHoaDon() {
        return lichSuHoaDon;
    }

    public void setLichSuHoaDon(String lichSuHoaDon) {
        this.lichSuHoaDon = lichSuHoaDon;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public HoaDon(int id, int idNhanVien, int idKhachHang, int idHinhThucThanhToan, String tenNhanVien, String tenKhachHang, String hinhThucThanhToan, String lichSuHoaDon, BigDecimal tongTien, Date ngayTao, Date ngaySua, String trangThai) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.idHinhThucThanhToan = idHinhThucThanhToan;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.lichSuHoaDon = lichSuHoaDon;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public HoaDon(int id, String tenNhanVien, String tenKhachHang, String hinhThucThanhToan, String lichSuHoaDon, BigDecimal tongTien, Date ngayTao, Date ngaySua, String trangThai) {
        this.id = id;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.lichSuHoaDon = lichSuHoaDon;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public HoaDon() {
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.id,
            this.tenNhanVien,
            this.tenKhachHang,
            this.hinhThucThanhToan,
            this.lichSuHoaDon,
            this.tongTien,
            this.ngayTao,
            this.ngaySua,
            this.trangThai
        };
    }
}
