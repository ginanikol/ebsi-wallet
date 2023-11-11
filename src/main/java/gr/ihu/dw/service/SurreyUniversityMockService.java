package gr.ihu.dw.service;

import gr.ihu.dw.dao.Vc;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static gr.ihu.dw.util.Utils.formatDate;

@Service
public class SurreyUniversityMockService {

    public Vc fetchUniversityCredentials() {
        Vc vc = new Vc();
        vc.setType("BSc Computer Science");
        vc.setIssuer("did:ebsi:ziDnioxYYLW1a3qUbqTFz4W");
        vc.setIssuanceDate(formatDate(LocalDateTime.now()));
        vc.setValidFrom(formatDate(LocalDateTime.now()));
        vc.setExpirationDate(formatDate(LocalDateTime.MAX));
        return vc;
    }
}
