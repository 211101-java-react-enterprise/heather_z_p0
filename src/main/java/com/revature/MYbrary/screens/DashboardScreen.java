/*
 * Dashboard has all the basic features we want
 * 1) Select Active Library (New Library on this screen, as the last item in the list above "Nevermind...").
 *      - Nay, have Library Settings here and then Select Active Library in that screen, along with New and Modify Name
 * 2) View Books (redirect to 1 if the user hasn't picked one this session)
 * 3) Add New Book
*/
package com.revature.MYbrary.screens;

import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Library;
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
        Library library = userService.getSessionLibrary();
        System.out.printf("~~~~~~~~ FLAG - DashboardScreen L.29 ~~~~~~~~\n%s", library.getName());

    }
}
