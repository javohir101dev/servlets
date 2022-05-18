package uz.nt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(value = "/logout")
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("authApp", "");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect("/home");
    }
}
