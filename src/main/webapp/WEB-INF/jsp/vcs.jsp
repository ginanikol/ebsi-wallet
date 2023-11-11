<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>VCs</title>
    </head>
    <body>

        <c:forEach var="vc" items="${vcs}" varStatus="loop">
            <div>
                <div class="vcs-content">
                    <div>
                        <p style="font-style: oblique">Verifiable Credential ${loop.index + 1}</p>
                        <p><span style="font-style: oblique"> Type </span> : ${vc.type}</p>
                        <p><span style="font-style: oblique"> Issuer </span> : ${vc.issuer}</p>
                        <p><span style="font-style: oblique">Issuance date </span>: ${vc.issuanceDate}</p>
                        <p><span style="font-style: oblique"> Valid from </span>: ${vc.validFrom}</p>
                        <p> <span style="font-style: oblique">Expires </span>: ${vc.expirationDate}</p>
                        <p> <span style="font-style: oblique">Issued </span>: ${vc.issued}</p>
                        <p> <span style="font-style: oblique">Credential Schema </span>: ${vc.credentialSchema}</p>
                        <p> <span style="font-style: oblique">Credential Subject </span>: ${vc.credentialSubject}</p>
                    </div>
                </div>
            </div>
        </c:forEach>

        <br>
        <form action="requestCredentials" method="post">
            <label for="did">Select a DID:</label>
            <select name="did" id="did">
                <c:forEach var="did" items="${dids}">
                    <option value="${did.value}">${did.value}</option>
                </c:forEach>
            </select>
            <button type="submit">Request Credentials</button>
        </form>

    </body>
</html>