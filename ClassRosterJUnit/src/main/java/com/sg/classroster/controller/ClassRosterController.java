/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.controller;
import com.sg.classroster.dao.*;
import com.sg.classroster.dto.Student;
import com.sg.classroster.service.ClassRosterDataValidationException;
import com.sg.classroster.service.ClassRosterDuplicateIdException;
import com.sg.classroster.service.ClassRosterServiceLayer;
import com.sg.classroster.ui.*;
import java.util.List;
/**
 *
 * @author Sumeet
 */
public class ClassRosterController {
    private ClassRosterView view; //Create view
    private ClassRosterServiceLayer service; //Our service layer

    
    //Constructor to initalize our data members
    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
        this.service = service;
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
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (ClassRosterPersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    //Displays menu and asks for next choice
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    //Creates and displays a student
    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do {
            Student currentStudent = view.getNewStudentInfo();
            try {
                service.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }
    
    //Lists all the students
    private void listStudents() throws ClassRosterPersistenceException {
        List<Student> studentList = service.getAllStudents();

        view.displayStudentList(studentList);
    }
    
    //Get's student ID and views student
    private void viewStudent() throws ClassRosterPersistenceException {
         String studentId = view.getStudentIdChoice();
         Student student = service.getStudent(studentId) ;
         view.displayStudent(student);
    }
    
    //Removes a student given an ID
    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        service.removeStudent(studentId);
        view.displayRemoveSuccessBanner();
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
