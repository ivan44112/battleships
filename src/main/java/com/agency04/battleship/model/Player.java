package com.agency04.battleship.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@JsonIgnoreProperties({"playerOneGames", "playerTwoGames"})
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "player1")
    private List<Game> playerOneGames;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "player2")
    private List<Game> playerTwoGames;
    @Transient
    private List<Game> gameList;

    @PostLoad
    public void appendGames() {
        this.gameList = new ArrayList<>(playerOneGames);
        gameList.addAll(playerTwoGames);
    }
}
