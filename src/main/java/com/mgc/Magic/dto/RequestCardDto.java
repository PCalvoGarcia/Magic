package com.mgc.Magic.dto;

import com.mgc.Magic.entity.Card;
import jakarta.validation.constraints.*;

public record RequestCardDto(
        @NotBlank(message = "Name cannot be empty")
        @Size(max = 50, message = "Name must have less than 50 characters")
        String name,

        @NotBlank(message = "Mana type cannot be empty")
        @Pattern(regexp = "^(white|blue|green|red|black)$", message = "Mana type must be one of: white, blue, green, red, black")
        Card.ManaType manaType,

        @NotNull(message = "Mana cost cannot be null")
        @Min(value = 0, message = "Mana cost must be a positive number")
        int manaCost,

        @NotBlank(message = "Type cannot be empty")
        @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Type must only contain letters")
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
        card.setName(this.name);
        card.setManaType(this.manaType);
        card.setManaCost(this.manaCost);
        card.setType(this.type);
        card.setTypeLine(this.typeLine);
        card.setExpansionSymbol(this.expansionSymbol);
        card.setSkills(this.skills);
        card.setTextEnvironment(this.textEnvironment);
        card.setForce(this.force);
        card.setResistance(this.resistance);
        return card;
    }
}
