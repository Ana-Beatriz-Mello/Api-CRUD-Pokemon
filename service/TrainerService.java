package com.example.PokemonCRUD.service;

import com.example.PokemonCRUD.entity.Pokemon;
import com.example.PokemonCRUD.entity.Trainer;
import com.example.PokemonCRUD.repository.PokemonRepository;
import com.example.PokemonCRUD.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private PokemonRepository pokemonRepository;

    public Trainer save(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    public Trainer findById(Long id) {
        return trainerRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        trainerRepository.deleteById(id);
    }

    public Trainer update(Long id, Trainer updatedTrainer) {
        Trainer existingTrainer = trainerRepository.findById(id).orElse(null);
        if (existingTrainer != null) {
            if (updatedTrainer.getName() != null) {
                existingTrainer.setName(updatedTrainer.getName());
            }
            if (updatedTrainer.getHomeRegion() != null) {
                existingTrainer.setHomeRegion(updatedTrainer.getHomeRegion());
            }
            return trainerRepository.save(existingTrainer);
        }

        return null;
    }

    public Trainer addPokemon(Long trainerId, Long pokemonId) {
        Trainer trainer = findById(trainerId);
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElse(null);
        if (trainer != null && pokemon != null) {
            trainer.getPokemons().add(pokemon);
            trainer.addPokemon(pokemon);
            return save(trainer);
        }
        return null;
    }

    public Trainer removePokemon(Long trainerId, Long pokemonId) {
        Trainer trainer = findById(trainerId);
        if (trainer != null) {
            Pokemon pokemon = trainer.getPokemons().stream().filter(b -> b.getId().equals(pokemonId)).findFirst().orElse(null);
            if (pokemon != null) {
                trainer.removePokemon(pokemon);
                return save(trainer);
            }
        }
        return null;
    }

}
