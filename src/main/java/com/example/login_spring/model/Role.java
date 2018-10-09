package com.example.login_spring.model;


import lombok.Data;

import javax.persistence.*;

@Table(name = "role")
@Data
@Entity
public class Role {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String role;

}
