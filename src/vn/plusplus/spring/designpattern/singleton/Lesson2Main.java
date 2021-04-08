package vn.plusplus.spring.designpattern.singleton;

import java.util.ArrayList;
import java.util.List;

public class Lesson2Main {
    public static void main(String[] args) {
        //Lop 01
        // Co cac hoc sinh A, B
        //In ra danh sach hoc sinh cua lop
        ClassStudent x = ClassStudent.getInstance();
        List<Student> students = new ArrayList<>();

        Student a = new Student();
        a.setName("A");
        a.setClassStudent(ClassStudent.getInstance());

        Student b = new Student();
        b.setName("B");
        b.setClassStudent(ClassStudent.getInstance());

        students.add(a);
        students.add(b);

        x.setStudents(students);
        x.printStudent();
    }
}
