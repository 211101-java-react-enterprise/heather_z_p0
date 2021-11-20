package com.revature.MYbrary.screens;

import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class AnnotationNew extends Screen {
    public AnnotationNew(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("AnnotationNew", "/New-annotation", consoleReader, router);
    }

    @Override
    public void render() throws Exception {

    }
}
