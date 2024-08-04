
/**
 * Student class represents a student with their details and marks. 
 * This class is responsible for storing data about each student that 
 * includs their name, student IDs, and mark for three assignments and 
 * calculates total marks. This class also contain main method that allow 
 * program to read student data from given file, process it, and let user 
 * interact by menu-driven interface. User can view all students' marks, 
 * filter students on the basis of threshold,and view the top 5 and 
 * bottom 5 students by their total marks.
 * Author: Barsha Dahal
 * Date: 4th August, 2024
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

public class Student {
    //Declare student attributes- name, studentId, assignment1, assignment2,assignment3 and totalMarks as private
    private String name;
    private String studentId;
    private double assignment1;
    private double assignment2;
    private double assignment3;
    private double totalMarks;

/**
* Constructor for objects of class Student
*/

    public Student(String name, String studentId, double assignment1, double assignment2, double assignment3) {
        // Initialize student properties
        this.name = name;
        this.studentId = studentId;
        this.assignment1 = assignment1;
        this.assignment2 = assignment2;
        this.assignment3 = assignment3;
        this.totalMarks = assignment1 + assignment2 + assignment3;
    }

    public Student() {
    }
}


