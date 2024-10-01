package Model_KhachHang_NhanVien;

import java.util.Date;

public class NhanVien {

    private int ID;
    private String HoTen;
    private String DiaChi;
    private String SoDienThoai;
    private String Email;
    private Date NamSinh;
    private String GioiTinh;
    private String ChucVu;
    private String MatKhau;
    private String TrangThai;

    public NhanVien() {
    }

    public NhanVien(int ID, String HoTen, String DiaChi, String SoDienThoai, String Email, Date NamSinh, String GioiTinh, String ChucVu, String MatKhau, String TrangThai) {
        this.ID = ID;
        this.HoTen = HoTen;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.Email = Email;
        this.NamSinh = NamSinh;
        this.GioiTinh = GioiTinh;
        this.ChucVu = ChucVu;
        this.MatKhau = MatKhau;
        this.TrangThai = TrangThai;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(Date NamSinh) {
        this.NamSinh = NamSinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

}