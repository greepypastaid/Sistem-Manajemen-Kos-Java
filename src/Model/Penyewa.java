/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author gangs
 */
public class Penyewa {
    private int id_penyewa;
    private String nama_Penyewa;
    private String nomor_hp;
    private String pekerjaan;
    private String alamat;
    private String kamar;

    public Penyewa() { // Konstruktor dasar
    }
    
    public Penyewa(int id_penyewa, String nama_Penyewa, String nomor_hp, String pekerjaan, String alamat, String kamar) { // Kontruktor lengkap
        this.id_penyewa = id_penyewa;
        this.nama_Penyewa = nama_Penyewa;
        this.nomor_hp = nomor_hp;
        this.pekerjaan = pekerjaan;
        this.alamat = alamat;
        this.kamar = kamar;
    }
    
    public Penyewa(String nama_Penyewa, String nomor_hp, String pekerjaan, String alamat, String kamar) { // Kontruktor tanpa id untuk insert manual
        this.nama_Penyewa = nama_Penyewa;
        this.nomor_hp = nomor_hp;
        this.pekerjaan = pekerjaan;
        this.alamat = alamat;
        this.kamar = kamar;
    }
    
    // Getter dan Setter
    public int getId_penyewa() {
        return id_penyewa;
    }

    public void setId_penyewa(int id_penyewa) {
        this.id_penyewa = id_penyewa;
    }

    public String getNama_penyewa() {
        return nama_Penyewa;
    }

    public void setNama_penyewa(String nama_Penyewa) {
        this.nama_Penyewa = nama_Penyewa;
    }

    public String getNomor_hp() {
        return nomor_hp;
    }

    public void setNomor_hp(String nomor_hp) {
        this.nomor_hp = nomor_hp;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKamar() {
        return kamar;
    }

    public void setKamar(String kamar) {
        this.kamar = kamar;
    }
}
