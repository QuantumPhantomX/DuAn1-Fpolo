/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import Model_KhachHang_NhanVien.NhanVien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import Repository_KhachHang_NhanVien.NhanVienService;

/**
 *
 * @author RAVEN
 */
public class Nhan_Vien extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    private DefaultComboBoxModel<String> cbb = new DefaultComboBoxModel<>();
    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtmHD = new DefaultTableModel();
    private DefaultTableModel dtmNV = new DefaultTableModel();

    private NhanVienService ser = new NhanVienService();

    private ArrayList<NhanVien> list = new ArrayList<>();
    private ArrayList<NhanVien> listNV = new ArrayList<>();

    public Nhan_Vien() {
        initComponents();
        list = ser.getAllNV();
        dtm = (DefaultTableModel) tblNhanVien.getModel();
        loadTable(list);
    }

    public void loadTable(ArrayList<NhanVien> list) {
        dtm.setRowCount(0);
        for (NhanVien nv : list) {
            String gioiTinh = nv.getGioiTinh().equals("Nam") ? "Nam" : "Nữ"; // Sửa isGioiTinh()
            String trangThai = nv.getTrangThai().equals("Làm việc") ? "Làm việc" : "Nghỉ việc"; // Sửa isTrangThai()
            String chucVu = nv.getChucVu().equals("Quản lý") ? "Quản lý" : "Nhân viên"; // Sửa isTrangThai()

            dtm.addRow(new Object[]{
                nv.getID(), // Sửa getID_MaNV()
                nv.getHoTen(), // Sửa getTenNV()
                nv.getDiaChi(),
                nv.getSoDienThoai(), // Sửa getSDT()
                nv.getEmail(),
                nv.getNamSinh(),
                gioiTinh,
                chucVu,
                nv.getMatKhau(),
                trangThai
            // Bỏ getNgayTao() và getNgaySua()
            });
        }
        dtm.fireTableDataChanged();
        tblNhanVien.revalidate();
        tblNhanVien.repaint();
    }

    public void fillData(NhanVien nv) {
        txtMaNV.setText(String.valueOf(nv.getID())); // Sửa getID_MaNV() và chuyển sang String
        txtTenNV.setText(nv.getHoTen()); // Sửa getTenNV()
        txtDiaChi1.setText(nv.getDiaChi());
        txtSDT1.setText(nv.getSoDienThoai()); // Sửa getSDT()
        txtEmail1.setText(nv.getEmail());
        txtNgaySinh1.setText(new SimpleDateFormat("yyyy-MM-dd").format(nv.getNamSinh())); // Định dạng ngày sinh
        txtPassWord1.setText(nv.getMatKhau());

        // Chức vụ
        if (nv.getChucVu().equals("Quản lý")) { // Sửa isChucVu()
            rdoQuanly1.setSelected(true);
            rdoNhanVien1.setSelected(false);
        } else {
            rdoQuanly1.setSelected(false);
            rdoNhanVien1.setSelected(true);
        }

        // Giới tính
        if (nv.getGioiTinh().equals("Nam")) { // Sửa isGioiTinh()
            rdoNam1.setSelected(true);
            rdoNu1.setSelected(false);
        } else {
            rdoNam1.setSelected(false);
            rdoNu1.setSelected(true);
        }

        // Trạng thái
        if (nv.getTrangThai().equals("Làm việc")) { // Sửa isTrangThai()
            rdoLamViec1.setSelected(true);
            rdoNghiViec1.setSelected(false);
        } else {
            rdoLamViec1.setSelected(false);
            rdoNghiViec1.setSelected(true);
        }
    }

    public void clearForm() {
        txtMaNV.setText("");
        txtTenNV.setText("");
        txtPassWord1.setText("");
        txtDiaChi1.setText("");
        txtSDT1.setText("");
        txtEmail1.setText("");
        txtNgaySinh1.setText("");
        rdoNam1.setSelected(false);
        rdoNu1.setSelected(false);
        rdoQuanly1.setSelected(false);
        rdoNhanVien1.setSelected(false);
        rdoLamViec1.setSelected(false);
        rdoNghiViec1.setSelected(false);
    }

    public NhanVien validateData() {
        String maNV = txtMaNV.getText();

        // Kiểm tra xem maNV có phải là số nguyên hay không
        try {
            Integer.parseInt(maNV);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên phải là số nguyên!");
            return null;
        }

        String tenNV = txtTenNV.getText();
        String diaChi = txtDiaChi1.getText();
        String sdt = txtSDT1.getText();
        String email = txtEmail1.getText();
        String ngaySinhStr = txtNgaySinh1.getText();
        String matKhau = txtPassWord1.getText();

        // Kiểm tra chức vụ 
        String chucVu; // Thay đổi kiểu dữ liệu sang String
        if (rdoQuanly1.isSelected()) {
            chucVu = "Quản lý";
        } else if (rdoNhanVien1.isSelected()) {
            chucVu = "Nhân viên";
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn chức vụ!");
            return null;
        }

        // Kiểm tra giới tính
        String gioiTinh; // Thay đổi kiểu dữ liệu sang String
        if (rdoNam1.isSelected()) {
            gioiTinh = "Nam";
        } else if (rdoNu1.isSelected()) {
            gioiTinh = "Nữ";
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính!");
            return null;
        }

        // Kiểm tra trạng thái
        String trangThai; // Thay đổi kiểu dữ liệu sang String
        if (rdoLamViec1.isSelected()) {
            trangThai = "Làm việc";
        } else if (rdoNghiViec1.isSelected()) {
            trangThai = "Nghỉ việc";
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái!");
            return null;
        }

        // Kiểm tra ngày sinh
        Date ngaySinh;
        try {
            ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinhStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd");
            return null;
        }

        // Kiểm tra số điện thoại
        if (!sdt.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 hoặc 11 chữ số!");
            return null;
        }

        // Kiểm tra email
        if (!email.matches("[a-zA-Z]+@[a-zA-Z]+\\.com")) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
            return null;
        }

        // Kiểm tra mật khẩu
        if (matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống!");
            return null;
        }

        int idNV = Integer.parseInt(maNV);
        NhanVien nv = new NhanVien(idNV, tenNV, diaChi, sdt, email, ngaySinh, gioiTinh, chucVu, matKhau, trangThai);
        return nv;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        rdoNam1 = new javax.swing.JRadioButton();
        rdoNu1 = new javax.swing.JRadioButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtSDT1 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtPassWord1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtDiaChi1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        rdoQuanly1 = new javax.swing.JRadioButton();
        rdoNhanVien1 = new javax.swing.JRadioButton();
        txtNgaySinh1 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtEmail1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        rdoNghiViec1 = new javax.swing.JRadioButton();
        rdoLamViec1 = new javax.swing.JRadioButton();
        jPanel20 = new javax.swing.JPanel();
        tbnLamMoi1 = new javax.swing.JButton();
        btnSua1 = new javax.swing.JButton();
        btnThem1 = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblNhanVienHD = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblNhanVienNV = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jPanel18.setPreferredSize(new java.awt.Dimension(1145, 683));

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Mã NV");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setText("Tên NV");

        txtTenNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenNVKeyReleased(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setText("Giới tính");

        buttonGroup3.add(rdoNam1);
        rdoNam1.setText("Nam");

        buttonGroup3.add(rdoNu1);
        rdoNu1.setSelected(true);
        rdoNu1.setText("Nữ");

        jLabel29.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel29.setText("Ngày sinh");

        jLabel30.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel30.setText("SĐT");

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setText("Địa chỉ");

        jLabel32.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel32.setText("Mật khẩu");

        jLabel33.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel33.setText("Chức vụ");

        buttonGroup1.add(rdoQuanly1);
        rdoQuanly1.setText("Quản lý");

        buttonGroup1.add(rdoNhanVien1);
        rdoNhanVien1.setSelected(true);
        rdoNhanVien1.setText("Nhân viên");

        jLabel34.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel34.setText("Email");

        jLabel35.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel35.setText("Trạng Thái");

        buttonGroup2.add(rdoNghiViec1);
        rdoNghiViec1.setText("Nghỉ việc");

        buttonGroup2.add(rdoLamViec1);
        rdoLamViec1.setSelected(true);
        rdoLamViec1.setText("Làm việc");
        rdoLamViec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoLamViec1ActionPerformed(evt);
            }
        });

        tbnLamMoi1.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tbnLamMoi1.setText("Làm mới");
        tbnLamMoi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnLamMoi1ActionPerformed(evt);
            }
        });

        btnSua1.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnSua1.setText("Sửa");
        btnSua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua1ActionPerformed(evt);
            }
        });

        btnThem1.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnThem1.setText("Thêm");
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbnLamMoi1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSua1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tbnLamMoi1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(36, 36, 36)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassWord1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(26, 26, 26)
                        .addComponent(rdoQuanly1)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNhanVien1)))
                .addGap(48, 48, 48)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgaySinh1)
                            .addComponent(txtSDT1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                            .addComponent(txtEmail1)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoNghiViec1)
                                    .addComponent(rdoNu1)))))
                    .addComponent(jLabel34)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoNam1)
                            .addComponent(rdoLamViec1))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel31)
                                .addComponent(txtDiaChi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29)
                                    .addComponent(txtNgaySinh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34)
                                    .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPassWord1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel32)
                                        .addComponent(txtSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel30))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel35)
                                    .addComponent(rdoLamViec1)
                                    .addComponent(rdoNghiViec1))))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoQuanly1)
                            .addComponent(rdoNhanVien1)
                            .addComponent(jLabel33)
                            .addComponent(jLabel28)
                            .addComponent(rdoNam1)
                            .addComponent(rdoNu1))))
                .addGap(10, 10, 10))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnTimKiem)
                .addGap(21, 21, 21))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jTabbedPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên ", "Địa chỉ", "SDT", "Email", "Ngày sinh", "Giới tính", "Chức vụ", "Mật khẩu", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setGridColor(new java.awt.Color(0, 0, 0));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tblNhanVien);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
        );

        jTabbedPane6.addTab("Danh sách nhân viên", jPanel1);

        tblNhanVienHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Tên ", "Địa chỉ", "SĐT", "Email", "Ngày sinh", "Giới tính", "Chức vụ", "Mật khẩu", "Trạng thái"
            }
        ));
        tblNhanVienHD.setGridColor(new java.awt.Color(0, 0, 0));
        tblNhanVienHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienHDMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblNhanVienHD);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane6.addTab("Đang làm việc", jPanel16);

        tblNhanVienNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV ", "Tên ", "Đia chỉ", "SDT", "Email", "Ngày sinh", "Giới tính", "Chức vụ", "Mật khẩu", "Trạng thái"
            }
        ));
        tblNhanVienNV.setGridColor(new java.awt.Color(0, 0, 0));
        tblNhanVienNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienNVMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblNhanVienNV);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
        );

        jTabbedPane6.addTab("Nghỉ việc", jPanel17);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 1042, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane6))
                .addGap(102, 102, 102))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(452, 452, 452)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 1075, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenNVKeyReleased

    }//GEN-LAST:event_txtTenNVKeyReleased

    private void rdoLamViec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoLamViec1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoLamViec1ActionPerformed

    private void tbnLamMoi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnLamMoi1ActionPerformed
        clearForm();
        loadTable(list);
    }//GEN-LAST:event_tbnLamMoi1ActionPerformed

    private void btnSua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua1ActionPerformed
        int row = tblNhanVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng để sửa");
            return;
        }

        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?");
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        int ma = list.get(row).getID(); // Lấy ID (kiểu int)
        NhanVien nv = validateData();
        if (nv == null) {
            return;
        }
        boolean success = ser.suaNhanVien(nv, ma); // Truyền ID (kiểu int) vào phương thức suaNhanVien
        if (success) {
            list = ser.getAllNV();
            JOptionPane.showMessageDialog(this, "Sửa thành công!");
            loadTable(list);
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi sửa nhân viên. Vui lòng thử lại.");
        }
    }//GEN-LAST:event_btnSua1ActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        NhanVien nv = validateData();
        if (nv == null) {
            return;
        }

        // Kiểm tra mã nhân viên tồn tại hay chưa
        if (isMaNVExist(nv.getID())) { // Truyền ID (kiểu int) vào phương thức isMaNVExist
            return;
        }

        // Hiển thị hộp thoại xác nhận trước khi thêm
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm nhân viên này?", "Xác nhận thêm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // Thực hiện thêm nhân viên
            boolean success = ser.themNhanVien(nv);
            if (success) {
                list = ser.getAllNV();
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                loadTable(list);
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi thêm nhân viên. Vui lòng thử lại.");
            }
        }
    }//GEN-LAST:event_btnThem1ActionPerformed
    private boolean isMaNVExist(int maNV) { // Thay đổi kiểu dữ liệu tham số thành int
        for (NhanVien existingNV : list) {
            if (existingNV.getID() == maNV) { // So sánh ID (kiểu int)
                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại. Vui lòng chọn mã khác.");
                return true;
            }
        }
        return false;
    }
    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int selectedRow = tblNhanVien.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để xóa!");
            return;
        }

        int maNV = (int) tblNhanVien.getValueAt(selectedRow, 0); // Lấy ID (kiểu int)
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa nhân viên có mã " + maNV + " không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            boolean success = ser.xoaNhanVien(maNV); // Truyền ID (kiểu int) vào phương thức xoaNhanVien

            if (success) {
                list = ser.getAllNV();
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                loadTable(list);

            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi xóa nhân viên. Vui lòng thử lại.");
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        btnTimKiemActionPerformed(evt);
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String tenNV = txtTimKiem.getText();
        if (tenNV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên để tìm kiếm!");
        } else {
            // Thực hiện tìm kiếm
            ArrayList<NhanVien> resultList = ser.timKiemTheoTenNV(tenNV);

            // Hiển thị kết quả lên tblNhanVienLV
            if (resultList != null && !resultList.isEmpty()) {
                loadTable(resultList);
                showTimedMessage("Đã tìm thấy kết quả!", 1500); // 2000 milliseconds (2 seconds)
            } else {
                showTimedMessage("Không tìm thấy kết quả!", 1500);
            }
            clearForm();
            txtTimKiem.setText("");
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        int row = tblNhanVien.getSelectedRow();
        NhanVien nv = list.get(row);
        fillData(nv);
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void tblNhanVienHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienHDMouseClicked
        int row = tblNhanVienHD.getSelectedRow();
        NhanVien nv = list.get(row);
        fillData(nv);
    }//GEN-LAST:event_tblNhanVienHDMouseClicked

    private void tblNhanVienNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienNVMouseClicked
        int row = tblNhanVienNV.getSelectedRow();
        NhanVien nv = list.get(row);
        fillData(nv);
    }//GEN-LAST:event_tblNhanVienNVMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua1;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JRadioButton rdoLamViec1;
    private javax.swing.JRadioButton rdoNam1;
    private javax.swing.JRadioButton rdoNghiViec1;
    private javax.swing.JRadioButton rdoNhanVien1;
    private javax.swing.JRadioButton rdoNu1;
    private javax.swing.JRadioButton rdoQuanly1;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblNhanVienHD;
    private javax.swing.JTable tblNhanVienNV;
    private javax.swing.JButton tbnLamMoi1;
    private javax.swing.JTextField txtDiaChi1;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgaySinh1;
    private javax.swing.JTextField txtPassWord1;
    private javax.swing.JTextField txtSDT1;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    private void showTimedMessage(String message, int duration) {
        JOptionPane pane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(null, "Thông báo");

        Timer timer = new Timer(duration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false); // Đảm bảo chỉ chạy một lần
        timer.start();

        dialog.setVisible(true);
    }
}
