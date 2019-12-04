/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.msaa.view.admin;

import com.mysql.jdbc.Connection;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.koneksi;

/**
 *
 * @author ABD, Qohar Agus Maulana (18650051)
 * @author Rizki Fitriani (18650053)
 * @author Annisa Rizkiana Putri (18650048)
 * @author Nisa Kholifatul Ummah (18650065)
 */
public class FormMahasantriAdmin extends javax.swing.JFrame {

    private DefaultTableModel model;
    private String pathfile;
    private JTable table;

    /**
     * Creates new form AdminMahasantri
     */
    public FormMahasantriAdmin() {
        initComponents();
        setLocationRelativeTo(null);
        awal();
        btn_Importfile.setEnabled(false);
    }

    public void awal() {
        try {
            cm_mabna.removeAllItems();
            cm_jurusan.removeAllItems();
            Connection conn = (Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            Statement stmt1 = conn.createStatement();
            ResultSet mabna = stmt.executeQuery("select nama_mab from mabna");
            ResultSet jurusan = stmt1.executeQuery("select nama_jur from jurusan");

            while (mabna.next()) {
                String obj = mabna.getString("nama_mab");
                cm_mabna.addItem(obj);

            }
            while (jurusan.next()) {
                String obj = jurusan.getString("nama_jur");
                cm_jurusan.addItem(obj);

            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        model();
        getdata();
        txt_nim.setText(null);
        txt_nama.setText(null);
        txt_lantai.setText(null);
        txt_kamar.setText(null);
    }

    public String ambilkodejur(String namamajur) {
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                    "select kode_jur from jurusan where nama_jur = ?");

            statement.setString(1, namamajur);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getString("kode_jur");
            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public String ambilkodemabna(String namamamabna) {
        try {
            PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                    "select kode_mab from mabna where nama_mab = ?");

            statement.setString(1, namamamabna);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getString("kode_mab");
            }

        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public static void saveCSV(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        JFileChooser chooser = new JFileChooser();
        int state = chooser.showSaveDialog(null);
        File file = chooser.getSelectedFile();
        if (file != null && state == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
                PrintWriter fileWriter = new PrintWriter(bufferedWriter);
                bufferedWriter.write("NIM, Nama, Fakultas, Jurusan, Mabna, Kamar, Lantai \r\n");
                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object o = model.getValueAt(i, j);
                        String s = String.valueOf(o);
//                        System.out.print(s);
                        bufferedWriter.write(s);

                        if (j < model.getColumnCount() - 1) {
                            bufferedWriter.write(",");
                        } else {
                            bufferedWriter.write("\r\n");
                        }
                    }
                }
                
                fileWriter.close();
                JOptionPane.showMessageDialog(null, "Success");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Failure");
            }
        }
    }

    public void model() {

        model = new DefaultTableModel();
        tabel_mahasantri.setModel(model);
        model.addColumn("NIM");
        model.addColumn("Nama");
        model.addColumn("Fakultas");
        model.addColumn("Jurusan");
        model.addColumn("Mabna");
        model.addColumn("Kamar");
        model.addColumn("Lantai");

//        getdata();
    }

