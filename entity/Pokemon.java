package com.example.PokemonCRUD.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Tag(name = "Definição da Classe Pokemons", description = "Definição de objetos pertencentes à classe pokemon")
@Entity
@Schema(description = "Entidade Pokemon que representa um pokemon.")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id Gerado por Auto-Increment", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Espécie do pokemon", example = "Pikachu")
    private String species;
    @Schema(description = "Nome do indivíduo", example = "Amarelinho")
    private String name;
    @Schema(description = "Primeiro tipo do pokemon", example = "Electric")
    private String type1;
    @Schema(description = "Segundo tipo do pokemon", example = "Electric")
    private String type2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id")
    @JsonBackReference
    @Schema(description = "Treinador a quem esse pokemon pertence", accessMode = Schema.AccessMode.READ_ONLY, hidden = true)
    private Trainer trainer;

    public Pokemon() {}
    public Pokemon(String species, String name, String type1, String type2) {
        this.species = species;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
    }

    @JsonProperty("Treinador")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    public String getTrainerName() {
        if (trainer != null)
            return trainer.getName() + " [" + trainer.getId() + "]";
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "Pokemon {id = " + id + ", species = " + species + ", trainer = " + trainer;
    }
}
