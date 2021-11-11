package com.revature.MYbrary.services;

import com.revature.MYbrary.daos.AppUserDAO;
import com.revature.MYbrary.exceptions.InvalidRequestException;
//import com.revature.MYbrary.exceptions.ResourcePersistenceException;
//import com.revature.MYbrary.exceptions.AuthenticationException;
import com.revature.MYbrary.models.AppUser;

public class UserService {

    private AppUserDAO appUserDAO = new AppUserDAO();

    public boolean isUserValid(AppUser user) {
        System.out.println(user);
        if (user == null) return false;
        if (user.getPersonalName() == null || user.getPersonalName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        return user.getPassword() != null && !user.getPassword().trim().equals("");
    }

    public boolean registerNewUser(AppUser newUser) {
        if (!isUserValid(newUser)) {
            throw new InvalidRequestException("Invalid user data provided!");
        } else {
            // TODO: write logic that verifies that the new user's username and email are not already taken
            appUserDAO.save(newUser);
            return true;
        }
    }

    public AppUser authenticateUser(String username, String password) {
        // Calls findByUserNameAndPassword in AppUserDAO
        // Receives either a user record or an error
        // Have username and password from user input
        // Need from file

        // Just make sure we're not receiving garbage
        if (username == null || username.equals("") || password == null || password.equals("")) {
            throw new InvalidRequestException("Invalid credential values provided");
        }
        AppUser authenticatedUser = appUserDAO.findForLogin(username, password);
        if (authenticatedUser != null) {
            return authenticatedUser;
        }
        throw new RuntimeException("No account found with provided credentials");
    }

}
