//D00193013
package dkit.oop;

// Stores all student CAO choices.
// Allows student to make course choices, save them and update them later.
//
// emphasis on speed of access when multiple users are accessing this at same time
//
// This component would interact with a Network component that would, in turn, send
// data over the internet to a web client.
//
// Clone all received and returned objects - encapsulation

import java.util.*;

public class CourseChoicesManager {

    // reference to constructor injected studentManager
    private StudentManager studentManager;

    // reference to constructor injected courseManager
    private CourseManager courseManager;

    // Store all the Course details -  fast access
    //Map<String, Course> courses = Map.copyOf(courseManager.courses);

    // caoNumber, course selection list - for fast access
    Map<Integer, List<Course>> choices = new HashMap<>();


    // CourseChoicesManager DEPENDS on both the StudentManager and CourseManager to access
    // student details and course details.  So, we receive a reference to each via
    // the constructor.
    // This is called "Dependency Injection", meaning that we
    // inject (or pass in) objects that this class requires to do its job.
    //
    CourseChoicesManager(StudentManager studentManager, CourseManager courseManager) {
        this.studentManager = studentManager;
        this.courseManager = courseManager;

    }

    public Student getStudentDetails(int caoNumber) throws CloneNotSupportedException
    {
        return studentManager.getStudent(caoNumber);
    }
//
    public Course getCourseDetails(String courseID) throws CloneNotSupportedException
    {
        return courseManager.getCourse(courseID);
    }

    public ArrayList getStudentChoices(int caoNumber)
    {
        Iterator<Map.Entry<Integer, List<Course>>> it = choices.entrySet().iterator();
        ArrayList<Course> choices = null;
        while(it.hasNext())
        {
            Map.Entry<Integer, List<Course>> set = it.next();
            if(set.getKey().equals(caoNumber))
            {
                choices = (ArrayList<Course>) set.getValue();
            }
        }
        return choices;
    }
//
    public void updateChoices(int caoNumber, ArrayList<Course> courseChoice) //ARRAYLIST
    {
        if(choices.containsKey(caoNumber))
        {
            choices.replace(caoNumber, courseChoice);
        }
        else
        {
            choices.put(caoNumber, courseChoice);
        }
    }
//
    /*public ArrayList getAllCourses()
    {
        ArrayList<Course> coursesList = new ArrayList<Course>();
        Iterator<Map.Entry<String, Course>> it = courses.entrySet().iterator();
        Course clone = null;
    
        while(it.hasNext())
        {
            Map.Entry<String, Course> set = (Map.Entry<String, Course>) it.next();
            coursesList.add(set.getValue());
        }
        return coursesList;
    }*/
//
    public boolean login(int caoNumber, String dob, String password) throws CloneNotSupportedException
    {
        if(studentManager.getStudent(caoNumber) == null)
        {
            System.out.println("User not registered. Try again");
            return false;
        }
        else if(studentManager.getStudent(caoNumber).getPassword().equals(password) == true &&
                studentManager.getStudent(caoNumber).getDayOfBirth().equals(dob) == true)
        {
            return true;
        }
        else
        {
            System.out.println("Incorrect credentials.");
            return false;
        }
    }


}
