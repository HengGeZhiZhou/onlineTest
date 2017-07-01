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
        student.setStudentName("大舅哥");
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
        questions.setQuestion("一个人长的有多帅");
        questions.setChooseA("A:超级帅");
        questions.setChooseB("B:一般帅");
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
        // 增加测试数据，第一个参数是访问量，最后一个是时间，中间是显示用不考虑

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


        chartDate.addValue(fail, "该分段人数", "60以下");
        chartDate.addValue(pass, "该分段人数", "60-79分");
        chartDate.addValue(good, "该分段人数", "80-89分");
        chartDate.addValue(excellent, "该分段人数", "90分以上");
        try {
            // 从数据库中获得数据集
            DefaultCategoryDataset data = chartDate;

            // 使用ChartFactory创建3D柱状图，不想使用3D，直接使用createBarChart
            JFreeChart chart = ChartFactory.createBarChart3D(
                    "网络测试成绩分布图", // 图表标题
                    "得分区间", // 目录轴的显示标签
                    "人数", // 数值轴的显示标签
                    data, // 数据集
                    PlotOrientation.VERTICAL, // 图表方向，此处为垂直方向
                    // PlotOrientation.HORIZONTAL, //图表方向，此处为水平方向
                    true, // 是否显示图例
                    true, // 是否生成工具
                    false // 是否生成URL链接
            );
            // 设置整个图片的背景色
            chart.setBackgroundPaint(Color.lightGray);
            // 设置图片有边框
            chart.setBorderVisible(true);
            Font kfont = new Font("微软雅黑", Font.PLAIN, 12);    // 底部
            Font titleFont = new Font("微软雅黑", Font.BOLD, 25); // 图片标题
            // 图片标题
            chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
            // 底部
            chart.getLegend().setItemFont(kfont);
            // 得到坐标设置字体解决乱码
            CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
            categoryplot.setDomainGridlinesVisible(true);
            categoryplot.setRangeCrosshairVisible(true);
            categoryplot.setRangeCrosshairPaint(Color.BLACK);
            NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
            numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();
            barrenderer.setBaseItemLabelFont(new Font("微软雅黑", Font.PLAIN, 12));
            barrenderer.setSeriesItemLabelFont(1, new Font("微软雅黑", Font.PLAIN, 12));
            CategoryAxis domainAxis = categoryplot.getDomainAxis();
            /*------设置X轴坐标上的文字-----------*/
            domainAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));
            /*------设置X轴的标题文字------------*/
            domainAxis.setLabelFont(new Font("微软雅黑", Font.PLAIN, 12));
            /*------设置Y轴坐标上的文字-----------*/
            numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));
            /*------设置Y轴的标题文字------------*/
            numberaxis.setLabelFont(new Font("微软雅黑", Font.PLAIN, 12));
            /*------这句代码解决了底部汉字乱码的问题-----------*/
            chart.getLegend().setItemFont(new Font("微软雅黑", Font.PLAIN, 12));
            // 生成图片并输出
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
