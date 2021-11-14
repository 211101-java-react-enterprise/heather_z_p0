package com.revature.MYbrary.util;

import com.revature.MYbrary.screens.WelcomeScreen;
import com.revature.MYbrary.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean appRunning;
    private final ScreenRouter router;

    public AppState() {
        appRunning = true;
        router = new ScreenRouter();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        UserService userService = new UserService();
        router.addScreen(new WelcomeScreen(consoleReader, router));
    }

    public void startup() {
        try {
            while(appRunning) {
                router.navigate("/welcome");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown() {
        appRunning = false;
    }
}
