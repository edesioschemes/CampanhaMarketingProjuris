package com.campanhamarketing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.campanhamarketing.entity.CampanhaEntity;
import com.campanhamarketing.model.CampanhaModel;
import com.campanhamarketing.repository.CampanhaRepository;

@Component
public class CampanhaService implements UserDetailsService {

	@Autowired
	private CampanhaRepository campanhaRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		return usuarioService.loadUserByUsername(login);
	}

	public void incluirCampanha(CampanhaModel campanhaModel) {
		if (this.validarNomeCampanha(campanhaModel)) {
			throw new PersistenceException("Nome de campanha ja existente.");
		} else {
			if (campanhaModel.isAtivo()) {
				campanhaRepository.updateStatus(false);
			}
			CampanhaEntity campanhaEntity = new CampanhaEntity();
			campanhaEntity.setAtivo(campanhaModel.isAtivo());
			campanhaEntity.setNome(campanhaModel.getNome());
			campanhaEntity.setLocalizacao(campanhaModel.getLocalizacao());

			this.campanhaRepository.save(campanhaEntity);
		}
	}

	public void excluirCampanha(Long codigoCampanha) {
		this.campanhaRepository.deleteById(codigoCampanha);
	}

	public void alterarCampanha(CampanhaModel campanhaModel) {
		if (this.validarNomeCampanha(campanhaModel)) {
			throw new PersistenceException("Nome de campanha ja existente.");
		} else {
			if (campanhaModel.isAtivo()) {
				campanhaRepository.updateStatus(false);
			}
			Optional<CampanhaEntity> campanhaEntity = this.campanhaRepository.findById(campanhaModel.getCodigo());
			campanhaEntity.get().setAtivo(campanhaModel.isAtivo());
			campanhaEntity.get().setNome(campanhaModel.getNome());
			campanhaEntity.get().setLocalizacao(campanhaModel.getLocalizacao());

			this.campanhaRepository.saveAndFlush(campanhaEntity.get());
		}
	}

	public CampanhaModel consultarCampanha(Long codigoCampanha) {

		Optional<CampanhaEntity> campanhaEntity = this.campanhaRepository.findById(codigoCampanha);
		return new CampanhaModel(campanhaEntity.get().getCodigo(), campanhaEntity.get().getNome(),
				campanhaEntity.get().getLocalizacao(), campanhaEntity.get().isAtivo());

	}

	public List<CampanhaModel> consultarCampanhas() {

		List<CampanhaModel> campanhaModel = new ArrayList<CampanhaModel>();
		List<CampanhaEntity> campanhasEntity = this.campanhaRepository.findAllByOrderByAtivoDesc();
		campanhasEntity.forEach(campanhaEntity -> {
			campanhaModel.add(new CampanhaModel(campanhaEntity.getCodigo(), campanhaEntity.getNome(),
					campanhaEntity.getLocalizacao(), campanhaEntity.isAtivo()));
		});
		return campanhaModel;
	}

	public List<CampanhaModel> consultarCampanhasByStatus(boolean ativo) {

		List<CampanhaModel> campanhaModel = new ArrayList<CampanhaModel>();
		List<CampanhaEntity> campanhasEntity = this.campanhaRepository.findByAtivo(ativo);
		campanhasEntity.forEach(campanhaEntity -> {
			campanhaModel.add(new CampanhaModel(campanhaEntity.getCodigo(), campanhaEntity.getNome(),
					campanhaEntity.getLocalizacao(), campanhaEntity.isAtivo()));
		});
		return campanhaModel;
	}

	private boolean validarNomeCampanha(CampanhaModel campanhaModel) {
		List<CampanhaModel> campanhasModel = this.consultarCampanhas();
		return campanhasModel.stream().filter(
				campanha -> campanha.getCodigo() == null || !campanha.getCodigo().equals(campanhaModel.getCodigo()))
				.anyMatch(campanha -> campanha.getNome().equalsIgnoreCase(campanhaModel.getNome()));
	}

}
