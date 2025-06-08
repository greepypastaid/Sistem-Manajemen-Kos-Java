/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;
import Model.Pengguna;

/**
 *
 * @author gangs
 */
public interface PenggunaDAO {
    public Pengguna login(String username, String password);
    public boolean register(Pengguna pengguna);
    public void edit(Pengguna pengguna);
    public void delete(Pengguna pengguna);
    public boolean checkUsername(String username);
}
