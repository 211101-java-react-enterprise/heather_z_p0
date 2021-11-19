// Qi pointed out that custom exceptions make the try/catch block more stable

package com.revature.MYbrary.exceptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Incorrect credentials provided. Could not authenticate.");
    }
}
