/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_Product.Repository_Thuoctinh;

import Model_Product.Thuoctinh.ThuongHieu;
import com.raven.connect.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ThuongHieuRepository {

    private Connection connection;

    public ThuongHieuRepository(Connection connection) {
        this.connection = connection;
    }

    public void addThuongHieu(ThuongHieu thuongHieu) throws SQLException {
        String sql = "INSERT INTO THUONGHIEU (Ten, MoTa) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, thuongHieu.getTen());
            stmt.setString(2, thuongHieu.getMoTa());
            stmt.executeUpdate();
        }
    }

    public void updateThuongHieu(ThuongHieu thuongHieu) throws SQLException {
        String sql = "UPDATE THUONGHIEU SET Ten = ?, MoTa = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, thuongHieu.getTen());
            stmt.setString(2, thuongHieu.getMoTa());
            stmt.setInt(3, thuongHieu.getId());
            stmt.executeUpdate();
        }
    }

    public boolean deleteThuongHieu(int id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnect.getConnection();

            String sqlCheck = "SELECT COUNT(*) FROM SANPHAMCHITIET WHERE ID_ThuongHieu = ?";
            ps = con.prepareStatement(sqlCheck);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);

            if (count > 0) {

                JOptionPane.showMessageDialog(null, " thương hiệu này đang được sử dụng");
                return false;
            }

            String sqlDelete = "DELETE FROM THUONGHIEU WHERE ID = ?";
            ps = con.prepareStatement(sqlDelete);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(con);
        }
    }

    public List<ThuongHieu> getAllThuongHieus() throws SQLException {
        List<ThuongHieu> thuongHieus = new ArrayList<>();
        String sql = "SELECT * FROM THUONGHIEU";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ThuongHieu thuongHieu = new ThuongHieu();
                thuongHieu.setId(rs.getInt("ID"));
                thuongHieu.setTen(rs.getString("Ten"));
                thuongHieu.setMoTa(rs.getString("MoTa"));
                thuongHieus.add(thuongHieu);
            }
        }
        return thuongHieus;
    }
}
