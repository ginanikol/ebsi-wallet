package gr.ihu.dw.dto;

import gr.ihu.dw.dao.JWKdata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KeysDataDTO {

    List<JWKdata> jwkKeys;
    List<String> keysTimestamps;
}
