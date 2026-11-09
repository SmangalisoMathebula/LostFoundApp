package com.lostfound.dao;

import com.lostfound.model.User;
import com.lostfound.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

   
    public boolean registerUser(User user) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO Users (full_name, email, password, contact_number) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getContactNumber());

            ps.executeUpdate();
            con.close();
            return true;

        } catch (Exception e) {
            System.out.println("Register error: " + e.getMessage());
            return false;
        }
    }

    
    public User findByEmail(String email) {
        User user = null;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM Users WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setContactNumber(rs.getString("contact_number"));
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Find user error: " + e.getMessage());
        }

        return user;
    }

    // Updates a user's details. Returns true if it worked.
    public boolean updateUser(User user) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE Users SET full_name = ?, email = ?, contact_number = ?, password = ? WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getContactNumber());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getUserId());

            ps.executeUpdate();
            con.close();
            return true;

        } catch (Exception e) {
            System.out.println("Update user error: " + e.getMessage());
            return false;
        }
    }
}