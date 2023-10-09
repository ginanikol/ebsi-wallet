package gr.ihu.dw;

import gr.ihu.dw.service.EcKeysGeneratorService;
import gr.ihu.dw.service.Ed25519KeysGeneratorService;
import gr.ihu.dw.service.KeysGeneratorService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.KeyPair;

@SpringBootTest
public class KeysGenTest {

    @Disabled
    @Test
    void generateEd25519Keys() throws Exception {
        KeysGeneratorService ed25519keysGenerator = new Ed25519KeysGeneratorService();
        KeyPair keyPair = ed25519keysGenerator.generateKeyPair();
        String privateKey = ed25519keysGenerator.getPrivateKey(keyPair);
        String publicKey = ed25519keysGenerator.getPublicKey(keyPair);

        assert privateKey.toString().length() > 0;
        assert publicKey.toString().length() > 0;
    }

    @Disabled
    @Test
    void generatEcKeys() throws Exception {
        KeysGeneratorService ecKeysGenerator = new EcKeysGeneratorService();
        KeyPair keyPair = ecKeysGenerator.generateKeyPair();
        String privateKey = ecKeysGenerator.getPrivateKey(keyPair);
        String publicKey = ecKeysGenerator.getPublicKey(keyPair);

        assert privateKey.length() > 0;
        assert publicKey.length() > 0;
    }
}
