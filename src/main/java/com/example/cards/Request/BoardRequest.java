package com.example.cards.Request;

import com.example.cards.Model.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    private Long id;
    private String title;
    private Map<Integer, String> columns;

    public Board ConvertToBoard(){
        Board board = new Board();

        board.setId(this.getId());
        board.setTitle(this.getTitle());
        board.setColumns(this.getColumns());

        return board;
    }
}
