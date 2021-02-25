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
        System.out.println("Choose your option: ");
        System.out.println("1. Add course choices");
        System.out.println("1. Log in: ");
        System.out.println("2. Display a course: ");
        System.out.println("3. Display all courses: ");
        System.out.println("4. Display all current choices: ");
        System.out.println("Update choices: ");
        System.out.println("Log out");
        option = kb.nextInt();
        while(option != 0)
        {
            if(option == 1)
            {
                mgr.updateChoices(101, choice1());
                System.out.println(mgr.getStudentChoices(101));
                
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
    
    public ArrayList<Course> choice1() throws CloneNotSupportedException
    {
        ArrayList<Course> choices = new ArrayList<>();
        System.out.println("Insert your course choices");
        String choice = kb.next();
        choices.add(courseManager.getCourse(choice));
        return choices;
    }
}
