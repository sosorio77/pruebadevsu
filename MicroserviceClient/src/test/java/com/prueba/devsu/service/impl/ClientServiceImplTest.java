package com.prueba.devsu.service.impl;

import com.prueba.devsu.constant.ClientMessages;
import com.prueba.devsu.dto.ClientDTO;
import com.prueba.devsu.entity.ClientEntity;
import com.prueba.devsu.exception.ClientNotFoundException;
import com.prueba.devsu.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findClientById() {
        Long id = 1L;
        ClientEntity entity = new ClientEntity();
        entity.setId(id);
        entity.setName("Santiago");

        Mockito.when(clientRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));

        ClientDTO dto = clientService.findClientById(id);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(id, dto.getId());
    }

    @Test
    void findClientByIdClientNotFound() {
        Long id = 2L;

        Mockito.when(clientRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(ClientNotFoundException.class, () -> {
            clientService.findClientById(id);
        });

        Assertions.assertEquals(String.format(ClientMessages.CLIENT_NOT_FOUND, id), exception.getMessage());
    }

    @Test
    public void findClientAll() {

        ClientEntity entity1 = new ClientEntity();
        entity1.setId(1L);
        entity1.setName("User 1");

        ClientEntity entity2 = new ClientEntity();
        entity2.setId(2L);
        entity2.setName("User 2");

        Mockito.when(clientRepository.findAll()).thenReturn(Arrays.asList(entity1, entity2));

        List<ClientDTO> result = clientService.findClientAll();

        assertEquals(2, result.size());

    }

    @Test
    public void createClient() {

        ClientDTO dto = ClientDTO.builder()
                .name("Santiago")
                .password("123")
                .status(true)
                .build();

        ClientEntity savedEntity = new ClientEntity();
        savedEntity.setId(1L);
        savedEntity.setName(dto.getName());
        savedEntity.setPassword(dto.getPassword());
        savedEntity.setStatus(dto.getStatus());

        Mockito.when(clientRepository.save(Mockito.any(ClientEntity.class))).thenReturn(savedEntity);

        ClientDTO result = clientService.createClient(dto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(dto.getName(), result.getName());

    }

    @Test
    public void updateClient() {

        Long id = 1L;

        ClientEntity existing = new ClientEntity();
        existing.setId(id);
        existing.setName("Antiguo");

        Mockito.when(clientRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(existing));
        Mockito.when(clientRepository.save(Mockito.any(ClientEntity.class))).thenAnswer(i -> i.getArgument(0));

        ClientDTO dtoUpdate = ClientDTO.builder()
                .name("Santiago")
                .password("123")
                .status(true)
                .build();

        ClientDTO result = clientService.updateClient(id, dtoUpdate);

        Assertions.assertEquals(dtoUpdate.getName(), result.getName());

    }

    @Test
    void updateClientNotFound() {
        Long id = 2L;

        ClientDTO dto = ClientDTO.builder().name("X").build();

        Mockito.when(clientRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Exception ex = assertThrows(ClientNotFoundException.class, () -> {
            clientService.updateClient(id, dto);
        });

        assertEquals(String.format(ClientMessages.CLIENT_NOT_FOUND, id), ex.getMessage());
    }


    @Test
    public void deleteClient() {

        Long id = 1L;
        ClientEntity entity = new ClientEntity();
        entity.setId(id);

        Mockito.when(clientRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));

        clientService.deleteClient(id);

        verify(clientRepository).deleteById(id);

    }

    @Test
    void deleteClient_shouldThrowException_whenNotFound() {
        Long id = 99L;

        Mockito.when(clientRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Exception ex = assertThrows(ClientNotFoundException.class, () -> {
            clientService.deleteClient(id);
        });

        assertEquals(String.format(ClientMessages.CLIENT_NOT_FOUND, id), ex.getMessage());
    }
}