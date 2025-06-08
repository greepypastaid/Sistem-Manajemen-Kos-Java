/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;

import Model.Transaksi;
import java.util.List;

/**
 *
 * @author gangs
 */
public interface TransaksiDAO {
    public void insert(Transaksi transaksi);
    public void update(Transaksi transaksi);
    public void delete(Transaksi transaksi);
    public List<Transaksi> readAll();
    public List<Transaksi> getTransaksiById(int id_transaksi);
}
