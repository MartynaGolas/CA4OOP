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

    public Student getStudent(int ceoNumber) throws CloneNotSupportedException
    {
        Iterator<Map.Entry<Integer, Student>> it = students.entrySet().iterator();
        Student clone = null;
        
        while(it.hasNext())
        {
            Map.Entry<Integer, Student> set = (Map.Entry<Integer, Student>) it.next();
            if(set.getKey().equals(ceoNumber))
            {
                clone = new Student((Student)set.getValue().clone());
                return clone;
            }
        }
        return clone;
    }
//
//
//
    public void addStudent(Student student) throws CloneNotSupportedException
    {
        Student clone = (Student)student.clone();
        students.put(clone.getCaoNumber(), clone);
    }

    public void removeStudent(int ceoNumber) {
        Iterator<Map.Entry<Integer, Student>> it = students.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<Integer, Student> set = (Map.Entry<Integer, Student>) it.next();
            if(set.getKey().equals(ceoNumber))
            {
                students.remove(set.getKey());
            }
        }
        
    }

   public Boolean isRegistered(int caoNumber)
   {
       if(students.containsKey(caoNumber))
       {
           return true;
       }
       return false;
   }
       
}
