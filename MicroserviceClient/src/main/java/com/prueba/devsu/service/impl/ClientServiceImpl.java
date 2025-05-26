package com.prueba.devsu.service.impl;

import com.prueba.devsu.constant.ClientMessages;
import com.prueba.devsu.dto.ClientDTO;
import com.prueba.devsu.entity.ClientEntity;
import com.prueba.devsu.exception.ClientNotFoundException;
import com.prueba.devsu.mapper.ClientMapper;
import com.prueba.devsu.repository.ClientRepository;
import com.prueba.devsu.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDTO findClientById(Long id) {
        return clientRepository.findById(id)
                .map(ClientMapper::clientEntityToClientDTO)
                .orElseThrow(() -> new ClientNotFoundException(String.format(ClientMessages.CLIENT_NOT_FOUND, id)));
    }

    @Override
    public List<ClientDTO> findClientAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientMapper::clientEntityToClientDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO createClient(ClientDTO client) {
        ClientEntity entity = ClientMapper.clientDtoToClientEntity(null, client);
        return ClientMapper.clientEntityToClientDTO(clientRepository.save(entity));
    }

    @Override
    public ClientDTO updateClient(Long id, ClientDTO client) {

        Optional<ClientEntity> clientExisting = clientRepository.findById(id);

        if (clientExisting.isEmpty()) {
            throw new ClientNotFoundException(String.format(ClientMessages.CLIENT_NOT_FOUND, id));
        }
        ClientEntity updated = ClientMapper.clientDtoToClientEntity(id, client);
        return ClientMapper.clientEntityToClientDTO(clientRepository.save(updated));
    }

    @Override
    public void deleteClient(Long id) {

        Optional<ClientEntity> clientExisting = clientRepository.findById(id);

        if (clientExisting.isEmpty()) {
            throw new ClientNotFoundException(String.format(ClientMessages.CLIENT_NOT_FOUND, id));
        }
        clientRepository.deleteById(id);
    }

}
