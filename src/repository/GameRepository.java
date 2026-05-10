package repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.Game;

public class GameRepository {

    private final String FILE = "."
            + File.separator
            + "data"
            + File.separator
            + "Games.json";

    private final ObjectMapper mapper =
            new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public void save(Game game) throws IOException {
        List<Game> games = getGames();
        games.add(game);
        updateAll(games);
    }

    public List<Game> getGames() throws IOException {
        File file = new File(FILE);
        file.getParentFile().mkdirs();

        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        return mapper.readValue(
                file,
                new TypeReference<List<Game>>() {}
        );
    }

    public void updateAll(List<Game> games) throws IOException {
        File file = new File(FILE);
        file.getParentFile().mkdirs();

        mapper.writeValue(file, games);
    }

    // Buscar por ID
    public Game findById(int id) throws IOException {
        for (Game game : getGames()) {
            if (game.getId() == id) {
                return game;
            }
        }
        return null;
    }
    
    public List<Game> search(String text) throws IOException {

        List<Game> results = new ArrayList<>();
        
        for (Game game : getGames()) {
            if (game.getName()
                    .toLowerCase()
                    .contains(text.toLowerCase())) {
                results.add(game);
            }
        }
        
        return results;
    }
    
    public List<String> getCategories() throws IOException {
        return getGames().stream()
                .flatMap(game -> game.getGenres().stream())
                .distinct()
                .sorted()
                .toList();
    }
    
    public List<Game> searchByCategory(String category) throws IOException {
        return getGames().stream()
                .filter(game ->
                        game.getGenres().contains(category)
                )
                .toList();
    }

    // Eliminar por ID
    public void deleteById(int id) throws IOException {
        List<Game> games = getGames();
        games.removeIf(game -> game.getId() == id);
        updateAll(games);
    }

    // Actualizar por ID
    public void updateById(int id, Game updatedGame) throws IOException {
        List<Game> games = getGames();

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId() == id) {
                games.set(i, updatedGame);
                break;
            }
        }
        updateAll(games);
    }
}