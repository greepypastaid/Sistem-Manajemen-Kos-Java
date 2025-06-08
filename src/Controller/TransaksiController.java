/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.KamarDAOImplements;
import DAO.PenyewaDAOImplements;
import DAO.TransaksiDAOImplements;
import Model.Kamar;
import Model.Penyewa;
import Model.TabelModelTransaksi;
import Model.Transaksi;
import View.HalamanAdmin2;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gangs
 */
public class TransaksiController {
    HalamanAdmin2 frame;
    TransaksiDAOImplements implTransaksi;
    KamarDAOImplements implKamar;
    PenyewaDAOImplements implPenyewa;
    List<Transaksi> transaksi;
    
    public TransaksiController(HalamanAdmin2 frame) {
        this.frame = frame;
        implTransaksi = new TransaksiDAOImplements();
        implKamar = new KamarDAOImplements();
        implPenyewa = new PenyewaDAOImplements();
        transaksi = implTransaksi.readAll();
    }
    
    public void isiTable() {
        transaksi = implTransaksi.readAll();
        TabelModelTransaksi tmt = new TabelModelTransaksi(transaksi);
        frame.getTableDataTransaksi().setModel(tmt);
    }
    
    public void loadPenyewaComboBox() {
        List<Penyewa> penyewaList = implPenyewa.readAll();
        frame.getLsPenyewa().removeAllItems();
        for (Penyewa p : penyewaList) {
            frame.getLsPenyewa().addItem(p);
        }
    }
    
    public void loadKamarComboBox() {
        List<Kamar> kamarList = implKamar.readAll();
        frame.getLsKamar().removeAllItems();
        for (Kamar k : kamarList) {
            // Hanya tampilkan kamar yang tersedia
            if (k.getStatus().equalsIgnoreCase("tersedia")) {
                frame.getLsKamar().addItem(k);
            }
        }
    }
    
    public void insert() {
        Penyewa penyewa = (Penyewa) frame.getLsPenyewa().getSelectedItem();
        Kamar kamar = (Kamar) frame.getLsKamar().getSelectedItem();
        String tanggalSewa = frame.getTxtTanggalSewa().getText();
        String durasiStr = frame.getTxtDurasi().getText();
        String status = (String) frame.getLsStatusPembayaran().getSelectedItem();

        if (penyewa == null || kamar == null || tanggalSewa.trim().isEmpty() || durasiStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Semua data harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int durasi = Integer.parseInt(durasiStr);
            int hargaKamar = kamar.getHarga();
            int total = hargaKamar;
            
            // Set total harga di text field agar terlihat pengguna
            frame.getTxtTotal().setText(String.valueOf(total));

            Transaksi t = new Transaksi();
            t.setId_penyewa(penyewa);
            t.setId_kamar(kamar);
            t.setTanggal_sewa(tanggalSewa);
            t.setDurasi_bulan(durasi);
            t.setTotal(total);
            t.setStatus_pembayaran(status);
            
            implTransaksi.insert(t);
            JOptionPane.showMessageDialog(null, "Data transaksi berhasil disimpan!");

            // Tandai kamar sebagai 'Terisi' setelah transaksi berhasil
            kamar.setStatus("Terisi");
//             implKamar.update(kamar); // Anda perlu menambahkan method update di KamarDAOImplements
            
            reset();
            isiTable();
            loadKamarComboBox(); // Muat ulang combo box kamar untuk menghilangkan kamar yg baru terisi

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Durasi harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void reset() {
        frame.getTxtIDTransaksi().setText("");
        frame.getLsPenyewa().setSelectedIndex(-1);
        frame.getLsKamar().setSelectedIndex(-1);
        frame.getTxtTanggalSewa().setText("");
        frame.getTxtDurasi().setText("");
        frame.getTxtTotal().setText("");
        frame.getLsStatusPembayaran().setSelectedIndex(0);
    }
}
