package vass.assessment.server.security.services.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vass.assessment.server.security.models.Usuario;
import vass.assessment.server.security.repository.UsuarioRepository;
import vass.assessment.server.security.utils.Constantes;

@Service
public class UsuarioDetailService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepo.findByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException(Constantes.MSG_USERNAME_NOT_FOUND);
		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(usuario.getPerfil().getPerfil()));

		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

}
