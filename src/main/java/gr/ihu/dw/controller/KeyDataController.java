package gr.ihu.dw.controller;

import gr.ihu.dw.dto.KeysDataDTO;
import gr.ihu.dw.service.JWKdataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/keys")
public class KeyDataController {

    private final JWKdataService jwKdataService;

    Logger logger = LoggerFactory.getLogger(KeyDataController.class);

    @Autowired
    public KeyDataController(JWKdataService jwKdataService) {
        this.jwKdataService = jwKdataService;
    }


    @GetMapping("/all")
    public String getAllKeys(Model model) {
        KeysDataDTO keysDataDTO = jwKdataService.fetchKeys();
        model.addAttribute("jwks", keysDataDTO.getJwkKeys());
        model.addAttribute("keysTimestamps", keysDataDTO.getKeysTimestamps());
        return "allJWKs";
    }

    @GetMapping("/create")
    public ResponseEntity<String> createKey() {
        try {
            jwKdataService.createAndSaveKey();
            return ResponseEntity.ok("New key created successfully! ");
        } catch (Exception e) {
            logger.error("Could not create new keys. " + e.getMessage(), e);
            return ResponseEntity.badRequest().body("Failed to create new keys: " + e.getMessage());
        }
    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteKey(@PathVariable("id") String id) {
//        try {
//            JWKdata jwKdata = jWkdataRepository.findById(String.valueOf(id)).get();
//            logger.info("Deleting key with ID {} ", id);
//            jWkdataRepository.delete(jwKdata);
//            return ResponseEntity.ok("Key deleted successfully!");
//        } catch (Exception e) {
//            logger.error("Could not delete key. " + e.getMessage(), e);
//            return ResponseEntity.badRequest().body("Failed to delete key: " + e.getMessage());
//        }
//    }



}
