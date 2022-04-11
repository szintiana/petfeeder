package com.backend.petfeeder.Service;

import com.backend.petfeeder.DAO.PetDAO;
import com.backend.petfeeder.DAO.UserDAO;
import com.backend.petfeeder.DTO.UserDTO;
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

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map((Function<UserDAO, UserDTO>) UserDAO::toDTO).collect(Collectors.toList());
    }

    public UserDTO getById(Integer id) {
        UserDAO userDAO = userRepository.getById(id);
        if (userDAO != null) {
            return userDAO.toDTO();
        } else {
            return null;
        }
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
        if (userDAOAdded != null) {
            return userDAOAdded.toDTO();
        } else {
            return null;
        }
    }

    public UserDTO updateUser(UserDTO userDTO) {
        UserDAO userDAO = userRepository.findByEmailEquals(userDTO.getEmail());
        if (userDAO != null) {
            UserDAO updatedUser = userDTO.toDAO();
            updatedUser.setPassword(userDAO.getPassword());
            updatedUser.setId(userDAO.getId());
            return userRepository.save(updatedUser).toDTO();
        } else {
            return null;
        }
    }

    public UserDTO addPetToUser(UserDTO userDTO, PetDAO petDAO) {
        UserDAO userDAO = userRepository.findByEmailEquals(userDTO.getEmail());
        if (userDAO != null) {
            UserDAO updatedUser = userDTO.toDAO();
            updatedUser.setPassword(userDAO.getPassword());
            updatedUser.setId(userDAO.getId());
            List<PetDAO> petDAOList = updatedUser.getPets();
            if (petDAOList.stream().anyMatch(e -> e.getName().equals(petDAO.getName()))) {
                petDAOList.add(petDAO);
                userDAO.setPets(petDAOList);
                UserDAO userDAOSaved = userRepository.save(userDAO);
                if (userDAOSaved != null) {
                    return userDAOSaved.toDTO();
                }
            } //I think error handling will be needed here
        }
        return null;
    }

    public UserDTO removePetFromUser(UserDTO userDTO, PetDAO petDAO) {
        UserDAO userDAO = userRepository.findByEmailEquals(userDTO.getEmail());
        if (userDAO != null) {
            UserDAO updatedUser = userDTO.toDAO();
            updatedUser.setPassword(userDAO.getPassword());
            updatedUser.setId(userDAO.getId());
            List<PetDAO> petDAOList = updatedUser.getPets();
            if (petDAOList.stream().anyMatch(e -> e.getName().equals(petDAO.getName()))) {
                petDAOList.remove(petDAO);
                userDAO.setPets(petDAOList);
                UserDAO userDAOSaved = userRepository.save(userDAO);
                if (userDAOSaved != null) {
                    return userDAOSaved.toDTO();
                }
            } //I think error handling will be needed here
        }
        return null;
    }

    public void deleteUser(UserDTO userDTO) {
        UserDAO userDAO = userRepository.findByEmailEquals(userDTO.getEmail());
        userRepository.delete(userDAO);
    }
}
