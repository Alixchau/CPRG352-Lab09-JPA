package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

public class UserService {

    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }

    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        return user;
    }
    
    public void insert(String email, boolean active, String firstName, String lastName, String password, int role_id) throws Exception {
        User user = new User(email, active, firstName, lastName, password);//create user, constructor without role 
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(role_id); //create role by its primary key role_id
        user.setRole(role); // update user's role attribute by setRole method in user class. role is the reference key to role table
        
        UserDB userDB = new UserDB();
        userDB.insert(user); //update user table
    }

    public void update(String email, boolean active, String firstName, String lastName, String password, int role_id) throws Exception {
       
        
        UserDB userDB = new UserDB();
        User user = userDB.get(email); //retrieve the user by PK
        user.setActive(active); //update user object
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(role_id); //retrieve the role by PK
        user.setRole(role); //Update user's role. link user table and role table
        
        userDB.update(user);
    }

    public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        userDB.delete(user);
    }
}
