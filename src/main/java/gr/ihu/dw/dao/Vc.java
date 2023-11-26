package gr.ihu.dw.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Vcs")
public class Vc {

    @Id
    private String id;
    private String jti;
    private String sub;
    private String iss;
    private Long nbf;
    private Long exp;
    private Long iat;
    private String base64QRCode;
    private VerifiableCredential vc;
}