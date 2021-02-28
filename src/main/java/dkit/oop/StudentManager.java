package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate student objects



import java.io.File;
import java.io.FileNotFoundException;
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
        try(Scanner scan = new Scanner(new File("src/Students.txt")))
        {
            scan.useDelimiter("[,|\r\n]");
            while(scan.hasNextLine())
            {
                int cao = scan.nextInt();
                String dob = scan.next();
                String pass = scan.next();
                String email = scan.next();
                
                Student stu = new Student(cao, dob, pass, email);
                students.put(stu.getCaoNumber(), stu);
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

    public Student getStudent(int ceoNumber) throws CloneNotSupportedException
    {
        Iterator<Map.Entry<Integer, Student>> it = students.entrySet().iterator();
        Student clone = null;
        
        while(it.hasNext())
        {
            Map.Entry<Integer, Student> set = it.next();
            if(set.getKey().equals(ceoNumber))
            {
                clone = new Student((Student)set.getValue().clone());
            }
        }
        return clone;
    }
//
//
//
    public void addStudent(Student student) throws CloneNotSupportedException
    {
        Scanner kb = new Scanner(System.in);
        Student clone = (Student)student.clone();
        if(students.containsKey(student.getCaoNumber()))
        {
            System.out.println("Student with this CAO number already exists, are you sure to overwrite?");
            String in = kb.next();
            if(in.equals("Y"))
            {
                students.put(clone.getCaoNumber(), clone);
                System.out.println("Student has been successfully added.");
            }
            else
            {
                System.out.println("Student not added.");
            }
        }
        
    }

    public void removeStudent(int ceoNumber)
    {
        if (students.containsKey(ceoNumber))
        {
            students.remove(ceoNumber);
            System.out.println("Student has been successfully removed.");
        }
        else
        {
            System.out.println("Student not removed or no student matching the given input: " + ceoNumber);
        }
    }
    

   public Boolean isRegistered(int caoNumber)
   {
       for (Map.Entry<Integer, Student> set : students.entrySet())
       {
           if (set.getKey().equals(caoNumber))
           {
               return true;
           }
       }
       return false;
   }
}
