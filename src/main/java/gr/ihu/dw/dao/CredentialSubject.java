package gr.ihu.dw.dao;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CredentialSubject {
    private String csid;
    private String personalIdentifier;
    private String familyName;
    private String firstName;
    private String dateOfBirth;
}
