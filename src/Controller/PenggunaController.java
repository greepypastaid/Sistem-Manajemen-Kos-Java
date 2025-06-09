/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package Controller;

import DAO.PenggunaDAOImplements;
import Model.Pengguna;
import Model.TabelModelPengguna;
import View.HalamanAdmin2;
import java.util.List;
import javax.swing.JOptionPane;

/**
*
* @author gangs
*/
public class PenggunaController {
   HalamanAdmin2 frame;
   PenggunaDAOImplements implPengguna;
   List<Pengguna> pengguna;

   public PenggunaController(HalamanAdmin2 frame) {
       this.frame = frame;
       implPengguna = new PenggunaDAOImplements();
       pengguna = implPengguna.readAll();
   }

   public void isiTable() {
       pengguna = implPengguna.readAll();
       TabelModelPengguna tmk = new TabelModelPengguna(pengguna);
       frame.getTableDataPengguna().setModel(tmk);
   }
   
   public void update() {
       Pengguna pengguna = new Pengguna();
       pengguna.setId_pengguna(Integer.parseInt(frame.getTxtIDPengguna().getText()));
       pengguna.setUsername(frame.getTxtUsernamePengguna().getText());
       pengguna.setRole(frame.getLsRolePengguna().getSelectedItem().toString());
       implPengguna.update(pengguna);
       JOptionPane.showMessageDialog(null, "Data pengguna berhasil diupdate");
       isiTable();
   }

   public void delete() {
      try {
          Pengguna pengguna = new Pengguna();
          pengguna.setUsername(frame.getTxtUsernamePengguna().getText());
          implPengguna.delete(pengguna);
          JOptionPane.showMessageDialog(null, "Berhasil menghapus");
          
          isiTable();
      } catch (Exception e){
          JOptionPane.showMessageDialog(null, "Data kamar tidak dapat dihapus");
      }
   }

   public void reset() {
       frame.getTxtIDPengguna().setText("");
       frame.getTxtUsernamePengguna().setText("");
       frame.getLsRolePengguna().setSelectedItem(0);
   }

   public void cariPengguna() {
       if (!frame.getTxtCariPengguna().getText().trim().isEmpty()) {
          pengguna = implPengguna.getPenggunaByName(frame.getTxtCariPengguna().getText());
          TabelModelPengguna tp = new TabelModelPengguna(pengguna);
          frame.getTableDataPengguna().setModel(tp);
       } else {
           isiTable();
       }
   } 

   public void isiField(int row) {
       frame.getTxtIDPengguna().setText(String.valueOf(pengguna.get(row).getId_pengguna()));
       frame.getTxtUsernamePengguna().setText(pengguna.get(row).getUsername());
       frame.getLsRolePengguna().setSelectedItem(pengguna.get(row).getRole());
   }    
}
