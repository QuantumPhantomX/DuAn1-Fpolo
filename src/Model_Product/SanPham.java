package Model_Product;

/**
 *
 * @author ASUS
 */
public class SanPham {

    private String id;
    private String tenSanPham;
    private String moTa;
    private int tongSoLuong;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTongSoLuong(int tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public SanPham(String id, String tenSanPham, String moTa) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
    }

    public SanPham() {
    }

    public SanPham(String id, String tenSanPham, String moTa, int tongSoLuong) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.moTa = moTa;
        this.tongSoLuong = tongSoLuong;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.id,
            this.tenSanPham, 
            this.moTa,
        };
    }
}
