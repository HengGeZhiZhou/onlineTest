package HelloSpringMVC.service.impl;


import HelloSpringMVC.dao.StudentDao;
import HelloSpringMVC.entity.Student;
import HelloSpringMVC.service.StudentsService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
@Scope("prototype")
public class StudentsServiceImpl implements StudentsService{

    @Resource(name = "studentDaoImpl")
    private StudentDao studentDao;

    @Override
    public List<Student> findStudents() {
        return studentDao.findObjects();
    }

    @Override
    public void addStudent(Student student) {
        studentDao.save(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.update(student);
    }

    @Override
    public void deleteStudent(String studentID) {
        studentDao.delete(studentID);
    }

    @Override
    public boolean addScore(Student student) {
        try {
            Student student1=studentDao.findObjectById(student.getStudentId());
            if (student1.getGrades()!=null){
                return false;
            }
            else {
                student1.setGrades(student.getGrades());
                student1.setTestTime(new java.sql.Timestamp(System.currentTimeMillis()));
                studentDao.update(student1);
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Student getScore(String stuNum) {
        return studentDao.findObjectById(stuNum);
    }

    @Override
    public List<Student> getExcellent() {
        return studentDao.getExcellent();
    }

    @Override
    public List<Student> getAllGrades() {
        return studentDao.getAllGrades();
    }
}
