package player.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

public class PlayerEntry {

    private PlayerInfo player;
    private List<StatisticsEntry> statistics;
}
