package com.agency04.battleship.service;

import com.agency04.battleship.dto.GameDTO;
import com.agency04.battleship.dto.PlayerDTO;
import com.agency04.battleship.mapper.GameMapper;
import com.agency04.battleship.mapper.PlayerMapper;
import com.agency04.battleship.model.Game;
import com.agency04.battleship.model.Player;
import com.agency04.battleship.repository.GameRepository;
import com.agency04.battleship.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    private final PlayerRepository playerRepository;

    private final GameMapper gameMapper;

    private final PlayerMapper playerMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository,
                           PlayerRepository playerRepository,
                           GameMapper gameMapper,
                           PlayerMapper playerMapper) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.gameMapper = gameMapper;
        this.playerMapper = playerMapper;
    }

    @Override
    public Game challengePlayer(int currentPlayerId, int opponentId) {
        boolean currentPlayerExists = playerRepository.existsById(currentPlayerId);
        boolean opponentExists = playerRepository.existsById(opponentId);

        Player currentPlayer = playerRepository.findById(currentPlayerId).get();
        Player opponent = playerRepository.findById(opponentId).get();

        if (!currentPlayerExists) {
            throw new IllegalStateException("Player with an id of " +
                    currentPlayerId + " does not exist");
        }
        if (!opponentExists) {
            throw new IllegalStateException("Player with an id of " +
                    opponentId + "does not exist");
        }

        GameDTO gameDTO = new GameDTO();

        int startingPlayer = new Random().nextBoolean() ?
                currentPlayer.getId() : opponent.getId();

        gameDTO.setPlayer1(currentPlayer);
        gameDTO.setPlayer2(opponent);
        gameDTO.setStartingPlayer(startingPlayer);

        Game newGame = gameMapper.mapDtoToEntity(gameDTO);
        return gameRepository.save(newGame);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GameDTO> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(game -> gameMapper.mapEntityToDto(game))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlayerDTO> getAllPlayerGames(int playerId) {
        boolean playerExists = playerRepository.existsById(playerId);

        if (!playerExists) throw new IllegalStateException(
                "Player does not exist"
        );

        return playerRepository.findById(playerId)
                .stream()
                .map(player -> playerMapper.mapEntityToDto(player))
                .filter(playerDTO -> playerDTO.getGameList()
                        .contains(gameRepository.findById(playerId).get()))
                .collect(Collectors.toList());
    }
}
