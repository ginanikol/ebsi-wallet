package gr.ihu.dw.controller;

import gr.ihu.dw.dao.DIDdata;
import gr.ihu.dw.service.DIDservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dids")
public class DIDcontroller {

    private final DIDservice didService;
    private final Logger logger = LoggerFactory.getLogger(DIDcontroller.class);

    @Autowired
    public DIDcontroller(DIDservice didService) {
        this.didService = didService;
    }

    @GetMapping("/all")
    public String getAllDids(Model model) {
        List<DIDdata> dids = didService.fetchDIDs();
        model.addAttribute("dids", dids);
        return "dids";
    }


    @GetMapping("/create")
    public ResponseEntity<String> createDIDfromMostRecentJWK() {
        try {
            didService.createDID();
            return ResponseEntity.ok("New DID created successfully! ");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create new did: " + e.getMessage());
        }
    }
}