    public void getdata() {
        try {
            Connection conn = (Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet data = stmt.executeQuery("select M.nim_mahasantri, M.nama, F.nama_fak, J.nama_jur, J.kode_fak , N.nama_mab, M.kamar, M.lantai from mahasantri M , fakultas F, jurusan J, mabna N where M.kode_jur = J.kode_jur and F.kode_fak = J.kode_fak and M.kode_mab = N.kode_mab");

            while (data.next()) {
                Object[] obj = new Object[7];
                obj[0] = data.getString("M.nim_mahasantri");
                obj[1] = data.getString("M.nama");
                obj[2] = data.getString("F.nama_fak");
                obj[3] = data.getString("J.nama_jur");
                obj[4] = data.getString("N.nama_mab");
                obj[5] = data.getString("M.kamar");
                obj[6] = data.getString("M.lantai");

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

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nim = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_mahasantri = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cm_mabna = new javax.swing.JComboBox<>();
        cm_jurusan = new javax.swing.JComboBox<>();
        txt_nama = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txt_lantai = new javax.swing.JTextField();
        txt_kamar = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btn_Importfile = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setText("NAMA");

        jLabel5.setText("MABNA");

        txt_nim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nimActionPerformed(evt);
            }
        });

        jButton2.setText("Cari");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tabel_mahasantri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NIM", "Nama", "Mabna", "Kamar", "Lantai", "Devisi", "Fakultas", "Jurusan", "Password"
            }
        ));
        tabel_mahasantri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_mahasantriMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_mahasantri);

        jButton3.setText("Edit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("KAMAR");

        jButton4.setText("Hapus");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Refres");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel8.setText("JURUSAN");

        jLabel1.setText("Data Mahasantri");

        jLabel2.setText("Ma'had");

        jLabel3.setText("NIM");

        jLabel9.setText("Lantai");

        txt_nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_namaActionPerformed(evt);
            }
        });

        jButton1.setText("Kembali");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setText("Simpan");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Pilih File CSV");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btn_Importfile.setText("Import File");
        btn_Importfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImportfileActionPerformed(evt);
            }
        });

        jButton8.setText("Export to CSV");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(486, 486, 486))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(450, 450, 450))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(layout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(jLabel5))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cm_jurusan, 0, 293, Short.MAX_VALUE)
                            .addComponent(cm_mabna, 0, 293, Short.MAX_VALUE)
                            .addComponent(txt_nim, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(txt_nama, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                            .addComponent(txt_kamar, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(txt_lantai))))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(btn_Importfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(btn_Importfile))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cm_jurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cm_mabna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_lantai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_kamar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(81, 81, 81)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nimActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            model();
            Connection conn = (Connection) koneksi.koneksiDB();
            Statement stmt = conn.createStatement();
            ResultSet data = stmt.executeQuery("select M.nim_mahasantri, M.nama, F.nama_fak, J.nama_jur, J.kode_fak , N.nama_mab, M.kamar, M.lantai from mahasantri M , fakultas F, jurusan J, mabna N where M.kode_jur = J.kode_jur and F.kode_fak = J.kode_fak and M.kode_mab = N.kode_mab and M.nim_mahasantri like '%"+txt_nim.getText()+"%' and M.nama like '%"+txt_nama.getText()+"%' and J.nama_jur = '"+cm_jurusan.getSelectedItem().toString()+"' and N.nama_mab = '"+cm_mabna.getSelectedItem().toString()+"'");

            while (data.next()) {
                Object[] obj = new Object[7];
                obj[0] = data.getString("M.nim_mahasantri");
                obj[1] = data.getString("M.nama");
                obj[2] = data.getString("F.nama_fak");
                obj[3] = data.getString("J.nama_jur");
                obj[4] = data.getString("N.nama_mab");
                obj[5] = data.getString("M.kamar");
                obj[6] = data.getString("M.lantai");

                model.addRow(obj);

            }
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        awal();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt_namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_namaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_namaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FormAdmin n = new FormAdmin();
        n.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {

            if (txt_nim.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, NIM belum diisi !");
                txt_nim.requestFocus();
            } else if (txt_nama.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Nama belum diisi !");
                txt_nama.requestFocus();

            } else if (txt_kamar.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Kamar belum diisi !");
                txt_kamar.requestFocus();

            } else if (txt_lantai.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Lantai belum diisi !");
                txt_lantai.requestFocus();

            } else {
                PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                        "insert into mahasantri values (?,?,?,?,?,?)");

                statement.setString(1, txt_nim.getText());
                statement.setString(2, txt_nama.getText());
                statement.setString(3, ambilkodejur(cm_jurusan.getSelectedItem().toString()));
                statement.setString(4, ambilkodemabna(cm_mabna.getSelectedItem().toString()));
                statement.setString(5, txt_lantai.getText());
                statement.setString(6, txt_kamar.getText());
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Baru telah di tambahkan");
                awal();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "gagal menambah Data");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus dataini ?", "Warning", 2) == JOptionPane.YES_OPTION) {
            try {

                PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                        "delete from mahasantri where nim = ?");

                statement.setString(1, txt_nim.getText());
                statement.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data Telah Di Hapus");
                awal();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus");
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {

            if (txt_nim.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, NIM belum diisi !");
                txt_nim.requestFocus();
            } else if (txt_nama.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Nama belum diisi !");
                txt_nama.requestFocus();

            } else if (txt_kamar.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Kamar belum diisi !");
                txt_kamar.requestFocus();

            } else if (txt_lantai.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Lantai belum diisi !");
                txt_lantai.requestFocus();

            } else {
                PreparedStatement statement = koneksi.koneksiDB().prepareStatement(
                        "UPDATE mahasantri SET nama = ?, kode_jur = ?, kode_mab = ?, lantai = ?, kamar = ? WHERE nim = ?");

                statement.setString(1, txt_nama.getText());
                statement.setString(2, ambilkodejur(cm_jurusan.getSelectedItem().toString()));
                statement.setString(3, ambilkodemabna(cm_mabna.getSelectedItem().toString()));
                statement.setString(4, txt_lantai.getText());
                statement.setString(5, txt_kamar.getText());
                statement.setString(6, txt_nim.getText());
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Baru telah di ubah");
                awal();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "gagal mengubah Data");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void tabel_mahasantriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_mahasantriMouseClicked
        // TODO add your handling code here:
        try {
            int row = tabel_mahasantri.rowAtPoint(evt.getPoint());

            String nim = tabel_mahasantri.getValueAt(row, 0).toString();
            String nama = tabel_mahasantri.getValueAt(row, 1).toString();
            String jurusan = tabel_mahasantri.getValueAt(row, 3).toString();
            String mabna = tabel_mahasantri.getValueAt(row, 4).toString();
            String lantai = tabel_mahasantri.getValueAt(row, 5).toString();
            String kamar = tabel_mahasantri.getValueAt(row, 6).toString();

            txt_nim.setText(String.valueOf(nim));
            txt_nama.setText(String.valueOf(nama));
            cm_jurusan.setSelectedItem(jurusan);
            cm_mabna.setSelectedItem(mabna);
            txt_lantai.setText(String.valueOf(lantai));
            txt_kamar.setText(String.valueOf(kamar));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tabel_mahasantriMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooseFile = new JFileChooser();
        chooseFile.showOpenDialog(null);
        File file = chooseFile.getSelectedFile();
        pathfile = file.getAbsolutePath();
        btn_Importfile.setEnabled(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btn_ImportfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImportfileActionPerformed
        // TODO add your handling code here:
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathfile));
            String line;
            int pas = 0;
            while ((line = br.readLine()) != null) {

                String[] value = line.split(",");
                String sql = "insert into mahasantri values (?,?,?,?,?,?)";
                java.sql.Connection conn = (java.sql.Connection) koneksi.koneksiDB();
                PreparedStatement stmt = conn.prepareStatement(sql);

                if (pas == 0) {
                    pas = 1;
                } else {
                    stmt.setString(1, value[0]);
                    stmt.setString(2, value[1]);
                    stmt.setString(3, value[2]);
                    stmt.setString(4, value[3]);
                    stmt.setString(5, value[4]);
                    stmt.setString(6, value[5]);
                    stmt.executeUpdate();
                    stmt.close();
                    awal();
                }

            }

            br.close();
            JOptionPane.showMessageDialog(null, "Import data berhasil !");
            btn_Importfile.setEnabled(false);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btn_ImportfileActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        saveCSV(tabel_mahasantri);
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
            java.util.logging.Logger.getLogger(FormMahasantriAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormMahasantriAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormMahasantriAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormMahasantriAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormMahasantriAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Importfile;
    private javax.swing.JComboBox<String> cm_jurusan;
    private javax.swing.JComboBox<String> cm_mabna;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel_mahasantri;
    private javax.swing.JTextField txt_kamar;
    private javax.swing.JTextField txt_lantai;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_nim;
    // End of variables declaration//GEN-END:variables
}
