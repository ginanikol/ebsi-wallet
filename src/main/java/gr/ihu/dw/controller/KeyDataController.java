package gr.ihu.dw.controller;

import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import gr.ihu.dw.dao.JWKdata;
import gr.ihu.dw.dao.JWkdataRepository;
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
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/keys")
public class KeyDataController {

    private final JWkdataRepository jWkdataRepository;
    private final KeysGeneratorService keysGeneratorService;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    Logger logger = LoggerFactory.getLogger(KeyDataController.class);

    @Autowired
    public KeyDataController(JWkdataRepository jWkdataRepository, @Qualifier("ecKeysGeneratorService") KeysGeneratorService keysGeneratorService) {
        this.jWkdataRepository = jWkdataRepository;
        this.keysGeneratorService = keysGeneratorService;
    }

    @GetMapping("/all")
    public String getAllKeys(Model model) {
        List<JWKdata> jwkKeys = jWkdataRepository.findAll();
        sortKeysAccordingToCreationTime(jwkKeys);
        List<String> keysTimestamps = extractTimeStampsAndFormat(jwkKeys);
        model.addAttribute("jwks", jwkKeys);
        model.addAttribute("keysTimestamps", keysTimestamps);
        return "allJWKs";
    }

    @GetMapping("/create")
    public ResponseEntity<String> createKey() {

        try {
            KeyPair keyPair = keysGeneratorService.generateKeyPair();
            JWK jwk = new ECKey.Builder(Curve.P_256, (ECPublicKey) keyPair.getPublic())
                    .privateKey((ECPrivateKey) keyPair.getPrivate())
                    .build();

            JWKdata jwKdata = new JWKdata();
            jwKdata.setValue(String.valueOf(jwk));
            jwKdata.setTimestamp(LocalDateTime.parse(LocalDateTime.now().format(inputFormatter)));
            jWkdataRepository.save(jwKdata);

            logger.info("key created");
            return ResponseEntity.ok("New key created successfully! " + jwKdata);
        } catch (Exception e) {
            logger.error("Could not create new keys. " + e.getMessage(), e);
            return ResponseEntity.badRequest().body("Failed to create new keys: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteKey(@PathVariable("id") String id) {
        try {
            JWKdata jwKdata = jWkdataRepository.findById(String.valueOf(id)).get();
            logger.info("Deleting key with ID {} ", id);
            jWkdataRepository.delete(jwKdata);
            return ResponseEntity.ok("Key deleted successfully!");
        } catch (Exception e) {
            logger.error("Could not delete key. " + e.getMessage(), e);
            return ResponseEntity.badRequest().body("Failed to delete key: " + e.getMessage());
        }
    }

    private static void sortKeysAccordingToCreationTime(List<JWKdata> jwkKeys) {
        jwkKeys.sort(Comparator.comparing(JWKdata::getTimestamp));
    }

    private static List<String> extractTimeStampsAndFormat(List<JWKdata> jwkKeys) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss", Locale.ENGLISH);
        List<String> keysTimestamps = new ArrayList<>();
        for (int i = 0; i < jwkKeys.size(); i++) {
            keysTimestamps.add(i, jwkKeys.get(i).getTimestamp().format(formatter));
        }
        return keysTimestamps;
    }

}
