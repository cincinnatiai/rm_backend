package com.example.demo.service;

import com.example.demo.dto.CharacterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CharacterService {
    List<CharacterDTO> getAllCharacters();
    CharacterDTO getCharacterById(Long id);
    CharacterDTO deleteCharacterById(Long id);
    Long deleteAllCharacters();
    CharacterDTO createCharacter(CharacterDTO characterDTO);
    CharacterDTO updateCharacter(CharacterDTO characterDTO, Long id);
    Page<CharacterDTO> getCharactersPage(Pageable pageable);
}