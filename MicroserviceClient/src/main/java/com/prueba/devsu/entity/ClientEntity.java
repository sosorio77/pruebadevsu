package com.prueba.devsu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity extends PersonEntity {

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean status;
}
