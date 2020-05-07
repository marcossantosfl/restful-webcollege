package com.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.rest.model.account.Coming;

@Repository
public interface ComingRepository extends JpaRepository<Coming, Long>{ 
	
	@Query(value="select * from Coming where account_id = ?1", nativeQuery = true)
	List<Coming> findByAccountId(Long id);
}