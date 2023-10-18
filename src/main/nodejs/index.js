import { EbsiWallet } from "@cef-ebsi/wallet-lib";
import { calculateJwkThumbprint, exportJWK, generateKeyPair } from "jose";
import { base64url } from "multiformats/bases/base64";
import { getResolver } from "@cef-ebsi/key-did-resolver";
import { util } from "@cef-ebsi/key-did-resolver";
import { Resolver } from "did-resolver";


async function displayDID() {
    const { publicKey } = await generateKeyPair("ES256K");
    const jwk = await exportJWK(publicKey);
    const did = EbsiWallet.createDid("NATURAL_PERSON", jwk);

    const keyResolver = getResolver();
    const didResolver = new Resolver(keyResolver);

    const doc = await didResolver.resolve(did);
    const outputElement = document.getElementById("output");
    outputElement.textContent = JSON.stringify(doc, null, 2);
}

displayDID();
