package player.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerInfo {

    private PlayerInfo player;
    private List<StatisticsEntry> statistics;

    public PlayerInfo getPlayer() {
        return player;
    }

    public List<StatisticsEntry> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<StatisticsEntry> statistics) {
        this.statistics = statistics;
    }

}
