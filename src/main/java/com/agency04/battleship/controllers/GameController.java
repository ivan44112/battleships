package com.agency04.battleship.controllers;

import com.agency04.battleship.dto.GameDTO;
import com.agency04.battleship.dto.PlayerDTO;
import com.agency04.battleship.mapper.GameMapper;
import com.agency04.battleship.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameService gameService;

    private final GameMapper gameMapper;

    @Autowired
    public GameController(GameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }


    @PostMapping("/{currentPlayerId}/{opponentId}/game")
    public GameDTO challengePlayer(@PathVariable("currentPlayerId") int currentPlayerId,
                                   @PathVariable("opponentId") int opponentId) {
        return gameMapper.mapEntityToDto(gameService.challengePlayer(
                currentPlayerId, opponentId));
    }

    @GetMapping("/game/list")
    public List<GameDTO> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/player/{playerId}/game/list")
    public List<PlayerDTO> getAllPlayerGames(@PathVariable("playerId") int playerId) {
        return gameService.getAllPlayerGames(playerId);
    }
}
