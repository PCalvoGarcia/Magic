package com.mgc.Magic.dto;


import com.mgc.Magic.entity.Card;

public record ResponseCardDto(Long id, String name, Card.ManaType manaType, int manaCost, Card.Type type, String typeLine, String expansionSymbol, String skills, String textEnvironment, int force, int resistance ) {
    public static ResponseCardDto fromEntity(Card Card){
        return new ResponseCardDto(
            Card.getId(),
            Card.getName(),
            Card.getManaType(),
            Card.getManaCost(),
            Card.getType(),
            Card.getTypeLine(),
            Card.getExpansionSymbol(),
            Card.getSkills(),
            Card.getTextEnvironment(),
            Card.getForce(),
            Card.getResistance()
        );
    }
}
