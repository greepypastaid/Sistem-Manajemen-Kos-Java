/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.KamarDAOImplements;
import DAOInterface.KamarDAO;
import Model.Kamar;
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
        frame.getTableData().setModel(tmk);
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
            if (implKamar.checkNomorKamar(kamar.getNomor_kamar())) {
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
    
        public void reset() {
        frame.getTxtIDKamar().setText("");
        frame.getTxtNomorKamar().setText("");
        frame.getLsTipeKamar().setSelectedIndex(0);  // Gunakan setSelectedIndex(0) bukan setSelectedItem("")
        frame.getTxtHarga().setText("");
        frame.getLsStatus().setSelectedIndex(0);
        
        // Refresh tabel setelah reset
        isiTable();
    }
}
