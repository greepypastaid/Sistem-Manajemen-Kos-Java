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
public class TabelModelTransaksi extends AbstractTableModel {
    List<Transaksi> transaksi;
    
    public TabelModelTransaksi(List<Transaksi> transaksi) {
        this.transaksi = transaksi;
    }
    
    
    @Override
    public int getColumnCount() {
        return 7;
    }
    
    @Override
    public int getRowCount() {
        return transaksi.size();
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID_Transaksi";
            case 1:
                return "ID_Penyewa";
            case 2:
                return "ID_Kamar";
            case 3:
                return "Tanggal_Sewa";
            case 4:
                return "Durasi_Bulan";
            case 5:
                return "Total";
            case 6:
                return "Status_Pembayaran";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return transaksi.get(row).getId_transaksi();
            case 1:
                return transaksi.get(row).getId_penyewa();
            case 2:
                return transaksi.get(row).getId_kamar();
            case 3:
                return transaksi.get(row).getTanggal_sewa();
            case 4:
                return transaksi.get(row).getDurasi_bulan();
            case 5:
                return transaksi.get(row).getTotal();
            case 6:
                return transaksi.get(row).getStatus_pembayaran();
            default:
                return null;
        }
    }
}
