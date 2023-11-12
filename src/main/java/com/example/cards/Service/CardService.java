package com.example.cards.Service;

import com.example.cards.Model.Board;
import com.example.cards.Model.Card;
import com.example.cards.Repository.CardRepository;
import com.example.cards.Request.CardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BoardService boardService;

    public Card registerCard(Long boardID, Card card){
        Board board = boardService.getBoardById(boardID);
        card.setBoard(board);

        return cardRepository.save(card);
    }

    public List<Card> getAllCardsByBoardId(Long boardId){
        return cardRepository.findByBoardId(boardId);
    }

    public Card getCardByIdAndBoardId(Long cardId, Long boardId) {
        return cardRepository.findByIdAndBoardId(cardId, boardId);
    }

    public Card getCardById(Long id){
        return cardRepository.findById(id).get();
    }

    public boolean deleteCardByIdAndBoardId(Long cardId, Long boardId) {
        // Check if the card exists and is associated with the given board
        Card card = cardRepository.findByIdAndBoardId(cardId, boardId);
        if (card != null) {
            cardRepository.delete(card);
            return true;
        } else {
            return false;
        }
    }

    public Card updateCard(Long boardId, Long cardId, CardRequest cardRequest) {
        Card card = cardRepository.findByIdAndBoardId(cardId, boardId);

        if (card == null) {
            return null; // Card not found, return null to indicate not found
        }

        // Update the card with the new data
        card.setTitle(cardRequest.getTitle());
        card.setDescription(cardRequest.getDescription());
        card.setSection(cardRequest.getSection());

        return cardRepository.save(card);
    }}
