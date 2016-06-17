package service.viz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by guardeec on 19.01.16.
 */
@WebServlet(name = "Graph", urlPatterns = "/graph")
public class Graph extends HttpServlet {
    /*
    Сервлет для отображения неориентированного графа
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("visualizationModels/Graph.html");
        view.forward(request, response);
    }
}
