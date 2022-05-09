package com.agency04.battleship.service;

import com.agency04.battleship.dto.GameDTO;
import com.agency04.battleship.model.Game;

import java.util.HashSet;
import java.util.List;

public interface GameService {

    Game challengePlayer(int currentPlayerId, int opponentId);

    List<GameDTO> getAllGames();
}
