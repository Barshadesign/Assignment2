
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

    private static ArrayList<Student> studentList;
    private static boolean fileLoad = false; // Check if file has loaded
    
    // Constructor for objects of class Student

    public Student(String name, String studentId, double assignment1, double assignment2, double assignment3) {
        // Initialize student properties
        this.name = name;
        this.studentId = studentId;
        this.assignment1 = assignment1;
        this.assignment2 = assignment2;
        this.assignment3 = assignment3;
        this.totalMarks = assignment1 + assignment2 + assignment3;
    }

    // Default constructor
    public Student() {
    }
    
    // Method to ask file as input from user
    public static void getStudentDetails() {
        // Ask file name to user
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Provide the file name: ");
            String fileName = scanner.nextLine();
            // Finding file
            File file = new File(fileName); 
            if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return;  // If file not found, exit method
            } 
            
            // File found confirm
            System.out.println("Congratulations, " + fileName + " has been found. Reading file ... ");
        
            Scanner filescanner = new Scanner(file);
            studentList = new ArrayList<>();

            int lnNumber = 0;
            // Reading file line by line
            while (filescanner.hasNextLine()) {
                String line = filescanner.nextLine();
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
            filescanner.close();
            System.out.println("Above file has been read successfully.");
            fileLoad = true; // Set fileLoaded to true after successful file load



        } catch (FileNotFoundException e) {
        System.out.println("File not found. Check file name and try again.");
        e.printStackTrace();  // locate error
    }
    }
    /** 
     * method that allow access private student detail
     */
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
    if (name != null && !name.trim().isEmpty()) { //validate name input before making change
            this.name = name;
            return true;
        }
    return false;
    }
    
    public boolean setStudentId(String studentId) {
        if (studentId != null && !studentId.trim().isEmpty()) {//validate studentId input before making change
            this.studentId = studentId;
            return true;
        }
        return false;
    }
    
    public boolean setAssignment1(double assignment1) { 
         // Ensure the mark is non-negative before updating
        if (assignment1 >= 0) { 
        this.assignment1 = assignment1;
        updateTotalmark(); //total marks recalculate for accurate result
        return true;
        }
        return false;
    }
    
    public boolean setAssignment2(double assignment2) { 
         // Ensure the mark is non-negative before updating
        if (assignment2 >= 0) { 
            this.assignment2 = assignment2;
            updateTotalmark(); //total marks recalculate for accurate result
            return true;
        }
        return false;
    }

public boolean setAssignment3(double assignment3) { 
 // Ensure the mark is non-negative before updating
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
        return "Student Name: " + name + "\n" +
               "Student ID: " + studentId + "\n" +
               "Assignment 1: " + assignment1 + "\n" +
               "Assignment 2: " + assignment2 + "\n" +
               "Assignment 3: " + assignment3 + "\n" + 
               "Total Marks: " + totalMarks + "\n";
    }

// Method to caary bubble sort on studentList
public static void bubbleSortStudents() {
    int n = studentList.size(); // Get number of students in list
    for (int i = 0; i < n - 1; i++) { // outer loop to ensure we make multiple pass
        for (int j = 0; j < n - i - 1; j++) {  // Inner loop to compare each student with next one 
            if (studentList.get(j).getTotalMarks() > studentList.get(j + 1).getTotalMarks()) {
                Student temp = studentList.get(j); // If student has more marks swap them 
                studentList.set(j, studentList.get(j + 1));
                studentList.set(j + 1, temp);
            }
        }
    }
}


// Filtering student on basis of threshold provided
    public static void filterThreshold() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please provide threshold: ");
        double markthreshold = scanner.nextDouble();

        System.out.println("List of student with total mark less than threshold " + markthreshold);

        boolean dataFound = false;
        // Loop executing through each student
        for (Student student : studentList) {

            if (student.getTotalMarks() < markthreshold) { //check if total mark is less than threshold
                System.out.println(student); // Print student detail if condition true
                dataFound = true;
            }
        }

        if (!dataFound) {
            System.out.println("No student with total marks less than " + markthreshold);
        }
        System.out.println("...................................................");
    }

    // Method to print top 5 and bottom 5 students
public static void printTopBottom() {
        // Sort student lst in ascending order using bubble sort

    bubbleSortStudents(); 
    System.out.println("Top 5 Students with highest marks:"); // Print top 5 students with highest marks

    for (int i = 0; i < 5; i++) {
        System.out.println(studentList.get(studentList.size() - 1 - i)); // Display top 5 students from end of sorted list
    }
    // Print bottom 5 students
    System.out.println("\nBottom 5 Students with lowest marks:"); // Print bottom 5 students with lowest marks
    for (int i = 0; i < 5; i++) {
        System.out.println(studentList.get(i)); // Display bottom 5 student from start of sort list
    }
}


    // Main method to run program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try{
            System.out.print("Please choose an option: ");
            System.out.println("1. Provide file before carrying any function");
            System.out.println("2. View total marks of students");
            System.out.println("3. Filter student details below threshold");
            System.out.println("4. Print top 5 and bottom 5 students with details");
            System.out.println("5. Exit");
            
            int choice = scanner.nextInt();

           // Implementing menu system for executing funtion
        if (choice == 1) {
                getStudentDetails();
            }
            else if (choice == 2) {
                if (fileLoad) {
                    printAllStudents();
                } else {
                    System.out.println("Provide file before carrying any function");
                }
            } else if (choice == 3) {
                if (fileLoad) {
                    filterThreshold();
                } else {
                    System.out.println("Provide file before carrying any function");
                }
            }  
            else if (choice == 4) {
                if (fileLoad) {
                    printTopBottom();
                } else {
                    System.out.println("Provide file before carrying any function");
                }
            }
            else if (choice == 5) {
                System.out.println("Exiting program...");
                break;
            } else {
                System.out.println("Please choose valid option.");
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input, enter number between 1 and 5.");
            scanner.next(); 
        }
    }
                scanner.close();

        
    }

    // Method to print  students
    public static void printAllStudents() {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}


