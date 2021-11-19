package com.revature.MYbrary.util;

import com.revature.MYbrary.daos.AppUserDAO;
import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.screens.*;
import com.revature.MYbrary.services.UserService;

import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;

////////////////////// DEBUGGING COMMENT TEMPLATE ///////////////////////
// System.out.println("~~~~ FLAG - LibraryDAO - L63 ~~~~\n" + userId); //
/////////////////////////////////////////////////////////////////////////

public class AppState {

    private static boolean appRunning; //
    private final ScreenRouter router;

    public AppState() {
        appRunning = true;
        router = new ScreenRouter();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        AppUserDAO userDAO = new AppUserDAO();
        LibraryDAO libraryDAO = new LibraryDAO();
        BookDAO bookDAO = new BookDAO();
        UserService userService = new UserService(userDAO, libraryDAO, bookDAO);
        router.addScreen(new WelcomeScreen(consoleReader, router));
        router.addScreen(new RegisterScreen(consoleReader, router, userService));
        router.addScreen(new LoginScreen(consoleReader, router, userService));
        router.addScreen(new DashboardScreen(consoleReader, router, userService));
        router.addScreen(new LibrarySelect(consoleReader, router, userService));
        router.addScreen(new LibraryNew(consoleReader, router, userService));
        router.addScreen(new BookScreen(consoleReader, router, userService));
        router.addScreen(new BookSelect(consoleReader, router, userService));
        router.addScreen(new BookNew(consoleReader, router, userService));
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
