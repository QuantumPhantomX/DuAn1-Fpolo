package Model_LichSuHoaDon;

import java.util.Date;

public class LichSuHoaDon {
    private int id;
    private int idHoaDon;
    private String hoatDong;
    private String nguoiTacDong;
    private String lichSuHoaDon;
    private Date ngayTacDong;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getHoatDong() {
        return hoatDong;
    }

    public void setHoatDong(String hoatDong) {
        this.hoatDong = hoatDong;
    }

    public String getNguoiTacDong() {
        return nguoiTacDong;
    }

    public void setNguoiTacDong(String nguoiTacDong) {
        this.nguoiTacDong = nguoiTacDong;
    }

    public String getLichSuHoaDon() {
        return lichSuHoaDon;
    }

    public void setLichSuHoaDon(String lichSuHoaDon) {
        this.lichSuHoaDon = lichSuHoaDon;
    }

    public Date getNgayTacDong() {
        return ngayTacDong;
    }

    public void setNgayTacDong(Date ngayTacDong) {
        this.ngayTacDong = ngayTacDong;
    }

    public LichSuHoaDon(int id, int idHoaDon, String hoatDong, String nguoiTacDong, String lichSuHoaDon, Date ngayTacDong) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.hoatDong = hoatDong;
        this.nguoiTacDong = nguoiTacDong;
        this.lichSuHoaDon = lichSuHoaDon;
        this.ngayTacDong = ngayTacDong;
    }

    public LichSuHoaDon() {
    }

}