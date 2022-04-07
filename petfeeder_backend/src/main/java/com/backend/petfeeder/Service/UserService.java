package com.backend.petfeeder.Service;

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
        return userRepository.getById(id).toDTO();
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
        return userRepository.save(userDAO).toDTO();
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

    public void deleteUser(UserDTO userDTO) {
        UserDAO userDAO = userRepository.findByEmailEquals(userDTO.getEmail());
        userRepository.delete(userDAO);
    }
}
