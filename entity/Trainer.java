package com.example.PokemonCRUD.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;

import java.util.*;

@Tag(name = "Definição da Classe Treinadores", description = "Definição de objetos pertencentes à classe treinador")
@Entity
@Schema(description = "Entidade Treinador que representa um treinador de pokemons.")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id Gerado por Auto-Increment", accessMode = AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome do treinador", example = "Ash")
    private String name;
    @Schema(description = "Região onde nasceu o treinador", example = "Kanto")
    private String homeRegion;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonManagedReference
    @Schema(description = "Lista de pokemons pertencentes a um treinador", accessMode = AccessMode.READ_ONLY)
    private Set<Pokemon> pokemons = new HashSet<>();

    public Trainer() {}

    public Trainer(String name, String homeRegion) {
        this.name = name;
        this.homeRegion = homeRegion;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomeRegion() {
        return homeRegion;
    }

    public void setHomeRegion(String homeRegion) {
        this.homeRegion = homeRegion;
    }

    public Set<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(Set<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void removePokemon(Pokemon pokemon) {
        pokemons.remove(pokemon);
        pokemon.setTrainer(null);
    }

    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        pokemon.setTrainer(this);
    }

    @Override
    public String toString() {
        return "Trainer {id = " + id + ", name = " + name + ", da região de " + homeRegion;
    }

}
