package com.backend.petfeeder.Service;

import com.backend.petfeeder.DAO.PetDAO;
import com.backend.petfeeder.DAO.UserDAO;
import com.backend.petfeeder.DTO.PetDTO;
import com.backend.petfeeder.DTO.UserDTO;
import com.backend.petfeeder.Repository.PetRepository;
import com.backend.petfeeder.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    @Lazy
    UserRepository userRepository;
    @Autowired
    @Lazy
    PetRepository petRepository;

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map((Function<UserDAO, UserDTO>) UserDAO::toDTO).collect(Collectors.toList());
    }

    public UserDTO getById(Integer id) {
        UserDAO userDAO = userRepository.getById(id);
        return userDAO.toDTO();
    }

    public UserDTO getByEmailAndPassword(String email, String password) {
        UserDAO userDAO = userRepository.findByEmailEqualsAndPasswordEquals(email, password);
        if (userDAO != null) {
            return userDAO.toDTO();
        } else {
            return null;
        }
    }

    public UserDTO getByUsernameAndPassword(String username, String password) {
        UserDAO userDAO = userRepository.findByUsernameEqualsAndPasswordEquals(username, password);
        if (userDAO != null) {
            return userDAO.toDTO();
        } else {
            return null;
        }
    }

    public UserDTO addUser(UserDAO userDAO) {
        UserDAO userDAOAdded = userRepository.save(userDAO);
        return userDAOAdded.toDTO();
    }

    public UserDTO updateUser(UserDTO userDTO) {
        UserDAO userDAO = userRepository.findByEmailEquals(userDTO.getEmail());
        if (userDAO != null) {
            UserDAO updatedUser = userDTO.toDAO();
            updatedUser.setPassword(userDAO.getPassword());
            updatedUser.setId(userDAO.getId());
            updatedUser.setPets(userDAO.getPets());
            return userRepository.save(updatedUser).toDTO();
        } else {
            return null;
        }
    }

    public UserDTO addPetToUser(String email, PetDAO petDAO) {
        UserDAO userDAO = userRepository.findByEmailEquals(email);
        if (userDAO != null) {
            List<PetDAO> petDAOList = userDAO.getPets();
            if (petDAOList.stream().noneMatch(e -> e.getName().equals(petDAO.getName()))) {
                petDAOList.add(petDAO);
                userDAO.setPets(petDAOList);
                petRepository.save(petDAO);
                UserDAO userDAOSaved = userRepository.save(userDAO);
                return userDAOSaved.toDTO();
            } //I think error handling will be needed here
        }
        return null;
    }

    public UserDTO removePetFromUser(String email, String petName) {
        UserDAO userDAO = userRepository.findByEmailEquals(email);
        if (userDAO != null) {
            List<PetDAO> petDAOList = userDAO.getPets();
            if (petDAOList.stream().anyMatch(e -> e.getName().equals(petName))) {
                PetDAO petDAO = petDAOList.stream().filter(e -> e.getName().equals(petName))
                        .findAny().orElse(null);
                if (petDAO != null) {
                    petDAOList.remove(petDAO);
                    userDAO.setPets(petDAOList);
                    UserDAO userDAOSaved = userRepository.save(userDAO);
                    return userDAOSaved.toDTO();
                }
            } //I think error handling will be needed here
        }
        return null;
    }

    public List<PetDTO> getPetsFromUser(String username) {
        UserDAO userDAO = userRepository.findByUsernameEquals(username);
        if (userDAO != null && !userDAO.getPets().isEmpty()) {
            return userDAO.getPets().stream().map(PetDAO::toDTO).collect(Collectors.toList());
        }
        return null;
    }

    public void deleteUser(UserDTO userDTO) {
        UserDAO userDAO = userRepository.findByEmailEquals(userDTO.getEmail());
        userRepository.delete(userDAO);
    }
}
