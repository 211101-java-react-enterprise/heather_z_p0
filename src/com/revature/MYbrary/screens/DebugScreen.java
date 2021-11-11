package com.revature.MYbrary.screens;

import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class DebugScreen extends Screen {
    private final UserService userService;
    public DebugScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("DebugScreen", "/debug", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {

    }
}
