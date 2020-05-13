package com.jdbc.day1;

import java.sql.*;

public class BasicTest {
    public static void main(String[] args) throws SQLException {

        String URL = "jdbc:oracle:thin:@52.23.159.253:1521:xe";
        String username = "hr";
        String password = "hr";

        //to establish connection with a daatabase
        Connection connection = DriverManager.getConnection(URL, username, password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        //resultSet.next() --returns true and jumps to next row if there is some row with data
        while (resultSet.next()) {
            //get data from 2nd column for every row
            //2nd column is a first name info
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
        }
        resultSet.beforeFirst(); // to comeback to the beginning of result set

        //information about database
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        //some technical information about resultset
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println("JDBC DRIVER : "+databaseMetaData.getDriverName());
        System.out.println("JDBC DRIVER VERSION : "+databaseMetaData.getDriverVersion());
        System.out.println("database name : "+databaseMetaData.getDatabaseProductName());
        System.out.println("database version : "+databaseMetaData.getDatabaseProductVersion());

        System.out.println();

        System.out.println("Number of columns : "+resultSetMetaData.getColumnCount());
        System.out.println("Type of employeeID column : "+resultSetMetaData.getColumnType(1));
        System.out.println("Label of 1st column : "+resultSetMetaData.getColumnLabel(1));
        System.out.println("DataType of 1st column : "+resultSetMetaData.getColumnTypeName(1));

        System.out.println("###############################");

        //this will loop through columns
        for (int columnIndex = 1 ;columnIndex <= resultSetMetaData.getColumnCount();columnIndex++){
            System.out.printf("%-20s",resultSetMetaData.getColumnName(columnIndex));
        }


//iterate rows
        while (resultSet.next()) {
            //iterate columns

            for (int columnIndex = 1 ;columnIndex <= resultSetMetaData.getColumnCount();columnIndex++){
                System.out.printf("%-20s",resultSet.getString(columnIndex)+" ");
            }
            System.out.println("");
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
