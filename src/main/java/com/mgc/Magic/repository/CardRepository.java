package com.mgc.Magic.repository;

import com.mgc.Magic.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Long> {
    List<Card> findAll();

    Optional<Card> findByName(String name);

}
