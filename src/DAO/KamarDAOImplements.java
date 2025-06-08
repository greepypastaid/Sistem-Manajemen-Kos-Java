/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAOInterface.KamarDAO;
import Helper.KoneksiDB;
import Model.Kamar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gangs
 */
public class KamarDAOImplements implements KamarDAO {
    // Fungsinya buat nyambungin DB dan Program kita cuy;
    
    private Connection koneksi;
    
    public KamarDAOImplements() {
        koneksi = KoneksiDB.getConnection();
    }
    
    // Method rangka create kamar yg diambil dari KamarDAO pada DAOInterface nih
    public void create(Kamar kamar) {
        String sql = "INSERT INTO kamar (nomor_kamar, tipe, harga, status) VALUES (?, ?, ?, ?)"; // Perbaikan: tutup kurung yang hilang
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql); // Perbaikan: inisialisasi statement
            statement.setString(1, kamar.getNomor_kamar());
            statement.setString(2, kamar.getTipe());
            statement.setInt(3, kamar.getHarga());
            statement.setString(4, kamar.getStatus());
            statement.executeUpdate();
            statement.close(); // Perbaikan: tutup statement

            System.out.println("Berhasil menambahkan kamar!");
        } catch (SQLException ex) {
           System.out.println("Error saat menambah kamar: " + ex.getMessage());
        }
    }
    
    public List<Kamar> readAll() {
        List<Kamar> listKamar = new ArrayList<>();
        String sql = "SELECT * FROM kamar";

        try {
        PreparedStatement st = koneksi.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Kamar kamar = new Kamar();
                kamar.setId_kamar(rs.getInt("id_kamar"));
                kamar.setNomor_kamar(rs.getString("nomor_kamar"));
                kamar.setTipe(rs.getString("tipe"));
                kamar.setHarga(rs.getInt("harga"));
                kamar.setStatus(rs.getString("status"));
                listKamar.add(kamar);
            }
        } catch (SQLException ex) {
            System.out.println("Error saat mengambil data kamar: " + ex.getMessage());
        }
        
        return listKamar;
    }
    
    public List<Kamar> getKamarByNomor(String nomor_kamar) {
        List<Kamar> listKamar = new ArrayList<>();
        String sql = "SELECT * FROM kamar WHERE nomor_kamar LIKE ?";
        
        try {
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, "%" + nomor_kamar + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Kamar kamar = new Kamar();
                kamar.setId_kamar(rs.getInt("id_kamar"));
                kamar.setNomor_kamar(rs.getString("nomor_kamar"));
                kamar.setTipe(rs.getString("tipe"));
                kamar.setHarga(rs.getInt("harga"));
                kamar.setStatus(rs.getString("status"));
                listKamar.add(kamar);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error saat mencari kamar: " + e.getMessage());
        }
        
        return listKamar;
    }

    public void update(Kamar kamar) {
        String sql = "UPDATE kamar SET nomor_kamar = ?, tipe = ?, harga = ?, status = ? WHERE id_kamar = ?";
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            statement.setString(1, kamar.getNomor_kamar());
            statement.setString(2, kamar.getTipe());
            statement.setInt(3, kamar.getHarga());
            statement.setString(4, kamar.getStatus());
            statement.setInt(5, kamar.getId_kamar());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Berhasil Update: " + rowsUpdated + " baris diperbarui");
            } else {
                System.out.println("Update gagal: Tidak ada baris yang diperbarui");
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error saat mengupdate kamar: " + ex.getMessage());
        }
    }

    public void delete(Kamar kamar) {
        String sql = "DELETE FROM kamar WHERE id_kamar = ?";

        PreparedStatement statement = null;

        try {
            statement = koneksi.prepareStatement(sql);
            statement.setInt(1, kamar.getId_kamar());
            statement.executeUpdate();
            statement.close();

            System.out.println("Berhasil menghapus kamar!");
        } catch (SQLException ex) {
            System.out.println("Error saat menghapus kamar: " + ex.getMessage());
        }
    }
}
