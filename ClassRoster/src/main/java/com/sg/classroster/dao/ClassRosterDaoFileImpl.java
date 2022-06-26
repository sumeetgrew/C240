/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classroster.dao;
import com.sg.classroster.dto.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author Sumeet
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao {
    
    private Map<String, Student> students = new HashMap<>();

    
    @Override
    public Student addStudent(String studentId, Student student) {
        Student prevStudent = students.put(studentId, student);
        return prevStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<Student>(students.values());
    }

    @Override
    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    @Override
    public Student removeStudent(String studentId) {
        Student removedStudent = students.remove(studentId);
        return removedStudent;
    }

}
