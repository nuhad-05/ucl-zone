package player.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseWrapper {

    private List<PlayerInfo> response;

    public List<PlayerInfo> getResponse() {
        return response;
    }

    public void setResponse(List<PlayerInfo> response) {
        this.response = response;
    }

}
