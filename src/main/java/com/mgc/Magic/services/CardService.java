package com.mgc.Magic.services;

import com.mgc.Magic.dto.RequestCardDto;
import com.mgc.Magic.dto.ResponseCardDto;
import com.mgc.Magic.entity.Card;
import com.mgc.Magic.exceptions.CardExistExcepcion;
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
        List<Card> existEqual = CARD_REPOSITORY.existingCard(newCard.getName(), newCard.getType(), newCard.getExpansionSymbol());
        if(!existEqual.isEmpty()){
            throw new CardExistExcepcion();
        }
        Card savedCard = CARD_REPOSITORY.save(newCard);
        return ResponseCardDto.fromEntity(savedCard);
    }

    //GET LIST
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

    //GET BY NAME LIST
    public List<ResponseCardDto> findCardsByName(String name){
        List<Card> cards = CARD_REPOSITORY.findByName(name.toUpperCase());
        if (cards.isEmpty()) {
            throw new NoNameFoundException(name);
        }
        return cards.stream()
                .map(ResponseCardDto::fromEntity)
                .toList();
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

        List<Card> existEqual = CARD_REPOSITORY.existingCard(existingCard.getName(), existingCard.getType(), existingCard.getExpansionSymbol());
        if(!existEqual.isEmpty()){
            throw new CardExistExcepcion();
        }

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
