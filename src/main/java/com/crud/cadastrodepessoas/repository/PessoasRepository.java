package com.crud.cadastrodepessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.cadastrodepessoas.model.Pessoas;

@Repository
public interface PessoasRepository extends JpaRepository <Pessoas, Long>{

}
