package Servlets;

import DAO.*;
import DBUtil.DBUtilAdmin;
import DBUtil.DBUtilEmployee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.FileSystemLoopException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls profiles of manager and employee
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private DBUtilEmployee dbUtil;
    private final String db_url = "jdbc:mysql://localhost:3306/companyDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=CET";
    private Manager manager;
    private Employee employee;
    private String msg;


    /**
     * Initiate connection to database, calls DBUtilEmployee class
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try {

            dbUtil = new DBUtilEmployee(db_url);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }


    /**
     * Opens correct jsp files and fills table with data, that belongs to user
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

        int who = 0;

        dbUtil.setName(name);
        dbUtil.setPassword(password);
        RequestDispatcher dispatcher;
        List<Employee>employees = new ArrayList<>();

        if (validate(name, password)) {

            List<WorkLeave> workLeavesEmployee = null;
            List<LeavesArchive> workArchivesEmployee = null;
            List<ManagerView> workLeavesToAccept = null;
            List<ManagerView> workLeavesAccepted = null;


            try {
                who = dbUtil.checkWho();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(who==1)
                dispatcher = request.getRequestDispatcher("/bossView.jsp");
            else if(who==2)
                dispatcher = request.getRequestDispatcher("/employeeView.jsp");
            else
                dispatcher = request.getRequestDispatcher("/index.html");

            try {
                manager = dbUtil.getManagerData(name);
                employee = dbUtil.getEmployeeData(name);

                employees.add(employee);
                workLeavesEmployee = dbUtil.getWorkLeaves(employee.getId());
                workArchivesEmployee = dbUtil.getOldLeaves(employee.getId());
                workLeavesToAccept = dbUtil.getManagerView(manager.getDepartmentId(), "czeka na akceptację");
                workLeavesAccepted = dbUtil.getManagerView(manager.getDepartmentId(), "zaakceptowany");


            } catch (Exception e) {
                e.printStackTrace();
            }

            request.setAttribute("WORK_LEAVE", workLeavesEmployee);
            request.setAttribute("WORK_LEAVES", workLeavesToAccept);
            request.setAttribute("REST", workLeavesAccepted);
            request.setAttribute("LEAVE_ARCHIVE", workArchivesEmployee);
            request.setAttribute("USER",employees);
            dispatcher.forward(request, response);
        } else {

            dispatcher = request.getRequestDispatcher("/messages.jsp");
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
                    listEmployeeView(request, response);
                    break;
                case "ADD":
                    addLeave(request, response);
                    break;
                case "DELETE_EMPLOYEE":
                    deleteEmployeeLeave(request, response);
                    break;
                case "UPDATE_EMPLOYEE":
                    loadLeave(request,response);
                    break;
                case "UPDATE":
                    updateLeave(request,response);
                    break;
                case "ACCEPT_MANAGER":
                    updateAccept(request, response);
                    break;
                case "CANCEL_MANAGER":
                    updateCancel(request, response);
                    break;
                default:
                    listEmployeeView(request, response);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    /**
     * Modifies data of chosen work_leave entry
     * @param request
     * @param response
     * @throws Exception
     */
    private void updateLeave(HttpServletRequest request, HttpServletResponse response) throws
            Exception  {

        String name = dbUtil.getName();
        int idEmpl = dbUtil.getEmployeeData(name).getId();
        int id = Integer.parseInt(request.getParameter("id"));
        String startDate = request.getParameter("start");
        LocalDate date = LocalDate.parse(startDate);
        LocalDate endDate = date.plusDays(Integer.parseInt(request.getParameter("time")));
        String endDAYS = endDate.toString();
        int days = Integer.parseInt(request.getParameter("time"));
        String leaveType = request.getParameter("metric");


        WorkLeave workLeave = new WorkLeave(id,startDate,endDAYS,days,leaveType,"do modyfikacji",idEmpl);
        dbUtil.updateLeaveModify(id,workLeave);
        listEmployeeView(request,response);
    }



    /**
     * Changes leave_status from work_leave table to 'do usuniecia'
     * @param request
     * @param response
     * @throws Exception
     */
    private void deleteEmployeeLeave(HttpServletRequest request, HttpServletResponse response) throws
            Exception  {

        int id = Integer.parseInt(request.getParameter("leaveID"));

        dbUtil.updateLeave(id);
        listEmployeeView(request,response);
    }


    /**
     * Sends data of chosen entry into modify.jsp
     * @param request
     * @param response
     * @throws Exception
     */
    private void loadLeave(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String id = request.getParameter("leaveID");

        WorkLeave workLeave = dbUtil.getWorkLeave(Integer.parseInt(id));
        request.setAttribute("WORKLEAVE",workLeave);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/modify.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Creates new work_leave entry
     * @param request
     * @param response
     * @throws Exception
     */
    private void addLeave(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String startDate = request.getParameter("start");
        int days = Integer.parseInt(request.getParameter("time"));
        String leaveType = request.getParameter("metric");
        String name = dbUtil.getName();
        int employeeId = dbUtil.getEmployeeData(name).getId();
        int daysAv = dbUtil.getEmployeeData(name).getAvailableDays();

        DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
        LocalDate ld = LocalDate.parse(startDate, f );
        String endDate = String.valueOf(ld.plusDays(days));

        WorkLeave workLeave = new WorkLeave(startDate, endDate, days, leaveType, "czeka na akceptację", employeeId);

        if(days<daysAv) {
            dbUtil.addLeave(workLeave);
            listEmployeeView(request, response);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/takeVacation.jsp");
            msg="Masz za mało wolnego :(";
            request.setAttribute("msg", msg);
            dispatcher.forward(request, response);
        }

    }

    /**
     * Deletes chosen entry
     * @param request
     * @param response
     * @throws Exception
     */
    private void updateCancel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        dbUtil.updateLeaveCancel(id);
        listManagerView(request, response);
    }

    /**
     * Changes leave_status from work_leaves table into 'zaakceptowany'
     * @param request
     * @param response
     * @throws Exception
     */
    private void updateAccept(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        dbUtil.updateLeaveAccept(id);
        listManagerView(request, response);
    }

    /**
     * Creates data to employees tables
     * @param request
     * @param response
     * @throws Exception
     */
    private void listEmployeeView(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = dbUtil.getName();
        int id = dbUtil.getEmployeeData(name).getId();
        List<WorkLeave> workLeaves = dbUtil.getWorkLeaves(id);
        List<LeavesArchive> leavesArchive = dbUtil.getOldLeaves(id);
        List<Employee> employees = new ArrayList();
        Employee employee = dbUtil.getEmployeeData(name);
        employees.add(employee);

        request.setAttribute("WORK_LEAVE",workLeaves);
        request.setAttribute("LEAVE_ARCHIVE",leavesArchive);
        request.setAttribute("USER",employees);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
        dispatcher.forward(request,response);
    }
    /**
     * Creates data to manager tables
     * @param request
     * @param response
     * @throws Exception
     */
    private void listManagerView(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String name = dbUtil.getName();
        int id = dbUtil.getManagerData(name).getDepartmentId();
        List<ManagerView> managerViews = dbUtil.getManagerView(id,"czeka na akceptację");
        List<ManagerView> managerViews2 = dbUtil.getManagerView(id,"zaakceptowany");

        request.setAttribute("WORK_LEAVES",managerViews);
        request.setAttribute("REST",managerViews2);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/bossView.jsp");

        dispatcher.forward(request, response);

    }

    /**
     * Checks if user exists
     * @param name
     * @param pass
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

