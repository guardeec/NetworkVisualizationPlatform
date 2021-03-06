package service.upload;

import com.oreilly.servlet.MultipartRequest;
import data.RawDataImpl;
import spring.ApplicationContextContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Guardeec on 26.04.16.
 */
@WebServlet(name = "UploadXMLServlet", urlPatterns = "/uploadXMLServlet")
public class UploadXML extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.write(getHtml());
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RawDataImpl xmlStorage = (RawDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
        MultipartRequest multipartRequest = new MultipartRequest(req, getServletContext().getRealPath(""));
        boolean success = xmlStorage.setMaxPatrolLog(multipartRequest.getFile("file"));
        PrintWriter out = resp.getWriter();
        out.write(getHtml());
        out.close();
    }

    private String getHtml(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Upload XML</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form action=\"http://localhost:8080/uploadXMLServlet\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                "        <input type=\"file\" name=\"file\" />\n" +
                "        <input type=\"submit\" />\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>";
    }
}
