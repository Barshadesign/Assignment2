
import java.io.*;
import java.util.*;

public class ManageMarks
{
        private ArrayList<Student> studentList = new ArrayList<>();  // Changed to non-static

    private boolean fileLoaded = false;
    
    // Method to ask file as input from user
    public void getStudentDetails(String fileName) {
        // Ask file name to user
        try {
            
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
                //Assign value of 0 if mark is empty for any assignment
                double assignment1 = (detail.length > 3 && !detail[3].trim().isEmpty()) ? Double.parseDouble(detail[3].trim()) : 0;
                double assignment2 = (detail.length > 4 && !detail[4].trim().isEmpty()) ? Double.parseDouble(detail[4].trim()) : 0;
                double assignment3 = (detail.length > 5 && !detail[5].trim().isEmpty()) ? Double.parseDouble(detail[5].trim()) : 0;

                // Create student object and add to list
                Student student = new Student(fName + " " + lName, studnetId, assignment1, assignment2, assignment3);
                studentList.add(student);
            }
            filescanner.close();
            fileLoaded = true;
            System.out.println("Above file has been read successfully.");


        } catch (FileNotFoundException e) {
        System.out.println("File not found. Check file name and try again.");
        e.printStackTrace();  // locate error
    }
    }
    
    
// Method to caary bubble sort on studentList
public void bubbleSortStudents() {
    int n = studentList.size(); // Get number of students in list
    for (int i = 0; i < n - 1; i++) { // outer loop to ensure multiple pass
        for (int j = 0; j < n - i - 1; j++) {  // Inner loop to compare each student with next 
            if (studentList.get(j).getTotalMarks() > studentList.get(j + 1).getTotalMarks()) {
                Student temp = studentList.get(j); // If student is greater swap them 
                studentList.set(j, studentList.get(j + 1));
                studentList.set(j + 1, temp);
            }
        }
    }
}


// Filtering student on basis of threshold 
    public void filterThreshold() {
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
    }

    // Method to print top 5 and bottom 5 students
public void printTopBottom() {
    // Sort student list in ascending order,use bubble sort
    bubbleSortStudents(); 

    System.out.println("Top 5 Students with highest marks:"); // Print top 5 students with highest marks
    for (int i = 0; i < 5; i++) {
        System.out.println(studentList.get(studentList.size() - 1 - i)); // Display top 5 students from end of sorted list
    }
    System.out.println("\nBottom 5 Students with lowest marks:"); // Print bottom 5 students with lowest marks
    for (int i = 0; i < 5; i++) {
        System.out.println(studentList.get(i)); // Display bottom 5 student from start of sort list
    }
   
}

// Method to print  students details
    public void printAllStudents() {
        // Loop through each student
        for (Student student : studentList) {
            System.out.println(student);// Display student information 

        }
    }
    public boolean isFileLoaded() {
        return fileLoaded;
    }
}
