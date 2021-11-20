package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.AnnotationDAO;
import com.revature.MYbrary.models.Annotation;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class AnnotationNew extends Screen {
    private final UserService userService;
    public AnnotationNew(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("AnnotationNew", "/new-annotation", consoleReader, router);
        this.userService = userService;
    }

    private AnnotationDAO annotationDAO = new AnnotationDAO();

    @Override
    public void render() throws Exception {
        System.out.println("Please enter the details for your new Annotation. ");

        System.out.print("STARTING PAGE: ");
        Integer startingPage = Integer.parseInt(consoleReader.readLine());
        System.out.print("STARTING WORDS: ");
        String startingWords = consoleReader.readLine();
        System.out.print("ENDING PAGE: ");
        Integer endingPage = Integer.parseInt(consoleReader.readLine());
        System.out.print("ENDING WORDS: ");
        String endingWords = consoleReader.readLine();
        System.out.print("NOTES: ");
        String notes = consoleReader.readLine();

        Annotation newAnnotation = new Annotation(startingWords, startingPage, endingWords, endingPage, notes, userService.getSessionBook().getId());

        try {
            Annotation createdAnnotation = annotationDAO.save(newAnnotation);
            userService.setSessionAnnotation(createdAnnotation.getId());
            router.navigate("/book");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
