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
public void run(String... args) throws Exception {
    List<Player> players = apiFootballService.fetchTopTeamPlayers();
    playerRepository.saveAll(players);
    System.out.println("Successfully saved " + players.size() + " top club players!");
}

}