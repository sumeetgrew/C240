package com.sg.dvdlibrary.dao;

/**
 *
 * @author Sumeet
 */
public class DVDLibraryDaoException extends Exception{
    
    //Takes string message and calls matching constructor in
    //Exception class using super
    public DVDLibraryDaoException(String message) {
        super(message);
    }
    
    //Takes string and cause and calls matching constructor
    public DVDLibraryDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
