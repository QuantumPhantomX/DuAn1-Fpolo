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
    private String hoTen;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private Date ngayTao;
    private Date ngaySua;
    private String trangThai;

    public HoaDon() {
    }

    public HoaDon(int id, int idNhanVien, int idKhachHang, int idHinhThucThanhToan, String lichSuHoaDon, BigDecimal tongTien, String hoTen, String diaChi, String soDienThoai, String email, Date ngayTao, Date ngaySua, String trangThai) {
        this.id = id;
        this.idNhanVien = idNhanVien;
        this.idKhachHang = idKhachHang;
        this.idHinhThucThanhToan = idHinhThucThanhToan;
        this.lichSuHoaDon = lichSuHoaDon;
        this.tongTien = tongTien;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

    public HoaDon(int id, String tenNhanVien, String tenKhachHang, String hinhThucThanhToan, String lichSuHoaDon, BigDecimal tongTien, String hoTen, String diaChi, String soDienThoai, String email, Date ngayTao, Date ngaySua, String trangThai) {
        this.id = id;
        this.tenNhanVien = tenNhanVien;
        this.tenKhachHang = tenKhachHang;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.lichSuHoaDon = lichSuHoaDon;
        this.tongTien = tongTien;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
    }

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

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Object[] toDataRow() {
        return new Object[]{
            this.id,
            this.tenNhanVien,
            this.tenKhachHang, 
            this.hinhThucThanhToan, 
            this.lichSuHoaDon,
            this.tongTien,
            this.hoTen,
            this.diaChi,
            this.soDienThoai,
            this.email,
            this.ngayTao,
            this.ngaySua,
            this.trangThai
        };
    }
}