package com.jdbc;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.*;

public class Main {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false";




    public static void main(String[] args) throws SQLException {
        try {
           Driver driver = new FabricMySQLDriver();
           DriverManager.registerDriver(driver);
        }
        catch (SQLException ex) {
            System.out.println("Произошла ошибка при создании драйвера.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            //    statement.execute("insert into server.user (user_id, login, password) values(5,\"Sergey\",\"123454321\")"); //добавление данных в таблицу
            //   statement.executeUpdate("update server.user set login=\"Vera\" where user_id=5"); // изменение данных в таблице по уникальному ключу
            //  statement.addBatch("insert into server.user (user_id, login, password) values(5,\"Sergey\",\"123454321\")");
          //  statement.addBatch("insert into server.user (user_id, login, password) values(5,\"Pip\",\"123454321\")");     //набор команд что бы выполнялось группами
          //  statement.addBatch("insert into server.user (user_id, login, password) values(5,\"Root\",\"123454321\")");
          //  statement.executeBatch();       // запуск всех команд в батч
          //  statement.clearBatch();         //очищаем если больше не будем использовать
            ResultSet resultSet = statement.executeQuery("select * from server.user"); // возвращает нам данные
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
