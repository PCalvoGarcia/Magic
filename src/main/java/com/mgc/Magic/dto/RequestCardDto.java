package com.mgc.Magic.dto;

import com.mgc.Magic.entity.Card;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;

public record RequestCardDto(
        @NotBlank(message = "Name cannot be empty")
        @Size(max = 50, message = "Name must have less than 50 characters")
        String name,

        @NotNull(message = "Mana type cannot be empty")
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        Card.ManaType manaType,

        @NotNull(message = "Mana cost cannot be null")
        @Min(value = 0, message = "Mana cost must be a positive number")
        int manaCost,

        @NotNull(message = "Type cannot be empty")
        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        Card.Type type,

        @NotBlank(message = "Type line cannot be empty")
        String typeLine,

        String expansionSymbol, // Puede ser opcional
        String skills,          // Puede ser opcional
        String textEnvironment, // Puede ser opcional

        @NotNull(message = "Force cannot be null")
        @Min(value = 0, message = "Force must be a positive number")
        int force,

        @NotNull(message = "Resistance cannot be null")
        @Min(value = 0, message = "Resistance must be a positive number")
        int resistance
) {
    public Card toEntity() {
        Card card = new Card();
        card.setName(this.name.toUpperCase());
        card.setManaType(this.manaType);
        card.setManaCost(this.manaCost);
        card.setType(this.type);
        card.setTypeLine(this.typeLine.toUpperCase());
        card.setExpansionSymbol(this.expansionSymbol != null ?this.expansionSymbol.toUpperCase() : null);
        card.setSkills(this.skills != null ?this.skills.toUpperCase() : null);
        card.setTextEnvironment(this.textEnvironment != null ?this.textEnvironment.toUpperCase() : null);
        card.setResistance(this.resistance);
        return card;
    }
}
