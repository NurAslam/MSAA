/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msaa.view.admin;

import com.mysql.jdbc.Connection;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.koneksi;

/**
 *
 * @author qoheng
 */
public class FromUniversitas extends javax.swing.JFrame {

    private DefaultTableModel model;
    private ComboBoxModel combo;

    /**
     * Creates new form FromUniversitas
     */
    public FromUniversitas() {
        initComponents();
        setLocationRelativeTo(null);
        awal();
    }

    public void awal() {

        try {
            cm_namafak.removeAllItems();
            Connection conn = (Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet fakultas = stmt.executeQuery("select nama_fak from fakultas");

            while (fakultas.next()) {
                String obj = fakultas.getString("nama_fak");
                cm_namafak.addItem(obj);

            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        modelfakultas();
        modeljurusan();
        txt_kodefak.setText(null);
        txt_namafak.setText(null);
        txt_kodejur.setText(null);
        txt_namajur.setText(null);
    }

    public String ambilkodefak(String namamafak) {
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                    "select kode_fak from fakultas where nama_fak = ?");

            statement.setString(1, namamafak);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getString("kode_fak");
            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public void modelfakultas() {

        model = new DefaultTableModel();
        tabel_univ.setModel(model);
        model.addColumn("kode Fakultas");
        model.addColumn("Nama Fakultas");

        getdatafakultas();
    }

    public void getdatafakultas() {
        try {
            Connection conn = (Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet data = stmt.executeQuery("select* from fakultas");

            while (data.next()) {
                Object[] obj = new Object[2];
                obj[0] = data.getString("kode_fak");
                obj[1] = data.getString("nama_fak");

                model.addRow(obj);

            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void modeljurusan() {

        model = new DefaultTableModel();
        tabel_jurusan.setModel(model);
        model.addColumn("kode Jurusan");
        model.addColumn("Nama Fakultas");
        model.addColumn("Nama Jurusan");

        getdatajurusan();
    }

    public void getdatajurusan() {
        try {
            Connection conn = (Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet data = stmt.executeQuery("select J.kode_jur, J.nama_jur, F.nama_fak from jurusan J natural join fakultas F where J.kode_fak = F.kode_fak");

            while (data.next()) {
                Object[] obj = new Object[3];
                obj[0] = data.getString("J.kode_jur");
                obj[1] = data.getString("F.nama_fak");
                obj[2] = data.getString("J.nama_jur");

                model.addRow(obj);

            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_kodefak = new javax.swing.JTextField();
        txt_namafak = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_kodejur = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_namajur = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_univ = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_jurusan = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cm_namafak = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Data Kampus");

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jLabel4.setText("id");

        jLabel5.setText("Nama");

        jLabel6.setText("id");

        jLabel7.setText("Jurusan");

        tabel_univ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_univ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_univMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_univ);

        tabel_jurusan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_jurusan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_jurusanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_jurusan);

        jLabel8.setText("Fakultas");

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Hapus");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Simpan");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Hapus");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Edit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Kembali");

        jButton8.setText("Segarkan");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel4))
                                            .addGap(35, 35, 35)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_kodefak)
                                                .addComponent(txt_namafak, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                                            .addGap(56, 56, 56)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel7)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jButton1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jButton2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton3)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton4)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton7)
                                        .addGap(191, 191, 191)
                                        .addComponent(jLabel1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton6))
                                    .addComponent(cm_namafak, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_kodejur)
                                    .addComponent(txt_namajur)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(139, 139, 139)
                                .addComponent(jButton8))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton7))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jButton8))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_kodefak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_kodejur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_namafak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cm_namafak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_namajur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton5)
                            .addComponent(jButton6))
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {

            if (txt_kodefak.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, kode Mabna belum diisi !");
                txt_kodefak.requestFocus();
            } else if (txt_namafak.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Nama Mabna belum diisi !");
                txt_namafak.requestFocus();
            } else {
                PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                        "insert into fakultas values (?,?)");

                statement.setString(1, txt_kodefak.getText());
                statement.setString(2, txt_namafak.getText());
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Baru telah di tambahkan");
                awal();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "gagal menambah Data");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {

            if (txt_kodejur.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, kode Mabna belum diisi !");
                txt_kodejur.requestFocus();
            } else if (txt_namajur.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Nama Mabna belum diisi !");
                txt_namajur.requestFocus();
            } else {
                PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                        "insert into jurusan values (?,?,?)");

                statement.setString(1, txt_kodejur.getText());
                statement.setString(2, txt_namajur.getText());
                statement.setString(3, ambilkodefak(cm_namafak.getSelectedItem().toString()));
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Baru telah di tambahkan");
                awal();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "gagal menambah Data");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabel_univMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_univMouseClicked
        // TODO add your handling code here:
        try {
            int row = tabel_univ.rowAtPoint(evt.getPoint());

            String kode = tabel_univ.getValueAt(row, 0).toString();
            String nama = tabel_univ.getValueAt(row, 1).toString();

            txt_kodefak.setText(String.valueOf(kode));
            txt_namafak.setText(String.valueOf(nama));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tabel_univMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan Mengubah dataini ?", "Warning", 2) == JOptionPane.YES_OPTION) {
            try {

                PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                        "update fakultas set nama_fak = ? where kode_fak = ?");

                statement.setString(1, txt_namafak.getText());
                statement.setString(2, txt_kodefak.getText());
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Telah Di Ubah");
                awal();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Data Gagal Di Ubah");

            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan Menhapus dataini ?", "Warning", 2) == JOptionPane.YES_OPTION) {
            try {

                PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                        "delete from fakultas where kode_fak = ?");

                statement.setString(1, txt_kodefak.getText());
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Telah Di Hapus");
                awal();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");

            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan Menhapus dataini ?", "Warning", 2) == JOptionPane.YES_OPTION) {
            try {

                PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                        "delete from jurusan where kode_jur = ?");

                statement.setString(1, txt_kodejur.getText());
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Telah Di Hapus");
                awal();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");

            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan Mengubah dataini ?", "Warning", 2) == JOptionPane.YES_OPTION) {
            try {

                PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                        "update jurusan set nama_jur =?, kode_fak =? where kode_jur = ?");

                statement.setString(1, txt_namajur.getText());
                statement.setString(2, ambilkodefak(cm_namafak.getSelectedItem().toString()));
                statement.setString(3, txt_kodejur.getText());
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Telah Di Ubah");
                awal();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Data Gagal Di Ubah");

            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tabel_jurusanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_jurusanMouseClicked
        // TODO add your handling code here:
         try {
            int row = tabel_jurusan.rowAtPoint(evt.getPoint());

            String kode = tabel_jurusan.getValueAt(row, 0).toString();
            String namafak = tabel_jurusan.getValueAt(row, 1).toString();
            String nama = tabel_jurusan.getValueAt(row, 2).toString();

            txt_kodejur.setText(String.valueOf(kode));
            cm_namafak.setSelectedItem(String.valueOf(namafak));
            txt_namajur.setText(String.valueOf(nama));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tabel_jurusanMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        awal();
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(FromUniversitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FromUniversitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FromUniversitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FromUniversitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FromUniversitas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cm_namafak;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabel_jurusan;
    private javax.swing.JTable tabel_univ;
    private javax.swing.JTextField txt_kodefak;
    private javax.swing.JTextField txt_kodejur;
    private javax.swing.JTextField txt_namafak;
    private javax.swing.JTextField txt_namajur;
    // End of variables declaration//GEN-END:variables
}
