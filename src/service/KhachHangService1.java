/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.raven.connect.DBConnect;
import entity.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class KhachHangService1 {
    public ArrayList<KhachHang> getAllSV() {
        String sql = "SELECT ID, HoTen, DiaChi, SoDienThoai, Email, GioiTinh, TrangThai, NgayTao FROM KhachHang";
        try ( Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ArrayList<KhachHang> listSV = new ArrayList<>();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setID(rs.getInt(1));
                khachHang.setHoTen(rs.getString(2));
                khachHang.setDiaChi(rs.getString(3));
                khachHang.setSoDienThoai(rs.getString(4));
                khachHang.setEmail(rs.getString(5));
                khachHang.setGioiTinh(rs.getString(6)); 
                khachHang.setTrangThai(rs.getString(7));
                khachHang.setNgayTao(rs.getDate(8));
                listSV.add(khachHang);
            }
            return listSV;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean themKhachHang(KhachHang kh) {
        String sql = "INSERT INTO KhachHang (ID, HoTen, DiaChi, SoDienThoai, Email, GioiTinh, TrangThai, NgayTao) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, GETDATE())";

        int check = 0;

        try ( Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, kh.getID());
            ps.setString(2, kh.getHoTen());
            ps.setString(3, kh.getDiaChi());
            ps.setString(4, kh.getSoDienThoai());
            ps.setString(5, kh.getEmail());
            ps.setString(6, kh.getGioiTinh());
            ps.setString(7, kh.getTrangThai());

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public boolean suaKhachHang(KhachHang kh, int ma) { 
        String sql = "UPDATE KhachHang SET HoTen=?, DiaChi=?, SoDienThoai=?, Email=?, GioiTinh=?, TrangThai=? WHERE ID=?";
        int check = 0;

        try ( Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, kh.getHoTen());
            ps.setString(2, kh.getDiaChi());
            ps.setString(3, kh.getSoDienThoai());
            ps.setString(4, kh.getEmail());
            ps.setString(5, kh.getGioiTinh());
            ps.setString(6, kh.getTrangThai());
            ps.setInt(7, ma); 

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public ArrayList<KhachHang> timKiemKHTen(String hoVaTen) {
        String sql = "SELECT ID, HoTen, DiaChi, SoDienThoai, Email, GioiTinh, TrangThai, NgayTao FROM KhachHang "
                + "WHERE lower(HoTen) COLLATE Latin1_General_CI_AI LIKE lower(?) COLLATE Latin1_General_CI_AI";
        try ( Connection cn = DBConnect.getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, "%" + hoVaTen + "%");
            ResultSet rs = ps.executeQuery();
            ArrayList<KhachHang> listKH = new ArrayList<>();
            while (rs.next()) {
                KhachHang khachHang = new KhachHang();
                khachHang.setID(rs.getInt(1));
                khachHang.setHoTen(rs.getString(2));
                khachHang.setDiaChi(rs.getString(3));
                khachHang.setSoDienThoai(rs.getString(4));
                khachHang.setEmail(rs.getString(5));
                khachHang.setGioiTinh(rs.getString(6));
                khachHang.setTrangThai(rs.getString(7));
                khachHang.setNgayTao(rs.getDate(8));
                listKH.add(khachHang);
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
