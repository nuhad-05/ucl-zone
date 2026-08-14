package com.ucl.ucl_zone;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ucl.ucl_zone.player.Player;
import com.ucl.ucl_zone.player.dto.ApiResponseWrapper;
import com.ucl.ucl_zone.player.dto.PlayerEntry;

@Service
public class ApiFootballService {

    private final RestTemplate restTemplate;

    @Value("${api-football.key}")
    private String apiKey;

    @Value("${api-football.base-url}")
    private String baseUrl;

    @Value("${api-football.league}")
    private String league;

    @Value("${api-football.season}")
    private String season;

    public ApiFootballService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Player> fetchAllPlayers() {
        List<Player> allPlayers = new ArrayList<>();
        int currentPage = 1;
        int totalPages = 1;

        do {
            ApiResponseWrapper wrapper = fetchPage(currentPage);
            if (wrapper == null || wrapper.getResponse() == null) {
                break;
            }

            for (PlayerEntry entry : wrapper.getResponse()) {
                Player player = mapToPlayer(entry);
                if (player != null) {
                    allPlayers.add(player);
                }
            }

            if (wrapper.getPaging() != null && wrapper.getPaging().getTotal() != null) {
                totalPages = wrapper.getPaging().getTotal();
            }
            currentPage++;

        } while (currentPage <= totalPages);

        return allPlayers;
    }

    private ApiResponseWrapper fetchPage(int page) {
        String url = UriComponentsBuilder.fromUriString(baseUrl + "/players")
                .queryParam("league", league)
                .queryParam("season", season)
                .queryParam("page", page)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apisports-key", apiKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponseWrapper> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, ApiResponseWrapper.class);

        return response.getBody();
    }

    private Player mapToPlayer(PlayerEntry entry) {
        if (entry.getPlayer() == null || entry.getStatistics() == null || entry.getStatistics().isEmpty()) {
            return null;
        }

        var info = entry.getPlayer();
        var stats = entry.getStatistics().get(0);

        String name = info.getName();
        String nationality = info.getNationality();
        Integer age = info.getAge();

        String team = stats.getTeam() != null ? stats.getTeam().getName() : null;
        String position = stats.getGames() != null ? stats.getGames().getPosition() : null;
        Integer appearances = stats.getGames() != null ? stats.getGames().getAppearences() : null;
        Integer minutes = stats.getGames() != null ? stats.getGames().getMinutes() : null;
        Integer goals = stats.getGoals() != null ? stats.getGoals().getTotal() : null;
        Integer assists = stats.getGoals() != null ? stats.getGoals().getAssists() : null;
        Integer yellowCards = stats.getCards() != null ? stats.getCards().getYellow() : null;
        Integer redCards = stats.getCards() != null ? stats.getCards().getRed() : null;

        return new Player(name, nationality, position, age, team,
                appearances, minutes, goals, assists, yellowCards, redCards);
    }

}