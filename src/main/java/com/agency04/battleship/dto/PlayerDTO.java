package com.agency04.battleship.dto;

import com.agency04.battleship.model.Game;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.PostLoad;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties({"playerOneGames", "playerTwoGames"})
public class PlayerDTO {

    private int id;
    private String name;
    private String email;
    private List<Game> playerOneGames;
    private List<Game> playerTwoGames;
    private List<Game> gameList;

    @PostLoad
    public void appendGames() {
        this.gameList = new ArrayList<>(playerOneGames);
        gameList.addAll(playerTwoGames);
    }

}
