package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class AnnotationScreen extends Screen{
        private final UserService userService;
        public AnnotationScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
            super("AnnotationScreen", "/annotation", consoleReader, router);
            this.userService = userService;
        }

        private BookDAO bookDAO = new BookDAO();

    @Override
    public void render() throws Exception {
        // List them all as 1) p.### - startingWords ... endingWords
        // So we get all the Annotations
    }
}
