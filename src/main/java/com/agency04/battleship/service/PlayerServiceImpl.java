package com.agency04.battleship.service;

import com.agency04.battleship.dto.PlayerDTO;
import com.agency04.battleship.mapper.PlayerMapper;
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

    private final PlayerMapper playerMapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }


    @Override
    public Player createPlayer(PlayerDTO player) {
        Optional<Player> playerByEmail = playerRepository.findPlayerByEmail(player.getEmail());
        boolean validEmail = player.getEmail().contains("@");
        playerByEmail.ifPresentOrElse(presentPlayer -> {
            throw new IllegalStateException("ERROR-email-already-taken");
        }, () -> {
            if (!validEmail) {
                throw new IllegalStateException("ERROR-email-must-include-@");
            }
        });
        Player newPlayer = playerMapper.mapDtoToEntity(player);
        return playerRepository.save(newPlayer);
    }

    @Override
    @Transactional(readOnly = true)
    public PlayerDTO getPlayerById(int playerId) {
        return playerMapper.getPlayerById(playerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(player -> playerMapper.mapEntityToDto(player))
                .collect(Collectors.toList());
    }
}
