package com.agency04.battleship.service;

import com.agency04.battleship.dto.PlayerDTO;
import com.agency04.battleship.model.Player;
import com.agency04.battleship.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void createPlayer(Player player) {
        Optional<Player> playerByEmail = playerRepository.findPlayerByEmail(player.getEmail());
        boolean validEmail = player.getEmail().contains("@");
        playerByEmail.ifPresentOrElse(presentPlayer -> {
            throw new IllegalStateException("ERROR-email-already-taken");
        }, () -> {
            if (!validEmail) {
                throw new IllegalStateException("ERROR-email-must-include-@");
            }
        });
        convertEntityToDto(playerRepository.save(player));
    }

    @Override
    @Transactional(readOnly = true)
    public PlayerDTO getPlayerById(int playerId) {
        boolean exists = playerRepository.existsById(playerId);
        if (!exists) {
            throw new IllegalStateException("Player does not exist");
        }
        return convertEntityToDto(playerRepository.findById(playerId).get());
    }

    @Override
    public PlayerDTO convertEntityToDto(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setEmail(player.getEmail());
        return playerDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
