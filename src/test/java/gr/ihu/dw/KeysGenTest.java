package gr.ihu.dw;

import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import gr.ihu.dw.dao.JWKdata;
import gr.ihu.dw.dao.JWkdataRepository;
import gr.ihu.dw.service.EcKeysGeneratorService;
import gr.ihu.dw.service.KeysGeneratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class KeysGenTest {


    private final JWkdataRepository jWkdataRepository;

    @Autowired
    public KeysGenTest(JWkdataRepository jWkdataRepository) {
        this.jWkdataRepository = jWkdataRepository;
    }

    @Test
    void generateAndSaveEcKeysAndJWK() throws Exception {
        KeyPair keyPair = createKeyPair();

        JWK jwk = new ECKey.Builder(Curve.P_256, (ECPublicKey) keyPair.getPublic())
                .privateKey((ECPrivateKey) keyPair.getPrivate())
                .build();

        JWKdata jwKdata = new JWKdata();
        jwKdata.setValue(String.valueOf(jwk));
        jWkdataRepository.save(jwKdata);

        assertNotNull(jwKdata.getId(), "jwKdata should have an ID after saving.");

    }

    private static KeyPair createKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeysGeneratorService ecKeysGenerator = new EcKeysGeneratorService();
        return ecKeysGenerator.generateKeyPair();
    }

}
