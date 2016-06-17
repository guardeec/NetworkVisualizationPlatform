package service.dashboard;

import data.GraphicalDataImpl;
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
 * Created by Guardeec on 20.05.16.
 */
@WebServlet(name = "ProgressBar", urlPatterns = "/progressBar")
public class ProgressBar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(getHtml(req.getParameter("type")));
        out.close();
    }

    private String getHtml(String type){
        String message = "";
        double progress = 0.1;
        switch (type){
            //при запросе инфы о паттернах
            case "Patterns":{
                RawDataImpl storage = (RawDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
                message = "Паттернов 0";
                if (storage.getCsvPatternList().size()>0){
                    progress = 1;
                    message = "Паттернов "+storage.getCsvPatternList().size();
                }
                break;
            }
            //при запросе инфы о загруженных данных
            case "Data":{
                RawDataImpl storage = (RawDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
                message = "Загрузите файлы";
                if (storage.getMaxPatrolLog()!=null){
                    progress += 0.5;
                    message = "загрузите CSV";
                }
                if (storage.getCsv()!=null){
                    progress += 0.5;
                }
                if (progress>1){
                    message = "Загрузка успешна";
                    progress = 1;
                }
                break;
            }
            //при запросе инфы о результатах рендеринга визуализации (непонятная шляпа но заказчик хочет)
            case "Viz":{
                message = "Ожидание";
                GraphicalDataImpl storage = (GraphicalDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
                if (storage.getGraph()!=null){
                    message = "Рендеринг успешен";
                    progress = 1;
                }
                break;
            }
        }
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <meta name=\"robots\" content=\"noindex, nofollow\">\n" +
                "    <meta name=\"googlebot\" content=\"noindex, nofollow\">\n" +
                "\n" +
                "\n" +
                "    <script type=\"text/javascript\" src=\"https://rawgit.com/kimmobrunfeldt/progressbar.js/1.0.0/dist/progressbar.js\"></script>\n" +
                "    <style type=\"text/css\">\n" +
                "        #container {\n" +
                "            margin: 20px;\n" +
                "            width: 200px;\n" +
                "            height: 200px;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "    <title></title>\n" +
                "    <script type='text/javascript'>//<![CDATA[\n" +
                "    window.onload=function(){\n" +
                "// progressbar.js@1.0.0 version is used\n" +
                "// Docs: http://progressbarjs.readthedocs.org/en/1.0.0/\n" +
                "\n" +
                "        var bar = new ProgressBar.Circle(container, {\n" +
                "            color: '#aaa',\n" +
                "            // This has to be the same size as the maximum width to\n" +
                "            // prevent clipping\n" +
                "            strokeWidth: 4,\n" +
                "            trailWidth: 0,\n" +
                "            easing: 'easeInOut',\n" +
                "            duration: 1400,\n" +
                "            text: {\n" +
                "                autoStyleContainer: false\n" +
                "            },\n" +
                "            from: { color: '#aaa', width: 1 },\n" +
                "            to: { color: '#aaa', width: 2 },\n" +
                "            // Set default step function for all animate calls\n" +
                "            step: function(state, circle) {\n" +
                "                circle.path.setAttribute('stroke', state.color);\n" +
                "                circle.path.setAttribute('stroke-width', state.width);\n" +
                "\n" +
                "                var value = Math.round(circle.value() * 50);\n" +
                "                if (value === 0) {\n" +
                "                    circle.setText('');\n" +
                "                } else {\n" +
                "                    circle.setText(\""+message+"\");\n" +
                "                }\n" +
                "\n" +
                "            }\n" +
                "        });\n" +
                "        bar.text.style.fontFamily = '\"Raleway\", Helvetica, sans-serif';\n" +
                "        bar.text.style.fontSize = '2rem';\n" +
                "bar.text.style.textAlign = \"center\";"+
                "\n" +
                "        bar.animate("+Double.toString(progress)+");  // Number from 0.0 to 1.0\n" +
                "    }//]]>\n" +
                "\n" +
                "    </script>\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "<link href=\"https://fonts.googleapis.com/css?family=Raleway:400,300,600,800,900\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "<div id=\"container\"></div>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>\n" +
                "\n" +
                "\n";
    }
}
