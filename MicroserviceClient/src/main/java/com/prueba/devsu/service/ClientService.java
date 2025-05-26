package com.prueba.devsu.service;

import com.prueba.devsu.dto.ClientDTO;

import java.util.List;

public interface ClientService {

    ClientDTO findClientById(Long id);
    List<ClientDTO> findClientAll();
    ClientDTO createClient(ClientDTO client);
    ClientDTO updateClient(Long id, ClientDTO client);
    void deleteClient(Long id);
}
