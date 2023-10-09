package gr.ihu.dw.service;

import gr.ihu.dw.util.Utils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

@Service("ed25519KeysGeneratorService")
public class Ed25519KeysGeneratorService implements KeysGeneratorService {

    Logger logger = LoggerFactory.getLogger(Ed25519KeysGeneratorService.class);

    private final static int KEY_SIZE = 256;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Override
    public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("Ed25519", "BC");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            logger.error("The algorithm does not exist. " + e.getMessage());
            throw new NoSuchAlgorithmException(e);
        }
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    @Override
    public String getPrivateKey(KeyPair keyPair) throws Exception {
        String privateKeyHex = Utils.convertKeyToHex(keyPair.getPrivate());
        return privateKeyHex;
    }

    @Override
    public String getPublicKey(KeyPair keyPair) throws Exception {
        String publicKeyStr = Utils.convertKeyToHex(keyPair.getPublic());
        return publicKeyStr;
    }

}
