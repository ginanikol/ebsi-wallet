<!DOCTYPE html>
<html>
<head>
    <title>Node Modules Example</title>
</head>
<body>

<h1>Webpack</h1>

<%--<script src="http://localhost:8080/digital-wallet/main.js"></script>--%>
<script type="module">

   import { util } from "http://localhost:8080/digital-wallet/main.js";

    const jwk2 = {
        kty: "EC",
        crv: "P-256",
        x: "B0w1caPbDtB8k3aQtt3MZrQt5r1dPZlRb-0gscih-VY",
        y: "qJmbN7u_iMFp1kV1M8eus-0eK88Wuz-K2OH_a_CoNMY",
    };

    const did2 = util.createDid(jwk2);

    console.log(did2);


</script>
</body>
</html>
