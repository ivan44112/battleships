package com.agency04.battleship.mapper;

import com.agency04.battleship.dto.PlayerDTO;
import com.agency04.battleship.model.Player;

public interface PlayerDTOMapperService {

    PlayerDTO convertEntityToDto(Player player);

    PlayerDTO getPlayerById(int playerId);
}
