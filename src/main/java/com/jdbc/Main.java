package com.jdbc;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.xml.transform.Result;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.sql.*;
import java.util.LinkedList;

public class Main {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/server?useSSL=no";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public static void main(String[] args) {
        LinkedList<String> query = new LinkedList<>();
        query.add("SELECT COUNT(user_id) FROM server.users");
        int num_of_users=0;
        String Front_Password="123454321", Front_Login="Vera";
        String Login_Server="";
        String Password_Server="";
        boolean LogIn = false;

        try {
            // открываем соединение с базой данных
            con = DriverManager.getConnection(url, user, password);

            stmt = con.createStatement();

            // очередь запросов к бд

            rs = stmt.executeQuery(query.get(0));
            while (rs.next()) {
                String temp = rs.getString(1);
                num_of_users = Integer.parseInt(temp);
            }
            System.out.println(num_of_users);



                            /*  АВТОРИЗАЦИЯ  */
            for (int i=1; i<=num_of_users;i++) {                                                                                //если пароль и логин верный
                query.add( "SELECT * FROM server.users WHERE user_id = " + i);                                                   //переменная LogIn становится true
                rs = stmt.executeQuery(query.get(i));
                while (rs.next()) {
                     Login_Server = rs.getString(2);
                     Password_Server = rs.getString(3);
                }
                    if ((Login_Server.equals(Front_Login)) && (Password_Server.equals(Front_Password))){
                       LogIn = true;
                       break;
                    }
                    else
                        LogIn= false;
            }
            System.out.println(LogIn);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and result set here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }
}
