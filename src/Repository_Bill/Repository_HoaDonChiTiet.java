/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository_Bill;

import Model_Bill.HoaDonChiTiet;
import com.raven.connect.DBConnect;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author ASUS
 */
public class Repository_HoaDonChiTiet {

    public ArrayList<HoaDonChiTiet> getHoaDonChiTietByIdHoaDon(int idHoaDon) {
        ArrayList<HoaDonChiTiet> listHoaDonChiTiet = new ArrayList<>();
        String sql = "SELECT hdct.ID, hdct.ID_HoaDon, hdct.ID_SanPhamChiTiet, hdct.TenSanPham, hdct.TenMauSac, hdct.TenSize, hdct.TenChatLieu, hdct.TenThuongHieu, hdct.TenLoaiCoCao, hdct.GiaBan, hdct.SoLuong, hdct.ThanhTien, hdct.TrangThai " +
             "FROM HOADONCHITIET hdct " +
             "WHERE hdct.ID_HoaDon = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idHoaDon);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setId(rs.getInt("ID"));
                hoaDonChiTiet.setIdHoaDon(rs.getInt("ID_HoaDon"));
                hoaDonChiTiet.setIdSanPhamChiTiet(rs.getString("ID_SanPhamChiTiet"));
                hoaDonChiTiet.setTenSanPham(rs.getString("TenSanPham"));
                hoaDonChiTiet.setTenMauSac(rs.getString("TenMauSac"));
                hoaDonChiTiet.setTenSize(rs.getString("TenSize"));
                hoaDonChiTiet.setTenChatLieu(rs.getString("TenChatLieu"));
                hoaDonChiTiet.setTenThuongHieu(rs.getString("TenThuongHieu"));
                hoaDonChiTiet.setTenLoaiCoCao(rs.getString("TenLoaiCoCao"));
                hoaDonChiTiet.setGiaBan(rs.getBigDecimal("GiaBan"));
                hoaDonChiTiet.setSoLuong(rs.getInt("SoLuong"));
                hoaDonChiTiet.setThanhTien(rs.getBigDecimal("ThanhTien"));
                hoaDonChiTiet.setTrangThai(rs.getString("TrangThai"));
                listHoaDonChiTiet.add(hoaDonChiTiet);
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách hóa đơn chi tiết: " + e.getMessage());
            e.printStackTrace();
        }

        return listHoaDonChiTiet;
    }
}
