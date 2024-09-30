package Repository_Product;

import com.raven.connect.DBConnect;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model_Product.SanPham;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Repository_SanPham {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public ArrayList<SanPham> getAll() {
        ArrayList<SanPham> listProducts = new ArrayList<>();
        String sql = "SELECT sp.ID, sp.TenSanPham, sp.MoTa, SUM(spct.SoLuongTon) AS TongSoLuong "
                + "FROM SANPHAM sp "
                + "LEFT JOIN SANPHAMCHITIET spct ON sp.ID = spct.ID_SanPham "
                + "GROUP BY sp.ID, sp.TenSanPham, sp.MoTa";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("ID");
                String tenSanPham = rs.getString("TenSanPham");
                String moTa = rs.getString("MoTa");
                int tongSoLuong = rs.getInt("TongSoLuong");

                SanPham sanPham = new SanPham(id, tenSanPham, moTa, tongSoLuong);
                listProducts.add(sanPham);
            }

        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }

        return listProducts;
    }

    public ArrayList<SanPham> searchProductsByKeyword(String keyword) {
        ArrayList<SanPham> filteredProducts = new ArrayList<>();
        String sql = "SELECT ID, TenSanPham, MoTa FROM SANPHAM WHERE TenSanPham LIKE ? OR MoTa LIKE ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            String searchPattern = keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("ID");
                    String tenSanPham = rs.getString("TenSanPham");
                    String moTa = rs.getString("MoTa");
                    SanPham sanPham = new SanPham(id, tenSanPham, moTa);
                    filteredProducts.add(sanPham);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return filteredProducts;
    }

    public void insertSanPham(String tenSanPham, String moTa) throws SQLException {
        Connection con = null;
        PreparedStatement psSanPham = null;
        PreparedStatement psSanPhamChiTiet = null;

        try {
            con = DBConnect.getConnection();
            con.setAutoCommit(false);

            String sanPhamId = generateProductId();
            String sanPhamChiTietId = generateDetailProductId();

            String sqlSanPham = "INSERT INTO SANPHAM (ID, TenSanPham, MoTa) VALUES (?, ?, ?)";
            psSanPham = con.prepareStatement(sqlSanPham);
            psSanPham.setString(1, sanPhamId);
            psSanPham.setString(2, tenSanPham);
            psSanPham.setString(3, moTa);
            try {
                psSanPham.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Lỗi khi insert vào SANPHAM: " + e.getMessage());
                throw e;
            }

            String sqlSanPhamChiTiet = "INSERT INTO SANPHAMCHITIET (ID, ID_SanPham, TenSanPham, ID_MauSac, ID_Size, ID_ChatLieu, ID_ThuongHieu, ID_LOAICOCAO, GiaBan, SoLuongTon, MoTa) "
                    + "VALUES (?, ?, ?, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL)";
            psSanPhamChiTiet = con.prepareStatement(sqlSanPhamChiTiet);
            psSanPhamChiTiet.setString(1, sanPhamChiTietId);
            psSanPhamChiTiet.setString(2, sanPhamId);
            psSanPhamChiTiet.setString(3, tenSanPham);
            try {
                psSanPhamChiTiet.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Lỗi khi insert vào SANPHAMCHITIET: " + e.getMessage());
                throw e;
            }

            con.commit();
            System.out.println("Thêm sản phẩm thành công!");
            pcs.firePropertyChange("dataChanged", null, null);
        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
            }
            System.err.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
            throw e;
        } finally {
            if (psSanPham != null) {
                try {
                    psSanPham.close();
                } catch (SQLException e) {
                    System.err.println("Lỗi khi đóng PreparedStatement psSanPham: " + e.getMessage());
                }
            }

            if (psSanPhamChiTiet != null) {
                try {
                    psSanPhamChiTiet.close();
                } catch (SQLException e) {
                    System.err.println("Lỗi khi đóng PreparedStatement psSanPhamChiTiet: " + e.getMessage());
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Lỗi khi đóng Connection: " + e.getMessage());
                }
            }
        }

    }

    public int update(SanPham sp) throws SQLException {
        Connection con = null;
        PreparedStatement psSanPham = null;
        PreparedStatement psSanPhamChiTiet = null;

        try {
            con = DBConnect.getConnection();
            con.setAutoCommit(false);

            String sqlSanPham = "UPDATE SANPHAM SET TenSanPham = ?, MoTa = ? WHERE ID = ?";
            psSanPham = con.prepareStatement(sqlSanPham);
            psSanPham.setString(1, sp.getTenSanPham());
            psSanPham.setString(2, sp.getMoTa());
            psSanPham.setString(3, sp.getId());
            psSanPham.executeUpdate();

            String sqlSanPhamChiTiet = "UPDATE SANPHAMCHITIET SET TenSanPham = ? WHERE ID_SanPham = ?";
            psSanPhamChiTiet = con.prepareStatement(sqlSanPhamChiTiet);
            psSanPhamChiTiet.setString(1, sp.getTenSanPham());
            psSanPhamChiTiet.setString(2, sp.getId());
            psSanPhamChiTiet.executeUpdate();

            con.commit();
            pcs.firePropertyChange("dataChanged", null, null);
            return 1;
        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (psSanPham != null) {
                try {
                    psSanPham.close();
                } catch (SQLException e) {
                    System.err.println("Lỗi khi đóng PreparedStatement psSanPham: " + e.getMessage());
                }
            }

            if (psSanPhamChiTiet != null) {
                try {
                    psSanPhamChiTiet.close();
                } catch (SQLException e) {
                    System.err.println("Lỗi khi đóng PreparedStatement psSanPhamChiTiet: " + e.getMessage());
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Lỗi khi đóng Connection: " + e.getMessage());
                }
            }
        }
    }

    public int delete(String id) throws SQLException {
        Connection con = null;
        PreparedStatement psSanPhamChiTiet = null;
        PreparedStatement psSanPham = null;

        try {
            con = DBConnect.getConnection();
            con.setAutoCommit(false);

            String sqlSanPhamChiTiet = "DELETE FROM SANPHAMCHITIET WHERE ID_SanPham = ?";
            psSanPhamChiTiet = con.prepareStatement(sqlSanPhamChiTiet);
            psSanPhamChiTiet.setString(1, id);
            psSanPhamChiTiet.executeUpdate();

            String sqlSanPham = "DELETE FROM SANPHAM WHERE ID = ?";
            psSanPham = con.prepareStatement(sqlSanPham);
            psSanPham.setString(1, id);
            psSanPham.executeUpdate();

            con.commit();
            pcs.firePropertyChange("dataChanged", null, null);
            return 1;
        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (psSanPham != null) {
                try {
                    psSanPham.close();
                } catch (SQLException e) {
                    System.err.println("Lỗi khi đóng PreparedStatement psSanPham: " + e.getMessage());
                }
            }

            if (psSanPhamChiTiet != null) {
                try {
                    psSanPhamChiTiet.close();
                } catch (SQLException e) {
                    System.err.println("Lỗi khi đóng PreparedStatement psSanPhamChiTiet: " + e.getMessage());
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Lỗi khi đóng Connection: " + e.getMessage());
                }
            }
        }
    }

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

    public String generateProductId() {
        String sql = "SELECT MAX(CAST(SUBSTRING(ID, 2, LEN(ID)) AS INT)) AS maxId FROM SANPHAM";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                int maxId = rs.getInt("maxId");
                int nextId = maxId + 1;
                return "A" + nextId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "A1";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
