package gr.ihu.dw.dao;

import gr.ihu.dw.service.EcKeysGeneratorService;
import gr.ihu.dw.service.Ed25519KeysGeneratorService;
import gr.ihu.dw.service.KeysGeneratorService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.security.KeyPair;

@SpringBootTest
public class KeyDataRepositoryTest {

    Logger logger = LoggerFactory.getLogger(KeyDataRepositoryTest.class);

    private final MongoTemplate mongoTemplate;

    public KeyDataRepositoryTest(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Test
    void saveECkeys() {
        KeysGeneratorService ecKeysGenerator = new EcKeysGeneratorService();
        KeyPair keyPair;
        try{
            keyPair = ecKeysGenerator.generateKeyPair();
            String privateKey = ecKeysGenerator.getPrivateKey(keyPair);
            String publicKey = ecKeysGenerator.getPublicKey(keyPair);

            KeyData keys = new KeyData();
            keys.setPrivateKey(privateKey);
            keys.setPublicKey(publicKey);

            mongoTemplate.insert(keys, "Keys");
        }
        catch (Exception e){
            logger.error("Could not save in db. " + e.getStackTrace());
        }
    }


    @Test
    void saveEd25519Keys() {
        KeysGeneratorService ed25519KeysGeneratorService = new Ed25519KeysGeneratorService();
        KeyPair keyPair;
        try{
            keyPair = ed25519KeysGeneratorService.generateKeyPair();
            String privateKey = ed25519KeysGeneratorService.getPrivateKey(keyPair);
            String publicKey = ed25519KeysGeneratorService.getPublicKey(keyPair);

            KeyData keys = new KeyData();
            keys.setPrivateKey(privateKey);
            keys.setPublicKey(publicKey);

            mongoTemplate.insert(keys, "Keys");
        }
        catch (Exception e){
            logger.error("Could not save in db. " + e.getStackTrace());
        }
    }



}
