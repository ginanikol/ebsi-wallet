package gr.ihu.dw.dao;

import lombok.*;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VerifiableCredential {
    private List<String> context;
    private String vcid;
    private List<String> type;
    private String issuer;
    private Long issuanceDate;
    private Long validFrom;
    private Long issued;
    private CredentialSubject credentialSubject;
    private CredentialSchema credentialSchema;
    private Long expirationDate;
    private TermsOfUse termsOfUse;

}
