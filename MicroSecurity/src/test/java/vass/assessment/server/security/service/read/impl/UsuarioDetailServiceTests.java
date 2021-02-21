package vass.assessment.server.security.service.read.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import vass.assessment.server.security.models.Usuario;
import vass.assessment.server.security.repository.UsuarioRepository;
import vass.assessment.server.security.services.read.impl.UsuarioDetailService;
import vass.assessment.server.security.utils.Constantes;
import vass.assessment.server.security.utils.DataMocks;

@SpringBootTest
public class UsuarioDetailServiceTests {
	
	@InjectMocks
	UsuarioDetailService usuarioDetailService;

	@Mock
	UsuarioRepository repoMock;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void loadUserByUsernameOk() {
		Usuario usuario = DataMocks.retornarUsuario();
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		User user = new User("junior", "pass", true, true, true, true,
				authorities);
		when(repoMock.findByUsername("junior")).thenReturn(usuario);
		UserDetails userdetails = usuarioDetailService.loadUserByUsername("junior");
		Assertions.assertThat(user).isInstanceOfAny(UserDetails.class);
		Assertions.assertThat(user.getUsername()).isEqualTo(userdetails.getUsername());
		
	}
	
	@Test
	void loadUserByUsernameNotFound() {
		when(repoMock.findByUsername("junior")).thenReturn(null);
		try {
			 usuarioDetailService.loadUserByUsername("junior");
		} catch (UsernameNotFoundException e) {
			Assertions.assertThat(Constantes.MSG_USERNAME_NOT_FOUND).isEqualTo(e.getMessage());
		}		
		
	}

}
