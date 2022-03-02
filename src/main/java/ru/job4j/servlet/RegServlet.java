package ru.job4j.servlet;

import ru.job4j.model.User;
import ru.job4j.store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    private final HbmStore hbmStore = new HbmStore();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        User user = hbmStore.findUserByEmail(email);
        if (user != null) {
            req.setAttribute("error", "Пользователь с таким e-mail уже существует");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
            return;
        }
        if (!password1.equals(password2)) {
            req.setAttribute("error", "Пароли не совпадают");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
            return;
        }
        User newUser = new User(name, email, password1);
        hbmStore.save(newUser);
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}
