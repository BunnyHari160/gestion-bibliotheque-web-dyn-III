package com.maBibliotheque.servlet;

import com.maBibliotheque.service.LivreService;
import com.maBibliotheque.model.LivreExemplaire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class LivreServlet extends HttpServlet {

    private LivreService livreService;

    @Override
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        livreService = (LivreService) context.getBean("livreService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String idParam = req.getParameter("id");

    if (idParam != null) {
        int id = Integer.parseInt(idParam);
        List<LivreExemplaire> exemplaires = livreService.getExemplairesParLivre(id);
        req.setAttribute("exemplaires", exemplaires);

        String titreLivre = livreService.getTitreLivreById(id);
        req.setAttribute("titreLivre", titreLivre);

        req.getRequestDispatcher("/WEB-INF/jsp/exemplaires.jsp").forward(req, resp);
    } else {
        List<LivreExemplaire> liste = livreService.getLivresEtExemplaires();
        req.setAttribute("livres", liste);
        req.getRequestDispatcher("/WEB-INF/jsp/livres.jsp").forward(req, resp);
    }
}

}
