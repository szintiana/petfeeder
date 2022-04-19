package com.backend.petfeeder.DTO;

import com.backend.petfeeder.DAO.UserDAO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private String token;

    public UserDAO toDAO() {
        return UserDAO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .email(email)
                .token(token)
                //.pets(pets.stream().map(PetDTO::toDAO).collect(Collectors.toList()))
                .build();
    }
}
