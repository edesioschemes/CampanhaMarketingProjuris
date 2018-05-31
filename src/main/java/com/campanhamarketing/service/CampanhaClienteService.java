package com.campanhamarketing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.campanhamarketing.entity.CampanhaClienteEntity;
import com.campanhamarketing.model.CampanhaClienteModel;
import com.campanhamarketing.repository.CampanhaClienteRepository;

@Component
public class CampanhaClienteService {

	@Autowired
	CampanhaClienteRepository campanhaClienteRepository;

	public void incluirCampanhaCliente(CampanhaClienteModel campanhaClienteModel) {

		if (this.consultarCampanhaClienteByIdCampanhaSigla(campanhaClienteModel.getIdCampanha(),
				campanhaClienteModel.getSiglaCliente()) == null) {
			CampanhaClienteEntity campanhaClienteEntity = new CampanhaClienteEntity();
			campanhaClienteEntity.setIdCampanha(campanhaClienteModel.getIdCampanha());
			campanhaClienteEntity.setSiglaCliente(campanhaClienteModel.getSiglaCliente());

			this.campanhaClienteRepository.save(campanhaClienteEntity);
		}
	}

	public CampanhaClienteModel consultarCampanhaClienteByIdCampanhaSigla(Long idCampanha, String siglaCliente) {

		CampanhaClienteEntity campanhaClienteEntity = this.campanhaClienteRepository
				.findByIdCampanhaSiglaCliente(idCampanha, siglaCliente);
		return new CampanhaClienteModel(campanhaClienteEntity.getCodigo(), campanhaClienteEntity.getIdCampanha(),
				campanhaClienteEntity.getSiglaCliente());
	}

}
