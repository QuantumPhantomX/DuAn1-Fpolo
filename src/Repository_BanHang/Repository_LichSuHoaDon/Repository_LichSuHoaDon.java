/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository_BanHang.Repository_LichSuHoaDon;

import Model_LichSuHoaDon.LichSuHoaDon;
import com.raven.connect.DBConnect;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author ASUS
 */
public class Repository_LichSuHoaDon {
       public void themLichSuHoaDon(LichSuHoaDon lichSu) throws SQLException {
        String sql = "INSERT INTO LICHSUHOADON (ID_HoaDon, HoatDong, NguoiTacDong, LichSuHoaDon, NgayTacDong) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, lichSu.getIdHoaDon());
            ps.setString(2, lichSu.getHoatDong());
            ps.setString(3, lichSu.getNguoiTacDong());
            ps.setString(4, lichSu.getLichSuHoaDon());
            ps.setDate(5, new java.sql.Date(lichSu.getNgayTacDong().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    public ArrayList<LichSuHoaDon> getAllLichSuHoaDon() {
        ArrayList<LichSuHoaDon> listLichSuHoaDon = new ArrayList<>();
        String sql = "SELECT * FROM LICHSUHOADON";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LichSuHoaDon lichSuHoaDon = new LichSuHoaDon();
                lichSuHoaDon.setId(rs.getInt("ID"));
                lichSuHoaDon.setIdHoaDon(rs.getInt("ID_HoaDon"));
                lichSuHoaDon.setHoatDong(rs.getString("HoatDong"));
                lichSuHoaDon.setNguoiTacDong(rs.getString("NguoiTacDong"));
                lichSuHoaDon.setLichSuHoaDon(rs.getString("LichSuHoaDon"));
                lichSuHoaDon.setNgayTacDong(rs.getDate("NgayTacDong"));
                listLichSuHoaDon.add(lichSuHoaDon);
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách lịch sử hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }

        return listLichSuHoaDon;
    }
    public ArrayList<LichSuHoaDon> timKiemLichSuHoaDonTheoMa(int maHoaDon) {
        ArrayList<LichSuHoaDon> listLichSuHoaDon = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM LICHSUHOADON "
                + "WHERE ID_HoaDon = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, maHoaDon);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LichSuHoaDon lichSuHoaDon = new LichSuHoaDon();
                lichSuHoaDon.setId(rs.getInt("ID"));
                lichSuHoaDon.setIdHoaDon(rs.getInt("ID_HoaDon"));
                lichSuHoaDon.setHoatDong(rs.getString("HoatDong"));
                lichSuHoaDon.setNguoiTacDong(rs.getString("NguoiTacDong"));
                lichSuHoaDon.setLichSuHoaDon(rs.getString("LichSuHoaDon"));
                lichSuHoaDon.setNgayTacDong(rs.getDate("NgayTacDong"));
                listLichSuHoaDon.add(lichSuHoaDon);
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi tìm kiếm lịch sử hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }

        return listLichSuHoaDon;
    }
}
