/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helper;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class KoneksiDB {
    static Connection con;
    
    public static Connection getConnection(){
        if (con== null){
            MysqlDataSource data=new MysqlDataSource();
            data.setDatabaseName("db_koskontrakan");
            data.setUser("root");
            data.setPassword("@Bull12345");
            try{
                con=data.getConnection();
                System.out.println("koneksi berhasil");
            }catch(SQLException e){
                System.out.println("tidak connect");
            }
        }return con;
    }
}
