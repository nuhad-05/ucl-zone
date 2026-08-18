package com.ucl.ucl_zone;

import com.ucl.ucl_zone.player.Player;
import com.ucl.ucl_zone.player.dto.ApiResponseWrapper;
import com.ucl.ucl_zone.player.dto.PlayerEntry;
import com.ucl.ucl_zone.player.dto.StatisticsEntry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiFootballService {

    private final RestTemplate restTemplate;

    @Value("${api.football.key:${api-football.key:#{null}}}")
    private String apiKey;

    // Top Champions League Club IDs in API-Football
    private static final List<Integer> TOP_TEAM_IDS = List.of(
            541, // Real Madrid
            50,  // Manchester City
            157, // Bayern Munich
            529, // Barcelona
            42,  // Arsenal
            85,  // Paris Saint-Germain
            40,  // Liverpool
            505  // Inter Milan
    );

    public ApiFootballService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Player> fetchTopTeamPlayers() {
        List<Player> allPlayers = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-apisports-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        for (Integer teamId : TOP_TEAM_IDS) {
            int currentPage = 1;
            int totalPages = 1;

            System.out.println(">>> Fetching players for Team ID: " + teamId);

            do {
                String url = "https://v3.football.api-sports.io/players?league=2&season=2024&team="
                             + teamId + "&page=" + currentPage;

                ApiResponseWrapper body = fetchWithRetry(url, entity, teamId, currentPage);

                if (body != null) {
                    if (body.getPaging() != null && body.getPaging().getTotal() != null) {
                        totalPages = body.getPaging().getTotal();
                    }

                    if (body.getResponse() != null && !body.getResponse().isEmpty()) {
                        int savedFromPage = 0;
                        for (PlayerEntry entry : body.getResponse()) {
                            Player player = mapToPlayerEntity(entry, teamId);

                            if (player != null) {
                                allPlayers.add(player);
                                savedFromPage++;
                            }
                        }
                        System.out.println("Team " + teamId + " | Page " + currentPage + "/" + totalPages 
                                + " -> Added " + savedFromPage + " players.");
                    } else {
                        System.err.println("No player data returned for Team " + teamId + " on page " + currentPage);
                    }
                } else {
                    System.err.println("Failed to fetch page " + currentPage + " for Team " + teamId);
                }

                currentPage++;

                // 6-second delay between calls to strictly comply with API-Football's 10 req/min rate limit
                sleep(6000);

            } while (currentPage <= totalPages);
        }

        return allPlayers;
    }

    private ApiResponseWrapper fetchWithRetry(String url, HttpEntity<String> entity, Integer teamId, int page) {
        try {
            ResponseEntity<ApiResponseWrapper> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, ApiResponseWrapper.class);
            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error fetching page " + page + " for team " + teamId
                    + ": " + e.getMessage() + " — retrying after 7 seconds...");

            sleep(7000);

            try {
                ResponseEntity<ApiResponseWrapper> retryResponse = restTemplate.exchange(
                        url, HttpMethod.GET, entity, ApiResponseWrapper.class);
                return retryResponse.getBody();
            } catch (Exception retryException) {
                System.err.println("Retry failed for page " + page + " for team " + teamId
                        + ": " + retryException.getMessage());
                return null;
            }
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private Player mapToPlayerEntity(PlayerEntry entry, Integer targetTeamId) {
        if (entry == null || entry.getPlayer() == null) {
            return null;
        }

        Player player = new Player();
        player.setName(entry.getPlayer().getName());
        player.setNationality(entry.getPlayer().getNationality());
        player.setAge(entry.getPlayer().getAge());

        if (entry.getStatistics() != null && !entry.getStatistics().isEmpty()) {
            // Correctly uses your StatisticsEntry DTO type
            StatisticsEntry stats = entry.getStatistics().get(0);

            if (stats.getTeam() != null) {
                player.setTeam(stats.getTeam().getName());
            }
            if (stats.getGames() != null) {
                player.setPosition(stats.getGames().getPosition());
                player.setAppearances(stats.getGames().getAppearences() != null ? stats.getGames().getAppearences() : 0);
                player.setMinutes(stats.getGames().getMinutes() != null ? stats.getGames().getMinutes() : 0);
            }
            if (stats.getGoals() != null) {
                player.setGoals(stats.getGoals().getTotal() != null ? stats.getGoals().getTotal() : 0);
                player.setAssists(stats.getGoals().getAssists() != null ? stats.getGoals().getAssists() : 0);
            }
            if (stats.getCards() != null) {
                player.setYellowCards(stats.getCards().getYellow() != null ? stats.getCards().getYellow() : 0);
                player.setRedCards(stats.getCards().getRed() != null ? stats.getCards().getRed() : 0);
            }
        }

        return player;
    }
}