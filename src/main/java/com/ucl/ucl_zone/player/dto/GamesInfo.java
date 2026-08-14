package com.ucl.ucl_zone.player.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GamesInfo {

    @JsonProperty("appearences")
    private Integer appearences;
    private Integer minutes;
    private String position;

    public Integer getAppearences() {
        return appearences;
    }

    public void setAppearences(Integer appearences) {
        this.appearences = appearences;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}