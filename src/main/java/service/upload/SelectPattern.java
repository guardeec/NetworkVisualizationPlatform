package service.upload;

import data.RawDataImpl;
import data.rawdata.csvdata.CsvPattern;
import spring.ApplicationContextContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Guardeec on 06.06.16.
 */
@WebServlet (name = "SelectPattern", urlPatterns = "/selectPattern")
public class SelectPattern extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(getHtml());
        out.close();
    }

    private String getHtml(){
        RawDataImpl csvStorage = (RawDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
        String listOfPatterns = "";
        for (CsvPattern csvPattern : csvStorage.getCsvPatternList()){
            listOfPatterns += "            <option value=\""+csvPattern.getPatternName()+"\">"+csvPattern.getPatternName()+"</option>\n";
        }
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Upload XML</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>\n" +
                "    <form action=\"http://localhost:8080/selectPattern\" method=\"post\" id=\"upload\">\n" +
                "        <select name=\"pattern\" form=\"upload\">\n" +
                listOfPatterns+
                "        </select>\n" +
                "\n" +
                "        <input type=\"submit\" align=\"center\"/>\n" +
                "    </form>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }
}
