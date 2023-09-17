package com.ibm.pedro.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Data
@Entity(name = "time")
public class TimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = ALL, mappedBy = "time")
    private List<JogadorEntity> jogadores;

}
