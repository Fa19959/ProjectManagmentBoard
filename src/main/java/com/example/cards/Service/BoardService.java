package com.example.cards.Service;

import com.example.cards.Model.Board;
import com.example.cards.Repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public String registerBoard(Board board){
        boardRepository.save(board);
        return "The board has been added";
    }

    public List<Board> getAllBoards(){
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id){
        return boardRepository.findById(id).get();
    }

    public String deleteBoard(Long id){
        boardRepository.deleteById(id);
        return "The board has been deleted";
    }

    public Board saveBoard(Board board){
        return boardRepository.save(board);
    }

}
