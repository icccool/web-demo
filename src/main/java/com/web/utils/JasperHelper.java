package com.web.utils;

/**
 * @Author WANG JI BO
 * @Date 2019/1/16 上午11:49
 * @Description
 **/

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseReport;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.Map;

@SuppressWarnings("deprecation")
public class JasperHelper {
    private static Logger logger = Logger.getLogger(JasperHelper.class);
    public static final String PRINT_TYPE = "print";
    public static final String PDF_TYPE = "pdf";
    public static final String EXCEL_TYPE = "excel";
    public static final String HTML_TYPE = "html";
    public static final String WORD_TYPE = "word";

    public static void prepareReport(JasperReport jasperReport, String type) {
        logger.debug("The method======= prepareReport() start.......................");
        /*
         * 如果導出的是excel，則需要去掉周圍的margin
         */
        if ("excel".equals(type))
            try {
                Field margin = JRBaseReport.class
                        .getDeclaredField("leftMargin");
                margin.setAccessible(true);
                margin.setInt(jasperReport, 0);
                margin = JRBaseReport.class.getDeclaredField("topMargin");
                margin.setAccessible(true);
                margin.setInt(jasperReport, 0);
                margin = JRBaseReport.class.getDeclaredField("bottomMargin");
                margin.setAccessible(true);
                margin.setInt(jasperReport, 0);
                Field pageHeight = JRBaseReport.class
                        .getDeclaredField("pageHeight");
                pageHeight.setAccessible(true);
                pageHeight.setInt(jasperReport, 2147483647);
            } catch (Exception exception) {
            }
    }

    /**
     * 導出excel
     */
    public static void exportExcel(JasperPrint jasperPrint,
                                   String defaultFilename, HttpServletRequest request,
                                   HttpServletResponse response) throws IOException, JRException {
        /*
         * 設置頭信息
         */
        response.setContentType("application/vnd.ms-excel");
        String defaultname = null;
        if (defaultFilename.trim() != null && defaultFilename != null) {
            defaultname = defaultFilename + ".xls";
        } else {
            defaultname = "export.xls";
        }

        response.setHeader("Content-Disposition", "attachment; filename=\""
                + URLEncoder.encode(defaultname, "UTF-8") + "\"");


        ServletOutputStream ouputStream = response.getOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);

