/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package CRUDMVCDAO;

import Helper.KoneksiDB;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import javax.swing.SwingUtilities;

/*
 * @author Greepypastaid
 */
public class CRUDMVCDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KoneksiDB.getConnection();
        
//        // Gunakan SwingUtilities.invokeLater untuk memastikan UI dibuat di EDT
//        SwingUtilities.invokeLater(() -> {
//            // Buat instance FormMahasiswa dan tampilkan
//            FormMahasiswa form = new FormMahasiswa();
//            form.setVisible(true);
//        });
    }
    
}
