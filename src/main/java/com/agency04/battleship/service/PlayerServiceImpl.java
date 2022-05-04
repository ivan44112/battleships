package com.agency04.battleship.service;

import com.agency04.battleship.model.Player;
import com.agency04.battleship.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService{

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void createPlayer(Player player) {
        boolean present = playerRepository.findPlayerByEmail(player.getEmail()).isPresent();
        if (present) {
            throw new IllegalStateException("ERROR-email-already-taken");
        }
        if (!player.getEmail().contains("@")) {
            throw new IllegalStateException("ERROR-email-must-include-@");
        }
        playerRepository.save(player);
    }
}
