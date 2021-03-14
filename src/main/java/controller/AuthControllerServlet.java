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

public class AuthControllerServlet extends HttpServlet {

    private static String AUTHORIZE_FIELD = "is_authorize";
    private static String CURRENT_USER = "current_username";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        DBUtils.execQuery("SELECT * FROM users");

        System.out.println("Request from browser reached the servlet...");
        HttpSession session = req.getSession();

        if (session.getAttribute(AuthControllerServlet.AUTHORIZE_FIELD) != null) {

            if (req.getParameter("action") != null && req.getParameter("action").equals("logout")) {
                session.removeAttribute(AuthControllerServlet.AUTHORIZE_FIELD);
                session.removeAttribute(AuthControllerServlet.CURRENT_USER);

            }
        }

        getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username != null && password != null && username.length() > 3 && password.length() >= 5) {

            try {
                ResultSet resultSet = DBUtils.query("SELECT COUNT(1) as user_exists FROM users WHERE username = ? and password = ? LIMIT 1", new String[] {username, password});
                if(resultSet != null && resultSet.next() && resultSet.getInt("user_exists") > 0){
                    HttpSession session = req.getSession();
                    session.setAttribute(AuthControllerServlet.AUTHORIZE_FIELD, true);
                    session.setAttribute(AuthControllerServlet.CURRENT_USER, username);
                    System.out.printf("User passed authentication. Trying to forward him...");
                    resp.sendRedirect("/ada_project_war");

                } else{
                    System.out.print("No users exist within system");
                    req.setAttribute("error_message", new String[] {"No user exists within the system"});
                }
            } catch (SQLException throwables) {
                req.setAttribute("error_message", new String[] {"Error while connecting to the database"});
                throwables.printStackTrace();
            }

        } else {
            System.out.println("Data Validation error");
            req.setAttribute("error_message", new String[] {"Data Validation error"});

        }
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
