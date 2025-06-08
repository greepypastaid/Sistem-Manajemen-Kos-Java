/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.PenyewaDAOImplements;
import Model.Penyewa;
import Model.TabelModelPenyewa;
import View.HalamanAdmin;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author gangs
 */
public class PenyewaController {
    HalamanAdmin frame;
    PenyewaDAOImplements implPenyewa;
    List<Penyewa> penyewa;
    
    public PenyewaController(HalamanAdmin frame) {
        this.frame = frame;
        implPenyewa = new PenyewaDAOImplements();
        penyewa = implPenyewa.readAll();
    }
    
    public void isiTable() {
        penyewa = implPenyewa.readAll();
        TabelModelPenyewa tp = new TabelModelPenyewa(penyewa);
        frame.getTableDataPenyewa().setModel(tp);
    }
    
    public void insert() {
        // Validasi input - pastikan nomor penyewa dan harga tidak kosong
        if (!frame.getTxtNamaPenyewa().getText().trim().isEmpty() && 
            !frame.getAlamat().getText().trim().isEmpty()) {
            
            Penyewa penyewa = new Penyewa();
            
            // Tambahkan ID dari form jika tidak kosong
            if (!frame.getTxtIDPenyewa().getText().trim().isEmpty()) {
                penyewa.setId_penyewa(Integer.parseInt(frame.getTxtIDPenyewa().getText()));
            }
            
            penyewa.setNama_penyewa(frame.getTxtNamaPenyewa().getText());
            penyewa.setNomor_hp(frame.getNomorHP().getText());
            penyewa.setPekerjaan(frame.getPekerjaan().getText());
            penyewa.setAlamat(frame.getAlamat().getText());
            penyewa.setKamar(frame.getKamar().getText());
            
            implPenyewa.create(penyewa);
            JOptionPane.showMessageDialog(null, "Data penyewa berhasil disimpan");
            
            // Refresh tabel
            isiTable();
        } else {
            JOptionPane.showMessageDialog(frame, "Nomor Penyewa dan Harga tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void update() {
        Penyewa penyewa = new Penyewa();
        penyewa.setId_penyewa(Integer.parseInt(frame.getTxtIDPenyewa().getText()));
        penyewa.setNama_penyewa(frame.getTxtNamaPenyewa().getText());
        penyewa.setNomor_hp(frame.getNomorHP().getText());
        penyewa.setPekerjaan(frame.getPekerjaan().getText());
        penyewa.setAlamat(frame.getAlamat().getText());
        penyewa.setKamar(frame.getKamar().getText());

        implPenyewa.update(penyewa);
        JOptionPane.showMessageDialog(null, "Data penyewa berhasil diupdate");

        isiTable();
    }

    public void delete() {
        Penyewa penyewa = new Penyewa();
        penyewa.setId_penyewa(Integer.parseInt(frame.getTxtIDPenyewa().getText()));
        implPenyewa.delete(penyewa);
        JOptionPane.showMessageDialog(null, "Data penyewa berhasil dihapus");
        isiTable();
    }

    public void reset() {
        frame.getTxtIDPenyewa().setText("");
        frame.getTxtNamaPenyewa().setText("");
        frame.getNomorHP().setText("");
        frame.getAlamat().setText("");
        frame.getPekerjaan().setText("");
        frame.getKamar().setText("");
        
        // Refresh tabel setelah reset
        isiTable();
    }
    
    public void cariPenyewa() {
        if (!frame.getTxtCariPenyewa().getText().trim().isEmpty()) {
            isiTableCariPenyewa();
        } else {
            JOptionPane.showMessageDialog(frame, "Silahkan Masukkan Nama Penyewa");
        }
    }
    
    public void isiTableCariPenyewa() {
        penyewa = implPenyewa.getPenyewaByName(frame.getTxtCariPenyewa().getText());
        TabelModelPenyewa tp = new TabelModelPenyewa(penyewa);
        frame.getTableDataPenyewa().setModel(tp);
    }

    public void isiField(int row) {
        frame.getTxtIDPenyewa().setText(String.valueOf(penyewa.get(row).getId_penyewa()));
        frame.getTxtNamaPenyewa().setText(penyewa.get(row).getNama_penyewa());
        frame.getNomorHP().setText(penyewa.get(row).getNomor_hp());
        frame.getPekerjaan().setText(penyewa.get(row).getPekerjaan());
        frame.getAlamat().setText(penyewa.get(row).getAlamat());
        frame.getKamar().setText(penyewa.get(row).getKamar());
    }
}
