package com.revature.MYbrary.screens;

import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

import static com.revature.MYbrary.util.AppState.shutdown;

public class DashboardScreen extends Screen {
    private final UserService userService;
    public DashboardScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("DashboardScreen", "/dashboard", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        // Welcome the user all personal-like
        // Display all existing library names, or a message if there are none

        System.out.print("Please indicate the library to explore, or input '0' to create a new library.\n" +
                " 1) New Library\n" +
                " 2) View Libraries\n" +
                " 3) Exit\n\n" +
                "> ");
        String userSelection = consoleReader.readLine();

        switch (userSelection) {
            case "1":
                router.navigate("/new-library");
                break;
            case "2":
                router.navigate("/libraries");
                break;
            case "3":
                System.out.println("Fare thee well!");
                shutdown();
                break;
        }
    }
}
