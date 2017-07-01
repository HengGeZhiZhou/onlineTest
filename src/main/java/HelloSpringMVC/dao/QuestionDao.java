package HelloSpringMVC.dao;


import HelloSpringMVC.entity.Questions;

import java.util.List;

public interface QuestionDao extends  BaseDao<Questions>{

    String getMaxQuestionID();

    List<Object[]> getRandomQuestion();
}
