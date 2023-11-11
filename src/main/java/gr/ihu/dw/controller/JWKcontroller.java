package gr.ihu.dw.controller;

import gr.ihu.dw.dao.JWKdata;
import gr.ihu.dw.dao.JWkdataRepository;
import gr.ihu.dw.service.DIDservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Deprecated(forRemoval = true)
@RequestMapping("/jwks")
public class JWKcontroller {

    Logger logger = LoggerFactory.getLogger(JWKcontroller.class);
    private final JWkdataRepository jWkdataRepository;
    private final DIDservice didService;

    public JWKcontroller(JWkdataRepository jWkdataRepository, DIDservice didService) {
        this.jWkdataRepository = jWkdataRepository;
        this.didService = didService;
    }

    @GetMapping("/all")
    public String getAllKeys(Model model) {
        List<JWKdata> keys = jWkdataRepository.findAll();
        model.addAttribute("jwks", keys);
        return "allJWKs";
    }



    //This is to create DID has to go elsewhere!!!
    @GetMapping("/createDID")
    public void createDIDfromMostRecentJWK(Model model) {
        didService.createDID();
    }
}
