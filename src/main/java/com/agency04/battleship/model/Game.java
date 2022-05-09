package com.agency04.battleship.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "player1_id", nullable = false)
    @JsonIgnore
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "player2_id", nullable = false)
    @JsonIgnore
    private Player player2;
    @Column(name = "starting_player")
    private int startingPlayer;

}
