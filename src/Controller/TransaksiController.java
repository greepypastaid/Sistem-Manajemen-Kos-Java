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
//            if (k.getStatus().equalsIgnoreCase("tersedia")) {
                frame.getLsKamar().addItem(k);
//            }
        }
    }
    
    public void insert() {
        Penyewa penyewa = (Penyewa) frame.getLsPenyewa().getSelectedItem(); // frame.getLsPenyewa().getSelectedItem(); ini tuh ngambil dari combo box penyewa
        Kamar kamar = (Kamar) frame.getLsKamar().getSelectedItem(); // frame.getLsKamar().getSelectedItem(); ini tuh ngambil dari combo box kamar
        
        try {
            Transaksi transaksi = new Transaksi();
            transaksi.setId_transaksi(Integer.parseInt(frame.getTxtIDTransaksi().getText())); // Ini juga ngeubah nih le
            transaksi.setId_penyewa(penyewa);
            transaksi.setId_kamar(kamar);
            transaksi.setTanggal_sewa(frame.getTxtTanggalSewa().getText());
            transaksi.setDurasi_bulan(Integer.parseInt(frame.getTxtDurasi().getText())); // UBAH DULU JADI STRING MAKE INTEGERPARSE
            transaksi.setTotal(Integer.parseInt(frame.getTxtTotal().getText())); // INI UBAH JUGA JADI STRING MAKE INTEGERPARSE
            transaksi.setStatus_pembayaran(frame.getLsStatusPembayaran().getSelectedItem().toString());
            
            implTransaksi.insert(transaksi);
            JOptionPane.showMessageDialog(null, "Data transaksi berhasil disimpan!");

            // Tandai kamar sebagai 'Terisi' setelah transaksi berhasil
            kamar.setStatus("Terisi"); // nah ini dia le yang buat bagian statusnya itu tuh kok bisa berubah
            implKamar.updateStatus(kamar); // Abis statusnya diubah, ini nih fungsi yang bikin status kamarnya bisa berubah, coba buka aja tuh di kamarDAOImplements
            
            reset();
            isiTable();
            loadKamarComboBox(); // Muat ulang combo box kamar untuk menghilangkan kamar yg baru terisi

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Semua data harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update() {
        Penyewa penyewa = (Penyewa) frame.getLsPenyewa().getSelectedItem();
        Kamar kamar = (Kamar) frame.getLsKamar().getSelectedItem();

        if (frame.getTxtIDTransaksi().getText().trim().isEmpty() || penyewa == null || kamar == null) {
            JOptionPane.showMessageDialog(frame, "Silakan pilih data transaksi yang akan diubah dari tabel.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Transaksi transaksi = new Transaksi();
            transaksi.setId_transaksi(Integer.parseInt(frame.getTxtIDTransaksi().getText()));
            transaksi.setId_penyewa(penyewa);
            transaksi.setId_kamar(kamar);
            transaksi.setTanggal_sewa(frame.getTxtTanggalSewa().getText());
            transaksi.setDurasi_bulan(Integer.parseInt(frame.getTxtDurasi().getText()));
            transaksi.setTotal(Integer.parseInt(frame.getTxtTotal().getText()));
            transaksi.setStatus_pembayaran(frame.getLsStatusPembayaran().getSelectedItem().toString());

            implTransaksi.update(transaksi);

            // Tandai kamar sebagai 'Terisi' setelah transaksi berhasil
            kamar.setStatus("Tersedia"); // nah ini dia le yang buat bagian statusnya itu tuh kok bisa berubah
            implKamar.updateStatus(kamar); // Abis statusnya diubah, ini nih fungsi yang bikin status kamarnya bisa berubah, coba buka aja tuh di kamarDAOImplements

            JOptionPane.showMessageDialog(null, "Data transaksi berhasil diupdate");

            isiTable();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Durasi dan Total harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void delete() {
        Transaksi transaksi = new Transaksi();
        transaksi.setId_transaksi(Integer.parseInt(frame.getTxtIDTransaksi().getText()));
        implTransaksi.delete(transaksi);
        JOptionPane.showMessageDialog(null, "Data transaksi berhasil dihapus");
        isiTable();
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
    
    public void isiField(int row) {
        frame.getTxtIDTransaksi().setText(String.valueOf(transaksi.get(row).getId_transaksi()));
        frame.getLsPenyewa().setSelectedItem(transaksi.get(row).getId_penyewa());
        frame.getLsKamar().setSelectedItem(transaksi.get(row).getId_kamar());
        frame.getTxtTanggalSewa().setText(transaksi.get(row).getTanggal_sewa());
        frame.getTxtDurasi().setText(String.valueOf(transaksi.get(row).getDurasi_bulan()));
        frame.getTxtTotal().setText(String.valueOf(transaksi.get(row).getTotal()));
    }

    public void cariTransaksi() {
        if (!frame.getTxtCariTransaksi().getText().trim().isEmpty()) {
            isiTableCariTransaksi();
        } else {
            JOptionPane.showMessageDialog(frame, "Silahkan Masukkan ID Transaksi");
        }
    }
    
    public void isiTableCariTransaksi() {
        transaksi = implTransaksi.getTransaksiById(Integer.parseInt(frame.getTxtCariTransaksi().getText()));
        TabelModelTransaksi tmt = new TabelModelTransaksi(transaksi);
        frame.getTableDataTransaksi().setModel(tmt);
    }
}
