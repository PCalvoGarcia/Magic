package com.mgc.Magic.Acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgc.Magic.dto.RequestCardDto;
import com.mgc.Magic.dto.ResponseCardDto;
import com.mgc.Magic.entity.Card;
import com.mgc.Magic.repository.CardRepository;
import com.mgc.Magic.services.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
public class CardAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;

    private ResponseCardDto testCard;

    @BeforeEach
    void setUp() {
        // Crear un card de prueba utilizando el DTO RequestCardDto
        RequestCardDto cardDto = new RequestCardDto("Lightning Bolt", Card.ManaType.BLUE, 1, Card.Type.INSTANT, "Instant", "3E", "Deal 3 damage", "Cast instantly", 0, 0);

        testCard = cardService.createCard(cardDto);  // Guardar la carta en la base de datos
    }

    @Test
    void shouldCreateCard() throws Exception {
        // Preparar el DTO de la carta que se va a crear
        RequestCardDto cardDto = new RequestCardDto(
                "Counterspell",
                Card.ManaType.BLUE,
                2,
                Card.Type.INSTANT,
                "Instant",
                "4E",
                "Counter a spell",
                "Instantly counters a spell.",
                0,
                0
        );


        mockMvc.perform(post("/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cardDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("COUNTERSPELL")))
                .andExpect(jsonPath("$.manaType", is("BLUE")))
                .andExpect(jsonPath("$.manaCost", is(2)))
                .andExpect(jsonPath("$.type", is("INSTANT")));
    }

    @Test
    void shouldGetAllCards() throws Exception {
        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("LIGHTNING BOLT")));
    }

    @Test
    void shouldGetCardById() throws Exception {
        mockMvc.perform(get("/cards/" + testCard.id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("LIGHTNING BOLT")));
    }

    @Test
    void shouldGetCardListByName() throws Exception {
        mockMvc.perform(get("/cards?name=LIGHTNING BOLT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("LIGHTNING BOLT")));
    }

    @Test
    void shouldUpdateCard() throws Exception {
        RequestCardDto updatedCardDto = new RequestCardDto(
                "Fireball",
                Card.ManaType.RED,
                4,
                Card.Type.SORCERY,
                "Sorcery",
                "5E",
                "Deal 5 damage",
                "Cast a fireball.",
                0,
                0
        );

        mockMvc.perform(put("/cards/" + testCard.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCardDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Fireball")))
                .andExpect(jsonPath("$.manaCost", is(4)))
                .andExpect(jsonPath("$.type", is("SORCERY")));
    }

//    @Test
//    void shouldDeleteCard() throws Exception {
//        mockMvc.perform(delete("/cards/" + testCard.id()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", is("The card has been deleted")));
//
//        List<ResponseCardDto> deletedCard = cardService.findAllCards();
//        assert(deletedCard.equals("Entity with ID 1 not found in data base"));
//    }
}
