package gr.ihu.dw.service;

import gr.ihu.dw.dao.DIDdata;
import gr.ihu.dw.dao.DIDdataRepository;
import gr.ihu.dw.dao.JWKdata;
import gr.ihu.dw.dao.JWkdataRepository;
import gr.ihu.dw.dao.Vc;
import gr.ihu.dw.dao.VcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class VcService {

    private final VcRepository vcRepository;
    SurreyUniversityMockService surreyUniversityMockService;
    private final Logger logger = LoggerFactory.getLogger(VcService.class);

    public VcService(VcRepository vcRepository, SurreyUniversityMockService surreyUniversityMockService) {
        this.vcRepository = vcRepository;
        this.surreyUniversityMockService = surreyUniversityMockService;
    }

    public List<Vc> fetchVcs() {
        List<Vc> vcs = vcRepository.findAll();
        return vcs;
    }

    public void createVc() {
       Vc vc = surreyUniversityMockService.fetchUniversityCredentials();
       vcRepository.save(vc);
        logger.info("Surrey uni diploma saved");
    }

}
