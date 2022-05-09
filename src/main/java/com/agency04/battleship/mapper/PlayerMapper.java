package com.agency04.battleship.mapper;

import com.agency04.battleship.dto.PlayerDTO;
import com.agency04.battleship.model.Player;
import com.agency04.battleship.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerMapper(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerDTO mapEntityToDto(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setEmail(player.getEmail());
        playerDTO.setPlayerOneGames(player.getPlayerOneGames());
        playerDTO.setPlayerTwoGames(player.getPlayerTwoGames());
        playerDTO.setGameList(player.getGameList());
        return playerDTO;
    }

    public Player mapDtoToEntity(PlayerDTO playerDTO) {
        Player mappedPlayer = new Player();
        mappedPlayer.setId(playerDTO.getId());
        mappedPlayer.setName(playerDTO.getName());
        mappedPlayer.setEmail(playerDTO.getEmail());
        mappedPlayer.setPlayerOneGames(playerDTO.getPlayerOneGames());
        mappedPlayer.setPlayerTwoGames(playerDTO.getPlayerTwoGames());
        mappedPlayer.setGameList(playerDTO.getGameList());
        return mappedPlayer;
    }

    public PlayerDTO getPlayerById(int playerId) {
        boolean exists = playerRepository.existsById(playerId);
        if (!exists) {
            throw new IllegalStateException("Player does not exist");
        }
        return mapEntityToDto(playerRepository.findById(playerId).get());
    }
}
