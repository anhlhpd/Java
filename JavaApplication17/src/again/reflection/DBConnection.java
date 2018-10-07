/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package again.reflection;

import java.sql.Connection;

/**
 *
 * @author Phuong Anh
 */
public class DBConnection {
    private Connection cnn = null;
    private final String CONNECTION_URL = "jd";
    private final String DATABSE = "";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    private static DBConnection instances;
    
    public static DBConnection getInstances(){
        if (null == instances) {
            instances = new DBConnection();
        }
        return instances;
    }
    
    public void closeConnection(){}
}
