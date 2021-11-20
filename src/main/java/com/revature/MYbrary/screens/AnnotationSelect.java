package com.revature.MYbrary.screens;

import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class AnnotationSelect extends Screen {
    public AnnotationSelect(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("AnnotationSelect", "/select-annotation", consoleReader, router);
    }

    @Override
    public void render() throws Exception {

    }
}
