package com.campanhamarketing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.campanhamarketing.entity.CampanhaEntity;

@Repository
public interface CampanhaRepository extends JpaRepository<CampanhaEntity, Long> {

	CampanhaEntity findByNome(String nome);

	List<CampanhaEntity> findByAtivo(boolean ativo);

	List<CampanhaEntity> findAllByOrderByAtivoDesc();

	@Modifying
	@Query("UPDATE CampanhaEntity SET ativo = :status")
	@Transactional
	void updateStatus(@Param("status") boolean status);

}
