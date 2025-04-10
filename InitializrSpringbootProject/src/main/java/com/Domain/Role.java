package com.Domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;  // "ROLE_ADMIN", "ROLE_USER", etc.

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