        exporter.setParameter(
                JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                Boolean.TRUE); // 刪除記錄最下面的空行

        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,
                Boolean.FALSE);// 刪除多餘的ColumnHeader
        //
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
                Boolean.FALSE);// 顯示邊框
        exporter.exportReport();
        ouputStream.flush();
        ouputStream.close();
    }

    public static enum DocType {
        PDF, HTML, XLS, XML, RTF
    }

    @SuppressWarnings("rawtypes")
    public static JRAbstractExporter getJRExporter(DocType docType) {
        JRAbstractExporter exporter = null;
        switch (docType) {
            case PDF:
                exporter = new JRPdfExporter();
                break;
            case HTML:
                exporter = new JRHtmlExporter();
                break;
            case XLS:
                exporter = new JExcelApiExporter();
                break;
            case XML:
                exporter = new JRXmlExporter();
                break;
            case RTF:
                exporter = new JRRtfExporter();
                break;
        }
        return exporter;
    }

    /**
     * 導出pdf，注意此處中文問題，
     * <p>
     * 這裏應該詳細說：主要在ireport裏變下就行了。看圖
     * <p>
     * 1）在ireport的classpath中加入iTextAsian.jar 2）在ireport畫jrxml時，看ireport最左邊有個屬性欄。
     * <p>
     * 下邊的設置就在點字段的屬性後出現。 pdf font name ：STSong-Light ，pdf encoding ：UniGB-UCS2-H
     */
    private static void exportPdf(JasperPrint jasperPrint,
                                  String defaultFilename, HttpServletRequest request,
                                  HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");
        String defaultname = null;
        if (defaultFilename.trim() != null && defaultFilename != null) {
            defaultname = defaultFilename + ".pdf";
        } else {
            defaultname = "export.pdf";
        }
        String fileName = new String(defaultname.getBytes("GBK"), "ISO8859_1");
        response.setHeader("Content-disposition", "attachment; filename="
                + fileName);
        ServletOutputStream ouputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }

    /**
     * 導出html
     */
    private static void exportHtml(JasperPrint jasperPrint,
                                   String defaultFilename, HttpServletRequest request,
                                   HttpServletResponse response) throws IOException, JRException {
        response.setContentType("text/html");
        ServletOutputStream ouputStream = response.getOutputStream();
        JRHtmlExporter exporter = new JRHtmlExporter();
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
                Boolean.FALSE);
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
        //設置圖片文件存放路徑，此路徑爲服務器上的絕對路徑
        String imageDIR = request.getSession().getServletContext().getRealPath("/");
        exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME, imageDIR);

        //設置圖片請求URI
        String imageURI = request.getContextPath() + "/";
        exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageURI);

        //設置導出圖片到圖片存放路徑
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.TRUE);
        exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);

        exporter.exportReport();

        ouputStream.flush();
        ouputStream.close();
    }

    /**
     * 導出word
     */
    @SuppressWarnings("rawtypes")
    private static void exportWord(JasperPrint jasperPrint,
                                   String defaultFilename, HttpServletRequest request,
                                   HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/msword;charset=utf-8");
        String defaultname = null;
        if (defaultFilename.trim() != null && defaultFilename != null) {
            defaultname = defaultFilename + ".doc";
        } else {
            defaultname = "export.doc";
        }
        String fileName = new String(defaultname.getBytes("GBK"), "utf-8");
        response.setHeader("Content-disposition", "attachment; filename="
                + fileName);
        JRExporter exporter = new JRRtfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
                response.getOutputStream());

        exporter.exportReport();
    }

    /**
     * 按照類型導出不同格式文件
     *
     * @param datas                     數據
     * @param type                      文件類型
     * @param is                        jasper文件的來源
     * @param request
     * @param response
     * @param defaultFilename默認的導出文件的名稱
     */
    public static void export(String type, String defaultFilename, File is,
                              HttpServletRequest request, HttpServletResponse response,
                              Map parameters, Connection conn) {
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
            prepareReport(jasperReport, type);

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, parameters, conn);

            if (EXCEL_TYPE.equals(type)) {
                exportExcel(jasperPrint, defaultFilename, request, response);
            } else if (PDF_TYPE.equals(type)) {
                exportPdf(jasperPrint, defaultFilename, request, response);
            } else if (HTML_TYPE.equals(type)) {
                exportHtml(jasperPrint, defaultFilename, request, response);
            } else if (WORD_TYPE.equals(type)) {
                exportWord(jasperPrint, defaultFilename, request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void export(String type, String defaultFilename, File is,
                              HttpServletRequest request, HttpServletResponse response,
                              Map parameters, JRDataSource conn) {
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(is);
            prepareReport(jasperReport, type);

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, parameters, conn);

            if (EXCEL_TYPE.equals(type)) {
                exportExcel(jasperPrint, defaultFilename, request, response);
            } else if (PDF_TYPE.equals(type)) {
                exportPdf(jasperPrint, defaultFilename, request, response);
            } else if (HTML_TYPE.equals(type)) {
                exportHtml(jasperPrint, defaultFilename, request, response);
            } else if (WORD_TYPE.equals(type)) {
                exportWord(jasperPrint, defaultFilename, request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 輸出html靜態頁面，必須注入request和response
     *
     * @param jasperPath
     * @param params
     * @param sourceList
     * @param imageUrl   報表文件使用的圖片路徑，比如 ../servlets/image?image=
     * @throws JRException
     * @throws IOException
     * @throws ServletException
     */
    public static void showHtml(String defaultFilename, String reportfile,
                                HttpServletRequest request, HttpServletResponse response, Map parameters,
                                JRDataSource conn) throws JRException, IOException {


        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        JRAbstractExporter exporter = getJRExporter(DocType.HTML);

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportfile,
                parameters, conn);
        request.getSession().setAttribute(
                ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
                jasperPrint);

        PrintWriter out = response.getWriter();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
        exporter.setParameter(
                JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
                Boolean.FALSE);

        //設置圖片文件存放路徑，此路徑爲服務器上的絕對路徑
        String imageDIR = request.getSession().getServletContext().getRealPath("/");
        exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME, imageDIR);

        //設置圖片請求URI
        String imageURI = request.getContextPath() + "/";
        exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageURI);

        //設置導出圖片到圖片存放路徑
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.TRUE);
        exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
        exporter.exportReport();

        out.flush();

    }

    public static void showHtml(String defaultFilename, String reportfile,
                                HttpServletRequest request, HttpServletResponse response, Map parameters,
                                Connection conn) throws JRException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        JRAbstractExporter exporter = getJRExporter(DocType.HTML);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportfile,
                parameters, conn);
        request.getSession().setAttribute(
                ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
                jasperPrint);

        PrintWriter out = response.getWriter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
        exporter.setParameter(
                JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
                Boolean.FALSE);
        //設置圖片文件存放路徑，此路徑爲服務器上的絕對路徑
        String imageDIR = request.getSession().getServletContext().getRealPath("/");
        exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME, imageDIR);

        //設置圖片請求URI
        String imageURI = request.getContextPath() + "/";
        exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, imageURI);

        //設置導出圖片到圖片存放路徑
        exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.TRUE);
        exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
        exporter.exportReport();
        out.flush();

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void showPdf(String defaultFilename, String reportfile,
                               HttpServletRequest request, HttpServletResponse response, Map parameters,
                               JRDataSource conn) throws JRException, IOException {


        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setContentType("application/pdf");

        JRAbstractExporter exporter = getJRExporter(DocType.PDF);

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportfile,
                parameters, conn);
        request.getSession().setAttribute(
                ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
                jasperPrint);

        OutputStream out = response.getOutputStream();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();
        out.flush();

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void showPdf(String defaultFilename, String reportfile,
                               HttpServletRequest request, HttpServletResponse response, Map parameters,
                               Connection conn) throws JRException, IOException {


        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        response.setContentType("application/pdf");

        JRAbstractExporter exporter = getJRExporter(DocType.PDF);

        JasperPrint jasperPrint = JasperFillManager.fillReport(reportfile,
                parameters, conn);
        request.getSession().setAttribute(
                ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
                jasperPrint);

        OutputStream out = response.getOutputStream();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();
        out.flush();

    }

}
