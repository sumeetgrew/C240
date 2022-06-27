/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;
import com.sg.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Sumeet
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    
    private Map<String, DVD> library = new HashMap<>(); //<Title, DVD Object>
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    
    //Read text into a student object
    private DVD unmarshallDVD(String dvdAsText){
        // studentAsText is expecting a line read in from our file.
        // For example, it might look like this:
        // 1234::Ada::Lovelace::Java-September1842
        //
        // We then split that line on our DELIMITER - which we are using as ::
        // Leaving us with an array of Strings, stored in studentTokens.
        // Which should look like this:
        // ______________________________________
        // |    |   |        |                  |
        // |1234|Ada|Lovelace|Java-September1842|
        // |    |   |        |                  |
        // --------------------------------------
        //  [0]  [1]      [2]     [3]     [4]    [5]   [6]
        //  ID::Title::Director::Studio::Release::MPAA:UserRating
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, the student Id is in index 0 of the array.
        String dvdId = dvdTokens[0];

        // Which we can then use to create a new DVD object to satisfy
        // the requirements of the DVD constructor.
        DVD dvdFromFile = new DVD(dvdId);

        // However, there are 3 remaining tokens that need to be set into the
        // new student object. Do this manually by using the appropriate setters.
        
        dvdFromFile.setTitle(dvdTokens[1]);
        
        // Index 1 - Director
        dvdFromFile.setDirector(dvdTokens[2]);

        // Index 2 - Studio
        dvdFromFile.setStudio(dvdTokens[3]);
        
        //Index 3 - Release
        dvdFromFile.setRelease(dvdTokens[4]);
        
        //Index 4 - MPAA Rating
        dvdFromFile.setMPAARating(dvdTokens[5]);
        
        //Index 5 - User Rating
        dvdFromFile.setUserRating(dvdTokens[6]);

        // We have now created a student! Return it!
        return dvdFromFile;
    }
    
    //Reads roster file into memory (student object)
    private void loadLibrary() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentStudent holds the most recent student unmarshalled
        DVD currentDVD;
        // Go through ROSTER_FILE line by line, decoding each line into a 
        // DVD object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVD
            currentDVD = unmarshallDVD(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            library.put(currentDVD.getId(), currentDVD);
        }
        // close scanner
        scanner.close();
    }
    
    //Creates a string for marshalling from a given student
    private String marshallDVD(DVD aDVD){
        // We need to turn a DVD object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // Start with the DVD ID, since that's supposed to be first.
        String dvdAsText = aDVD.getId() + DELIMITER;

        // add the rest of the properties in the correct order:
        
        //  [0]  [1]      [2]     [3]     [4]    [5]   [6]
        //  ID::Title::Director::Studio::Release::MPAA:UserRating
        
        //Title
        dvdAsText += aDVD.getTitle() + DELIMITER;
        
        // Director
        dvdAsText += aDVD.getDirector() + DELIMITER;
        
        // Studio
        dvdAsText += aDVD.getStudio() + DELIMITER;
        
        // Release
        dvdAsText += aDVD.getRelease() + DELIMITER;
        
        // MPAA Rating
        dvdAsText += aDVD.getMPAARating() + DELIMITER;
        
        // User Rating - Skip DELIMITER
        dvdAsText += aDVD.getUserRating();

        // We have now turned a dvd to text! Return it!
        return dvdAsText;
    }

    /**
     * Writes all students in the roster out to a ROSTER_FILE.  See loadRoster
     * for file format.
     *      Writes students into roster file
     * @throws DVDLibraryDaoException if an error occurs writing to the file
     */
    private void writeLibrary() throws DVDLibraryDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException(
                    "Could not save DVD data.", e);
        }

        // Write out the DVD objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for (DVD currentDVD : dvdList) {
            // turn a DVD into a String
            dvdAsText = marshallDVD(currentDVD);
            // write the DVD object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
    
    @Override
    public DVD addDVD(String dvdId, DVD dvdObj) 
     throws DVDLibraryDaoException {
        loadLibrary();
        DVD checkDVD = library.get(dvdId);
        if (checkDVD != null) {
            return null;
        } else {
            DVD newDVD = library.put(dvdId, dvdObj);
            writeLibrary();
            return dvdObj;
        }
    }

    @Override
    public List<DVD> getAllDVDs() 
     throws DVDLibraryDaoException {
        loadLibrary();
        return new ArrayList(library.values());
    }

    @Override
    public DVD getDVD(String dvdId) 
     throws DVDLibraryDaoException {
        loadLibrary();
        return library.get(dvdId);
    }
    
    @Override
    public DVD getDVDTitle(String dvdTitle) 
     throws DVDLibraryDaoException {
        loadLibrary();
        for (DVD dvd : library.values()) {
            if (dvd.getTitle().equals(dvdTitle)) {
                return dvd;
            }
        }
        return null;
    }

    @Override
    public DVD removeDVD (String dvdId) 
     throws DVDLibraryDaoException {
        loadLibrary();
        DVD removedDVD = library.remove(dvdId);
        writeLibrary();
        return removedDVD;
    }
    
    @Override
    public DVD editDVD (String dvdId, DVD dvd) 
     throws DVDLibraryDaoException {
        loadLibrary();
        DVD oldDVD = library.remove(dvdId);
        DVD newDVD = library.put(dvd.getId(), dvd);
        writeLibrary();
        return oldDVD;
    }
    
//    @Override
//    public DVD editDVD (String dvdId, DVD dvd) 
//     throws DVDLibraryDaoException {
//        loadLibrary();
//        DVD editedDVD = library.get(dvdId);
//        if (editedDVD == null) {
//            return null;
//        } else {
//            DVD oldDVD = library.remove(dvdId);
//            DVD newDVD = library.put(dvdId, dvd);
//            writeLibrary();
//            return oldDVD;
//        }
//    }
}
