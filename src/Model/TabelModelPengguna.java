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
public class TabelModelPengguna extends AbstractTableModel {
    List<Pengguna> pengguna;

    public TabelModelPengguna(List<Pengguna> pengguna) {
        this.pengguna = pengguna;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return pengguna.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID_Pengguna";
            case 1:
                return "Username";
            case 2:
                return "Role";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return pengguna.get(row).getId_pengguna();
            case 1:
                return pengguna.get(row).getUsername();
            case 2:
                return pengguna.get(row).getRole();
            default:
                return null;
        }
    }
}
