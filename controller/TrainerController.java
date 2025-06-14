package com.example.PokemonCRUD.controller;

import com.example.PokemonCRUD.entity.Pokemon;
import com.example.PokemonCRUD.entity.Trainer;
import com.example.PokemonCRUD.service.TrainerService;
import com.example.PokemonCRUD.repository.PokemonRepository;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Controller de Treinadores", description = "Realiza as operações CRUD para a classe Treinador")
@RestController
@RequestMapping("/trainers")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;
    @Autowired
    private PokemonRepository pokemonRepository;

    @Operation(summary = "Cadastrar treinador", description = "Cria um novo treinador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Criado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro ao criar.")
    })
    @PostMapping
    public Trainer createTrainer(@RequestBody Trainer trainer) {
        return trainerService.save(trainer);
    }

    @Operation(summary = "Visualizar treinadores", description = "retorna todos os treinadores cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Informação encontrada!"),
            @ApiResponse(responseCode = "404", description = "Nenhum treinador encontrado.")
    })
    @GetMapping
    public List<Trainer> getAllTrainers() {
        return trainerService.findAll();
    }

    @Operation(summary = "Visualizar treinador específico", description = "Retorna um treindor pelo seu Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Informação encontrada!"),
            @ApiResponse(responseCode = "404", description = "Nenhum treinador encontrado.")
    })
    @GetMapping("/{id}")
    public Trainer getTrainerById(@PathVariable Long id) {
        return trainerService.findById(id);
    }

    @Operation(summary = "Deletar treinador", description = "Deleta um treinador pelo seu Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Treinador deletado!"),
            @ApiResponse(responseCode = "404", description = "Nenhum treinador encontrado com esse Id.")
    })
    @DeleteMapping("/{id}")
    public void deleteTrainer(@PathVariable Long id) {
        trainerService.deleteById(id);
    }

    @Operation(summary = "Assinalar um pokemon a um treinador", description = "Aloca um pokemon já registrado para um treinador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pokemon adicionado para o treinador!"),
            @ApiResponse(responseCode = "404", description = "O treinador ou o pokemon não foi/foram encontrado/s."),
            @ApiResponse(responseCode = "500", description = "O Pokemon já possui um treinador.")
    })
    @PostMapping("/{trainerId}/{pokemonId}")
    public Trainer addPokemon(@PathVariable Long trainerId, @PathVariable Long pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElse(null);
        if (pokemon != null && pokemon.getTrainer() != null) {
            throw new IllegalArgumentException("Não pode roubar pokemon!");
        }
        return trainerService.addPokemon(trainerId, pokemonId);
    }

    @Operation(summary = "Deletar Pokemon de um Treinador", description = "Deleta um pokemon de um treinador de acordo com os Ids")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pokemon deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum treinador encontrado.")
    })
    @DeleteMapping("/{trainerId}/pokemons/{pokemonId}")
    //@Hidden
    public Trainer removePokemon(@PathVariable Long trainerId, @PathVariable Long pokemonId) {
        return trainerService.removePokemon(trainerId, pokemonId);
    }

    @Operation(summary = "Alterar Treinador", description = "Altera as informações de um treinador de acordo com input")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Treinador atualizado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Nenhum treinador encontrado.")
    })
    @PutMapping("/{id}")
    public Trainer updateTrainer(@PathVariable Long id, @RequestBody Trainer trainer) {
        return trainerService.update(id, trainer);
    }

}
