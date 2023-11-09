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
                <a href="javascript:void(0);" class="resolve-did" onclick="resolveDID('${did.value}')">${did.value}</a>
                <p>Created: ${did.timestamp}</p>
                <p> Method: key</p>
            </div>
        </div>
    </div>
</c:forEach>
</body>
</html>