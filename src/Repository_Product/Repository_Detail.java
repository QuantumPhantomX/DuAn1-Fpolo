package Repository_Product;

import Model_Product.DetailSanPham;
import com.raven.connect.DBConnect;
import Model_Product.Thuoctinh.ChatLieu;
import Model_Product.Thuoctinh.LoaiCoCao;
import Model_Product.Thuoctinh.MauSac;
import Model_Product.Thuoctinh.Size;
import Model_Product.Thuoctinh.ThuongHieu;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Repository_Detail {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public ArrayList<DetailSanPham> getAll() {
        ArrayList<DetailSanPham> listDetails = new ArrayList<>();
        String sql = "SELECT d.ID, d.ID_SanPham, d.TenSanPham, "
                + "m.Ten AS TenMauSac, "
                + "s.Ten AS TenSize, "
                + "c.Ten AS TenChatLieu, "
                + "h.Ten AS TenThuongHieu, "
                + "l.Ten AS TenLoaiCao, "
                + "d.GiaBan, d.SoLuongTon, d.MoTa "
                + "FROM SANPHAMCHITIET d "
                + "LEFT JOIN MAUSAC m ON d.ID_MauSac = m.ID "
                + "LEFT JOIN SIZE s ON d.ID_Size = s.ID "
                + "LEFT JOIN CHATLIEU c ON d.ID_ChatLieu = c.ID "
                + "LEFT JOIN THUONGHIEU h ON d.ID_ThuongHieu = h.ID "
                + "LEFT JOIN LOAICOCAO l ON d.ID_LOAICOCAO = l.ID";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("ID");
                String idSanPham = rs.getString("ID_SanPham");
                String tenSanPham = rs.getString("TenSanPham");
                String tenMauSac = rs.getString("TenMauSac");
                String tenSize = rs.getString("TenSize");
                String tenChatLieu = rs.getString("TenChatLieu");
                String tenThuongHieu = rs.getString("TenThuongHieu");
                String tenLoaiCao = rs.getString("TenLoaiCao");
                BigDecimal giaBan = rs.getBigDecimal("GiaBan");
                int soLuongTon = rs.getInt("SoLuongTon");
                String moTa = rs.getString("MoTa");

                DetailSanPham detail = new DetailSanPham(
                        id,
                        idSanPham,
                        tenSanPham,
                        tenMauSac,
                        tenSize,
                        tenChatLieu,
                        tenThuongHieu,
                        tenLoaiCao,
                        giaBan,
                        soLuongTon,
                        moTa
                );
                listDetails.add(detail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listDetails;
    }

    public ArrayList<DetailSanPham> searchDetailProductsByKeyword(String keyword) {
        ArrayList<DetailSanPham> filteredDetailProducts = new ArrayList<>();
        String sql = "SELECT dc.ID, dc.ID_SanPham, dc.TenSanPham, "
                + "m.Ten AS TenMauSac, s.Ten AS TenSize, "
                + "cl.Ten AS TenChatLieu, th.Ten AS TenThuongHieu, "
                + "l.Ten AS TenLoaiCao, "
                + "dc.GiaBan, dc.SoLuongTon, dc.MoTa "
                + "FROM SANPHAMCHITIET dc "
                + "JOIN MAUSAC m ON dc.ID_MauSac = m.ID "
                + "JOIN SIZE s ON dc.ID_Size = s.ID "
                + "JOIN CHATLIEU cl ON dc.ID_ChatLieu = cl.ID "
                + "JOIN THUONGHIEU th ON dc.ID_ThuongHieu = th.ID "
                + "JOIN LOAICOCAO l ON dc.ID_LOAICOCAO = l.ID "
                + "WHERE dc.TenSanPham LIKE ? OR dc.MoTa LIKE ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("ID");
                    String idSanPham = rs.getString("ID_SanPham");
                    String tenSanPham = rs.getString("TenSanPham");
                    String tenMauSac = rs.getString("TenMauSac");
                    String tenSize = rs.getString("TenSize");
                    String tenChatLieu = rs.getString("TenChatLieu");
                    String tenThuongHieu = rs.getString("TenThuongHieu");
                    String tenLoaiCao = rs.getString("TenLoaiCao");
                    BigDecimal giaBan = rs.getBigDecimal("GiaBan");
                    int soLuongTon = rs.getInt("SoLuongTon");
                    String moTa = rs.getString("MoTa");

                    DetailSanPham detailSanPham = new DetailSanPham(
                            id,
                            idSanPham,
                            tenSanPham,
                            tenMauSac,
                            tenSize,
                            tenChatLieu,
                            tenThuongHieu,
                            tenLoaiCao,
                            giaBan,
                            soLuongTon,
                            moTa
                    );
                    filteredDetailProducts.add(detailSanPham);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return filteredDetailProducts;
    }

    public boolean addDetailProduct(DetailSanPham detailSanPham) throws SQLException {
        Connection con = null;
        PreparedStatement psSanPhamChiTiet = null;

        try {
            con = DBConnect.getConnection();
            con.setAutoCommit(false);

            String generatedId = generateDetailProductId();
            String sqlSanPhamChiTiet = "INSERT INTO SANPHAMCHITIET (ID, ID_SanPham, TenSanPham, ID_MauSac, ID_Size, ID_ChatLieu, ID_ThuongHieu, ID_LOAICOCAO, GiaBan, SoLuongTon, MoTa) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            psSanPhamChiTiet = con.prepareStatement(sqlSanPhamChiTiet);
            psSanPhamChiTiet.setString(1, generatedId);
            psSanPhamChiTiet.setString(2, detailSanPham.getIdSanPham());
            psSanPhamChiTiet.setString(3, detailSanPham.getTenSanPham());
            psSanPhamChiTiet.setInt(4, detailSanPham.getIdMauSac());
            psSanPhamChiTiet.setInt(5, detailSanPham.getIdSize());
            psSanPhamChiTiet.setInt(6, detailSanPham.getIdChatLieu());
            psSanPhamChiTiet.setInt(7, detailSanPham.getIdThuongHieu());
            psSanPhamChiTiet.setInt(8, detailSanPham.getIdLoaiCoCao());
            psSanPhamChiTiet.setBigDecimal(9, detailSanPham.getGiaBan());
            psSanPhamChiTiet.setInt(10, detailSanPham.getSoLuongTon());
            psSanPhamChiTiet.setString(11, detailSanPham.getMoTa());
            psSanPhamChiTiet.executeUpdate();

            con.commit();
            pcs.firePropertyChange("dataChanged", null, null);
            return true;
        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {

            DBConnect.closePreparedStatement(psSanPhamChiTiet);
            DBConnect.closeConnection(con);
        }
    }

    public boolean updateDetailProduct(DetailSanPham detailSanPham) throws SQLException {
        Connection con = null;
        PreparedStatement psSanPhamChiTiet = null;

        try {
            con = DBConnect.getConnection();
            con.setAutoCommit(false);

            String sqlSanPhamChiTiet = "UPDATE SANPHAMCHITIET SET ID_SanPham = ?, TenSanPham = ?, ID_MauSac = ?, ID_Size = ?, ID_ChatLieu = ?, "
                    + "ID_ThuongHieu = ?, ID_LOAICOCAO = ?, GiaBan = ?, SoLuongTon = ?, MoTa = ? WHERE ID = ?";
            psSanPhamChiTiet = con.prepareStatement(sqlSanPhamChiTiet);
            psSanPhamChiTiet.setString(1, detailSanPham.getIdSanPham());
            psSanPhamChiTiet.setString(2, detailSanPham.getTenSanPham());
            psSanPhamChiTiet.setInt(3, detailSanPham.getIdMauSac());
            psSanPhamChiTiet.setInt(4, detailSanPham.getIdSize());
            psSanPhamChiTiet.setInt(5, detailSanPham.getIdChatLieu());
            psSanPhamChiTiet.setInt(6, detailSanPham.getIdThuongHieu());
            psSanPhamChiTiet.setInt(7, detailSanPham.getIdLoaiCoCao());
            psSanPhamChiTiet.setBigDecimal(8, detailSanPham.getGiaBan());
            psSanPhamChiTiet.setInt(9, detailSanPham.getSoLuongTon());
            psSanPhamChiTiet.setString(10, detailSanPham.getMoTa());
            psSanPhamChiTiet.setString(11, detailSanPham.getId());
            psSanPhamChiTiet.executeUpdate();

            con.commit();
            pcs.firePropertyChange("dataChanged", null, null);
            return true;
        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            DBConnect.closePreparedStatement(psSanPhamChiTiet);
            DBConnect.closeConnection(con);
        }
    }

    public boolean deleteDetailProduct(String id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBConnect.getConnection();
            con.setAutoCommit(false); 

            String sqlGetIdSanPham = "SELECT ID_SanPham FROM SANPHAMCHITIET WHERE ID = ?";
            ps = con.prepareStatement(sqlGetIdSanPham);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String idSanPham = rs.getString("ID_SanPham");
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);

            String sqlCheckSPCT = "SELECT COUNT(*) FROM SANPHAMCHITIET WHERE ID_SanPham = ?";
            ps = con.prepareStatement(sqlCheckSPCT);
            ps.setString(1, idSanPham);
            rs = ps.executeQuery();
            rs.next();
            int countSPCT = rs.getInt(1);
            DBConnect.closeResultSet(rs);
            DBConnect.closePreparedStatement(ps);

            if (countSPCT <= 1) {
                JOptionPane.showMessageDialog(null, "Không thể xóa sản phẩm chi tiết cuối cùng của sản phẩm này.");
                return false;
            }

            // Nếu còn sản phẩm chi tiết khác, thực hiện xóa
            String sqlDelete = "DELETE FROM SANPHAMCHITIET WHERE ID = ?";
            ps = con.prepareStatement(sqlDelete);
            ps.setString(1, id);
            int rowsAffected = ps.executeUpdate();
            con.commit();
            pcs.firePropertyChange("dataChanged", null, null);
            return rowsAffected > 0;

        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            DBConnect.closePreparedStatement(ps);
            DBConnect.closeConnection(con);
        }
    }

    ///////////////////////////////////////////////////
    public ArrayList<MauSac> getAllMauSac() {
        ArrayList<MauSac> colors = new ArrayList<>();
        String sql = "SELECT ID, Ten, MoTa FROM MAUSAC";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String ten = rs.getString("Ten");
                String moTa = rs.getString("MoTa");
                MauSac mauSac = new MauSac(id, ten, moTa);
                colors.add(mauSac);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return colors;
    }

    public ArrayList<Size> getAllSize() {
        ArrayList<Size> sizes = new ArrayList<>();
        String sql = "SELECT ID, Ten, MoTa FROM SIZE";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String ten = rs.getString("Ten");
                String moTa = rs.getString("MoTa");
                Size size = new Size(id, ten, moTa);
                sizes.add(size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sizes;
    }

    public ArrayList<ChatLieu> getAllChatLieu() {
        ArrayList<ChatLieu> chatLieus = new ArrayList<>();
        String sql = "SELECT ID, Ten, MoTa FROM CHATLIEU";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String ten = rs.getString("Ten");
                String moTa = rs.getString("MoTa");
                ChatLieu chatLieu = new ChatLieu(id, ten, moTa);
                chatLieus.add(chatLieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chatLieus;
    }

    public ArrayList<ThuongHieu> getAllThuongHieu() {
        ArrayList<ThuongHieu> thuongHieus = new ArrayList<>();
        String sql = "SELECT ID, Ten, MoTa FROM THUONGHIEU";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String ten = rs.getString("Ten");
                String moTa = rs.getString("MoTa");
                ThuongHieu thuongHieu = new ThuongHieu(id, ten, moTa);
                thuongHieus.add(thuongHieu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thuongHieus;
    }

    public ArrayList<LoaiCoCao> getAllLoaiCoCao() {
        ArrayList<LoaiCoCao> loaiCoCaos = new ArrayList<>();
        String sql = "SELECT ID, Ten, MoTa FROM LOAICOCAO";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String ten = rs.getString("Ten");
                String moTa = rs.getString("MoTa");
                LoaiCoCao loaiCoCao = new LoaiCoCao(id, ten, moTa);
                loaiCoCaos.add(loaiCoCao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loaiCoCaos;
    }

    //////////////////////////////////
    public String generateDetailProductId() {
        String sql = "SELECT MAX(CAST(SUBSTRING(ID, 2, LEN(ID)) AS INT)) AS maxId FROM SANPHAMCHITIET";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int maxId = rs.getInt("maxId");
                int nextId = maxId + 1;
                return "B" + nextId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "B1";
    }

    /////////////////////////////////////////
    public ArrayList<DetailSanPham> getDetailProductsByTrangThai(boolean conHang) {
        ArrayList<DetailSanPham> filteredProducts = new ArrayList<>();
        String sql = "SELECT d.ID, d.ID_SanPham, d.TenSanPham, "
                + "m.Ten AS TenMauSac, "
                + "s.Ten AS TenSize, "
                + "c.Ten AS TenChatLieu, "
                + "h.Ten AS TenThuongHieu, "
                + "l.Ten AS TenLoaiCao, "
                + "d.GiaBan, d.SoLuongTon, d.MoTa "
                + "FROM SANPHAMCHITIET d "
                + "LEFT JOIN MAUSAC m ON d.ID_MauSac = m.ID "
                + "LEFT JOIN SIZE s ON d.ID_Size = s.ID "
                + "LEFT JOIN CHATLIEU c ON d.ID_ChatLieu = c.ID "
                + "LEFT JOIN THUONGHIEU h ON d.ID_ThuongHieu = h.ID "
                + "LEFT JOIN LOAICOCAO l ON d.ID_LOAICOCAO = l.ID "
                + "WHERE d.SoLuongTon " + (conHang ? ">" : "=") + " 0";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("ID");
                String idSanPham = rs.getString("ID_SanPham");
                String tenSanPham = rs.getString("TenSanPham");
                String tenMauSac = rs.getString("TenMauSac");
                String tenSize = rs.getString("TenSize");
                String tenChatLieu = rs.getString("TenChatLieu");
                String tenThuongHieu = rs.getString("TenThuongHieu");
                String tenLoaiCao = rs.getString("TenLoaiCao");
                BigDecimal giaBan = rs.getBigDecimal("GiaBan");
                int soLuongTon = rs.getInt("SoLuongTon");
                String moTa = rs.getString("MoTa");

                DetailSanPham detailSanPham = new DetailSanPham(
                        id,
                        idSanPham,
                        tenSanPham,
                        tenMauSac,
                        tenSize,
                        tenChatLieu,
                        tenThuongHieu,
                        tenLoaiCao,
                        giaBan,
                        soLuongTon,
                        moTa
                );
                filteredProducts.add(detailSanPham);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredProducts;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

}
