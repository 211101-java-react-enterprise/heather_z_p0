package com.revature.MYbrary.screens;

import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;
import java.sql.SQLOutput;

import static com.revature.MYbrary.util.AppState.shutdown;

public class WelcomeScreen extends Screen {

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("WelcomeScreen", "/welcome", consoleReader, router);
    }

    @Override
    public void render() throws Exception {

        System.out.println("Welcome to...\n" +
                "  __  ____     ___                          \n" +
                " |  \\/  \\ \\   / / |                         \n" +
                " | \\  / |\\ \\_/ /| |__  _ __ __ _ _ __ _   _ \n" +
                " | |\\/| | \\   / | '_ \\| '__/ _` | '__| | | |\n" +
                " | |  | |  | |  | |_) | | | (_| | |  | |_| |\n" +
                " |_|  |_|  |_|  |_.__/|_|  \\__,_|_|   \\__, |\n" +
                "                                       __/ |\n" +
                "                                      |___/ \n" +
                "...your personal library cataloging service!\n\n" +
                "Please select an option:");
        System.out.print(" 1) Login\n" +
                         " 2) Register\n" +
                         " 3) Exit\n" +
                         "> ");

        String userSelection = consoleReader.readLine();

        switch (userSelection) {
            case "1":
                router.navigate("/login");
                break;
            case "2":
                router.navigate("/register");
                break;
            case "3":
                System.out.println("Fare thee well!");
                shutdown();
                break;
        }
    }
}
