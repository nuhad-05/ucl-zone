package com.ucl.ucl_zone.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getPlayer();
    }

    @GetMapping("/team/{team}")
    public List<Player> getPlayersByTeam(@PathVariable String team) {
        return playerService.getPlayersByTeam(team);
    }

    @GetMapping("/search")
    public List<Player> getPlayersByName(@RequestParam String name) {
        return playerService.getPlayersByName(name);
    }

    @GetMapping("/position/{position}")
    public List<Player> getPlayersByPosition(@PathVariable String position) {
        return playerService.getPlayersByPosition(position);
    }

    @GetMapping("/nationality/{nationality}")
    public List<Player> getPlayersByNationality(@PathVariable String nationality) {
        return playerService.getPlayersByNationality(nationality);
    }

    @GetMapping("/team/{team}/position/{position}")
    public List<Player> getPlayersByTeamAndPosition(@PathVariable String team, @PathVariable String position) {
        return playerService.getPlayersByTeamAndPosition(team, position);
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player saved = playerService.addPlayer(player);
        return ResponseEntity.ok(saved);
    }

    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        Player updated = playerService.updatePlayer(player);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deletePlayer(@PathVariable String name) {
        playerService.deletePlayer(name);
        return ResponseEntity.noContent().build();
    }

}