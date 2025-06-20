package com.example.demo.service;

import com.example.demo.client.RickAndMortyClient;
import com.example.demo.dto.external.CharacterPageDto;
import com.example.demo.dto.external.ExternalCharacterDTO;
import com.example.demo.entity.Character;
import com.example.demo.entity.Location;
import com.example.demo.mapper.CharacterMapper;
import com.example.demo.mapper.LocationMapper;
import com.example.demo.repository.CharacterRepository;
import com.example.demo.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CharacterImportService {

    private final RickAndMortyClient client;
    private final CharacterRepository characterRepository;
    private final LocationRepository locationRepository;
    private final CharacterMapper characterMapper;
    private final LocationMapper locationMapper;

    @Transactional
    public void importAll() {
        int page = 1;
        CharacterPageDto response;

        do {
            response = client.fetchPage(page);

            List<Character> characters = response.results().stream()
                    .map(externalCharacter -> characterMapper.toEntity(
                            externalCharacter,
                            findOrCreateLocation(externalCharacter.origin()),
                            findOrCreateLocation(externalCharacter.location())
                    ))
                    .toList();

            characterRepository.saveAll(characters);
            page++;

        } while (response.info().next() != null);
    }

    private Location findOrCreateLocation(ExternalCharacterDTO.NestedLocation dto) {

        return locationRepository
                .findFirstByNameAndUrl(dto.name(), dto.url())
                .orElseGet(() -> locationRepository.save(
                        new Location(dto.name(), dto.url())
                ));
    }
}

