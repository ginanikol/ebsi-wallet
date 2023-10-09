<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Keys</title>
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
<h1>Your Personal Keys</h1>

<button id="createKeyButton">Create New Key</button>

<table style="table-layout: fixed; width: 35%; margin-top: 20px; border-collapse: collapse;">
    <tr>
        <th style="background-color: #3498db; color: white; width: 10%; padding: 10px;"></th>
        <th style="background-color: #3498db; color: white; word-wrap: break-word; padding: 10px;">Private Key</th>
        <th style="background-color: #3498db; color: white; word-wrap: break-word; padding: 10px;">Public Key</th>
        <th style="background-color: #3498db; color: white; word-wrap: break-word; padding: 10px;"></th>
    </tr>
    <c:forEach var="key" items="${keys}" varStatus="loop">
        <tr>
            <td style="background-color: #f2f2f2; width: 10%; padding: 10px;">Key ${loop.index + 1}</td>
            <td style="background-color: #ecf0f1; word-wrap: break-word; padding: 10px;">${key.privateKey}</td>
            <td style="background-color: #ecf0f1; word-wrap: break-word; padding: 10px;">${key.publicKey}</td>
            <td style="background-color: #ecf0f1; word-wrap: break-word; padding: 10px;">
                <button class="deleteKeyButton" id="deleteKeyButton-${key.id}">Delete</button>
            </td>
        </tr>
    </c:forEach>
</table>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<%--<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>--%>

<script>
    $(document).ready(function () {
        function refreshKeys() {
            console.log("Refreshing keys...");
            $.get("/keys/all", function (data) {
                console.log("Received data:", data);
// Replace the existing list with the updated list from the server
                $("#keysTable").html(data);
            });
        }

// Attach a click event handler to the "Create New Key" button
        $("#createKeyButton").click(function () {
// Send an AJAX POST request to create a new key
            console.log("Creating a new key...");
            $.ajax({
                type: "POST",
                url: "/keys/createKey", // Adjust the URL to match your controller mapping
                success: function (data) {
// Handle the successful response here
                    console.log("Key created successfully:", data);
// Refresh the whole page after creating a new key
                    location.reload();
                },
                error: function (error) {
// Handle errors here
                    console.error("Error creating key:", error.responseText);
// You can display an error message to the user
                },
            });
        });

// Attach a click event handler to each delete key button
        $(".deleteKeyButton").click(function () {
// Get the key pair ID from the button ID
            var keyPairId = $(this).attr("id").split("-")[1];

            console.log("Deleting key pair with ID:", keyPairId);
            $.ajax({
                type: "DELETE",
                url: "/keys/delete/" + keyPairId,
                success: function (data) {
// Handle the successful response here
                    console.log("Key pair deleted successfully:", data);
                    location.reload();
                },
                error: function (error) {
                    console.error("Error deleting key pair:", error.responseText);
                },
            });
        });

// Initial load of the list of keys when the page loads
        refreshKeys();
    });


</script>

</body>
</html>
