package com.example.PokemonCRUD.service;

import com.example.PokemonCRUD.entity.Pokemon;
import com.example.PokemonCRUD.entity.Trainer;
import com.example.PokemonCRUD.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public Pokemon save(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    public Pokemon findById(Long id) {
        return pokemonRepository.findById(id).orElse(null);
    }

    public Pokemon update(Long id, Pokemon updatedPokemon) {
        Pokemon existingPokemon = pokemonRepository.findById(id).orElse(null);
        if (existingPokemon != null) {
            if (updatedPokemon.getName() != null) {
                existingPokemon.setName(updatedPokemon.getName());
            }
            if (updatedPokemon.getSpecies() != null) {
                existingPokemon.setSpecies(updatedPokemon.getSpecies());
            }
            if (updatedPokemon.getType1() != null) {
                existingPokemon.setType1(updatedPokemon.getType1());
            }
            if (updatedPokemon.getType2() != null) {
                existingPokemon.setType2(updatedPokemon.getType2());
            }
            return pokemonRepository.save(existingPokemon);
        }
        return null;
    }

    public void deleteById(Long id) {
        pokemonRepository.deleteById(id);
    }

}
