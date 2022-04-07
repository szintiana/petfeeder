package com.backend.petfeeder.Controller;

import com.backend.petfeeder.DAO.PetDAO;
import com.backend.petfeeder.DTO.PetDTO;
import com.backend.petfeeder.Service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetController {
    @Autowired
    @Lazy
    PetService petService;

    @GetMapping("/getAllPets")
    public List<PetDTO> getAll() {
        return petService.getAll();
    }

    @PostMapping("/addPet")
    public ResponseEntity<PetDTO> addPet(@RequestBody PetDAO petDAO) {
        PetDTO petDTO = petService.addPet(petDAO);
        if (petDTO != null) {
            return new ResponseEntity<>(petDTO, HttpStatus.CREATED);
        } else {
          return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
