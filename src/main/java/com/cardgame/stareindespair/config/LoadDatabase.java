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
            Player player1 = repository.save(new Player("String username", "String email", 12342354645L, "String password"));
            log.info("Preloading " + player1 + " with ID: " + player1.getPhone_number());

        };
    }

}