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
     * DVD id. If there is already a DVD associated with the given 
     * DVD id it will return that DVD object, otherwise it will 
     * return null.
     * 
     * @param dvd id with which dvd is to be associated
     * @param DVD dvd to be added to the library
     * @return the DVD object previously associated with the given  
 student id if it exists, null otherwise
     * @throws DVDLibraryDaoException
     */
    DVD addDVD(String dvdId, DVD dvd)
     throws DVDLibraryDaoException;

    /**
     * Returns a List of all DVDs in the library. 
     * 
     * @return DVD List containing all dvds on the roster.
     * @throws DVDLibraryDaoException
     */
    List<DVD> getAllDVDs()
     throws DVDLibraryDaoException;

    /**
     * Returns the dvd object associated with the given dvd id.
     * Returns null if no such student exists
     * 
     * @param dvd ID of the dvd to retrieve
     * @return the DVD object associated with the given dvd id,  
 null if no such dvd exists
     * @throws DVDLibraryDaoException
     */
    
    //Gets the current DVD with the DVD id
    DVD getDVD(String dvdId)
     throws DVDLibraryDaoException;
    
    //Gets the DVD with title only
    DVD getDVDTitleOnly(String dvdTitle)
     throws DVDLibraryDaoException;
    
    //Gets DVD with title and year
    DVD getDVDTitle(String dvdTitle, String dvdYear)
     throws DVDLibraryDaoException;
    
    //Counts numbeer of DVDs with the same name
    int countDVD(String dvdTitle)
     throws DVDLibraryDaoException;
    /**
     * Removes from the roster the dvd associated with the given id. 
     * Returns the dvd object that is being removed or null if 
     * there is no dvd associated with the given id
     * 
     * @param dvd id of student to be removed
     * @return DVD object that was removed or null if no student 
 was associated with the given student id
     * @throws DVDLibraryDaoException
     */
    DVD removeDVD(String dvdId)
     throws DVDLibraryDaoException;
    
    //Edits DVD with ID and DVD object
    DVD editDVD(String dvdId, DVD dvd)
     throws DVDLibraryDaoException;
}
