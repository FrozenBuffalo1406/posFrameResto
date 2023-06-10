/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas_pbo;
import java.sql.*;
import static uas_pbo.DBConnector.*;
/**
 *
 * @author User
 */
    public class User {
    private static int user_id = 0;
    private String phone;
    String name;
    private String password;
    private int point;

    public User(String phone,String name, String password){
        this.phone = phone;
        this.name = name;
        this.password = password;
    }
}