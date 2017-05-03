/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datalayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maxim
 */
public class MysqlDAO {

    private static MysqlDAO instance;

    private MysqlDAO() {

    }

    public static MysqlDAO getInstance() {
        if (instance == null) {
            return new MysqlDAO();
        } else {
            return instance;
        }
    }
    
    //Create connection object with MySQL JDBC driver
    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/bandplanner", "bandplanner", "iZzpgiWoxPnt8jQ4X");
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //Close connection if exists
    public void closeConnection(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
