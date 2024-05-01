package com.cardgame.stareindespair.repository;

import com.cardgame.stareindespair.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {



    Optional<Player> findByPhoneNumber(Long phoneNumber); // 更新方法名以匹配实体属性名
    Optional<Player> findByPhoneNumberAndPassword(Long phoneNumber, String password);


    Optional<Player> findByUsername(String username);
}