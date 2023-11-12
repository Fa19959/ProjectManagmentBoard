package com.example.cards.Model;


import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "card")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    private String title;

    private String description;

    Integer section;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
