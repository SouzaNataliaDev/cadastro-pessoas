package com.crud.cadastrodepessoas.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.cadastrodepessoas.model.Pessoas;
import com.crud.cadastrodepessoas.repository.PessoasRepository;

@RestController
public class PessoasController {

	PessoasRepository pessoasRepository;

	public PessoasController(PessoasRepository pessoasRepository) {
		this.pessoasRepository = pessoasRepository;
	}

	@GetMapping
	public ResponseEntity<List<Pessoas>> pegarPessoas() {
		return new ResponseEntity<>(pessoasRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoas> pegarPessoasId(@PathVariable Long id) {
		Optional<Pessoas> op = pessoasRepository.findById(id);
		if (op.isPresent()) {
			return new ResponseEntity<>(op.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<Pessoas> criarPessoas(@Valid @RequestBody Pessoas pessoas) {
		return new ResponseEntity<>(pessoasRepository.save(pessoas), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity <Pessoas> atualizarPessoas(@Valid @RequestBody Pessoas pessoas) {
		if (pessoasRepository.existsById(pessoas.getId())) {
			return new ResponseEntity<> (pessoasRepository.save(pessoas), HttpStatus.OK);
		}
		return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity <Object> deletarPessoas(@PathVariable Long id) {
		if (pessoasRepository.existsById(id)) {
			pessoasRepository.deleteById(id);
			return new ResponseEntity <> ( HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity <> (HttpStatus.NOT_FOUND);
	}

}
