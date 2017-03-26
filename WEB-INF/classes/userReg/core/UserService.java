package userReg.core;

import userReg.entity.User;
import userReg.data.UserRepository;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService{
	UserRepository repository;
	
	public UserService(){
		repository = new UserRepository();
	}
	
    public boolean add(User User){
        return repository.add(User);
    }
	
	public boolean editPassword(String uid, String newPass){
        return repository.editPassword(uid, newPass);
    }
    
    public boolean edit(User User){
        return repository.edit(User);
    }
    
    public boolean remove(int UserId){
        return repository.remove(UserId);
    }
    
    public List<User> getAll(){
        return repository.getAll();
    }   
    
    public User getById(String UserId){
        return repository.getById(UserId);
    }
	public User getByName(String UserName){
        return repository.getByName(UserName);
    }
	public boolean isDuplicateUserId(String UserId){
        if(repository.getById(UserId)==null){
			return false;
		}
		return true;
    }
	
	public String validateLogin(String userId, String userPass){
		User user = repository.getById(userId);
		if(user==null)
			return "Invalid ID. User doesn't exist.";
		else if(!user.getPassword().equals(userPass))
			return "Invalid Password.";
		else return "";
	}
	/*
	public boolean isDuplicateUser(String UserName){
        if(repository.getByName(UserName)==null){
			return false;
		}
		return true;
    }
	*/
	public String validateAdd(User user, String confirmPass){
		String js = "";
		String regPattern = "[a-zA-Z]+";
		
		
		if(!user.getPassword().equals(confirmPass)){
			js="Password and Confirm Passwords do not match";
		}
		else if(isDuplicateUserId(user.getId())){
			js="Duplicate User Id";
		}
		else if(!add( user )){
			js="Something went wrong. Request failed.";
		}
		return js;
	}
	
	public boolean hasEmptyFieldsRegistration(String id, String name, String password, String confirmPassword, String admin){
		if( isEmptyField(id) || isEmptyField(name) || isEmptyField(password) || isEmptyField(confirmPassword) || isEmptyField(admin) ){
			return true;
		}
		return false;
	}
	public boolean hasEmptyFieldsLogin(String id, String password){
		if( isEmptyField(id) || isEmptyField(password) ){
			return true;
		}
		return false;
	}
	public boolean hasEmptyFieldsChangePassword(String pw, String newpw, String retypenewpw){
		if( isEmptyField(pw) || isEmptyField(newpw) || isEmptyField(retypenewpw) ){
			return true;
		}
		return false;
	}
	
	public boolean isEmptyField(String s){
		if(s==null || s.equals("") )
			return true;
		return false;
	}
	
	public boolean isPasswordMatch(String pass, String cpass){
		if(pass.equals(cpass)){
			return true;
		}
		return false;
	}
	
	
	public String validateEditPassword(String uid, String pass, String newPass, String retypeNewPass){
		String js = "";
		if( !((getById(uid).getPassword()).equals(pass)) ){
			js="Error! Wrong current password.";
		}
		else if(!newPass.equals(retypeNewPass)){
			js="Error! Retyped password does not match with new password.";
		}
		else if(!editPassword(uid, newPass)){
			js = "Error! Something went wrong. Request failed.";
		}
		else{
			js = "Password change successful!";
		}
		return js;
	}
	
	
	
	
	/*
	public String validateAdd(String UserName){
		String js = "";
		if(UserName.equals("")){
			js = "<script type=\"text/javascript\">"
						+ "alert('User cannot be empty');"
						+ "location='add-User'"
						+ "</script>"
			
						+ "<noscript type=\"text/javascript\">"
						+ "<a href='add-User'>User cannot be empty. Click to continue</a>"
						+ "</noscript>";
		}
		else if(isDuplicateUser(UserName)){
			js = "<script type=\"text/javascript\">"
						+ "alert('Duplicate User');"
						+ "location='add-User'"
						+ "</script>"
			
						+ "<noscript type=\"text/javascript\">"
						+ "<a href='add-User'>Duplicate User. Click to continue</a>"
						+ "</noscript>";
		}
		else if(!add( new User(UserName) )){
			js = "<script type=\"text/javascript\">"
						+ "alert('Something went wrong. Request failed.');"
						+ "location='add-User'"
						+ "</script>"
			
						+ "<noscript type=\"text/javascript\">"
						+ "<a href='add-User'>Something went wrong. Request failed. Click to continue</a>"
						+ "</noscript>";
		}
		return js;
	}
	*/
}