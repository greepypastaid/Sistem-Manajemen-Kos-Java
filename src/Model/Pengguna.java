/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author gangs
 */
public class Pengguna {
    private int id_pengguna;
    private String username;
    private String password;
    private String role;
    
    // Constructor tanpa parameter
    public Pengguna() {
    }
    
    // Constructor dengan parameter
    public Pengguna(int id_pengguna, String username, String password, String role) {
        this.id_pengguna = id_pengguna;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    // Constructor tanpa id (untuk insert baru)
    public Pengguna(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    // Getter dan Setter
    public int getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(int id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
