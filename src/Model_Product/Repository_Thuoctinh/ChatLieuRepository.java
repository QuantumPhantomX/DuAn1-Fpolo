/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_Product.Repository_Thuoctinh;

import Model_Product.Thuoctinh.ChatLieu;
import com.raven.connect.DBConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ChatLieuRepository {

    private Connection connection;

    public ChatLieuRepository(Connection connection) {
        this.connection = connection;
    }

    public void addChatLieu(ChatLieu chatLieu) throws SQLException {
        String sql = "INSERT INTO CHATLIEU (Ten, MoTa) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, chatLieu.getTen());
            stmt.setString(2, chatLieu.getMoTa());
            stmt.executeUpdate();
        }
    }

    public void updateChatLieu(ChatLieu chatLieu) throws SQLException {
        String sql = "UPDATE CHATLIEU SET Ten = ?, MoTa = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, chatLieu.getTen());
            stmt.setString(2, chatLieu.getMoTa());
            stmt.setInt(3, chatLieu.getId());
            stmt.executeUpdate();
        }
    }

    public boolean deleteChatLieu(int id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnect.getConnection();

            String sqlCheck = "SELECT COUNT(*) FROM SANPHAMCHITIET WHERE ID_ChatLieu = ?";
            ps = con.prepareStatement(sqlCheck);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);

            if (count > 0) {
                JOptionPane.showMessageDialog(null, " chất liệu này đang được sử dụng.");
                return false;
            }

            String sqlDelete = "DELETE FROM CHATLIEU WHERE ID = ?";
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

    public List<ChatLieu> getAllChatLieus() throws SQLException {
        List<ChatLieu> chatLieus = new ArrayList<>();
        String sql = "SELECT * FROM CHATLIEU";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ChatLieu chatLieu = new ChatLieu();
                chatLieu.setId(rs.getInt("ID"));
                chatLieu.setTen(rs.getString("Ten"));
                chatLieu.setMoTa(rs.getString("MoTa"));
                chatLieus.add(chatLieu);
            }
        }
        return chatLieus;
    }
}
