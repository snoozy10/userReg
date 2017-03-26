package userReg.data;

import userReg.entity.User;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserRepository {

    public boolean add(User user) {
        try {
			int admin = user.getAdmin() ? 1:0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "");

            PreparedStatement stmt = con.prepareStatement("INSERT INTO Users(Id, Name, Password, Admin) VALUES(?, ?, ?, ?)");
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, admin);

            int result = stmt.executeUpdate();
            con.close();
            
            if (result != 0) {
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean edit(User user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "");

            PreparedStatement stmt = con.prepareStatement("UPDATE Users SET Name=? WHERE Id=?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getId());

            int result = stmt.executeUpdate();
            con.close();

            if (result != 0) {
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }
	
	public boolean editPassword(String UserId, String NewPass) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "");

            PreparedStatement stmt = con.prepareStatement("UPDATE Users SET Password=? WHERE Id=?");
            stmt.setString(1, NewPass);
            stmt.setString(2, UserId);

            int result = stmt.executeUpdate();
            con.close();

            if (result != 0) {
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }
	
    public boolean remove(int UserId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "");

            PreparedStatement stmt = con.prepareStatement("DELETE from Users WHERE Id=?");
            stmt.setInt(1, UserId);

            int result = stmt.executeUpdate();
            con.close();

            if (result != 0) {
                return true;
            }
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public List<User> getAll() {
        List<User> UserList = new ArrayList<User>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/userdb", "root", "");

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Users");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
				boolean admin = (rs.getInt("Admin")==1 ? true:false);
                User user = new User(rs.getString("Id"), rs.getString("Name"), rs.getString("Password"), admin);
                UserList.add(user);
            }
            con.close();

        } catch (Exception e) {

        }
        return UserList;
    }

    public User getById(String UserId) {
        User user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/userdb", "root", "");

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Users WHERE Id=?");
			stmt.setString(1, UserId);
            ResultSet rs = stmt.executeQuery();

            
            if (rs.next()) {
                boolean admin = (rs.getInt("Admin")==1 ? true:false);
                user = new User(rs.getString("Id"), rs.getString("Name"), rs.getString("Password"), admin);
            }
            con.close();

        } catch (Exception e) {

        }
        return user;
    }
	
	public User getByName(String UserName) {
        User user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/userdb", "root", "");

            PreparedStatement stmt = con.prepareStatement("SELECT Id, Name FROM Users WHERE Name=?");
            stmt.setString(1, UserName);
			ResultSet rs = stmt.executeQuery();

            
            if (rs.next()) {
                boolean admin = (rs.getInt("Admin")==1 ? true:false);
                user = new User(rs.getString("Id"), rs.getString("Name"), rs.getString("Password"), admin);
            }
            con.close();

        } catch (Exception e) {

        }
        return user;
    }
}
