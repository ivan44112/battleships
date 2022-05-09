package com.agency04.battleship.mapper;

import com.agency04.battleship.dto.GameDTO;
import com.agency04.battleship.model.Game;
import org.springframework.stereotype.Component;


@Component
public class GameMapper {

    public GameDTO mapEntityToDto(Game game) {
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setPlayer1(game.getPlayer1());
        gameDTO.setPlayer2(game.getPlayer2());
        gameDTO.setStartingPlayer(game.getStartingPlayer());
        return gameDTO;
    }

    public Game mapDtoToEntity(GameDTO gameDTO) {
        Game mappedGame = new Game();
        mappedGame.setId(gameDTO.getId());
        mappedGame.setPlayer1(gameDTO.getPlayer1());
        mappedGame.setPlayer2(gameDTO.getPlayer2());
        mappedGame.setStartingPlayer(gameDTO.getStartingPlayer());
        return mappedGame;
    }

}
