package com.example.PokemonCRUD.repository;
import com.example.PokemonCRUD.entity.Trainer;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Tag(name = "Repositório de Treinadores", description = "Cuida do acesso de dados da classe Treinador")
public interface TrainerRepository extends JpaRepository<Trainer, Long> {}
