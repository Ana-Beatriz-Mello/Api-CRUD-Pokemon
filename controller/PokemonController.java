package com.example.PokemonCRUD.controller;

import com.example.PokemonCRUD.entity.Pokemon;
import com.example.PokemonCRUD.entity.Trainer;
import com.example.PokemonCRUD.service.PokemonService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Controller de Pokemons", description = "Realiza as operações CRUD para a classe Pokemon")
@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Operation(summary = "Cadastrar Pokemon", description = "Cadastra um pokemon na base de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Criado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro ao criar.")
    })
    @PostMapping
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.save(pokemon);
    }

    @Operation(summary = "Visualizar Pokemons", description = "Exibe todos os pokemons cadastrados na base")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Informação encontrada!"),
            @ApiResponse(responseCode = "404", description = "Nenhum pokemon encontrado.")
    })
    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonService.findAll();
    }

    @Operation(summary = "Visualizar um Pokemon específico", description = "Exibe as informações de um pokemon pelo seu Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Informação encontrada!"),
            @ApiResponse(responseCode = "404", description = "Pokemon não encontrado.")
    })
    @GetMapping("/{id}")
    public Pokemon getPokemonById(@PathVariable Long id) {
        return pokemonService.findById(id);
    }

    @Operation(summary = "Deletar Pokemon", description = "Deleta um pokemon da base de dados pelo seu Id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Pokemon não encontrado.")
    })
    @DeleteMapping("/{id}")
    public void deletePokemon(@PathVariable Long id) {
        pokemonService.deleteById(id);
    }

    @Operation(summary = "Atualizar Pokemon", description = "Atualiza as informações de um pokemon já exostente na base")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Pokemon não encontrado.")
    })
    @PutMapping("/{id}")
    public Pokemon updatePokemon(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        return pokemonService.update(id,pokemon);
    }
}
