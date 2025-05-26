package com.prueba.devsu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class ClientDTO {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private Boolean status;
}