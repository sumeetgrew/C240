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

        return io.readInt("Please select from the above choices.", 1, 8);
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
    public void displayCreatedResult(DVD dvd) {
        if(dvd != null){
          io.print("DVD successfully created.");
        }else{
          io.print("DVD already exists.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Takes list of DVDs and displays them, waiting for enter key
    //  Title::Director::Studio::Release::MPAA:UserRating
    public void displayStudentList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            String dvdInfo = String.format("%s (%s) by %s and %s : %s : %s",
                  currentDVD.getTitle(),
                  currentDVD.getRelease(),
                  currentDVD.getDirector(),
                  currentDVD.getStudio(),
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
    
    //Display DVD being edited
    public void displayEditedResult(DVD dvd) {
        if (dvd != null) {
            io.print("Successfully edited DVD.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Displays exit banner
    public void displayExitBanner() {
        io.print("=== Exiting Library ===");
    }
    
    //Dispays unknown command banner
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    //Banner for displaying remove DVD
    public void displayRemoveDVDBanner () {
        io.print("=== Remove DVD ===");
    }
    
    //Banner for displaying single DVD
    public void displayDisplayDVDBanner () {
        io.print("=== Display DVD ===");
    }

    //Helper method to get the DVD title
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
    
    //Displays edit banner
    public void displayEditDVDBanner() {
        io.print("=== EDIT DVD ===");
    }
}
