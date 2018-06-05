package com.campanhamarketing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.campanhamarketing.entity.CampanhaClienteEntity;
import com.campanhamarketing.model.CampanhaModel;
import com.campanhamarketing.repository.CampanhaClienteRepository;

@Component
public class CampanhaClienteService {

	@Autowired
	CampanhaClienteRepository campanhaClienteRepository;

	@Autowired
	CampanhaService campanhaService;

	public void incluirCampanhaCliente(Long idCampanha, String siglaCliente) {

		if (this.consultarCampanhaClienteByIdCampanhaSigla(idCampanha, siglaCliente) == null) {
			CampanhaClienteEntity campanhaClienteEntity = new CampanhaClienteEntity();
			campanhaClienteEntity.setIdCampanha(idCampanha);
			campanhaClienteEntity.setSiglaCliente(siglaCliente);

			this.campanhaClienteRepository.save(campanhaClienteEntity);
		}
	}

	public CampanhaModel consultarCampanhaClienteByIdCampanhaSigla(Long idCampanha, String siglaCliente) {

		CampanhaClienteEntity campanhaClienteEntity = this.campanhaClienteRepository
				.findByIdCampanhaAndSiglaCliente(idCampanha, siglaCliente);

		if (campanhaClienteEntity == null) {
			return this.campanhaService.consultarPrimeiraCampanhaAtiva();
		} else {
			return campanhaService.consultarCampanha(campanhaClienteEntity.getIdCampanha());
		}
	}

}
