package com.revature.MYbrary.screens;

import com.revature.MYbrary.daos.AnnotationDAO;
import com.revature.MYbrary.models.Annotation;
import com.revature.MYbrary.models.Book;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.Logger;
import com.revature.MYbrary.util.ScreenRouter;

import java.io.BufferedReader;

public class AnnotationSelect extends Screen {

    private final UserService userService;
    private final Logger logger;

    public AnnotationSelect(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("AnnotationSelect", "/select-annotation", consoleReader, router);
        this.userService = userService;
        logger = Logger.getLogger(false);
    }

    private AnnotationDAO annotationDAO = new AnnotationDAO();

    @Override
    public void render() throws Exception {

        Book activeBook = userService.getSessionBook();

        LinkedList<Annotation> annotations = annotationDAO.findAll(activeBook.getId());
        if (annotations.size() == 0){
            System.out.println("No annotations in current book! Here, let's fix that...");
            router.navigate("/new-annotation");
        }

        System.out.printf("==== All Annotations in %s ====\n", activeBook.getTitle());

        StringBuilder consoleOutput = new StringBuilder();
        for (int i = 0; i < annotations.size(); i++) {
            consoleOutput.append(i + 1);
            consoleOutput.append(") ");
            Annotation thisAnnotation = annotations.get(i);
            consoleOutput.append(thisAnnotation.getNotes());
            consoleOutput.append("\n");
        }
        System.out.println(consoleOutput);
        System.out.print("> ");
        String userInput = consoleReader.readLine();

        try {
            Integer selectedAnnotationId = Integer.parseInt(userInput) - 1;
            Annotation userSelection = annotations.get(selectedAnnotationId);
            userService.setSessionAnnotation(userSelection.getId());
            logger.log("Annotation ID " + selectedAnnotationId + " is now the active Annotation");
            router.navigate("/annotation");
        } catch (NumberFormatException e) {
            System.out.println("Input could not be interpreted as an Integer.");
            router.navigate("/book");
        } catch (RuntimeException e) {
            System.out.println("Selection could not be found in the above list.");
            router.navigate("/book");
        }
    }
}
