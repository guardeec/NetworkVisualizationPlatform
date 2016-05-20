package service.upload;

import data.RawDataImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import spring.ApplicationContextContainer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Guardeec on 26.04.16.
 */
@WebServlet (name = "/uploadCSVServlet")
public class UploadCSV extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("upload/UploadCSV.html");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RawDataImpl csvStorage = (RawDataImpl) ApplicationContextContainer.getInstance().getFactory().getBean("store");
        PrintWriter out = resp.getWriter();
        String file = "";
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if(isMultipart){
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try{
                List<FileItem> fields = upload.parseRequest(req);
                Iterator<FileItem> it = fields.iterator();
                while (it.hasNext()) {
                    FileItem fileItem = it.next();
                    file+=fileItem.getString();
                }
            }catch (FileUploadException e) {
                e.printStackTrace();
            }
        }

        File csvFile = new File("csv.csv");
        if (!csvFile.exists()){
            csvFile.createNewFile();
        }else {
            out = new PrintWriter(csvFile.getAbsoluteFile());
            out.write(file);
            out.close();
        }
        boolean success = csvStorage.setCsv(csvFile, "testPattern");

        if (success){
            RequestDispatcher view = req.getRequestDispatcher("upload/UploadCSV.html");
            view.forward(req, resp);
        }else {
        }
    }
}
