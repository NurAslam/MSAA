/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msaa.view.admin;

import com.msaa.view.admin.FormAdmin;
import model.koneksi;
import com.mysql.jdbc.Connection;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ABD, Qohar Agus Maulana (18650051)
 * @author Rizki Fitriani (18650053)
 * @author Annisa Rizkiana Putri (18650048)
 * @author Nisa Kholifatul Ummah (18650065)
 */
public class FormPendampingAdmin extends javax.swing.JFrame {

    Dimension layar = Toolkit.getDefaultToolkit().getScreenSize();
    private DefaultTableModel model;

    /**
     * Creates new form AdminPendamping
     */
    public FormPendampingAdmin() {
        initComponents();
        lokasi();
        model();
        getdata();
    setIcon();
        setTitle("MSAA Application");
    }
  private void setIcon() {
   
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("rsz_picture1.png"))); }
    protected void lokasi() {
        int x = layar.width / 2 - this.getSize().width / 2;
        int y = layar.height / 2 - this.getSize().height / 2;
        setLocation(x, y);
        awal();

    }

    public void awal() {
        try {
            cm_devisi.removeAllItems();
            cm_mabna.removeAllItems();
            Connection conn = (Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            Statement stmt1 = conn.createStatement();
            ResultSet mabna = stmt.executeQuery("select nama_mab from mabna");
            ResultSet devisi = stmt1.executeQuery("select nama_devisi from devisi");

            while (mabna.next()) {
                String obj = mabna.getString("nama_mab");
                cm_mabna.addItem(obj);

            }
            while (devisi.next()) {
                String obj = devisi.getString("nama_devisi");
                cm_devisi.addItem(obj);

            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public String ambilkodemabna(String namamabna) {
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                    "select kode_mab from mabna where nama_mab = ?");

            statement.setString(1, namamabna);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getString("kode_mab");
            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public String ambiliddevisi(String namadev) {
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                    "select id_devisi from devisi where nama_devisi = ?");

            statement.setString(1, namadev);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getString("id_devisi");
            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public void model() {

        model = new DefaultTableModel();
        tabel_pendamping.setModel(model);
        model.addColumn("user ID");
        model.addColumn("Nama");
        model.addColumn("Mabna");
        model.addColumn("Devisi");
        model.addColumn("Password");

//        getdata();
    }

    public void getdata() {
        try {
            Connection conn = (Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet data = stmt.executeQuery("select P.userid, P.nama, M.nama_mab, D.nama_devisi, P.password from acount_musyrifah P, mabna M , devisi D where P.kode_mab = M.kode_mab and D.id_devisi = P.id_devisi");

            while (data.next()) {
                Object[] obj = new Object[5];
                obj[0] = data.getString("P.userid");
                obj[1] = data.getString("P.nama");
                obj[2] = data.getString("M.nama_mab");
                obj[3] = data.getString("D.nama_devisi");
                obj[4] = data.getString("P.password");
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        userid_txt = new javax.swing.JTextField();
        nama_txt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pendamping = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        cm_mabna = new javax.swing.JComboBox<>();
        cm_devisi = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        pass_txt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Data Pendamping");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(506, 18, -1, -1));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel3.setText("User ID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, -1, -1));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel4.setText("Nama");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel5.setText("Mabna");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, -1));

        userid_txt.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        userid_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userid_txtActionPerformed(evt);
            }
        });
        getContentPane().add(userid_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 85, 293, -1));

        nama_txt.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        getContentPane().add(nama_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 122, 293, -1));

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel6.setText("Devisi");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 210, -1, -1));

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jLabel9.setText("Password");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, -1, -1));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton2.setText("Cari");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(753, 82, 140, -1));

        tabel_pendamping.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "NIM", "Nama", "Mabna", "Kamar", "Fakultas", "Jurusan", "Password"
            }
        ));
        tabel_pendamping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pendampingMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pendamping);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 890, 200));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(753, 162, 144, -1));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton4.setText("Hapus");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(753, 199, 144, -1));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton5.setText("Refresh");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(753, 242, 144, -1));

        cm_mabna.setBackground(new java.awt.Color(255, 255, 255));
        cm_mabna.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        cm_mabna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cm_mabnaActionPerformed(evt);
            }
        });
        getContentPane().add(cm_mabna, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 162, 200, -1));

        cm_devisi.setBackground(new java.awt.Color(255, 255, 255));
        cm_devisi.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        getContentPane().add(cm_devisi, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 199, 100, -1));

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton6.setText("Kembali");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(753, 279, 144, -1));

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        jButton7.setText("Simpan");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(753, 119, 144, -1));

        pass_txt.setFont(new java.awt.Font("Trebuchet MS", 1, 12)); // NOI18N
        getContentPane().add(pass_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(442, 245, 293, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/9pendamping.jpg"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userid_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userid_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userid_txtActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            model();
            Connection conn = (Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet data = stmt.executeQuery("select P.userid, P.nama, M.nama_mab, D.nama_devisi, P.password from acount_musyrifah P natural join mabna M natural join devisi D where P.kode_mab = M.kode_mab and D.id_devisi = P.id_devisi and P.userid like '%" + userid_txt.getText() + "%' and P.nama like '%" + nama_txt.getText() + "%' and M.nama_mab = '" + cm_mabna.getSelectedItem().toString() + "' and D.nama_devisi = '" + cm_devisi.getSelectedItem().toString() + "'");

            while (data.next()) {
                Object[] obj = new Object[5];
                obj[0] = data.getString("P.userid");
                obj[1] = data.getString("P.nama");
                obj[2] = data.getString("M.nama_mab");
                obj[3] = data.getString("D.nama_devisi");
                obj[4] = data.getString("P.password");
                model.addRow(obj);

            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        FormAdmin n = new FormAdmin();
        n.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        try {

            if (userid_txt.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, user ID belum diisi !");
                userid_txt.requestFocus();
            } else if (nama_txt.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Nama Mabna belum diisi !");
                nama_txt.requestFocus();
            } else if (pass_txt.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Password belum diisi !");
                pass_txt.requestFocus();
            } else {
                String userid = userid_txt.getText();
                String nama = nama_txt.getText();
                String mabna = cm_mabna.getSelectedItem().toString();
                String idmabna = ambilkodemabna(mabna);
                String devisi = cm_devisi.getSelectedItem().toString();
                String iddevisi = ambiliddevisi(devisi);
                String pass = pass_txt.getText();
                PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                        "insert into acount_musyrifah values (?,?,?,?,?)");

                statement.setString(1, userid);
                statement.setString(2, nama);
                statement.setString(3, idmabna);
                statement.setString(4, iddevisi);
                statement.setString(5, pass);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Baru telah di tambahkan");
                model();
                model();
                userid_txt.setText(null);
                nama_txt.setText(null);
                pass_txt.setText(null);
                awal();
                getdata();
//                komponen("segarkan");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "gagal menambah Data");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                    "update acount_musyrifah set nama = ?, kode_mab = ? ,id_devisi = ? ,password = ? where userid = ?");

            statement.setString(1, nama_txt.getText());
            statement.setString(2, ambilkodemabna(cm_mabna.getSelectedItem().toString()));
            statement.setString(3, ambiliddevisi(cm_devisi.getSelectedItem().toString()));
            statement.setString(4, pass_txt.getText());
            statement.setString(5, userid_txt.getText());
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil di update");
            model();
            getdata();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void cm_mabnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cm_mabnaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cm_mabnaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                    "delete from acount_musyrifah where userid = ?");

            statement.setString(1, userid_txt.getText());
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil di hapus");
            model();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tabel_pendampingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pendampingMouseClicked
        // TODO add your handling code here:
        try {
            int row = tabel_pendamping.rowAtPoint(evt.getPoint());
            if (row > -1) {
                String kode = tabel_pendamping.getValueAt(row, 0).toString();
                String nama = tabel_pendamping.getValueAt(row, 1).toString();
                String namamabna = tabel_pendamping.getValueAt(row, 2).toString();
                String devisi = tabel_pendamping.getValueAt(row, 3).toString();
                String password = tabel_pendamping.getValueAt(row, 4).toString();

                userid_txt.setText(kode);
                nama_txt.setText(nama);
                cm_mabna.setSelectedItem(namamabna);
                cm_devisi.setSelectedItem(devisi);
                pass_txt.setText(password);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_tabel_pendampingMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        model();
        userid_txt.setText(null);
        nama_txt.setText(null);
        pass_txt.setText(null);
        awal();
        getdata();
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(FormPendampingAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPendampingAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPendampingAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPendampingAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPendampingAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cm_devisi;
    private javax.swing.JComboBox<String> cm_mabna;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nama_txt;
    private javax.swing.JTextField pass_txt;
    private javax.swing.JTable tabel_pendamping;
    private javax.swing.JTextField userid_txt;
    // End of variables declaration//GEN-END:variables

}
