package com.revature.MYbrary.screens;

import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {
    private final UserService userService;
    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("RegisterScreen", "/register", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("Glad to have you with us! To set up your account, we'll need the following information: ");

        System.out.print("USERNAME: ");
        String username = consoleReader.readLine();
        System.out.print("PASSWORD: ");
        String password = consoleReader.readLine();
        System.out.print("EMAIL: ");
        String email = consoleReader.readLine();
        System.out.print("NAME: ");
        String personalName = consoleReader.readLine();

        AppUser newUser = new AppUser(personalName, email, username, password);

        try {
            userService.registerNewUser(newUser);
            System.out.println("Your user account has been successfully registered!\n" +
                    "Redirecting you to the Login page...\n\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
