package com.ibm.pedro.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "jogador")
public class JogadorEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private TimeEntity time;

}
