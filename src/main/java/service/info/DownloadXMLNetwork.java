package service.info;

import data.GraphicalDataImpl;
import parsers.export.VizToXMLParser;
import spring.ApplicationContextContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Guardeec on 10.06.16.
 */
@WebServlet (name = "DownloadXML", urlPatterns = "/downloadXML")
public class DownloadXMLNetwork extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GraphicalDataImpl graphicalModels = (GraphicalDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
        String xmlData;

        try {
            switch (req.getParameter("type")){
                case "graph" : {
                    xmlData = new VizToXMLParser().parse(graphicalModels.getGraph().getJSON());
                    break;
                }
                case "glyph" : {
                    xmlData = new VizToXMLParser().parse(graphicalModels.getGlyph().getJSON());
                    break;
                }
                case "matrix" : {
                    xmlData = new VizToXMLParser().parse(graphicalModels.getMatrix().getJSON());
                    break;
                }
                case "treeMap" : {
                    xmlData = new VizToXMLParser().parse(graphicalModels.getTreeMap().getJson());
                    break;
                }
                default: {
                    xmlData = "";
                    break;
                }
            }

        }catch (NullPointerException ignored){
            xmlData = "";
        }

        resp.setContentType("text/xml");
        resp.setHeader("Content-Disposition", "attachment; filename=\"viz.xml\"");
        try
        {
            OutputStream outputStream = resp.getOutputStream();
            outputStream.write(xmlData.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        catch(Exception e)
        {
            System.out.println(e.toString());
        }


    }
}
