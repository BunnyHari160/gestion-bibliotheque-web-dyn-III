<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maBibliotheque.model.LivreExemplaire" %>

<html>
<head>
    <title>Liste des livres et exemplaires</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
    <h2>Livres & Exemplaires</h2>

    <table border="1">
        <tr>
            <th>Livre</th>
            <th>Exemplaire ID</th>
            <th>Disponibilité</th>
            <th>Dernier emprunt</th>
            <th>Action</th>
        </tr>
        <%
            List<LivreExemplaire> livres = (List<LivreExemplaire>) request.getAttribute("livres");
            if (livres != null) {
                for (LivreExemplaire le : livres) {
        %>
        <tr>
            <td><%= le.getTitre() %></td>
            <td><%= le.getExemplaireId() %></td>
            <td><%= le.isDisponible() ? "Disponible" : "Emprunté" %></td>
            <td><%= le.getDernierEmprunt() != null ? le.getDernierEmprunt() : "Aucun" %></td>
            <td>
                <a href="livres?id=<%= le.getLivreId() %>">Voir les exemplaires</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="5">Aucun livre disponible</td></tr>
        <% } %>
    </table>

    <p><a href="<%= request.getContextPath() %>/dashboard">Retour au dashboard</a></p>
</body>
</html>
