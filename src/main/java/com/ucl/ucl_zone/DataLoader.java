package com.ucl.ucl_zone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import com.ucl.ucl_zone.player.Player;
import com.ucl.ucl_zone.player.PlayerRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final PlayerRepository playerRepository;
    private final ApiFootballService apiFootballService;

    public DataLoader(PlayerRepository playerRepository, ApiFootballService apiFootballService) {
        this.playerRepository = playerRepository;
        this.apiFootballService = apiFootballService;
    }

    @Override
    public void run(String... args) {
        if (playerRepository.count() == 0) {
            List<Player> players = apiFootballService.fetchAllPlayers();
            playerRepository.saveAll(players);
            System.out.println("Loaded " + players.size() + " players from API-Football.");
        } else {
            System.out.println("Player data already present — skipping API fetch.");
        }
    }

}