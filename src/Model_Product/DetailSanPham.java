package Model_Product;

import java.math.BigDecimal;

public class DetailSanPham {

    private String id;
    private String idSanPham;
    private String tenSanPham;
    private int idMauSac;
    private int idSize;
    private int idChatLieu;
    private int idThuongHieu;
    private int idLoaiCoCao;
    private String tenMauSac;
    private String tenSize;
    private String tenChatLieu;
    private String tenThuongHieu;
    private String tenLoaiCoCao;
    private BigDecimal giaBan;
    private int soLuongTon;
    private String moTa;

    public DetailSanPham(String id, String idSanPham, String tenSanPham, int idMauSac, int idSize, int idChatLieu, int idThuongHieu, int idLoaiCoCao, BigDecimal giaBan, int soLuongTon, String moTa) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.idMauSac = idMauSac;
        this.idSize = idSize;
        this.idChatLieu = idChatLieu;
        this.idThuongHieu = idThuongHieu;
        this.idLoaiCoCao = idLoaiCoCao;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
    }

    public DetailSanPham(String id, String idSanPham, String tenSanPham, String tenMauSac, String tenSize,
            String tenChatLieu, String tenThuongHieu, String tenLoaiCoCao,
            BigDecimal giaBan, int soLuongTon, String moTa) {
        this.id = id;
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.tenMauSac = tenMauSac;
        this.tenSize = tenSize;
        this.tenChatLieu = tenChatLieu;
        this.tenThuongHieu = tenThuongHieu;
        this.tenLoaiCoCao = tenLoaiCoCao;
        this.giaBan = giaBan;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
    }

    public DetailSanPham() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenMauSac() {
        return tenMauSac;
    }

    public void setTenMauSac(String tenMauSac) {
        this.tenMauSac = tenMauSac;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public String getTenThuongHieu() {
        return tenThuongHieu;
    }

    public void setTenThuongHieu(String tenThuongHieu) {
        this.tenThuongHieu = tenThuongHieu;
    }

    public String getTenLoaiCoCao() {
        return tenLoaiCoCao;
    }

    public void setTenLoaiCoCao(String tenLoaiCoCao) {
        this.tenLoaiCoCao = tenLoaiCoCao;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(int idMauSac) {
        this.idMauSac = idMauSac;
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public int getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(int idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public int getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(int idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public int getIdLoaiCoCao() {
        return idLoaiCoCao;
    }

    public void setIdLoaiCoCao(int idLoaiCoCao) {
        this.idLoaiCoCao = idLoaiCoCao;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Object[] toDataRow() {
        return new Object[]{
            this.id,
            this.idSanPham,
            this.tenSanPham,
            this.idMauSac,
            this.idSize,
            this.idChatLieu,
            this.idThuongHieu,
            this.idLoaiCoCao,
            this.giaBan,
            this.soLuongTon,
            this.moTa
        };
    }

    public Object[] toDataRow2() {
        return new Object[]{
            this.id,
            this.idSanPham,
            this.tenSanPham,
            this.tenMauSac,
            this.tenSize,
            this.tenChatLieu,
            this.tenThuongHieu,
            this.tenLoaiCoCao,
            this.giaBan,
            this.soLuongTon,
            this.moTa
        };
    }
}
