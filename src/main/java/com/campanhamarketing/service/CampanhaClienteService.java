package com.campanhamarketing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.campanhamarketing.entity.CampanhaClienteEntity;
import com.campanhamarketing.repository.CampanhaClienteRepository;

@Component
public class CampanhaClienteService {

	@Autowired
	CampanhaClienteRepository campanhaClienteRepository;

	public void incluirCampanhaCliente(Long idCampanha, Long idUsuario, String siglaCliente) {

		CampanhaClienteEntity campanhaClienteEntity = new CampanhaClienteEntity();
		campanhaClienteEntity.setIdCampanha(idCampanha);
		campanhaClienteEntity.setIdUsuario(idUsuario);
		campanhaClienteEntity.setSiglaCliente(siglaCliente);

		this.campanhaClienteRepository.save(campanhaClienteEntity);

	}

	public CampanhaClienteEntity consultarCampanhaClienteByIdCampanhaSigla(Long idCampanha, Long idUsuario,
			String siglaCliente) {

		CampanhaClienteEntity campanhaClienteEntity = this.campanhaClienteRepository
				.findByIdCampanhaAndIdUsuarioAndSiglaCliente(idCampanha, idUsuario, siglaCliente);
		return campanhaClienteEntity;

	}

}
