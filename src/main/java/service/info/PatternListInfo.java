package service.info;

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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Guardeec on 06.06.16.
 */
@WebServlet (name = "PatternListInfo", urlPatterns = "/patternListInfo")
public class PatternListInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.write(getHtml());
        out.close();
    }

    private String getHtml(){
        RawDataImpl storage = (RawDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");

        String message = "";
        message += "<table class=\"tftable\" border=\"1\">";
        for (CsvPattern pattern : storage.getCsvPatternList()){
            message += "<tr>";
            //пишем имя и первые 2 поля
            message += "<td>Pattern name: "+pattern.getPatternName()+"</td>";
            message += "<td>HostIpFrom</td><td>HostIpTo</td>";

            //инвертируем мапу для поиска позиций
            Map<Integer, String> params = new HashMap<>();
            for(Map.Entry<String, Integer> entry : pattern.getCsvKeys().entrySet()){
                params.put(entry.getValue(), entry.getKey());
            }

            //находим длинну паттерна
            Integer biggestNumber = 0;
            for (Integer count : params.keySet()){
                if (biggestNumber<count){
                    biggestNumber = count;
                }
            }

            //заполняем поля в соответствии с позициями
            for (int i=2; i<biggestNumber; i++){
                if (params.get(i)==null){
                    message += "<td></td>";
                }else {
                    message += "<td>"+params.get(i)+"</td>";
                }
            }

            message += "</tr>";
        }
        message += "</table>";

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Upload XML</title>\n" +
                "<style type=\"text/css\">\n" +
                ".tftable {font-size:12px;color:#333333;width:100%;border-width: 1px;border-color: #729ea5;border-collapse: collapse;}\n" +
                ".tftable th {font-size:12px;background-color:#acc8cc;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;text-align:left;}\n" +
                ".tftable tr {background-color:#ffffff;}\n" +
                ".tftable td {font-size:12px;border-width: 1px;padding: 8px;border-style: solid;border-color: #729ea5;}\n" +
                ".tftable tr:hover {background-color:#ffff99;}\n" +
                "</style>"+
                "</head>\n" +
                "<body>\n" +
                message +
                "</body>\n" +
                "</html>";
    }
}
