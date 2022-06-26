/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.dto.DVD;
import java.util.List;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
/**
 *
 * @author Sumeet
 */
public class DVDLibraryController {
    private DVDLibraryView view; //Create view
    private DVDLibraryDao dao; //Create dao
    
    //Constructor to initalize our data members
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVDs();
                        break;
                    case 2:
                        createDVD();
                        break;
                    case 3:
                        viewDVD();
                        break;
                    case 4:
                        //viewDVDByTitle();
                        break;
                    case 5:
                        removeDVD();
                        break;
                    case 6:
                        //editDVD();
                        break;
                    case 7:
                        //editMultipleDVDs();
                        break;
                    case 8:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    //Displays menu and asks for next choice
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    //Creates and displays a DVD
    private void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }
    
    //Lists all the DVDs
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayStudentList(dvdList);
    }
    
    //Get's DVD title and views DVD
    private void viewDVD() throws DVDLibraryDaoException {
       view.displayDisplayDVDBanner();
       String dvdTitle = view.getDVDTitleChoice();
       DVD dvd = dao.getDVD(dvdTitle);
       view.displayDVD(dvd);
   }
    
    //Removes a student given an ID
    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String dvdTitle = view.getDVDTitleChoice();
        DVD removedDVD = dao.removeDVD(dvdTitle);
        view.displayRemoveResult(removedDVD);
    }
    
    //Displays if unkown command
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    //Displays the exit message
    private void exitMessage() {
        view.displayExitBanner();
    }
    
}
