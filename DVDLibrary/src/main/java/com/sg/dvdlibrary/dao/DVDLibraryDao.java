/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;
import com.sg.dvdlibrary.dto.DVD;
import java.util.List;
/**
 *
 * @author Sumeet
 */
public interface DVDLibraryDao {
    /**
     * Adds the given DVD to the roster and associates it with the given 
     * student id. If there is already a student associated with the given 
     * student id it will return that student object, otherwise it will 
     * return null.
     * 
     * @param studentId id with which student is to be associated
     * @param student student to be added to the roster
     * @return the DVD object previously associated with the given  
 student id if it exists, null otherwise
     * @throws DVDLibraryDaoException
     */
    DVD addDVD(String dvdTitle, DVD dvd)
     throws DVDLibraryDaoException;

    /**
     * Returns a List of all Students on the roster. 
     * 
     * @return DVD List containing all students on the roster.
     * @throws DVDLibraryDaoException
     */
    List<DVD> getAllDVDs()
     throws DVDLibraryDaoException;

    /**
     * Returns the student object associated with the given student id.
     * Returns null if no such student exists
     * 
     * @param studentId ID of the student to retrieve
     * @return the DVD object associated with the given student id,  
 null if no such student exists
     * @throws DVDLibraryDaoException
     */
    DVD getDVD(String dvdId)
     throws DVDLibraryDaoException;
    
    DVD getDVDTitle(String dvdTitle)
     throws DVDLibraryDaoException;

    /**
     * Removes from the roster the student associated with the given id. 
     * Returns the student object that is being removed or null if 
     * there is no student associated with the given id
     * 
     * @param studentId id of student to be removed
     * @return DVD object that was removed or null if no student 
 was associated with the given student id
     * @throws DVDLibraryDaoException
     */
    DVD removeDVD(String dvdTitle)
     throws DVDLibraryDaoException;
    
    DVD editDVD(String dvdTitle, DVD dvd)
     throws DVDLibraryDaoException;
}
