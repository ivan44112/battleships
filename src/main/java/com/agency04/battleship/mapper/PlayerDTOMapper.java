package com.agency04.battleship.mapper;

import com.agency04.battleship.dto.PlayerDTO;
import com.agency04.battleship.model.Player;
import com.agency04.battleship.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerDTOMapper {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerDTOMapper(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerDTO convertEntityToDto(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setEmail(player.getEmail());
        return playerDTO;
    }

    public PlayerDTO getPlayerById(int playerId) {
        boolean exists = playerRepository.existsById(playerId);
        if (!exists) {
            throw new IllegalStateException("Player does not exist");
        }
        return convertEntityToDto(playerRepository.findById(playerId).get());
    }
}
