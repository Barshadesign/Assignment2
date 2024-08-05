
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

    private static ArrayList<Student> studentList;
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
    
    public static void getStudentDetails() {
        try {
            File file = new File("prog5001_students_grade_2022.csv");
            Scanner scanner = new Scanner(file);
            studentList = new ArrayList<>();

            int lnNumber = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Ignore two lines from first which is title and unit name
                if (lnNumber < 2) {
                    lnNumber++;
                    continue;
                }

                // Skip comment on the file
                if (line.startsWith("#")) {
                System.out.println("Skipping comment if there are any: " + line);
                continue;
                }
                
                // Extract student data 
                String[] detail = line.split(","); //Split line into array
                // Extract and clean  student's data that are on each columns
                String lName = detail[0].trim();
                String fName = detail[1].trim();
                String studnetId = detail[2].trim();
                // Assign value of 0 if mark is empty for any assignment
                double assignment1 = (detail.length > 3 && !detail[3].trim().isEmpty()) ? Double.parseDouble(detail[3].trim()) : 0;
                double assignment2 = (detail.length > 4 && !detail[4].trim().isEmpty()) ? Double.parseDouble(detail[4].trim()) : 0;
                double assignment3 = (detail.length > 5 && !detail[5].trim().isEmpty()) ? Double.parseDouble(detail[5].trim()) : 0;

                // Create student object and add to list
                Student student = new Student(fName + " " + lName, studnetId, assignment1, assignment2, assignment3);
                studentList.add(student);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


