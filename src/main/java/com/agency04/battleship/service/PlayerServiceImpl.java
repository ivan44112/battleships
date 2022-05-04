package com.agency04.battleship.service;

import com.agency04.battleship.dto.PlayerDTO;
import com.agency04.battleship.mapper.PlayerDTOMapper;
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

    private final PlayerDTOMapper playerDTOMapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerDTOMapper playerDTOMapper) {
        this.playerRepository = playerRepository;
        this.playerDTOMapper = playerDTOMapper;
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
        playerDTOMapper.convertEntityToDto(playerRepository.save(player));
    }

    @Override
    @Transactional(readOnly = true)
    public PlayerDTO getPlayerById(int playerId) {
        return playerDTOMapper.getPlayerById(playerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(player -> playerDTOMapper.convertEntityToDto(player))
                .collect(Collectors.toList());
    }
}
