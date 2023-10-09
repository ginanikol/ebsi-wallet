package gr.ihu.dw.controller;

import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import gr.ihu.dw.dao.JWKdata;
import gr.ihu.dw.dao.JWkdataRepository;
import gr.ihu.dw.dao.KeyData;
import gr.ihu.dw.dao.KeyDataRepository;
import gr.ihu.dw.service.KeysGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.List;

@Controller
@RequestMapping("/keys")
public class KeyDataController {

    private final KeyDataRepository keyDataRepository;
    private final JWkdataRepository jWkdataRepository;
    private final KeysGeneratorService keysGeneratorService;

    Logger logger = LoggerFactory.getLogger(KeyDataController.class);

    @Autowired
    public KeyDataController(KeyDataRepository keyDataRepository,
                             JWkdataRepository jWkdataRepository, @Qualifier("ecKeysGeneratorService") KeysGeneratorService keysGeneratorService) {
        this.keyDataRepository = keyDataRepository;
        this.jWkdataRepository = jWkdataRepository;
        this.keysGeneratorService = keysGeneratorService;
    }

    @GetMapping("/all")
    public String getAllKeys(Model model) {
        List<KeyData> keys = keyDataRepository.findAll();
        model.addAttribute("keys", keys);
        return "allKeys";
    }

    @PostMapping("/createKey")
    public ResponseEntity<String> createKey() {

        try{
            KeyPair keyPair = keysGeneratorService.generateKeyPair();
            KeyData keyData = new KeyData();
            keyData.setPrivateKey(String.valueOf(keyPair.getPrivate()));
            keyData.setPublicKey(String.valueOf(keyPair.getPublic()));
            keyDataRepository.save(keyData);

            JWK jwk = new ECKey.Builder(Curve.P_256, (ECPublicKey) keyPair.getPublic())
                    .privateKey((ECPrivateKey) keyPair.getPrivate())
                    .build();

            JWKdata jwKdata = new JWKdata();
            jwKdata.setValue(String.valueOf(jwk));
            jWkdataRepository.save(jwKdata);

            logger.info("key created");
            return ResponseEntity.ok("New key created successfully! " + keyData);
        }catch (Exception e){
            logger.error("Could not create new keys. " + e.getMessage(), e);
            return ResponseEntity.badRequest().body("Failed to create new keys: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteKey(@PathVariable("id") String id) {
        try {
            KeyData keyData = keyDataRepository.findById(String.valueOf(id)).get();
            logger.info("Deleting key with ID {} ", id);
            keyDataRepository.delete(keyData);
            return ResponseEntity.ok("Key deleted successfully!");
        } catch (Exception e) {
            logger.error("Could not delete key. " + e.getMessage(), e);
            return ResponseEntity.badRequest().body("Failed to delete key: " + e.getMessage());
        }
    }


}
