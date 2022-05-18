package uz.nt;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Login extends HttpServlet {

    List<User> userList = new ArrayList<>(Arrays.asList(
            new User("user", "123"),
            new User("java101dev", "123")

    ));

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
        User user = new User("user", "123");
        if (user.getUsername().equals(username) && user.getPassowrd().equals(password)){
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
