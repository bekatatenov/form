package com.exam9.exam9.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;

    @Column
    @Builder.Default
    private boolean enabled = true;

    @Builder.Default
    private String role = "USER";
}
