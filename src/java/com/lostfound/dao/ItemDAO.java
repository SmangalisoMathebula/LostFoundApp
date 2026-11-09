package com.lostfound.dao;

import com.lostfound.model.Item;
import com.lostfound.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemDAO {

   
    public boolean addItem(Item item) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO LostFoundItems (user_id, item_name, item_type, description, "
                    + "location, date_reported, report_type, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, 'Unclaimed')";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, item.getUserId());
            ps.setString(2, item.getItemName());
            ps.setString(3, item.getItemType());
            ps.setString(4, item.getDescription());
            ps.setString(5, item.getLocation());
            ps.setString(6, item.getDateReported());
            ps.setString(7, item.getReportType());

            ps.executeUpdate();
            con.close();
            return true;

        } catch (Exception e) {
            System.out.println("Add item error: " + e.getMessage());
            return false;
        }
    }

  
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> list = new ArrayList<Item>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM LostFoundItems ORDER BY item_id DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getInt("item_id"));
                item.setUserId(rs.getInt("user_id"));
                item.setItemName(rs.getString("item_name"));
                item.setItemType(rs.getString("item_type"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setDateReported(rs.getString("date_reported"));
                item.setReportType(rs.getString("report_type"));
                item.setStatus(rs.getString("status"));
                list.add(item);
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Get all items error: " + e.getMessage());
        }

        return list;
    }

   
    public ArrayList<Item> getUnclaimedItems() {
        ArrayList<Item> list = new ArrayList<Item>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM LostFoundItems WHERE status = 'Unclaimed' ORDER BY item_id DESC";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getInt("item_id"));
                item.setUserId(rs.getInt("user_id"));
                item.setItemName(rs.getString("item_name"));
                item.setItemType(rs.getString("item_type"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setDateReported(rs.getString("date_reported"));
                item.setReportType(rs.getString("report_type"));
                item.setStatus(rs.getString("status"));
                list.add(item);
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Get unclaimed items error: " + e.getMessage());
        }

        return list;
    }

   
    public ArrayList<Item> searchItems(String field, String keyword) {
        ArrayList<Item> list = new ArrayList<Item>();

        try {
            Connection con = DBConnection.getConnection();

            String sql;
            if (field != null && field.equals("location")) {
                sql = "SELECT * FROM LostFoundItems WHERE location LIKE ? ORDER BY item_id DESC";
            } else {
                sql = "SELECT * FROM LostFoundItems WHERE item_name LIKE ? ORDER BY item_id DESC";
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");   // % means "anything before or after"
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getInt("item_id"));
                item.setUserId(rs.getInt("user_id"));
                item.setItemName(rs.getString("item_name"));
                item.setItemType(rs.getString("item_type"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setDateReported(rs.getString("date_reported"));
                item.setReportType(rs.getString("report_type"));
                item.setStatus(rs.getString("status"));
                list.add(item);
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Search error: " + e.getMessage());
        }

        return list;
    }

   
    public Item getItemById(int id) {
        Item item = null;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM LostFoundItems WHERE item_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new Item();
                item.setItemId(rs.getInt("item_id"));
                item.setUserId(rs.getInt("user_id"));
                item.setItemName(rs.getString("item_name"));
                item.setItemType(rs.getString("item_type"));
                item.setDescription(rs.getString("description"));
                item.setLocation(rs.getString("location"));
                item.setDateReported(rs.getString("date_reported"));
                item.setReportType(rs.getString("report_type"));
                item.setStatus(rs.getString("status"));
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Get item error: " + e.getMessage());
        }

        return item;
    }

    
    public boolean updateItem(Item item) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE LostFoundItems SET item_name = ?, item_type = ?, description = ?, "
                    + "location = ?, date_reported = ?, report_type = ?, status = ? WHERE item_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, item.getItemName());
            ps.setString(2, item.getItemType());
            ps.setString(3, item.getDescription());
            ps.setString(4, item.getLocation());
            ps.setString(5, item.getDateReported());
            ps.setString(6, item.getReportType());
            ps.setString(7, item.getStatus());
            ps.setInt(8, item.getItemId());

            ps.executeUpdate();
            con.close();
            return true;

        } catch (Exception e) {
            System.out.println("Update item error: " + e.getMessage());
            return false;
        }
    }

   
    public boolean markAsClaimed(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "UPDATE LostFoundItems SET status = 'Claimed' WHERE item_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            con.close();
            return true;

        } catch (Exception e) {
            System.out.println("Claim error: " + e.getMessage());
            return false;
        }
    }

   
    public boolean deleteItem(int id) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "DELETE FROM LostFoundItems WHERE item_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            con.close();
            return true;

        } catch (Exception e) {
            System.out.println("Delete error: " + e.getMessage());
            return false;
        }
    }

    
    public int countAll() {
        int count = 0;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT COUNT(*) FROM LostFoundItems";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Count error: " + e.getMessage());
        }

        return count;
    }

   
    public int countByStatus(String status) {
        int count = 0;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT COUNT(*) FROM LostFoundItems WHERE status = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

            con.close();

        } catch (Exception e) {
            System.out.println("Count error: " + e.getMessage());
        }

        return count;
    }
}