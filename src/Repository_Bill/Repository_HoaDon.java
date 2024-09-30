/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository_Bill;

import Model_Bill.HoaDon;
import com.raven.connect.DBConnect;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Repository_HoaDon {

    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        String sql = "SELECT hd.ID, nv.HoTen AS TenNhanVien, kh.HoTen AS TenKhachHang, httt.HinhThuc AS HinhThucThanhToan, hd.LichSuHoaDon, hd.TongTien, hd.HoTen, hd.DiaChi, hd.SoDienThoai, hd.Email, hd.NgayTao, hd.NgaySua, hd.TrangThai "
                + "FROM HOADON hd "
                + "JOIN NHANVIEN nv ON hd.ID_NhanVien = nv.ID "
                + "JOIN KHACHHANG kh ON hd.ID_KhachHang = kh.ID "
                + "JOIN HINHTHUCTHANHTOAN httt ON hd.ID_HinhThucThanhToan = httt.ID";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(rs.getInt("ID"));
                hoaDon.setTenNhanVien(rs.getString("TenNhanVien"));
                hoaDon.setTenKhachHang(rs.getString("TenKhachHang"));
                hoaDon.setHinhThucThanhToan(rs.getString("HinhThucThanhToan"));
                hoaDon.setLichSuHoaDon(rs.getString("LichSuHoaDon"));
                hoaDon.setTongTien(rs.getBigDecimal("TongTien"));
                hoaDon.setHoTen(rs.getString("HoTen"));
                hoaDon.setDiaChi(rs.getString("DiaChi"));
                hoaDon.setSoDienThoai(rs.getString("SoDienThoai"));
                hoaDon.setEmail(rs.getString("Email"));
                hoaDon.setNgayTao(rs.getDate("NgayTao"));
                hoaDon.setNgaySua(rs.getDate("NgaySua"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));

                listHoaDon.add(hoaDon);
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }

        return listHoaDon;
    }

    public ArrayList<HoaDon> timKiemHoaDonTheoMa(int maHoaDon) {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        String sql = "SELECT hd.ID, nv.HoTen AS TenNhanVien, kh.HoTen AS TenKhachHang, httt.HinhThuc AS HinhThucThanhToan, hd.LichSuHoaDon, hd.TongTien, hd.HoTen, hd.DiaChi, hd.SoDienThoai, hd.Email, hd.NgayTao, hd.NgaySua, hd.TrangThai "
                + "FROM HOADON hd "
                + "JOIN NHANVIEN nv ON hd.ID_NhanVien = nv.ID "
                + "JOIN KHACHHANG kh ON hd.ID_KhachHang = kh.ID "
                + "JOIN HINHTHUCTHANHTOAN httt ON hd.ID_HinhThucThanhToan = httt.ID "
                + "WHERE hd.ID = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maHoaDon);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(rs.getInt("ID"));
                hoaDon.setTenNhanVien(rs.getString("TenNhanVien"));
                hoaDon.setTenKhachHang(rs.getString("TenKhachHang"));
                hoaDon.setHinhThucThanhToan(rs.getString("HinhThucThanhToan"));
                hoaDon.setLichSuHoaDon(rs.getString("LichSuHoaDon"));
                hoaDon.setTongTien(rs.getBigDecimal("TongTien"));
                hoaDon.setHoTen(rs.getString("HoTen"));
                hoaDon.setDiaChi(rs.getString("DiaChi"));
                hoaDon.setSoDienThoai(rs.getString("SoDienThoai"));
                hoaDon.setEmail(rs.getString("Email"));
                hoaDon.setNgayTao(rs.getDate("NgayTao"));
                hoaDon.setNgaySua(rs.getDate("NgaySua"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));

                listHoaDon.add(hoaDon);
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi tìm kiếm hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }

        return listHoaDon;
    }

    public ArrayList<HoaDon> timKiemHoaDon(String trangThai, String hinhThucThanhToan, Date ngayBatDau, Date ngayKetThuc) {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();

        String sql = "SELECT hd.ID, nv.HoTen AS TenNhanVien, kh.HoTen AS TenKhachHang, httt.HinhThuc AS HinhThucThanhToan, hd.LichSuHoaDon, hd.TongTien, hd.HoTen, hd.DiaChi, hd.SoDienThoai, hd.Email, hd.NgayTao, hd.NgaySua, hd.TrangThai "
                + "FROM HOADON hd "
                + "JOIN NHANVIEN nv ON hd.ID_NhanVien = nv.ID "
                + "JOIN KHACHHANG kh ON hd.ID_KhachHang = kh.ID "
                + "JOIN HINHTHUCTHANHTOAN httt ON hd.ID_HinhThucThanhToan = httt.ID "
                + "WHERE 1=1 ";

        ArrayList<Object> params = new ArrayList<>();
        if (trangThai != null && !trangThai.equals("Tất cả")) {
            sql += " AND hd.TrangThai = ?";
            params.add(trangThai);
        }
        if (hinhThucThanhToan != null && !hinhThucThanhToan.equals("Tất cả")) {
            sql += " AND httt.HinhThuc = ?";
            params.add(hinhThucThanhToan);
        }
        if (ngayBatDau != null && ngayKetThuc != null) {
            sql += " AND hd.NgayTao BETWEEN ? AND ?";
            params.add(ngayBatDau);
            params.add(ngayKetThuc);
        }

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(rs.getInt("ID"));
                hoaDon.setTenNhanVien(rs.getString("TenNhanVien"));
                hoaDon.setTenKhachHang(rs.getString("TenKhachHang"));
                hoaDon.setHinhThucThanhToan(rs.getString("HinhThucThanhToan"));
                hoaDon.setLichSuHoaDon(rs.getString("LichSuHoaDon"));
                hoaDon.setTongTien(rs.getBigDecimal("TongTien"));
                hoaDon.setHoTen(rs.getString("HoTen"));
                hoaDon.setDiaChi(rs.getString("DiaChi"));
                hoaDon.setSoDienThoai(rs.getString("SoDienThoai"));
                hoaDon.setEmail(rs.getString("Email"));
                hoaDon.setNgayTao(rs.getDate("NgayTao"));
                hoaDon.setNgaySua(rs.getDate("NgaySua"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));
                listHoaDon.add(hoaDon);
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi tìm kiếm hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }

        return listHoaDon;
    }

}
