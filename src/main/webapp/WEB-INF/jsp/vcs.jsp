<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>VCs</title>
</head>
<body>

<c:forEach var="vc" items="${vcs}">

        <div class="vcs-content" id="vc-${vc.id}" data-vc-id="${vc.id}">
            <div class="vc-data">
                <p><span class="vc-label">VC Type :</span> <span class="vc-values"> ${vc.vc.type}</span></p>
                <p><span class="vc-label">VC Id :</span> <span class="vc-values"> ${vc.vc.vcid}</span></p>
                <p><span class="vc-label"> Subject :</span> <span class="vc-values"> ${vc.sub}</span></p>
                <p><span class="vc-label"> VC Issuer :</span>  <span class="vc-values"> ${vc.vc.issuer}</span></p>
                <p><span class="vc-label">Issuance date :</span><span class="vc-values">  ${vc.iat}</span></p>
                <p> <span class="vc-label">VC expiration date :</span> <span class="vc-values"> ${vc.vc.expirationDate.time}</span></p>
                <p> <span class="vc-label">Credential Schema Id: </span> <span class="vc-values"> ${vc.vc.credentialSchema.csid}</span></p>
                <p> <span class="vc-label">Credential Schema Type: </span> <span class="vc-values"> ${vc.vc.credentialSchema.type}</span></p>
                <p> <span class="vc-label">Credential Subject Name: </span> <span class="vc-values"> ${vc.vc.credentialSubject.firstName}</span></p>
                <p> <span class="vc-label">Credential Subject Family Name: </span> <span class="vc-values"> ${vc.vc.credentialSubject.familyName}</span></p>
                <p> <button class="deleteVCbutton" data-vc-id="${vc.id}">Delete</button></p>
            </div>
            <div class="qr-code">
                <img src="data:image/png;base64,${vc.base64QRCode}" alt="QR Code">
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

<script>

    document.querySelectorAll('.deleteVCbutton').forEach(button => {
        button.addEventListener('click', function() {
            // Get the Verifiable Credential ID from the data attribute
            const vcId = this.getAttribute('data-vc-id');

            // Make an AJAX request to delete the Verifiable Credential
            fetch(`delete/${vcId}`, {
                method: 'DELETE',
            })
                .then(response => {
                    console.log("the vc id is : " + vcId);

                    if (!response.ok) {
                        throw new Error(`Failed to delete Verifiable Credential: ${response.status}`);
                    }
                    // Optionally, you can remove the deleted VC from the DOM
                    const vcElement = document.getElementById(`vc-${vcId}`);
                    vcElement.parentNode.removeChild(vcElement);
                })
                .catch(error => {
                    console.error(error);
                });
        });
    });
</script>
</body>
</html>
