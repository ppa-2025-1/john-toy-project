package com.jaime.mschamado.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import com.jaime.mschamado.model.entity.Chamado;

public interface ChamadoRepository extends BaseRepository<Chamado, Integer> {

  
    boolean existsById(Integer id);

}

