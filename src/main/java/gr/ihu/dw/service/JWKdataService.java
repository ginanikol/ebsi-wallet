package gr.ihu.dw.service;

import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jose.jwk.JWK;
import gr.ihu.dw.dao.JWKdata;
import gr.ihu.dw.dao.JWkdataRepository;
import gr.ihu.dw.dto.KeysDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

@Service
public class JWKdataService {

    private final JWkdataRepository jWkdataRepository;
    private final KeysGeneratorService keysGeneratorService;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    Logger logger = LoggerFactory.getLogger(JWKdataService.class);

    public JWKdataService(JWkdataRepository jWkdataRepository, @Qualifier("ecKeysGeneratorService") KeysGeneratorService keysGeneratorService) {
        this.jWkdataRepository = jWkdataRepository;
        this.keysGeneratorService = keysGeneratorService;
    }

    public void createAndSaveKey() {
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
        } catch (Exception e) {
            logger.info("Could not create key");
        }

    }

    public KeysDataDTO fetchKeys() {
        KeysDataDTO keysDataDTO = new KeysDataDTO();
        keysDataDTO.setJwkKeys(jWkdataRepository.findAll());
        List<JWKdata> jwkKeys = keysDataDTO.getJwkKeys();
        sortKeysAccordingToCreationTime(jwkKeys);
        keysDataDTO.setKeysTimestamps(extractTimeStampsAndFormat(jwkKeys));
        return keysDataDTO;
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
