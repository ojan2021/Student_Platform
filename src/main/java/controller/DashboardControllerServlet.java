package controller;

import utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String first_name = req.getParameter("first_name");
        String last_name = req.getParameter("last_name");
        String age = req.getParameter("age");
        String city = req.getParameter("city");
        String country = req.getParameter("country");

        System.out.println(country);

        try {
            ResultSet resultSet = DBUtils.query("UPDATE users set first_name = '?',\n" +
                    "                 last_name = '?',\n" +
                    "                 age = ?,\n" +
                    "                 city = '?',\n" +
                    "                 country = '?'\n" +
                    "                 where email = 'ojan2021@ada.edu.az';",
                    new String[] {first_name,last_name,age,city,country});
            ResultSet resultSet2 = DBUtils.query("SELECT * from courses");
            System.out.println(resultSet2);
            if(resultSet != null && resultSet.next()    ){
                resp.sendRedirect("/ada_project_war");


            } else{
                System.out.println("No users exist within system");
                req.setAttribute("error_message", new String[] {"No user exists within the system"});
                resp.sendRedirect("/ada_project_war");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }


        super.doPost(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
