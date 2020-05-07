package com.api.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.model.account.Coming;
import com.api.rest.repository.ComingRepository;

@RestController
@RequestMapping(value = "/coming")
@CrossOrigin(origins = "*")
public class ComingController {
	
	@Autowired
	private ComingRepository comingRepository;
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<List<Coming>> getComingByAccountId(@PathVariable("id") Long id) {

		List<Coming>coming = (List<Coming>)comingRepository.findByAccountId(id);
		
		return new ResponseEntity<List<Coming>>(coming, HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Coming> registerComing(@RequestBody @Valid Coming coming) {
		
		Coming comingsave = comingRepository.save(coming);
		
		return new ResponseEntity<Coming>(comingsave, HttpStatus.OK);
	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Coming> updateComing(@RequestBody @Valid Coming coming) {
		
		Coming comingsave = comingRepository.save(coming);
		
		return new ResponseEntity<Coming>(comingsave, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String deleteComing(@PathVariable("id") Long id) {

		comingRepository.deleteById(id);
		
		return "Coming deleted!";
	}
}
