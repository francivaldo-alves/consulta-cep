package com.f3pro.apicep.client;

import com.f3pro.apicep.dto.ViaCepResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Component
public class ViaCepClient {

    private final RestClient restClient;

    public ViaCepClient(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("https://viacep.com.br/ws/").build();
    }

    public ViaCepResponse consultarCep(String cep) {
        try {
            return restClient.get()
                    .uri("{cep}/json/", cep)
                    .retrieve()
                    .body(ViaCepResponse.class);
        } catch (RestClientException ex) {
            throw new IllegalStateException("Falha ao consultar a API ViaCEP para o CEP " + cep, ex);
        }
    }
}