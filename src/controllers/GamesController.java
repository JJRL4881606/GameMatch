package controllers;

import java.io.IOException;
import java.util.List;

import models.Game;
import repository.GameRepository;
import views.GamesView;
import views.MainView;

public class GamesController {

    private MainView mainView;
    private GamesView view;
    private GameRepository repository;

    public GamesController(MainView mainView) {

        this.mainView = mainView;
        this.view = mainView.gamesPanel;

        repository = new GameRepository();

        loadGames();
        initListeners();
    }

    private void initListeners() {

        mainView.menuGames.addActionListener(e -> {

            mainView.showView(MainView.GAMES);
            mainView.menuHome.setEnabled(true);
        });
    }

    private void loadGames() {

        try {

            List<Game> games = repository.getGames();
            view.setGames(games);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}