<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.maBibliotheque.model.LivreExemplaire" %>

<html>
<head>
    <title>Exemplaires du livre</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<h1>Exemplaires du livre : <%= request.getAttribute("titreLivre") != null ? request.getAttribute("titreLivre") : "Inconnu" %></h1>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Disponible</th>
            <th>Dernier emprunt</th>
        </tr>
        <%
            List<LivreExemplaire> exemplaires = (List<LivreExemplaire>) request.getAttribute("exemplaires");
            if (exemplaires != null && !exemplaires.isEmpty()) {
                for (LivreExemplaire ex : exemplaires) {
        %>
        <tr>
            <td><%= ex.getExemplaireId() %></td>
            <td><%= ex.isDisponible() ? "Oui" : "Non" %></td>
            <td><%= ex.getDernierEmprunt() != null ? ex.getDernierEmprunt() : "Aucun" %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="3">Aucun exemplaire trouvé</td>
        </tr>
        <%
            }
        %>
    </table>

    <p><a href="<%= request.getContextPath() %>/livres">Retour à la liste des livres</a></p>
</body>
</html>
