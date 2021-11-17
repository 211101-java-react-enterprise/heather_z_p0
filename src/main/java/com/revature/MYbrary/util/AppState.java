package com.revature.MYbrary.util;

import com.revature.MYbrary.daos.AppUserDAO;
import com.revature.MYbrary.services.UserService;

import com.revature.MYbrary.screens.WelcomeScreen;
import com.revature.MYbrary.screens.RegisterScreen;
import com.revature.MYbrary.screens.LoginScreen;
import com.revature.MYbrary.screens.DashboardScreen;
import com.revature.MYbrary.screens.LibraryScreen;
import com.revature.MYbrary.screens.LibraryNew;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

////////////////////// DEBUGGING COMMENT TEMPLATE ///////////////////////
// System.out.println("~~~~ FLAG - LibraryDAO - L63 ~~~~\n" + userId); //
/////////////////////////////////////////////////////////////////////////

public class AppState {

    private static boolean appRunning;
    private final ScreenRouter router;

    public AppState() {
        appRunning = true;
        router = new ScreenRouter();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        AppUserDAO userDAO = new AppUserDAO();
        UserService userService = new UserService(userDAO);
        router.addScreen(new WelcomeScreen(consoleReader, router));
        router.addScreen(new RegisterScreen(consoleReader, router, userService));
        router.addScreen(new LoginScreen(consoleReader, router, userService));
        router.addScreen(new DashboardScreen(consoleReader, router, userService));
        router.addScreen(new LibraryScreen(consoleReader, router, userService));
        router.addScreen(new LibraryNew(consoleReader, router, userService));
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
