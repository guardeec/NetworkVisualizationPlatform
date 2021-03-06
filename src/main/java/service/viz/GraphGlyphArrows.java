package service.viz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by guardeec on 28.01.16.
 */
@WebServlet(name = "GraphGlyphArrows", urlPatterns = "/graphGlyphArrows")
public class GraphGlyphArrows extends HttpServlet {
    /*
    Сервлет для отображения ориентированного графа с глифами
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("visualizationModels.GraphGlyphArrows.html");
        view.forward(request, response);
    }
}
