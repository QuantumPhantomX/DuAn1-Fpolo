/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_Product.Repository_Thuoctinh;

import Model_Product.Thuoctinh.LoaiCoCao;
import com.raven.connect.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class LoaiCoCaoRepository {

    private Connection connection;

    public LoaiCoCaoRepository(Connection connection) {
        this.connection = connection;
    }

    public void addLoaiCoCao(LoaiCoCao loaiCoCao) throws SQLException {
        String sql = "INSERT INTO LOAICOCAO (Ten, MoTa) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, loaiCoCao.getTen());
            stmt.setString(2, loaiCoCao.getMoTa());
            stmt.executeUpdate();
        }
    }

    public void updateLoaiCoCao(LoaiCoCao loaiCoCao) throws SQLException {
        String sql = "UPDATE LOAICOCAO SET Ten = ?, MoTa = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, loaiCoCao.getTen());
            stmt.setString(2, loaiCoCao.getMoTa());
            stmt.setInt(3, loaiCoCao.getId());
            stmt.executeUpdate();
        }
    }

    public boolean deleteLoaiCoCao(int id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnect.getConnection();

            String sqlCheck = "SELECT COUNT(*) FROM SANPHAMCHITIET WHERE ID_LOAICOCAO = ?";
            ps = con.prepareStatement(sqlCheck);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);

            if (count > 0) {
                JOptionPane.showMessageDialog(null, " loại cổ áo này đang được sử dụng ");
                return false;
            }

            String sqlDelete = "DELETE FROM LOAICOCAO WHERE ID = ?";
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

    public List<LoaiCoCao> getAllLoaiCoCaos() throws SQLException {
        List<LoaiCoCao> loaiCoCaos = new ArrayList<>();
        String sql = "SELECT * FROM LOAICOCAO";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LoaiCoCao loaiCoCao = new LoaiCoCao();
                loaiCoCao.setId(rs.getInt("ID"));
                loaiCoCao.setTen(rs.getString("Ten"));
                loaiCoCao.setMoTa(rs.getString("MoTa"));
                loaiCoCaos.add(loaiCoCao);
            }
        }
        return loaiCoCaos;
    }
}
