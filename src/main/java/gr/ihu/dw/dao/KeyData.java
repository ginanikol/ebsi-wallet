package gr.ihu.dw.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Keys")
@Deprecated(forRemoval = true)
public class KeyData {

    @Id
    private String id;
    String privateKey;

    String publicKey;

    private LocalDateTime timestamp;


}