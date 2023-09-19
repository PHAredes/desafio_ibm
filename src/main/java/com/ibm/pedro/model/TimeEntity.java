package com.ibm.pedro.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Data
@NoArgsConstructor
@Entity(name = "time")
public class TimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @JsonIgnore // impedir recursão infinita ao fazer uma busca
    @OneToMany(cascade = ALL, mappedBy = "time")
    private List<JogadorEntity> jogadores = new ArrayList<>();


}
