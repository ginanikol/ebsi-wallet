package gr.ihu.dw;

import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import gr.ihu.dw.dao.JWKdata;
import gr.ihu.dw.dao.JWkdataRepository;
import gr.ihu.dw.dao.KeyData;
import gr.ihu.dw.dao.KeyDataRepository;
import gr.ihu.dw.service.EcKeysGeneratorService;
import gr.ihu.dw.service.Ed25519KeysGeneratorService;
import gr.ihu.dw.service.KeysGeneratorService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class KeysGenTest {

    private final KeyDataRepository keyDataRepository;
    private final JWkdataRepository jWkdataRepository;

    @Autowired
    public KeysGenTest(KeyDataRepository keyDataRepository, JWkdataRepository jWkdataRepository) {
        this.keyDataRepository = keyDataRepository;
        this.jWkdataRepository = jWkdataRepository;
    }

    @Disabled
    @Test
    void generateEd25519Keys() throws Exception {
        KeysGeneratorService ed25519keysGenerator = new Ed25519KeysGeneratorService();
        KeyPair keyPair = ed25519keysGenerator.generateKeyPair();
        String privateKey = ed25519keysGenerator.getPrivateKey(keyPair);
        String publicKey = ed25519keysGenerator.getPublicKey(keyPair);

        assert !privateKey.isEmpty();
        assert !publicKey.isEmpty();
    }

    @Test
    void generateAndSaveEcKeysAndJWK() throws Exception {
        KeyPair keyPair = createKeyPair();
        KeyData keyData = new KeyData();

        String privateKeyBase64 = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        String publicKeyBase64 = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());

        keyData.setPrivateKey(privateKeyBase64);
        keyData.setPublicKey(publicKeyBase64);
        keyDataRepository.save(keyData);

        JWK jwk = new ECKey.Builder(Curve.P_256, (ECPublicKey) keyPair.getPublic())
                .privateKey((ECPrivateKey) keyPair.getPrivate())
                .build();

        JWKdata jwKdata = new JWKdata();
        jwKdata.setValue(String.valueOf(jwk));
        jwKdata.setKeysId(keyData.getId());
        jWkdataRepository.save(jwKdata);

        assertNotNull(jwKdata.getId(), "jwKdata should have an ID after saving.");

    }

    private static KeyPair createKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeysGeneratorService ecKeysGenerator = new EcKeysGeneratorService();
        return ecKeysGenerator.generateKeyPair();
    }

    private static KeyData createKeyData(KeyPair keyPair) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyData keyData = new KeyData();
        keyData.setPrivateKey(String.valueOf(keyPair.getPrivate()));
        keyData.setPublicKey(String.valueOf(keyPair.getPublic()));
        return keyData;
    }

}
