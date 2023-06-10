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
    private int user_id = 0;
    private int memberIdCounter = 1;
    private int userIdCounter = 1;

    String name;

    public void addUser(String phone,String name, String password){
        this.user_id = this.user_id++;
        this.name = name;
    }
    
    public class Member{ 
            private String member_id;
            private String password;
            private String phone;
            private int point;
            
            public void setMemberID(){ 
                this.member_id = generateID("M");
            }
        public String generateID(String prefix) {
               String memberID = prefix + memberIdCounter;
               memberIdCounter++;
               return memberID;
           }

    
        
    }
    
}