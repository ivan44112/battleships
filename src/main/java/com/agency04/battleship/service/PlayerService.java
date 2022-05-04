package com.agency04.battleship.service;

import com.agency04.battleship.dto.PlayerDTO;
import com.agency04.battleship.model.Player;

import java.util.List;

public interface PlayerService {

    void createPlayer(Player player);

    PlayerDTO getPlayerById(int playerId);

    List<PlayerDTO> getAllPlayers();
}
