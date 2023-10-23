import { getResolver } from "@cef-ebsi/key-did-resolver";
import { util } from "@cef-ebsi/key-did-resolver";
import {Resolver} from "did-resolver";

// getResolver will return an object with a key/value pair of { "key": resolver } where resolver is a function used by the generic DID resolver.
const keyResolver = getResolver();
const didResolver = new Resolver(keyResolver);

didResolver
    .resolve(
        "did:key:z5AZQq7DEXdQHS6FHmeUfspRoZncMJwi2ChvKyNUNiTBJzmoZHptYHsJKjyz2oRgR9tVx16J292D6aoXoaimZvcuLT2YYcJhsmCSwZ9UXtZzr88hKTtJXz1oX69HQbkBUPYB3xqZdVGEyo45ztPun4PcaFLffvg5ZUKsP3zgptWAoTy6jWxp3VhGbfA36AkZiLEe6A48PqV55AecYY6TpWoTD4QdSg4Dwaiwo6U8cjdbSuLGMQL3K2mJV2XSi4S7QtcJoPsxvHEoKisN"
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
    x: "103844653912605496737232059191089405327851447790199748658798573111195341414667",
    y: "38004373292326482181958585395616043984979484999638695695757834158908448549969",
};

// const jwk2 = {
//     kty: "EC",
//     crv: "P-256",
//     x: "B0w1caPbDtB8k3aQtt3MZrQt5r1dPZlRb-0gscih-VY",
//     y: "qJmbN7u_iMFp1kV1M8eus-0eK88Wuz-K2OH_a_CoNMY",
// };

const did = util.createDid(jwk);
// const did2 = util.createDid(jwk2);

console.log("did1 is " + did);
// console.log("did2 is " + did2);