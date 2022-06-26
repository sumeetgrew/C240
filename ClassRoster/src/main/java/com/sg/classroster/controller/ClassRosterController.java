/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.controller;
import com.sg.classroster.dao.*;
import com.sg.classroster.dto.Student;
import com.sg.classroster.ui.*;
import java.util.List;
/**
 *
 * @author Sumeet
 */
public class ClassRosterController {
    private ClassRosterView view = new ClassRosterView();
    private ClassRosterDao dao = new ClassRosterDaoFileImpl();

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
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
    }
    
    //Creates and displays a student
    private void createStudent() {
        view.displayCreateStudentBanner();
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentId(), newStudent);
        view.displayCreateSuccessBanner();
    }
    
    //Lists all the students
    private void listStudents() {
        view.displayDisplayAllBanner();
        List<Student> studentList = dao.getAllStudents();
        view.displayStudentList(studentList);
    }
    
    //Get's student ID and views student
    private void viewStudent() {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = dao.getStudent(studentId);
        view.displayStudent(student);
    }
    
    //Removes a student given an ID
    private void removeStudent() {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student removedStudent = dao.removeStudent(studentId);
        view.displayRemoveResult(removedStudent);
    }
    
    //Displays if unkown command
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    //Displays the exit message
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    //Displays menu and asks for next choice
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
}
