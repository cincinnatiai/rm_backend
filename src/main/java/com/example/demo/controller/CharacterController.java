package com.example.demo.controller;

import com.example.demo.dto.CharacterDTO;
import com.example.demo.service.CharacterImportService;
import com.example.demo.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/characters")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService, CharacterImportService importService) {
        this.characterService = characterService;
        this.importService = importService;
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getAllCharacters(){
        List<CharacterDTO> allCharactersDto = characterService.getAllCharacters();
        return new ResponseEntity<>(allCharactersDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getCharacterById(@PathVariable Long id){
        CharacterDTO characterFoundById = characterService.getCharacterById(id);
        return new ResponseEntity<>(characterFoundById, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Long> deleteAllCharacters(){
        Long charactersErased = characterService.deleteAllCharacters();
        return new ResponseEntity<>(charactersErased, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CharacterDTO> deleteCharactersById(@PathVariable Long id){
        CharacterDTO characterDeletedById = characterService.deleteCharacterById(id);
        return new ResponseEntity<>(characterDeletedById, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CharacterDTO> editCharacter(@PathVariable Long id, @RequestBody CharacterDTO characterDTO){
        CharacterDTO characterToUpdate = characterService.updateCharacter(characterDTO, id);
        return new ResponseEntity<>(characterToUpdate, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<CharacterDTO> createCharacter(@RequestBody CharacterDTO characterDTO){
        CharacterDTO characterCreated = characterService.createCharacter(characterDTO);
        return new ResponseEntity<>(characterCreated, HttpStatus.CREATED);
    }

    private final CharacterImportService importService;

    @GetMapping("/getAll")
    public ResponseEntity<Void> importAll() {
        importService.importAll();
        return ResponseEntity.accepted().build();
    }
}
