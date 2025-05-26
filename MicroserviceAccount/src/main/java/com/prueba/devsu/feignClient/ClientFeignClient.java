package com.prueba.devsu.feignClient;

import com.prueba.devsu.dto.ClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MicroserviceClient", url = "http://localhost:8080/clientes")
public interface ClientFeignClient {

    @GetMapping("/{id}")
    ClientDto findClientById(@PathVariable("id") Long id);
}
