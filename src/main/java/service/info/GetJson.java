package service.info;

import data.GraphicalDataImpl;
import spring.ApplicationContextContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by guardeec on 25.01.16.
 */
/*
сервлет для отправки сгенерированного json
впоследствии весь пакет generators будет заменен на модуль импорта данных визуализации
 */
@WebServlet(name = "GetJson", urlPatterns = "/getJson")
public class GetJson extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
            в запросе задаётся какой набор данных необходим:
            0 - неориентированный граф
            1 - ориентированный граф
            2 - неориентированный граф с глифами
            3 - ориентированный граф с глифами
            4 - матрица
            5 - карта деревьев
        */
        GraphicalDataImpl graphicalModels = (GraphicalDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
        int typeOfVisualisation = Integer.parseInt(request.getParameterMap().get("type")[0]);
        String json="";
        switch (typeOfVisualisation){
            case 0: {
                //граф
                json = graphicalModels.getGraph().getJSON();
                break;
            }
            case 1: {
                //глиф
                json = graphicalModels.getGlyph().getJSON();
                break;
            }
            case 2: {
                //матрица
                json = graphicalModels.getMatrix().getJSON(
                        Boolean.parseBoolean(request.getParameterMap().get("up")[0]),
                        Integer.parseInt(request.getParameterMap().get("metric")[0]),
                        Boolean.parseBoolean(request.getParameterMap().get("stroke")[0])
                );
                break;
            }
            case 3:{
                //карта Деревьев
                json = graphicalModels.getTreeMap().getJson();
                break;
            }
        }

        PrintWriter out = response.getWriter();
        out.append(json);
        out.close();
    }
}
