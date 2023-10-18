import { getResolver } from "@cef-ebsi/key-did-resolver";
import { util } from "@cef-ebsi/key-did-resolver";
import {Resolver} from "did-resolver";

// getResolver will return an object with a key/value pair of { "key": resolver } where resolver is a function used by the generic DID resolver.
const keyResolver = getResolver();
const didResolver = new Resolver(keyResolver);

didResolver
    .resolve(
        "did:key:z2dmzD81cgPx8Vki7JbuuMmFYrWPgYoytykUZ3eyqht1j9KbsEYvdrjxMjQ4tpnje9BDBTzuNDP3knn6qLZErzd4bJ5go2CChoPjd5GAH3zpFJP5fuwSk66U5Pq6EhF4nKnHzDnznEP8fX99nZGgwbAh1o7Gj1X52Tdhf7U4KTk66xsA5r"
    )
    .then((doc) => console.log(doc));

//You can also use ES7 async/await syntax
const doc = await didResolver.resolve(
    "did:key:z2dmzD81cgPx8Vki7JbuuMmFYrWPgYoytykUZ3eyqht1j9KbonQbB7P27sVYie1ATFYLmg9n6qkuPva28HaryVM99DifMRB4dvNK2rMRJxL5TJhS13MiQreX4AWprZwMZxZTpdFTtxHVRNrXLABCrHAUTR7R4ZTKLQ5btHEEordyCXS6vY"
);
console.log(doc);


const jwk = {
    kty: "EC",
    crv: "P-256",
    x: "ngy44T1vxAT6Di4nr-UaM9K3Tlnz9pkoksDokKFkmNc",
    y: "QCRfOKlSM31GTkb4JHx3nXB4G_jSPMsbdjzlkT_UpPc",
};

const jwk2 = {
    kty: "EC",
    crv: "P-256",
    x: "B0w1caPbDtB8k3aQtt3MZrQt5r1dPZlRb-0gscih-VY",
    y: "qJmbN7u_iMFp1kV1M8eus-0eK88Wuz-K2OH_a_CoNMY",
};

const did = util.createDid(jwk);
const did2 = util.createDid(jwk2);

console.log("did1 is " + did);
console.log("did2 is " + did2);