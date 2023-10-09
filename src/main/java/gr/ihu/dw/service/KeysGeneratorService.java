package gr.ihu.dw.service;

import org.springframework.stereotype.Service;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

@Service
public interface KeysGeneratorService {

    public KeyPair generateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException;
    public String getPrivateKey(KeyPair keyPair) throws Exception;
    public String getPublicKey(KeyPair keyPair) throws Exception;
}
