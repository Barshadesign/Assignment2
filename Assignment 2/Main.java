import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    // Main method to run program
    public static void main(String[] args) {
        ManageMarks manager = new ManageMarks();
        Scanner scanner = new Scanner(System.in);
        //  Infinite loop for menu interface
        while (true) {
            try{
                // Showing menu options to users
            System.out.println("Welcome to Program, access below menu: ");
            System.out.println("1. Provide file before carrying any function");
            System.out.println("2. View total marks of students");
            System.out.println("3. Filter student details below threshold");
            System.out.println("4. Print top 5 and bottom 5 students with details");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // newline

           
      // Implementing menu system for executing funtion based on choice

        if (choice == 1) { // User chooses to provide file
                System.out.print("Enter file name: ");
                String fileName = scanner.next();
                manager.getStudentDetails(fileName);
            } else if (choice >= 2 && choice <= 4) {
                // Ensure if file is loaded before processing with other option
                if (manager.isFileLoaded()) { 
                    if (choice == 2) {
                        // option to view total marks of students
                        manager.printAllStudents();
                    } else if (choice == 3) {
                        // Option to filter students based on threshold
                        manager.filterThreshold();
                    } else if (choice == 4) {
                        // Option to print top 5 and bottom 5 
                        manager.printTopBottom();
                    }
                } else {
                    // Warning user if they proceed without loading  file 

                    System.out.println("Please provide a file before carrying any function.");
                }
            } else if (choice == 5) {
                System.out.println("Exiting program...");
                 scanner.close(); // Close scanner before exiting
                 System.exit(0); // Exit program

                break;
            } else {
                // Notify user if they enter invalid value
                System.out.println("Choose valid option, a number between 1 and 5.");
            }
        } catch (InputMismatchException e) {
            // Notify user if they inout invalid integer
            System.out.println("Invalid input, enter a number between 1 and 5.");
            scanner.next(); 
        }
        // Add divider after each operation for better readability
        System.out.println("=====================================================");
    }

}
} 



