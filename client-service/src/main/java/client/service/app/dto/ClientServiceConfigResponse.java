package client.service.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ClientServiceConfigResponse {

    @JsonProperty("configuration")
    private Map<String, String> configMap;

    @JsonProperty("value")
    private int value;
}
