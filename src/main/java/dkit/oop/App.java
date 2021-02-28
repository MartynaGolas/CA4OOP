package dkit.oop;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.*;

/**
 *
 * Notes:
 *  Synchronisation of multiple reads and writes to file is not considered here.
 *
 */
public class App 
{
    private static Scanner kb = new Scanner(System.in);
    private static Vector<String[]> top_menu;
    private static Vector<String[]> admin_menu;
    private static Vector<String[]> student_menu;
    private static Vector<String[]> student_login;
    private static StudentManager studentManager = new StudentManager();
    private static CourseManager courseManager= new CourseManager();
    private static CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);
    private static int login;
    
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
        init();
        menuHelper(login());
        // manual testing of mgr public interface
        
        
    }
    
    public static void init()
    {
        //Initializing Top Menu Options
        top_menu = new Vector<String[]>();
        top_menu.add(new String[]{"Student", "student_menu"});
        top_menu.add(new String[]{"Admin", "admin_menu"});
        
        // Initializing Admin Menu Options
        admin_menu = new Vector<String[]>();
        admin_menu.add(new String[]{"Add a Course", "addCourse"});
        admin_menu.add(new String[]{"Remove a Course", "removeCourse"});
        admin_menu.add(new String[]{"Display All Courses", "displayCourses"});
        admin_menu.add(new String[]{"Display a Course", "getCourse"});
        admin_menu.add(new String[]{"Add Student", "addStudent"});
        admin_menu.add(new String[]{"Remove Student", "removeStudent"});
        admin_menu.add(new String[]{"Display a Student", "getStudent"});
        
        //Initializing Student Menu Options
        student_menu = new Vector<String[]>();
        student_menu.add(new String[]{"Display a Course", "getCourse"});
        student_menu.add(new String[]{"Display all Courses", "displayCourses"});
        student_menu.add(new String[]{"Display Current Course Choices", "getStudentChoices"});
        student_menu.add(new String[]{"Update Course Choices", "updateChoices"});
    }
    
    public static void menuHelper(Vector< String[]> menu)
    {
        while(true)
        {
            System.out.println("0. Back");
            // Show options
            for (int i = 0; i < menu.size(); i++)
            {
                System.out.println((i + 1) + ". " + menu.get(i)[0]);
            }
            // Take user input
            String in = kb.nextLine();
            try
            {
                int option = Integer.parseInt(in) - 1;
                // option to back out
                if (option == -1)
                    return;
    
                Method m = null;
                String mName = menu.get(option)[1];
    
                switch (mName)
                {
                    case "student_menu":
                        m = App.class.getMethod("menuHelper", Vector.class);
                        m.invoke(null, student_menu);
                        break;
                    case "admin_menu":
                        m = App.class.getMethod("menuHelper", Vector.class);
                        m.invoke(null, admin_menu);
                        break;
                    default:
                        m = App.class.getMethod(mName, null);
                        m.invoke(null, null);
                }
    
            } catch (NumberFormatException e)
            {
                System.out.println("Not a Number");
            } catch (NoSuchMethodException e)
            {
                System.out.println("Method was not found");
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Not a valid option");
            } catch (Exception e)
            {
                System.out.println("ooops, something went wrong...");
            }
        }
    }
    
    public static Vector login() throws CloneNotSupportedException
    {
        String pass = "";
        String dob = "";
        System.out.println("Login: ");
        login = kb.nextInt();
        System.out.println("Date Of Birth FORMAT YYYY-MM-DD");
        dob = kb.next();
        System.out.println("Password: ");
        pass = kb.next();
    
        if (login == 0000)
        {
            return admin_menu;
        }
        else if (mgr.login(login, dob, pass))
        {
            return student_menu;
        }
        return null;
    }
    
    public static void addCourse() throws CloneNotSupportedException
    {
            System.out.println("Enter course ID");
            String courseID = kb.next();
            System.out.println("Enter course level");
            String level = kb.next();
            System.out.println("Enter course title");
            String title = kb.next();
            System.out.println("Enter course Institution");
            String institution = kb.next();
            Course c = new Course(courseID, level, title, institution);
            courseManager.addCourse(c);
    }
    
    public static void removeCourse()
    {
        System.out.println("Enter ID of course you want to remove");
        String courseID = kb.next();
        courseManager.removeCourse(courseID);
    }
    
    public static void getCourse() throws CloneNotSupportedException
    {
        System.out.println("Enter ID of course you want to display");
        String courseID = kb.next();
        if(courseManager.getCourse(courseID) == null)
        {
            System.out.println("No course with ID: " + courseID + " has been found.");
        }
        else
        {
            System.out.println(courseManager.getCourse(courseID));
        }
    }
    
    public static void addStudent() throws CloneNotSupportedException
    {
        System.out.println("Enter student CAO number: ");
        int caoNum = kb.nextInt();
        System.out.println("Enter student DOB: ");
        String dob = kb.next();
        System.out.println("Enter student password: ");
        String pass = kb.next();
        System.out.println("Enter student email address: ");
        String email = kb.next();
        
        Student s = new Student(caoNum, dob, pass, email);
        studentManager.addStudent(s);
    }
    
    public static void getStudent() throws CloneNotSupportedException
    {
        System.out.println("Enter CAO number of student you want to display");
        int cao = kb.nextInt();
        if(studentManager.getStudent(cao) == null)
        {
            System.out.println("No student with CAO number: " + cao + " has been found.");
        }
        else
        {
            System.out.println(studentManager.getStudent(cao));
        }
    }
    
    public static void removeStudent()
    {
        System.out.println("Enter CAO number of student you want to remove");
        int cao = kb.nextInt();
        studentManager.removeStudent(cao);
    }
    
    public static void getStudentChoices()
    {
        if(login != 2288)
        {
            System.out.println(mgr.getStudentChoices(login));
        }
    }
    
    public static void updateChoices() throws CloneNotSupportedException
    {
        String courseID = "";
        ArrayList<Course> choices = new ArrayList<>();
        
        System.out.println("Please enter your course choices (up to 10) by entering course ID: ");
        
        for(int i = 0; i < 10; i++)
        {
            System.out.print((i + 1) + ": ");
            courseID = kb.next();
            choices.add(courseManager.getCourse(courseID));
        }
        
        mgr.updateChoices(login, choices);
        System.out.println("Your choices have been updated.");
    }
    
}
