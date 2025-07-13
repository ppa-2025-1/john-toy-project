package com.example.mschamado.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.example.mschamado.model.entity.BaseEntity;

// Parâmetro de Tipo: generics, template, ..
public interface BaseRepository<T extends BaseEntity, ID> extends ListCrudRepository<T, ID> {
    

    // T findByIdAndDeletedIsTrue(ID id);

}
