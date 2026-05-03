package com.ucl.ucl_zone.player;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
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
        return playerRepository.findAll().stream()
        .filter(player -> teamName.equals(player.getTeam()))
        .collect(Collectors.toList()); 
    }

    public List<Player> getPlayersByName(String searchText) {
        return playerRepository.findAll().stream()
        .filter(player -> player.getName().toLowerCase().contains(searchText.toLowerCase()))
        .collect(Collectors.toList());
    }

    public List<Player> getPlayersByPosition(String searchText) {
        return playerRepository.findAll().stream()
                .filter(player -> player.getPosition() != null &&
                        player.getPosition().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByNationality(String searchText) {
        return playerRepository.findAll().stream()
                .filter(player -> player.getNationality() != null &&
                        player.getNationality().toLowerCase().contains(searchText.toLowerCase())) 
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByTeamAndPosition(String team, String position) {
        return playerRepository.findAll().stream()
                .filter(player -> team.equalsIgnoreCase(player.getTeam()) &&
                        position.equalsIgnoreCase(player.getPosition()))
                .collect(Collectors.toList());
    }

    public Player addPlayer(Player player) {
        playerRepository.save(player);
        return player;
    }

    public Player updatePlayer(Player updatedPlayer) {
        Optional<Player> existingPlayer = playerRepository.findByName(updatedPlayer.getName());

        if (existingPlayer.isPresent()) {
            Player playerToUpdate = existingPlayer.get();
            playerToUpdate.setName(updatedPlayer.getName());
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
            playerRepository.save(playerToUpdate);
            return playerToUpdate;
        }
        return null;
    }

    @Transactional
    public void deletePlayer(String playerName) {
        playerRepository.deleteByname(playerName);
    }





      


}
