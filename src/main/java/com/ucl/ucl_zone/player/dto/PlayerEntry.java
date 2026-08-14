package com.ucl.ucl_zone.player.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerEntry {

    private PlayerInfo player;
    private List<StatisticsEntry> statistics;

    public PlayerInfo getPlayer() {
        return player;
    }

    public void setPlayer(PlayerInfo player) {
        this.player = player;
    }

    public List<StatisticsEntry> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<StatisticsEntry> statistics) {
        this.statistics = statistics;
    }

}
