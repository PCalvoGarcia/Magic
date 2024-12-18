package com.mgc.Magic.controller;

import com.mgc.Magic.dto.RequestCardDto;
import com.mgc.Magic.dto.ResponseCardDto;
import com.mgc.Magic.repository.CardRepository;
import com.mgc.Magic.services.CardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    private final CardRepository CARD_REPOSITORY;
    private final CardService CARD_SERVICE;

    public CardController(CardRepository cardRepository, CardService cardService) {
        CARD_REPOSITORY = cardRepository;
        CARD_SERVICE = cardService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseCardDto>> getCardList() {
        List<ResponseCardDto> cards = CARD_SERVICE.findAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCardDto> getCardById(@PathVariable Long id) {
        ResponseCardDto card = CARD_SERVICE.findCardById(id);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<ResponseCardDto> getCardByName(@RequestParam String name) {
        ResponseCardDto card = CARD_SERVICE.findCardByName(name);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseCardDto> createCard(@Valid @RequestBody RequestCardDto requestCard){
        ResponseCardDto newCard = CARD_SERVICE.createCard(requestCard);
        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCardDto> updateCard(@PathVariable Long id,@Valid @RequestBody RequestCardDto request){
        ResponseCardDto updateCard = CARD_SERVICE.updateCard(id, request);
        return new ResponseEntity<>(updateCard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        CARD_SERVICE.deleteCardById(id);
        return new ResponseEntity<>("The card has been deleted", HttpStatus.OK);
    }

}
