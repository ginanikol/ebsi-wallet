package gr.ihu.dw.service;

import gr.ihu.dw.dao.DIDdataRepository;
import gr.ihu.dw.dao.JWKdata;
import gr.ihu.dw.dao.JWkdataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DIDserviceTest {

    @Mock
    private JWkdataRepository jWkdataRepository;

    @Mock
    private DIDdataRepository diDdataRepository;

    private DIDservice diDservice;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize annotated mocks
        diDservice = new DIDservice(jWkdataRepository, diDdataRepository);
    }

//    @Test
//    void testCreateDID() {
//        JWKdata expectedJWKdata = new JWKdata();
//        expectedJWKdata.setId("6535361a1a8c4a14ccc2b954");
//        when(jWkdataRepository.save(expectedJWKdata)).thenReturn(expectedJWKdata);
//
//        JWKdata jwKdata = diDservice.createDID();
//
//        assertEquals(jwKdata.getId(),  "6535361a1a8c4a14ccc2b954");
//    }
}
