package gr.ihu.dw.controller;

import gr.ihu.dw.dao.DIDdata;
import gr.ihu.dw.dao.DIDdataRepository;
import gr.ihu.dw.dao.KeyDataRepository;
import gr.ihu.dw.service.DIDservice;
import gr.ihu.dw.service.KeysGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/dids")
public class DIDcontroller {

    private final DIDdataRepository diDdataRepository;
    private final DIDservice didService;
    Logger logger = LoggerFactory.getLogger(DIDcontroller.class);

    @Autowired
    public DIDcontroller(DIDdataRepository diDdataRepository, DIDservice didService) {
        this.diDdataRepository = diDdataRepository;
        this.didService = didService;
    }

    @GetMapping("/all")
    public String getAllDids(Model model) {
        List<DIDdata> dids = diDdataRepository.findAll();
        model.addAttribute("dids", dids);
        return "dids";
    }

    @GetMapping("/createDID")
    public void createDIDfromMostRecentJWK(Model model) {
        didService.saveDID();
    }
}
