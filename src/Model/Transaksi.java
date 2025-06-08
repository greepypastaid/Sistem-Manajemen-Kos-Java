/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author gangs
 */
public class Transaksi {
    private int id_transaksi;
    private Penyewa penyewa; // RELASI KE TABEL PENYEWA YA ANYING :D
    private Kamar kamar; // RELASI KE TABEL KAMAR YA LE!
    private String tanggal_sewa;
    private int durasi_bulan;
    private int total;
    private String status_pembayaran;
    
    public Transaksi() {
    }
    
    public Transaksi(int id_transaksi, Penyewa penyewa, Kamar kamar, String tanggal_sewa, int durasi_bulan, int total, String status_pembayaran) {
        this.id_transaksi = id_transaksi;
        this.penyewa = penyewa;
        this.kamar = kamar;
        this.tanggal_sewa = tanggal_sewa;
        this.total = total;
        this.status_pembayaran = status_pembayaran;
    }

    /**
     * @return the id_transaksi
     */
    public int getId_transaksi() {
        return id_transaksi;
    }

    /**
     * @param id_transaksi the id_transaksi to set
     */
    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    /**
     * @return the id_penyewa
     */
    public Penyewa getId_penyewa() {
        return penyewa;
    }

    /**
     * @param id_penyewa the id_penyewa to set
     */
    public void setId_penyewa(Penyewa penyewa) {
        this.penyewa = penyewa;
    }

    /**
     * @return the id_kamar
     */
    public Kamar getId_kamar() {
        return kamar;
    }

    /**
     * @param id_kamar the id_kamar to set
     */
    public void setId_kamar(Kamar kamar) {
        this.kamar= kamar;
    }

    /**
     * @return the tanggal_sewa
     */
    public String getTanggal_sewa() {
        return tanggal_sewa;
    }

    /**
     * @param tanggal_sewa the tanggal_sewa to set
     */
    public void setTanggal_sewa(String tanggal_sewa) {
        this.tanggal_sewa = tanggal_sewa;
    }

    /**
     * @return the durasi_bulan
     */
    public int getDurasi_bulan() {
        return durasi_bulan;
    }

    /**
     * @param durasi_bulan the durasi_bulan to set
     */
    public void setDurasi_bulan(int durasi_bulan) {
        this.durasi_bulan = durasi_bulan;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * @return the status_pembayaran
     */
    public String getStatus_pembayaran() {
        return status_pembayaran;
    }

    /**
     * @param status_pembayaran the status_pembayaran to set
     */
    public void setStatus_pembayaran(String status_pembayaran) {
        this.status_pembayaran = status_pembayaran;
    }
}
