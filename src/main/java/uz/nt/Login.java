package uz.nt;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("Login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String username = req.getParameter("username");

        System.out.println(username);
        System.out.println(password);

        PrintWriter writer = resp.getWriter();
        User ourUserTakenFromDatabase = new User("ourUserTakenFromDatabase", "123");

        if (ourUserTakenFromDatabase.getUsername().equals(username) && ourUserTakenFromDatabase.getPassowrd().equals(password)){
            Cookie cookie = new Cookie("authApp", username);
            cookie.setMaxAge(60 * 5);
            resp.addCookie(cookie);
            HttpSession session = req.getSession();
            session.setAttribute("authApp", username);
            resp.sendRedirect("/cabinet");
        }else {
            writer.write("Login or password wrong");
        }
    }
}
