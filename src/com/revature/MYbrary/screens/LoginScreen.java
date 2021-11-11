package com.revature.MYbrary.screens;

import com.revature.MYbrary.exceptions.AuthenticationException;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class LoginScreen extends Screen {
    private final UserService userService;
    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LoginScreen", "/login", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("Please provide your credentials to log into your account.");
        System.out.print("Username > ");
        String username = consoleReader.readLine();
        System.out.print("Password > ");
        String password = consoleReader.readLine();

        try {
            AppUser authenticatedUser = userService.authenticateUser(username, password);
            System.out.println("Credentials validated, matching user found: " + authenticatedUser);
            // TODO navigate to dashboard
        } catch (AuthenticationException e) {
            System.out.println("Incorrect credentials provided! No matching user account found.");
        }
    }
}
