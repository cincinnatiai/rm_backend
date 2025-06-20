package com.example.demo.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CharacterPageDto (PageInfo info, List<ExternalCharacterDTO> results){

    public record PageInfo(int count, int pages, String next, String prev){};
}