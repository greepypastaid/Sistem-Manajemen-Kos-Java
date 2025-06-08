/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Helper.KoneksiDB;
import Model.Transaksi;
import DAOInterface.TransaksiDAO;
import Model.Kamar;
import Model.Penyewa;
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

public class TransaksiDAOImplements implements TransaksiDAO {
    private Connection koneksi;
    
    public TransaksiDAOImplements() {
        koneksi = KoneksiDB.getConnection();
    }
    
    // KALO INI KEK FORMAT BIASANYA YAKK NAMBAHINNYA
    @Override
    public void insert(Transaksi transaksi) {
        String sql = "INSERT INTO transaksi (id_transaksi, id_penyewa, id_kamar, tanggal_sewa, durasi_bulan, total, status_pembayaran) VALUES (?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            statement.setInt(1, transaksi.getId_transaksi());
            statement.setInt(2, transaksi.getId_penyewa().getId_penyewa());
            statement.setInt(3, transaksi.getId_kamar().getId_kamar());
            statement.setString(4, transaksi.getTanggal_sewa());
            statement.setInt(5, transaksi.getDurasi_bulan());
            statement.setInt(6, transaksi.getTotal());
            statement.setString(7, transaksi.getStatus_pembayaran());
            statement.executeUpdate();
            statement.close();

            System.out.println("Berhasil menambahkan transaksi!");
        } catch (SQLException ex) {
           System.out.println("Error saat menambah transaksi: " + ex.getMessage());
        }
    }

    public void update(Transaksi transaksi) {
        String sql = "UPDATE transaksi SET id_penyewa = ?, id_kamar = ?, tanggal_sewa = ?, durasi_bulan = ?, total = ?, status_pembayaran = ? WHERE id_transaksi = ?";

        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            statement.setInt(1, transaksi.getId_penyewa().getId_penyewa());
            statement.setInt(2, transaksi.getId_kamar().getId_kamar());
            statement.setString(3, transaksi.getTanggal_sewa());
            statement.setInt(4, transaksi.getDurasi_bulan());
            statement.setInt(5, transaksi.getTotal());
            statement.setString(6, transaksi.getStatus_pembayaran());
            statement.setInt(7, transaksi.getId_transaksi());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Berhasil Update: " + rowsUpdated + " baris diperbarui");
            } else {
                System.out.println("Update gagal: Tidak ada baris yang diperbarui");
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error saat mengupdate transaksi: " + ex.getMessage());
        }
    }

    public void delete(Transaksi transaksi) {
        String sql = "DELETE FROM transaksi WHERE id_transaksi = ?";

        PreparedStatement statement = null;

        try {
            statement = koneksi.prepareStatement(sql);
            statement.setInt(1, transaksi.getId_transaksi());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error saat menghapus transaksi: " + ex.getMessage());
        }
    }
    
    // NAH GEGARA RELATION NIH JADINYA RADA RADA DISINI CO :(
    @Override
    public List<Transaksi> readAll() {
        List<Transaksi> listTransaksi = new ArrayList<>();
        String sql = "SELECT t.*, p.*, k.* FROM transaksi t " + // Manggil semuanya yaa, ga bisa kalo cuman manggil idnya ajaa
                     "LEFT JOIN penyewa p ON t.id_penyewa = p.id_penyewa " + // Dalang buat gabungin tabelnya nih, kalo ga ada idnya, maka akan diisi dengan null
                     "LEFT JOIN kamar k ON t.id_kamar = k.id_kamar"; // Dalang buat gabungin tabelnya nih, kalo ga ada idnya, maka akan diisi dengan null :D

        try {
            PreparedStatement st = koneksi.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                // 1. Buat Objek Penyewa dari data hasil JOIN (WAJIB INI TUH KEK BIKIN KERANGKANYA DULU BUAT DI READ GES)
                Penyewa penyewa = new Penyewa();
                penyewa.setId_penyewa(rs.getInt("id_penyewa"));
                penyewa.setNama_penyewa(rs.getString("name"));
                penyewa.setNomor_hp(rs.getString("no_hp"));
                penyewa.setPekerjaan(rs.getString("pekerjaan"));
                penyewa.setAlamat(rs.getString("alamat"));

                // 2. Buat Objek Kamar dari data hasil JOIN (NAH INI JUGA KEK YANG DI PENYEWA YA)
                Kamar kamar = new Kamar();
                kamar.setId_kamar(rs.getInt("id_kamar"));
                kamar.setNomor_kamar(rs.getString("nomor_kamar"));
                kamar.setTipe(rs.getString("tipe"));
                kamar.setHarga(rs.getInt("harga"));
                kamar.setStatus(rs.getString("status"));

                // 3. Buat Objek Transaksi (INI MAH KEK KITA NGISI VERSI NORMAL ITU GES!)
                Transaksi transaksi = new Transaksi();
                transaksi.setId_transaksi(rs.getInt("id_transaksi"));
                transaksi.setTanggal_sewa(rs.getString("tanggal_sewa"));
                transaksi.setDurasi_bulan(rs.getInt("durasi_bulan"));
                transaksi.setTotal(rs.getInt("total"));
                transaksi.setStatus_pembayaran(rs.getString("status_pembayaran"));
                
                // 4. Masukkan objek Penyewa dan Kamar ke dalam Transaksi (BARU NANTI DIPANGGILIN LEWAT SINI LE!)
                transaksi.setId_penyewa(penyewa);
                transaksi.setId_kamar(kamar);
                
                listTransaksi.add(transaksi);
            }
        } catch (SQLException ex) {
            System.out.println("Error saat mengambil data transaksi: " + ex.getMessage());
        }
        
        return listTransaksi;
    }
    
    @Override
    public List<Transaksi> getTransaksiById(int id_transaksi) {
        List<Transaksi> listTransaksi = new ArrayList<>();
        String sql = "SELECT t.*, p.*, k.* FROM transaksi t " +
                     "LEFT JOIN penyewa p ON t.id_penyewa = p.id_penyewa " +
                     "LEFT JOIN kamar k ON t.id_kamar = k.id_kamar " +
                     "WHERE t.id_transaksi = ?";
        try {
            PreparedStatement st = koneksi.prepareStatement(sql);
            st.setInt(1, id_transaksi);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Penyewa penyewa = new Penyewa();
                penyewa.setId_penyewa(rs.getInt("id_penyewa"));
                penyewa.setNama_penyewa(rs.getString("name"));
                penyewa.setNomor_hp(rs.getString("no_hp"));
                penyewa.setPekerjaan(rs.getString("pekerjaan"));
                penyewa.setAlamat(rs.getString("alamat"));

                Kamar kamar = new Kamar();
                kamar.setId_kamar(rs.getInt("id_kamar"));
                kamar.setNomor_kamar(rs.getString("nomor_kamar"));
                kamar.setTipe(rs.getString("tipe"));
                kamar.setHarga(rs.getInt("harga"));
                kamar.setStatus(rs.getString("status"));

                Transaksi transaksi = new Transaksi();
                transaksi.setId_transaksi(rs.getInt("id_transaksi"));
                transaksi.setTanggal_sewa(rs.getString("tanggal_sewa"));
                transaksi.setDurasi_bulan(rs.getInt("durasi_bulan"));
                transaksi.setTotal(rs.getInt("total"));
                transaksi.setStatus_pembayaran(rs.getString("status_pembayaran"));
                
                transaksi.setId_penyewa(penyewa);
                transaksi.setId_kamar(kamar);
                
                listTransaksi.add(transaksi);
            }
        } catch (SQLException ex) {
            System.out.println("Error saat mengambil data transaksi: " + ex.getMessage());
        }
        return listTransaksi;
    }
}
