package com.maBibliotheque.model;

import java.time.LocalDate;

public class LivreExemplaire {
    private int livreId;
    private String titre;
    private int exemplaireId;
    private boolean disponible;
    private LocalDate dernierEmprunt;

    // Constructeur
    public LivreExemplaire(int livreId, String titre, int exemplaireId, boolean disponible, LocalDate dernierEmprunt) {
        this.livreId = livreId;
        this.titre = titre;
        this.exemplaireId = exemplaireId;
        this.disponible = disponible;
        this.dernierEmprunt = dernierEmprunt;
    }

    // Getters
    public int getLivreId() { return livreId; }
    public String getTitre() { return titre; }
    public int getExemplaireId() { return exemplaireId; }
    public boolean isDisponible() { return disponible; }
    public LocalDate getDernierEmprunt() { return dernierEmprunt; }
}
