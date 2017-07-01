package HelloSpringMVC.service;


import HelloSpringMVC.entity.Student;

import java.util.List;

public interface StudentsService {

    List<Student> findStudents();

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(String studentID);

    boolean addScore(Student student);

    Student getScore(String stuNum);

    List<Student> getExcellent();

    List<Student> getAllGrades();
}
