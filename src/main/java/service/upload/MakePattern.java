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
@WebServlet (name = "MakePattern", urlPatterns = "/makePattern")
public class MakePattern extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(getHtml());
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        RawDataImpl csvStorage = (RawDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
        CsvPattern csvPattern = new CsvPattern(req.getParameter("name"));
        csvPattern.addCsvKey(CsvPattern.PossibleNames.linkChannelCapacity, Integer.parseInt(req.getParameter("linkChannelCapacity")));
        csvPattern.addCsvKey(CsvPattern.PossibleNames.linkChannelDamageCoast, Integer.parseInt(req.getParameter("linkChannelDamageCoast")));
        csvPattern.addCsvKey(CsvPattern.PossibleNames.linkChannelLoad, Integer.parseInt(req.getParameter("linkChannelLoad")));
        csvPattern.addCsvKey(CsvPattern.PossibleNames.linkChannelValue, Integer.parseInt(req.getParameter("linkChannelValue")));
        csvPattern.addCsvKey(CsvPattern.PossibleNames.hostHostIp, Integer.parseInt(req.getParameter("hostHostIp")));
        csvPattern.addCsvKey(CsvPattern.PossibleNames.hostFqdn, Integer.parseInt(req.getParameter("hostFqdn")));
        csvPattern.addCsvKey(CsvPattern.PossibleNames.hostInformationValue, Integer.parseInt(req.getParameter("hostInformationValue")));
        csvPattern.addCsvKey(CsvPattern.PossibleNames.hostLoad, Integer.parseInt(req.getParameter("hostLoad")));
        csvPattern.addCsvKey(CsvPattern.PossibleNames.hostPossibleDamage, Integer.parseInt(req.getParameter("hostPossibleDamage")));
        csvPattern.addCsvKey(CsvPattern.PossibleNames.hostType, Integer.parseInt(req.getParameter("hostType")));
        csvStorage.setPattern(csvPattern);
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
                "<div>\n" +
                "    <form action=\"http://localhost:8080/makePattern\" method=\"post\">\n" +
                "        <input type=\"text\" name=\"name\" /> - Имя паттерна<Br>\n" +
                "        <input type=\"text\" name=\"linkChannelCapacity\" /> - Пропускная способность канала<Br>\n" +
                "        <input type=\"text\" name=\"linkChannelDamageCoast\" /> - Стоимость ущерба канала<Br>\n" +
                "        <input type=\"text\" name=\"linkChannelLoad\" /> - Загруженность канала<Br>\n" +
                "        <input type=\"text\" name=\"linkChannelValue\" /> - Стоимость канала<Br>\n" +
                "        <input type=\"text\" name=\"hostHostIp\" /> - IP хоста<Br>\n" +
                "        <input type=\"text\" name=\"hostFqdn\" /> - Fqdn хоста<Br>\n" +
                "        <input type=\"text\" name=\"hostInformationValue\" /> - Стоимость информации на хосте<Br>\n" +
                "        <input type=\"text\" name=\"hostLoad\" /> - Загруженность хоста<Br>\n" +
                "        <input type=\"text\" name=\"hostPossibleDamage\" /> - Стоимость ущерба хоста<Br>\n" +
                "        <input type=\"text\" name=\"hostType\" /> - Тип хоста<Br>\n" +
                "        <input type=\"submit\" align=\"center\"/>\n" +
                "    </form>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }
}
