package com.maBibliotheque.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maBibliotheque.service.AdherentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AdherentServlet extends HttpServlet {
    private AdherentService adherentService;

    @Override
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        adherentService = (AdherentService) context.getBean("adherentService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Map<String, Object>> adherents = adherentService.getAdherentsActifs();

        // Indiquer qu'on renvoie du JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Convertir l'objet Java en JSON
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(adherents);

        // Écrire le JSON dans la réponse
        resp.getWriter().write(json);
    }
}
