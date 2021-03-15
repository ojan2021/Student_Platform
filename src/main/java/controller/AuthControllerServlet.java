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

        System.out.println("Request from browser reached the servlet...");
        HttpSession session = req.getSession();

        if (session.getAttribute(AuthControllerServlet.AUTHORIZE_FIELD) != null) {

            if (req.getParameter("action") != null && req.getParameter("action").equals("logout")) {
                session.removeAttribute(AuthControllerServlet.AUTHORIZE_FIELD);
                session.removeAttribute(AuthControllerServlet.CURRENT_USER);

            }
        }

        getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String first_name = req.getParameter("firstname");
        String last_name = req.getParameter("lastname");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        HttpSession session = null;

//        System.out.println(email+" "+password);
        if (email != null && password != null) {

            try {
                ResultSet resultSet = DBUtils.query("SELECT * FROM users WHERE email = ? and password = crypt(?, password) LIMIT 1", new String[]{email, password});

                if (resultSet != null) {
                    session = req.getSession();
                    session.setAttribute(AuthControllerServlet.AUTHORIZE_FIELD, true);
                    session.setAttribute(AuthControllerServlet.CURRENT_USER, email);


                    System.out.printf("User passed authentication. Trying to forward him...");
                    resp.sendRedirect("/ada_project_war");
                    resultSet.next();

                } else {
                    System.out.println("No users exist within system");
                    req.setAttribute("error_message", new String[]{"No user exists within the system"});
                    resp.sendRedirect("/ada_project_war");
                }
            } catch (SQLException throwables) {
                req.setAttribute("error_message", new String[]{"Error while connecting to the database"});
                throwables.printStackTrace();
            }

        } else if (email == null && password == null ) {
            ResultSet resultSet = null;
            try {
                resultSet = DBUtils.query("UPDATE users set first_name = ?, " +
                                "last_name = ?, city = ?, country = ? where email = 'ojan2021@ada.edu.az';",
                        new String[] {first_name,last_name,city,country});

                resp.sendRedirect("/ada_project_war");
                resultSet.next();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else {
            System.out.println("Data Validation error");
            req.setAttribute("error_message", new String[]{"Data Validation error"});

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
