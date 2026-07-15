package com.f3pro.apicep.repository;

import com.f3pro.apicep.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findByCep(String cep);
}