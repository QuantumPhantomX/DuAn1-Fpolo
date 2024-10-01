/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import Model_KhachHang_NhanVien.KhachHang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import Repository_KhachHang_NhanVien.KhachHangService1;

/**
 *
 * @author RAVEN
 */
public class Khach_hang extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    private DefaultTableModel dtm = new DefaultTableModel();
    private KhachHangService1 ser = new KhachHangService1();
    private ArrayList<KhachHang> list = new ArrayList<>();

    public Khach_hang() {
        initComponents();
        list = ser.getAllSV();
        dtm = (DefaultTableModel) tblKhachHangTTCN.getModel();
        loadTable(list);
    }

    public void loadTable(ArrayList<KhachHang> list) {
        dtm.setRowCount(0);
        for (KhachHang kh : list) {
            String gioiTinh = kh.getGioiTinh().equals("Nam") ? "Nam" : "Nữ"; // Thay đổi isGioiTinh() thành getGioiTinh()
            String trangThai = kh.getTrangThai().equals("Còn hoạt động") ? "Còn hoạt động" : "Ngừng hoạt động"; // Thay đổi isTrangThai() thành getTrangThai()
            dtm.addRow(new Object[]{
                kh.getID(), // Thay đổi getMaKH() thành getID()
                kh.getHoTen(),
                kh.getDiaChi(),
                kh.getSoDienThoai(),
                kh.getEmail(),
                gioiTinh,
                trangThai,
                kh.getNgayTao()
            // Bỏ kh.getNgaySua() vì không có trong database
            });
        }
    }

    public void fillData(KhachHang kh) {
        txtMaKH.setText(String.valueOf(kh.getID())); // Thay đổi getMaKH() thành getID() và chuyển đổi sang String
        txtTenKH.setText(kh.getHoTen());
        txtDiaChi.setText(kh.getDiaChi());
        txtSoDienThoai.setText(kh.getSoDienThoai());
        txtEmail.setText(kh.getEmail());

        if (kh.getGioiTinh().equals("Nam")) { // Thay đổi isGioiTinh() thành getGioiTinh()
            rdoNam.setSelected(true);
            rdoNu.setSelected(false);
        } else {
            rdoNam.setSelected(false);
            rdoNu.setSelected(true);
        }

        if (kh.getTrangThai().equals("Còn hoạt động")) { // Thay đổi isTrangThai() thành getTrangThai()
            rdoHoatDong.setSelected(true);
            rdoKhongHoatDong.setSelected(false);
        } else {
            rdoHoatDong.setSelected(false);
            rdoKhongHoatDong.setSelected(true);
        }
    }

    public KhachHang VaLiDaTe() {
        String maKH = txtMaKH.getText();

        // Kiểm tra xem maKH có phải là số nguyên hay không
        try {
            Integer.parseInt(maKH); // Chuyển đổi maKH thành số nguyên
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã khách hàng phải là số nguyên!");
            return null;
        }

        String hoTen = txtTenKH.getText();
        String diaChi = txtDiaChi.getText();
        String soDienThoai = txtSoDienThoai.getText();
        String email = txtEmail.getText();

        String gioiTinh; // Thay đổi kiểu dữ liệu thành String
        if (rdoNam.isSelected()) {
            gioiTinh = "Nam";
        } else if (rdoNu.isSelected()) {
            gioiTinh = "Nữ";
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giới tính!!");
            return null;
        }

        String trangThai = "Còn hoạt động"; // Thay đổi kiểu dữ liệu thành String

        if (!soDienThoai.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải >=10 & <= 11!");
            return null;
        }

        if (!email.matches("[a-zA-Z]+@[a-zA-Z]+\\.com")) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ!");
            return null;
        }

        if (hoTen.isEmpty() || diaChi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liệu!");
            return null;
        }

        int idKH = Integer.parseInt(maKH); // Chuyển đổi maKH thành số nguyên
        KhachHang kh = new KhachHang(idKH, hoTen, diaChi, soDienThoai, email, gioiTinh, trangThai, null); // Thay đổi kiểu dữ liệu của maKH thành int
        return kh;
    }

    private boolean isMaKHExist(String maKH) {
        for (KhachHang kh : list) {
            if (String.valueOf(kh.getID()).equals(maKH)) { // Thay đổi getMaKH() thành getID() và chuyển đổi sang String
                JOptionPane.showMessageDialog(this, "Mã khách hàng đã tồn tại. Vui lòng chọn mã khác.");
                return true;
            }
        }
        return false;
    }

    public void clearForm() {
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtDiaChi.setText("");
        txtSoDienThoai.setText("");
        txtEmail.setText("");
        rdoNam.setSelected(false);
        rdoNu.setSelected(false);
        rdoHoatDong.setSelected(false);
        rdoKhongHoatDong.setSelected(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKachHang = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLichSu = new javax.swing.JTable();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblKhachHangTTCN = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        txtTimKiem2 = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblKhachHangLSHD = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        rdoKhongHoatDong = new javax.swing.JRadioButton();
        rdoHoatDong = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        tbnLamMoi = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();

        tblKachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Stt", "Id", "Họ Tên", "Số Điện Thoại", "Giới Tính", "Email", "Địa Chỉ"
            }
        ));
        tblKachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKachHang);

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        jLabel11.setText("Tim Kiếm");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(607, 607, 607)
                .addComponent(jLabel11)
                .addGap(26, 26, 26)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1068, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Danh Sách Khách Hàng", jPanel5);

        tblLichSu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Ngày Tạo", "Tên Nhân Viên", "tên Khách Hàng", "Tên Voucher", "Tổng Tiền", "Hình Thức Thanh Toán", "Trạng Thái"
            }
        ));
        jScrollPane2.setViewportView(tblLichSu);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Lịch Sử Giao Dịch", jPanel6);

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ KHÁCH HÀNG");

        jTabbedPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jTabbedPane5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        tblKhachHangTTCN.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblKhachHangTTCN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "Địa chỉ", "SDT", "Email", "Giới tính", "Trạng thái", "Ngày tạo"
            }
        ));
        tblKhachHangTTCN.setGridColor(new java.awt.Color(0, 0, 0));
        tblKhachHangTTCN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangTTCNMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblKhachHangTTCN);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        txtTimKiem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiem2ActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Cambria", 0, 14)); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnTimKiem)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addComponent(txtTimKiem2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(554, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Danh sách khách hàng", jPanel10);

        tblKhachHangLSHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Tên KH", "SDT", "Ngày mua", "Tên sản phẩm", "Số lượng", "Giá", "Tổng tiền", "Trạng thái giao dịch"
            }
        ));
        tblKhachHangLSHD.setGridColor(new java.awt.Color(0, 0, 0));
        tblKhachHangLSHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangLSHDMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblKhachHangLSHD);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Lịch sử giao dịch", jPanel3);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Mã KH:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Tên KH:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rdoNu.setText("Nữ");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Số điện thoại:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Địa chỉ:");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Email");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setText("Trạng Thái");

        buttonGroup2.add(rdoKhongHoatDong);
        rdoKhongHoatDong.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rdoKhongHoatDong.setText("Không hoạt động");

        buttonGroup2.add(rdoHoatDong);
        rdoHoatDong.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rdoHoatDong.setText("Đang hoạt động");
        rdoHoatDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHoatDongActionPerformed(evt);
            }
        });

        tbnLamMoi.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        tbnLamMoi.setText("Làm mới");
        tbnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tbnLamMoiActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Cambria", 0, 18)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tbnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addGap(20, 20, 20)
                .addComponent(btnSua)
                .addGap(20, 20, 20)
                .addComponent(tbnLamMoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(82, 82, 82)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel4)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoKhongHoatDong)
                            .addComponent(rdoNam))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoNu)
                            .addComponent(rdoHoatDong)))
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13)
                            .addComponent(rdoKhongHoatDong)
                            .addComponent(rdoHoatDong)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(463, 463, 463)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblKachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKachHangMouseClicked

    }//GEN-LAST:event_tblKachHangMouseClicked

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed

    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased

    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void tblKhachHangTTCNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangTTCNMouseClicked
        int row = tblKhachHangTTCN.getSelectedRow();
        KhachHang kh = list.get(row);
        fillData(kh);
    }//GEN-LAST:event_tblKhachHangTTCNMouseClicked

    private void txtTimKiem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiem2ActionPerformed
        btnTimKiemActionPerformed(evt);
    }//GEN-LAST:event_txtTimKiem2ActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String hoVaTen = txtTimKiem2.getText();
        if (hoVaTen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập họ và tên để tìm kiếm!");
        } else {
            // Thực hiện tìm kiếm
            ArrayList<KhachHang> resultList = ser.timKiemKHTen(hoVaTen);

            // Hiển thị kết quả lên tblKhachHangTTCN
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

    private void tblKhachHangLSHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangLSHDMouseClicked

    }//GEN-LAST:event_tblKhachHangLSHDMouseClicked

    private void rdoHoatDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHoatDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoHoatDongActionPerformed

    private void tbnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tbnLamMoiActionPerformed
        clearForm();
        loadTable(list);
    }//GEN-LAST:event_tbnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int row = tblKhachHangTTCN.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng để sửa");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?");
        if (check != JOptionPane.YES_OPTION) {
            return;
        }
        int ma = list.get(row).getID(); // Lấy ID thay vì MaKH
        KhachHang sv = VaLiDaTe();
        if (sv == null) {
            return;
        }
        ser.suaKhachHang(sv, ma); // Truyền ID vào phương thức suaKhachHang
        list = ser.getAllSV();
        JOptionPane.showMessageDialog(this, "Sửa thành công !!");
        loadTable(list);
        clearForm();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        KhachHang kh = VaLiDaTe();
        if (kh == null) {
            return;
        }

        // Kiểm tra mã khách hàng tồn tại hay chưa
        if (isMaKHExist(String.valueOf(kh.getID()))) { // Chuyển đổi ID sang String để so sánh
            return;
        }

        // Hiển thị hộp thoại xác nhận trước khi thêm
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm khách hàng này?", "Xác nhận thêm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            // Thực hiện thêm khách hàng
            ser.themKhachHang(kh);
            list = ser.getAllSV();
            JOptionPane.showMessageDialog(this, "Thêm thành công !");
            loadTable(list);
            clearForm();
        }
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JRadioButton rdoHoatDong;
    private javax.swing.JRadioButton rdoKhongHoatDong;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKachHang;
    private javax.swing.JTable tblKhachHangLSHD;
    private javax.swing.JTable tblKhachHangTTCN;
    private javax.swing.JTable tblLichSu;
    private javax.swing.JButton tbnLamMoi;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiem2;
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
