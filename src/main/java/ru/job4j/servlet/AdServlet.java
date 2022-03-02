package ru.job4j.servlet;

import ru.job4j.model.Ad;
import ru.job4j.model.Cat;
import ru.job4j.model.CatBreed;
import ru.job4j.model.User;
import ru.job4j.store.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdServlet extends HttpServlet {

    private final HbmStore hbmStore = new HbmStore();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ad> ads = hbmStore.findAllAds();
        int photoName;
        if (ads.isEmpty()) {
            photoName = 1;
        } else {
            photoName = ads.get(ads.size() - 1).getId() + 1;
        }
        req.setAttribute("photoName", photoName);
        req.getRequestDispatcher("ad.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String emailOfCurrentUser = req.getParameter("userEmail");
        User currentUser = hbmStore.findUserByEmail(emailOfCurrentUser);
        String description = req.getParameter("description");
        String catName = req.getParameter("catName");
        String catAge = req.getParameter("catAge");
        String catBreed = req.getParameter("catBreed");
        String coatColor = req.getParameter("coatColor");
        String eyesColor = req.getParameter("eyesColor");
        CatBreed newCatBreed = hbmStore.findCatBreedById(Integer.parseInt(catBreed));
        Cat newCat = new Cat(catName, Integer.parseInt(catAge), coatColor, eyesColor, newCatBreed);
        hbmStore.save(newCat);
        Ad newAd = new Ad(description, true, newCat, currentUser);
        hbmStore.save(newAd);
        req.setAttribute("currentCatId", newCat.getId());
        req.getRequestDispatcher("upload.jsp").forward(req, resp);
    }
}
