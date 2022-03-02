package ru.job4j.servlet;

import ru.job4j.store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CatsServlet extends HttpServlet {

    private final HbmStore hbmStore = new HbmStore();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("adsAboutCats", hbmStore.findAllAds());
        req.getRequestDispatcher("cats.jsp").forward(req, resp);
    }
}
