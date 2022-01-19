/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.comandasinterfaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author juanl
 */
public class Conexion {
    
     
    private static Connection conexion;
    
    static{
        String url="jdbc:mysql://localhost:3306/comandas?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user="root";
        String pass="";
        
        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión realizada con exito!!!");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

    public static Connection getConexion() {
        return conexion;
    }
}
