package com.example.demo.mapper;

import com.example.demo.dto.CharacterDTO;
import com.example.demo.dto.external.ExternalCharacterDTO;
import com.example.demo.entity.Character;
import com.example.demo.entity.Location;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CharacterMapper {

    public CharacterDTO characterToCharacterDto(Character character) {
        return new CharacterDTO(
                character.getCharacterId(),
                character.getName(),
                character.getStatus(),
                character.getSpecies(),
                character.getType(),
                character.getGender(),
                character.getOrigin(),
                character.getLocation(),
                character.getImage()
        );
    }

    public List<CharacterDTO> listOfCharactersToCharactersDto(List<Character> listOfCharacters) {
        return listOfCharacters.stream()
                .map(this::characterToCharacterDto)
                .collect(Collectors.toList());
    }

    public Character toEntity(CharacterDTO characterDTO) {
        Location origin = new Location(null, characterDTO.getOrigin().getName(), characterDTO.getOrigin().getUrl());
        Location location = new Location(null, characterDTO.getLocation().getName(), characterDTO.getLocation().getUrl());

        return new Character(
                null,
                characterDTO.getName(),
                characterDTO.getStatus(),
                characterDTO.getSpecies(),
                characterDTO.getType(),
                characterDTO.getGender(),
                origin,
                location,
                characterDTO.getImage()
        );
    }

    public Character toEntity(
            ExternalCharacterDTO externalCharacterDTO,
            Location origin,
            Location location
    ) {
        return new Character(
                null,
                externalCharacterDTO.name(),
                externalCharacterDTO.status(),
                externalCharacterDTO.species(),
                externalCharacterDTO.type(),
                externalCharacterDTO.gender(),
                origin,
                location,
                externalCharacterDTO.image()
        );
    }

}
