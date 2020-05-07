package com.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.rest.model.account.Account;
import com.api.rest.repository.AccountRepository;

@Service
public class ImplementUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Account account = accountRepository.findUserByLogin(username);

		if (account == null) {
			throw new UsernameNotFoundException("Login does not exist!");
		}

		return new User(account.getLogin(), account.getPassword(), account.getAuthorities());
	}

	public void insertAcess(Long id) {

		accountRepository.insertRoleAcess(id);
	}

}