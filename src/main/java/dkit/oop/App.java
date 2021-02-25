package dkit.oop;

import java.util.*;

/**
 *
 * Notes:
 *  Synchronisation of multiple reads and writes to file is not considered here.
 *
 */
public class App 
{
    Scanner kb = new Scanner(System.in);
    int option;
    StudentManager studentManager = new StudentManager();
    CourseManager courseManager= new CourseManager();
    CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);
    
    public static void main( String[] args ) throws CloneNotSupportedException
    {
        System.out.println( "CAO Online - CA4" );
        new App() .start();
    }

    private void start() throws CloneNotSupportedException
    {
        
        studentManager.addStudent(new Student(2000, "2000-01-01", "Name", "Surname"));
        System.out.println(studentManager.getStudent(2000));
        System.out.println(courseManager.getCourse("DK105"));

        // display a menu to do things
        // manual testing of mgr public interface
        
        mainMenu();
        option = kb.nextInt();
        while(option != 0)
        {
            if(option == 1)
            {
                mainOption1();
            }
            if(option == 2)
            {
                System.out.println("Enter cao number of student you want to check.");
                int num = kb.nextInt();
                System.out.println(mgr.getStudentDetails(num));
            }
            option = kb.nextInt();
        }
        

//        if ( mgr.login(22224444, "xxxx","bbbb") )
//        {
//            Student student = mgr.getStudentDetails(22224444);
//
//            System.out.println("Student: " + student);
//        }
//        else
//            System.out.println("Not logged in - try again");


        //mgr.saveToFile();
    }
    
    public void mainMenu()
    {
        System.out.println("***** WELCOME *****");
        System.out.println("0. Exit the app.");
        System.out.println("1. Log in");
        System.out.println("2. Register");
    }
    
    public void mainOption1() throws CloneNotSupportedException
    {
        System.out.println("Enter your CAO number");
        int cao = kb.nextInt();
        System.out.println("Enter your password");
        String pass = kb.next();
        boolean login = mgr.login(cao, pass);
        if(!login)
        {
            System.out.println("Login failed, please try again.");
            mainOption1();
        }
        else
        {
            System.out.println("Welcome!");
            studentMenu();
            int option = kb.nextInt();
            while(option != 0)
            {
                if(option == 1)
                {
                    studentOption1();
                }
            }
        }
    }
    
    public void studentMenu()
    {
        System.out.println("Choose your option: ");
        System.out.println("1. Display a course: ");
        System.out.println("2. Display all courses: ");
        System.out.println("3. Display all current choices: ");
        System.out.println("4. Update choices: ");
        System.out.println("5. Log out");
    }
    
    public void studentOption1() throws CloneNotSupportedException
    {
        System.out.println("Please enter the ID of course you're interested in: ");
        String courseID = kb.next();
        if(courseManager.courses.containsKey(courseID))
        {
            System.out.println(mgr.getCourseDetails(courseID));
        }
        else
        {
            System.out.println("Invalid course ID, please try again.");
            studentOption1();
        }
        studentMenu();
        option = kb.nextInt();
    }
    
    
    public ArrayList<Course> choice1() throws CloneNotSupportedException
    {
        ArrayList<Course> choices = new ArrayList<>();
        System.out.println("Insert your course choices");
        String choice = kb.next();
        choices.add(courseManager.getCourse(choice));
        return choices;
    }
}
