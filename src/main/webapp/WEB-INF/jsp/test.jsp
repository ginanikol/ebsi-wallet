<!DOCTYPE html>
<html>
<head>
    <title>Node Modules Example</title>
</head>
<body>

<h1>test jsp</h1>


<!-- Add a button to generate a new DID -->
<button id="generateDIDButton">/generate-did</button>
<div id="generateResult"></div>

<script>
    document.getElementById("generateDIDButton").addEventListener("click", function() {
        // Make an HTTP GET request to the /generate-did endpoint
        fetch('http://localhost:8000/generate-did', {
            method: 'GET',
        })
            .then(response => response.json())
            .then(data => {
                // Handle the response
                document.getElementById("generateResult").innerHTML = "Generated DID: " + data.did;
            })
            .catch(error => {
                console.error("Error:", error);
                document.getElementById("generateResult").innerHTML = "Error generating DID.";
            });
    });
</script>



<button id="createDIDButton">create-did-from-jwk</button>
<div id="result"></div>

<script>
    document.getElementById("createDIDButton").addEventListener("click", function() {
        // Define the JWK object you want to send
        const jwk = {
            kty: "EC",
            crv: "P-256",
            x: "ngy44T1vxAT6Di4nr-UaM9K3Tlnz9pkoksDokKFkmNc",
            y: "QCRfOKlSM31GTkb4JHx3nXB4G_jSPMsbdjzlkT_UpPc",
        };

        // Make an HTTP POST request to the /create-did-from-jwk endpoint
        console.log(jwk);
        fetch('http://localhost:8000/create-did-from-jwk', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(jwk)
        })
            .then(response => response.json())
            .then(data => {
                // Handle the response
                document.getElementById("result").innerHTML = "Created DID: " + data.did;
            })
            .catch(error => {
                console.error("Error:", error);
                document.getElementById("result").innerHTML = "Error creating DID.";
            });
    });
</script>

</body>
</html>
