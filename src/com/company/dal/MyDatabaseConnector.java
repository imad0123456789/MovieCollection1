package com.company.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;


public class MyDatabaseConnector {
    private static SQLServerDataSource dataSource;

    public  MyDatabaseConnector()
    {

        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName("Mov");
        dataSource.setUser("CSe21B_12");
        dataSource.setPassword("CSe21B_12");
        dataSource.setPortNumber(1433);
        dataSource.setServerName("10.176.111.31");

        //System.out.println("Connected");
    }

    public static Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }



    //test connection
    public static void main(String[] args) throws SQLException {

        MyDatabaseConnector databaseConnector = new MyDatabaseConnector();

        try  (Connection connection = databaseConnector.getConnection()){
            System.out.println("Is it Open " + !connection.isClosed());
        }
    }

     
}
