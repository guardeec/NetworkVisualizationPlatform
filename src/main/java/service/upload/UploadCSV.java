package service.upload;

import com.oreilly.servlet.MultipartRequest;
import data.RawDataImpl;
import data.rawdata.csvdata.CsvPattern;
import spring.ApplicationContextContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Guardeec on 26.04.16.
 */
@WebServlet (name = "UploadCSVServlet", urlPatterns = "/uploadCSVServlet")
public class UploadCSV extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.write(getHtml());
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MultipartRequest multipartRequest = new MultipartRequest(req, getServletContext().getRealPath(""));
        String patternName = multipartRequest.getParameter("pattern");
        File csvFile = multipartRequest.getFile("file");
        RawDataImpl csvStorage = (RawDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
        boolean success = csvStorage.setCsv(csvFile, patternName);
        PrintWriter out = resp.getWriter();
        out.write(getHtml());
        out.close();
    }

    private String getHtml(){
        RawDataImpl csvStorage = (RawDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
        String patternList = "";
        for (CsvPattern pattern : csvStorage.getCsvPatternList()){
            patternList += "            <option value=\""+pattern.getPatternName()+"\">"+pattern.getPatternName()+"</option>\n";
        }
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Upload XML</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"http://localhost:8080/uploadCSVServlet\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                "    <input type=\"file\" name=\"file\" />\n" +
                "\n" +
                "    <select name=\"pattern\">\n" +
                patternList+
                "    </select>\n" +
                "    <input type=\"submit\"/>\n" +
                "</form>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
}
