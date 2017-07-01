package HelloSpringMVC.controller;


import HelloSpringMVC.entity.Questions;
import HelloSpringMVC.entity.Student;
import HelloSpringMVC.service.QuestionsService;
import HelloSpringMVC.service.StudentsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static HelloSpringMVC.util.ReadExcel.readExcel;

@Controller
@RequestMapping(value = "onlineTest/questions/*")
public class QuestionsController {
    @Resource(name = "questionsServiceImpl")
    private QuestionsService questionsService;

    @Resource(name = "studentsServiceImpl")
    private StudentsService studentsService;


    @RequestMapping(value = "addQuestion", method = RequestMethod.POST)
    public String addQuestion(
            @RequestParam String question,
            @RequestParam String chooseA,
            @RequestParam String chooseB,
            @RequestParam String chooseC,
            @RequestParam String chooseD,
            @RequestParam String answer
    ) {
        Questions questions = new Questions();
        questions.setQuestion(question);
        questions.setChooseA(chooseA);
        questions.setChooseB(chooseB);
        questions.setChooseC(chooseC);
        questions.setChooseD(chooseD);
        questions.setAnswer(answer);
        questionsService.addQuestion(questions);
        return "success";
    }

    @RequestMapping(value = "updateQuestion", method = RequestMethod.POST)
    @ResponseBody
    public String updateQuestion(
            @RequestParam String questionID,
            @RequestParam String question,
            @RequestParam String chooseA,
            @RequestParam String chooseB,
            @RequestParam String chooseC,
            @RequestParam String chooseD,
            @RequestParam String answer) {
        Questions questions = new Questions();
        questions.setQuestionId(questionID);
        questions.setQuestion(question);
        questions.setQuestion(chooseA);
        questions.setQuestion(chooseB);
        questions.setQuestion(chooseC);
        questions.setQuestion(chooseD);
        questions.setAnswer(answer);
        questionsService.updateQuestion(questions);
        return "success";
    }


    @RequestMapping(value = "deleteQuestion", method = RequestMethod.GET)
    public String deleteQuestion(@RequestParam String questionID) {
        questionsService.deleteQuestion(questionID);
        return "forward:/onlineTest/questions/getAllQuestions";
    }

    @RequestMapping(value = "getAllQuestions")
    public String getAllQuestions(HttpServletRequest request) {
        List<Questions> questions = questionsService.questions();
        request.setAttribute("questions", questions);
        return "allTestPapers";
    }

    @RequestMapping(value = "uploadQuestions", method = RequestMethod.POST)
    public String uploadQuestions(MultipartFile file) throws IllegalStateException, IOException {
        String path = "";
        if (!file.isEmpty()) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.indexOf(".") + 1);
            if (suffix.equals("xlsx") || suffix.equals("xls")) {
                path = "D:\\OnlineTest\\testPapers\\" + uuid + "." + suffix;
                file.transferTo(new File(path));
                File readFile = new File(path);
                List<List<Object>> list = readExcel(readFile, 0);
                for (List<Object> list2 : list) {
                    Questions questions = new Questions();
                    questions.setQuestion(list2.get(0).toString());
                    questions.setChooseA(list2.get(1).toString());
                    questions.setChooseB(list2.get(2).toString());
                    questions.setChooseC(list2.get(3).toString());
                    questions.setChooseD(list2.get(4).toString());
                    questions.setAnswer(list2.get(5).toString());
                    questionsService.addQuestion(questions);
                }
            } else {
                return "文件格式错误，无法上传";
            }
        }
        return "forward:/onlineTest/questions/getAllQuestions";
    }

    @RequestMapping(value = "startTest")
    public String startTest(HttpServletRequest request) {
        String answers = "";
        List<Questions> questions = questionsService.getRandomQuestions();
        for (Questions question : questions) {
            answers += question.getAnswer();
        }
        request.setAttribute("answers", answers);
        request.setAttribute("questions", questions);
        return "test";
    }

    @RequestMapping(value = "postResult", method = RequestMethod.POST)
    public String postResult(@RequestParam String stuNum,
                             @RequestParam String answers,
                             @RequestParam String question0,
                             @RequestParam String question1,
                             @RequestParam String question2,
                             @RequestParam String question3,
                             @RequestParam String question4,
                             @RequestParam String question5,
                             @RequestParam String question6,
                             @RequestParam String question7,
                             @RequestParam String question8,
                             @RequestParam String question9,HttpServletRequest request
    ) {
        int score = 0;
        if (question0.equals(answers.substring(0, 1)) || question0 == answers.substring(0, 1)) {
            score += 10;
        }
        if (question1.equals(answers.substring(1, 2)) || question1 == answers.substring(1, 2)) {
            score += 10;
        }
        if (question2.equals(answers.substring(2, 3)) || question2 == answers.substring(2, 3)) {
            score += 10;
        }
        if (question3.equals(answers.substring(3, 4)) || question3 == answers.substring(3, 4)) {
            score += 10;
        }
        if (question4.equals(answers.substring(4, 5)) || question4 == answers.substring(4, 5)) {
            score += 10;
        }
        if (question5.equals(answers.substring(5, 6)) || question5 == answers.substring(5, 6)) {
            score += 10;
        }
        if (question6.equals(answers.substring(6, 7)) || question6 == answers.substring(6, 7)) {
            score += 10;
        }
        if (question7.equals(answers.substring(7, 8)) || question7 == answers.substring(7, 8)) {
            score += 10;
        }
        if (question8.equals(answers.substring(8, 9)) || question8 == answers.substring(8, 9)) {
            score += 10;
        }
        if (question9.equals(answers.substring(9, 10)) || question9 == answers.substring(9, 10)) {
            score += 10;
        }
        Student student=new Student();
        student.setStudentId(stuNum);
        student.setGrades(String.valueOf(score));
        boolean result= studentsService.addScore(student);
        if (result){
           request.setAttribute("resultInfo","提交成功，祝您好运");
        }else {
          request.setAttribute("resultInfo","提交失败，请检查您的学号是否正确或您已经进行过考试");
        }
        return "result";

    }

}
