package Servlets;

import DAO.WorkLeave;
import DBUtil.DBUtilAdmin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.List;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet{

    private DBUtilAdmin dbUtil;
    private final String db_url = "jdbc:mysql://localhost:3306/companyDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {

            dbUtil = new DBUtilAdmin(db_url);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        response.setContentType("text/html");

        String name = request.getParameter("login");
        String password = request.getParameter("password");

        dbUtil.setName(name);
        dbUtil.setPassword(password);

        if (validate(name, password)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/administratoView.jsp");

            List<WorkLeave> workLeavesToModify = null;
            List<WorkLeave> workLeavesToDelete = null;

            try {

               workLeavesToDelete = dbUtil.getWorkLeavesToDelete();
               workLeavesToModify = dbUtil.getWorkLeavesToModify();

            } catch (Exception e) {
                e.printStackTrace();
            }

            // dodanie listy do obiektu zadania
            request.setAttribute("ToModify", workLeavesToModify);
            request.setAttribute("ToDelete", workLeavesToDelete);


            dispatcher.forward(request, response);
        } else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
            dispatcher.include(request, response);
        }


    }
    // do pracownika
    // tu tylko status
    private void updatePhone(HttpServletRequest request, HttpServletResponse response) throws Exception {

        /* LocalDate date = LocalDate.parse(request.getParameter("start"));
        LocalDate end = date.plusDays(Integer.parseInt(request.getParameter("time")));

        int id = Integer.parseInt(request.getParameter("id"));
        String startDate = request.getParameter("start");
        String endDate = end.toString();
        int days = Integer.parseInt(request.getParameter("time"));
        String leaveType = request.getParameter("metric");
        String leaveStatus = "zaakceptowany";
        int employeeID = 1;  */


        int id = Integer.parseInt(request.getParameter("id"));

        dbUtil.updateLeave(id);


        listLeavesModify(request, response);

    }

    private void deleteLeave(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // odczytanie danych z formularza
        String id = request.getParameter("id");

        // usuniecie telefonu z BD
        dbUtil.deleteLeave(id);

        // wyslanie danych do strony z lista telefonow
        listLeaves(request, response);

    }

    private void listLeaves(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<WorkLeave> workLeaves = dbUtil.getWorkLeavesToDelete();

        // dodanie listy do obiektu zadania
        request.setAttribute("ToDelete", workLeaves);

        // dodanie request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/administratoView.jsp");

        // przekazanie do JSP
        dispatcher.forward(request, response);

    }

    private void listLeavesModify(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<WorkLeave> workLeaves = dbUtil.getWorkLeavesToDelete();

        // dodanie listy do obiektu zadania
        request.setAttribute("ToModify", workLeaves);

        // dodanie request dispatcher
        RequestDispatcher dispatcher = request.getRequestDispatcher("/administratoView.jsp");

        // przekazanie do JSP
        dispatcher.forward(request, response);

    }


    private boolean validate(String name, String pass) {
        boolean status = false;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(db_url, name, pass);
            status = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

}
