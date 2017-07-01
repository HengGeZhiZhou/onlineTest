import HelloSpringMVC.entity.Questions;
import HelloSpringMVC.entity.Student;
import HelloSpringMVC.service.QuestionsService;
import HelloSpringMVC.service.StudentsService;
import HelloSpringMVC.util.CreateNewUserId;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test {
    private ClassPathXmlApplicationContext ctx;
    @Resource(name = "studentsServiceImpl")
    private StudentsService studentsService;
    @Resource(name = "questionsServiceImpl")
    private QuestionsService questionsService;

    @org.junit.Test
    public void testStudents(){
        Student student=new Student();
        student.setStudentId("140015511101");
        student.setStudentName("��˸�");
//        studentsService.addStudent(student);
//        studentsService.deleteStudent(student);
        List<Student>list=studentsService.findStudents();
        for (Student student1:list){
            System.out.println(student1.toString());
        }
    }

    @org.junit.Test
    public void testSuffix(){
        String filename="dasdasdasd.dasd";
        String suffix=filename.substring(filename.indexOf(".")+1);
        System.out.println(suffix);
        if (suffix.equals("xslx") || suffix.equals("xls")){
            System.out.println("dd");
        }else {
            System.out.println("geshi");
        }
        System.out.println();
    }

    @org.junit.Test
    public void testQuestions(){
        Questions questions=new Questions();
//        questions.setQuestionId(CreateNewUserId.getNewUserId(null));
        questions.setQuestion("һ���˳����ж�˧");
        questions.setChooseA("A:����˧");
        questions.setChooseB("B:һ��˧");
        questions.setChooseC("C:hhha");
        questions.setChooseD("D:hhha");
        questions.setAnswer("D");
        List<Questions> questionss=questionsService.questions();
//        for (Questions questions1:questionss){
//            System.out.println(questions1);
//        }
        questionsService.addQuestion(questions);
    }


    @org.junit.Test
    public void testGetAllQuestions(){
        List<Questions> list=questionsService.getRandomQuestions();
//        for (int i=0;i<=list.size();i++){
//            System.out.println(list.get(i).toString());
//        }

        for (Questions questions:list){
            System.out.println("=========");
            System.out.println(questions.toString());
//            System.out.println("=======");
//            System.out.println(questions[0]);
//            System.out.println(questions[1]);
//            System.out.println(questions[2]);
//            System.out.println(questions[3]);
//            System.out.println(questions[4]);
//            System.out.println(questions[5]);
        }
    }

    @org.junit.Test
    public void testScore(){
//        Student student=new Student();
//        student.setStudentId("23123231");
//        student.setGrades(String.valueOf(80));
//        System.out.println(studentsService.addScore(student));
        System.out.println(studentsService.getScore("23833").toString());
    }

    @org.junit.Test
    public void getExcellent(){
        List<Student> students=studentsService.getExcellent();
        for (Student student:students){
            System.out.println(student);
        }
    }

    @org.junit.Test
    public void getChart(){
        List<Student> students=studentsService.findStudents();
        DefaultCategoryDataset chartDate = new DefaultCategoryDataset();
        // ���Ӳ������ݣ���һ�������Ƿ����������һ����ʱ�䣬�м�����ʾ�ò�����

        int fail=0;
        int pass=0;
        int good=0;
        int excellent=0;

        for (Student student:students){
            int grades=Integer.parseInt(student.getGrades());
            if (grades<60){
                fail++;

            }
            if (grades>=60&&grades<80){
                pass++;

            }
            if (grades>=80&&grades<90){
                good++;
            }
            if (grades>=90){
                excellent++;
            }
        }
        System.out.println(fail);
        System.out.println(pass);
        System.out.println(good);
        System.out.println(excellent);


        chartDate.addValue(fail, "�÷ֶ�����", "60����");
        chartDate.addValue(pass, "�÷ֶ�����", "60-79��");
        chartDate.addValue(good, "�÷ֶ�����", "80-89��");
        chartDate.addValue(excellent, "�÷ֶ�����", "90������");
        try {
            // �����ݿ��л�����ݼ�
            DefaultCategoryDataset data = chartDate;

            // ʹ��ChartFactory����3D��״ͼ������ʹ��3D��ֱ��ʹ��createBarChart
            JFreeChart chart = ChartFactory.createBarChart3D(
                    "������Գɼ��ֲ�ͼ", // ͼ�����
                    "�÷�����", // Ŀ¼�����ʾ��ǩ
                    "����", // ��ֵ�����ʾ��ǩ
                    data, // ���ݼ�
                    PlotOrientation.VERTICAL, // ͼ���򣬴˴�Ϊ��ֱ����
                    // PlotOrientation.HORIZONTAL, //ͼ���򣬴˴�Ϊˮƽ����
                    true, // �Ƿ���ʾͼ��
                    true, // �Ƿ����ɹ���
                    false // �Ƿ�����URL����
            );
            // ��������ͼƬ�ı���ɫ
            chart.setBackgroundPaint(Color.lightGray);
            // ����ͼƬ�б߿�
            chart.setBorderVisible(true);
            Font kfont = new Font("΢���ź�", Font.PLAIN, 12);    // �ײ�
            Font titleFont = new Font("΢���ź�", Font.BOLD, 25); // ͼƬ����
            // ͼƬ����
            chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
            // �ײ�
            chart.getLegend().setItemFont(kfont);
            // �õ�������������������
            CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
            categoryplot.setDomainGridlinesVisible(true);
            categoryplot.setRangeCrosshairVisible(true);
            categoryplot.setRangeCrosshairPaint(Color.BLACK);
            NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
            numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
            barrenderer.setBaseItemLabelFont(new Font("΢���ź�", Font.PLAIN, 12));
            barrenderer.setSeriesItemLabelFont(1, new Font("΢���ź�", Font.PLAIN, 12));
            CategoryAxis domainAxis = categoryplot.getDomainAxis();
            /*------����X�������ϵ�����-----------*/
            domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
            /*------����X��ı�������------------*/
            domainAxis.setLabelFont(new Font("΢���ź�", Font.PLAIN, 12));
            /*------����Y�������ϵ�����-----------*/
            numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
            /*------����Y��ı�������------------*/
            numberaxis.setLabelFont(new Font("΢���ź�", Font.PLAIN, 12));
            /*------���������˵ײ��������������-----------*/
            chart.getLegend().setItemFont(new Font("΢���ź�", Font.PLAIN, 12));
            // ����ͼƬ�����
            File file=new File("D:\\pic\\"+"chart.jpg");
            OutputStream outputStream=null;
            outputStream=new FileOutputStream(file,true);
            ChartUtilities.writeChartAsJPEG(outputStream, 1.0f,
                    chart, 500, 300, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testGetAllGrades(){
        List<Student> students=studentsService.getAllGrades();
        for (Student student:students){
            System.out.println(student.toString());
        }
    }
}
