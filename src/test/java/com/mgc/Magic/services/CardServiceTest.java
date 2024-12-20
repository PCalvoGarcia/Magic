package com.mgc.Magic.services;

import com.mgc.Magic.dto.RequestCardDto;
import com.mgc.Magic.dto.ResponseCardDto;
import com.mgc.Magic.entity.Card;
import com.mgc.Magic.exceptions.CardExistExcepcion;
import com.mgc.Magic.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
class CardServiceTest {

    @Autowired
    private CardService cardService;

    @MockBean
    private CardRepository cardRepository;

    @Test
    void should_createNewCard() {
        // GIVEN
        RequestCardDto request = new RequestCardDto(
                "Fireball",
                Card.ManaType.RED,
                3,
                Card.Type.SORCERY,
                "Type Line",
                "Expansion Symbol",
                "Skills",
                "Text Environment",
                5,
                5
        );

        Card cardToSave = request.toEntity();
        cardToSave.setName(cardToSave.getName().toUpperCase()); // Conversión a mayúsculas

        Card expectedCard = new Card();
        expectedCard.setId(1L);
        expectedCard.setName("FIREBALL");
        expectedCard.setManaType(Card.ManaType.RED);
        expectedCard.setManaCost(3);
        expectedCard.setType(Card.Type.SORCERY);
        expectedCard.setTypeLine("TYPE LINE");
        expectedCard.setExpansionSymbol("EXPANSION SYMBOL");
        expectedCard.setSkills("SKILLS");
        expectedCard.setTextEnvironment("TEXT ENVIRONMENT");
        expectedCard.setForce(5);
        expectedCard.setResistance(5);

        ResponseCardDto expectedResponse = ResponseCardDto.fromEntity(expectedCard);

        when(cardRepository.existingCard("FIREBALL", Card.Type.SORCERY, "EXPANSION SYMBOL")).thenReturn(List.of());
        when(cardRepository.save(any(Card.class))).thenReturn(expectedCard);

        // WHEN
        ResponseCardDto cardResponse = cardService.createCard(request);

        // THEN
        verify(cardRepository).save(any(Card.class));
        assertEquals(expectedResponse, cardResponse);
    }

    @Test
    void should_findAllCards() {
        // GIVEN
        Card card1 = new Card();
        card1.setId(1L);
        card1.setName("FIREBALL");
        card1.setManaType(Card.ManaType.RED);

        Card card2 = new Card();
        card2.setId(2L);
        card2.setName("COUNTERSPELL");
        card2.setManaType(Card.ManaType.BLUE);

        List<Card> cardList = List.of(card1, card2);
        List<ResponseCardDto> expectedResponse = cardList.stream()
                .map(ResponseCardDto::fromEntity)
                .toList();

        when(cardRepository.findAll()).thenReturn(cardList);

        // WHEN
        List<ResponseCardDto> cardResponse = cardService.findAllCards();

        // THEN
        assertEquals(expectedResponse, cardResponse);
    }

    @Test
    void should_getCardById() {
        // GIVEN
        Card card = new Card();
        card.setId(1L);
        card.setName("FIREBALL");
        card.setManaType(Card.ManaType.RED);
        ResponseCardDto expectedResponse = ResponseCardDto.fromEntity(card);

        when(cardRepository.findById(1L)).thenReturn(Optional.of(card));

        // WHEN
        ResponseCardDto cardResponse = cardService.findCardById(1L);

        // THEN
        assertEquals(expectedResponse, cardResponse);
    }

    @Test
    void should_updateCard() {
        // GIVEN
        RequestCardDto request = new RequestCardDto(
                "Fireball Updated",
                Card.ManaType.RED,
                6,
                Card.Type.SORCERY,
                "Instant - Fireball Updated",
                "Core Set",
                "Deal more damage",
                "This spell deals 6 damage",
                0,
                0
        );

        Card existingCard = new Card();
        existingCard.setId(1L);
        existingCard.setName("FIREBALL");

        Card updatedCard = request.toEntity();
        updatedCard.setId(1L);
        updatedCard.setName(updatedCard.getName().toUpperCase()); // Conversión a mayúsculas

        ResponseCardDto expectedResponse = ResponseCardDto.fromEntity(updatedCard);

        when(cardRepository.findById(1L)).thenReturn(Optional.of(existingCard));
        when(cardRepository.existingCard("FIREBALL UPDATED", Card.Type.SORCERY, "CORE SET")).thenReturn(List.of());
        when(cardRepository.save(any(Card.class))).thenReturn(updatedCard);

        // WHEN
        ResponseCardDto cardResponse = cardService.updateCard(1L, request);

        // THEN
        assertEquals(expectedResponse, cardResponse);
    }

    @Test
    void should_deleteCardById() {
        // GIVEN
        Card cardToDelete = new Card();
        cardToDelete.setId(1L);

        when(cardRepository.findById(1L)).thenReturn(Optional.of(cardToDelete));

        // WHEN
        cardService.deleteCardById(1L);

        // THEN
        verify(cardRepository).deleteById(1L);
    }

    @Test
    void should_throwExceptionIfCardAlreadyExists() {
        // GIVEN
        RequestCardDto request = new RequestCardDto(
                "Fireball",
                Card.ManaType.RED,
                3,
                Card.Type.SORCERY,
                "Type Line",
                "Expansion Symbol",
                "Skills",
                "Text Environment",
                5,
                5
        );

        Card cardToSave = request.toEntity();
        cardToSave.setName(cardToSave.getName().toUpperCase()); // Conversión a mayúsculas

        when(cardRepository.existingCard("FIREBALL", Card.Type.SORCERY, "EXPANSION SYMBOL")).thenReturn(List.of(cardToSave));

        // WHEN & THEN
        assertThrows(CardExistExcepcion.class, () -> cardService.createCard(request));
    }
}
