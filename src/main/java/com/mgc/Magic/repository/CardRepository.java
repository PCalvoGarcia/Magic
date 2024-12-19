package com.mgc.Magic.repository;

import com.mgc.Magic.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Long> {
    List<Card> findAll();

    List<Card> findByName(String name);

    @Query("SELECT c FROM Card c " +
            "WHERE c.name = :name " +
            "AND (c.type = :type OR c.expansionSymbol = :expansion OR (c.expansionSymbol = :expansion AND c.type = :type))")
   List<Card> existingCard(@Param("name") String name, @Param("type") Card.Type type, @Param("expansion") String expansion);
}
