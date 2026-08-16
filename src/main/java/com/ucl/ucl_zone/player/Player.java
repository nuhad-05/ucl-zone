package com.ucl.ucl_zone.player;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
 
 
@Entity
@Table(name = "player_stats")
public class Player {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String name;
    private String nationality;
    private String position;
    private Integer age;
    private String team;
    private Integer appearances;
    private Integer minutes;
    private Integer goals;
    private Integer assists;
    private Integer yellowCards;
    private Integer redCards;
 
    public Player() {
    }
 
    public Player(String name, String nationality, String position, Integer age, String team,
        Integer appearances, Integer minutes, Integer goals, Integer assists, Integer yellowCards,
        Integer redCards) {
            this.name = name;
            this.nationality = nationality;
            this.position = position;
            this.age = age;
            this.team = team;
            this.appearances = appearances;
            this.minutes = minutes;
            this.goals = goals;
            this.assists = assists;
            this.yellowCards = yellowCards;
            this.redCards = redCards;
        }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getNationality() {
        return nationality;
    }
 
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
 
    public String getPosition() {
        return position;
    }
 
    public void setPosition(String position) {
        this.position = position;
    }
 
    public Integer getAge() {
        return age;
    }
 
    public void setAge(Integer age) {
        this.age = age;
    }
 
    public String getTeam() {
        return team;
    }
 
    public void setTeam(String team) {
        this.team = team;
    }
 
    public Integer getAppearances() {
        return appearances;
    }
 
    public void setAppearances(Integer appearances) {
        this.appearances = appearances;
    }
 
    public Integer getMinutes() {
        return minutes;
    }
 
    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }
 
    public Integer getGoals() {
        return goals;
    }
 
    public void setGoals(Integer goals) {
        this.goals = goals;
    }
 
    public Integer getAssists() {
        return assists;
    }
 
    public void setAssists(Integer assists) {
        this.assists = assists;
    }
 
    public Integer getYellowCards() {
        return yellowCards;
    }
 
    public void setYellowCards(Integer yellowCards) {
        this.yellowCards = yellowCards;
    }
 
    public Integer getRedCards() {
        return redCards;
    }
 
    public void setRedCards(Integer redCards) {
        this.redCards = redCards;
    }
 
        
}