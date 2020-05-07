package com.api.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.model.account.Telephone;

@Repository
public interface TelephoneRepository extends CrudRepository<Telephone, Long>{ 

}
