package Servlets;

import DAO.Employee;
import DBUtil.DBUtilRegister;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/MakeAccountServlet")
public class MakeAccountServlet extends HttpServlet {

    private DataSource dataSource;
    private DBUtilRegister dbUtil;
    private String msg;

    public MakeAccountServlet() {

        Context initCtx = null;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            // Look up our data source
            dataSource = (DataSource)
                    envCtx.lookup("jdbc/companydb"); //z context.xml

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();

        try {

            dbUtil = new DBUtilRegister(dataSource);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            addEmployee(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/messages.jsp");
            msg="Niepoprawne dane rejestracji :(";
            request.setAttribute("message", msg);
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int leaveDays;
        int years;
        int idDep;

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String position = request.getParameter("job");
        int yearsJob = Integer.parseInt(request.getParameter("years"));
        String school = request.getParameter("customRadio");
        String dep = request.getParameter("metric");

        switch(dep){
            case "Dział 1":
                idDep=1;
                break;

            case "Dział 2":
                idDep=2;
                break;

            case "Dział 3":
                idDep=3;
                break;

            default:
                idDep=1;
        }

        switch (school) {
            case "Zasadnicza szkoła zawodowa":
                years=yearsJob+3;
                break;

            case "Średnia szkoła zawodowa":
                years=yearsJob+5;
                break;

            case "Średnia szkoła ogólnokształcąca":
                years=yearsJob+4;
                break;

            case "Szkoła policealna":
                years=yearsJob+6;
                break;

            case "Szkoła wyższa":
                years=yearsJob+8;
                break;

            default:
                years=yearsJob;
        }

        if (years>10)
            leaveDays=26;
        else
            leaveDays=20;

        Employee employee = new Employee(login, password, firstName, lastName, email, idDep,
                position, leaveDays, leaveDays);

        dbUtil.addEmployee(employee);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
        dispatcher.include(request, response);

    }



}
