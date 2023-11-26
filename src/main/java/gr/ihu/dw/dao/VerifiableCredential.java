package gr.ihu.dw.dao;

import com.fasterxml.jackson.annotation.JsonValue;
import jdk.jfr.Label;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

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
    @Field(targetType = FieldType.DATE_TIME)
    private Date issuanceDate;
    @Field(targetType = FieldType.DATE_TIME)
    private Date validFrom;
    @Field(targetType = FieldType.DATE_TIME)
    private Date issued;
    private CredentialSubject credentialSubject;
    private CredentialSchema credentialSchema;
    @Field(targetType = FieldType.DATE_TIME)
    private Date expirationDate;
    private TermsOfUse termsOfUse;
}
