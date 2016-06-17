package service.dashboard;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Guardeec on 20.05.16.
 */
@WebServlet (name = "DashboardGlyph", urlPatterns = "/dashboardGlyph")
public class Glyph extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher view = req.getRequestDispatcher("dashboard/GraphGlyph.html");
        view.forward(req, response);
    }
}
