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
public class TabelModelKamar extends AbstractTableModel {
    List<Kamar> kamar;
    
    public TabelModelKamar(List<Kamar> kamar) {
        this.kamar = kamar;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public int getRowCount() {
        return kamar.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID_Kamar";
            case 1:
                return "Nomor_Kamar";
            case 2:
                return "Tipe";
            case 3:
                return "Harga";
            case 4:
                return "Status";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return kamar.get(row).getId_kamar();
            case 1:
                return kamar.get(row).getNomor_kamar();
            case 2:
                return kamar.get(row).getTipe();
            case 3:
                return kamar.get(row).getHarga();
            case 4:
                return kamar.get(row).getStatus();
            default:
                return null;
        }
    }
}
