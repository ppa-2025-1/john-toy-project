package com.example.demo.repository;

import java.util.Optional;
import java.util.List;

import com.example.demo.repository.entity.Chamado;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ChamadoRepository extends BaseRepository<Chamado, Integer> {

    Optional<Chamado> findById(Integer id);

    /**
     * Atualiza a situação de um chamado, seguindo as regras de transição:
     * - NOVO => ANDAMENTO
     * - NOVO => CANCELADO
     * - ANDAMENTO => CANCELADO
     * - ANDAMENTO => RESOLVIDO
     *
     * @param id            ID do chamado a ser atualizado
     * @param novaSituacao  Nova situação do chamado
     * @param situacaoAtual Situação atual do chamado (para validação)
     * @return número de registros atualizados (deve ser 1 se bem-sucedido)
     */
    @Modifying
    @Transactional
    @Query("UPDATE Chamado c SET c.situacao = :novaSituacao WHERE c.id = :id AND c.situacao = :situacaoAtual")
    int atualizarSituacao(
            @Param("id") Integer id,
            @Param("novaSituacao") String novaSituacao,
            @Param("situacaoAtual") String situacaoAtual);

    /**
     * Encontra chamados por situação
     */
    List<Chamado> findBySituacao(String situacao);
}