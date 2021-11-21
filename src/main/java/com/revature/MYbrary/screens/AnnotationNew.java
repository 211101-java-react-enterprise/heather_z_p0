package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.AnnotationDAO;
import com.revature.MYbrary.models.Annotation;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.Logger;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class AnnotationNew extends Screen {

    private final UserService userService;
    private final Logger logger;

    public AnnotationNew(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("AnnotationNew", "/new-annotation", consoleReader, router);
        this.userService = userService;
        logger = Logger.getLogger(false);
    }

    private AnnotationDAO annotationDAO = new AnnotationDAO();

    @Override
    public void render() throws Exception {
        System.out.println("Please enter the details for your new Annotation. ");

        System.out.print("STARTING PAGE: ");
        String startingPageString = consoleReader.readLine();
        System.out.print("STARTING WORDS: ");
        String startingWords = consoleReader.readLine();
        System.out.print("ENDING PAGE: ");
        String endingPageString = consoleReader.readLine();
        System.out.print("ENDING WORDS: ");
        String endingWords = consoleReader.readLine();
        System.out.print("NOTES: ");
        String notes = consoleReader.readLine();

        try {
            Integer startingPage = Integer.parseInt(startingPageString);
            Integer endingPage = Integer.parseInt(endingPageString);

            Annotation newAnnotation = new Annotation(startingWords, startingPage, endingWords, endingPage, notes, userService.getSessionBook().getId());
            Annotation createdAnnotation = annotationDAO.save(newAnnotation);
            userService.setSessionAnnotation(createdAnnotation.getId());

            logger.log("Annotation ID " + createdAnnotation.getId() + " created in " + userService.getSessionBook().getTitle());
            router.navigate("/book");
        } catch (NumberFormatException e) {
            System.out.println("Could not parse input. Starting Page and Ending Page must be integers.");
            router.navigate("/book");
        }



        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
