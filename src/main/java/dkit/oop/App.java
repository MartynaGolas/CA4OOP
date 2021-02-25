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

        // load students
        

        // load courses

        // load manager to provide functionality to allow a student
        // to login and add/update their course selections
        // This CourseChoicesManager component depends on the
        // StudentManager and the CourseManager,
        // so we 'inject' or pass-in these objects.
        
    
        studentManager.addStudent(new Student(2000, "2000-01-01", "Name", "Surname"));
        System.out.println(studentManager.getStudent(2000));
        System.out.println(courseManager.getCourse("DK105"));

        // display a menu to do things
        // manual testing of mgr public interface
    
        option = kb.nextInt();
        while(option != 0)
        {
            System.out.println("Choose your option: ");
            System.out.println("1. Add course choices");
            if(option == 1)
            {
                mgr.updateChoices(2000, choice1());
                System.out.println(mgr.getStudentChoices(2000));
                option = kb.nextInt();
            }
            
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
