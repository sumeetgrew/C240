/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.ui;

import com.sg.classroster.dto.Student;
import java.util.List;

/**
 *
 * @author Sumeet
 */
public class ClassRosterView {
    private UserIO io; //Create io
    
    //Constructor to initalize io
    public ClassRosterView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List Students");
        io.print("2. Create New Student");
        io.print("3. View a Student");
        io.print("4. Remove a Student");
        io.print("5. Exit");

        return io.readInt("Please select from the above choices.", 1, 5);
    }
    
    //Prompts for student info, then creates and returns a new student
    public Student getNewStudentInfo() {
        String studentId = io.readString("Please enter Student ID");
        String firstName = io.readString("Please enter First Name");
        String lastName = io.readString("Please enter Last Name");
        String cohort = io.readString("Please enter Cohort");
        Student currentStudent = new Student(studentId);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        currentStudent.setCohort(cohort);
        return currentStudent;
    }
    
    //Displays a banner to UI indicating next interaction will be creating a student
    public void displayCreateStudentBanner() {
        io.print("=== Create Student ===");
    }
    //Displays a message the student was successfully created
    public void displayCreateSuccessBanner() {
        io.readString(
                "Student successfully created.  Please hit enter to continue");
    }
    
    //Takes list of students and displays them, waiting for enter key
    public void displayStudentList(List<Student> studentList) {
        for (Student currentStudent : studentList) {
            String studentInfo = String.format("#%s : %s %s",
                  currentStudent.getStudentId(),
                  currentStudent.getFirstName(),
                  currentStudent.getLastName());
            io.print(studentInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Displays a single student
    public void displayStudent(Student student) {
        if (student != null) {
            io.print(student.getStudentId());
            io.print(student.getFirstName() + " " + student.getLastName());
            io.print(student.getCohort());
            io.print("");
        } else {
            io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    //Displays a student being removed
    public void displayRemoveResult(Student studentRecord) {
        if(studentRecord != null){
          io.print("Student successfully removed.");
        }else{
          io.print("No such student.");
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
    public void displayRemoveStudentBanner () {
        io.print("=== Remove Student ===");
    }
    
    //Banner for displaying single student
    public void displayDisplayStudentBanner () {
        io.print("=== Display Student ===");
    }

    //Helper method to get the student ID
    public String getStudentIdChoice() {
        return io.readString("Please enter the Student ID.");
    }
    
    //Display all students banner
    public void displayDisplayAllBanner() {
        io.print("=== Display All Students ===");
    }
    
    //Displays error message
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
