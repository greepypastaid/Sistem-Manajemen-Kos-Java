/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;

import Model.Penyewa;
import java.util.List;

/**
 *
 * @author gangs
 */
public interface PenyewaDAO {
    public void create(Penyewa penyewa);
    public List<Penyewa> readAll();
    public List<Penyewa> getPenyewaByName(String nama_penyewa);
}
