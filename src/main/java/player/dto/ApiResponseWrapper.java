package player.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseWrapper {

    private List<PlayerEntry> response;

    public List<PlayerEntry> getResponse() {
        return response;
    }

    public void setResponse(List<PlayerEntry> response) {
        this.response = response;
    }

}
