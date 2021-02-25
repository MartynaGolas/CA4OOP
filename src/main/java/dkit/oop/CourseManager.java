package dkit.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * CoursesManager
 * This software component Encapsulates the storage and management of
 * all courses available through the CAO system.
 * Only administrators would typically be allowed to update this data,
 * but other users can get a COPY of all the courses by calling getAllCourses().
 * The Web Client would need this data to display the course codes,
 * course titles and institutions to the student.
 */

public class CourseManager {

    // Store all the Course details.
    // Requires fast access given courseId.
    Map<String, Course> courses = new HashMap<>();

    public CourseManager() {
        // Hardcode some values to get started
        Course c1 = new Course("DK105", "8", "BSc Computing in Soft Development", "DkIT");
        Course c2 = new Course("DK106", "8", "BSc Computing in Soft Development", "DkIT");
        // load from text file "courses.dat" and populate coursesMap
        courses.put(c1.getCourseId(), c1);
        courses.put(c2.getCourseId(), c2);
        try(Scanner scan = new Scanner(new File("src/Courses.txt")))
        {
            scan.useDelimiter("[,|\r\n]");
            while(scan.hasNextLine())
            {
                String courseID = scan.next();
                String level = scan.next();
                String title = scan.next();
                String institution = scan.next();
            
                Course stu = new Course(courseID, level, title, institution);
                courses.put(stu.getCourseId(), stu);
                if(scan.hasNext())
                {
                    scan.nextLine();
                }
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("No such file");
        } catch (InputMismatchException exception)
        {
            System.out.println("InputMismatchexception caught." + exception);
        }
    }

     public Course getCourse(String courseID) throws CloneNotSupportedException
     {
        Iterator<Map.Entry<String, Course>> it = courses.entrySet().iterator();
        Course clone = null;
        
        while(it.hasNext())
        {
            Map.Entry<String, Course> set = (Map.Entry<String, Course>) it.next();
            if(set.getKey().equals(courseID))
            {
                clone = new Course((Course)set.getValue().clone());
                return clone;
            }
        }
        return clone;
     }
//
//
//    public  getAllCourses() {
//    }
//
    public void addCourse(Course course) throws CloneNotSupportedException
    {
        Course clone = (Course)course.clone();
        courses.put(clone.getCourseId(), clone);
    }
//
    public void removeCourse(String courseID)
    {
        Iterator<Map.Entry<String, Course>> it = courses.entrySet().iterator();
        
        while(it.hasNext())
        {
            Map.Entry<String, Course> set = (Map.Entry<String, Course>) it.next();
            if(set.getKey().equals(courseID))
            {
                courses.remove(courseID);
            }
        }
    }

    // editCourse(courseId);       // not required for this iteration

}







