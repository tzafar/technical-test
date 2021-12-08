package com.branthill.techinicaltask.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class H2ConnectionProvider {
    private static Connection connection;

    private H2ConnectionProvider(){
        if (this.connection == null){
            try {
                connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "password");
            } catch (Exception e){
            }
        }
    }

    public static Connection getDatabaseConnection(){
        if (connection == null){
            new H2ConnectionProvider();
        }
        return connection;
    }
}
