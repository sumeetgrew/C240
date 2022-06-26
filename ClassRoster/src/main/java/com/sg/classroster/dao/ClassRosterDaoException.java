/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;

/**
 *
 * @author Sumeet
 */
public class ClassRosterDaoException extends Exception{
    
    //Takes string message and calls matching constructor in
    //Exception class using super
    public ClassRosterDaoException(String message) {
        super(message);
    }
    
    //Takes string and cause and calls matching constructor
    public ClassRosterDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
