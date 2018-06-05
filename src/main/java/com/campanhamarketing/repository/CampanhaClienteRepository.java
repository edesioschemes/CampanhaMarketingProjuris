package com.campanhamarketing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campanhamarketing.entity.CampanhaClienteEntity;

@Repository
public interface CampanhaClienteRepository extends JpaRepository<CampanhaClienteEntity, Long> {

	CampanhaClienteEntity findByIdCampanha(Long idCampanha);

	CampanhaClienteEntity findBySiglaCliente(String siglaCliente);

	CampanhaClienteEntity findByIdCampanhaAndSiglaCliente(Long idCampanha, String siglaCliente);

}
