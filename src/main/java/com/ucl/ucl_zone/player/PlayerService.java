package com.ucl.ucl_zone.player;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getPlayer() {
        return playerRepository.findAll();
    }

    public List<Player> getPlayersByTeam(String teamName) {
        return playerRepository.findByTeamIgnoreCase(teamName);
    }

    public List<Player> getPlayersByName(String searchText) {
        return playerRepository.findByNameContainingIgnoreCase(searchText);
    }

    public List<Player> getPlayersByPosition(String searchText) {
        return playerRepository.findByPositionContainingIgnoreCase(searchText);
    }

    public List<Player> getPlayersByNationality(String searchText) {
        return playerRepository.findByNationalityContainingIgnoreCase(searchText);
    }

    public List<Player> getPlayersByTeamAndPosition(String team, String position) {
        return playerRepository.findByTeamIgnoreCaseAndPositionIgnoreCase(team, position);
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Player updatedPlayer) {
        Optional<Player> existingPlayer = playerRepository.findByName(updatedPlayer.getName());

        if (existingPlayer.isPresent()) {
            Player playerToUpdate = existingPlayer.get();
            playerToUpdate.setNationality(updatedPlayer.getNationality());
            playerToUpdate.setPosition(updatedPlayer.getPosition());
            playerToUpdate.setAge(updatedPlayer.getAge());
            playerToUpdate.setTeam(updatedPlayer.getTeam());
            playerToUpdate.setAppearances(updatedPlayer.getAppearances());
            playerToUpdate.setMinutes(updatedPlayer.getMinutes());
            playerToUpdate.setGoals(updatedPlayer.getGoals());
            playerToUpdate.setAssists(updatedPlayer.getAssists());
            playerToUpdate.setYellowCards(updatedPlayer.getYellowCards());
            playerToUpdate.setRedCards(updatedPlayer.getRedCards());
            return playerRepository.save(playerToUpdate);
        }
        return null;
    }

    @Transactional
    public void deletePlayer(String playerName) {
        playerRepository.deleteByName(playerName);
    }
}