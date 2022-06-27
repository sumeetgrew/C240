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
                        viewDVDTitle();
                        break;
                    case 5:
                        removeDVD();
                        break;
                    case 6:
                        editDVD();
                        break;
                    case 7:
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
        DVD addedDVD = dao.addDVD(newDVD.getId(), newDVD);
        view.displayCreatedResult(addedDVD);
    }
    
    //Lists all the DVDs
    private void listDVDs() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }
    
    //Get's DVD ID and views DVD
    private void viewDVD() throws DVDLibraryDaoException {
       view.displayDisplayDVDBanner();
       String dvdId = view.getDVDIdChoice();
       DVD dvd = dao.getDVD(dvdId);
       view.displayDVD(dvd);
    }
    
    //Get's DVD title and views DVD
    private void viewDVDTitle() throws DVDLibraryDaoException {
       view.displayDisplayDVDTitleBanner();
       String dvdTitle = view.getDVDTitleChoice();
       
       if (dao.countDVD(dvdTitle) > 1) { //More than 1 - Ask for year
           String dvdYear = view.getDVDYearChoice();
           DVD dvd = dao.getDVDTitle(dvdTitle, dvdYear);
           view.displayDVD(dvd);
       } else {
           DVD dvd = dao.getDVDTitleOnly(dvdTitle);
           view.displayDVD(dvd);
       }
    }
    
    //Removes a student given an ID
    private void removeDVD() throws DVDLibraryDaoException {
        view.displayRemoveDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD removedDVD = dao.removeDVD(dvdId);
        view.displayRemoveResult(removedDVD);
    }
    
    //Edits a DVD by ID
    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditDVDBanner();
        String dvdId = view.getDVDIdChoice();
        DVD checkDVD = dao.getDVD(dvdId);
        if (checkDVD == null) { //If DVD does not exist
            view.displayNotFoundBanner(); //Display not found
        } else { //If DVD does exist
            view.displayFoundBanner();
            view.displayEditDVDBanner();
            DVD newDVD = view.getNewDVDInfo(); //Request new info
            DVD editedDVD = dao.editDVD(checkDVD.getId(), newDVD); //Edit DVD in dao
            view.displayEditedResult(editedDVD);
        }
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
