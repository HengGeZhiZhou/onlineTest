<%@ page import="org.jfree.data.category.DefaultCategoryDataset" %>
<%@ page import="org.jfree.chart.StandardChartTheme" %>
<%@ page import="java.awt.*" %>
<%@ page import="org.jfree.chart.ChartFactory" %>
<%@ page import="org.jfree.chart.JFreeChart" %>
<%@ page import="org.jfree.chart.plot.PlotOrientation" %>
<%@ page import="org.jfree.chart.plot.CategoryPlot" %>
<%@ page import="org.jfree.chart.axis.CategoryAxis" %>
<%@ page import="org.jfree.chart.renderer.category.BarRenderer" %>
<%@ page import="org.jfree.chart.labels.StandardCategoryItemLabelGenerator" %>
<%@ page import="org.jfree.chart.servlet.ServletUtilities" %>
<%@ page import="org.jfree.chart.ChartRenderingInfo" %>
<%@ page import="org.jfree.chart.entity.StandardEntityCollection" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/6/30
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>studentGrades</title>
</head>
<body>
<%
    //显示柱状图
    DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
    mDataset.addValue(2000, "清华大学", "本科生");
    mDataset.addValue(1500, "清华大学", "研究生");
    mDataset.addValue(1000, "清华大学", "博士生");
    mDataset.addValue(900, "清华大学", "讲师");
    mDataset.addValue(800, "清华大学", "副教授");
    mDataset.addValue(300, "清华大学", "教授");
    mDataset.addValue(600, "清华大学", "行政人员");
    mDataset.addValue(400, "清华大学", "管理人员");

    //创建主题样式
    StandardChartTheme mChartTheme = new StandardChartTheme("CN");
    //设置图表标题
    mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
    //设置轴向字体
    mChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15));
    //设置图例字体
    mChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15));
    //应用主题
    ChartFactory.setChartTheme(mChartTheme);

    JFreeChart mChart = ChartFactory.createBarChart3D(
            "学校人员分布图",
            "类型",
            "数量",
            mDataset,
            PlotOrientation.VERTICAL,
            true,
            true,true);
    //设置内部属性
    CategoryPlot mPlot = (CategoryPlot)mChart.getPlot();
    CategoryAxis mDomainAxis = mPlot.getDomainAxis();
    //设置柱状图距离x轴最左端（即y轴）的距离百分比10%
    //mDomainAxis.setLowerMargin(0.1);
    mDomainAxis.setUpperMargin(0.1);
    //柱体显示数值
    BarRenderer mRenderer = new BarRenderer();
    mRenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    mRenderer.setItemLabelFont(new Font("宋体", Font.PLAIN, 15));
    mRenderer.setItemLabelsVisible(true);
    mPlot.setRenderer(mRenderer);

    ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
    //500是图片长度，300是图片高度
    String filenamebar = ServletUtilities.saveChartAsPNG(mChart, 800, 500, info, session);
    String graphURLbar = request.getContextPath() + "/servlet/DisplayChart?filename=" + filenamebar;
%>
    <a href="<%=graphURLbar%>">tiao</a>
</body>
</html>
