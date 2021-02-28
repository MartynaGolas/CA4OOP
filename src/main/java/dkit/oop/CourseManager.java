//D00193013
package dkit.oop;

import java.io.*;
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
    File file = new File("src/Courses.txt");
    
    BufferedWriter bf = null;

    public CourseManager() {
        // Hardcode some values to get started
        // load from text file "courses.dat" and populate coursesMap
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
        } catch (InputMismatchException e)
        {
            System.out.println("Input Mismatch exception caught." + e);
        }
    }

     public Course getCourse(String courseID) throws CloneNotSupportedException
     {
        Iterator<Map.Entry<String, Course>> it = courses.entrySet().iterator();
        Course clone = null;
        
        while(it.hasNext())
        {
            Map.Entry<String, Course> set = it.next();
            if(set.getKey().equals(courseID))
            {
                clone = new Course((Course)set.getValue().clone());
            }
        }
        return clone;
     }


    public void getAllCourses()
    {
        Iterator<Map.Entry<String, Course>> it = courses.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<String, Course> set = it.next();
            System.out.println("Course ID: " + set.getValue().getCourseId() + " Level: " + set.getValue().getLevel()
                    + " Title: " + set.getValue().getTitle() + " Institution: " + set.getValue().getInstitution()
            );
        }
    }

    public void addCourse(Course course) throws CloneNotSupportedException
    {
        Scanner kb = new Scanner(System.in);
        if(courses.containsKey(course.getCourseId()))
        {
            System.out.println("Adding this course will replace an existing course with the same course ID, " +
                    "are you sure you want to continue? Y/N");
            String in = kb.next();
            if(in.equals("Y"))
            {
                Course clone = (Course)course.clone();
                courses.put(clone.getCourseId(), clone);
                System.out.println("Course added successfully");
            }
            else if(in.equals("N"))
            {
                System.out.println("Course not added.");
            }
        }
        else
        {
            Course clone = (Course)course.clone();
            courses.put(clone.getCourseId(), clone);
            System.out.println("Course added successfully");
        }
    }
//
    public void removeCourse(String courseID)
    {
        for (Map.Entry<String, Course> set : courses.entrySet())
        {
            if (set.getKey().equals(courseID))
            {
                courses.remove(courseID);
                System.out.println("Course has been removed.");
            }
        }
    }
    
    public void writeToFile()
    {
        try {
        
            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file));
        
            // iterate map entries
            for (Map.Entry<String, Course> set : courses.entrySet())
            {
                // put key and value separated by a colon
                bf.write(set.getKey() + "," + set.getValue().getLevel()
                        + "," + set.getValue().getTitle() + "," + set.getValue().getInstitution());
            
                // new line
                bf.newLine();
            }
        
            bf.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
        
            try {
            
                // always close the writer
                bf.close();
            }
            catch (Exception e) {
            }
        }
    }
}

    // editCourse(courseId);       // not required for this iteration









