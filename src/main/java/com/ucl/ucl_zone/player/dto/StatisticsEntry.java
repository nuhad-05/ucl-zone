package com.ucl.ucl_zone.player.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatisticsEntry {

    private TeamInfo team;
    private GamesInfo games;
    private GoalsInfo goals;
    private CardsInfo cards;

    public TeamInfo getTeam() {
        return team;
    }

    public void setTeam(TeamInfo team) {
        this.team = team;
    }

    public GamesInfo getGames() {
        return games;
    }

    public void setGames(GamesInfo games) {
        this.games = games;
    }

    public GoalsInfo getGoals() {
        return goals;
    }

    public void setGoals(GoalsInfo goals) {
        this.goals = goals;
    }

    public CardsInfo getCards() {
        return cards;
    }

    public void setCards(CardsInfo cards) {
        this.cards = cards;
    }


}
