package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.BookDAO;
import com.revature.MYbrary.models.Annotation;
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
        Annotation activeAnnotation = userService.getSessionAnnotation();

        System.out.println(activeAnnotation.getStartingWords() + " ... " + activeAnnotation.getEndingWords());
        System.out.println(activeAnnotation.getStartingPage() + "-" + activeAnnotation.getEndingPage());
        System.out.println(activeAnnotation.getNotes());
        System.out.print("\n\n");

        System.out.print("1) Go back\n> ");
        String userSelection = consoleReader.readLine();

        switch(userSelection) {
            case "1":
            default:
                router.navigate("/book");
        }
    }
}
