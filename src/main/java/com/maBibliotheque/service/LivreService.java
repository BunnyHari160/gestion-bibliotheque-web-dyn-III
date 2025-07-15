package com.maBibliotheque.service;

import com.maBibliotheque.model.LivreExemplaire;
import com.maBibliotheque.repository.LivreRepository;

import java.util.List;

public class LivreService {

    private LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public List<LivreExemplaire> getLivresEtExemplaires() {
        return livreRepository.findAllLivresEtExemplaires();
    }

    public List<LivreExemplaire> getExemplairesParLivre(int idLivre) {
    return livreRepository.findExemplairesByLivreId(idLivre);
}

public String getTitreLivreById(int id) {
    return livreRepository.getTitreById(id);
}


}
