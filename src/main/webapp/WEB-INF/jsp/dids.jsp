<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dids</title>
    <style>
        /* Add your CSS styles here */
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
    </style>
</head>
<body>
<h1>DIDs</h1>

<script>
    // Define your resolver code here
    document.addEventListener("DOMContentLoaded", function () {
        const keyResolver = getResolver();
        const didResolver = new Resolver(keyResolver);

        didResolver
            .resolve(
                "did:key:z2dmzD81cgPx8Vki7JbuuMmFYrWPgYoytykUZ3eyqht1j9KbsEYvdrjxMjQ4tpnje9BDBTzuNDP3knn6qLZErzd4bJ5go2CChoPjd5GAH3zpFJP5fuwSk66U5Pq6EhF4nKnHzDnznEP8fX99nZGgwbAh1o7Gj1X52Tdhf7U4KTk66xsA5r"
            )
            .then(function (result) {
                // Use the result to handle DID resolution
                console.log(result);

                // Table structure
                const table = document.createElement('table');
                table.style = "table-layout: fixed; width: 35%; margin-top: 20px; border-collapse: collapse;";
                const tableHeader = `
          <tr>
            <th style="background-color: #3498db; color: white; width: 10%; padding: 10px;"></th>
            <th style="background-color: #3498db; color: white; word-wrap: break-word; padding: 10px;">DID</th>
            <th style="background-color: #3498db; color: white; word-wrap: break-word; padding: 10px;">DID Document</th>
          </tr>
        `;

                // Loop to create table rows
                for (let i = 0; i < 5; i++) { // Replace '5' with the number of rows you need
                    const tableRow = `
            <tr>
              <td style="background-color: #f2f2f2; width: 10%; padding: 10px;">DID ${i + 1}</td>
              <td style="background-color: #ecf0f1; word-wrap: break-word; padding: 10px;">${result}</td>
              <td style="background-color: #ecf0f1; word-wrap: break-word; padding: 10px;">
                <div id="resolvedDID_${i}"></div>
                <!-- Additional script for each DID resolution if needed -->
              </td>
            </tr>
          `;
                    table.innerHTML += tableRow;
                }

                // Add the table to the body
                document.body.appendChild(table);
            })
            .catch(function (error) {
                // Handle any errors that occur during resolution
                console.error(error);
            });
    });
</script>
</body>
</html>
