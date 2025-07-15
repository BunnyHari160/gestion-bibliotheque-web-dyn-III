package com.maBibliotheque.repository;

import com.maBibliotheque.model.LivreExemplaire;
import com.maBibliotheque.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LivreRepository {

    private static final String SQL = 
        "SELECT l.id_livre, l.titre, e.id_exemplaire, e.disponible, MAX(em.date_emprunt) AS dernier_emprunt " +
        "FROM livre l " +
        "JOIN exemplaire e ON l.id_livre = e.id_livre " +
        "LEFT JOIN emprunt em ON e.id_exemplaire = em.id_exemplaire " +
        "GROUP BY l.id_livre, l.titre, e.id_exemplaire, e.disponible " +
        "ORDER BY l.titre, e.id_exemplaire";

    public List<LivreExemplaire> findAllLivresEtExemplaires() {
        List<LivreExemplaire> liste = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int livreId = rs.getInt("id_livre");
                String titre = rs.getString("titre");
                int exemplaireId = rs.getInt("id_exemplaire");
                boolean disponible = rs.getBoolean("disponible");
                LocalDate dernierEmprunt = rs.getDate("dernier_emprunt") != null ?
                        rs.getDate("dernier_emprunt").toLocalDate() : null;

                liste.add(new LivreExemplaire(livreId, titre, exemplaireId, disponible, dernierEmprunt));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public List<LivreExemplaire> findExemplairesByLivreId(int idLivre) {
    List<LivreExemplaire> result = new ArrayList<>();

    try (Connection conn = DatabaseConnection.getConnection()) {
        String sql = "SELECT l.id_livre, l.titre, e.id_exemplaire, e.disponible, MAX(em.date_emprunt) AS dernier_emprunt " +
                     "FROM livre l " +
                     "JOIN exemplaire e ON l.id_livre = e.id_livre " +
                     "LEFT JOIN emprunt em ON e.id_exemplaire = em.id_exemplaire " +
                     "WHERE l.id_livre = ? " +
                     "GROUP BY l.id_livre, l.titre, e.id_exemplaire, e.disponible";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idLivre);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            result.add(new LivreExemplaire(
                rs.getInt("id_livre"),
                rs.getString("titre"),
                rs.getInt("id_exemplaire"),
                rs.getBoolean("disponible"),
                rs.getDate("dernier_emprunt") != null ? rs.getDate("dernier_emprunt").toLocalDate() : null
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return result;
}

public List<LivreExemplaire> getExemplairesByLivreId(int livreId) {
    List<LivreExemplaire> liste = new ArrayList<>();

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(
             "SELECT l.id_livre, l.titre, e.id_exemplaire, e.disponible, MAX(em.date_emprunt) AS dernier_emprunt " +
             "FROM livre l " +
             "JOIN exemplaire e ON l.id_livre = e.id_livre " +
             "LEFT JOIN emprunt em ON e.id_exemplaire = em.id_exemplaire " +
             "WHERE l.id_livre = ? " +
             "GROUP BY l.id_livre, l.titre, e.id_exemplaire, e.disponible")) {

        stmt.setInt(1, livreId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            liste.add(new LivreExemplaire(
                rs.getInt("id_livre"),
                rs.getString("titre"),
                rs.getInt("id_exemplaire"),
                rs.getBoolean("disponible"),
                rs.getDate("dernier_emprunt") != null ? rs.getDate("dernier_emprunt").toLocalDate() : null
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return liste;
}

public String getTitreById(int idLivre) {
    String titre = null;
    String sql = "SELECT titre FROM livre WHERE id_livre = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idLivre);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                titre = rs.getString("titre");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return titre;
}


}
