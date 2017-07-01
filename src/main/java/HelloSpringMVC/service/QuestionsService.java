package HelloSpringMVC.service;


import HelloSpringMVC.entity.Questions;

import java.util.List;

public interface QuestionsService {

    void addQuestion(Questions questions);

    void updateQuestion(Questions questions);

    void deleteQuestion(String QuestionID);

    List<Questions> questions();

    List<Questions> getRandomQuestions();
}
