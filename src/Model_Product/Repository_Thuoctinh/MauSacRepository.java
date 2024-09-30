/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_Product.Repository_Thuoctinh;

import Model_Product.Thuoctinh.MauSac;
import com.raven.connect.DBConnect;
import java.beans.PropertyChangeSupport;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class MauSacRepository {

    private Connection connection;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public MauSacRepository(Connection connection) {
        this.connection = connection;
    }

    public void addMauSac(MauSac mauSac) throws SQLException {
        String sql = "INSERT INTO MAUSAC (Ten, MoTa) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mauSac.getTen());
            stmt.setString(2, mauSac.getMoTa());
            stmt.executeUpdate();
        }
    }

    public void updateMauSac(MauSac mauSac) throws SQLException {
        String sql = "UPDATE MAUSAC SET Ten = ?, MoTa = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mauSac.getTen());
            stmt.setString(2, mauSac.getMoTa());
            stmt.setInt(3, mauSac.getId());
            stmt.executeUpdate();
        }
    }

    public boolean deleteMauSac(int id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnect.getConnection();

            String sqlCheck = "SELECT COUNT(*) FROM SANPHAMCHITIET WHERE ID_MauSac = ?";
            ps = con.prepareStatement(sqlCheck);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);

            if (count > 0) {
                JOptionPane.showMessageDialog(null, " màu sắc này đang được sử dụng ");
                return false;
            }

            String sqlDelete = "DELETE FROM MAUSAC WHERE ID = ?";
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

    public ArrayList<MauSac> getAllMauSacs() {
        ArrayList<MauSac> listMauSacs = new ArrayList<>();
        String sql = "SELECT ID, Ten, MoTa FROM MAUSAC";
        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String tenMauSac = rs.getString("Ten");
                String moTa = rs.getString("MoTa");
                MauSac mauSac = new MauSac(id, tenMauSac, moTa);
                listMauSacs.add(mauSac);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách màu sắc: " + e.getMessage());
            e.printStackTrace();
        }

        return listMauSacs;
    }
}
