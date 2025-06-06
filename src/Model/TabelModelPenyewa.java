/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gangs
 */
public class TabelModelPenyewa extends AbstractTableModel {
   List<Penyewa> penyewa;
   
   public TabelModelPenyewa(List<Penyewa> penyewa) {
       this.penyewa = penyewa;
   }
   
   @Override
   public int getColumnCount() {
       return 6;
   }
   
   @Override
   public int getRowCount() {
       return penyewa.size();
   }
   
   @Override
   public String getColumnName(int column) {
       switch (column) {
           case 0:
               return "ID_Penyewa";
           case 1:
               return "Nama_Penyewa";
           case 2:
               return "Nomor_HP";
           case 3:
               return "Pekerjaan";
           case 4:
               return "Alamat";
           case 5: 
               return "Kamar";
           default: 
               return null;
       }
   }
   
   @Override
   public Object getValueAt(int row, int column) {
       switch (column) {
            case 0:
                return penyewa.get(row).getId_penyewa();
            case 1:
                return penyewa.get(row).getNama_penyewa();
            case 2:
                return penyewa.get(row).getNomor_hp();
            case 3:
                return penyewa.get(row).getPekerjaan();
            case 4:
                return penyewa.get(row).getAlamat();
            case 5:
                return penyewa.get(row).getKamar();
            default:
                return null;
        }
   }
}
