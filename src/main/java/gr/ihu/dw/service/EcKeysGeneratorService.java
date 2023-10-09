package gr.ihu.dw.service;

import com.nimbusds.jose.jwk.Curve;
import gr.ihu.dw.util.Utils;
import org.springframework.stereotype.Service;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@Service("ecKeysGeneratorService")
public class EcKeysGeneratorService implements KeysGeneratorService {
    @Override
    public KeyPair generateKeyPair() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");
        generator.initialize(Curve.P_256.toECParameterSpec());
        KeyPair keyPair = generator.generateKeyPair();
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
