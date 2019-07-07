package com.web.controller.ireport;

import com.web.model.vo.TableVo;
import com.web.utils.DateUtil;
import com.web.utils.JasperHelper;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author 使用ireport5.6设计格式
 * @Date 2019/1/16 上午11:59
 * @Description
 **/
@Controller
@RequestMapping("/report")
public class ReportController {


    @RequestMapping("/reportIndex.do")
    public ModelAndView reportIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("jsp/ireport/ireport");
    }

    @RequestMapping("/demoReport2.do")
    public void demoReport2(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletContext context = request.getSession().getServletContext();
        //map作为报表数据源
        Map<String,Object> parameters = new HashMap<String,Object>(16);
        parameters.put("TITLE", "THIS IS TITLE");
        parameters.put("DATE", new SimpleDateFormat("yyyy-mm-dd").format(new Date()));
        parameters.put("NAME", "小明");
        parameters.put("AGE", "18");
        parameters.put("DEPT", "开发部");
        parameters.put("GENDER", "男");

//        String jasperPath =context.getRealPath("/pages/jasper/Blank_A4.jasper");
//        FileInputStream isRef = new FileInputStream(new File(jasperPath));
//        ServletOutputStream os = response.getOutputStream();

        String exportFilePath = "table_export_test";
        File reportFile = new File(context.getRealPath("/pages/jasper/Blank_A4.jasper"));
        JasperHelper.showHtml(exportFilePath, reportFile.getPath(), request, response, parameters, new JREmptyDataSource());
//            JasperRunManager.runReportToPdfStream(isRef, os, parameters, new JREmptyDataSource());
//            response.setContentType("application/pdf");

    }


    @RequestMapping("/report.do")
    public void report(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<TableVo> reports = getDataSource();
        //JRBeanCollectionDataSource通過構造注入collection類型的參數，這裏我們用的是list結構
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(reports);
        //構建參數map
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("header_date", DateUtil.formatDate(new Date()));
        //子數據源測試
        map.put("chart1", reports);
        //指定模板文件
        ServletContext context = request.getSession().getServletContext();
        File reportFile = null;
        reportFile = new File(context.getRealPath("/pages/jasper/report4.jasper"));
        //指定導出文件名稱
        String exportFilePath = "table_export_test";
        //調用工具類
        JasperHelper.showPdf(exportFilePath, reportFile.getPath(), request, response, map,jrDataSource);
//        JasperHelper.showHtml(exportFilePathortFilePath , reportFile.getPath(), request,response, map, jrDataSource);
//        JasperHelper.export("excel", exportFilePath, reportFile, request, response, map, jrDataSource);

    }

    public static List<TableVo> getDataSource() {
        List<TableVo> reports = new ArrayList<TableVo>();
        TableVo report = new TableVo();
        //裝載數據
        report.setId("1");//測試爲空時，
        report.setName("測1");
        report.setNumber(6);
        report.setLable("第一類");
        report.setShowFlag(true);
        reports.add(report);

        report = new TableVo();
        report.setId("2");
        report.setName("測2");
        report.setNumber(2);
        report.setLable("第一類");
        report.setShowFlag(true);
        reports.add(report);

        report = new TableVo();
        report.setId("3");
        report.setName("測3");
        report.setNumber(3);
        report.setLable("第一類");
        report.setShowFlag(true);
        reports.add(report);

        report = new TableVo();
        report.setId("4");
        report.setName("測1");
        report.setNumber(1);
        report.setLable("第二類");
        report.setShowFlag(true);
        reports.add(report);

        report = new TableVo();
        report.setId("5");
        report.setName("測2");
        report.setNumber(4);
        report.setLable("第二類");
        report.setShowFlag(true);
        reports.add(report);

        report = new TableVo();
        report.setId("6");
        report.setName("測3");
        report.setNumber(15);
        report.setLable("第二類");
        report.setShowFlag(true);
        reports.add(report);
        return reports;
    }

}
