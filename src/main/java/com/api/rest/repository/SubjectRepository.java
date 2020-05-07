package com.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.rest.model.account.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{ 
	
	@Query(value="select * from Subject where account_id = ?1", nativeQuery = true)
	List<Subject> findByAccountId(Long id);

}

