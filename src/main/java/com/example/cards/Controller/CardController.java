package com.example.cards.Controller;

import com.example.cards.Model.Card;
import com.example.cards.Request.CardRequest;
import com.example.cards.Response.CardResponse;
import com.example.cards.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/v1/card")
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping(path = "/{board_id}/cards")
    public ResponseEntity<List<CardResponse>> getAllCards(@PathVariable("board_id") Long boardId) {
        List<Card> cards = cardService.getAllCardsByBoardId(boardId);

        List<CardResponse> cardResponses = cards.stream()
                .map(card -> new CardResponse(card.getId(), card.getTitle(), card.getDescription(), card.getSection()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(cardResponses, HttpStatus.OK);
    }

    @GetMapping(path = "/{board_id}/cards/{card_id}")
    public ResponseEntity<CardResponse> getCardById(@PathVariable("board_id") Long boardId, @PathVariable("card_id") Long cardId) {
        Card card = cardService.getCardByIdAndBoardId(cardId, boardId);

        if (card == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CardResponse cardResponse = new CardResponse(card.getId(), card.getTitle(), card.getDescription(), card.getSection());

        return new ResponseEntity<>(cardResponse, HttpStatus.OK);
    }



    @PostMapping(path = "/add/{board_id}/cards")
    public String createCard(@PathVariable("board_id") Long boardId, @RequestBody CardRequest cardRequest){
        Card card = new Card();
        card.setTitle(cardRequest.getTitle());
        card.setDescription(cardRequest.getDescription());
        card.setSection(cardRequest.getSection());

        card = cardService.registerCard(boardId, card);

        return "Card has been added";
    }

    @PutMapping(path = "/{board_id}/cards/{card_id}")
    public ResponseEntity<CardResponse> updateCard(@PathVariable("board_id") Long boardId, @PathVariable("card_id") Long cardId, @RequestBody CardRequest cardRequest) {
        Card updatedCard = cardService.updateCard(boardId, cardId, cardRequest);

        if (updatedCard == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CardResponse cardResponse = new CardResponse(updatedCard.getId(), updatedCard.getTitle(), updatedCard.getDescription(), updatedCard.getSection());

        return new ResponseEntity<>(cardResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{board_id}/cards/{card_id}")
    public ResponseEntity<Map<String, String>> deleteCard(@PathVariable("board_id") Long boardId, @PathVariable("card_id") Long cardId) {
        boolean deleted = cardService.deleteCardByIdAndBoardId(cardId, boardId);

        if (deleted) {
            Map<String, String> response = Collections.singletonMap("message", String.format("Card with ID %d has been deleted successfully from board %d.", cardId, boardId));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}