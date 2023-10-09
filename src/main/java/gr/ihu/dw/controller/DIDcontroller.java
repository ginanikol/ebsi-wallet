package gr.ihu.dw.controller;

import gr.ihu.dw.dao.DIDdata;
import gr.ihu.dw.dao.DIDdataRepository;
import gr.ihu.dw.dao.KeyData;
import gr.ihu.dw.dao.KeyDataRepository;
import gr.ihu.dw.service.KeysGeneratorService;
import org.bitcoinj.core.Base58;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.List;

@Controller
@RequestMapping("/dids")
public class DIDcontroller {
    private final KeyDataRepository keyDataRepository;
    private final DIDdataRepository diDdataRepository;
    private KeysGeneratorService keysGeneratorService;
    Logger logger = LoggerFactory.getLogger(DIDcontroller.class);

    @Autowired
    public DIDcontroller(KeyDataRepository keyDataRepository, DIDdataRepository diDdataRepository, @Qualifier("ed25519KeysGeneratorService") KeysGeneratorService keysGeneratorService) {
        this.keyDataRepository = keyDataRepository;
        this.diDdataRepository = diDdataRepository;
        this.keysGeneratorService = keysGeneratorService;
    }

//    @GetMapping("/")
//    public String getDids(Model model) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
//        KeyPair keyPair = keysGeneratorService.generateKeyPair();
//        KeyData keyData = new KeyData();
//        keyData.setPrivateKey(String.valueOf(keyPair.getPrivate()));
//        keyData.setPublicKey(String.valueOf(keyPair.getPublic()));
//
//        PublicKey publicKey = keyPair.getPublic();
//
//        byte[] publicKeyBytes = publicKey.getEncoded();
//
//        // Specify the Multicodec identifier for Ed25519 (0xed) and concatenate with raw public key bytes
//        byte[] concatenatedBytes = new byte[publicKeyBytes.length + 1];
//        concatenatedBytes[0] = (byte) 0xed; // Multicodec identifier for Ed25519
//        System.arraycopy(publicKeyBytes, 0, concatenatedBytes, 1, publicKeyBytes.length);
//
//        // Base58 encode the concatenated bytes
//        String base58PublicKey = "z" + Base58.encode(concatenatedBytes);
//
//        // Create the DID:key
//        String didKey = "did:key:" + base58PublicKey;
//
//
//        DIDdata did = new DIDdata();
//        did.setValue(didKey);
//
//        diDdataRepository.save(did);
//        keyDataRepository.save(keyData);
//
//        model.addAttribute("did")
//        return "dids";
//    }

    @GetMapping("/all")
    public String getAllDids(Model model) {
        List<DIDdata> dids = diDdataRepository.findAll();
        model.addAttribute("dids", dids);
        return "test";
    }


}
