package com.f3pro.apicep.controller;

import com.f3pro.apicep.dto.ViaCepResponse;
import com.f3pro.apicep.entity.Endereco;
import com.f3pro.apicep.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
        ("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/{cep}")
    public ResponseEntity<ViaCepResponse> buscarESalvar(@PathVariable String cep) {

        Endereco endereco = enderecoService.buscarESalvarCep(cep);

        return ResponseEntity.ok(ViaCepResponse.fromEntity(endereco));
    }
}