package com.f3pro.apicep.dto;

import com.f3pro.apicep.entity.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCepResponse(
        String cep, String logradouro, String complemento, String bairro,
        String localidade, String uf, String estado, String regiao,
        String ibge, String gia, String ddd, String siafi,
        Boolean erro
) {
    public static ViaCepResponse fromEntity(Endereco endereco) {
        return new ViaCepResponse(
                endereco.getCep(), endereco.getLogradouro(), endereco.getComplemento(),
                endereco.getBairro(), endereco.getLocalidade(), endereco.getUf(),
                endereco.getEstado(), endereco.getRegiao(), endereco.getIbge(),
                endereco.getGia(), endereco.getDdd(), endereco.getSiafi(), false
        );
    }
}