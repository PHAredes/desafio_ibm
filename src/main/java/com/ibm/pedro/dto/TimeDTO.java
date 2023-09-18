package com.ibm.pedro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TimeDTO {
    private String nome;
    private List<String> jogadores;

}
