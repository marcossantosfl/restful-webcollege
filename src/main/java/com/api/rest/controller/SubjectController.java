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

import com.api.rest.model.account.Subject;
import com.api.rest.repository.SubjectRepository;

@RestController
@RequestMapping(value = "/subject")
@CrossOrigin(origins = "*")
public class SubjectController {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<List<Subject>> getSubjectByAccountId(@PathVariable("id") Long id) {

		List<Subject>subject = (List<Subject>)subjectRepository.findByAccountId(id);
		
		return new ResponseEntity<List<Subject>>(subject, HttpStatus.OK);
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Subject> registerSubject(@RequestBody @Valid Subject subject) {
		
		Subject subjectsave = subjectRepository.save(subject);
		
		return new ResponseEntity<Subject>(subjectsave, HttpStatus.OK);
	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Subject> updateSubject(@RequestBody @Valid Subject subject) {
		
		Subject subjectsave = subjectRepository.save(subject);
		
		return new ResponseEntity<Subject>(subjectsave, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String deleteSubject(@PathVariable("id") Long id) {

		subjectRepository.deleteById(id);
		
		return "Subject deleted!";
	}

}
