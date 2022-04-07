package com.backend.petfeeder.Controller;

import com.backend.petfeeder.DAO.UserDAO;
import com.backend.petfeeder.DTO.UserDTO;
import com.backend.petfeeder.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getAllUsers")
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @GetMapping("/getUserByUsernameAndPassword/{username}/{password}")
    public ResponseEntity<UserDTO> getUserByUsernameAndPassword(@PathVariable("username") String username,
                                                               @PathVariable("password") String password) {
        UserDTO userDTO = userService.getByUsernameAndPassword(username, password);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getUserByEmailAndPassword/{email}/{password}")
    public ResponseEntity<UserDTO> getUserByEmailAndPassword(@PathVariable("email") String email,
                                                             @PathVariable("password") String password) {
        UserDTO userDTO = userService.getByEmailAndPassword(email, password);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDAO userDAO) {
        UserDTO userDTO = userService.addUser(userDAO);
        if (userDTO != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser (@RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userDTO);
        if (updatedUser != null) {
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
