package DefiningClassesExercise.PokemonTrainer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final String BEGIN_TOURNAMENT = "Tournament";

    private static final String BREAK_COMMAND = "End";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //next time just use a set to ensure uniqueness of the variables:
        List<Trainer> trainers = assignPokemonsToTrainers(input, scanner);

        input = scanner.nextLine();
        while (!BREAK_COMMAND.equals(input)) {
            String element = input;

//            Trainer currentTrainer = trainers.stream().filter(trainer -> trainer.getPokemons().stream()
//                    .filter(pokemon -> pokemon.getElement().equals(element)).isParallel()).findAny().orElse(null);

            //i simply have little knowledge how this is to be done using stream api
            boolean hasEligiblePokemon = false;
            for (Trainer trainer : trainers) {
                List<Pokemon> currentTrainerCaptures = trainer.getPokemons();
                for (Pokemon currentPokemon : currentTrainerCaptures) {
                    if (currentPokemon.getElement().equals(element)) {
                        hasEligiblePokemon = true;
                        break;
                    }
                }

                if (hasEligiblePokemon) {
                    trainer.increaseNumberOfBadges(trainer.getNumberOfBadges() + 1);
                } else {
                    for (int i = 0; i < currentTrainerCaptures.size(); i++) {
                        Pokemon currentPokemon = currentTrainerCaptures.get(i);
                        currentPokemon.setHealth(currentPokemon.getHealth() - 10);
                        if(currentPokemon.getHealth() <= 0){
                            currentTrainerCaptures.remove(currentPokemon);
                        }

                    }
                }
            }


            input = scanner.nextLine();
        }


    }

    private static List<Trainer> assignPokemonsToTrainers(String input, Scanner scanner) {
        List<Trainer> trainers = new ArrayList<>();
        while (!BEGIN_TOURNAMENT.equals(input)) {
            String[] tokens = scanner.nextLine().split("\\s+");

            String trainerName = tokens[0];

            String pokemonName = tokens[1];
            String pokemonElement = tokens[2];
            int pokemonHealth = Integer.parseInt(tokens[3]);

            Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);
            Trainer trainer = new Trainer(trainerName, new ArrayList<>());

            if (!trainers.contains(trainer)) {
                trainer.getPokemons().add(pokemon);
                trainers.add(trainer);
            } else {
                Trainer alreadyExistingTrainer = trainers.stream()
                        .filter(trainer1 -> trainer1.getName().equals(trainerName)).findFirst().orElse(null);

                assert alreadyExistingTrainer != null;
                alreadyExistingTrainer.getPokemons().add(pokemon);
            }

            input = scanner.nextLine();
        }
        return trainers;
    }
}
