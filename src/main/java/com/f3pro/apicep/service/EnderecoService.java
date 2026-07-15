package com.f3pro.apicep.service;

import com.f3pro.apicep.client.ViaCepClient;
import com.f3pro.apicep.dto.ViaCepResponse;
import com.f3pro.apicep.entity.Endereco;
import com.f3pro.apicep.mapper.EnderecoMapper;
import com.f3pro.apicep.repository.EnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.regex.Pattern;

@Service
public class EnderecoService {

    private static final Pattern CEP_PATTERN = Pattern.compile("\\d{8}");

    private final ViaCepClient viaCepClient;
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(ViaCepClient viaCepClient, EnderecoRepository enderecoRepository) {
        this.viaCepClient = viaCepClient;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public Endereco buscarESalvarCep(String cepEntrada) {
        String cep = normalizar(cepEntrada);

        ViaCepResponse response = viaCepClient.consultarCep(cep);
        if (response == null || Boolean.TRUE.equals(response.erro())) {
            throw new IllegalArgumentException("CEP não encontrado: " + cep);
        }

        Endereco endereco = enderecoRepository.findByCep(response.cep())
                .orElseGet(Endereco::new);

        EnderecoMapper.atualizarEntity(endereco, response); // veja abaixo
        return enderecoRepository.save(endereco);
    }

    private String normalizar(String cepEntrada) {
        if (cepEntrada == null) {
            throw new IllegalArgumentException("CEP não pode ser nulo");
        }
        String cep = cepEntrada.replaceAll("\\D", "");
        if (!CEP_PATTERN.matcher(cep).matches()) {
            throw new IllegalArgumentException("CEP inválido: " + cepEntrada);
        }
        return cep;
    }
}