<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>jwks</title>

</head>
<body>
<c:forEach var="jwk" items="${jwks}" varStatus="loop">
    <div>
        <h2>JWK ${loop.index + 1}</h2>
        <div class="jwk-content">
            <div>
                <!-- 1. put a break after each comma. 2. Remove first and last curly brace -->
                <pre><c:out value="${fn:replace(jwk.value.substring(1, jwk.value.length() - 1), ',', '<br>')}" escapeXml="false"/></pre>
                <b>Created:</b> ${jwk.timestamp}
            </div>
        </div>
    </div>
</c:forEach>
</body>
</html>
