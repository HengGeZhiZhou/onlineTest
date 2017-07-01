package HelloSpringMVC.service.impl;

import HelloSpringMVC.dao.QuestionDao;
import HelloSpringMVC.entity.Questions;
import HelloSpringMVC.service.QuestionsService;
import HelloSpringMVC.util.CreateNewUserId;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Scope("prototype")
public class QuestionsServiceImpl implements QuestionsService{

    @Resource(name = "questionsDaoImpl")
    private QuestionDao questionDao;

    @Override
    public void addQuestion(Questions questions) {
        String id= CreateNewUserId.getNewUserId(questionDao.getMaxQuestionID());
        questions.setQuestionId(id);
        questionDao.save(questions);
    }

    @Override
    public void updateQuestion(Questions questions) {
        questionDao.update(questions);
    }

    @Override
    public void deleteQuestion(String QuestionID) {
        questionDao.delete(QuestionID);
    }

    @Override
    public List<Questions> questions() {
        return questionDao.findObjects();
    }

    @Override
    public List<Questions> getRandomQuestions() {
        List<Questions> questions = new ArrayList<Questions>();
        List<Object[]> list=questionDao.getRandomQuestion();
        for (int i = 0; i <list.size(); i++) {
            Object []objects=list.get(i);
            Questions questions1=new Questions(objects[0].toString(),
                    objects[1].toString(),
                    objects[2].toString(),
                    objects[3].toString(),
                    objects[4].toString(),
                    objects[5].toString(),
                    objects[6].toString());
            questions.add(i,questions1);
        }
        return questions;
    }
}
