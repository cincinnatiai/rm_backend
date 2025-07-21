package com.example.demo.service;

import com.example.demo.dto.CharacterDTO;
import com.example.demo.entity.Character;
import com.example.demo.exceptions.CharacterNotFoundException;
import com.example.demo.exceptions.ErrorMessage;
import com.example.demo.mapper.CharacterMapper;
import com.example.demo.repository.CharacterRepository;
import com.example.demo.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterServiceImpl implements CharacterService{

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;

    public CharacterServiceImpl(CharacterRepository characterRepository, CharacterMapper characterMapper, LocationRepository locationRepository) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
    }

    @Override
    public List<CharacterDTO> getAllCharacters() {
        //List<CharacterDTO> allCharacters = characterMapper.listOfCharactersToCharactersDto(characterRepository.findAll());
        return characterMapper.listOfCharactersToCharactersDto(characterRepository.findAll());
    }

    @Override
    public CharacterDTO getCharacterById(Long id) {
        return characterMapper.characterToCharacterDto(characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(ErrorMessage.USE_VALID_ID.getMessage())));
    }

    @Override
    public CharacterDTO deleteCharacterById(Long id) {
        CharacterDTO characterDeletedById = characterMapper.characterToCharacterDto(characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(ErrorMessage.USE_VALID_ID.getMessage())));
        characterRepository.deleteById(id);
        return characterDeletedById;
    }

    @Override
    public Long deleteAllCharacters() {
        Long charactersDeletedCount = characterRepository.count();
        characterRepository.deleteAll();
        return charactersDeletedCount;
    }

    @Transactional
    public CharacterDTO createCharacter(CharacterDTO dto) {
        Character character = characterMapper.toEntity(dto);
        characterRepository.save(character);
        return characterMapper.characterToCharacterDto(character);
    }

    @Override
    public CharacterDTO updateCharacter(CharacterDTO characterDTO, Long id) {
        Character characterToUpdate = characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(ErrorMessage.USE_VALID_ID.getMessage()));
        characterToUpdate.setName(characterDTO.getName());
        characterToUpdate.setStatus(characterDTO.getStatus());
        characterToUpdate.setSpecies(characterDTO.getSpecies());
        characterToUpdate.setType(characterDTO.getType());
        characterToUpdate.setGender(characterDTO.getGender());
        characterToUpdate.setOrigin(characterDTO.getOrigin());
        characterToUpdate.setLocation(characterDTO.getLocation());
        characterRepository.save(characterToUpdate);
        return characterMapper.characterToCharacterDto(characterToUpdate);
    }

    @Override
    public Page<CharacterDTO> getCharactersPage(Pageable pageable) {
        Page<Character> characterEntityPage = characterRepository.findAll(pageable);
        return characterEntityPage.map(characterMapper::characterToCharacterDto);
    }
}
