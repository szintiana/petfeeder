package com.backend.petfeeder.Service;

import com.backend.petfeeder.DAO.PetDAO;
import com.backend.petfeeder.DAO.UserDAO;
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
        PetDAO petDAOAdded = petRepository.save(petDAO);
        return petDAOAdded.toDTO();
    }

    public PetDTO updatePet(PetDTO petDTO, String username) {
        List<PetDAO> petDAOList = petRepository.findByNameEqualsAndBreedEqualsAndBirthDateEquals(petDTO.getName(),
                petDTO.getBreed(), petDTO.getBirthDate());
        for (PetDAO petDAO : petDAOList) {
            List<UserDAO> userDAOList = petDAO.getOwners();
            for (UserDAO userDAO : userDAOList) {
                if (userDAO.getUsername().equals(username)) {
                    PetDAO petDAOUpdated = petDTO.toDAO();
                    petDAOUpdated.setId(petDAO.getId());
                    petDAOUpdated.setOwners(petDAO.getOwners());
                    petDAOUpdated = petRepository.save(petDAOUpdated);
                    if (petDAOUpdated != null) {
                        return petDAOUpdated.toDTO();
                    }
                }
            }
        }
        return null;
    }
}
