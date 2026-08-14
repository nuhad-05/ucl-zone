package com.ucl.ucl_zone;

import com.ucl.ucl_zone.player.Player;
import com.ucl.ucl_zone.player.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ApiFootballService apiFootballService;
    private final PlayerRepository playerRepository;

    public DataLoader(ApiFootballService apiFootballService, PlayerRepository playerRepository) {
        this.apiFootballService = apiFootballService;
        this.playerRepository = playerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (playerRepository.count() == 0) {
            System.out.println("Database empty. Fetching player data from API-Football...");
            List<Player> players = apiFootballService.fetchTopTeamPlayers();
            playerRepository.saveAll(players);
            System.out.println("Successfully saved " + players.size() + " top club players!");
        } else {
            System.out.println("Players already exist in local database (" 
                    + playerRepository.count() + " found). Skipping API-Football fetch.");
        }
    }
}