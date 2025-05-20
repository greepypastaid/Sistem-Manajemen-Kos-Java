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

/**
 *
 * @author gangs
 */
public abstract class PenggunaDAOImplements implements PenggunaDAO {
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
}
