/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository_BanHang;

import Model_Bill.HoaDon;
import Model_Bill.HoaDonChiTiet;
import Model_Product.DetailSanPham;
import com.raven.connect.DBConnect;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Repository_BanHang {

    public void themHoaDon(HoaDon hd) throws SQLException {
        String sql = "INSERT INTO HOADON (ID, ID_NhanVien, ID_KhachHang, ID_HinhThucThanhToan, TenNhanVien, TenKhachHang, HinhThucThanhToan, LichSuHoaDon, TongTien, NgayTao, NgaySua, TrangThai) "
                + "VALUES (?, null, null, null, ?, ?, ?, ?, ?, GETDATE(), NULL, N'Chưa thanh toán')";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, hd.getId());
            ps.setString(2, hd.getTenNhanVien());
            ps.setString(3, hd.getTenKhachHang()); 
            ps.setString(4, hd.getHinhThucThanhToan()); 
            ps.setString(5, hd.getLichSuHoaDon());
            ps.setBigDecimal(6, hd.getTongTien());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int generateHoaDonId() throws SQLException {
        String sql = "SELECT MAX(ID) AS maxId FROM HOADON";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int maxId = rs.getInt("maxId");
                return maxId + 1;
            }
        }
        return 1;
    }

    public ArrayList<HoaDon> getHoaDonChuaThanhToan() throws SQLException {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        String sql = "SELECT ID, ID_NhanVien, ID_KhachHang, ID_HinhThucThanhToan, TenNhanVien, TenKhachHang, HinhThucThanhToan, LichSuHoaDon, TongTien, NgayTao, NgaySua, TrangThai "
                + "FROM HOADON WHERE TrangThai = N'Chưa thanh toán'";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("ID"));
                hd.setIdNhanVien(rs.getInt("ID_NhanVien"));
                hd.setIdKhachHang(rs.getInt("ID_KhachHang"));
                hd.setIdHinhThucThanhToan(rs.getInt("ID_HinhThucThanhToan"));
                hd.setTenNhanVien(rs.getString("TenNhanVien"));
                hd.setTenKhachHang(rs.getString("TenKhachHang"));
                hd.setHinhThucThanhToan(rs.getString("HinhThucThanhToan"));
                hd.setLichSuHoaDon(rs.getString("LichSuHoaDon"));
                hd.setTongTien(rs.getBigDecimal("TongTien"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setNgaySua(rs.getDate("NgaySua"));
                hd.setTrangThai(rs.getString("TrangThai"));

                listHoaDon.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return listHoaDon;
    }

    ////////////////////////////////////////////////////////////////
    public int laySoLuongTonKho(String idSanPhamChiTiet) {
        String sql = "SELECT SoLuongTon FROM SANPHAMCHITIET WHERE ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, idSanPhamChiTiet);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("SoLuongTon");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void themHoaDonChiTiet(HoaDonChiTiet hdct) throws SQLException {
        String sql = "INSERT INTO HOADONCHITIET (ID, ID_HoaDon, ID_SanPhamChiTiet, TenSanPham, TenMauSac, TenSize, TenChatLieu, TenThuongHieu, TenLoaiCoCao, GiaBan, SoLuong, ThanhTien, TrangThai) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            int newId = generateHoaDonChiTietId();
            hdct.setId(newId);

            ps.setInt(1, hdct.getId());
            ps.setInt(2, hdct.getIdHoaDon());
            ps.setString(3, hdct.getIdSanPhamChiTiet());
            ps.setString(4, hdct.getTenSanPham());
            ps.setString(5, hdct.getTenMauSac());
            ps.setString(6, hdct.getTenSize());
            ps.setString(7, hdct.getTenChatLieu());
            ps.setString(8, hdct.getTenThuongHieu());
            ps.setString(9, hdct.getTenLoaiCoCao());
            ps.setBigDecimal(10, hdct.getGiaBan());
            ps.setInt(11, hdct.getSoLuong());
            ps.setBigDecimal(12, hdct.getThanhTien());
            ps.setString(13, hdct.getTrangThai());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public int generateHoaDonChiTietId() throws SQLException {
        String sql = "SELECT MAX(ID) AS maxId FROM HOADONCHITIET";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int maxId = rs.getInt("maxId");
                return maxId + 1;
            }
        }
        return 1;
    }

    public BigDecimal tinhTongTienHoaDon(int idHoaDon) throws SQLException {
        String sql = "SELECT SUM(ThanhTien) AS TongTien FROM HOADONCHITIET WHERE ID_HoaDon = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal("TongTien");
            }
        }
        return BigDecimal.ZERO;
    }

    public DetailSanPham getDetailSanPhamById(String idSanPhamChiTiet) {
        String sql = "SELECT spct.ID, spct.ID_SanPham, sp.TenSanPham, ms.Ten AS TenMauSac, s.Ten AS TenSize, cl.Ten AS TenChatLieu, th.Ten AS TenThuongHieu, lc.Ten AS TenLoaiCoCao, spct.GiaBan, spct.SoLuongTon, spct.MoTa "
                + "FROM SANPHAMCHITIET spct "
                + "JOIN SANPHAM sp ON spct.ID_SanPham = sp.ID "
                + "LEFT JOIN MAUSAC ms ON spct.ID_MauSac = ms.ID "
                + "LEFT JOIN SIZE s ON spct.ID_Size = s.ID "
                + "LEFT JOIN CHATLIEU cl ON spct.ID_ChatLieu = cl.ID "
                + "LEFT JOIN THUONGHIEU th ON spct.ID_ThuongHieu = th.ID "
                + "LEFT JOIN LOAICOCAO lc ON spct.ID_LOAICOCAO = lc.ID "
                + "WHERE spct.ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idSanPhamChiTiet);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DetailSanPham detailSanPham = new DetailSanPham();
                detailSanPham.setId(rs.getString("ID"));
                detailSanPham.setIdSanPham(rs.getString("ID_SanPham"));
                detailSanPham.setTenSanPham(rs.getString("TenSanPham"));
                detailSanPham.setTenMauSac(rs.getString("TenMauSac"));
                detailSanPham.setTenSize(rs.getString("TenSize"));
                detailSanPham.setTenChatLieu(rs.getString("TenChatLieu"));
                detailSanPham.setTenThuongHieu(rs.getString("TenThuongHieu"));
                detailSanPham.setTenLoaiCoCao(rs.getString("TenLoaiCoCao"));
                detailSanPham.setGiaBan(rs.getBigDecimal("GiaBan"));
                detailSanPham.setSoLuongTon(rs.getInt("SoLuongTon"));
                detailSanPham.setMoTa(rs.getString("MoTa"));

                return detailSanPham;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void capNhatHoaDon(HoaDon hd) throws SQLException {
        String sql = "UPDATE HOADON SET "
                + "TenNhanVien = ?, "
                + "TenKhachHang = ?, "
                + "HinhThucThanhToan = ?, "
                + "LichSuHoaDon = ?, "
                + "TongTien = ?, "
                + "NgaySua = GETDATE() "
                + "WHERE ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hd.getTenNhanVien());
            ps.setString(2, hd.getTenKhachHang());
            ps.setString(3, hd.getHinhThucThanhToan());
            ps.setString(4, hd.getLichSuHoaDon());
            ps.setBigDecimal(5, hd.getTongTien());
            ps.setInt(6, hd.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ArrayList<HoaDonChiTiet> getHoaDonChiTietByIdHoaDon(int idHoaDon) {
        ArrayList<HoaDonChiTiet> listHoaDonChiTiet = new ArrayList<>();
        String sql = "SELECT * FROM HOADONCHITIET WHERE ID_HoaDon = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHoaDon);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setId(rs.getInt("ID"));
                hdct.setIdHoaDon(rs.getInt("ID_HoaDon"));
                hdct.setIdSanPhamChiTiet(rs.getString("ID_SanPhamChiTiet"));
                hdct.setTenSanPham(rs.getString("TenSanPham"));
                hdct.setTenMauSac(rs.getString("TenMauSac"));
                hdct.setTenSize(rs.getString("TenSize"));
                hdct.setTenChatLieu(rs.getString("TenChatLieu"));
                hdct.setTenThuongHieu(rs.getString("TenThuongHieu"));
                hdct.setTenLoaiCoCao(rs.getString("TenLoaiCoCao"));
                hdct.setGiaBan(rs.getBigDecimal("GiaBan"));
                hdct.setSoLuong(rs.getInt("SoLuong"));
                hdct.setThanhTien(rs.getBigDecimal("ThanhTien"));
                hdct.setTrangThai(rs.getString("TrangThai"));
                listHoaDonChiTiet.add(hdct);
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách hóa đơn chi tiết: " + e.getMessage());
            e.printStackTrace();
        }

        return listHoaDonChiTiet;
    }

    public void giamSoLuongTonKho(String idSanPhamChiTiet, int soLuong) throws SQLException {
        String sql = "UPDATE SANPHAMCHITIET SET SoLuongTon = SoLuongTon - ? WHERE ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soLuong);
            ps.setString(2, idSanPhamChiTiet);
            ps.executeUpdate();
        }
    }

    public void xoaHoaDonChiTiet(int id) throws SQLException {
        String sql = "DELETE FROM HOADONCHITIET WHERE ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void tangSoLuongTonKho(String idSanPhamChiTiet, int soLuong) throws SQLException {
        String sql = "UPDATE SANPHAMCHITIET SET SoLuongTon = SoLuongTon + ? WHERE ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, soLuong);
            ps.setString(2, idSanPhamChiTiet);
            ps.executeUpdate();
        }
    }

    public void xoaToanBoGioHang(int idHoaDon) throws SQLException {
        String sql = "DELETE FROM HOADONCHITIET WHERE ID_HoaDon = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            ps.executeUpdate();
        }
    }

    public void capNhatHoaDonThanhToan(HoaDon hd) throws SQLException {
        String sql = "UPDATE HOADON SET "
                + "TenNhanVien = ?, "
                + "TenKhachHang = ?, "
                + "HinhThucThanhToan = ?, "
                + "LichSuHoaDon = ?, "
                + "TongTien = ?, "
                + "NgaySua = GETDATE(), "
                + "TrangThai = ? "
                + "WHERE ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hd.getTenNhanVien());
            ps.setString(2, hd.getTenKhachHang());
            ps.setString(3, hd.getHinhThucThanhToan());
            ps.setString(4, hd.getLichSuHoaDon());
            ps.setBigDecimal(5, hd.getTongTien());
            ps.setString(6, hd.getTrangThai());
            ps.setInt(7, hd.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void capNhatHoaDonTrangThai(HoaDon hd) throws SQLException {
        String sql = "UPDATE HOADON SET LichSuHoaDon = ?, TrangThai = ?, NgaySua = GETDATE() WHERE ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hd.getLichSuHoaDon());
            ps.setString(2, hd.getTrangThai());
            ps.setInt(3, hd.getId());
            ps.executeUpdate();
        }
    }

    public HoaDon getHoaDonById(int idHoaDon) throws SQLException {
        String sql = "SELECT * FROM HOADON WHERE ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setId(rs.getInt("ID"));
                hoaDon.setIdNhanVien(rs.getInt("ID_NhanVien"));
                hoaDon.setIdKhachHang(rs.getInt("ID_KhachHang"));
                hoaDon.setIdHinhThucThanhToan(rs.getInt("ID_HinhThucThanhToan"));
                hoaDon.setTenNhanVien(rs.getString("TenNhanVien"));
                hoaDon.setTenKhachHang(rs.getString("TenKhachHang"));
                hoaDon.setHinhThucThanhToan(rs.getString("HinhThucThanhToan"));
                hoaDon.setLichSuHoaDon(rs.getString("LichSuHoaDon"));
                hoaDon.setTongTien(rs.getBigDecimal("TongTien"));
                hoaDon.setNgayTao(rs.getDate("NgayTao"));
                hoaDon.setNgaySua(rs.getDate("NgaySua"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));
                return hoaDon;
            }
        }
        return null;
    }

    public ArrayList<HoaDon> getHoaDonTheoTrangThai(String trangThai) throws SQLException {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM HOADON WHERE TrangThai = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, trangThai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getInt("ID"));
                hd.setIdNhanVien(rs.getInt("ID_NhanVien"));
                hd.setIdKhachHang(rs.getInt("ID_KhachHang"));
                hd.setIdHinhThucThanhToan(rs.getInt("ID_HinhThucThanhToan"));
                hd.setTenNhanVien(rs.getString("TenNhanVien"));
                hd.setTenKhachHang(rs.getString("TenKhachHang"));
                hd.setHinhThucThanhToan(rs.getString("HinhThucThanhToan"));
                hd.setLichSuHoaDon(rs.getString("LichSuHoaDon"));
                hd.setTongTien(rs.getBigDecimal("TongTien"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setNgaySua(rs.getDate("NgaySua"));
                hd.setTrangThai(rs.getString("TrangThai"));
                listHoaDon.add(hd);
            }
        }
        return listHoaDon;
    }

    public String getTrangThaiHoaDonById(int idHoaDon) {
        String sql = "SELECT TrangThai FROM HOADON WHERE ID = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("TrangThai");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
