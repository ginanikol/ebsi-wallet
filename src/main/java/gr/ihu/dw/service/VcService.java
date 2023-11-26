package gr.ihu.dw.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import gr.ihu.dw.dao.Vc;
import gr.ihu.dw.dao.VcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
public class VcService {

    private static final int QR_CODE_WIDTH = 250;
    private static final int QR_CODE_HEIGHT = 250;
    private final VcRepository vcRepository;
    private final Logger logger = LoggerFactory.getLogger(VcService.class);

    public VcService(VcRepository vcRepository) {
        this.vcRepository = vcRepository;
    }

    public List<Vc> fetchVcs() {
        List<Vc> vcs = vcRepository.findAll();
        createQRcodesForEachVc(vcs);
        return vcs;
    }

//    public void createVc() {
//       Vc vc = surreyUniversityMockService.fetchUniversityCredentials();
//       vcRepository.save(vc);
//        logger.info("Surrey uni diploma saved");
//    }

    public void deleteById(String id) {
        vcRepository.deleteById(id);
    }

    private void createQRcodesForEachVc(List<Vc> vcs) {
        for (Vc vc : vcs) {
            String qrCodeData = vc.getBase64QRCode();
            String base64QRCode = generateBase64QRCode(qrCodeData);
            vc.setBase64QRCode(base64QRCode);
        }
    }
    private String generateBase64QRCode(String qrCodeData) {
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(
                    new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, QR_CODE_WIDTH, QR_CODE_HEIGHT),
                    "PNG",
                    byteArray
            );
            byte[] bytes = byteArray.toByteArray();

            Base64.Encoder encoder = Base64.getEncoder();
            byte[] base64Bytes = encoder.encode(bytes);

            return new String(base64Bytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            logger.info(String.format("Could not generate base64 QRcode: %s", e.getMessage()));
            return null;
        }
    }
}
