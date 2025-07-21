package com.example.demo.controller;

import com.example.demo.dto.CharacterDTO;
import com.example.demo.service.CharacterImportService;
import com.example.demo.service.CharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/page")
    public ResponseEntity<Page<CharacterDTO>>getCharactersPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "characterId") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending
    ){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(characterService.getCharactersPage(pageable), HttpStatus.OK);
    }
}
