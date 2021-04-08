package vn.plusplus.spring.designpattern.singleton;

import java.util.List;

public class ClassStudent {
    //Eager sigleton
    private static final ClassStudent classStudent = new ClassStudent();
    private ClassStudent(){}
    public static ClassStudent getInstance(){
        return classStudent;
    }


    /*//Lazy singleton
    private static ClassStudent classStudent;

    private ClassStudent(){}

    public static ClassStudent getInstance(){
        if(classStudent == null){
            classStudent = new ClassStudent();
        }
        return classStudent;
    }*/

    List<Student> students;
    private String className;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void printStudent(){
        for(Student s: students){
            System.out.println(s.toString());
        }
    }
}
