<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Key Pairs</title>

</head>
<body>
<c:forEach var="key" items="${keys}" varStatus="loop">
    <div>
        <h2>Key ${loop.index + 1}</h2>
        <div class="key-content">
            <div>
                <pre>Private Key: ${key.privateKey}</pre>
                <pre>Public Key: ${key.publicKey}</pre>
            </div>
        </div>
    </div>
</c:forEach>
</body>
</html>
