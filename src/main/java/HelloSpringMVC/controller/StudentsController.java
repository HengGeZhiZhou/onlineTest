package HelloSpringMVC.controller;


import HelloSpringMVC.entity.Student;
import HelloSpringMVC.service.StudentsService;
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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import static HelloSpringMVC.util.ReadExcel.readExcel;

@Controller
@RequestMapping(value = "onlineTest/students/*")
public class StudentsController  {

    @Resource(name = "studentsServiceImpl")
    private StudentsService studentsService;

    @RequestMapping(value = "addStudent",method = RequestMethod.POST,produces = "application/json")
    public String addStudent(@RequestParam String studentName, @RequestParam String studentID){
        Student student=new Student();
        student.setStudentName(studentName);
        student.setStudentId(studentID);
        studentsService.addStudent(student);
        return "forward:/onlineTest/students/getAllStudents";
    }

    @RequestMapping(value = "updateStudent",method = RequestMethod.POST)
    public String updateStudent(@RequestParam String studentName, @RequestParam String studentID){
        Student student=new Student();
        student.setStudentName(studentName);
        student.setStudentId(studentID);
        studentsService.updateStudent(student);
        return "forward:/onlineTest/students/getAllStudents";
    }

    @RequestMapping(value = "deleteStudent")
    public String deleteStudent(@RequestParam String studentID){
        studentsService.deleteStudent(studentID);
        return "forward:/onlineTest/students/getAllStudents";
    }


    @RequestMapping(value = "getAllStudents")
    public String getAllStudents(HttpServletRequest request){
        List<Student> lists=studentsService.findStudents();
        request.setAttribute("students",lists);
        return "getAllStudents";
    }

    @RequestMapping(value = "uploadStudents", method = RequestMethod.POST)
    public String photoUpload(MultipartFile file) throws IllegalStateException, IOException {
        String path = "";
        if(!file.isEmpty()){
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            String fileName=file.getOriginalFilename();
            String suffix=fileName.substring(fileName.indexOf(".")+1);
            if (suffix.equals("xlsx") || suffix.equals("xls")){
                path="D:\\OnlineTest\\Students\\"+uuid+"."+suffix;
                file.transferTo(new File(path));
                File readFile=new File(path);
                List<List<Object>> list = readExcel(readFile,0);
                for (List<Object> list2 : list) {
                    Student student=new Student();
                    student.setStudentName(list2.get(0).toString());
                    student.setStudentId(list2.get(1).toString());
                    studentsService.addStudent(student);
                }
            }else {
                return "�ļ���ʽ�����޷��ϴ�";
            }
        }
        return "forward:/onlineTest/students/getAllStudents";
    }

    @RequestMapping(value = "getGrades",method =RequestMethod.GET)
    public String getGrades(@RequestParam String stuNum,HttpServletRequest request){
        request.setAttribute("student",studentsService.getScore(stuNum));
        return "grades";
    }

    @RequestMapping(value = "getPic")
    @ResponseBody
    public String getPic(HttpServletRequest request, HttpServletResponse response)
    {
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
            ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f,
                    chart, 1000, 600, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "getAllStudentsGrades")
    public String getAllStudentsGrades(HttpServletRequest request){
        request.setAttribute("allGrades",studentsService.getAllGrades());
        return "displayGrade";
    }

    @RequestMapping(value = "getExcellent")
    public String getExcellent(HttpServletRequest request){
        request.setAttribute("students",studentsService.getExcellent());

        return "rank";
    }
}
