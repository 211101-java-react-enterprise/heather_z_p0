/* TODO
 *  - Persist the Library
 *  - Set Active Library
 */

package com.revature.MYbrary.services;

import com.revature.MYbrary.util.LinkedList;
import com.revature.MYbrary.util.List;
import com.revature.MYbrary.daos.LibraryDAO;
import com.revature.MYbrary.exceptions.ResourcePersistenceException;
import com.revature.MYbrary.models.AppUser;
import com.revature.MYbrary.models.Library;

import java.io.BufferedReader;

public class LibraryService {

    private final LibraryDAO libraryDAO;
    private Library activeLibrary = null; // This isn't right, but I'll fix it when it breaks

    public LibraryService(LibraryDAO libraryDAO) {
        this.libraryDAO = libraryDAO;
    }



}
