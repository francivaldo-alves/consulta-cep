package com.f3pro.apicep.mapper;

import com.f3pro.apicep.dto.ViaCepResponse;
import com.f3pro.apicep.entity.Endereco;

public final class EnderecoMapper {

    private EnderecoMapper() {
    }

    public static void atualizarEntity(Endereco endereco, ViaCepResponse dto) {
        endereco.setCep(dto.cep());
        endereco.setLogradouro(dto.logradouro());
        endereco.setComplemento(dto.complemento());
        endereco.setBairro(dto.bairro());
        endereco.setLocalidade(dto.localidade());
        endereco.setUf(dto.uf());
        endereco.setEstado(dto.estado());
        endereco.setRegiao(dto.regiao());
        endereco.setIbge(dto.ibge());
        endereco.setGia(dto.gia());
        endereco.setDdd(dto.ddd());
        endereco.setSiafi(dto.siafi());
    }

    public static ViaCepResponse toResponse(Endereco endereco) {
        return ViaCepResponse.fromEntity(endereco);
    }
}