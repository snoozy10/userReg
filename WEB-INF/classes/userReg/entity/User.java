package userReg.entity;

import java.util.List;


public class User{
    String id;
    String name;
    String password;
	boolean admin;
    
    public User(String id, String name, String password, boolean admin) {
	    this.id = id;
        this.name = name;
		this.password = password;
		this.admin = admin;
    }
	
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
	public void setPassword(String password) {
        this.password = password;
    }
	public void setAdmin(boolean admin) {
        this.admin = admin;
    }
	

    
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
	public String getPassword() {
        return password;
    }
	public boolean getAdmin() {
        return admin;
    }
	public String getAdminString() {
        return admin ? "Admin":"User";
    }
    
}