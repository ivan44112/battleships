package com.agency04.battleship.controllers;

import com.agency04.battleship.dto.PlayerDTO;
import com.agency04.battleship.model.Player;
import com.agency04.battleship.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/player")
    public void createPlayer(@RequestBody Player player) {
        playerService.createPlayer(player);
    }

    @GetMapping("/player/{playerId}")
    public PlayerDTO getPlayerById(@PathVariable("playerId") int playerId) {
        return playerService.getPlayerById(playerId);
    }

    @GetMapping("/player/list")
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }
}
