package com.ibm.pedro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public record TimeDTO(String nome, List<String> jogadores) {
}
