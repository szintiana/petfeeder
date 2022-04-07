package com.backend.petfeeder.DTO;

import com.backend.petfeeder.DAO.UserDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private List<PetDTO> pets;

    public UserDAO toDAO() {
        return UserDAO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .email(email)
                .pets(pets.stream().map(PetDTO::toDAO).collect(Collectors.toList()))
                .build();
    }
}
