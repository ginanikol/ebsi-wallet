<!DOCTYPE html>
<html>
<head>
    <title>Node Modules Example</title>
<%--    <script type="module" src="http://localhost:8080/digital-wallet/theResolver.js"></script>--%>

    <script type="module" src="https://cdn.jsdelivr.net/npm/@cef-ebsi/ebsi-did-resolver@4.0.0-alpha.2/+esm"></script>

    <script type="module">
        // Wait for the library to load
        window.addEventListener('load', () => {
            // Create a resolver object using the imported functions and classes
            import('@cef-ebsi/ebsi-did-resolver').then((ebsiDidResolver) => {
                const { getResolver, Resolver } = ebsiDidResolver;

                // Create keyResolver and didResolver
                const keyResolver = getResolver();
                const didResolver = new Resolver(keyResolver);

                // Now, you can use keyResolver and didResolver as needed
                console.log('Key Resolver:', keyResolver);
                console.log('DID Resolver:', didResolver);
            }).catch((error) => {
                console.error('Error loading ebsi-did-resolver:', error);
            });
        });
    </script>
</head>
<body>

<h1> test.jsp</h1>



</body>
</html>
