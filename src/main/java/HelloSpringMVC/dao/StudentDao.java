package HelloSpringMVC.dao;

import HelloSpringMVC.entity.Student;

import java.util.List;

public interface StudentDao extends BaseDao<Student> {

    List<Student> getExcellent();

    List<Student> getAllGrades();
}
