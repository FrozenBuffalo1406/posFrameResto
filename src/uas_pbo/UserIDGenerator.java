/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas_pbo;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import static uas_pbo.DBConnector.*;
import java.lang.String;

public class UserIDGenerator {
    String UserID;
    // Database connection details
   
    private static final String SELECT_QUERY = "SELECT MAX(CAST(SUBSTRING(user_id, ?) AS UNSIGNED)) FROM users WHERE user_id LIKE ?";
    private static final String INSERT_QUERY = "INSERT INTO users (user_id, name, email, password) VALUES (?, ?, ?, ?)";

    // Prefixes and their corresponding counts
    private Map<String, Integer> prefixCounts;
    
    // Maximum count for each prefix
    private int maxCount;
    private String String;

    public UserIDGenerator() {
        prefixCounts = new HashMap<>();
        maxCount = 999; // Set the maximum count value
    }
    public void setUserID(String userID, String prefix){
        this.UserID = generateUserID(prefix);
    }
    
    public String generateUserID(String prefix) {
        // Get the count for the current prefix
        int count = prefixCounts.getOrDefault(prefix, 0);
        
        // Check if the count exceeds the maximum count
        if (count > maxCount) {
            throw new RuntimeException("Maximum count exceeded for prefix: " + prefix);
        }
        
        // Increment the count for the current prefix
        count++;
        
        // Update the count for the current prefix
        prefixCounts.put(prefix, count);
        
        // Generate the userID
        String userID = prefix + count;
        
        return userID;
    }

    public boolean isPrefixUsed(String prefix) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            statement.setInt(1, prefix.length() + 1);
            statement.setString(2, prefix + "%");
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int maxCount = resultSet.getInt(1);
                return maxCount > 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }
}
