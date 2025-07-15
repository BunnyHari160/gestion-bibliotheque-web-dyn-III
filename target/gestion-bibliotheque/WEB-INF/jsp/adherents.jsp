<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<html>
<head>
    <title>Liste des adhérents actifs</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<h2>Adhérents actifs</h2>
<table border="1">
    <tr>
        <th>ID</th><th>Nom</th><th>Prénom</th>
    </tr>
    <%
        List<Map<String, Object>> adherents = (List<Map<String, Object>>) request.getAttribute("adherents");
        if (adherents != null && !adherents.isEmpty()) {
            for (Map<String, Object> a : adherents) {
    %>
    <tr>
        <td><%= a.get("id") %></td>
        <td><%= a.get("nom") %></td>
        <td><%= a.get("prenom") %></td>
    </tr>
    <%
            }
        } else {
    %>
    <tr><td colspan="3">Aucun adhérent actif trouvé</td></tr>
    <% } %>
</table>

<p><a href="<%= request.getContextPath() %>/dashboard">Retour au dashboard</a></p>
</body>
</html>
