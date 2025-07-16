<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des adhérents (via API)</title>
    <style>
        table { border-collapse: collapse; width: 60%; margin-top: 20px; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
    <script>
        window.onload = function () {
            fetch('<%= request.getContextPath() %>/adherents')
                .then(response => response.json())
                .then(data => {
                    const tbody = document.getElementById("tbody");
                    data.forEach(adh => {
                        const row = `<tr>
                                        <td>${adh.id}</td>
                                        <td>${adh.nom}</td>
                                        <td>${adh.prenom}</td>
                                     </tr>`;
                        tbody.innerHTML += row;
                    });
                })
                .catch(error => {
                    document.getElementById("tbody").innerHTML = `<tr><td colspan="3">Erreur : ${error}</td></tr>`;
                });
        }
    </script>
</head>
<body>
<h2>Adhérents actifs (chargés via API JSON)</h2>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prénom</th>
        </tr>
    </thead>
    <tbody id="tbody">
        <tr><td colspan="3">Chargement...</td></tr>
    </tbody>
</table>
</body>
</html>
