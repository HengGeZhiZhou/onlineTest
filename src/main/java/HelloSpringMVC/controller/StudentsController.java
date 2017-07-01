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
                return "文件格式错误，无法上传";
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
