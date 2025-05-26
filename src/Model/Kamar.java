/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author gangs
 */
public class Kamar {
    private int id_kamar;
    private String nomor_kamar;
    private String tipe;
    private int harga;
    private String status;

    public Kamar() { // Konstruktor dasar
    }
    
    public Kamar(int id_kamar, String nomor_kamar, String tipe, int harga, String status) { // Kontruktor lengkap
        this.id_kamar = id_kamar;
        this.nomor_kamar = nomor_kamar;
        this.tipe = tipe;
        this.harga = harga;
        this.status = status;
    }
    
    public Kamar(String nomor_kamar, String tipe, int harga, String status) { // Kontruktor tanpa id untuk insert manual
        this.nomor_kamar = nomor_kamar;
        this.tipe = tipe;
        this.harga = harga;
        this.status = status;
    }
    
    // Getter dan Setter
    public int getId_kamar() {
        return id_kamar;
    }

    public void setId_kamar(int id_kamar) {
        this.id_kamar = id_kamar;
    }

    public String getNomor_kamar() {
        return nomor_kamar;
    }

    public void setNomor_kamar(String nomor_kamar) {
        this.nomor_kamar = nomor_kamar;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}



