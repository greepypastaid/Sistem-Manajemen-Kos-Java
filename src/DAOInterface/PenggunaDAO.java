/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;
import Model.Pengguna;
import java.util.List;

/**
 *
 * @author gangs
 */
public interface PenggunaDAO {
    public Pengguna login(String username, String password);
    public List<Pengguna> readAll();
    public List<Pengguna> getPenggunaByName(String username);
    public boolean register(Pengguna pengguna);
    public void update(Pengguna pengguna);
    public void delete(Pengguna pengguna);
    public boolean checkUsername(String username);
}
