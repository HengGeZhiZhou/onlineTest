package HelloSpringMVC.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Questions {
    private String questionId;
    private String question;
    private String chooseA;
    private String chooseB;
    private String chooseC;
    private String chooseD;
    private String answer;

    @Id
    @Column(name = "question_id")
    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "question")
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "choose_a")
    public String getChooseA() {
        return chooseA;
    }

    public void setChooseA(String chooseA) {
        this.chooseA = chooseA;
    }

    @Basic
    @Column(name = "choose_b")
    public String getChooseB() {
        return chooseB;
    }

    public void setChooseB(String chooseB) {
        this.chooseB = chooseB;
    }

    @Basic
    @Column(name = "choose_c")
    public String getChooseC() {
        return chooseC;
    }

    public void setChooseC(String chooseC) {
        this.chooseC = chooseC;
    }

    @Basic
    @Column(name = "choose_d")
    public String getChooseD() {
        return chooseD;
    }

    public void setChooseD(String chooseD) {
        this.chooseD = chooseD;
    }

    @Basic
    @Column(name = "answer")
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Questions() {
    }

    public Questions(String questionId, String question, String chooseA, String chooseB, String chooseC, String chooseD, String answer) {
        this.questionId = questionId;
        this.question = question;
        this.chooseA = chooseA;
        this.chooseB = chooseB;
        this.chooseC = chooseC;
        this.chooseD = chooseD;
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Questions questions = (Questions) o;

        if (questionId != null ? !questionId.equals(questions.questionId) : questions.questionId != null) return false;
        if (question != null ? !question.equals(questions.question) : questions.question != null) return false;
        if (chooseA != null ? !chooseA.equals(questions.chooseA) : questions.chooseA != null) return false;
        if (chooseB != null ? !chooseB.equals(questions.chooseB) : questions.chooseB != null) return false;
        if (chooseC != null ? !chooseC.equals(questions.chooseC) : questions.chooseC != null) return false;
        if (chooseD != null ? !chooseD.equals(questions.chooseD) : questions.chooseD != null) return false;
        if (answer != null ? !answer.equals(questions.answer) : questions.answer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionId != null ? questionId.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (chooseA != null ? chooseA.hashCode() : 0);
        result = 31 * result + (chooseB != null ? chooseB.hashCode() : 0);
        result = 31 * result + (chooseC != null ? chooseC.hashCode() : 0);
        result = 31 * result + (chooseD != null ? chooseD.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "questionId='" + questionId + '\'' +
                ", question='" + question + '\'' +
                ", chooseA='" + chooseA + '\'' +
                ", chooseB='" + chooseB + '\'' +
                ", chooseC='" + chooseC + '\'' +
                ", chooseD='" + chooseD + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
