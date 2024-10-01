/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.main;

import java.sql.*;
import com.raven.connect.DBConnect;
import javax.swing.JOptionPane;

public class ChucVuLogin extends javax.swing.JFrame {
    
    DBConnect cn = new DBConnect();
    Connection conn;
    
    private String email, matKhau, chucVu;

    public ChucVuLogin() {
        initComponents();
    }
    
    public ChucVuLogin(String email,String matKhau, String chucVu){
        initComponents();
        this.email = email;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
        
        conn = cn.getConnection();
        
        
        try {
            String sql_chucVu = "Select * from NhanVien where ChucVu = ?";
            PreparedStatement pst = conn.prepareStatement(sql_chucVu);
            pst.setString(1, chucVu);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                BLEmail.setText("Email");
                BLMatKhau.setText("MatKhau");
                BLChucVu.setText(rs.getString("ChucVu"));
                
            }
        } catch (Exception e) {
        }
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BLEmail = new javax.swing.JLabel();
        BLMatKhau = new javax.swing.JLabel();
        BLChucVu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BLEmail.setText("Email");

        BLMatKhau.setText("Mật Khẩu");

        BLChucVu.setText("Chức Vụ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BLEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BLMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                    .addComponent(BLChucVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(BLEmail)
                .addGap(32, 32, 32)
                .addComponent(BLMatKhau)
                .addGap(40, 40, 40)
                .addComponent(BLChucVu)
                .addContainerGap(138, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChucVuLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChucVuLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChucVuLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChucVuLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChucVuLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BLChucVu;
    private javax.swing.JLabel BLEmail;
    private javax.swing.JLabel BLMatKhau;
    // End of variables declaration//GEN-END:variables
}
