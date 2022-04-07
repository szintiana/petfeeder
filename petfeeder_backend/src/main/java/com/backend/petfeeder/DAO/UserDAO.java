package com.backend.petfeeder.DAO;

import com.backend.petfeeder.DTO.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
public class UserDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String username;
    @Column
    private String password;
    @ManyToMany
    @JoinTable(name = "users_pets", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("owners")
    private List<PetDAO> pets;

    public UserDTO toDTO() {
        return UserDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .username(username)
                .pets(pets.stream().map(PetDAO::toDTO).collect(Collectors.toList()))
                .build();
    }
}
