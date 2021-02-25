package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate student objects


import java.util.*;

public class StudentManager
{

    // Store all students in data structure
    Map<Integer, Student> students = new HashMap<>();
    


    public StudentManager()
    {
        // Hardcode some values to get started
    Student student1 = new Student(101, "1996-09-17", "default", "default");
    students.put(student1.getCaoNumber(), student1);
        // later, load from text file "students.dat" and populate studentsMap
    }

    public Student getStudent(int caoNum)
    {
        for(Map.Entry mapElement : students.entrySet())
        {
            int key = (int)mapElement.getKey();
            if(mapElement.getCaoNumber())
            {
                return new Student copy = Student(Student);
            }
        }
        
    }
//
//    public addStudent() {
//    }
//
//    public removeStudent() {
//    }

//    isRegistered( caoNumber)
//        students.isValid()
}
