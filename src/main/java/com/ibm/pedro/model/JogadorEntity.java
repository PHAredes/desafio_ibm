package com.ibm.pedro.model;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.CascadeType.ALL;

@Data
@Entity(name = "jogador")
public class JogadorEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "time_id")
    private TimeEntity time;

}
