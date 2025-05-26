package com.prueba.devsu.mapper;

import com.prueba.devsu.dto.ClientDTO;
import com.prueba.devsu.entity.ClientEntity;

public class ClientMapper {

    public static ClientDTO clientEntityToClientDTO(ClientEntity clientEntity) {
        return ClientDTO
                .builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .gender(clientEntity.getGender())
                .age(clientEntity.getAge())
                .identification(clientEntity.getIdentification())
                .address(clientEntity.getAddress())
                .phone(clientEntity.getPhone())
                .password(clientEntity.getPassword())
                .status(clientEntity.getStatus())
                .build();
    }

    public static ClientEntity clientDtoToClientEntity(Long id, ClientDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setId(id);
        entity.setName(dto.getName());
        entity.setGender(dto.getGender());
        entity.setAge(dto.getAge());
        entity.setIdentification(dto.getIdentification());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}