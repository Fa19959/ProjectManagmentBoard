package com.example.cards.Controller;

import com.example.cards.Model.Board;
import com.example.cards.Response.BoardResponse;
import com.example.cards.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/api/v1/board")
public class BoardController {

    @Autowired
    BoardService boardService;

//    private static final Map<Integer, String> FIXED_COLUMNS = Map.of(
//            1, "To do",
//            2, "In progress",
//            3, "Done"
//    );

    @GetMapping(path = "/getAll")
    public List<BoardResponse> getAllBoards(){
        List<Board> boards = boardService.getAllBoards();
        List<BoardResponse> boardResponses = new ArrayList<>();

        for (Board board : boards) {
            //board.setColumns(new HashMap<>(FIXED_COLUMNS));
            BoardResponse boardResponse = new BoardResponse();
            boardResponse.setId(board.getId());
            boardResponse.setTitle(board.getTitle());
            boardResponse.setColumns(board.getColumns());
            boardResponses.add(boardResponse);
        }

        return boardResponses;
    }

    @PostMapping(path = "/add")
    public String createBoard(@RequestBody Board board){
        boardService.registerBoard(board);
        return "Board has been added";
    }

    @PutMapping(path = "/update/{id}")
    public String updateBoard(@PathVariable(name = "id") Long id, @RequestBody Board upadtedBoard){

        Board board = boardService.getBoardById(id);
        board.setTitle(upadtedBoard.getTitle());

        boardService.saveBoard(board);

        return "Updated Successfully";
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteBoard(@PathVariable(name = "id") Long id){
        boardService.deleteBoard(id);
        return "Board with ID " + id + " has been deleted successfully.";
    }
}
