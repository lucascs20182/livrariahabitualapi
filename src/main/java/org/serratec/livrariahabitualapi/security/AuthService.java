package org.serratec.livrariahabitualapi.security;

import org.serratec.livrariahabitualapi.entities.ClienteEntity;
import org.serratec.livrariahabitualapi.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	ClienteRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ClienteEntity client = repository.findByUsername(username);

		if (client == null) {
			throw new UsernameNotFoundException("Usuario não existe");
		}

		return new UserSS(client.getId(), client.getUsername(), client.getSenha());
	}

}