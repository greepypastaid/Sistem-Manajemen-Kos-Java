/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.KamarDAOImplements;
import DAOInterface.KamarDAO;
import Model.Kamar;
import Model.Penyewa;
import Model.TabelModelKamar;
import View.HalamanAdmin;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gangs
 */

public class KamarController {
    HalamanAdmin frame;
    KamarDAOImplements implKamar;
    List<Kamar> kamar;
    
    public KamarController(HalamanAdmin frame) {
        this.frame = frame;
        implKamar = new KamarDAOImplements();
        kamar = implKamar.readAll();
    }
    
    public void isiTable() {
        kamar = implKamar.readAll();
        TabelModelKamar tmk = new TabelModelKamar(kamar);
        frame.getTableDataKamar().setModel(tmk);
    }
    
    public void loadKamarComboBox() {
        List<Kamar> kamarList = implKamar.readAll();
    }
    
    public void insert() {
        // Validasi input - pastikan nomor kamar dan harga tidak kosong
        if (!frame.getTxtNomorKamar().getText().trim().isEmpty() && 
            !frame.getTxtHarga().getText().trim().isEmpty()) {
            
            Kamar kamar = new Kamar();
            
            // Tambahkan ID dari form jika tidak kosong
            if (!frame.getTxtIDKamar().getText().trim().isEmpty()) {
                kamar.setId_kamar(Integer.parseInt(frame.getTxtIDKamar().getText()));
            }
            
            kamar.setNomor_kamar(frame.getTxtNomorKamar().getText());
            kamar.setTipe(frame.getLsTipeKamar().getSelectedItem().toString());
            
            try {
                int harga = Integer.parseInt(frame.getTxtHarga().getText());
                kamar.setHarga(harga);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            kamar.setStatus(frame.getLsStatus().getSelectedItem().toString());
            
            // Cek apakah nomor kamar sudah ada
            List<Kamar> existingKamar = implKamar.getKamarByNomor(kamar.getNomor_kamar());
            if (!existingKamar.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nomor kamar sudah ada!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            implKamar.create(kamar);
            JOptionPane.showMessageDialog(null, "Data kamar berhasil disimpan");
            
            // Refresh tabel
            isiTable();
        } else {
            JOptionPane.showMessageDialog(frame, "Nomor Kamar dan Harga tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // TANYA RUMPUT AJAH KALO GATAU FUNGSI INI BUAT APA :D
    public void update() {
        Kamar kamar = new Kamar();
        kamar.setId_kamar(Integer.parseInt(frame.getTxtIDKamar().getText()));
        kamar.setNomor_kamar(frame.getTxtNomorKamar().getText());
        kamar.setTipe(frame.getLsTipeKamar().getSelectedItem().toString());
        kamar.setHarga(Integer.parseInt(frame.getTxtHarga().getText()));
        kamar.setStatus(frame.getLsStatus().getSelectedItem().toString());

        implKamar.update(kamar);
        implKamar.updateStatus(kamar);
        JOptionPane.showMessageDialog(null, "Data kamar berhasil diupdate");

        isiTable();
    }

    // INI FUGNSI BUAT STECU STECU
    public void delete() {
        
        try {
            Kamar kamar = new Kamar();
            kamar.setId_kamar(Integer.parseInt(frame.getTxtIDKamar().getText()));
            implKamar.delete(kamar);
            JOptionPane.showMessageDialog(null, "Data kamar berhasil dihapus");
    
            isiTable();            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data kamar gagal dihapus karena kamar memiliki transaksi dengan penyewa dan transaksi!");
        }

    }

    public void reset() {
        frame.getTxtIDKamar().setText("");
        frame.getTxtNomorKamar().setText("");
        frame.getLsTipeKamar().setSelectedIndex(0);
        frame.getTxtHarga().setText("");
        frame.getLsStatus().setSelectedIndex(0);
        
        // Refresh tabel setelah reset
        isiTable();
    }
    
    public void cariKamar() {
        if (!frame.getTxtCariKamar().getText().trim().isEmpty()) {
            isiTableCariNama();
        } else {
            JOptionPane.showMessageDialog(frame, "SILAHKAN MASUKKAN NOMOR KAMAR");
        }
    }
    
    public void isiTableCariNama() {
        kamar = implKamar.getKamarByNomor(frame.getTxtCariKamar().getText());
        TabelModelKamar tmk = new TabelModelKamar(kamar);
        frame.getTableDataKamar().setModel(tmk);
    }

    // merupakan fungsi buat mouseListener tu loh yang pas diklik bakalan muncul data yang dipilih dari tabelnya cik!
    public void isiField(int row) {
        frame.getTxtIDKamar().setText(String.valueOf(kamar.get(row).getId_kamar()));
        frame.getTxtNomorKamar().setText(kamar.get(row).getNomor_kamar());
        frame.getLsTipeKamar().setSelectedItem(kamar.get(row).getTipe());
        frame.getTxtHarga().setText(String.valueOf(kamar.get(row).getHarga()));
        frame.getLsStatus().setSelectedItem(kamar.get(row).getStatus());
    }
}
