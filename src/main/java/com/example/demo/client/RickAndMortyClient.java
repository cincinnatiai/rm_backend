package com.example.demo.client;

import com.example.demo.dto.external.CharacterPageDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RickAndMortyClient {
    private final WebClient rickAndMortyWebClient;

    public RickAndMortyClient(WebClient rickAndMortyWebClient) {
        this.rickAndMortyWebClient = rickAndMortyWebClient;
    }

    public CharacterPageDto fetchPage(int page) {
        return rickAndMortyWebClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/character")
                        .queryParam("page", page)
                        .build())
                .retrieve()
                .bodyToMono(CharacterPageDto.class)
                .block();
    }
}
