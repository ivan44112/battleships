package com.agency04.battleship.service;

import com.agency04.battleship.dto.GameDTO;
import com.agency04.battleship.dto.PlayerDTO;
import com.agency04.battleship.model.Game;

import java.util.List;

public interface GameService {

    Game challengePlayer(int currentPlayerId, int opponentId);

    List<GameDTO> getAllGames();

    List<PlayerDTO> getAllPlayerGames(int playerId);
}
