package com.api.rest.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.model.account.Account;
import com.api.rest.repository.AccountRepository;

@RestController
@RequestMapping(value = "/account")
@CrossOrigin(origins = "*")
public class IndexController {

	@Autowired
	private AccountRepository accountRepository;


	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Account> getAccountById(@PathVariable(value = "id") Long id) {
		
		Optional<Account> account = accountRepository.findById(id);

		return new ResponseEntity<Account>(account.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/find/{login}",produces = "application/json")
	public ResponseEntity<Account> getAccountByLogin(@PathVariable(value = "login") String login) {
		
		Account account = accountRepository.findUserByLogin(login);
		

		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Account> registerNewAccount(@RequestBody @Valid Account account) {

		for (int pos = 0; pos < account.getTelephones().size(); pos++) 
		{
			account.getTelephones().get(pos).setUser(account);
		}

		String password = new BCryptPasswordEncoder().encode(account.getPassword());
		account.setPassword(password);
		Account accountsave = accountRepository.save(account);
		
		//implement acess here

		return new ResponseEntity<Account>(accountsave, HttpStatus.OK);

	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Account> updateAccount(@RequestBody @Valid Account account) {

		for (int pos = 0; pos < account.getTelephones().size(); pos++) 
		{
			account.getTelephones().get(pos).setUser(account);
		}
		
		Account accountCheckPass = accountRepository.findUserByLogin(account.getLogin());
		
		if(account.getPassword() == null || account.getPassword().isEmpty())
		{
			account.setPassword(accountCheckPass.getPassword());
		}
		
		
		Account accountsave = accountRepository.save(account);
		

		return new ResponseEntity<Account>(accountsave, HttpStatus.OK);

	}
}
