package com.example.PokemonCRUD.repository;

import com.example.PokemonCRUD.entity.Pokemon;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Tag(name = "Repositório de Pokemons", description = "Cuida do acesso de dados da classe Pokemon")
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {}
