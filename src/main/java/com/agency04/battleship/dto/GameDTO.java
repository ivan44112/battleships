package com.agency04.battleship.dto;

import com.agency04.battleship.model.Player;
import lombok.Data;

@Data
public class GameDTO {

    private int id;
    private Player player1;
    private Player player2;
    private int startingPlayer;

}
