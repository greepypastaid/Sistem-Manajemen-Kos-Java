/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;

import Model.Kamar;
import java.util.List;

/**
 *
 * @author gangs
 */
public interface KamarDAO {
    public void create(Kamar kamar);
    public void update(Kamar kamar);
    public void delete(Kamar kamar);
    public List<Kamar> readAll();
    public List<Kamar> getKamarByNomor(String nomor_kamar);
}
