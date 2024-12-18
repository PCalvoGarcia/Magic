package com.mgc.Magic.services;

import com.mgc.Magic.dto.RequestCardDto;
import com.mgc.Magic.dto.ResponseCardDto;
import com.mgc.Magic.entity.Card;
import com.mgc.Magic.exceptions.NoIdFoundException;
import com.mgc.Magic.exceptions.NoNameFoundException;
import com.mgc.Magic.exceptions.NoRegistersFoundException;
import com.mgc.Magic.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private final CardRepository CARD_REPOSITORY;

    public CardService(CardRepository cardRepository) {
        CARD_REPOSITORY = cardRepository;
    }

    //POST
    public ResponseCardDto createCard (RequestCardDto cardDto){
        Card newCard = cardDto.toEntity();
        Card savedCard = CARD_REPOSITORY.save(newCard);
        return ResponseCardDto.fromEntity(savedCard);
    }

    //GET
    public List<ResponseCardDto> findAllCards(){
        List<Card> cards = CARD_REPOSITORY.findAll();
        if (cards.isEmpty()) {
            throw new NoRegistersFoundException();
        }
        return cards.stream()
                .map(ResponseCardDto::fromEntity)
                .toList();
    }

    //GET BY ID
    public ResponseCardDto findCardById(Long id){
        Card card = CARD_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));
        return ResponseCardDto.fromEntity(card);
    }

    //GET BY NAME
    public ResponseCardDto findCardByName(String name){
        Card card = CARD_REPOSITORY.findByName(name)
                .orElseThrow(() -> new NoNameFoundException(name));
        return ResponseCardDto.fromEntity(card);
    }

    //PUT
    public ResponseCardDto updateCard(Long id, RequestCardDto request){
        Card existingCard = CARD_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));

        existingCard.setName(request.name());
        existingCard.setManaType(request.manaType());
        existingCard.setManaCost(request.manaCost());
        existingCard.setType(request.type());
        existingCard.setTypeLine(request.typeLine());
        existingCard.setExpansionSymbol(request.expansionSymbol());
        existingCard.setSkills(request.skills());
        existingCard.setTextEnvironment(request.textEnvironment());
        existingCard.setForce(request.force());
        existingCard.setResistance(request.resistance());

        Card updatedCard = CARD_REPOSITORY.save(existingCard);
        return ResponseCardDto.fromEntity(updatedCard);
    }

    //DELETE
    public void deleteCardById(Long id){
        Card card = CARD_REPOSITORY.findById(id)
                .orElseThrow(() -> new NoIdFoundException(id));
        CARD_REPOSITORY.deleteById(id);
    }
}
