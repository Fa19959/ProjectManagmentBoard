package com.example.cards.Request;

import com.example.cards.Model.Board;
import com.example.cards.Model.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {
    private Long id;

    private String title;

    private String description;

    Integer section;

    public Card ConvertToCard(){
        Card card = new Card();

        card.setId(this.getId());
        card.setTitle(this.getTitle());
        card.setDescription(this.getDescription());
        card.setSection(this.getSection());

        return card;
    }
}
