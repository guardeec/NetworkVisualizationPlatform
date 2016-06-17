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
@WebServlet (name = "DeletePattern", urlPatterns = "/deletePattern")
public class DeletePattern extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.write(getHtml());
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RawDataImpl csvStorage = (RawDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
        if (req.getParameter("name")!=null){
            for (CsvPattern pattern : csvStorage.getCsvPatternList()){
                if (pattern.getPatternName().compareTo(req.getParameter("name"))==0){
                    csvStorage.getCsvPatternList().remove(pattern);
                    break;
                }
            }
        }
        PrintWriter out = resp.getWriter();
        out.write(getHtml());
        out.close();
    }

    private String getHtml(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Delete Pattern</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div  style=\"text-align:center;\">"+
                "<form action=\"http://localhost:8080/deletePattern\" method=\"post\">\n" +
                "    <input type=\"text\" name=\"name\"/>" +
                "    <input type=\"submit\" value=\"delete\"/>\n" +
                "    </form>\n" +
                "</div>"+
                "</body>\n" +
                "</html>";
    }
}
