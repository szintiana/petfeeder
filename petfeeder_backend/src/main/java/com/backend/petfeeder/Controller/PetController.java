package com.backend.petfeeder.Controller;

import com.backend.petfeeder.DAO.PetDAO;
import com.backend.petfeeder.DTO.PetDTO;
import com.backend.petfeeder.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetController {
    @Autowired
    @Lazy
    PetService petService;

    @GetMapping("getAllPets")
    public List<PetDTO> getAll() {
        return petService.getAll();
    }

    @PostMapping("addPet")
    public ResponseEntity<PetDTO> addPet(@RequestBody PetDAO petDAO) {
        PetDTO petDTO = petService.addPet(petDAO);
        if (petDTO != null) {
            return new ResponseEntity<>(petDTO, HttpStatus.CREATED);
        } else {
          return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PutMapping("updatePet/{username}")
    public ResponseEntity<PetDTO> updatePet(@RequestBody PetDTO petDTO, @PathVariable("username") String username) {
        PetDTO petDTOUpdated = petService.updatePet(petDTO, username);
        if (petDTOUpdated != null) {
            return new ResponseEntity<>(petDTOUpdated, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
