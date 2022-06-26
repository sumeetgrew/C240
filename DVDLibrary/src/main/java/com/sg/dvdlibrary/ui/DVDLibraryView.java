/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Sumeet
 */
public class DVDLibraryView {
    private UserIO io; //Create io
    
    //Constructor to initalize io
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }
//    Allow the user to add a DVD to the collection --
//    Allow the user to remove a DVD from the collection --
//    Allow the user to edit the information for an existing --DVD in the collection
//    Allow the user to list the DVDs in the collection --
//    Allow the user to display the information for a particular DVD--
//    Allow the user to search for a DVD by title --
//    Load the DVD library from a file
//    Save the DVD library back to the file when the program completes
//    Allow the user to add, edit, or delete many DVDs in one session --
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Create New DVD");
        io.print("3. View a DVD");
        io.print("4. View a DVD by title");
        io.print("5. Remove a DVD");
        io.print("6. Edit a DVD");
        io.print("7. Edit multiple DVDs");
        io.print("8. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    //Prompts for DVD info, then creates and returns a new DVD
    //  Title::Director::Studio::Release::MPAA:UserRating
    public DVD getNewDVDInfo() {
        String dvdTitle = io.readString("Please enter the DVD's title");
        String director = io.readString("Please enter the director's name");
        String studio = io.readString("Please enter the studio");
        String release = io.readString("Please enter the release date");
        String mpaa = io.readString("Please enter the MPAA rating");
        String userRating = io.readString("Please enter the user rating");
        
        DVD currentDVD = new DVD(dvdTitle);
        
        currentDVD.setDirector(director);
        currentDVD.setStudio(studio);
        currentDVD.setRelease(release);
        currentDVD.setMPAARating(mpaa);
        currentDVD.setUserRating(userRating);
        
        return currentDVD;
    }
    
    //Displays a banner to UI indicating next interaction will be creating a DVD
    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }
    //Displays a message the DVD was successfully created
    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
    }
    
    //Takes list of DVDs and displays them, waiting for enter key
    //  Title::Director::Studio::Release::MPAA:UserRating
    public void displayStudentList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            String dvdInfo = String.format("#%s by %s and %s : %s : %s : %s",
                  currentDVD.getTitle(),
                  currentDVD.getDirector(),
                  currentDVD.getStudio(),
                  currentDVD.getRelease(),
                  currentDVD.getMPAARating(),
                  currentDVD.getUserRating()
                  );
            io.print(dvdInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Displays a single DVD
    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getDirector());
            io.print(dvd.getStudio());
            io.print(dvd.getRelease());
            io.print(dvd.getMPAARating());
            io.print(dvd.getUserRating());
            io.print("");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Displays a DVD being removed
    public void displayRemoveResult(DVD dvdRecord) {
        if(dvdRecord != null){
          io.print("DVD successfully removed.");
        }else{
          io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Displays exit banner
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    //Dispays unknown command banner
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    //Banner for displaying remove student
    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }
    
    //Banner for displaying single student
    public void displayDisplayDVDBanner () {
        io.print("=== Display DVD ===");
    }

    //Helper method to get the student ID
    public String getDVDTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }
    
    //Display all DVD banner
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
    
    //Displays error message
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
