<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dids</title>
</head>
<body>
<c:forEach var="did" items="${dids}" varStatus="loop">
    <div>
        <h2>DID ${loop.index + 1}</h2>
        <div class="dids-content">
            <div>
                <pre>${did.value}</pre>
            </div>
        </div>
    </div>
</c:forEach>
</body>
</html>
