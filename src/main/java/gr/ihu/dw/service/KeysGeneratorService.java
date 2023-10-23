package gr.ihu.dw.service;

import org.springframework.stereotype.Service;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

@Service
public interface KeysGeneratorService {

    KeyPair generateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException;
    @Deprecated
    String getPrivateKey(KeyPair keyPair) throws Exception;
    @Deprecated
    String getPublicKey(KeyPair keyPair) throws Exception;
}
