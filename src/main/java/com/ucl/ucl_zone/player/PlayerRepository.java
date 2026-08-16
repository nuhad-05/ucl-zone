package com.ucl.ucl_zone.player;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
 
    void deleteByName(String playerName);
 
    Optional<Player> findByName(String name);
 
    List<Player> findByTeamIgnoreCase(String team);
 
    List<Player> findByNameContainingIgnoreCase(String name);
 
    List<Player> findByPositionContainingIgnoreCase(String position);
 
    List<Player> findByNationalityContainingIgnoreCase(String nationality);
 
    List<Player> findByTeamIgnoreCaseAndPositionIgnoreCase(String team, String position);
}