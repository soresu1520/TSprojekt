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

/**
 * Controls profile of admin
 */

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet{

    private DBUtilAdmin dbUtil;
    private final String db_url = "jdbc:mysql://localhost:3306/companyDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";
    private String msg;

    /**
     * Initiate connection to database, calls DBUtilAdminclass
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {

            dbUtil = new DBUtilAdmin(db_url);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Opens correct jsp files and fills tables with correct data
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        response.setContentType("text/html");

        String name = request.getParameter("login");
        String password = request.getParameter("password");

        dbUtil.setName(name);
        dbUtil.setPassword(password);

            if (validate(name, password) == true && name.equals("root")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/administratoView.jsp");

                List<WorkLeave> workLeavesToModify = null;
                List<WorkLeave> workLeavesToDelete = null;

                try {

                    workLeavesToDelete = dbUtil.getWorkLeavesToDelete();
                    workLeavesToModify = dbUtil.getWorkLeavesToModify();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                request.setAttribute("TO_MODIFY", workLeavesToModify);
                request.setAttribute("TO_DELETE", workLeavesToDelete);


                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/messages.jsp");
                msg="Niepoprawne dane logowania :(";
                request.setAttribute("message", msg);
                dispatcher.forward(request, response);
            }



    }

    /**
     * Checks what command was used on site and calls correct method
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {


        try {

            String command = request.getParameter("command");

            if (command == null)
                command = "LIST";

            switch (command) {

                case "LIST":
                    listLeaves(request, response);
                    break;
                case "CANCEL":
                    updateLeaveCancel(request, response);
                    break;
                case "UPDATE":
                    updateLeave(request, response);
                    break;
                case "DELETE":
                    deleteLeave(request, response);
                    break;

                default:
                    listLeaves(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    /**
     *Updates leave
     * @param request
     * @param response
     * @throws Exception
     */
    private void updateLeave(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));

        dbUtil.updateLeave(id);

        listLeaves(request, response);


    }

    /**
     * Changes status of entry to 'odrzucono modyfikacje'
     * @param request
     * @param response
     * @throws Exception
     */
    private void updateLeaveCancel(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int id = Integer.parseInt(request.getParameter("id"));
        dbUtil.updateLeaveCancel(id);
        listLeaves(request, response);

    }

    /**
     * Deletes leave
     * @param request
     * @param response
     * @throws Exception
     */
    private void deleteLeave(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        dbUtil.deleteLeave(id);
        listLeaves(request, response);

    }

    /**
     * Creates data for admin's tables on website
     * @param request
     * @param response
     * @throws Exception
     */
    private void listLeaves(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<WorkLeave> workLeaves = dbUtil.getWorkLeavesToDelete();
        List<WorkLeave> workLeaves2 = dbUtil.getWorkLeavesToModify();

        request.setAttribute("TO_DELETE", workLeaves);
        request.setAttribute("TO_MODIFY", workLeaves2);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/administratoView.jsp");

        dispatcher.forward(request, response);

    }

    /**
     * Checks if correct login data were given
     * @param name user's login
     * @param pass user's password
     * @return
     */
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
