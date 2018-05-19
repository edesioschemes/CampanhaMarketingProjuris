package com.campanhamarketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campanhamarketing.entity.CampanhaEntity;

@Repository
public interface CampanhaRepository extends JpaRepository<CampanhaEntity, Long> {

	CampanhaEntity findByNome(String nome);

	List<CampanhaEntity> findByAtivo(boolean ativo);

}
