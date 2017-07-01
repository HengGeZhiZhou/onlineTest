package HelloSpringMVC.dao.impl;


import HelloSpringMVC.dao.QuestionDao;
import HelloSpringMVC.entity.Questions;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope("prototype")
public class QuestionsDaoImpl extends BaseDaoImpl<Questions> implements QuestionDao {

    @Override
    public String getMaxQuestionID() {
        String hql = "select max(questionId) from Questions ";
        List<String> list = (List<String>) this.getHibernateTemplate().find(hql);
        if (list != null) return list.get(0);
        return null;
    }

    @Override
    public List<Object[]> getRandomQuestion() {
        String sql="select * from Questions order by rand() limit 10 ";
        Session session= this.getHibernateTemplate().getSessionFactory().openSession();
        session.beginTransaction();
        Query query=session.createSQLQuery(sql);
        List<Object[]> list=query.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }


}
