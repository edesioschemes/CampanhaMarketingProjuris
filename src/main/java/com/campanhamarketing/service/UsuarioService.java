package com.campanhamarketing.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.campanhamarketing.entity.UsuarioEntity;
import com.campanhamarketing.model.UsuarioModel;
import com.campanhamarketing.model.UsuarioSecurityModel;
import com.campanhamarketing.repository.UsuarioRepository;

@Component
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws BadCredentialsException, DisabledException {

		UsuarioEntity usuarioEntity = usuarioRepository.findByLogin(login);

		if (usuarioEntity == null)
			throw new BadCredentialsException("Usuário não encontrado no sistema!");

		if (!usuarioEntity.isAtivo())
			throw new DisabledException("Usuário não está ativo no sistema!");

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return new UsuarioSecurityModel(usuarioEntity.getLogin(), usuarioEntity.getSenha(), usuarioEntity.isAtivo(),
				authorities);
	}

	public void incluirUsuario(UsuarioModel usuarioModel) {

		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setAtivo(true);
		usuarioEntity.setLogin(usuarioModel.getLogin());
		usuarioEntity.setNome(usuarioModel.getNome());
		usuarioEntity.setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));

		this.usuarioRepository.save(usuarioEntity);
	}

	public void alterarUsuario(UsuarioModel usuarioModel) {

		Optional<UsuarioEntity> usuarioEntity = this.usuarioRepository.findById(usuarioModel.getCodigo());
		usuarioEntity.get().setAtivo(usuarioModel.isAtivo());
		usuarioEntity.get().setLogin(usuarioModel.getLogin());
		usuarioEntity.get().setNome(usuarioModel.getNome());
		if (!StringUtils.isEmpty(usuarioModel.getSenha())) {
			usuarioEntity.get().setSenha(new BCryptPasswordEncoder().encode(usuarioModel.getSenha()));
		}

		this.usuarioRepository.saveAndFlush(usuarioEntity.get());
	}

	public UsuarioModel consultarUsuario(Long codigoUsuario) {

		Optional<UsuarioEntity> usuarioEntity = this.usuarioRepository.findById(codigoUsuario);
		return new UsuarioModel(usuarioEntity.get().getCodigo(), usuarioEntity.get().getNome(),
				usuarioEntity.get().getLogin(), null, usuarioEntity.get().isAtivo());

	}

	public List<UsuarioModel> consultarUsuarios() {

		List<UsuarioModel> usuariosModel = new ArrayList<UsuarioModel>();
		List<UsuarioEntity> usuariosEntity = this.usuarioRepository.findAll();
		usuariosEntity.forEach(usuarioEntity -> {
			usuariosModel.add(new UsuarioModel(usuarioEntity.getCodigo(), usuarioEntity.getNome(),
					usuarioEntity.getLogin(), null, usuarioEntity.isAtivo()));
		});
		return usuariosModel;
	}

	public void excluirUsuario(Long codigoUsuario) {
		this.usuarioRepository.deleteById(codigoUsuario);
	}
}
