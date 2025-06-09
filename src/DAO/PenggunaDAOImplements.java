/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAOInterface.PenggunaDAO;
import Helper.KoneksiDB;
import Model.Pengguna;
import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gangs
 */
public class PenggunaDAOImplements implements PenggunaDAO {
    private Connection koneksi;
    
    public PenggunaDAOImplements() {
        koneksi = KoneksiDB.getConnection();
    }
    
    @Override
    public boolean register(Pengguna pengguna) {
        String sql = "INSERT INTO pengguna (username, password, role) VALUES (?, ?, ?)";
        
        try {
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, pengguna.getUsername());
            ps.setString(2, pengguna.getPassword());
            ps.setString(3, pengguna.getRole());
            
            int result = ps.executeUpdate();
            ps.close();
            
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Error saat registrasi: " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean checkUsername(String username) {
        String sql = "SELECT * FROM pengguna WHERE username = ?";
        
        try {
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            boolean exists = rs.next();
            
            rs.close();
            ps.close();
            
            return exists;
        } catch (SQLException e) {
            System.out.println("Error saat cek username: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Pengguna login(String username, String password) {
        Pengguna pengguna = null;
        String sql = "SELECT * FROM pengguna WHERE username = ? AND password = ?";
        
        try {
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                pengguna = new Pengguna();
                pengguna.setId_pengguna(rs.getInt("id_pengguna"));
                pengguna.setUsername(rs.getString("username"));
                pengguna.setPassword(rs.getString("password"));
                pengguna.setRole(rs.getString("role"));
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error saat login: " + e.getMessage());
        }
        
        return pengguna;
    }

    @Override
    public List<Pengguna> readAll() {
        List<Pengguna> listPengguna = new ArrayList<>();
        String sql = "SELECT * FROM pengguna";
        
        try {
            PreparedStatement st = koneksi.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pengguna pengguna = new Pengguna();
                pengguna.setId_pengguna(rs.getInt("id_pengguna"));
                pengguna.setUsername(rs.getString("username"));
                pengguna.setPassword(rs.getString("password"));
                pengguna.setRole(rs.getString("role"));
                listPengguna.add(pengguna);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println("Error saat mengambil data pengguna: " + ex.getMessage());
        }
        
        return listPengguna;
    }

    public void update(Pengguna pengguna) {
        String sql = "UPDATE pengguna SET username = ?, role = ? WHERE id_pengguna = ?";

        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            statement.setString(1, pengguna.getUsername());
            statement.setString(2, pengguna.getRole());
            statement.setInt(3, pengguna.getId_pengguna());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Berhasil Update: " + rowsUpdated + " baris diperbarui");
            } else {
                System.out.println("Update gagal: Tidak ada pengguna dengan ID tersebut.");
            }
        } catch (SQLException e) {
            System.out.println("Error saat mengupdate pengguna: " + e.getMessage());
        }
    }

    public void delete(Pengguna pengguna) {
        String sql = "DELETE FROM pengguna WHERE username = ?";

        PreparedStatement statement = null;

        try {
            statement = koneksi.prepareStatement(sql);
            statement.setString(1, pengguna.getUsername());
            statement.executeUpdate();
            statement.close();

            System.out.println("Berhasil menghapus pengguna!");
        } catch (SQLException ex) {
            System.out.println("Error saat menghapus pengguna: " + ex.getMessage());
        }
    }

    public List<Pengguna> getPenggunaByName(String username) {
        List<Pengguna> listPengguna = new ArrayList<>();
        String sql = "SELECT * FROM pengguna WHERE username LIKE ?";
        
        try {
            PreparedStatement ps = koneksi.prepareStatement(sql);
            ps.setString(1, "%" + username + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pengguna pengguna = new Pengguna();
                pengguna.setId_pengguna(rs.getInt("id_pengguna"));
                pengguna.setUsername(rs.getString("username"));
                pengguna.setPassword(rs.getString("password"));
                pengguna.setRole(rs.getString("role"));
                listPengguna.add(pengguna);
            }
        } catch (SQLException ex) {
            System.out.println("Error saat mengambil data pengguna: " + ex.getMessage());
        }

        return listPengguna;
    } 
}
