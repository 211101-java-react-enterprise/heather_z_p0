package com.revature.MYbrary.util;

import com.revature.MYbrary.daos.AnnotationDAO;
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
// System.out.println("~~~~ FLAG - FileName - L## ~~~~\n" + variable); //
/////////////////////////////////////////////////////////////////////////

public class AppState {

    private final Logger logger;
    private static boolean appRunning; //
    private final ScreenRouter router;

    public AppState() {
        logger = Logger.getLogger(false);
        appRunning = true;
        router = new ScreenRouter();

        logger.log("Initializing via AppState...");
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        AppUserDAO userDAO = new AppUserDAO();
        LibraryDAO libraryDAO = new LibraryDAO();
        BookDAO bookDAO = new BookDAO();
        AnnotationDAO annotationDAO = new AnnotationDAO();
        UserService userService = new UserService(userDAO, libraryDAO, bookDAO, annotationDAO);
        router.addScreen(new WelcomeScreen(consoleReader, router));
        router.addScreen(new RegisterScreen(consoleReader, router, userService));
        router.addScreen(new LoginScreen(consoleReader, router, userService));
        router.addScreen(new DashboardScreen(consoleReader, router, userService));

        router.addScreen(new LibrarySelect(consoleReader, router, userService));
        router.addScreen(new LibraryNew(consoleReader, router, userService));

        router.addScreen(new BookScreen(consoleReader, router, userService));
        router.addScreen(new BookSelect(consoleReader, router, userService));
        router.addScreen(new BookNew(consoleReader, router, userService));
        router.addScreen(new BookPageUpdate(consoleReader, router, userService));

        router.addScreen(new AnnotationScreen(consoleReader, router, userService));
        router.addScreen(new AnnotationSelect(consoleReader, router, userService));
        router.addScreen(new AnnotationNew(consoleReader, router, userService));

        router.addScreen(new LoanScreen(consoleReader, router, userService));
    }

    public void startup() {
        logger.log("Placing user in router flow...");
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
