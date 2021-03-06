package com.backend.petfeeder.Controller;

import com.backend.petfeeder.DAO.PetDAO;
import com.backend.petfeeder.DAO.UserDAO;
import com.backend.petfeeder.DTO.PetDTO;
import com.backend.petfeeder.DTO.UserDTO;
import com.backend.petfeeder.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("getAllUsers")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @PostMapping("login/getUserByUsernameAndPassword/{username}/{password}")
    public ResponseEntity<UserDTO> getUserByUsernameAndPassword(@PathVariable("username") String username,
                                                                @PathVariable("password") String password) {
        UserDTO userDTO = userService.getByUsernameAndPassword(username, password);
        if (userDTO != null) {
            userService.updateUser(userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("login/getUserByEmailAndPassword/{email}/{password}")
    public ResponseEntity<UserDTO> getUserByEmailAndPassword(@PathVariable("email") String email,
                                                             @PathVariable("password") String password) {
        UserDTO userDTO = userService.getByEmailAndPassword(email, password);
        if (userDTO != null) {
            userService.updateUser(userDTO);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("addUser")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDAO userDAO) {
        UserDTO userDTO = userService.addUser(userDAO);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PostMapping("updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userDTO);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PostMapping("addPetToUser/{email}")
    public ResponseEntity<UserDTO> addPetToUser(@RequestBody PetDAO petDAO, @PathVariable("email") String email) {
        UserDTO updatedUser = userService.addPetToUser(email, petDAO);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PostMapping("removePetFromUser/{email}/{petName}")
    public ResponseEntity<UserDTO> removePetFromUser(@PathVariable("petName") String petName, @PathVariable("email") String email) {
        UserDTO updatedUser = userService.removePetFromUser(email, petName);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/login/{identifier}/{password}")
    public ResponseEntity<UserDTO> login(@PathVariable("identifier") String identifier,
                                         @PathVariable("password") String password) {
        UserDTO userDTO = userService.getByEmailAndPassword(identifier, password);
        if (userDTO != null) {
            UserDTO updatedUser = userService.updateUser(userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            userDTO = userService.getByUsernameAndPassword(identifier, password);
            if (userDTO != null) {
                UserDTO updatedUser = userService.updateUser(userDTO);
                return new ResponseEntity<>(updatedUser, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getPetsFromUsername/{username}")
    public ResponseEntity<List<PetDTO>> getPetsFromUser(@PathVariable("username") String username) {
        List<PetDTO> petDTOList = userService.getPetsFromUser(username);
        if (petDTOList != null) {
            return new ResponseEntity<>(petDTOList, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
