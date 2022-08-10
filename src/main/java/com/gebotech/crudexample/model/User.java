package com.gebotech.crudexample.model;

import com.gebotech.crudexample.model.request.UserRequest;
import com.gebotech.crudexample.model.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany
    private List<Expenses> expensesList;

    public User() {

    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static UserResponse toResponse(User user) {
        return  UserResponse.builder()
                .username(user.username)
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }
//
//    public static User toEntity(UserRequest request) {
//        return new UserBuilder()
//                .username(request.getUsername())
//                .email(request.getEmail())
//                .password(request.getPassword())
//                .roles(request.getRoles())
//                .build();
//    }

}
