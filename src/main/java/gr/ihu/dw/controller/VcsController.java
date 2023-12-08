package gr.ihu.dw.controller;

import gr.ihu.dw.dao.DIDdata;
import gr.ihu.dw.dao.Vc;
import gr.ihu.dw.dto.VcsDTO;
import gr.ihu.dw.service.DIDservice;
import gr.ihu.dw.service.VcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vcs")
public class VcsController {

    private final VcService vcService;
    private final DIDservice didService;
    @Autowired
    public VcsController(VcService vcService, DIDservice didService) {
        this.vcService = vcService;
        this.didService = didService;
    }

    @GetMapping("/all")
    public String getAllVcs(Model model) {
        List<VcsDTO> vcs = vcService.fetchVcs();
        List<DIDdata> dids = didService.fetchDIDs();

        model.addAttribute("vcs", vcs);
        model.addAttribute("dids", dids);
        return "vcs";
    }

//    @GetMapping("/create")
//    public ResponseEntity<String> createVc() {
//        try {
//            vcService.createVc();
//            return ResponseEntity.ok("New VC created successfully! ");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Failed to create new VC: " + e.getMessage());
//        }
//    }

    @DeleteMapping("/delete/{id}")
    public void deleteVc(@PathVariable("id") String id) {
        vcService.deleteById(id);
    }

}
