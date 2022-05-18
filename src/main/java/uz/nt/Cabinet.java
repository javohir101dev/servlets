package uz.nt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/cabinet")
public class Cabinet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        Cookie[] cookies = req.getCookies();
        String username = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authApp")) {
                    username = cookie.getValue();
                }
            }
        }
        HttpSession session = req.getSession();
        String sessionAttribute = (String)session.getAttribute("authApp");
        if (username != null && !username.equals("") && sessionAttribute.equals(username)) {

            resp.setContentType("text/html");
            writer.write("<h1>Welcome cabinet " + username + "</h1>");
            writer.write("<button><a href='/logout'>Logout</a>");
        } else {
            resp.sendRedirect("/login");
        }
    }
}
