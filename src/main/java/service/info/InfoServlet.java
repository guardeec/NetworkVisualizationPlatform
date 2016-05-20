package service.info;

import spring.ApplicationContextContainer;
import data.RawDataImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Guardeec on 28.04.16.
 */
@WebServlet (name = "/infoServlet")
public class InfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RawDataImpl storage = (RawDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
        PrintWriter out = resp.getWriter();
        out.write("MaxPatrol: "+storage.getMaxPatrolLog()+"\n"+"CSV: "+storage.getCsv());
    }
}
