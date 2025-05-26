package com.prueba.devsu.controller;

import com.prueba.devsu.constant.ClientMessages;
import com.prueba.devsu.dto.ClientDTO;
import com.prueba.devsu.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findClientById(id));
    }

    @GetMapping("/listClient")
    public ResponseEntity<List<ClientDTO>> findClientAll() {
        return ResponseEntity.ok(clientService.findClientAll());
    }

    @PostMapping()
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO client) {
        return ResponseEntity.ok(clientService.createClient(client));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO client) {
        return ResponseEntity.ok(clientService.updateClient(id, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok(Map.of("message", ClientMessages.CLIENT_DELETED));
    }

}
