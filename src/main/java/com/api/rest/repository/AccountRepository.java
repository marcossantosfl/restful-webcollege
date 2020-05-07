package com.api.rest.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.rest.model.account.Account;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long>{
	
	@Query("select u from Account u where u.login = ?1")
	Account findUserByLogin(String login);
	
	@Query("select u from Account u where u.login like %?1%")
	List<Account> findUserByName(String login);	
	
	//@Query(value = "select constraint_name FROM information_schema.constraint_column_usage where table_name = 'account_role' and column_name = 'role_id' and constraint_name <> 'unique_role_user';", nativeQuery = true)
	//String getConstraintRole();

	@Transactional
	@Modifying
	@Query(value = "insert into account_role (account_id, role_id) values (?1,(select id from role where role_name = 'ROLE_NORMAL'));", nativeQuery = true)
	void insertRoleAcess(Long id);

	default Page<Account> findUserByNamePage(String login, PageRequest pageRequest)
	{
		Account account = new Account();
		account.setLogin(login);
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("login", ExampleMatcher.GenericPropertyMatchers.contains()
						.ignoreCase());
		
		Example<Account> list = Example.of(account, exampleMatcher);
		
		Page<Account> ret = findAll(list, pageRequest);
		
		return ret;
	}

}
