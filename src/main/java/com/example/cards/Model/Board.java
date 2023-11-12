package com.example.cards.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    private String title;

    @Convert(converter = MapConverter.class)
    private Map<Integer, String> columns = Map.of(
            1, "To do",
            2, "In progress",
            3, "Done"
    );

    @OneToMany(mappedBy = "board")
    private List<Card> cards;
}
