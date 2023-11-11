package gr.ihu.dw.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

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
    private String type;
    private String issuer;
    @Field(targetType = FieldType.DATE_TIME)
    private LocalDateTime issuanceDate;
    @Field(targetType = FieldType.DATE_TIME)
    private LocalDateTime validFrom;
    @Field(targetType = FieldType.DATE_TIME)
    private LocalDateTime expirationDate;
    @Field(targetType = FieldType.DATE_TIME)
    private LocalDateTime issued;
    private String credentialSubject;
    private String credentialSchema;
}

