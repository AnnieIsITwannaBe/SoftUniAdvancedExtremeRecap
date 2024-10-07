package DefiningClassesExercise.PokemonTrainer;

import java.util.List;

public class Trainer {
    public String name;

    public List<Pokemon> pokemons;

    public int numberOfBadges;

    public String getName() {
        return name;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public int getNumberOfBadges() {
        return numberOfBadges;
    }

    public void setNumberOfBadges(int numberOfBadges) {
        this.numberOfBadges = numberOfBadges;
    }

    public void increaseNumberOfBadges(int numberOfBadges){
        this.numberOfBadges += numberOfBadges;
    }
    public Trainer(String name, List<Pokemon> pokemons) {
        this.name = name;
        this.pokemons = pokemons;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}




