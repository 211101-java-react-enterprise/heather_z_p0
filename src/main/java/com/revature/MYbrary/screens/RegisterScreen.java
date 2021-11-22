/* TODO
*   - Include New Library with user registration
* */

package com.revature.MYbrary.screens;

import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Library;
import com.revature.MYbrary.services.UserService;
import com.revature.MYbrary.util.ScreenRouter;
import com.revature.MYbrary.util.Logger;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    private final UserService userService;
    private final Logger logger;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("RegisterScreen", "/register", consoleReader, router);
        this.userService = userService;
        logger = Logger.getLogger(false);
    }

    @Override
    public void render() throws Exception {
        System.out.println("Glad to have you with us! To set up your account, we'll need the following information: ");

        System.out.print("NAME: ");
        String personalName = consoleReader.readLine();
        System.out.print("EMAIL: ");
        String email = consoleReader.readLine();
        System.out.print("USERNAME: ");
        String username = consoleReader.readLine();
        System.out.print("PASSWORD: ");
        String password = consoleReader.readLine();
        System.out.print("YOUR LIBRARY NAME: ");
        String libraryName = consoleReader.readLine();

        AppUser newUser = new AppUser(personalName, email, username, password);
        Library newLibrary = new Library(libraryName, "orphan");

        try {
            userService.registerNewUser(newUser, newLibrary);
            logger.log("NEW USER REGISTERED: " + newUser.getUsername());
            System.out.println("Your user account has been successfully registered!\n" +
                    "Redirecting you to the Login page...\n");
            router.navigate("/login");
        } catch (Exception e) {
            System.out.println("Could not register user as input!");
            router.navigate("/welcome");
        }

    }
}
