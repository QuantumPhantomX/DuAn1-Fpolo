/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_Product.Repository_Thuoctinh;

import Model_Product.Thuoctinh.Size;
import com.raven.connect.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class SizeRepository {

    private Connection connection;

    public SizeRepository(Connection connection) {
        this.connection = connection;
    }

    public void addSize(Size size) throws SQLException {
        String sql = "INSERT INTO SIZE (Ten, MoTa) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, size.getTen());
            stmt.setString(2, size.getMoTa());
            stmt.executeUpdate();
        }
    }

    public void updateSize(Size size) throws SQLException {
        String sql = "UPDATE SIZE SET Ten = ?, MoTa = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, size.getTen());
            stmt.setString(2, size.getMoTa());
            stmt.setInt(3, size.getId());
            stmt.executeUpdate();
        }
    }

    public boolean deleteSize(int id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnect.getConnection();
            String sqlCheck = "SELECT COUNT(*) FROM SANPHAMCHITIET WHERE ID_Size = ?";
            ps = con.prepareStatement(sqlCheck);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);

            if (count > 0) {
                JOptionPane.showMessageDialog(null, "Size này đang được sử dụng");
                return false;
            }

            String sqlDelete = "DELETE FROM SIZE WHERE ID = ?";
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

    public List<Size> getAllSizes() throws SQLException {
        List<Size> sizes = new ArrayList<>();
        String sql = "SELECT * FROM SIZE";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Size size = new Size();
                size.setId(rs.getInt("ID"));
                size.setTen(rs.getString("Ten"));
                size.setMoTa(rs.getString("MoTa"));
                sizes.add(size);
            }
        }
        return sizes;
    }
}
