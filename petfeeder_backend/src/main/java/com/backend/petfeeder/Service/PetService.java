package com.backend.petfeeder.Service;

import com.backend.petfeeder.DAO.PetDAO;
import com.backend.petfeeder.DTO.PetDTO;
import com.backend.petfeeder.Repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    @Lazy
    PetRepository petRepository;

    public List<PetDTO> getAll() {
        List<PetDAO> petDAOList = petRepository.findAll();
        if (!petDAOList.isEmpty()) {
            return petDAOList.stream().map(PetDAO::toDTO).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public PetDTO addPet(PetDAO petDAO) {
        return petRepository.save(petDAO).toDTO();
    }
}
