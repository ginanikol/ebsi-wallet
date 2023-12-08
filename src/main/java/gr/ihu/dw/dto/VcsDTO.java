package gr.ihu.dw.dto;

import gr.ihu.dw.dao.Vc;
import lombok.*;

import java.time.LocalDateTime;

import static gr.ihu.dw.util.Utils.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VcsDTO {
    private static final int QR_CODE_WIDTH = 250;
    private static final int QR_CODE_HEIGHT = 250;
    private String id;
    private String jti;
    private String sub;
    private String iss;
    private LocalDateTime nbf;
    private LocalDateTime exp;
    private LocalDateTime iat;
    private String base64QRCode;
    private VerifiableCredentialDTO vc;

    public static VcsDTO fromVc(Vc vc) {
        VcsDTO vcsDTO = new VcsDTO();
        vcsDTO.setId(vc.getId());
        vcsDTO.setJti(vc.getJti());
        vcsDTO.setSub(vc.getSub());
        vcsDTO.setIss(vc.getIss());
        vcsDTO.setNbf(formatDate(convertToLocalDateTimeViaInstant(vc.getNbf())));
        vcsDTO.setExp(formatDate(convertToLocalDateTimeViaInstant(vc.getExp())));
        vcsDTO.setIat(formatDate(convertToLocalDateTimeViaInstant(vc.getIat())));
        vcsDTO.setBase64QRCode(vc.getBase64QRCode());
        createQRcode(vcsDTO);

        VerifiableCredentialDTO vcdto = new VerifiableCredentialDTO();
        vcdto.setContext(vc.getVc().getContext());
        vcdto.setVcid(vc.getVc().getVcid());
        vcdto.setType(vc.getVc().getType());
        vcdto.setIssuer(vc.getVc().getIssuer());
        vcdto.setIssuanceDate(formatDate(convertToLocalDateTimeViaInstant(vc.getVc().getIssuanceDate())));
        vcdto.setValidFrom(formatDate(convertToLocalDateTimeViaInstant(vc.getVc().getValidFrom())));
        vcdto.setIssued(formatDate(convertToLocalDateTimeViaInstant(vc.getVc().getIssued())));
        vcdto.setCredentialSubject(vc.getVc().getCredentialSubject());
        vcdto.setCredentialSchema(vc.getVc().getCredentialSchema());
        vcdto.setExpirationDate(formatDate(convertToLocalDateTimeViaInstant(vc.getVc().getExpirationDate())));
        vcdto.setTermsOfUse(vc.getVc().getTermsOfUse());

        vcsDTO.setVc(vcdto);
        return vcsDTO;
    }

    private static void createQRcode(VcsDTO vc) {
        String base64QRCode = generateBase64QRCode(vc.getBase64QRCode(), QR_CODE_WIDTH, QR_CODE_HEIGHT);
        vc.setBase64QRCode(base64QRCode);
    }


}
