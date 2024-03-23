package com.cardgame.stareindespair.config;

import com.cardgame.stareindespair.model.Player;
import com.cardgame.stareindespair.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase{
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(PlayerRepository repository){
        return args -> {
            Player player1 = repository.save(new Player("Test Player1"));
            log.info("Preloading " + player1 + " with ID: " + player1.getId());
            Player player2 = repository.save(new Player("Test Player2"));
            log.info("Preloading " + player2 + " with ID: " + player2.getId());
            Player player3 = repository.save(new Player("Test Player3"));
            log.info("Preloading " + player3 + " with ID: " + player3.getId());
        };
    }

}