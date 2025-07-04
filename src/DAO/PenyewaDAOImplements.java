/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAOInterface.PenyewaDAO;
import Helper.KoneksiDB;
import Model.Penyewa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gangs
 */
public class PenyewaDAOImplements implements PenyewaDAO {
    private Connection koneksi;
    
    public PenyewaDAOImplements() {
        koneksi = KoneksiDB.getConnection();
    }
    
    @Override
    public void create(Penyewa penyewa) {
        String sql = "INSERT INTO penyewa (id_penyewa, name, no_hp, pekerjaan, alamat, kamar) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setInt(1, penyewa.getId_penyewa());
            ps.setString(2, penyewa.getNama_penyewa());
            ps.setString(3, penyewa.getNomor_hp());
            ps.setString(4, penyewa.getPekerjaan());
            ps.setString(5, penyewa.getAlamat());
            ps.setString(6, penyewa.getKamar());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error saat menyimpan data: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update (Penyewa penyewa) {
        String sql = "UPDATE penyewa SET name = ?, no_hp = ?, pekerjaan = ?, alamat = ?, kamar = ? WHERE id_penyewa = ?";

        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            statement.setString(1, penyewa.getNama_penyewa());
            statement.setString(2, penyewa.getNomor_hp());
            statement.setString(3, penyewa.getPekerjaan());
            statement.setString(4, penyewa.getAlamat());
            statement.setString(5, penyewa.getKamar());
            statement.setInt(6, penyewa.getId_penyewa());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Berhasil Update: " + rowsUpdated + " baris diperbarui");
            } else {
                System.out.println("Update gagal: Tidak ada baris yang diperbarui");
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error saat mengupdate penyewa: " + ex.getMessage());
        }       
    }

    public void delete(Penyewa penyewa) {
        String sql = "DELETE FROM penyewa WHERE id_penyewa = ?";

        PreparedStatement statement = null;

        try {
            statement = koneksi.prepareStatement(sql);
            statement.setInt(1, penyewa.getId_penyewa());
            statement.executeUpdate();
            statement.close();

            System.out.println("Berhasil menghapus penyewa!");
        } catch (SQLException ex) {
            System.out.println("Error saat menghapus penyewa: " + ex.getMessage());
        }
    }
    
    public List<Penyewa> readAll() {
        List<Penyewa> listPenyewa = new ArrayList<>();
        String sql = "SELECT * FROM penyewa";

        try {
        PreparedStatement st = koneksi.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Penyewa penyewa = new Penyewa();
                penyewa.setId_penyewa(rs.getInt("id_penyewa"));
                penyewa.setNama_penyewa(rs.getString("name"));
                penyewa.setNomor_hp(rs.getString("no_hp"));
                penyewa.setPekerjaan(rs.getString("pekerjaan"));
                penyewa.setAlamat(rs.getString("alamat"));
                penyewa.setKamar(rs.getString("kamar"));
                listPenyewa.add(penyewa);
            }
        } catch (SQLException ex) {
            System.out.println("Error saat mengambil data penyewa: " + ex.getMessage());
        }
        
        return listPenyewa;
    }
        
    public List<Penyewa> getPenyewaByName(String nama_penyewa) {
        List<Penyewa> listPenyewa = new ArrayList<>();
        String sql = "SELECT * FROM penyewa WHERE name LIKE ?";
        
        try {
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, "%" + nama_penyewa + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Penyewa penyewa = new Penyewa();
                penyewa.setId_penyewa(rs.getInt("id_penyewa"));
                penyewa.setNama_penyewa(rs.getString("name"));
                penyewa.setNomor_hp(rs.getString("no_hp"));
                penyewa.setPekerjaan(rs.getString("pekerjaan"));
                penyewa.setAlamat(rs.getString("alamat"));
                penyewa.setKamar(rs.getString("kamar"));
                listPenyewa.add(penyewa);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error saat mencari penyewa: " + e.getMessage());
        }
        
        return listPenyewa;
    }
}
