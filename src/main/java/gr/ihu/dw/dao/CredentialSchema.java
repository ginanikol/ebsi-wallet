package gr.ihu.dw.dao;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CredentialSchema {
    private String csid;
    private String type;
}
