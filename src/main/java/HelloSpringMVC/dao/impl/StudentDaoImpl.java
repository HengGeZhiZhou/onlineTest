package HelloSpringMVC.dao.impl;

import HelloSpringMVC.dao.StudentDao;
import HelloSpringMVC.entity.Student;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope("prototype")
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {
    @Override
    public List<Student> getExcellent() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
        detachedCriteria.addOrder(Order.desc("grades"));
        return (List<Student>) this.getHibernateTemplate().findByCriteria(detachedCriteria, 0, 10);
    }

    @Override
    public List<Student> getAllGrades() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
        detachedCriteria.addOrder(Order.desc("grades"));
        return (List<Student>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }
}
