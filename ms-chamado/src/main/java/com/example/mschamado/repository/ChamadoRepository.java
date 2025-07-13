package com.example.mschamado.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.example.mschamado.model.entity.Chamado;

public interface ChamadoRepository extends BaseRepository<Chamado, Integer> {

  
    boolean existsById(Integer id);

}
