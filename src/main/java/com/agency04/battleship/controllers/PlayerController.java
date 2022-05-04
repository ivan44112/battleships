package com.agency04.battleship.controllers;

import com.agency04.battleship.dto.PlayerDTO;
import com.agency04.battleship.mapper.PlayerMapper;
import com.agency04.battleship.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private final PlayerService playerService;

    private final PlayerMapper playerMapper;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerMapper playerMapper) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @PostMapping("/player")
    public PlayerDTO createPlayer(@RequestBody PlayerDTO playerDTO) {
        return playerMapper.mapEntityToDto(playerService.createPlayer(playerDTO));
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
