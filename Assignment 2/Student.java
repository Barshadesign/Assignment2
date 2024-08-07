
/**
 * Student class represents a student with their details and marks. 
 * This class is responsible for storing data about each student that 
 * includs their name, student IDs, and mark for three assignments and 
 * calculates total marks. This class also contain main method that allow 
 * program to read student data from file, process it, and let user 
 * interact by menu-driven interface. User can view all students' marks, 
 * filter students on the basis of threshold,and view the top 5 and 
 * bottom 5 students by their total marks.
 * Author: Barsha Dahal
 * Date: 5th August, 2024
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.*;

public class Student {
    //Declare student attributes- name, studentId, assignment1, assignment2,assignment3 and totalMarks as private
    private String name;
    private String studentId;
    private double assignment1;
    private double assignment2;
    private double assignment3;
    private double totalMarks;
    
    // Constructor for objects of class Student

    public Student(String name, String studentId, double assignment1, double assignment2, double assignment3) {
        // Initialize student property
        this.name = name;
        this.studentId = studentId;
        this.assignment1 = assignment1;
        this.assignment2 = assignment2;
        this.assignment3 = assignment3;
        this.totalMarks = assignment1 + assignment2 + assignment3;
    }

    /** 
     * method that allow access private student detail
     */
    // Return student's name, id, assignment1, assignment2, assignment3 and total marks
    public String getName() {
        return name; 
    }

    public String getStudentId() {
        return studentId; 
    }

    public double getAssignment1() {
        return assignment1; 
    }

    public double getAssignment2() {
        return assignment2;
    }

    public double getAssignment3() {
        return assignment3;
    }

    public double getTotalMarks() {
        return totalMarks;
    }
    
     /**
      * methods that allow updating student's name, ID, and assignment marks. 
      */
     
     public boolean setName(String name) {
        if (name != null && !name.trim().isEmpty()) { // name input validate 
            this.name = name;
            return true;
        }
        return false;
    }
    
    public boolean setStudentId(String studentId) {
        if (studentId != null && !studentId.trim().isEmpty()) {// Validate studentId input before making change
            this.studentId = studentId;
            return true;
        }
        return false;
    }
    
    public boolean setAssignment1(double assignment1) { 
         // Ensure mark is not less than 0
        if (assignment1 >= 0) { 
            this.assignment1 = assignment1;
            updateTotalmark(); //total mark recalculate for accurate result
            return true;
        }
        return false;
    }
    
    public boolean setAssignment2(double assignment2) { 
         // Ensure the mark is less than 0
        if (assignment2 >= 0) { 
            this.assignment2 = assignment2;
            updateTotalmark(); //total marks recalculate for accuracy
            return true;
        }
        return false;
    }

    public boolean setAssignment3(double assignment3) { 
         // Ensure  mark is non-negative
        if (assignment3 >= 0) { 
            this.assignment3 = assignment3;
            //recalculate total marks for accuracy
            updateTotalmark(); 
            return true;
        }
        return false;
    }

    // Method to recalculate total marks
    private void updateTotalmark() {
        this.totalMarks = this.assignment1 + this.assignment2 + this.assignment3;
    }
   

    // Method to return student details
    @Override
    public String toString() {
        //Print each student detail in new line
        return 
        
        "Student Name: " + name + "\n" +
        "Student ID: " + studentId + "\n" +
        "Assignment 1: " + assignment1 + "\n" +
        "Assignment 2: " + assignment2 + "\n" +
        "Assignment 3: " + assignment3 + "\n" + 
        "Total Marks: " + totalMarks + "\n" ;

    }

}


