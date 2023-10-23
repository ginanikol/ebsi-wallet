package gr.ihu.dw.controller;

import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import gr.ihu.dw.dao.JWKdata;
import gr.ihu.dw.dao.JWkdataRepository;
import gr.ihu.dw.dao.KeyDataRepository;
import gr.ihu.dw.dto.Keys;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/keys")
public class KeyDataController {

    private final KeyDataRepository keyDataRepository;
    private final JWkdataRepository jWkdataRepository;
    private final KeysGeneratorService keysGeneratorService;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
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
        List<JWKdata> jwkKeys = jWkdataRepository.findAll();
        List<Keys> keys = new ArrayList<>();
        for (JWKdata jwkKey : jwkKeys){
            Keys key = new Keys();
            key.setValue(jwkKey.getValue());
            key.setTimestamp(convertToDateViaInstant(jwkKey.getTimestamp()));
            keys.add(key);
        }
        model.addAttribute("jwks", keys);
        return "allJWKs";
    }

    @GetMapping("/create")
    public ResponseEntity<String> createKey() {

        try{
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
        }catch (Exception e){
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

    Date convertToDateViaInstant(LocalDateTime dateToConvert) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = dateToConvert.format(formatter);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return sdf.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Handle the exception as needed
        }
    }







}
