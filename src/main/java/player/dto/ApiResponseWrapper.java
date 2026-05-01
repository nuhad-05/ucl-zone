package player.dto;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponseWrapper {

    private List<PlayerEntry> response;
    
}
