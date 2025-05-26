package com.prueba.devsu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.devsu.dto.ClientDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testCreateClient() throws Exception {
        ClientDTO client = ClientDTO.builder()
                .name("Juan")
                .gender("Masculino")
                .age(30)
                .identification("1234567890")
                .address("Calle Falsa 123")
                .phone("0987654321")
                .password("1234")
                .status(true)
                .build();

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Juan"));
    }

    @Test
    void testFindClientAll() throws Exception {
        mockMvc.perform(get("/clientes/listClient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testFindClientById() throws Exception {
        ClientDTO client = ClientDTO.builder()
                .name("Ana")
                .gender("Femenino")
                .age(25)
                .identification("987654321")
                .address("Calle Luna 456")
                .phone("0911223344")
                .password("abcd")
                .status(true)
                .build();

        String response = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ClientDTO createdClient = objectMapper.readValue(response, ClientDTO.class);

        mockMvc.perform(get("/clientes/" + createdClient.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ana"));
    }

    @Test
    void testUpdateClient() throws Exception {
        ClientDTO client = ClientDTO.builder()
                .name("Luis")
                .gender("Masculino")
                .age(28)
                .identification("555333222")
                .address("Av. Central")
                .phone("099998877")
                .password("pass")
                .status(true)
                .build();

        String response = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ClientDTO created = objectMapper.readValue(response, ClientDTO.class);

        ClientDTO updatedClient = ClientDTO.builder()
                .id(created.getId())
                .name("Luis")
                .gender("Masculino")
                .age(28)
                .identification("555333222")
                .address("Nueva Dirección")
                .phone("099998877")
                .password("pass")
                .status(true)
                .build();

        mockMvc.perform(put("/clientes/update/" + created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("Nueva Dirección"));
    }

    @Test
    void testDeleteClient() throws Exception {
        ClientDTO client = ClientDTO.builder()
                .name("Carlos")
                .gender("Masculino")
                .age(40)
                .identification("111222333")
                .address("Zona Norte")
                .phone("091111111")
                .password("eliminar")
                .status(true)
                .build();

        String response = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ClientDTO created = objectMapper.readValue(response, ClientDTO.class);

        mockMvc.perform(delete("/clientes/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Cliente eliminado exitosamente"));
    }

}