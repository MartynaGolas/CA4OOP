//D00193013
package dkit.oop;

import  java.lang.Cloneable;

public class Student implements Cloneable{
    private int caoNumber;  // In the CAO system, cao number is unique identifier for student
    private String dateOfBirth; // yyyy-mm-dd
    private String password;    // min 8 characters
    private String email;

    // Copy Constructor
    // Copies the contents of a Student object argument into
    // a new Student object, and returns that new object (a clone)
    public Student(Student student)
        {
            this.caoNumber = student.caoNumber;
            this.dateOfBirth = student.dateOfBirth;
            this.password = student.password;
            this.email = student.email;
        }


    // Constructor
    public Student(int caoNumber, String dateOfBirth, String password, String email) {
        this.caoNumber = caoNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
    }
    
    

    //public boolean verifyLoginCredentials( yyy-mm-dd, password);

    public int getCaoNumber() {
        return caoNumber;
    }

    public void setCaoNumber(int caoNumber) {
        this.caoNumber = caoNumber;
    }

    public String getDayOfBirth() {
        return dateOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dateOfBirth = dayOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "caoNumber=" + caoNumber +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
