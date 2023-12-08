package gr.ihu.dw.dto;

import gr.ihu.dw.dao.CredentialSchema;
import gr.ihu.dw.dao.CredentialSubject;
import gr.ihu.dw.dao.TermsOfUse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VerifiableCredentialDTO {

    private List<String> context;
    private String vcid;
    private List<String> type;
    private String issuer;
    private LocalDateTime issuanceDate;
    private LocalDateTime validFrom;
    private LocalDateTime issued;
    private CredentialSubject credentialSubject;
    private CredentialSchema credentialSchema;
    private LocalDateTime expirationDate;
    private TermsOfUse termsOfUse;
}
